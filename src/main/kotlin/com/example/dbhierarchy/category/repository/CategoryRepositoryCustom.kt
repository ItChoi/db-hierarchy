package com.example.dbhierarchy.category.repository

import com.example.dbhierarchy.category.domain.dto.request.CategorySearch
import com.example.dbhierarchy.category.domain.dto.response.CategoryResponse

interface CategoryRepositoryCustom {
    fun findAllBy(categorySearch: CategorySearch?): List<CategoryResponse>
}