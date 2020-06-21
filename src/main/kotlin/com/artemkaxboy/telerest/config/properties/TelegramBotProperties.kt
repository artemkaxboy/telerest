package com.artemkaxboy.telerest.config.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.validation.annotation.Validated
import javax.validation.constraints.NotBlank

@ConfigurationProperties("telegram")
@Validated
class TelegramBotProperties {

    @NotBlank(message = "Telegram token must be provided. Use config files or env variables.")
    lateinit var token: String

    var defaultChatId: String? = null
}
