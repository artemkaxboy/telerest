package com.artemkaxboy.telerest.services

import com.elbekD.bot.Bot
import org.slf4j.LoggerFactory

class TelegramBot(
    private val botName: String,
    token: String
) {

    private val bot = Bot.createPolling(botName, token)

    fun start(): Boolean {
        return runCatching {
            logger.info("Telegram Bot {$botName} starting...")
            bot.start()
            logger.info("Telegram Bot {$botName} started successfully")
            true
        }.getOrElse {
            logger.error("Cannot start Telegram Bot {$botName}: ${it.message}")
            false
        }
    }

    // bot.onCommand("/start") { msg, _ ->
    //     bot.sendMessage(msg.chat.id, "Hello World!")
    // }
    //
    // bot.onCommand("/echo") { msg, opts ->
    //     bot.sendMessage(msg.chat.id, "${msg.text} ${opts ?: ""}")
    // }

    companion object {
        private val logger = LoggerFactory.getLogger(this::class.java.declaringClass)
    }
}
