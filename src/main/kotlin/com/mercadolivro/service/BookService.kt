package com.mercadolivro.service

import com.mercadolivro.enums.BookStatus
import com.mercadolivro.model.BookModel
import com.mercadolivro.model.CustomerModel
import com.mercadolivro.repository.BookRepository
import org.springframework.stereotype.Service

@Service
class BookService(
        val bookRepository: BookRepository
) {

    fun create(book: BookModel) {
        bookRepository.save(book)
    }

    fun getBooks(): List<BookModel> {
        return bookRepository.findAll().toList();
    }

    fun getActives(): List<BookModel> {
        return bookRepository.findByStatus(BookStatus.ATIVO)
    }

    fun getBookById(id: Int): BookModel {
        return bookRepository.findById(id).orElseThrow()
    }

    fun deleteBook(id: Int) {
        val book: BookModel = bookRepository.findById(id).orElseThrow()

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
