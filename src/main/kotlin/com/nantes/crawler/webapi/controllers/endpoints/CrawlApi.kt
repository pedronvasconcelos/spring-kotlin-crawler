package com.nantes.crawler.webapi.controllers.endpoints
import com.nantes.crawler.application.dto.CreateCrawlRequest
import com.nantes.crawler.application.dto.CreateCrawlResponse
import com.nantes.crawler.application.usecase.GetCrawlResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RequestMapping("/api/crawls")
@Tag(name = "Crawl Management", description = "Operations for managing web crawling tasks")
interface CrawlApi {

    @PostMapping
    @Operation(
        summary = "Create a new crawl task",
        description = "Creates a new web crawling task for the specified keyword"
    )
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "201",
                description = "Crawl task created successfully",
                content = [Content(
                    mediaType = "application/json",
                    schema = Schema(implementation = CreateCrawlResponse::class)
                )]
            ),
            ApiResponse(
                responseCode = "400",
                description = "Invalid request data",
                content = [Content()]
            ),
            ApiResponse(
                responseCode = "500",
                description = "Internal server error",
                content = [Content()]
            )
        ]
    )
    fun createCrawl(
        @Parameter(description = "Crawl request containing the keyword to search for", required = true)
        @RequestBody request: CreateCrawlRequest
    ): ResponseEntity<CreateCrawlResponse>

    @GetMapping("/{id}")
    @Operation(
        summary = "Get a crawl task by ID",
        description = "Retrieves the details of a specific web crawling task by its unique ID"
    )
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200", description = "Crawl task found", content = [Content(
                    mediaType = "application/json",
                    schema = Schema(implementation = GetCrawlResponse::class)
                )]
            ),
            ApiResponse(responseCode = "400", description = "Invalid ID format", content = [Content()]),
            ApiResponse(responseCode = "404", description = "Crawl task not found", content = [Content()]),
            ApiResponse(responseCode = "500", description = "Internal server error", content = [Content()])
        ]
    )
    fun getCrawl(
        @Parameter(description = "The UUID of the crawl task", required = true, example = "123e4567-e89b-12d3-a456-426614174000")
        @PathVariable("id") id: UUID
    ): ResponseEntity<GetCrawlResponse>
}