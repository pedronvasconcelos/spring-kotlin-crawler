package com.nantes.crawler.domain.shared

data class ResourceNotFoundException(
    val resourceName: String,
    val id: Any
) : RuntimeException("Resource '$resourceName' with ID '$id' was not found.")