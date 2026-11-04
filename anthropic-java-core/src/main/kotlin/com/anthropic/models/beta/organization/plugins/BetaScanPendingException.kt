package com.anthropic.models.beta.organization.plugins

import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.checkRequired
import com.anthropic.core.http.Headers
import com.anthropic.core.valueAt
import com.anthropic.errors.AnthropicInvalidDataException
import com.anthropic.errors.ConflictException
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/**
 * The content scan of the version in `plugin_version_id` is still running. Retry once the scan
 * finishes.
 */
class BetaScanPendingException
private constructor(headers: Headers, body: JsonValue, cause: Throwable?) :
    ConflictException(headers, body, cause) {

    /**
     * `error.details` of [body].
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun details(): BetaScanPendingErrorDetails =
        body()
            .valueAt<JsonField<BetaScanPendingErrorDetails>>("error", "details")
            .orElse(JsonMissing.of())
            .getRequired("details")

    override fun toBuilder() = Builder().from(this)

    companion object {

        /**
         * Returns a mutable builder for constructing an instance of [BetaScanPendingException].
         *
         * The following fields are required:
         * ```java
         * .headers()
         * .body()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaScanPendingException]. */
    class Builder internal constructor() : ConflictException.Builder() {

        private var headers: Headers? = null
        private var body: JsonValue? = null
        private var cause: Throwable? = null

        @JvmSynthetic
        internal fun from(betaScanPendingException: BetaScanPendingException) = apply {
            headers = betaScanPendingException.headers()
            body = betaScanPendingException.body()
            cause = betaScanPendingException.cause
        }

        override fun headers(headers: Headers) = apply { this.headers = headers }

        override fun body(body: JsonValue) = apply { this.body = body }

        override fun cause(cause: Throwable?) = apply { this.cause = cause }

        /** Alias for calling [Builder.cause] with `cause.orElse(null)`. */
        override fun cause(cause: Optional<Throwable>) = cause(cause.getOrNull())

        /**
         * Returns an immutable instance of [BetaScanPendingException].
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
        override fun build(): BetaScanPendingException =
            BetaScanPendingException(
                checkRequired("headers", headers),
                checkRequired("body", body),
                cause,
            )
    }
}
