package com.artemkaxboy.telerest.config.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.validation.annotation.Validated
import javax.validation.constraints.NotBlank

@ConfigurationProperties("telegram")
@Validated
class TelegramBotProperties {

    @NotBlank(message = "TELEGRAM_TOKEN environment variable must be provided")
    lateinit var token: String
}
