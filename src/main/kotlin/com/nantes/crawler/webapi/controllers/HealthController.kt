package com.nantes.crawler.webapi.controllers

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api")
class HealthController {

    @GetMapping("")
    fun getHealth()   =
        ResponseEntity.ok("Ok")
}