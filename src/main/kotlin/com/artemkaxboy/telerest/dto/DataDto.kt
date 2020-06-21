package com.artemkaxboy.telerest.dto

import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty

@ApiModel(value = "Error", description = "Basic error information.")
data class DataDto(

    @ApiModelProperty(
        value = "Requested items.",
        example = "todo",
        required = true
    )
    val items: List<Any>
)
