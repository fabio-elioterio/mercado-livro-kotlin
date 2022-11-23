package com.mercadolivro.service

import com.mercadolivro.enums.BookStatus
import com.mercadolivro.enums.Errors
import com.mercadolivro.events.PurchaseEvent
import com.mercadolivro.exception.BookNotAvailableException
import com.mercadolivro.model.PurchaseModel
import com.mercadolivro.repository.PurchaseRepository
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service

@Service
class PurchaseService(
        private val purchaseRepository: PurchaseRepository,
        private val applicationEventPublisher: ApplicationEventPublisher,
        private val bookService: BookService
) {

    fun create(purchaseModel: PurchaseModel) {
        for (book in purchaseModel.books) {
            if (book.status!! != BookStatus.ATIVO)
                throw BookNotAvailableException(Errors.ML103.message.format(book.id, book.status), Errors.ML103.code)
        }
        purchaseRepository.save(purchaseModel)

        applicationEventPublisher.publishEvent(PurchaseEvent(this, purchaseModel))
    }

    fun update(purchaseModel: PurchaseModel) {
        purchaseRepository.save(purchaseModel)
    }

}
