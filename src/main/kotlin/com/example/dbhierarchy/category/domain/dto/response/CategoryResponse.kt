package com.example.dbhierarchy.category.domain.dto.response

data class CategoryResponse(
    val id: Long,
    val name: String,
    val depth: Int,
    val displayOrder: Int,
    val categories: List<CategoryResponse>?
)
