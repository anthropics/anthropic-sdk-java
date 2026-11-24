package com.anthropic.models.beta

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
 * Another organization has data controls turned on for this Google Cloud project, or turned them
 * off recently. The message says how long to wait.
 */
class BetaGcpProjectControlledByAnotherOrganizationException
private constructor(headers: Headers, body: JsonValue, cause: Throwable?) :
    ConflictException(headers, body, cause) {

    /**
     * `error.details` of [body].
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun details(): BetaGcpProjectControlledByAnotherOrganizationErrorDetails =
        body()
            .valueAt<JsonField<BetaGcpProjectControlledByAnotherOrganizationErrorDetails>>(
                "error",
                "details",
            )
            .orElse(JsonMissing.of())
            .getRequired("details")

    override fun toBuilder() = Builder().from(this)

    companion object {

        /**
         * Returns a mutable builder for constructing an instance of
         * [BetaGcpProjectControlledByAnotherOrganizationException].
         *
         * The following fields are required:
         * ```java
         * .headers()
         * .body()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaGcpProjectControlledByAnotherOrganizationException]. */
    class Builder internal constructor() : ConflictException.Builder() {

        private var headers: Headers? = null
        private var body: JsonValue? = null
        private var cause: Throwable? = null

        @JvmSynthetic
        internal fun from(
            betaGcpProjectControlledByAnotherOrganizationException:
                BetaGcpProjectControlledByAnotherOrganizationException
        ) = apply {
            headers = betaGcpProjectControlledByAnotherOrganizationException.headers()
            body = betaGcpProjectControlledByAnotherOrganizationException.body()
            cause = betaGcpProjectControlledByAnotherOrganizationException.cause
        }

        override fun headers(headers: Headers) = apply { this.headers = headers }

        override fun body(body: JsonValue) = apply { this.body = body }

        override fun cause(cause: Throwable?) = apply { this.cause = cause }

        /** Alias for calling [Builder.cause] with `cause.orElse(null)`. */
        override fun cause(cause: Optional<Throwable>) = cause(cause.getOrNull())

        /**
         * Returns an immutable instance of
         * [BetaGcpProjectControlledByAnotherOrganizationException].
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
        override fun build(): BetaGcpProjectControlledByAnotherOrganizationException =
            BetaGcpProjectControlledByAnotherOrganizationException(
                checkRequired("headers", headers),
                checkRequired("body", body),
                cause,
            )
    }
}
