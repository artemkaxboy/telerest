package com.artemkaxboy.telerest.config.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.validation.annotation.Validated
import javax.validation.constraints.NotBlank

@ConfigurationProperties("telegram")
@Validated
class TelegramBotProperties {

    @NotBlank(message = "Bot token must be provided")
    lateinit var token: String

    @NotBlank(message = "Bot username must be provided")
    lateinit var botName: String
}
