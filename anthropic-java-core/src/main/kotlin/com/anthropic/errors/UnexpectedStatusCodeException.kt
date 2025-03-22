// File generated from our OpenAPI spec by Stainless.

package com.anthropic.errors

import com.anthropic.core.JsonValue
import com.anthropic.core.checkRequired
import com.anthropic.core.http.Headers
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

class UnexpectedStatusCodeException
private constructor(
    private val statusCode: Int,
    private val headers: Headers,
    private val body: JsonValue,
    cause: Throwable?,
) : AnthropicServiceException("$statusCode: $body", cause) {

    override fun statusCode(): Int = statusCode

    override fun headers(): Headers = headers

    override fun body(): JsonValue = body

    fun toBuilder() = Builder().from(this)

    companion object {

        /**
         * Returns a mutable builder for constructing an instance of
         * [UnexpectedStatusCodeException].
         *
         * The following fields are required:
         * ```java
         * .statusCode()
         * .headers()
         * .body()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [UnexpectedStatusCodeException]. */
    class Builder internal constructor() {

        private var statusCode: Int? = null
        private var headers: Headers? = null
        private var body: JsonValue? = null
        private var cause: Throwable? = null

        @JvmSynthetic
        internal fun from(unexpectedStatusCodeException: UnexpectedStatusCodeException) = apply {
            statusCode = unexpectedStatusCodeException.statusCode
            headers = unexpectedStatusCodeException.headers
            body = unexpectedStatusCodeException.body
            cause = unexpectedStatusCodeException.cause
        }

        fun statusCode(statusCode: Int) = apply { this.statusCode = statusCode }

        fun headers(headers: Headers) = apply { this.headers = headers }

        fun body(body: JsonValue) = apply { this.body = body }

        fun cause(cause: Throwable?) = apply { this.cause = cause }

        /** Alias for calling [Builder.cause] with `cause.orElse(null)`. */
        fun cause(cause: Optional<Throwable>) = cause(cause.getOrNull())

        /**
         * Returns an immutable instance of [UnexpectedStatusCodeException].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .statusCode()
         * .headers()
         * .body()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): UnexpectedStatusCodeException =
            UnexpectedStatusCodeException(
                checkRequired("statusCode", statusCode),
                checkRequired("headers", headers),
                checkRequired("body", body),
                cause,
            )
    }
}
