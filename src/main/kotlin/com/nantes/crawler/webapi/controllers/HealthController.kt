package com.nantes.crawler.webapi.controllers

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag

@RestController
@RequestMapping("/api")
@Tag(name = "Health Check", description = "Application health monitoring endpoints")
class HealthController {

    @GetMapping("")
    @Operation(
        summary = "Health check",
        description = "Returns the health status of the application"
    )
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200",
                description = "Application is healthy",
                content = [Content(
                    mediaType = "text/plain",
                    schema = Schema(type = "string", example = "Ok")
                )]
            )
        ]
    )
    fun getHealth() = ResponseEntity.ok("Ok")
}