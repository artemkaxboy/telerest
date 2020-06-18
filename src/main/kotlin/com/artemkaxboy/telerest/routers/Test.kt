package com.artemkaxboy.telerest.routers

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.body
import org.springframework.web.reactive.function.server.router
import reactor.core.publisher.Flux

@Configuration
class SimpleRoute {
    @Suppress("ReactiveStreamsUnusedPublisher")
    @Bean
    fun route() = router {
        GET("/route") { _ ->
            ServerResponse.ok()
                .body(Flux.range(1, 5))
        }
    }
}
