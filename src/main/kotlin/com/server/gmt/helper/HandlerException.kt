package com.server.gmt.helper

import org.springframework.http.HttpStatus
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.InternalAuthenticationServiceException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus

@ControllerAdvice
class HandlerException {

    @ExceptionHandler(InternalAuthenticationServiceException::class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    @ResponseBody
    fun exceptionUserNotFound(): ResponseException {
        return ResponseException("Usuario no encontrado", "username")
    }

    @ExceptionHandler(BadCredentialsException::class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    @ResponseBody
    fun exceptionBadCredentials(): ResponseException {
        return ResponseException("Contraseña Incorrecta", "password")
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    fun exceptionSpringValidation(exception: MethodArgumentNotValidException): ResponseException {
        return ResponseException(exception.message, "validation")
    }

    @ExceptionHandler(Exception::class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    @ResponseBody
    fun exceptionUnespected(): ResponseException {
        return ResponseException("Error inesperado", "unespected")
    }

}

class ResponseException(var message: String, var causa: String?)