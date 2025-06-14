package com.nantes.crawler.application.consumer

import org.slf4j.LoggerFactory
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Component
import java.util.UUID


@Component
class CrawlerConsumer {
    private val logger = LoggerFactory.getLogger(this::class.java)

    @RabbitListener(queues =  ["\${queue.order.name}"] )
    fun receiveCrawl(id: UUID){
        logger.info("Message received: $id");
    }
}