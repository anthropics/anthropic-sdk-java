// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

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

class BetaWebSearchResultBlock
private constructor(
    private val encryptedContent: JsonField<String>,
    private val pageAge: JsonField<String>,
    private val title: JsonField<String>,
    private val type: JsonValue,
    private val url: JsonField<String>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("encrypted_content")
        @ExcludeMissing
        encryptedContent: JsonField<String> = JsonMissing.of(),
        @JsonProperty("page_age") @ExcludeMissing pageAge: JsonField<String> = JsonMissing.of(),
        @JsonProperty("title") @ExcludeMissing title: JsonField<String> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
        @JsonProperty("url") @ExcludeMissing url: JsonField<String> = JsonMissing.of(),
    ) : this(encryptedContent, pageAge, title, type, url, mutableMapOf())

    fun toParam(): BetaWebSearchResultBlockParam =
        BetaWebSearchResultBlockParam.builder()
            .encryptedContent(_encryptedContent())
            .title(_title())
            .url(_url())
            .pageAge(_pageAge())
            .build()

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun encryptedContent(): String = encryptedContent.getRequired("encrypted_content")

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun pageAge(): Optional<String> = pageAge.getOptional("page_age")

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun title(): String = title.getRequired("title")

    /**
     * Expected to always return the following:
     * ```java
     * JsonValue.from("web_search_result")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun url(): String = url.getRequired("url")

    /**
     * Returns the raw JSON value of [encryptedContent].
     *
     * Unlike [encryptedContent], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("encrypted_content")
    @ExcludeMissing
    fun _encryptedContent(): JsonField<String> = encryptedContent

    /**
     * Returns the raw JSON value of [pageAge].
     *
     * Unlike [pageAge], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("page_age") @ExcludeMissing fun _pageAge(): JsonField<String> = pageAge

    /**
     * Returns the raw JSON value of [title].
     *
     * Unlike [title], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("title") @ExcludeMissing fun _title(): JsonField<String> = title

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
         * Returns a mutable builder for constructing an instance of [BetaWebSearchResultBlock].
         *
         * The following fields are required:
         * ```java
         * .encryptedContent()
         * .pageAge()
         * .title()
         * .url()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaWebSearchResultBlock]. */
    class Builder internal constructor() {

        private var encryptedContent: JsonField<String>? = null
        private var pageAge: JsonField<String>? = null
        private var title: JsonField<String>? = null
        private var type: JsonValue = JsonValue.from("web_search_result")
        private var url: JsonField<String>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaWebSearchResultBlock: BetaWebSearchResultBlock) = apply {
            encryptedContent = betaWebSearchResultBlock.encryptedContent
            pageAge = betaWebSearchResultBlock.pageAge
            title = betaWebSearchResultBlock.title
            type = betaWebSearchResultBlock.type
            url = betaWebSearchResultBlock.url
            additionalProperties = betaWebSearchResultBlock.additionalProperties.toMutableMap()
        }

        fun encryptedContent(encryptedContent: String) =
            encryptedContent(JsonField.of(encryptedContent))

        /**
         * Sets [Builder.encryptedContent] to an arbitrary JSON value.
         *
         * You should usually call [Builder.encryptedContent] with a well-typed [String] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun encryptedContent(encryptedContent: JsonField<String>) = apply {
            this.encryptedContent = encryptedContent
        }

        fun pageAge(pageAge: String?) = pageAge(JsonField.ofNullable(pageAge))

        /** Alias for calling [Builder.pageAge] with `pageAge.orElse(null)`. */
        fun pageAge(pageAge: Optional<String>) = pageAge(pageAge.getOrNull())

        /**
         * Sets [Builder.pageAge] to an arbitrary JSON value.
         *
         * You should usually call [Builder.pageAge] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun pageAge(pageAge: JsonField<String>) = apply { this.pageAge = pageAge }

        fun title(title: String) = title(JsonField.of(title))

        /**
         * Sets [Builder.title] to an arbitrary JSON value.
         *
         * You should usually call [Builder.title] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun title(title: JsonField<String>) = apply { this.title = title }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("web_search_result")
         * ```
         *
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun type(type: JsonValue) = apply { this.type = type }

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
            putAlladditional_properties(additionalProperties)
        }

        fun putAdditionalProperty(key: String, value: JsonValue) = apply {
            additionalProperties.put(key, value)
        }

        fun putAlladditional_properties(additionalProperties: Map<String, JsonValue>) = apply {
            this.additionalProperties.putAll(additionalProperties)
        }

        fun removeAdditionalProperty(key: String) = apply { additionalProperties.remove(key) }

        fun removeAlladditional_properties(keys: Set<String>) = apply {
            keys.forEach(::removeAdditionalProperty)
        }

        /**
         * Returns an immutable instance of [BetaWebSearchResultBlock].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .encryptedContent()
         * .pageAge()
         * .title()
         * .url()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaWebSearchResultBlock =
            BetaWebSearchResultBlock(
                checkRequired("encryptedContent", encryptedContent),
                checkRequired("pageAge", pageAge),
                checkRequired("title", title),
                type,
                checkRequired("url", url),
                additionalProperties.toMutableMap(),
            )
    }

    private var validated: Boolean = false

    fun validate(): BetaWebSearchResultBlock = apply {
        if (validated) {
            return@apply
        }

        encryptedContent()
        pageAge()
        title()
        _type().let {
            if (it != JsonValue.from("web_search_result")) {
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
        (if (encryptedContent.asKnown().isPresent) 1 else 0) +
            (if (pageAge.asKnown().isPresent) 1 else 0) +
            (if (title.asKnown().isPresent) 1 else 0) +
            type.let { if (it == JsonValue.from("web_search_result")) 1 else 0 } +
            (if (url.asKnown().isPresent) 1 else 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaWebSearchResultBlock &&
            encryptedContent == other.encryptedContent &&
            pageAge == other.pageAge &&
            title == other.title &&
            type == other.type &&
            url == other.url &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(encryptedContent, pageAge, title, type, url, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaWebSearchResultBlock{encryptedContent=$encryptedContent, pageAge=$pageAge, title=$title, type=$type, url=$url, additionalProperties=$additionalProperties}"
}
