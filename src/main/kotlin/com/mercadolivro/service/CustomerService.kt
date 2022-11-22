package com.mercadolivro.service

import com.mercadolivro.enums.CustomerStatus
import com.mercadolivro.enums.Errors
import com.mercadolivro.exception.NotFoundException
import com.mercadolivro.model.CustomerModel
import com.mercadolivro.repository.CustomerRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class CustomerService(
        val customerRepository: CustomerRepository,
        val bookService: BookService
) {

    fun getCustomers(name: String?, pageable: Pageable): Page<CustomerModel> {
        name?.let {
            return customerRepository.findByNameContaining(name, pageable)
        }
        return customerRepository.findAll(pageable)
    }

    fun create(customer: CustomerModel) {
        customerRepository.save(customer)
    }

    fun getCustomerById(id: Int): CustomerModel {
        return customerRepository.findById(id).orElseThrow {
            NotFoundException(Errors.ML201.message.format(id), Errors.ML201.code)
        }
    }

    fun update(customer: CustomerModel) {

        if (!customerRepository.existsById(customer.id!!)) {
            throw Exception()
        }

        customerRepository.save(customer)
    }

    fun delete(id: Int) {

        val customer = getCustomerById(id)
        bookService.deleteBookByCustomer(customer)

        customer.status = CustomerStatus.INATIVO
        customerRepository.save(customer)
    }
}