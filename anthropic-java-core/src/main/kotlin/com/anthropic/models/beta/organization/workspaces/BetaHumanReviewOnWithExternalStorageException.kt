package com.anthropic.models.beta.organization.workspaces

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
 * The request would leave the Workspace with `inference_data_privacy.safeguards_storage` set to
 * External Storage and `inference_data_privacy.human_review` enabled. Anthropic doesn't offer human
 * review of data in a customer's own storage. Set `human_review` to `disabled`, or
 * `safeguards_storage` to `internal`.
 */
class BetaHumanReviewOnWithExternalStorageException
private constructor(headers: Headers, body: JsonValue, cause: Throwable?) :
    BadRequestException(headers, body, cause) {

    /**
     * `error.details` of [body].
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun details(): BetaHumanReviewOnWithExternalStorageErrorDetails =
        body()
            .valueAt<JsonField<BetaHumanReviewOnWithExternalStorageErrorDetails>>(
                "error",
                "details",
            )
            .orElse(JsonMissing.of())
            .getRequired("details")

    override fun toBuilder() = Builder().from(this)

    companion object {

        /**
         * Returns a mutable builder for constructing an instance of
         * [BetaHumanReviewOnWithExternalStorageException].
         *
         * The following fields are required:
         * ```java
         * .headers()
         * .body()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaHumanReviewOnWithExternalStorageException]. */
    class Builder internal constructor() : BadRequestException.Builder() {

        private var headers: Headers? = null
        private var body: JsonValue? = null
        private var cause: Throwable? = null

        @JvmSynthetic
        internal fun from(
            betaHumanReviewOnWithExternalStorageException:
                BetaHumanReviewOnWithExternalStorageException
        ) = apply {
            headers = betaHumanReviewOnWithExternalStorageException.headers()
            body = betaHumanReviewOnWithExternalStorageException.body()
            cause = betaHumanReviewOnWithExternalStorageException.cause
        }

        override fun headers(headers: Headers) = apply { this.headers = headers }

        override fun body(body: JsonValue) = apply { this.body = body }

        override fun cause(cause: Throwable?) = apply { this.cause = cause }

        /** Alias for calling [Builder.cause] with `cause.orElse(null)`. */
        override fun cause(cause: Optional<Throwable>) = cause(cause.getOrNull())

        /**
         * Returns an immutable instance of [BetaHumanReviewOnWithExternalStorageException].
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
        override fun build(): BetaHumanReviewOnWithExternalStorageException =
            BetaHumanReviewOnWithExternalStorageException(
                checkRequired("headers", headers),
                checkRequired("body", body),
                cause,
            )
    }
}
