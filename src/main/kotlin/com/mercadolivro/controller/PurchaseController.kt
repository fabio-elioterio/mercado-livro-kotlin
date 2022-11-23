package com.mercadolivro.controller

import com.mercadolivro.controller.mapper.PurchaseMapper
import com.mercadolivro.controller.request.PostPurchaseRequest
import com.mercadolivro.service.PurchaseService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController()
@RequestMapping("purchase")
class PurchaseController(
        private val purchaseService: PurchaseService,
        private val purchaseMapper: PurchaseMapper
) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createPurchase(@RequestBody request: PostPurchaseRequest) {

        purchaseService.create(purchaseMapper.toModel(request))

    }
}