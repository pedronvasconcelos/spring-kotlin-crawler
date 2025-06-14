package com.nantes.crawler

import org.springframework.boot.fromApplication
import org.springframework.boot.with


fun main(args: Array<String>) {
    fromApplication<CrawlerApplication>().with(TestcontainersConfiguration::class).run(*args)
}
