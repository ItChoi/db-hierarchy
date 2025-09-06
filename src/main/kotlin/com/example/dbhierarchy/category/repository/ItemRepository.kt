package com.example.dbhierarchy.category.repository

import com.example.dbhierarchy.category.domain.ItemEntity
import org.springframework.data.jpa.repository.JpaRepository

interface ItemRepository : JpaRepository<ItemEntity, Long> {

}