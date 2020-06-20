package com.artemkaxboy.telerest.service

import com.artemkaxboy.telerest.tool.loggerFor
import com.elbekD.bot.Bot
import com.elbekD.bot.types.Message
import kotlinx.coroutines.delay

private const val COMMAND_START = "/start"
private const val COMMAND_ECHO = "/echo"

private const val BOT_NAME = "bot_name"

class TelegramBot(
    token: String
) {

    private val bot = Bot.createPolling(BOT_NAME, token)

    /**
     * Starts the bot.
     *
     * @return true if bot started correct, false - otherwise.
     */
    suspend fun start(): Boolean {
        configureBot()

        repeat(3) {
            if (startBot()) {
                return true
            }
            delay(1000)
        }
        return false
    }

    fun sendMessage(chatId: Any, text: String): Int {
        return bot.sendMessage(chatId, text).get().message_id
    }

    private fun configureBot() {
        bot.onCommand(COMMAND_START, this::onStartCommand)
        bot.onCommand(COMMAND_ECHO, this::onEchoCommand)
    }

    private fun startBot(): Boolean {
        return runCatching {
            logger.info("Telegram bot starting...")
            bot.start()
            logger.info("Telegram bot started successfully")
            true
        }.getOrElse {
            logger.error("Cannot start Telegram bot: ${it.message}")
            false
        }
    }

    @Suppress("RedundantSuspendModifier", "UNUSED_PARAMETER") // signature is fixed to use in Bot.onCommand
    private suspend fun onStartCommand(message: Message, opts: String?) {
        val name = message.from?.first_name
            ?: message.from?.username
            ?: "guest"
        bot.sendMessage(message.chat.id, "Hello, $name!")
    }

    @Suppress("RedundantSuspendModifier") // signature is fixed to use in Bot.onCommand
    private suspend fun onEchoCommand(message: Message, opts: String?) {
        val reply = message.text?.removePrefix(COMMAND_ECHO)?.trim()?.takeUnless { it.isEmpty() }
            ?: opts
            ?: "---"
        bot.sendMessage(message.chat.id, reply)
    }

    companion object {
        private val logger = loggerFor(this::class.java)
    }
}
