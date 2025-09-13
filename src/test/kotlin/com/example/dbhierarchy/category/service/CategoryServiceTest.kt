package com.example.dbhierarchy.category.service

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

import com.example.dbhierarchy.category.domain.dto.request.CategoryRequest
import com.example.dbhierarchy.category.repository.CategoryClosureRepository
import com.example.dbhierarchy.category.repository.CategoryRepository
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
        var categoryIds = service.extractAllCategoryIds(mutableListOf(categoryRequest1, categoryRequest2, categoryRequest3));

        // then
        Assertions.assertThat(categoryIds.containsAll(listOf(1L, 2L)))

    }

    @Test
    @DisplayName("extractAllCategoryIds: 단일 루트-중첩 계층을 BFS 순서로 평탄화한다")
    fun extractAllCategoryIds_bfs_singleRoot() {
        // given
        // A(1)
        // ├─ B(2)
        // │  ├─ D(4)
        // │  └─ E(5)
        // └─ C(3)
        val d = req(4)
        val e = req(5)
        val b = req(2, categories = listOf(d, e))
        val c = req(3)
        val a = req(1, categories = listOf(b, c))

        val sut = service()

        // when
        val result = sut.extractAllCategoryIds(listOf(a))

        // then
        assertEquals(listOf(1L, 2L, 3L, 4L, 5L), result)
    }

    @Test
    @DisplayName("extractAllCategoryIds: 다중 루트 입력도 BFS 순서로 평탄화한다")
    fun extractAllCategoryIds_bfs_multipleRoots() {
        // given
        // X(10)
        // Y(20)
        // └─ Z(30)
        val z = req(30)
        val y = req(20, categories = listOf(z))
        val x = req(10)

        val sut = service()

        // when
        val result = sut.extractAllCategoryIds(listOf(x, y))

        // then
        assertEquals(listOf(10L, 20L, 30L), result)
    }

    @Test
    @DisplayName("extractAllCategoryIds: 비어있는 입력은 빈 리스트를 반환한다")
    fun extractAllCategoryIds_emptyInput_returnsEmpty() {
        // given
        val sut = service()

        // when
        val result = sut.extractAllCategoryIds(emptyList())

        // then
        assertTrue(result.isEmpty())
    }

    @Test
    @DisplayName("put: 예외 없이 동작한다(내부적으로 extractAllCategoryIds와 create 호출)")
    fun put_runs_withoutException() {
        // given
        val sut = service()
        val reqs = listOf(
            req(1, categories = listOf(req(2), req(3)))
        )

        // when / then
        assertDoesNotThrow { sut.put(reqs) }
    }
}
// ... existing code ...
