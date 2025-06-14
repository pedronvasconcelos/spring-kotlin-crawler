package com.nantes.crawler.domain.interfaces

import org.springframework.stereotype.Service
import java.util.UUID

interface CrawlQueueNotification {
    fun notify(crawlId: UUID)
}



