package com.artemkaxboy.telerest.listeners

import com.artemkaxboy.telerest.services.TelegramBot
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
class AppEvents(
    val telegramBot: TelegramBot
) {

    @EventListener(ApplicationReadyEvent::class)
    fun startApp() {
        GlobalScope.launch {

            repeat(10) {
                if (telegramBot.start()) {
                    return@launch
                }
                delay(10000)
            }
        }
    }
}
