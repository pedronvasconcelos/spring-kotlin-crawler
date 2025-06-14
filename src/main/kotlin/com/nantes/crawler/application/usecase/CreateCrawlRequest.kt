package com.nantes.crawler.application.dto

import java.util.UUID
import com.nantes.crawler.domain.models.Crawl
import com.nantes.crawler.domain.interfaces.CrawlRepository
import com.nantes.crawler.domain.interfaces.CrawlQueueNotification
import org.springframework.transaction.annotation.Transactional
import org.springframework.stereotype.Service
import io.swagger.v3.oas.annotations.media.Schema

@Schema(description = "Request to create a new crawl task")
data class CreateCrawlRequest(
    @Schema(
        description = "The keyword to search for during crawling",
        example = "spring boot",
        required = true
    )
    val keyword: String
)

@Schema(description = "Response containing the created crawl task information")
data class CreateCrawlResponse(
    @Schema(
        description = "Unique identifier of the created crawl task",
        example = "123e4567-e89b-12d3-a456-426614174000"
    )
    val crawlId: UUID
)

@Transactional
@Service
class CreateCrawlRequestHandler(
    private val crawlRepository: CrawlRepository,
    private val crawlQueueNotification: CrawlQueueNotification
) {
    fun handle(request: CreateCrawlRequest): CreateCrawlResponse {
        val crawl = Crawl.create(request.keyword)
        crawlRepository.save(crawl)
        crawlQueueNotification.notify(crawl.id)
        return CreateCrawlResponse(crawl.id)
    }
}


