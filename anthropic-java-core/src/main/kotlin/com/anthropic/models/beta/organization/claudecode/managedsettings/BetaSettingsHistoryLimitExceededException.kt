package com.anthropic.models.beta.organization.claudecode.managedsettings

import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.checkRequired
import com.anthropic.core.http.Headers
import com.anthropic.core.valueAt
import com.anthropic.errors.AnthropicInvalidDataException
import com.anthropic.errors.BadRequestException
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/**
 * The organization has reached its settings history limit, a cap on stored versions of its settings
 * documents. Nothing in the API or claude.ai lowers the count, so contact support. Creating or
 * deleting group settings, creating or clearing the default settings, and reordering are refused.
 * Changing the mode still works, unless it would copy the organization settings into a new default.
 */
class BetaSettingsHistoryLimitExceededException
private constructor(headers: Headers, body: JsonValue, cause: Throwable?) :
    BadRequestException(headers, body, cause) {

    /**
     * `error.details` of [body].
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun details(): BetaSettingsHistoryLimitExceededErrorDetails =
        body()
            .valueAt<JsonField<BetaSettingsHistoryLimitExceededErrorDetails>>("error", "details")
            .orElse(JsonMissing.of())
            .getRequired("details")

    override fun toBuilder() = Builder().from(this)

    companion object {

        /**
         * Returns a mutable builder for constructing an instance of
         * [BetaSettingsHistoryLimitExceededException].
         *
         * The following fields are required:
         * ```java
         * .headers()
         * .body()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaSettingsHistoryLimitExceededException]. */
    class Builder internal constructor() : BadRequestException.Builder() {

        private var headers: Headers? = null
        private var body: JsonValue? = null
        private var cause: Throwable? = null

        @JvmSynthetic
        internal fun from(
            betaSettingsHistoryLimitExceededException: BetaSettingsHistoryLimitExceededException
        ) = apply {
            headers = betaSettingsHistoryLimitExceededException.headers()
            body = betaSettingsHistoryLimitExceededException.body()
            cause = betaSettingsHistoryLimitExceededException.cause
        }

        override fun headers(headers: Headers) = apply { this.headers = headers }

        override fun body(body: JsonValue) = apply { this.body = body }

        override fun cause(cause: Throwable?) = apply { this.cause = cause }

        /** Alias for calling [Builder.cause] with `cause.orElse(null)`. */
        override fun cause(cause: Optional<Throwable>) = cause(cause.getOrNull())

        /**
         * Returns an immutable instance of [BetaSettingsHistoryLimitExceededException].
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
        override fun build(): BetaSettingsHistoryLimitExceededException =
            BetaSettingsHistoryLimitExceededException(
                checkRequired("headers", headers),
                checkRequired("body", body),
                cause,
            )
    }
}
