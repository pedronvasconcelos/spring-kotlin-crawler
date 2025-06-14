package com.nantes.crawler.webapi.controllers

import org.springframework.web.bind.annotation.*
import org.springframework.http.ResponseEntity
import org.springframework.http.HttpStatus
import com.nantes.crawler.application.dto.CreateCrawlRequest
import com.nantes.crawler.application.dto.CreateCrawlResponse
import com.nantes.crawler.application.dto.CreateCrawlRequestHandler
import com.nantes.crawler.application.usecase.GetCrawlRequestHandler
import com.nantes.crawler.application.usecase.GetCrawlResponse
import com.nantes.crawler.webapi.controllers.endpoints.CrawlApi
import java.util.*

@RestController
class CrawlController(
    private val createCrawlRequestHandler: CreateCrawlRequestHandler,
    private val getCrawlRequestHandler: GetCrawlRequestHandler
) : CrawlApi {

    override fun createCrawl(request: CreateCrawlRequest): ResponseEntity<CreateCrawlResponse> {
        val response = createCrawlRequestHandler.handle(request)
        return ResponseEntity(response, HttpStatus.CREATED)
    }

    override fun getCrawl(id: UUID): ResponseEntity<GetCrawlResponse> {

        val response = getCrawlRequestHandler.handle(id)
        return ResponseEntity(response, HttpStatus.OK)
    }
}