package com.anthropic.models.beta.compliance.review.items

import com.anthropic.core.Params
import com.anthropic.core.http.Headers
import com.anthropic.core.http.QueryParams
import com.anthropic.core.opentelemetry.SpanData
import com.anthropic.core.toImmutable
import com.anthropic.models.beta.AnthropicBeta
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/**
 * Read one message that Anthropic flagged for your organization to review, with its request and
 * response.
 *
 * A message that the API key can't read, or that isn't flagged, returns the same 404 as one that
 * doesn't exist. For a message recorded with a user profile, send that profile's ID in the
 * `anthropic-user-profile-id` header. A flagged message can also return 404 if it belongs to
 * another organization than the key's own, or to your end customer's user profile or Workspace.
 *
 * If your encryption key or storage bucket refuses the read, the API returns a 400 with
 * `cmek_key_disabled`, `storage_bucket_unreadable` or `customer_storage_refusal_unattributed` in
 * `error.details.error_code`.
 *
 * The API key must have the `read:compliance_review_items`, `read:compliance_user_data` or
 * `read:org_audit` scope. A key of an organization with no parent organization must have
 * `read:compliance_review_items`.
 */
class ItemRetrieveParams
private constructor(
    private val itemId: String?,
    private val betas: List<AnthropicBeta>?,
    private val userProfileId: String?,
    private val additionalHeaders: Headers,
    private val additionalQueryParams: QueryParams,
) : Params {

    /**
     * The ID of the item. For a message, its ID as the client received it or as a review case lists
     * it, such as `msg_...`.
     */
    fun itemId(): Optional<String> = Optional.ofNullable(itemId)

    /** Optional header to specify the beta version(s) you want to use. */
    fun betas(): Optional<List<AnthropicBeta>> = Optional.ofNullable(betas)

    /**
     * The ID of the user profile that the message was recorded with (`uprof_...`). Send it for a
     * message recorded with a user profile, and leave it out for a message recorded with none.
     */
    fun userProfileId(): Optional<String> = Optional.ofNullable(userProfileId)

    /** Additional headers to send with the request. */
    fun _additionalHeaders(): Headers = additionalHeaders

    /** Additional query param to send with the request. */
    fun _additionalQueryParams(): QueryParams = additionalQueryParams

    fun toBuilder() = Builder().from(this)

    companion object {

        @JvmStatic fun none(): ItemRetrieveParams = builder().build()

        /** Returns a mutable builder for constructing an instance of [ItemRetrieveParams]. */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [ItemRetrieveParams]. */
    class Builder internal constructor() {

        private var itemId: String? = null
        private var betas: MutableList<AnthropicBeta>? = null
        private var userProfileId: String? = null
        private var additionalHeaders: Headers.Builder = Headers.builder()
        private var additionalQueryParams: QueryParams.Builder = QueryParams.builder()

        @JvmSynthetic
        internal fun from(itemRetrieveParams: ItemRetrieveParams) = apply {
            itemId = itemRetrieveParams.itemId
            betas = itemRetrieveParams.betas?.toMutableList()
            userProfileId = itemRetrieveParams.userProfileId
            additionalHeaders = itemRetrieveParams.additionalHeaders.toBuilder()
            additionalQueryParams = itemRetrieveParams.additionalQueryParams.toBuilder()
        }

        /**
         * The ID of the item. For a message, its ID as the client received it or as a review case
         * lists it, such as `msg_...`.
         */
        fun itemId(itemId: String?) = apply { this.itemId = itemId }

        /** Alias for calling [Builder.itemId] with `itemId.orElse(null)`. */
        fun itemId(itemId: Optional<String>) = itemId(itemId.getOrNull())

        /** Optional header to specify the beta version(s) you want to use. */
        fun betas(betas: List<AnthropicBeta>?) = apply { this.betas = betas?.toMutableList() }

        /** Alias for calling [Builder.betas] with `betas.orElse(null)`. */
        fun betas(betas: Optional<List<AnthropicBeta>>) = betas(betas.getOrNull())

        /**
         * Adds a single [AnthropicBeta] to [betas].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addBeta(beta: AnthropicBeta) = apply {
            betas = (betas ?: mutableListOf()).apply { add(beta) }
        }

        /**
         * Sets [addBeta] to an arbitrary [String].
         *
         * You should usually call [addBeta] with a well-typed [AnthropicBeta] constant instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun addBeta(value: String) = addBeta(AnthropicBeta.of(value))

        /**
         * The ID of the user profile that the message was recorded with (`uprof_...`). Send it for
         * a message recorded with a user profile, and leave it out for a message recorded with
         * none.
         */
        fun userProfileId(userProfileId: String?) = apply { this.userProfileId = userProfileId }

        /** Alias for calling [Builder.userProfileId] with `userProfileId.orElse(null)`. */
        fun userProfileId(userProfileId: Optional<String>) =
            userProfileId(userProfileId.getOrNull())

        fun additionalHeaders(additionalHeaders: Headers) = apply {
            this.additionalHeaders.clear()
            putAllAdditionalHeaders(additionalHeaders)
        }

        fun additionalHeaders(additionalHeaders: Map<String, Iterable<String>>) = apply {
            this.additionalHeaders.clear()
            putAllAdditionalHeaders(additionalHeaders)
        }

        fun putAdditionalHeader(name: String, value: String) = apply {
            additionalHeaders.put(name, value)
        }

        fun putAdditionalHeaders(name: String, values: Iterable<String>) = apply {
            additionalHeaders.put(name, values)
        }

        fun putAllAdditionalHeaders(additionalHeaders: Headers) = apply {
            this.additionalHeaders.putAll(additionalHeaders)
        }

        fun putAllAdditionalHeaders(additionalHeaders: Map<String, Iterable<String>>) = apply {
            this.additionalHeaders.putAll(additionalHeaders)
        }

        fun replaceAdditionalHeaders(name: String, value: String) = apply {
            additionalHeaders.replace(name, value)
        }

        fun replaceAdditionalHeaders(name: String, values: Iterable<String>) = apply {
            additionalHeaders.replace(name, values)
        }

        fun replaceAllAdditionalHeaders(additionalHeaders: Headers) = apply {
            this.additionalHeaders.replaceAll(additionalHeaders)
        }

        fun replaceAllAdditionalHeaders(additionalHeaders: Map<String, Iterable<String>>) = apply {
            this.additionalHeaders.replaceAll(additionalHeaders)
        }

        fun removeAdditionalHeaders(name: String) = apply { additionalHeaders.remove(name) }

        fun removeAllAdditionalHeaders(names: Set<String>) = apply {
            additionalHeaders.removeAll(names)
        }

        fun additionalQueryParams(additionalQueryParams: QueryParams) = apply {
            this.additionalQueryParams.clear()
            putAllAdditionalQueryParams(additionalQueryParams)
        }

        fun additionalQueryParams(additionalQueryParams: Map<String, Iterable<String>>) = apply {
            this.additionalQueryParams.clear()
            putAllAdditionalQueryParams(additionalQueryParams)
        }

        fun putAdditionalQueryParam(key: String, value: String) = apply {
            additionalQueryParams.put(key, value)
        }

        fun putAdditionalQueryParams(key: String, values: Iterable<String>) = apply {
            additionalQueryParams.put(key, values)
        }

        fun putAllAdditionalQueryParams(additionalQueryParams: QueryParams) = apply {
            this.additionalQueryParams.putAll(additionalQueryParams)
        }

        fun putAllAdditionalQueryParams(additionalQueryParams: Map<String, Iterable<String>>) =
            apply {
                this.additionalQueryParams.putAll(additionalQueryParams)
            }

        fun replaceAdditionalQueryParams(key: String, value: String) = apply {
            additionalQueryParams.replace(key, value)
        }

        fun replaceAdditionalQueryParams(key: String, values: Iterable<String>) = apply {
            additionalQueryParams.replace(key, values)
        }

        fun replaceAllAdditionalQueryParams(additionalQueryParams: QueryParams) = apply {
            this.additionalQueryParams.replaceAll(additionalQueryParams)
        }

        fun replaceAllAdditionalQueryParams(additionalQueryParams: Map<String, Iterable<String>>) =
            apply {
                this.additionalQueryParams.replaceAll(additionalQueryParams)
            }

        fun removeAdditionalQueryParams(key: String) = apply { additionalQueryParams.remove(key) }

        fun removeAllAdditionalQueryParams(keys: Set<String>) = apply {
            additionalQueryParams.removeAll(keys)
        }

        /**
         * Returns an immutable instance of [ItemRetrieveParams].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         */
        fun build(): ItemRetrieveParams =
            ItemRetrieveParams(
                itemId,
                betas?.toImmutable(),
                userProfileId,
                additionalHeaders.build(),
                additionalQueryParams.build(),
            )
    }

    fun _pathParam(index: Int): String =
        when (index) {
            0 -> itemId ?: ""
            else -> ""
        }

    override fun _headers(): Headers =
        Headers.builder()
            .apply {
                betas?.let {
                    if (it.isNotEmpty()) {
                        put("anthropic-beta", it.joinToString(","))
                    }
                }
                userProfileId?.let { put("anthropic-user-profile-id", it) }
                putAll(additionalHeaders)
            }
            .build()

    override fun _queryParams(): QueryParams = additionalQueryParams

    override fun _spanData(): SpanData =
        SpanData(
            name = "anthropic.compliance.review.items.retrieve",
            route = "/v1/compliance/review/items/{item_id}",
            attributes = mapOf("anthropic.compliance.review.item.id" to _pathParam(0)),
        )

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is ItemRetrieveParams &&
            itemId == other.itemId &&
            betas == other.betas &&
            userProfileId == other.userProfileId &&
            additionalHeaders == other.additionalHeaders &&
            additionalQueryParams == other.additionalQueryParams
    }

    override fun hashCode(): Int =
        Objects.hash(itemId, betas, userProfileId, additionalHeaders, additionalQueryParams)

    override fun toString() =
        "ItemRetrieveParams{itemId=$itemId, betas=$betas, userProfileId=$userProfileId, additionalHeaders=$additionalHeaders, additionalQueryParams=$additionalQueryParams}"
}
