package io.github.minkik715.catalogservice.service

import io.github.minkik715.catalogservice.vo.ResponseCatalog

interface CatalogService {
    fun getAllCatalogs(): List<ResponseCatalog>
}