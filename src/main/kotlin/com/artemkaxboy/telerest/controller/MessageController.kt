package com.artemkaxboy.telerest.controller

import com.artemkaxboy.telerest.dto.MessageDto
import com.artemkaxboy.telerest.dto.ResponseDto
import com.artemkaxboy.telerest.service.TelegramBot
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import mu.KotlinLogging
import org.springframework.http.server.reactive.ServerHttpRequest
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono

const val API_VERSION = "v1"

// https://www.baeldung.com/spring-webflux
@RestController
@RequestMapping(value = ["api/$API_VERSION"])
class MessageController (
    val telegramBot: TelegramBot
){

    @ApiOperation(value = "Post message", response = Int::class)
    @PostMapping("/message")
    private fun postMessage(

        @ApiParam(value = "Message data")
        @RequestBody(required = true)
        message: MessageDto,

        request: ServerHttpRequest
    ): Mono<ResponseDto> {

        return ResponseDto
            .getResponse(request) { telegramBot.sendMessage(message.text, message.chatId) }
            .toMono()
    }

    companion object {
        private val logger = KotlinLogging.logger { }
    }
}
