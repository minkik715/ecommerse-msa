package io.github.minkik715.catalogservice.repository

import io.github.minkik715.catalogservice.entity.CatalogEntity
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface CatalogRepository : CrudRepository<CatalogEntity, Long> {

    fun findByProductId(productId: String): CatalogEntity?
}