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

class WebFetchBlock
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val content: JsonField<DocumentBlock>,
    private val retrievedAt: JsonField<String>,
    private val type: JsonValue,
    private val url: JsonField<String>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("content")
        @ExcludeMissing
        content: JsonField<DocumentBlock> = JsonMissing.of(),
        @JsonProperty("retrieved_at")
        @ExcludeMissing
        retrievedAt: JsonField<String> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
        @JsonProperty("url") @ExcludeMissing url: JsonField<String> = JsonMissing.of(),
    ) : this(content, retrievedAt, type, url, mutableMapOf())

    fun toParam(): WebFetchBlockParam =
        WebFetchBlockParam.builder()
            .content(
                _content().map {
                    DocumentBlockParam.builder()
                        .source(
                            it._source().map {
                                it.accept(
                                    object :
                                        DocumentBlock.Source.Visitor<DocumentBlockParam.Source> {
                                        override fun visitBase64(
                                            base64: Base64PdfSource
                                        ): DocumentBlockParam.Source =
                                            DocumentBlockParam.Source.ofBase64(base64)

                                        override fun visitText(
                                            text: PlainTextSource
                                        ): DocumentBlockParam.Source =
                                            DocumentBlockParam.Source.ofBase64(
                                                Base64PdfSource.builder().data(text._data()).build()
                                            )
                                    }
                                )
                            }
                        )
                        .citations(
                            it._citations().map {
                                CitationsConfigParam.builder().enabled(it._enabled()).build()
                            }
                        )
                        .title(it._title())
                        .build()
                }
            )
            .url(_url())
            .retrievedAt(_retrievedAt())
            .build()

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun content(): DocumentBlock = content.getRequired("content")

    /**
     * ISO 8601 timestamp when the content was retrieved
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun retrievedAt(): Optional<String> = retrievedAt.getOptional("retrieved_at")

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
     * Returns the raw JSON value of [content].
     *
     * Unlike [content], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("content") @ExcludeMissing fun _content(): JsonField<DocumentBlock> = content

    /**
     * Returns the raw JSON value of [retrievedAt].
     *
     * Unlike [retrievedAt], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("retrieved_at")
    @ExcludeMissing
    fun _retrievedAt(): JsonField<String> = retrievedAt

    /**
     * Returns the raw JSON value of [url].
     *
     * Unlike [url], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("url") @ExcludeMissing fun _url(): JsonField<String> = url

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
         * Returns a mutable builder for constructing an instance of [WebFetchBlock].
         *
         * The following fields are required:
         * ```java
         * .content()
         * .retrievedAt()
         * .url()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [WebFetchBlock]. */
    class Builder internal constructor() {

        private var content: JsonField<DocumentBlock>? = null
        private var retrievedAt: JsonField<String>? = null
        private var type: JsonValue = JsonValue.from("web_fetch_result")
        private var url: JsonField<String>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(webFetchBlock: WebFetchBlock) = apply {
            content = webFetchBlock.content
            retrievedAt = webFetchBlock.retrievedAt
            type = webFetchBlock.type
            url = webFetchBlock.url
            additionalProperties = webFetchBlock.additionalProperties.toMutableMap()
        }

        fun content(content: DocumentBlock) = content(JsonField.of(content))

        /**
         * Sets [Builder.content] to an arbitrary JSON value.
         *
         * You should usually call [Builder.content] with a well-typed [DocumentBlock] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun content(content: JsonField<DocumentBlock>) = apply { this.content = content }

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
         * Returns an immutable instance of [WebFetchBlock].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .content()
         * .retrievedAt()
         * .url()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): WebFetchBlock =
            WebFetchBlock(
                checkRequired("content", content),
                checkRequired("retrievedAt", retrievedAt),
                type,
                checkRequired("url", url),
                additionalProperties.toMutableMap(),
            )
    }

    private var validated: Boolean = false

    fun validate(): WebFetchBlock = apply {
        if (validated) {
            return@apply
        }

        content().validate()
        retrievedAt()
        _type().let {
            if (it != JsonValue.from("web_fetch_result")) {
                throw AnthropicInvalidDataException("'type' is invalid, received $it")
            }
        }
        url()
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
            (if (retrievedAt.asKnown().isPresent) 1 else 0) +
            type.let { if (it == JsonValue.from("web_fetch_result")) 1 else 0 } +
            (if (url.asKnown().isPresent) 1 else 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is WebFetchBlock &&
            content == other.content &&
            retrievedAt == other.retrievedAt &&
            type == other.type &&
            url == other.url &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(content, retrievedAt, type, url, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "WebFetchBlock{content=$content, retrievedAt=$retrievedAt, type=$type, url=$url, additionalProperties=$additionalProperties}"
}
