package com.mercadolivro.events.listener

import com.mercadolivro.events.PurchaseEvent
import com.mercadolivro.service.BookService
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
class UpdateSoldBookListener(
        private val bookService: BookService
) {

    @EventListener
    fun listen(purchaseEvent: PurchaseEvent) {


        bookService.bookSold(purchaseEvent.purchaseModel.books)
    }

}