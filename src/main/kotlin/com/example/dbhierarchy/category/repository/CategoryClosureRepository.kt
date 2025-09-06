package com.example.dbhierarchy.category.repository

import com.example.dbhierarchy.category.domain.CategoryClosureEntity
import org.springframework.data.jpa.repository.JpaRepository

interface CategoryClosureRepository : JpaRepository<CategoryClosureEntity, Long> {
}