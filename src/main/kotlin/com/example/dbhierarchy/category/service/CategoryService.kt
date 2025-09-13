package com.example.dbhierarchy.category.service

import com.example.dbhierarchy.category.domain.CategoryEntity
import com.example.dbhierarchy.category.domain.dto.request.CategoryRequest
import com.example.dbhierarchy.category.repository.CategoryClosureRepository
import com.example.dbhierarchy.category.repository.CategoryRepository
import lombok.RequiredArgsConstructor
import org.apache.coyote.BadRequestException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.util.CollectionUtils

@Transactional
@RequiredArgsConstructor
@Service
class CategoryService(
    private val categoryRepository: CategoryRepository,
    private val categoryClosureRepository: CategoryClosureRepository,
) {

    fun put(
        requests: List<CategoryRequest>
    ) {
        val categoryById = fetchCategoryEntityMapByRequests(requests)
        for (request in requests) {
            saveHierarchyCategory(request)
        }

        create(requests)
    }

    private fun saveHierarchyCategory(
        request: CategoryRequest
    ) {

    }

    fun fetchCategoryEntityMapByRequests(
        requests: List<CategoryRequest>
    ): Map<Long, CategoryEntity> {
        val categoryIds = extractAllCategoryIds(requests)
        if (CollectionUtils.isEmpty(categoryIds)) {
            return mapOf()
        }

        val categoryEntityById = categoryRepository.findAllById(categoryIds)
            .associateBy(CategoryEntity::id)

        if (categoryEntityById.keys.size != categoryIds.size) {
            throw BadRequestException("미스매치 사이즈!")
        }

        return categoryEntityById
    }


    fun extractAllCategoryIds(
        requests: Collection<CategoryRequest>
    ): List<Long> {
        val result = buildList {
            val queue = ArrayDeque<CategoryRequest>().apply { addAll(requests) }
            while (queue.isNotEmpty()) {
                val poll = queue.removeFirst()
                poll.id?.let { add(it) }
                poll.categories?.let { queue.addAll(it) }
            }
        }

        return result
    }


    private fun create(
        descendants: List<CategoryRequest>?,
        ancestor: Long? = null
    ) {
        if (descendants == null) {
            return;
        }



    }

    private fun createClosure(
        ancestor: Long?,
        descendant: Long?,
    ) {

    }
}