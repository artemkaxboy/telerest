package com.artemkaxboy.telerest.dto

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty

@ApiModel(value = "Message", description = "Basic message to send to telegram chat.")
data class MessageDto(

    @ApiModelProperty(
        value = "Target chat id.",
        required = true,
        allowEmptyValue = false,
        example = "30811102"
    )
    val chatId: Long,

    @ApiModelProperty(
        value = "Message text to send.",
        required = true,
        allowEmptyValue = false,
        example = "Hi, there!"
    )
    val text: String
)
