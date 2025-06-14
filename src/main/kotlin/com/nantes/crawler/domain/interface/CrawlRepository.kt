package com.nantes.crawler.domain.interface

import com.nantes.crawler.domain.models.Crawl
import java.util.UUID

interface CrawlRepository {
    fun save(crawl: Crawl)
    fun findById(id: UUID): Crawl?
}

