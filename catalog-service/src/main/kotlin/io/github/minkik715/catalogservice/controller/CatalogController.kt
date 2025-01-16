package io.github.minkik715.catalogservice.controller

import io.github.minkik715.catalogservice.service.CatalogService
import io.github.minkik715.catalogservice.vo.ResponseCatalog
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/catalog-service")
class CatalogController(
    private val catalogService: CatalogService
) {

    @GetMapping("/catalogs")
    fun getCatalogs(): List<ResponseCatalog> {
        return catalogService.getAllCatalogs()
    }
}