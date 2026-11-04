package com.anthropic.models.messages

import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.checkRequired
import com.anthropic.core.handlers.valueAt
import com.anthropic.core.http.Headers
import com.anthropic.errors.AnthropicInvalidDataException
import com.anthropic.errors.OverloadedException
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/** The compaction could not be completed for a transient server reason. Retry the request. */
class CompactionUnavailableException
private constructor(statusCode: Int, headers: Headers, body: JsonValue, cause: Throwable?) :
    OverloadedException(statusCode, headers, body, cause) {

    /**
     * `error.details` of [body].
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun details(): CompactionUnavailableErrorDetails =
        body()
            .valueAt<JsonField<CompactionUnavailableErrorDetails>>("error", "details")
            .orElse(JsonMissing.of())
            .getRequired("details")

    override fun toBuilder() = Builder().from(this)

    companion object {

        /**
         * Returns a mutable builder for constructing an instance of
         * [CompactionUnavailableException].
         *
         * The following fields are required:
         * ```java
         * .headers()
         * .body()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [CompactionUnavailableException]. */
    class Builder internal constructor() : OverloadedException.Builder() {

        private var statusCode: Int = 529
        private var headers: Headers? = null
        private var body: JsonValue? = null
        private var cause: Throwable? = null

        @JvmSynthetic
        internal fun from(compactionUnavailableException: CompactionUnavailableException) = apply {
            statusCode = compactionUnavailableException.statusCode()
            headers = compactionUnavailableException.headers()
            body = compactionUnavailableException.body()
            cause = compactionUnavailableException.cause
        }

        override fun statusCode(statusCode: Int) = apply { this.statusCode = statusCode }

        override fun headers(headers: Headers) = apply { this.headers = headers }

        override fun body(body: JsonValue) = apply { this.body = body }

        override fun cause(cause: Throwable?) = apply { this.cause = cause }

        /** Alias for calling [Builder.cause] with `cause.orElse(null)`. */
        override fun cause(cause: Optional<Throwable>) = cause(cause.getOrNull())

        /**
         * Returns an immutable instance of [CompactionUnavailableException].
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
        override fun build(): CompactionUnavailableException =
            CompactionUnavailableException(
                statusCode,
                checkRequired("headers", headers),
                checkRequired("body", body),
                cause,
            )
    }
}
