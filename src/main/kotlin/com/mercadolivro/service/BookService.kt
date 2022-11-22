package com.mercadolivro.service

import com.mercadolivro.enums.BookStatus
import com.mercadolivro.enums.Errors
import com.mercadolivro.exception.NotFoundException
import com.mercadolivro.model.BookModel
import com.mercadolivro.model.CustomerModel
import com.mercadolivro.repository.BookRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class BookService(
        val bookRepository: BookRepository
) {

    fun create(book: BookModel) {
        bookRepository.save(book)
    }

    fun getBooks(pageable: Pageable): Page<BookModel> {
        return bookRepository.findAll(pageable)
    }

    fun getActives(pageable: Pageable): Page<BookModel> {
        return bookRepository.findByStatus(BookStatus.ATIVO, pageable)
    }

    fun getBookById(id: Int): BookModel {
        return bookRepository.findById(id).orElseThrow {
            NotFoundException(Errors.ML101.message.format(id), Errors.ML101.code)
        }
    }

    fun deleteBook(id: Int) {
        val book: BookModel = bookRepository.findById(id).orElseThrow {
            NotFoundException(Errors.ML101.message.format(id), Errors.ML101.code)
        }

        book.status = BookStatus.CANCELADO

        updateBook(book)
    }

    fun updateBook(book: BookModel) {
        bookRepository.save(book)
    }

    fun deleteBookByCustomer(customer: CustomerModel) {
        val books = bookRepository.findByCustomer(customer)

        for (book in books) {
            book.status = BookStatus.DELETADO
        }

        bookRepository.saveAll(books)
    }

}
