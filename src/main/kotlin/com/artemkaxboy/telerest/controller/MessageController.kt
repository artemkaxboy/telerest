package com.artemkaxboy.telerest.controller

import com.artemkaxboy.telerest.dto.MessageDto
import com.artemkaxboy.telerest.service.TelegramBot
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono

// https://www.baeldung.com/spring-webflux
@RestController
@RequestMapping(value = ["api"])
class MessageController (
    @Value("\${chat_id}")
    val chatId: Any,

    val telegramBot: TelegramBot
){

    @PostMapping("/message")
    private fun postMessage(
        @RequestBody(required = true)
        message: MessageDto
    ): Mono<Int> {

        return telegramBot.sendMessage(chatId, message.text)
            .also { logger.trace { "Message {$it} sent" } }
            .toMono()
    }

    companion object {
        private val logger = KotlinLogging.logger { }
    }
}
