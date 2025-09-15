package com.example.dbhierarchy.category.repository

import com.example.dbhierarchy.category.domain.CategoryClosureEntity
import org.springframework.data.jpa.repository.JpaRepository

interface CategoryClosureRepository : JpaRepository<CategoryClosureEntity, Long> {
    fun findByAncestorDescendant(ancestor: Long, descendant: Long): CategoryClosureEntity
    fun existsByAncestorDescendant(ancestor: Long, descendant: Long): Boolean

}