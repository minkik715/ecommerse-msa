package io.github.minkik715.catalogservice.service

import io.github.minkik715.catalogservice.repository.CatalogRepository
import io.github.minkik715.catalogservice.vo.ResponseCatalog
import org.springframework.stereotype.Service

@Service
class CatalogServiceImpl(
    private val catalogRepository: CatalogRepository
): CatalogService {
    override fun getAllCatalogs(): List<ResponseCatalog> {
        return catalogRepository.findAll().map { it.toResponse() }
    }
}