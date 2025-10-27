package com.example.dbhierarchy.category.repository

import com.example.dbhierarchy.category.domain.QCategoryClosureEntity as categoryClosure
import com.example.dbhierarchy.category.domain.QCategoryEntity as category
import com.example.dbhierarchy.category.domain.dto.request.CategorySearch
import com.example.dbhierarchy.category.domain.dto.response.CategoryResponse
import com.querydsl.core.types.Projections
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Repository
import kotlin.io.path.name


@Repository
class CategoryRepositoryCustomImpl(
    private val queryFactory: JPAQueryFactory,
) : CategoryRepositoryCustom {

    override fun findAllBy(
        categorySearch: CategorySearch?
    ): List<CategoryResponse> {
        val c1 = category("c1")
        val c2 = category("c2")
        val c3 = category("c3")
        val cc1 = categoryClosure("cc1")
        val cc2 = categoryClosure("cc2")

        /*SELECT
        c2.id,
        c2.name,
        c2.depth,
        c2.display_order,
        c3.id,
        c3.name,
        c3.depth,
        c3.display_order
        FROM
        test_category c1
                INNER JOIN test_category_closure cc1
                ON cc1.ancestor = c1.id
                INNER JOIN test_category c2
                ON c2.id = cc1.descendant
                INNER JOIN test_category_closure cc3
                ON cc3.descendant = c2.id
                INNER JOIN test_category c3
                ON c3.id = cc3.ancestor
                AND c3.depth = c2.depth - 1
        WHERE c1.depth = 1
        ORDER BY c2.depth, c2.display_order;*/
        queryFactory
            .select(
                Projections.fields(
                    CategoryResponse::class.java

                )
            )
            .from(c1)
            .innerJoin(cc1)
            .on(cc1.ancestor.eq(c1.id))
            .innerJoin(c2)
            .on(c2.id.eq(cc1.descendant))
            .innerJoin(cc2)
            .on(cc2.descendant.eq(c2.id))
            .innerJoin(c3)
            .on(c3.id.eq(cc2.ancestor)
                .and(c3.depth.eq(c2.depth.subtract(1))))
            .where(c1.depth.eq(1))
            .orderBy(c2.depth.asc(), c2.displayOrder.asc())
        //ㅅㄷㄴㅅ
        return emptyList()
    }
}