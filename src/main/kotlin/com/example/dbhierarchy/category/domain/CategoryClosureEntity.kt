package com.example.dbhierarchy.category.domain

import jakarta.persistence.*

@Entity
@Table(name = "test_category_closure")
class CategoryClosureEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long?,
    var ancestor: Long,
    var descendant: Long,
)