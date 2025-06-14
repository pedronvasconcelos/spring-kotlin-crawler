package com.nantes.crawler.application.usecase

import com.nantes.crawler.domain.interfaces.CrawlRepository
import com.nantes.crawler.domain.models.Crawl
import com.nantes.crawler.domain.models.CrawlStatus
import com.nantes.crawler.domain.shared.ResourceNotFoundException
import org.springframework.stereotype.Service
import java.util.UUID


data class GetCrawlResponse(
    val crawlId: UUID,
    val keyword: String,
    val urls: Set<String>,
    val status : CrawlStatus,
) {
    companion object {
        fun fromDomain(crawl: Crawl): GetCrawlResponse {
            return GetCrawlResponse(
                crawlId = crawl.id, keyword = crawl.keyword, urls = crawl.urls, status = crawl.status
            )
        }
    }
}

@Service
class GetCrawlRequestHandler(
    private val crawlRepository: CrawlRepository,
) {
    fun handle(request: UUID): GetCrawlResponse {
        val crawl = crawlRepository.findById(request) ?:
        throw ResourceNotFoundException("Crawl", request)

        return GetCrawlResponse.fromDomain(crawl)
    }
}