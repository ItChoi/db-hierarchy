package com.example.dbhierarchy.category.controller

import com.example.dbhierarchy.category.domain.dto.request.CategoryRequest
import com.example.dbhierarchy.category.service.CategoryService
import lombok.RequiredArgsConstructor
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/categories")
class CategoryController(
    private val categoryService: CategoryService,
) {

    @PutMapping
    fun putCategories(
        @RequestBody requests: List<CategoryRequest>,
    ) {
        /**
         * 생성, 수정 수정 포괄
         */
        categoryService.put(requests)
    }


}