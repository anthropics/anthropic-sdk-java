package com.anthropic.errors

import com.anthropic.core.JsonValue
import com.anthropic.core.checkRequired
import com.anthropic.core.http.Headers
import com.anthropic.core.jsonMapper
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

open class PreconditionFailedException
protected constructor(
    private val headers: Headers,
    private val body: JsonValue,
    cause: Throwable?,
) :
    AnthropicServiceException(
        "412: ${if (body.isMissing()) "Unknown" else jsonMapper().writeValueAsString(body)}",
        cause,
    ) {

    override fun statusCode(): Int = 412

    override fun headers(): Headers = headers

    override fun body(): JsonValue = body

    open fun toBuilder() = Builder().from(this)

    companion object {

        /**
         * Returns a mutable builder for constructing an instance of [PreconditionFailedException].
         *
         * The following fields are required:
         * ```java
         * .headers()
         * .body()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [PreconditionFailedException]. */
    open class Builder internal constructor() {

        private var headers: Headers? = null
        private var body: JsonValue? = null
        private var cause: Throwable? = null

        @JvmSynthetic
        internal fun from(preconditionFailedException: PreconditionFailedException) = apply {
            headers = preconditionFailedException.headers
            body = preconditionFailedException.body
            cause = preconditionFailedException.cause
        }

        open fun headers(headers: Headers) = apply { this.headers = headers }

        open fun body(body: JsonValue) = apply { this.body = body }

        open fun cause(cause: Throwable?) = apply { this.cause = cause }

        /** Alias for calling [Builder.cause] with `cause.orElse(null)`. */
        open fun cause(cause: Optional<Throwable>) = cause(cause.getOrNull())

        /**
         * Returns an immutable instance of [PreconditionFailedException].
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
        open fun build(): PreconditionFailedException =
            PreconditionFailedException(
                checkRequired("headers", headers),
                checkRequired("body", body),
                cause,
            )
    }
}
