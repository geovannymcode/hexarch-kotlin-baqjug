package com.baqjug.catalog.domain.exception

class ProductNotFoundException(id: String) :
    RuntimeException("No se encontró el producto $id")