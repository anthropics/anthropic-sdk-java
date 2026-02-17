// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.checkRequired
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.Collections
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

class WebFetchBlockParam
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val content: JsonField<DocumentBlockParam>,
    private val type: JsonValue,
    private val url: JsonField<String>,
    private val retrievedAt: JsonField<String>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("content")
        @ExcludeMissing
        content: JsonField<DocumentBlockParam> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
        @JsonProperty("url") @ExcludeMissing url: JsonField<String> = JsonMissing.of(),
        @JsonProperty("retrieved_at")
        @ExcludeMissing
        retrievedAt: JsonField<String> = JsonMissing.of(),
    ) : this(content, type, url, retrievedAt, mutableMapOf())

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun content(): DocumentBlockParam = content.getRequired("content")

    /**
     * Expected to always return the following:
     * ```java
     * JsonValue.from("web_fetch_result")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    /**
     * Fetched content URL
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun url(): String = url.getRequired("url")

    /**
     * ISO 8601 timestamp when the content was retrieved
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun retrievedAt(): Optional<String> = retrievedAt.getOptional("retrieved_at")

    /**
     * Returns the raw JSON value of [content].
     *
     * Unlike [content], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("content") @ExcludeMissing fun _content(): JsonField<DocumentBlockParam> = content

    /**
     * Returns the raw JSON value of [url].
     *
     * Unlike [url], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("url") @ExcludeMissing fun _url(): JsonField<String> = url

    /**
     * Returns the raw JSON value of [retrievedAt].
     *
     * Unlike [retrievedAt], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("retrieved_at")
    @ExcludeMissing
    fun _retrievedAt(): JsonField<String> = retrievedAt

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

        /**
         * Returns a mutable builder for constructing an instance of [WebFetchBlockParam].
         *
         * The following fields are required:
         * ```java
         * .content()
         * .url()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [WebFetchBlockParam]. */
    class Builder internal constructor() {

        private var content: JsonField<DocumentBlockParam>? = null
        private var type: JsonValue = JsonValue.from("web_fetch_result")
        private var url: JsonField<String>? = null
        private var retrievedAt: JsonField<String> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(webFetchBlockParam: WebFetchBlockParam) = apply {
            content = webFetchBlockParam.content
            type = webFetchBlockParam.type
            url = webFetchBlockParam.url
            retrievedAt = webFetchBlockParam.retrievedAt
            additionalProperties = webFetchBlockParam.additionalProperties.toMutableMap()
        }

        fun content(content: DocumentBlockParam) = content(JsonField.of(content))

        /**
         * Sets [Builder.content] to an arbitrary JSON value.
         *
         * You should usually call [Builder.content] with a well-typed [DocumentBlockParam] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun content(content: JsonField<DocumentBlockParam>) = apply { this.content = content }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("web_fetch_result")
         * ```
         *
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun type(type: JsonValue) = apply { this.type = type }

        /** Fetched content URL */
        fun url(url: String) = url(JsonField.of(url))

        /**
         * Sets [Builder.url] to an arbitrary JSON value.
         *
         * You should usually call [Builder.url] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun url(url: JsonField<String>) = apply { this.url = url }

        /** ISO 8601 timestamp when the content was retrieved */
        fun retrievedAt(retrievedAt: String?) = retrievedAt(JsonField.ofNullable(retrievedAt))

        /** Alias for calling [Builder.retrievedAt] with `retrievedAt.orElse(null)`. */
        fun retrievedAt(retrievedAt: Optional<String>) = retrievedAt(retrievedAt.getOrNull())

        /**
         * Sets [Builder.retrievedAt] to an arbitrary JSON value.
         *
         * You should usually call [Builder.retrievedAt] with a well-typed [String] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun retrievedAt(retrievedAt: JsonField<String>) = apply { this.retrievedAt = retrievedAt }

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
         * Returns an immutable instance of [WebFetchBlockParam].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .content()
         * .url()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): WebFetchBlockParam =
            WebFetchBlockParam(
                checkRequired("content", content),
                type,
                checkRequired("url", url),
                retrievedAt,
                additionalProperties.toMutableMap(),
            )
    }

    private var validated: Boolean = false

    fun validate(): WebFetchBlockParam = apply {
        if (validated) {
            return@apply
        }

        content().validate()
        _type().let {
            if (it != JsonValue.from("web_fetch_result")) {
                throw AnthropicInvalidDataException("'type' is invalid, received $it")
            }
        }
        url()
        retrievedAt()
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
     * Returns a score indicating how many valid values are contained in this object recursively.
     *
     * Used for best match union deserialization.
     */
    @JvmSynthetic
    internal fun validity(): Int =
        (content.asKnown().getOrNull()?.validity() ?: 0) +
            type.let { if (it == JsonValue.from("web_fetch_result")) 1 else 0 } +
            (if (url.asKnown().isPresent) 1 else 0) +
            (if (retrievedAt.asKnown().isPresent) 1 else 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is WebFetchBlockParam &&
            content == other.content &&
            type == other.type &&
            url == other.url &&
            retrievedAt == other.retrievedAt &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(content, type, url, retrievedAt, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "WebFetchBlockParam{content=$content, type=$type, url=$url, retrievedAt=$retrievedAt, additionalProperties=$additionalProperties}"
}
