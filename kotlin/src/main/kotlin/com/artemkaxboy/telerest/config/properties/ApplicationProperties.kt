package com.artemkaxboy.telerest.config.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration
import org.springframework.validation.annotation.Validated
import javax.validation.constraints.NotBlank

@Configuration
@ConfigurationProperties("application")
@Validated
class ApplicationProperties {

    /** Application version */
    @NotBlank(message = "Application version must be set.")
    lateinit var version: String
}
