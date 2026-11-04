package com.anthropic.errors

import com.anthropic.core.JsonValue
import com.anthropic.core.checkRequired
import com.anthropic.core.http.Headers
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

open class OverloadedException
protected constructor(statusCode: Int, headers: Headers, body: JsonValue, cause: Throwable?) :
    InternalServerException(statusCode, headers, body, cause) {

    override fun toBuilder() = Builder().from(this)

    companion object {

        /**
         * Returns a mutable builder for constructing an instance of [OverloadedException].
         *
         * The following fields are required:
         * ```java
         * .headers()
         * .body()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [OverloadedException]. */
    open class Builder internal constructor() : InternalServerException.Builder() {

        private var statusCode: Int = 529
        private var headers: Headers? = null
        private var body: JsonValue? = null
        private var cause: Throwable? = null

        @JvmSynthetic
        internal fun from(overloadedException: OverloadedException) = apply {
            statusCode = overloadedException.statusCode()
            headers = overloadedException.headers()
            body = overloadedException.body()
            cause = overloadedException.cause
        }

        override fun statusCode(statusCode: Int) = apply { this.statusCode = statusCode }

        override fun headers(headers: Headers) = apply { this.headers = headers }

        override fun body(body: JsonValue) = apply { this.body = body }

        override fun cause(cause: Throwable?) = apply { this.cause = cause }

        /** Alias for calling [Builder.cause] with `cause.orElse(null)`. */
        override fun cause(cause: Optional<Throwable>) = cause(cause.getOrNull())

        /**
         * Returns an immutable instance of [OverloadedException].
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
        override fun build(): OverloadedException =
            OverloadedException(
                statusCode,
                checkRequired("headers", headers),
                checkRequired("body", body),
                cause,
            )
    }
}
