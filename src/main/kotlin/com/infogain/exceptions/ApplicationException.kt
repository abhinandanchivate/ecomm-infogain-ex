package com.infogain.exceptions

open class ApplicationException(message: String) : RuntimeException(message)
class NotFoundException(message: String) : ApplicationException(message)
