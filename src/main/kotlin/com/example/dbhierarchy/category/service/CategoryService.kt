package com.example.dbhierarchy.category.service

import com.example.dbhierarchy.category.domain.CategoryClosureEntity
import com.example.dbhierarchy.category.domain.CategoryEntity
import com.example.dbhierarchy.category.domain.dto.request.CategoryRequest
import com.example.dbhierarchy.category.repository.CategoryClosureRepository
import com.example.dbhierarchy.category.repository.CategoryRepository
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional
@RequiredArgsConstructor
@Service
class CategoryService(
    private val categoryRepository: CategoryRepository,
    private val categoryClosureRepository: CategoryClosureRepository,
) {

    fun recreateCategoryClosures(
        requests: List<CategoryRequest>
    ) {
        categoryClosureRepository.deleteAll()
        for (request in requests.orEmpty()) {
            replaceAllCategories(null, request)
        }
    }

    private fun replaceAllCategories(
        ancestor: CategoryRequest?,
        descendant: CategoryRequest,
    ) {
        val ancestorId: Long? = saveOnlyCategory(ancestor)?.id
        ancestorId?.let { ancestor?.id = it}
        val descendantId: Long? = saveOnlyCategory(descendant)!!.id
        descendantId?.let { descendant?.id = it}

        if (ancestor != null) {
            val categories: List<CategoryEntity> =
                ancestorId?.let { id ->
                    val ancestorIds: List<Long> = categoryClosureRepository
                        .findAllByDescendant(id)
                        .map { it.ancestor }

                    categoryRepository.findAllById(ancestorIds).toList()
                } ?: emptyList()

            for (category in categories) {
               categoryClosureRepository.save(CategoryClosureEntity(null, category.id!!, descendant.id!!))
            }
        }

        categoryClosureRepository.save(CategoryClosureEntity(null, descendantId!!, descendantId!!))

        for (request in descendant.categories.orEmpty()) {
            replaceAllCategories(descendant, request)
        }
    }

    fun saveOnlyCategory(
        categoryRequest: CategoryRequest?
    ): CategoryEntity? {
        if (categoryRequest == null) return null

        return categoryRepository.save(
            CategoryEntity(
                categoryRequest.id,
                categoryRequest.name,
                categoryRequest.depth,
                categoryRequest.displayOrder
            )
        )
    }



}