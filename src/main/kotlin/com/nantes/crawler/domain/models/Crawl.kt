package com.nantes.crawler.domain.models

import java.util.UUID
import java.time.LocalDateTime
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Field


data class Crawl(
    @Id
    val crawlId: UUID = UUID.randomUUID(),
    @Field("status")
    val status: CrawlStatus,
    @Field("urls")
    val urls:  MutableSet<String> = mutableSetOf(),
    @Field("keyword")
    val keyword: String,
    @Field("createdAt")
    val createdAt: LocalDateTime = LocalDateTime.now(),
    @Field("updatedAt")
    val updatedAt: LocalDateTime = LocalDateTime.now()
    ) {

        companion object {
            fun create(keyword: String): Crawl {
                return Crawl(UUID.randomUUID(),
                CrawlStatus.ACTIVE,
                mutableSetOf(),
                keyword)
            }
        }


        fun addUrl(url: String) : Crawl {
            val newUrls = urls.toMutableSet()
            newUrls.add(url)
            return copy(urls = newUrls, updatedAt = LocalDateTime.now())            
        }

      fun finish() : Crawl {
         return copy(status = CrawlStatus.FINISHED, updatedAt = LocalDateTime.now())     
      }
   
         
}

enum class CrawlStatus {
    ACTIVE,
    FINISHED
}

