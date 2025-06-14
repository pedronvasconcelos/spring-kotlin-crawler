package com.nantes.crawler.application.handlers

import com.nantes.crawler.TestcontainersConfiguration
import com.nantes.crawler.application.dto.CreateCrawlRequest
import com.nantes.crawler.application.dto.CreateCrawlRequestHandler
import com.nantes.crawler.domain.interfaces.CrawlRepository
import com.nantes.crawler.webapi.configs.RabbitMQConfig
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.amqp.core.AmqpTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.springframework.test.context.ActiveProfiles

@ActiveProfiles("test")
@SpringBootTest
@Import(TestcontainersConfiguration::class)
class CreateCrawlHandlerIntegrationTests {

    @Autowired
    private lateinit var createCrawlRequestHandler: CreateCrawlRequestHandler

    @Autowired
    private lateinit var crawlRepository: CrawlRepository

    @Autowired
    private lateinit var rabbitTemplate: AmqpTemplate

    private val queueName = RabbitMQConfig.CRAWL_QUEUE_NAME

    @Test
    @DisplayName("Given a valid request, when handler is called, then it should save crawl to database and send notification to queue")
    fun `handle should save crawl to database and send notification`() {
        val keyword = "spring boot with testcontainers"
        val request = CreateCrawlRequest(keyword)

        val response = createCrawlRequestHandler.handle(request)
        val crawlId = response.crawlId

        val crawl = crawlRepository.findById(crawlId)
        val messageBody = rabbitTemplate.receiveAndConvert(queueName, 5000) as? String

        assertTrue(crawl != null, "Crawl should be saved in the database")
        assertEquals(crawlId, crawl?.id)
        assertEquals(keyword, crawl?.keyword)
        assertNotNull(messageBody, "A message should be present in the queue")
    }
}