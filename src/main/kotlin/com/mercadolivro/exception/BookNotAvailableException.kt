package com.mercadolivro.exception

class BookNotAvailableException(override val message: String, val errorCode: String) : Exception() {
}