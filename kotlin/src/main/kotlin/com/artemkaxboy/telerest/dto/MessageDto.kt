package com.artemkaxboy.telerest.dto

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty

@ApiModel(value = "Message", description = "Basic message to send to telegram chat.")
data class MessageDto(

    @ApiModelProperty(
        value = "Target chat id.",
        example = "30811102"
    )
    val chatId: String? = null,

    @Suppress("SpellCheckingInspection") // password may content anything
    @ApiModelProperty(
        value = "Password to send message. Required if app started with password protection.",
        example = "uhfewuia67t34t78hagre8h9"
    )
    val password: String? = null,

    @ApiModelProperty(
        value = "Message text to send.",
        required = true,
        allowEmptyValue = false,
        example = "Hi, there!"
    )
    val text: String
)
