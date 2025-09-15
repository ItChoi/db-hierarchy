package com.example.dbhierarchy.category.service

import com.example.dbhierarchy.category.domain.CategoryClosureEntity
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
        deleteAllCategoryClosure()

    }

    fun deleteAllCategoryClosure() {
        categoryClosureRepository.deleteAll()
    }


}