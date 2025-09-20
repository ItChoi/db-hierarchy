package com.example.dbhierarchy.category.domain.dto.request

import com.example.dbhierarchy.common.enumeration.CategorySearchType

data class CategorySearch(
    val searchType: CategorySearchType?,
    val searchTypeValue: String?,
)