package com.example.dbhierarchy.category.repository

import com.example.dbhierarchy.category.domain.dto.response.CategoryResponse

interface CategoryRepositoryCustom {
    fun findAllByDepth(depth: Int): List<CategoryResponse>
}