package com.nantes.crawler.infra.data

import com.nantes.crawler.domain.interfaces.CrawlRepository
import com.nantes.crawler.domain.models.Crawl
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.stereotype.Repository
import java.util.UUID






@Repository
class CrawlRepositoryImpl(
    private val mongoTemplate: MongoTemplate
) : CrawlRepository {




    override fun save(crawl: Crawl) {
        mongoTemplate.save(crawl)
    }

    override fun findById(id: UUID): Crawl? {
        return mongoTemplate.findById(id, Crawl::class.java)
     }

 }
