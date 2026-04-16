// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.userprofiles

import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.Params
import com.anthropic.core.http.Headers
import com.anthropic.core.http.QueryParams
import com.anthropic.core.toImmutable
import com.anthropic.errors.AnthropicInvalidDataException
import com.anthropic.models.beta.AnthropicBeta
import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.Collections
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/** Create User Profile */
class UserProfileCreateParams
private constructor(
    private val betas: List<AnthropicBeta>?,
    private val body: Body,
    private val additionalHeaders: Headers,
    private val additionalQueryParams: QueryParams,
) : Params {

    /** Optional header to specify the beta version(s) you want to use. */
    fun betas(): Optional<List<AnthropicBeta>> = Optional.ofNullable(betas)

    /**
     * Platform's own identifier for this user. Not enforced unique. Maximum 255 characters.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun externalId(): Optional<String> = body.externalId()

    /**
     * Free-form key-value data to attach to this user profile. Maximum 16 keys, with keys up to 64
     * characters and values up to 512 characters. Values must be non-empty strings.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun metadata(): Optional<Metadata> = body.metadata()

    /**
     * Returns the raw JSON value of [externalId].
     *
     * Unlike [externalId], this method doesn't throw if the JSON field has an unexpected type.
     */
    fun _externalId(): JsonField<String> = body._externalId()

    /**
     * Returns the raw JSON value of [metadata].
     *
     * Unlike [metadata], this method doesn't throw if the JSON field has an unexpected type.
     */
    fun _metadata(): JsonField<Metadata> = body._metadata()

    fun _additionalBodyProperties(): Map<String, JsonValue> = body._additionalProperties()

    /** Additional headers to send with the request. */
    fun _additionalHeaders(): Headers = additionalHeaders

    /** Additional query param to send with the request. */
    fun _additionalQueryParams(): QueryParams = additionalQueryParams

    fun toBuilder() = Builder().from(this)

    companion object {

        @JvmStatic fun none(): UserProfileCreateParams = builder().build()

        /** Returns a mutable builder for constructing an instance of [UserProfileCreateParams]. */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [UserProfileCreateParams]. */
    class Builder internal constructor() {

        private var betas: MutableList<AnthropicBeta>? = null
        private var body: Body.Builder = Body.builder()
        private var additionalHeaders: Headers.Builder = Headers.builder()
        private var additionalQueryParams: QueryParams.Builder = QueryParams.builder()

        @JvmSynthetic
        internal fun from(userProfileCreateParams: UserProfileCreateParams) = apply {
            betas = userProfileCreateParams.betas?.toMutableList()
            body = userProfileCreateParams.body.toBuilder()
            additionalHeaders = userProfileCreateParams.additionalHeaders.toBuilder()
            additionalQueryParams = userProfileCreateParams.additionalQueryParams.toBuilder()
        }

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
         * Sets the entire request body.
         *
         * This is generally only useful if you are already constructing the body separately.
         * Otherwise, it's more convenient to use the top-level setters instead:
         * - [externalId]
         * - [metadata]
         */
        fun body(body: Body) = apply { this.body = body.toBuilder() }

        /** Platform's own identifier for this user. Not enforced unique. Maximum 255 characters. */
        fun externalId(externalId: String?) = apply { body.externalId(externalId) }

        /** Alias for calling [Builder.externalId] with `externalId.orElse(null)`. */
        fun externalId(externalId: Optional<String>) = externalId(externalId.getOrNull())

        /**
         * Sets [Builder.externalId] to an arbitrary JSON value.
         *
         * You should usually call [Builder.externalId] with a well-typed [String] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun externalId(externalId: JsonField<String>) = apply { body.externalId(externalId) }

        /**
         * Free-form key-value data to attach to this user profile. Maximum 16 keys, with keys up to
         * 64 characters and values up to 512 characters. Values must be non-empty strings.
         */
        fun metadata(metadata: Metadata) = apply { body.metadata(metadata) }

        /**
         * Sets [Builder.metadata] to an arbitrary JSON value.
         *
         * You should usually call [Builder.metadata] with a well-typed [Metadata] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun metadata(metadata: JsonField<Metadata>) = apply { body.metadata(metadata) }

        fun additionalBodyProperties(additionalBodyProperties: Map<String, JsonValue>) = apply {
            body.additionalProperties(additionalBodyProperties)
        }

        fun putAdditionalBodyProperty(key: String, value: JsonValue) = apply {
            body.putAdditionalProperty(key, value)
        }

        fun putAllAdditionalBodyProperties(additionalBodyProperties: Map<String, JsonValue>) =
            apply {
                body.putAllAdditionalProperties(additionalBodyProperties)
            }

        fun removeAdditionalBodyProperty(key: String) = apply { body.removeAdditionalProperty(key) }

        fun removeAllAdditionalBodyProperties(keys: Set<String>) = apply {
            body.removeAllAdditionalProperties(keys)
        }

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
         * Returns an immutable instance of [UserProfileCreateParams].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         */
        fun build(): UserProfileCreateParams =
            UserProfileCreateParams(
                betas?.toImmutable(),
                body.build(),
                additionalHeaders.build(),
                additionalQueryParams.build(),
            )
    }

    fun _body(): Body = body

    override fun _headers(): Headers =
        Headers.builder()
            .apply {
                betas?.forEach { put("anthropic-beta", it.toString()) }
                putAll(additionalHeaders)
            }
            .build()

    override fun _queryParams(): QueryParams = additionalQueryParams

    class Body
    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
    private constructor(
        private val externalId: JsonField<String>,
        private val metadata: JsonField<Metadata>,
        private val additionalProperties: MutableMap<String, JsonValue>,
    ) {

        @JsonCreator
        private constructor(
            @JsonProperty("external_id")
            @ExcludeMissing
            externalId: JsonField<String> = JsonMissing.of(),
            @JsonProperty("metadata")
            @ExcludeMissing
            metadata: JsonField<Metadata> = JsonMissing.of(),
        ) : this(externalId, metadata, mutableMapOf())

        /**
         * Platform's own identifier for this user. Not enforced unique. Maximum 255 characters.
         *
         * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if
         *   the server responded with an unexpected value).
         */
        fun externalId(): Optional<String> = externalId.getOptional("external_id")

        /**
         * Free-form key-value data to attach to this user profile. Maximum 16 keys, with keys up to
         * 64 characters and values up to 512 characters. Values must be non-empty strings.
         *
         * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if
         *   the server responded with an unexpected value).
         */
        fun metadata(): Optional<Metadata> = metadata.getOptional("metadata")

        /**
         * Returns the raw JSON value of [externalId].
         *
         * Unlike [externalId], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("external_id")
        @ExcludeMissing
        fun _externalId(): JsonField<String> = externalId

        /**
         * Returns the raw JSON value of [metadata].
         *
         * Unlike [metadata], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("metadata") @ExcludeMissing fun _metadata(): JsonField<Metadata> = metadata

        @JsonAnySetter
        private fun putAdditionalProperty(key: String, value: JsonValue) {
            additionalProperties.put(key, value)
        }

        @JsonAnyGetter
        @ExcludeMissing
        fun _additionalProperties(): Map<String, JsonValue> =
            Collections.unmodifiableMap(additionalProperties)

        fun toBuilder() = Builder().from(this)

        companion object {

            /** Returns a mutable builder for constructing an instance of [Body]. */
            @JvmStatic fun builder() = Builder()
        }

        /** A builder for [Body]. */
        class Builder internal constructor() {

            private var externalId: JsonField<String> = JsonMissing.of()
            private var metadata: JsonField<Metadata> = JsonMissing.of()
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            @JvmSynthetic
            internal fun from(body: Body) = apply {
                externalId = body.externalId
                metadata = body.metadata
                additionalProperties = body.additionalProperties.toMutableMap()
            }

            /**
             * Platform's own identifier for this user. Not enforced unique. Maximum 255 characters.
             */
            fun externalId(externalId: String?) = externalId(JsonField.ofNullable(externalId))

            /** Alias for calling [Builder.externalId] with `externalId.orElse(null)`. */
            fun externalId(externalId: Optional<String>) = externalId(externalId.getOrNull())

            /**
             * Sets [Builder.externalId] to an arbitrary JSON value.
             *
             * You should usually call [Builder.externalId] with a well-typed [String] value
             * instead. This method is primarily for setting the field to an undocumented or not yet
             * supported value.
             */
            fun externalId(externalId: JsonField<String>) = apply { this.externalId = externalId }

            /**
             * Free-form key-value data to attach to this user profile. Maximum 16 keys, with keys
             * up to 64 characters and values up to 512 characters. Values must be non-empty
             * strings.
             */
            fun metadata(metadata: Metadata) = metadata(JsonField.of(metadata))

            /**
             * Sets [Builder.metadata] to an arbitrary JSON value.
             *
             * You should usually call [Builder.metadata] with a well-typed [Metadata] value
             * instead. This method is primarily for setting the field to an undocumented or not yet
             * supported value.
             */
            fun metadata(metadata: JsonField<Metadata>) = apply { this.metadata = metadata }

            fun additionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
                this.additionalProperties.clear()
                putAllAdditionalProperties(additionalProperties)
            }

            fun putAdditionalProperty(key: String, value: JsonValue) = apply {
                additionalProperties.put(key, value)
            }

            fun putAllAdditionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
                this.additionalProperties.putAll(additionalProperties)
            }

            fun removeAdditionalProperty(key: String) = apply { additionalProperties.remove(key) }

            fun removeAllAdditionalProperties(keys: Set<String>) = apply {
                keys.forEach(::removeAdditionalProperty)
            }

            /**
             * Returns an immutable instance of [Body].
             *
             * Further updates to this [Builder] will not mutate the returned instance.
             */
            fun build(): Body = Body(externalId, metadata, additionalProperties.toMutableMap())
        }

        private var validated: Boolean = false

        fun validate(): Body = apply {
            if (validated) {
                return@apply
            }

            externalId()
            metadata().ifPresent { it.validate() }
            validated = true
        }

        fun isValid(): Boolean =
            try {
                validate()
                true
            } catch (e: AnthropicInvalidDataException) {
                false
            }

        /**
         * Returns a score indicating how many valid values are contained in this object
         * recursively.
         *
         * Used for best match union deserialization.
         */
        @JvmSynthetic
        internal fun validity(): Int =
            (if (externalId.asKnown().isPresent) 1 else 0) +
                (metadata.asKnown().getOrNull()?.validity() ?: 0)

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Body &&
                externalId == other.externalId &&
                metadata == other.metadata &&
                additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy {
            Objects.hash(externalId, metadata, additionalProperties)
        }

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "Body{externalId=$externalId, metadata=$metadata, additionalProperties=$additionalProperties}"
    }

    /**
     * Free-form key-value data to attach to this user profile. Maximum 16 keys, with keys up to 64
     * characters and values up to 512 characters. Values must be non-empty strings.
     */
    class Metadata
    @JsonCreator
    private constructor(
        @com.fasterxml.jackson.annotation.JsonValue
        private val additionalProperties: Map<String, JsonValue>
    ) {

        @JsonAnyGetter
        @ExcludeMissing
        fun _additionalProperties(): Map<String, JsonValue> = additionalProperties

        fun toBuilder() = Builder().from(this)

        companion object {

            /** Returns a mutable builder for constructing an instance of [Metadata]. */
            @JvmStatic fun builder() = Builder()
        }

        /** A builder for [Metadata]. */
        class Builder internal constructor() {

            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            @JvmSynthetic
            internal fun from(metadata: Metadata) = apply {
                additionalProperties = metadata.additionalProperties.toMutableMap()
            }

            fun additionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
                this.additionalProperties.clear()
                putAllAdditionalProperties(additionalProperties)
            }

            fun putAdditionalProperty(key: String, value: JsonValue) = apply {
                additionalProperties.put(key, value)
            }

            fun putAllAdditionalProperties(additionalProperties: Map<String, JsonValue>) = apply {
                this.additionalProperties.putAll(additionalProperties)
            }

            fun removeAdditionalProperty(key: String) = apply { additionalProperties.remove(key) }

            fun removeAllAdditionalProperties(keys: Set<String>) = apply {
                keys.forEach(::removeAdditionalProperty)
            }

            /**
             * Returns an immutable instance of [Metadata].
             *
             * Further updates to this [Builder] will not mutate the returned instance.
             */
            fun build(): Metadata = Metadata(additionalProperties.toImmutable())
        }

        private var validated: Boolean = false

        fun validate(): Metadata = apply {
            if (validated) {
                return@apply
            }

            validated = true
        }

        fun isValid(): Boolean =
            try {
                validate()
                true
            } catch (e: AnthropicInvalidDataException) {
                false
            }

        /**
         * Returns a score indicating how many valid values are contained in this object
         * recursively.
         *
         * Used for best match union deserialization.
         */
        @JvmSynthetic
        internal fun validity(): Int =
            additionalProperties.count { (_, value) -> !value.isNull() && !value.isMissing() }

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Metadata && additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy { Objects.hash(additionalProperties) }

        override fun hashCode(): Int = hashCode

        override fun toString() = "Metadata{additionalProperties=$additionalProperties}"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is UserProfileCreateParams &&
            betas == other.betas &&
            body == other.body &&
            additionalHeaders == other.additionalHeaders &&
            additionalQueryParams == other.additionalQueryParams
    }

    override fun hashCode(): Int =
        Objects.hash(betas, body, additionalHeaders, additionalQueryParams)

    override fun toString() =
        "UserProfileCreateParams{betas=$betas, body=$body, additionalHeaders=$additionalHeaders, additionalQueryParams=$additionalQueryParams}"
}
