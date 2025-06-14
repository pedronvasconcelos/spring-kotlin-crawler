package com.nantes.crawler.infra.bus

import com.nantes.crawler.domain.interfaces.CrawlQueueNotification
import com.nantes.crawler.webapi.configs.RabbitMQConfig
import org.springframework.amqp.core.AmqpTemplate
import org.springframework.stereotype.Service
import java.util.*
@Service
class CrawlRabbitMQQueueNotification(private val amqpTemplate : AmqpTemplate) : CrawlQueueNotification {
    override fun notify(crawlId: UUID) {
        amqpTemplate.convertAndSend(RabbitMQConfig.CRAWL_QUEUE_NAME, crawlId.toString())
    }
}