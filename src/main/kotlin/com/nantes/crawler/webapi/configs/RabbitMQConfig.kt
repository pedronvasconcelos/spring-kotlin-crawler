package com.nantes.crawler.webapi.configs

import org.springframework.amqp.core.Queue
import org.springframework.amqp.rabbit.config.RetryInterceptorBuilder
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory
import org.springframework.amqp.rabbit.connection.ConnectionFactory
import org.springframework.amqp.rabbit.retry.RejectAndDontRequeueRecoverer
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.retry.interceptor.StatefulRetryOperationsInterceptor

@Configuration
class RabbitMQConfig {

    companion object {
        const val CRAWL_QUEUE_NAME = "crawl"
    }

    @Bean
    fun crawlQueue(): Queue {
        return Queue(CRAWL_QUEUE_NAME, true)
    }

    @Bean
    fun retryInterceptor(): StatefulRetryOperationsInterceptor {
        return RetryInterceptorBuilder.stateful()
            .maxAttempts(5)
            .backOffOptions(
                2000,
                2.0,
                10000
            )
            .recoverer(RejectAndDontRequeueRecoverer())
            .build()
    }
    @Bean
    fun rabbitListenerContainerFactory(
        configurer: SimpleRabbitListenerContainerFactoryConfigurer,
        connectionFactory: ConnectionFactory,
        retryInterceptor: StatefulRetryOperationsInterceptor
    ): SimpleRabbitListenerContainerFactory {
        val factory = SimpleRabbitListenerContainerFactory()
        configurer.configure(factory, connectionFactory)
        factory.setAdviceChain(retryInterceptor)
        return factory
    }
}