package com.nantes.crawler.application.consumer

import com.nantes.crawler.domain.interfaces.CrawlRepository
import com.nantes.crawler.domain.models.CrawlStatus
import org.slf4j.LoggerFactory
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import java.util.UUID


@Component
@Transactional
class CrawlerConsumer(private val crawlRepository: CrawlRepository) {
    private val logger = LoggerFactory.getLogger(this::class.java)

    @RabbitListener(queues = ["\${queue.order.name}"])
    fun receiveCrawl(id: String) {
        runCatching {
            UUID.fromString(id)
        }.map { crawlId ->
                crawlRepository.findById(crawlId) ?: throw IllegalArgumentException("Error")
            }.map { crawl ->
                if (crawl.status == CrawlStatus.FINISHED) {
                    throw IllegalArgumentException("Error")
                }
                crawl
            }.map { crawlToUpdate ->
                crawlToUpdate.finish()
            }.onSuccess { updatedCrawl ->
                crawlRepository.save(updatedCrawl)
                logger.info("Crawl ${updatedCrawl.id} successfully updated to FINISHED.")
            }.onFailure { error ->
                when (error) {
                    is IllegalArgumentException -> logger.error("Invalid UUID format for ID: $id", error)
                    else -> logger.error("An unexpected error occurred while processing crawl ID: $id", error)
                }
            }
    }
}