package com.artemkaxboy.telerest.controller

import com.artemkaxboy.telerest.config.properties.ApplicationProperties
import com.artemkaxboy.telerest.dto.ResponseDto
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.http.server.reactive.ServerHttpRequest
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono

@Api(tags = ["General information"], description = "Get basic service information")
@RestController
@RequestMapping(value = ["api/$API_VERSION"])
class GeneralController(
    val applicationProperties: ApplicationProperties
) {

    @ApiOperation(value = "Get app version")
    @GetMapping("/version")
    private fun getVersion(request: ServerHttpRequest): Mono<ResponseDto> {
        return ResponseDto.getResponse(request) { applicationProperties.version }.toMono()
    }
}
