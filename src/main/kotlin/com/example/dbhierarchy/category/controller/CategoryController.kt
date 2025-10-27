package com.example.dbhierarchy.category.controller

import com.example.dbhierarchy.category.domain.dto.request.CategoryRequest
import com.example.dbhierarchy.category.domain.dto.response.CategoryResponse
import com.example.dbhierarchy.category.service.CategoryService
import lombok.RequiredArgsConstructor
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
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
        categoryService.recreateCategoryClosures(requests)
    }

    @GetMapping
    fun findCategories(
        depth: Int = 1
    ): ResponseEntity<List<CategoryResponse>> {
        return ResponseEntity.ok(categoryService.findAllByDepth(depth))
    }


}