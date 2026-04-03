// File generated from our OpenAPI spec by Stainless.

package com.anthropic.errors

import com.anthropic.core.JsonValue
import com.anthropic.core.checkRequired
import com.anthropic.core.http.Headers

class UnauthorizedException
private constructor(private val headers: Headers, private val body: JsonValue, cause: Throwable?) :
    AnthropicServiceException("401: $body", cause) {

    override fun statusCode(): Int = 401

    override fun headers(): Headers = headers

    override fun body(): JsonValue = body

    fun toBuilder() = Builder().from(this)

    companion object {

        /**
         * Returns a mutable builder for constructing an instance of [UnauthorizedException].
         *
         * The following fields are required:
         * ```java
         * .headers()
         * .body()
         * ```
         */
        fun builder() = Builder()
    }

    /** A builder for [UnauthorizedException]. */
    class Builder internal constructor() {

        private var headers: Headers? = null
        private var body: JsonValue? = null
        private var cause: Throwable? = null

        internal fun from(unauthorizedException: UnauthorizedException) = apply {
            headers = unauthorizedException.headers
            body = unauthorizedException.body
            cause = unauthorizedException.cause
        }

        fun headers(headers: Headers) = apply { this.headers = headers }

        fun body(body: JsonValue) = apply { this.body = body }

        fun cause(cause: Throwable?) = apply { this.cause = cause }

        /**
         * Returns an immutable instance of [UnauthorizedException].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .headers()
         * .body()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): UnauthorizedException =
            UnauthorizedException(
                checkRequired("headers", headers),
                checkRequired("body", body),
                cause,
            )
    }
}
