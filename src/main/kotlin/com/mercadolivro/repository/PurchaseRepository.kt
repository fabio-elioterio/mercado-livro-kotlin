package com.mercadolivro.repository

import com.mercadolivro.model.PurchaseModel
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.CrudRepository

interface PurchaseRepository : CrudRepository<PurchaseModel, Int> {
    fun findPurchaseByCustomerId(customerId: Int, pageable: Pageable): Page<PurchaseModel>

}
