package io.github.minkik715.catalogservice.entity

import io.github.minkik715.catalogservice.vo.ResponseCatalog
import jakarta.persistence.*
import org.hibernate.annotations.ColumnDefault
import java.util.*

@Entity(name = "catalog")
class CatalogEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L

    @Column(nullable = false, unique = true)
    val productId: String = ""

    @Column(nullable = false, length = 120)
    var productName: String = ""

    @Column(nullable = false)
    var stock: Int = 0

    @Column(nullable = false, length = 200)
    var unitPrice: Int = 0

    @Column(nullable = false, updatable = false, insertable = false)
    @ColumnDefault(value = "CURRENT_TIMESTAMP")
    var createdAt: Date = Date()

    fun toResponse(): ResponseCatalog {
        return ResponseCatalog(
            this.productId,
            this.productName,
            this.stock,
            this.unitPrice,
            this.createdAt
        )
    }
}