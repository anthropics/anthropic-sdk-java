package com.anthropic.models.beta.organization.claudecode.managedsettings

import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.checkRequired
import com.anthropic.core.http.Headers
import com.anthropic.core.valueAt
import com.anthropic.errors.AnthropicInvalidDataException
import com.anthropic.errors.NotFoundException
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/** This RBAC Group has no Claude Code managed settings. */
class BetaRbacGroupManagedSettingsNotFoundException
private constructor(headers: Headers, body: JsonValue, cause: Throwable?) :
    NotFoundException(headers, body, cause) {

    /**
     * `error.details` of [body].
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun details(): BetaRbacGroupManagedSettingsNotFoundErrorDetails =
        body()
            .valueAt<JsonField<BetaRbacGroupManagedSettingsNotFoundErrorDetails>>(
                "error",
                "details",
            )
            .orElse(JsonMissing.of())
            .getRequired("details")

    override fun toBuilder() = Builder().from(this)

    companion object {

        /**
         * Returns a mutable builder for constructing an instance of
         * [BetaRbacGroupManagedSettingsNotFoundException].
         *
         * The following fields are required:
         * ```java
         * .headers()
         * .body()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaRbacGroupManagedSettingsNotFoundException]. */
    class Builder internal constructor() : NotFoundException.Builder() {

        private var headers: Headers? = null
        private var body: JsonValue? = null
        private var cause: Throwable? = null

        @JvmSynthetic
        internal fun from(
            betaRbacGroupManagedSettingsNotFoundException:
                BetaRbacGroupManagedSettingsNotFoundException
        ) = apply {
            headers = betaRbacGroupManagedSettingsNotFoundException.headers()
            body = betaRbacGroupManagedSettingsNotFoundException.body()
            cause = betaRbacGroupManagedSettingsNotFoundException.cause
        }

        override fun headers(headers: Headers) = apply { this.headers = headers }

        override fun body(body: JsonValue) = apply { this.body = body }

        override fun cause(cause: Throwable?) = apply { this.cause = cause }

        /** Alias for calling [Builder.cause] with `cause.orElse(null)`. */
        override fun cause(cause: Optional<Throwable>) = cause(cause.getOrNull())

        /**
         * Returns an immutable instance of [BetaRbacGroupManagedSettingsNotFoundException].
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
        override fun build(): BetaRbacGroupManagedSettingsNotFoundException =
            BetaRbacGroupManagedSettingsNotFoundException(
                checkRequired("headers", headers),
                checkRequired("body", body),
                cause,
            )
    }
}
