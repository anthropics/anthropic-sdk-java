package com.anthropic.errors

import com.anthropic.core.JsonValue
import com.anthropic.core.checkRequired
import com.anthropic.core.http.Headers
import com.anthropic.core.jsonMapper
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

open class ContentTooLargeException
protected constructor(
    private val headers: Headers,
    private val body: JsonValue,
    cause: Throwable?,
) :
    AnthropicServiceException(
        "413: ${if (body.isMissing()) "Unknown" else jsonMapper().writeValueAsString(body)}",
        cause,
    ) {

    override fun statusCode(): Int = 413

    override fun headers(): Headers = headers

    override fun body(): JsonValue = body

    open fun toBuilder() = Builder().from(this)

    companion object {

        /**
         * Returns a mutable builder for constructing an instance of [ContentTooLargeException].
         *
         * The following fields are required:
         * ```java
         * .headers()
         * .body()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [ContentTooLargeException]. */
    open class Builder internal constructor() {

        private var headers: Headers? = null
        private var body: JsonValue? = null
        private var cause: Throwable? = null

        @JvmSynthetic
        internal fun from(contentTooLargeException: ContentTooLargeException) = apply {
            headers = contentTooLargeException.headers
            body = contentTooLargeException.body
            cause = contentTooLargeException.cause
        }

        open fun headers(headers: Headers) = apply { this.headers = headers }

        open fun body(body: JsonValue) = apply { this.body = body }

        open fun cause(cause: Throwable?) = apply { this.cause = cause }

        /** Alias for calling [Builder.cause] with `cause.orElse(null)`. */
        open fun cause(cause: Optional<Throwable>) = cause(cause.getOrNull())

        /**
         * Returns an immutable instance of [ContentTooLargeException].
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
        open fun build(): ContentTooLargeException =
            ContentTooLargeException(
                checkRequired("headers", headers),
                checkRequired("body", body),
                cause,
            )
    }
}
