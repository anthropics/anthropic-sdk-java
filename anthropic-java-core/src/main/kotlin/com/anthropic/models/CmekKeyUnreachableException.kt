package com.anthropic.models

import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.checkRequired
import com.anthropic.core.handlers.valueAt
import com.anthropic.core.http.Headers
import com.anthropic.errors.AnthropicInvalidDataException
import com.anthropic.errors.ServiceUnavailableException
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/**
 * The workspace's customer-managed encryption key could not be reached. Retry the request; if this
 * continues, check that the key is available in your key management service, or contact Anthropic
 * support.
 */
class CmekKeyUnreachableException
private constructor(statusCode: Int, headers: Headers, body: JsonValue, cause: Throwable?) :
    ServiceUnavailableException(statusCode, headers, body, cause) {

    /**
     * `error.details` of [body].
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun details(): CmekKeyUnreachableErrorDetails =
        body()
            .valueAt<JsonField<CmekKeyUnreachableErrorDetails>>("error", "details")
            .orElse(JsonMissing.of())
            .getRequired("details")

    override fun toBuilder() = Builder().from(this)

    companion object {

        /**
         * Returns a mutable builder for constructing an instance of [CmekKeyUnreachableException].
         *
         * The following fields are required:
         * ```java
         * .headers()
         * .body()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [CmekKeyUnreachableException]. */
    class Builder internal constructor() : ServiceUnavailableException.Builder() {

        private var statusCode: Int = 503
        private var headers: Headers? = null
        private var body: JsonValue? = null
        private var cause: Throwable? = null

        @JvmSynthetic
        internal fun from(cmekKeyUnreachableException: CmekKeyUnreachableException) = apply {
            statusCode = cmekKeyUnreachableException.statusCode()
            headers = cmekKeyUnreachableException.headers()
            body = cmekKeyUnreachableException.body()
            cause = cmekKeyUnreachableException.cause
        }

        override fun statusCode(statusCode: Int) = apply { this.statusCode = statusCode }

        override fun headers(headers: Headers) = apply { this.headers = headers }

        override fun body(body: JsonValue) = apply { this.body = body }

        override fun cause(cause: Throwable?) = apply { this.cause = cause }

        /** Alias for calling [Builder.cause] with `cause.orElse(null)`. */
        override fun cause(cause: Optional<Throwable>) = cause(cause.getOrNull())

        /**
         * Returns an immutable instance of [CmekKeyUnreachableException].
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
        override fun build(): CmekKeyUnreachableException =
            CmekKeyUnreachableException(
                statusCode,
                checkRequired("headers", headers),
                checkRequired("body", body),
                cause,
            )
    }
}
