package com.example.dbhierarchy.category.domain

import jakarta.persistence.*

@Entity
@Table(name = "test_category")
class CategoryEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long?,
    var name: String,
    var depth: Int,
    var displayOrder: Int,
)