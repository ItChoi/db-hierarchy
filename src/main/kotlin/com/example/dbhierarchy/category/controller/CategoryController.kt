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
         * - 1 (1)
         *   - 1-1 (2)
         *   - 1-2 (2)
         *     - 1-2-1 (3)
         *       - (추가1)
         *         - (추가2)
         *       - 1-2-1-1 (4)
         *       - 1-2-1-2 (4)
         *         - 1-2-1-2-1 (5)
         *         - 1-2-1-2-2 (5)
         *         - 1-2-1-2-3 (5)
         *           - (추가3)
         *     - 1-2-2 (3)
         *
         * 추가1:
         *
         */
        categoryService.put(requests)
    }


}