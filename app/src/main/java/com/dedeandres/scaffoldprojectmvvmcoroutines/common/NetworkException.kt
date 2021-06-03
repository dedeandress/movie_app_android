package com.dedeandres.scaffoldprojectmvvmcoroutines.common

import retrofit2.HttpException
import java.util.concurrent.TimeoutException

class CacheException(private val msg: String?): Exception()

sealed class NetworkException(private val msg: String?) : Exception() {

    companion object {
        const val CODE_BAD_REQUEST = 400
        const val CODE_UNAUTHORIZED = 401
        const val CODE_FORBIDDEN = 403
        const val CODE_NOT_FOUND = 404
        const val CODE_UNPROCESSABLE = 422
        const val CODE_SERVER_ERROR = 500
        const val CODE_REDIRECT = 302
        const val TIMEOUT = 408
    }

    override val message: String?
        get() = msg

    class BadRequestException(msg: String?) : NetworkException(msg)
    class NotFoundException(msg: String?) : NetworkException(msg)
    class ForbiddenException(msg: String?) : NetworkException(msg)
    class ServerException(msg: String?) : NetworkException(msg)
    class UnauthorizedException(msg: String?) : NetworkException(msg)
    class UnprocessableException(msg: String?) : NetworkException(msg)
    class UnknownException(msg: String?) : NetworkException(msg)
}


open class HttpErrorHandler {
    fun handleException(e: Exception): Exception {
        return when (e) {
            is HttpException -> handleHttpExeception(e)
            else -> e
        }
    }

    private fun handleHttpExeception(httpException: HttpException): Exception {
        return when (httpException.code()) {
            NetworkException.TIMEOUT -> TimeoutException()
            NetworkException.CODE_UNAUTHORIZED -> NetworkException.UnauthorizedException("Unauthorize")
            NetworkException.CODE_NOT_FOUND -> NetworkException.NotFoundException("Not Found")
            NetworkException.CODE_BAD_REQUEST -> NetworkException.BadRequestException("Bad Request")
            NetworkException.CODE_SERVER_ERROR -> NetworkException.ServerException("Server Error")
            NetworkException.CODE_FORBIDDEN -> NetworkException.ForbiddenException("Forbidden")
            else -> NetworkException.ServerException("Something went wrong")
        }
    }
}