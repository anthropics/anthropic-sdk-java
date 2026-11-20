package com.anthropic.models.organization.workspaces

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
 * The Workspace was created, but some of what the request asked for did not take effect, or may not
 * have. Don't send the create again. The error's `message` says what to do with the Workspace at
 * `workspace_id`.
 */
class WorkspaceCreatedIncompleteException
private constructor(statusCode: Int, headers: Headers, body: JsonValue, cause: Throwable?) :
    ServiceUnavailableException(statusCode, headers, body, cause) {

    /**
     * `error.details` of [body].
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun details(): WorkspaceCreatedIncompleteErrorDetails =
        body()
            .valueAt<JsonField<WorkspaceCreatedIncompleteErrorDetails>>("error", "details")
            .orElse(JsonMissing.of())
            .getRequired("details")

    override fun toBuilder() = Builder().from(this)

    companion object {

        /**
         * Returns a mutable builder for constructing an instance of
         * [WorkspaceCreatedIncompleteException].
         *
         * The following fields are required:
         * ```java
         * .headers()
         * .body()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [WorkspaceCreatedIncompleteException]. */
    class Builder internal constructor() : ServiceUnavailableException.Builder() {

        private var statusCode: Int = 503
        private var headers: Headers? = null
        private var body: JsonValue? = null
        private var cause: Throwable? = null

        @JvmSynthetic
        internal fun from(
            workspaceCreatedIncompleteException: WorkspaceCreatedIncompleteException
        ) = apply {
            statusCode = workspaceCreatedIncompleteException.statusCode()
            headers = workspaceCreatedIncompleteException.headers()
            body = workspaceCreatedIncompleteException.body()
            cause = workspaceCreatedIncompleteException.cause
        }

        override fun statusCode(statusCode: Int) = apply { this.statusCode = statusCode }

        override fun headers(headers: Headers) = apply { this.headers = headers }

        override fun body(body: JsonValue) = apply { this.body = body }

        override fun cause(cause: Throwable?) = apply { this.cause = cause }

        /** Alias for calling [Builder.cause] with `cause.orElse(null)`. */
        override fun cause(cause: Optional<Throwable>) = cause(cause.getOrNull())

        /**
         * Returns an immutable instance of [WorkspaceCreatedIncompleteException].
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
        override fun build(): WorkspaceCreatedIncompleteException =
            WorkspaceCreatedIncompleteException(
                statusCode,
                checkRequired("headers", headers),
                checkRequired("body", body),
                cause,
            )
    }
}
