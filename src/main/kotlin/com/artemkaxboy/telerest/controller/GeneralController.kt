package com.artemkaxboy.telerest.controller

import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono

@RestController
@RequestMapping(value = ["api/$API_VERSION"])
class GeneralController (
    @Value("\${application.version}")
    val version: String
) {

    @ApiOperation(value = "Get app version")
    @GetMapping("/version")
    private fun getVersion(): Mono<String> {
        return version.toMono()
    }
}
