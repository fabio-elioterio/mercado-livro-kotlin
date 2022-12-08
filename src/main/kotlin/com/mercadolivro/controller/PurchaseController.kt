package com.mercadolivro.controller

import com.mercadolivro.controller.mapper.PurchaseMapper
import com.mercadolivro.controller.request.PostPurchaseRequest
import com.mercadolivro.model.PurchaseModel
import com.mercadolivro.service.PurchaseService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController()
@RequestMapping("purchases")
class PurchaseController(
        private val purchaseService: PurchaseService,
        private val purchaseMapper: PurchaseMapper
) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createPurchase(@RequestBody request: PostPurchaseRequest) {

        purchaseService.create(purchaseMapper.toModel(request))

    }

    @GetMapping("/{id}")
    fun getCustomerPurchases(@PathVariable id: Int,
                             @PageableDefault(page = 0, size = 10) pageable: Pageable): Page<PurchaseModel> {

        return purchaseService.getCustomerPurchases(id, pageable)

    }
}