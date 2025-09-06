package com.example.dbhierarchy.category.service

import com.example.dbhierarchy.category.domain.dto.request.CategoryRequest
import com.example.dbhierarchy.category.repository.CategoryClosureRepository
import com.example.dbhierarchy.category.repository.CategoryRepository
import com.example.dbhierarchy.category.repository.ItemRepository
import lombok.RequiredArgsConstructor
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
        deleteAllCategories()
        create(requests)
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

    private fun deleteAllCategories() {
        categoryRepository.deleteAll()
        categoryClosureRepository.deleteAll()
    }

}