package com.anthropic.models.organization

import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.checkRequired
import com.anthropic.core.handlers.valueAt
import com.anthropic.core.http.Headers
import com.anthropic.errors.AnthropicInvalidDataException
import com.anthropic.errors.ConflictException
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/**
 * Another change to the organization's members is in progress. Retry the request once it completes.
 */
class ConcurrentMembershipChangeException
private constructor(headers: Headers, body: JsonValue, cause: Throwable?) :
    ConflictException(headers, body, cause) {

    /**
     * `error.details` of [body].
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun details(): ConcurrentMembershipChangeErrorDetails =
        body()
            .valueAt<JsonField<ConcurrentMembershipChangeErrorDetails>>("error", "details")
            .orElse(JsonMissing.of())
            .getRequired("details")

    override fun toBuilder() = Builder().from(this)

    companion object {

        /**
         * Returns a mutable builder for constructing an instance of
         * [ConcurrentMembershipChangeException].
         *
         * The following fields are required:
         * ```java
         * .headers()
         * .body()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [ConcurrentMembershipChangeException]. */
    class Builder internal constructor() : ConflictException.Builder() {

        private var headers: Headers? = null
        private var body: JsonValue? = null
        private var cause: Throwable? = null

        @JvmSynthetic
        internal fun from(
            concurrentMembershipChangeException: ConcurrentMembershipChangeException
        ) = apply {
            headers = concurrentMembershipChangeException.headers()
            body = concurrentMembershipChangeException.body()
            cause = concurrentMembershipChangeException.cause
        }

        override fun headers(headers: Headers) = apply { this.headers = headers }

        override fun body(body: JsonValue) = apply { this.body = body }

        override fun cause(cause: Throwable?) = apply { this.cause = cause }

        /** Alias for calling [Builder.cause] with `cause.orElse(null)`. */
        override fun cause(cause: Optional<Throwable>) = cause(cause.getOrNull())

        /**
         * Returns an immutable instance of [ConcurrentMembershipChangeException].
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
        override fun build(): ConcurrentMembershipChangeException =
            ConcurrentMembershipChangeException(
                checkRequired("headers", headers),
                checkRequired("body", body),
                cause,
            )
    }
}
