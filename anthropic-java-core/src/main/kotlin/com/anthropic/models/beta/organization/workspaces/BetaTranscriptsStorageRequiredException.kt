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
 * In an organization managed through AWS or Google Cloud, the request would leave the Workspace
 * with `inference_data_privacy.human_review` disabled and
 * `inference_data_privacy.transcripts_storage` not set to External Storage. With human review
 * disabled, the organization reviews its own flagged requests, and it receives them only in that
 * storage.
 */
class BetaTranscriptsStorageRequiredException
private constructor(headers: Headers, body: JsonValue, cause: Throwable?) :
    BadRequestException(headers, body, cause) {

    /**
     * `error.details` of [body].
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun details(): BetaTranscriptsStorageRequiredErrorDetails =
        body()
            .valueAt<JsonField<BetaTranscriptsStorageRequiredErrorDetails>>("error", "details")
            .orElse(JsonMissing.of())
            .getRequired("details")

    override fun toBuilder() = Builder().from(this)

    companion object {

        /**
         * Returns a mutable builder for constructing an instance of
         * [BetaTranscriptsStorageRequiredException].
         *
         * The following fields are required:
         * ```java
         * .headers()
         * .body()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaTranscriptsStorageRequiredException]. */
    class Builder internal constructor() : BadRequestException.Builder() {

        private var headers: Headers? = null
        private var body: JsonValue? = null
        private var cause: Throwable? = null

        @JvmSynthetic
        internal fun from(
            betaTranscriptsStorageRequiredException: BetaTranscriptsStorageRequiredException
        ) = apply {
            headers = betaTranscriptsStorageRequiredException.headers()
            body = betaTranscriptsStorageRequiredException.body()
            cause = betaTranscriptsStorageRequiredException.cause
        }

        override fun headers(headers: Headers) = apply { this.headers = headers }

        override fun body(body: JsonValue) = apply { this.body = body }

        override fun cause(cause: Throwable?) = apply { this.cause = cause }

        /** Alias for calling [Builder.cause] with `cause.orElse(null)`. */
        override fun cause(cause: Optional<Throwable>) = cause(cause.getOrNull())

        /**
         * Returns an immutable instance of [BetaTranscriptsStorageRequiredException].
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
        override fun build(): BetaTranscriptsStorageRequiredException =
            BetaTranscriptsStorageRequiredException(
                checkRequired("headers", headers),
                checkRequired("body", body),
                cause,
            )
    }
}
