package com.anthropic.models.organization

import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.checkRequired
import com.anthropic.core.http.Headers
import com.anthropic.core.valueAt
import com.anthropic.errors.AnthropicInvalidDataException
import com.anthropic.errors.ServiceUnavailableException
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/**
 * Some of the request's changes may have been saved and others not. Send the same request again to
 * finish it; changes already saved aren't repeated.
 */
class ChangeOutcomeUnknownException
private constructor(statusCode: Int, headers: Headers, body: JsonValue, cause: Throwable?) :
    ServiceUnavailableException(statusCode, headers, body, cause) {

    /**
     * `error.details` of [body].
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun details(): ChangeOutcomeUnknownErrorDetails =
        body()
            .valueAt<JsonField<ChangeOutcomeUnknownErrorDetails>>("error", "details")
            .orElse(JsonMissing.of())
            .getRequired("details")

    override fun toBuilder() = Builder().from(this)

    companion object {

        /**
         * Returns a mutable builder for constructing an instance of
         * [ChangeOutcomeUnknownException].
         *
         * The following fields are required:
         * ```java
         * .headers()
         * .body()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [ChangeOutcomeUnknownException]. */
    class Builder internal constructor() : ServiceUnavailableException.Builder() {

        private var statusCode: Int = 503
        private var headers: Headers? = null
        private var body: JsonValue? = null
        private var cause: Throwable? = null

        @JvmSynthetic
        internal fun from(changeOutcomeUnknownException: ChangeOutcomeUnknownException) = apply {
            statusCode = changeOutcomeUnknownException.statusCode()
            headers = changeOutcomeUnknownException.headers()
            body = changeOutcomeUnknownException.body()
            cause = changeOutcomeUnknownException.cause
        }

        override fun statusCode(statusCode: Int) = apply { this.statusCode = statusCode }

        override fun headers(headers: Headers) = apply { this.headers = headers }

        override fun body(body: JsonValue) = apply { this.body = body }

        override fun cause(cause: Throwable?) = apply { this.cause = cause }

        /** Alias for calling [Builder.cause] with `cause.orElse(null)`. */
        override fun cause(cause: Optional<Throwable>) = cause(cause.getOrNull())

        /**
         * Returns an immutable instance of [ChangeOutcomeUnknownException].
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
        override fun build(): ChangeOutcomeUnknownException =
            ChangeOutcomeUnknownException(
                statusCode,
                checkRequired("headers", headers),
                checkRequired("body", body),
                cause,
            )
    }
}
