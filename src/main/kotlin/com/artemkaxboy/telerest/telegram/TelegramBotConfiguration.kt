package com.artemkaxboy.telerest.telegram

import com.artemkaxboy.telerest.services.TelegramBot
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@EnableConfigurationProperties(TelegramBotProperties::class)
class TelegramBotConfiguration (
    val telegramBotProperties: TelegramBotProperties
) {

    @Bean
    fun telegramBot(): TelegramBot {
        return TelegramBot(telegramBotProperties.botName, telegramBotProperties.token)
    }
}
