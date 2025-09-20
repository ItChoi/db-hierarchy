package com.example.dbhierarchy.category.service

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

import com.example.dbhierarchy.category.domain.dto.request.CategoryRequest
import com.example.dbhierarchy.category.repository.CategoryClosureRepository
import com.example.dbhierarchy.category.repository.CategoryRepository
import com.example.dbhierarchy.category.repository.CategoryRepositoryCustom
import org.assertj.core.api.Assertions
import org.mockito.Mockito.mock

class CategoryServiceTest() {

    private fun service(): CategoryService {
        val categoryRepository = mock(CategoryRepository::class.java)
        val categoryClosureRepository = mock(CategoryClosureRepository::class.java)
        return CategoryService(categoryRepository, categoryClosureRepository)
    }

    private fun req(
        id: Long,
        name: String = "name-$id",
        depth: Int = 0,
        displayOrder: Int = 0,
        categories: List<CategoryRequest>? = null
    ): CategoryRequest = CategoryRequest(
        id = id,
        name = name,
        depth = depth,
        displayOrder = displayOrder,
        categories = categories
    )

    @Test
    @DisplayName("")
    fun test() {
        // given
        val service = service()
        val categoryRequest1 = CategoryRequest(1, "test1", 1, 1, null)
        val categoryRequest2 = CategoryRequest(2, "test2", 1, 2, null)
        val categoryRequest3 = CategoryRequest(null, "test2", 1, 2, null)

        // when

        // then

    }

}
// ... existing code ...
