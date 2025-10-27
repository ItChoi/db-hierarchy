package com.example.dbhierarchy.category.domain.dto.response

data class CategoryResponse(
    val id: Long,
    val name: String,
    var depth: Int,
    val displayOrder: Int,
    val categories: MutableList<CategoryResponse> = mutableListOf()
)
