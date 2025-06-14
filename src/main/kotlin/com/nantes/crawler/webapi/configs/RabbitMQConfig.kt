package com.nantes.crawler.webapi.configs

import org.springframework.amqp.core.Queue
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RabbitMQConfig {

    companion object {
        const val CRAWL_QUEUE_NAME = "crawl"
    }

    @Bean
    fun crawlQueue(): Queue {
        return Queue(CRAWL_QUEUE_NAME, true)
    }
}