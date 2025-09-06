package com.example.dbhierarchy.category.controller

import com.example.dbhierarchy.category.domain.dto.request.CategoryRequest
import com.example.dbhierarchy.category.service.CategoryService
import lombok.RequiredArgsConstructor
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequiredArgsConstructor
@RestController
@RequestMapping("/categories")
class CategoryController(
    private val categoryService: CategoryService,
) {

    @PutMapping
    fun putCategories(
        @RequestBody requests: List<CategoryRequest>,
    ) {
        categoryService.put(requests)
    }


}