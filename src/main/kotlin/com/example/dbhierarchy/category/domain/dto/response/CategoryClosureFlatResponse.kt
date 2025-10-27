package com.example.dbhierarchy.category.domain.dto.response

class CategoryClosureFlatResponse(
    val id: Long,
    val name: String,
    val depth: Int,
    val displayOrder: Int,
    val parentId: Long?,
    val parentName: String?,
    val parentDepth: Int,
    val parentDisplayOrder: Int,
) {
}