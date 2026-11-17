package com.anthropic.models

import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.checkRequired
import com.anthropic.core.http.Headers
import com.anthropic.core.valueAt
import com.anthropic.errors.AnthropicInvalidDataException
import com.anthropic.errors.RateLimitException
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/** The organization has reached the spend limit of its contract. */
class ContractSpendLimitReachedException
private constructor(headers: Headers, body: JsonValue, cause: Throwable?) :
    RateLimitException(headers, body, cause) {

    /**
     * `error.details` of [body].
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun details(): ContractSpendLimitReachedErrorDetails =
        body()
            .valueAt<JsonField<ContractSpendLimitReachedErrorDetails>>("error", "details")
            .orElse(JsonMissing.of())
            .getRequired("details")

    override fun toBuilder() = Builder().from(this)

    companion object {

        /**
         * Returns a mutable builder for constructing an instance of
         * [ContractSpendLimitReachedException].
         *
         * The following fields are required:
         * ```java
         * .headers()
         * .body()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [ContractSpendLimitReachedException]. */
    class Builder internal constructor() : RateLimitException.Builder() {

        private var headers: Headers? = null
        private var body: JsonValue? = null
        private var cause: Throwable? = null

        @JvmSynthetic
        internal fun from(contractSpendLimitReachedException: ContractSpendLimitReachedException) =
            apply {
                headers = contractSpendLimitReachedException.headers()
                body = contractSpendLimitReachedException.body()
                cause = contractSpendLimitReachedException.cause
            }

        override fun headers(headers: Headers) = apply { this.headers = headers }

        override fun body(body: JsonValue) = apply { this.body = body }

        override fun cause(cause: Throwable?) = apply { this.cause = cause }

        /** Alias for calling [Builder.cause] with `cause.orElse(null)`. */
        override fun cause(cause: Optional<Throwable>) = cause(cause.getOrNull())

        /**
         * Returns an immutable instance of [ContractSpendLimitReachedException].
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
        override fun build(): ContractSpendLimitReachedException =
            ContractSpendLimitReachedException(
                checkRequired("headers", headers),
                checkRequired("body", body),
                cause,
            )
    }
}
