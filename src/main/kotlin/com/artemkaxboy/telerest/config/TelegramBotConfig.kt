package com.artemkaxboy.telerest.config

import com.artemkaxboy.telerest.service.TelegramBot
import com.artemkaxboy.telerest.config.properties.TelegramBotProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@EnableConfigurationProperties(TelegramBotProperties::class)
class TelegramBotConfig(
    val telegramBotProperties: TelegramBotProperties
) {

    @Bean
    fun getTelegramBot(): TelegramBot {
        return TelegramBot(
            telegramBotProperties.token,
            defaultChatId = telegramBotProperties.defaultChatId
        )
    }
}
