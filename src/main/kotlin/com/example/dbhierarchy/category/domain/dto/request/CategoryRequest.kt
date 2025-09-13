package com.example.dbhierarchy.category.domain.dto.request


class CategoryRequest(
    var id: Long?,
    var name: String,
    var depth: Int,
    var displayOrder: Int,
    var categories: List<CategoryRequest>?
)