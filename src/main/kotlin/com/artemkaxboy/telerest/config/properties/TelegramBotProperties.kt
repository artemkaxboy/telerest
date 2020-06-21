package com.artemkaxboy.telerest.config.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.convert.DurationUnit
import org.springframework.validation.annotation.Validated
import java.time.Duration
import java.time.temporal.ChronoUnit
import javax.validation.constraints.NotBlank

/** Amount of attempts to initially connect to Telegram API */
const val DEFAULT_RECONNECTION_COUNT = 10

/** Default delay between connection attempts */
const val DEFAULT_RECONNECTION_DELAY_SECONDS = 3L

@ConfigurationProperties("telegram")
@Validated
class TelegramBotProperties {

    /** Telegram bot token */
    @NotBlank(message = "Telegram token must be provided. Use config files or env variables.")
    lateinit var token: String

    /** Default chat ID. You can send messages without implicitly set chat id if it set. */
    var defaultChatId: String? = null

    val reconnection = Reconnection()

    class Reconnection {

        /** Amount of attempts to initially connect to Telegram API */
        var count = DEFAULT_RECONNECTION_COUNT

        /** Default delay between connection attempts */
        @DurationUnit(ChronoUnit.SECONDS)
        var delay: Duration = Duration.ofSeconds(DEFAULT_RECONNECTION_DELAY_SECONDS)
    }
}
