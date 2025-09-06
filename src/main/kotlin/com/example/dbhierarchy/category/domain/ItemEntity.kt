package com.example.dbhierarchy.category.domain

import jakarta.persistence.*


@Entity
@Table(name = "test_item")
class ItemEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long?,
    var categoryItemId: Long?,
    var name: String,

)