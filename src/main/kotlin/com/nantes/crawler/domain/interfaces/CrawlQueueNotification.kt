package com.nantes.crawler.domain.interfaces
import java.util.UUID

interface CrawlQueueNotification {
    fun notify(crawlId: UUID)
}



