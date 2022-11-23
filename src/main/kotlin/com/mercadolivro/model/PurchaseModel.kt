package com.mercadolivro.model

import java.math.BigDecimal
import java.time.LocalDateTime
import javax.persistence.*

@Entity(name = "purshace")
data class PurchaseModel(

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Int? = null,

        @ManyToOne
        @JoinColumn(name = "customer_id")
        val customer: CustomerModel,

        @ManyToMany
        @JoinTable(name = "purshace_book",
                joinColumns = [JoinColumn(name = "purchase_id")],
                inverseJoinColumns = [JoinColumn(name = "book_id")])
        val books: List<BookModel>,

        @Column
        val nfe: String? = null,

        @Column
        val price: BigDecimal,

        @Column(name = "created_at")
        val createdAt: LocalDateTime

)