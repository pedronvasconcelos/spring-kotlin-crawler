package com.nantes.crawler.domain.interfaces

import com.nantes.crawler.domain.models.Crawl
import java.util.UUID

interface CrawlRepository {
    fun save(crawl: Crawl)
    fun findById(id: UUID): Crawl?
}

