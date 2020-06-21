package com.artemkaxboy.telerest.dto

import com.artemkaxboy.telerest.config.CURRENT_API_VERSION
import io.swagger.annotations.ApiModel
import io.swagger.annotations.ApiModelProperty
import org.springframework.http.server.reactive.ServerHttpRequest
import kotlin.random.Random

@ApiModel(value = "Message", description = "Basic message to send to telegram chat.")
data class ResponseDto(

    @ApiModelProperty(
        value = "Used API version.",
        example = "v1",
        required = false
    )
    val apiVersion: String = CURRENT_API_VERSION,

    @ApiModelProperty(
        value = "Request ID.",
        example = "todo",
        required = false
    )
    val id: String? = null,

    @ApiModelProperty(
        value = "Requested method.",
        example = "todo",
        required = false
    )
    val method: String? = null,

    @ApiModelProperty(
        value = "Occurred error or null.",
        example = "todo",
        required = false
    )
    val data: DataDto? = null,

    @ApiModelProperty(
        value = "Occurred error or null.",
        example = "todo",
        required = false
    )
    val error: ErrorDto? = null
) {

    companion object {

        fun getResponse(request: ServerHttpRequest, block: () -> Any): ResponseDto {

            return runCatching { wrap(request, block()) }
                .getOrElse { wrapError(request, it) }
        }

        private fun wrapError(request: ServerHttpRequest, error: Throwable): ResponseDto {
            return ErrorDto(
                Random.nextInt(10_000), // todo add later
                error.message,
                errors = listOf(ErrorDetailDto.fromThrowable(error))
            )
                .let {
                    ResponseDto(
                        id = request.id,
                        method = request.path.value(),
                        error = it
                    )
                }
        }

        private fun wrap(request: ServerHttpRequest, result: Any): ResponseDto {
            /* wrap result into list */
            val list = when (result) {
                is List<*> -> result.filterNotNull()
                else -> listOf(result)
            }

            return ResponseDto(
                id = request.id,
                method = request.path.value(),
                data = DataDto(list)
            )
        }
    }
}
