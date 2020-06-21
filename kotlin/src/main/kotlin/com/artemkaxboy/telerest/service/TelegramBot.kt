package com.artemkaxboy.telerest.service

import com.artemkaxboy.telerest.config.properties.DEFAULT_RECONNECTION_COUNT
import com.artemkaxboy.telerest.config.properties.DEFAULT_RECONNECTION_DELAY_SECONDS
import com.elbekD.bot.Bot
import com.elbekD.bot.types.Message
import com.elbekD.bot.types.Update
import kotlinx.coroutines.delay
import mu.KotlinLogging
import java.security.AccessControlException
import java.time.Duration

private const val COMMAND_START = "/start"

// must be added to bot commands with @BotFather's help https://stackoverflow.com/a/34458436/1452052
private const val COMMAND_CHAT_ID = "/chatid"

/** Doesn't make any sense but required by the library */
private const val DUMMY_BOT_NAME = "bot_name"

class TelegramBot(
    token: String,
    val password: String? = null,
    val defaultChatId: String? = null,
    val reconnectionCount: Int = DEFAULT_RECONNECTION_COUNT,
    val reconnectionDelay: Duration = Duration.ofSeconds(DEFAULT_RECONNECTION_DELAY_SECONDS)
) {

    private val bot = Bot.createPolling(DUMMY_BOT_NAME, token)

    /**
     * Starts the bot.
     *
     * @return true if bot started correct, false - otherwise.
     */
    suspend fun start(): Boolean {
        configureBot()

        repeat(reconnectionCount) {
            if (startBot()) {
                return true
            }
            delay(reconnectionDelay.toMillis())
        }
        return false
    }

    fun sendMessage(text: String, password: String? = null, chatId: String? = null): Message {
        if (compareValues(this.password, password) != 0) {
            throw AccessControlException("Wrong password")
        }

        val sendTo =
            chatId ?: defaultChatId ?: throw IllegalArgumentException("Neither chat id nor default chat id provided")

        return bot.sendMessage(sendTo, text).get()
    }

    private fun configureBot() {
        bot.onCommand(COMMAND_CHAT_ID, this::onChatId)
        bot.onCommand(COMMAND_START, this::onChatId)
        bot.onAnyUpdate(this::onAnyUpdate)
    }

    private fun startBot(): Boolean {
        return runCatching {
            logger.info { "Telegram bot starting..." }
            bot.start()
            logger.info { "Telegram bot started successfully" }
            true
        }.getOrElse {
            logger.error { "Cannot start Telegram bot: ${it.message}" }
            false
        }
    }

    @Suppress("RedundantSuspendModifier", "UNUSED_PARAMETER") // signature is fixed to use in Bot.onCommand
    private suspend fun onChatId(message: Message, opts: String?) {
        bot.sendMessage(message.chat.id, "ChatID: ${message.chat.id}")
    }

    @Suppress("RedundantSuspendModifier") // signature is fixed to use in Bot.onCommand
    private suspend fun onAnyUpdate(update: Update) {
        logger.info { update }
    }

    companion object {
        private val logger = KotlinLogging.logger { }
    }
}
