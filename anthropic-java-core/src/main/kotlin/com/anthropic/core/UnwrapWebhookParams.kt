// File generated from our OpenAPI spec by Stainless.

package com.anthropic.core

import com.anthropic.core.http.Headers
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

class UnwrapWebhookParams
private constructor(
    private val body: String,
    private val headers: Headers?,
    private val secret: String?,
) {

    /** The raw JSON body of the webhook request. */
    fun body(): String = body

    /** The headers from the webhook request. */
    fun headers(): Optional<Headers> = Optional.ofNullable(headers)

    /** The secret used to verify the webhook signature. */
    fun secret(): Optional<String> = Optional.ofNullable(secret)

    fun toBuilder() = Builder().from(this)

    companion object {

        /**
         * Returns a mutable builder for constructing an instance of [UnwrapWebhookParams].
         *
         * The following fields are required:
         * ```java
         * .body()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [UnwrapWebhookParams]. */
    class Builder internal constructor() {

        private var body: String? = null
        private var headers: Headers? = null
        private var secret: String? = null

        @JvmSynthetic
        internal fun from(unwrapWebhookParams: UnwrapWebhookParams) = apply {
            body = unwrapWebhookParams.body
            headers = unwrapWebhookParams.headers
            secret = unwrapWebhookParams.secret
        }

        /** The raw JSON body of the webhook request. */
        fun body(body: String) = apply { this.body = body }

        /** The headers from the webhook request. */
        fun headers(headers: Headers?) = apply { this.headers = headers }

        /** Alias for calling [Builder.headers] with `headers.orElse(null)`. */
        fun headers(headers: Optional<Headers>) = headers(headers.getOrNull())

        /** The secret used to verify the webhook signature. */
        fun secret(secret: String?) = apply { this.secret = secret }

        /** Alias for calling [Builder.secret] with `secret.orElse(null)`. */
        fun secret(secret: Optional<String>) = secret(secret.getOrNull())

        /**
         * Returns an immutable instance of [UnwrapWebhookParams].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .body()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): UnwrapWebhookParams =
            UnwrapWebhookParams(checkRequired("body", body), headers, secret)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is UnwrapWebhookParams &&
            body == other.body &&
            headers == other.headers &&
            secret == other.secret
    }

    private val hashCode: Int by lazy { Objects.hash(body, headers, secret) }

    override fun hashCode(): Int = hashCode

    override fun toString() = "UnwrapWebhookParams{body=$body, headers=$headers, secret=$secret}"
}
