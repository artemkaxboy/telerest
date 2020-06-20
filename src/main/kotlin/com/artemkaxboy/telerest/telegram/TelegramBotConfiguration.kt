package com.artemkaxboy.telerest.telegram

import com.artemkaxboy.telerest.service.TelegramBot
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@EnableConfigurationProperties(TelegramBotProperties::class)
class TelegramBotConfiguration (
    val telegramBotProperties: TelegramBotProperties
) {

    @Bean
    fun getTelegramBot(): TelegramBot {
        return TelegramBot(telegramBotProperties.botName, telegramBotProperties.token)
    }
}
