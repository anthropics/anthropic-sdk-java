// File generated from our OpenAPI spec by Stainless.

@file:JvmName("ErrorHandler")

package com.anthropic.core.handlers

import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonString
import com.anthropic.core.JsonValue
import com.anthropic.core.http.HttpResponse
import com.anthropic.core.http.HttpResponse.Handler
import com.anthropic.errors.BadRequestException
import com.anthropic.errors.InternalServerException
import com.anthropic.errors.NotFoundException
import com.anthropic.errors.PermissionDeniedException
import com.anthropic.errors.RateLimitException
import com.anthropic.errors.UnauthorizedException
import com.anthropic.errors.UnexpectedStatusCodeException
import com.anthropic.errors.UnprocessableEntityException
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.json.JsonMapper
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef

@JvmSynthetic
internal fun errorBodyHandler(jsonMapper: JsonMapper): Handler<JsonValue> {
    val reader =
        jsonMapper
            .readerFor(jacksonTypeRef<JsonValue>())
            .with(DeserializationFeature.FAIL_ON_TRAILING_TOKENS)

    return object : Handler<JsonValue> {
        override fun handle(response: HttpResponse): JsonValue {
            val body =
                try {
                    response.body().readBytes()
                } catch (e: Exception) {
                    return JsonMissing.of()
                }
            return try {
                reader.readValue<JsonValue>(body)
            } catch (e: Exception) {
                // Not JSON (e.g. a plain-text gateway error), so keep the raw text.
                if (body.isEmpty()) JsonMissing.of()
                else JsonString.of(body.toString(Charsets.UTF_8))
            }
        }
    }
}

@JvmSynthetic
internal fun errorHandler(errorBodyHandler: Handler<JsonValue>): Handler<HttpResponse> =
    object : Handler<HttpResponse> {
        override fun handle(response: HttpResponse): HttpResponse =
            when (val statusCode = response.statusCode()) {
                in 200..299 -> response
                400 ->
                    throw BadRequestException.builder()
                        .headers(response.headers())
                        .body(errorBodyHandler.handle(response))
                        .build()
                401 ->
                    throw UnauthorizedException.builder()
                        .headers(response.headers())
                        .body(errorBodyHandler.handle(response))
                        .build()
                403 ->
                    throw PermissionDeniedException.builder()
                        .headers(response.headers())
                        .body(errorBodyHandler.handle(response))
                        .build()
                404 ->
                    throw NotFoundException.builder()
                        .headers(response.headers())
                        .body(errorBodyHandler.handle(response))
                        .build()
                422 ->
                    throw UnprocessableEntityException.builder()
                        .headers(response.headers())
                        .body(errorBodyHandler.handle(response))
                        .build()
                429 ->
                    throw RateLimitException.builder()
                        .headers(response.headers())
                        .body(errorBodyHandler.handle(response))
                        .build()
                in 500..599 ->
                    throw InternalServerException.builder()
                        .statusCode(statusCode)
                        .headers(response.headers())
                        .body(errorBodyHandler.handle(response))
                        .build()
                else ->
                    throw UnexpectedStatusCodeException.builder()
                        .statusCode(statusCode)
                        .headers(response.headers())
                        .body(errorBodyHandler.handle(response))
                        .build()
            }
    }
