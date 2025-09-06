package com.example.dbhierarchy.category.repository

import com.example.dbhierarchy.category.domain.CategoryEntity
import org.springframework.data.jpa.repository.JpaRepository

interface CategoryRepository : JpaRepository<CategoryEntity, Long> {
}