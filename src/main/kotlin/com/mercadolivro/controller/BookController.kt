package com.mercadolivro.controller

import com.mercadolivro.controller.request.PostBookRequest
import com.mercadolivro.controller.request.PutBookRequest
import com.mercadolivro.controller.response.BookResponse
import com.mercadolivro.controller.response.PageResponse
import com.mercadolivro.extension.toBookModel
import com.mercadolivro.extension.toBookResponse
import com.mercadolivro.extension.toPageResponse
import com.mercadolivro.service.BookService
import com.mercadolivro.service.CustomerService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("books")
class BookController(
    private val bookService: BookService,
    private val customerService: CustomerService
) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createBook(@RequestBody @Valid request: PostBookRequest) {
        val customer = customerService.getCustomerById(request.customerId)
        bookService.create(request.toBookModel(customer))
    }

    @GetMapping
    fun getBooks(@PageableDefault(page = 0, size = 10) pageable: Pageable): PageResponse<BookResponse> {
        return bookService.getBooks(pageable).map { it.toBookResponse() }.toPageResponse()
    }

    @GetMapping("/active")
    fun getActives(@PageableDefault(page = 0, size = 10) pageable: Pageable): Page<BookResponse> =
        bookService.getActives(pageable).map { it.toBookResponse() }

    @GetMapping("/{id}")
    fun getBookById(@PathVariable id: Int): BookResponse {
        return bookService.getBookById(id).toBookResponse()
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteBook(@PathVariable id: Int) {
        bookService.deleteBook(id)
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun updateBook(@PathVariable id: Int, @RequestBody book: PutBookRequest) {
        val bookSaved = bookService.getBookById(id)
        bookService.updateBook(book.toBookModel(bookSaved))
    }

}