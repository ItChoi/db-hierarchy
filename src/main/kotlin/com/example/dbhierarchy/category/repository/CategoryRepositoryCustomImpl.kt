package com.example.dbhierarchy.category.repository

import com.example.dbhierarchy.category.domain.dto.response.CategoryClosureFlatResponse
import com.example.dbhierarchy.category.domain.dto.response.CategoryResponse
import com.querydsl.core.types.Projections
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Repository
import org.springframework.util.CollectionUtils
import java.util.Queue
import com.example.dbhierarchy.category.domain.QCategoryClosureEntity as categoryClosure
import com.example.dbhierarchy.category.domain.QCategoryEntity as category

@Repository
class CategoryRepositoryCustomImpl(
    private val queryFactory: JPAQueryFactory,
) : CategoryRepositoryCustom {

    override fun findAllByDepth(
        depth: Int,
    ): List<CategoryResponse> {
        val c1 = category("c1")
        val c2 = category("c2")
        val c3 = category("c3")
        val cc1 = categoryClosure("cc1")
        val cc2 = categoryClosure("cc2")

        val flatCategories = queryFactory
            .select(
                Projections.constructor(
                    CategoryClosureFlatResponse::class.java,
                    c2.id,
                    c2.name,
                    c2.depth,
                    c2.displayOrder,
                    c3.id,
                    c3.name,
                    c3.depth,
                    c3.displayOrder,
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
            .on(
                c3.id.eq(cc2.ancestor)
                    .and(c3.depth.eq(c2.depth.subtract(1)))
            )
            .where(c1.depth.eq(depth))
            .orderBy(c2.depth.asc(), c2.displayOrder.asc())
            .fetch()

        return buildCategoryTree(flatCategories)
    }

    fun buildCategoryTree(
        flatCategories: List<CategoryClosureFlatResponse>
    ): List<CategoryResponse> {
        if (CollectionUtils.isEmpty(flatCategories)) {
            return emptyList()
        }

        for (flatCategory in flatCategories) {

        }

        return emptyList()
    }

//    fun buildCategoryTree(
//        rows: List<CategoryClosureFlatResponse>
//    ): List<CategoryResponse> {
//        val nodeMap = mutableMapOf<Long, CategoryResponse>()
//
//        fun getOrCreate(id: Long, name: String, depth: Int, order: Int): CategoryResponse {
//            return nodeMap.getOrPut(id) { CategoryResponse(id, name, depth, order) }.also {
//                // 혹시 이전에 placeholder처럼 만들어졌다면 최신 값으로 메우기
//                it.name = name
//                it.depth = depth
//                it.displayOrder = order
//            }
//        }
//
//        rows.forEach { r ->
//            // 자식 노드 확보
//            val child = getOrCreate(r.id, r.name, r.depth, r.displayOrder)
//
//            // 부모가 있으면 연결(최상위면 parentId == null)
//            if (r.parentId != null) {
//                val parent = nodeMap[r.parentId] ?: CategoryResponse(
//                    id = r.parentId,
//                    name = r.parentName ?: "(unknown)",
//                    depth = r.parentDepth ?: (r.depth - 1).coerceAtLeast(0),
//                    displayOrder = r.parentDisplayOrder ?: 0
//                ).also { nodeMap[r.parentId] = it }
//
//                // 링크 & 중복 방지
//                if (child.parent == null) child.parent = parent
//                if (parent.children.none { it.id == child.id }) {
//                    parent.children += child
//                }
//            }
//        }
//
//        // 루트들만 추출(부모가 없는 노드)
//        val roots = nodeMap.values.filter { it.parent == null }
//
//        // 정렬 규칙: displayOrder → name
//        fun sortRec(nodes: MutableList<CategoryResponse>) {
//            nodes.sortWith(compareBy<CategoryResponse> { it.displayOrder }.thenBy { it.name })
//            nodes.forEach { sortRec(it.children) }
//        }
//        sortRec(roots.toMutableList())
//
//        return roots
//    }
}