// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.checkKnown
import com.anthropic.core.toImmutable
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.Collections
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

class BetaWebFetchTool20250910
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val name: JsonValue,
    private val type: JsonValue,
    private val allowedDomains: JsonField<List<String>>,
    private val blockedDomains: JsonField<List<String>>,
    private val cacheControl: JsonField<BetaCacheControlEphemeral>,
    private val citations: JsonField<BetaCitationsConfigParam>,
    private val maxContentTokens: JsonField<Long>,
    private val maxUses: JsonField<Long>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("name") @ExcludeMissing name: JsonValue = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
        @JsonProperty("allowed_domains")
        @ExcludeMissing
        allowedDomains: JsonField<List<String>> = JsonMissing.of(),
        @JsonProperty("blocked_domains")
        @ExcludeMissing
        blockedDomains: JsonField<List<String>> = JsonMissing.of(),
        @JsonProperty("cache_control")
        @ExcludeMissing
        cacheControl: JsonField<BetaCacheControlEphemeral> = JsonMissing.of(),
        @JsonProperty("citations")
        @ExcludeMissing
        citations: JsonField<BetaCitationsConfigParam> = JsonMissing.of(),
        @JsonProperty("max_content_tokens")
        @ExcludeMissing
        maxContentTokens: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("max_uses") @ExcludeMissing maxUses: JsonField<Long> = JsonMissing.of(),
    ) : this(
        name,
        type,
        allowedDomains,
        blockedDomains,
        cacheControl,
        citations,
        maxContentTokens,
        maxUses,
        mutableMapOf(),
    )

    /**
     * Name of the tool.
     *
     * This is how the tool will be called by the model and in `tool_use` blocks.
     *
     * Expected to always return the following:
     * ```java
     * JsonValue.from("web_fetch")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("name") @ExcludeMissing fun _name(): JsonValue = name

    /**
     * Expected to always return the following:
     * ```java
     * JsonValue.from("web_fetch_20250910")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    /**
     * List of domains to allow fetching from
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun allowedDomains(): Optional<List<String>> = allowedDomains.getOptional("allowed_domains")

    /**
     * List of domains to block fetching from
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun blockedDomains(): Optional<List<String>> = blockedDomains.getOptional("blocked_domains")

    /**
     * Create a cache control breakpoint at this content block.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun cacheControl(): Optional<BetaCacheControlEphemeral> =
        cacheControl.getOptional("cache_control")

    /**
     * Citations configuration for fetched documents. Citations are disabled by default.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun citations(): Optional<BetaCitationsConfigParam> = citations.getOptional("citations")

    /**
     * Maximum number of tokens used by including web page text content in the context. The limit is
     * approximate and does not apply to binary content such as PDFs.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun maxContentTokens(): Optional<Long> = maxContentTokens.getOptional("max_content_tokens")

    /**
     * Maximum number of times the tool can be used in the API request.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun maxUses(): Optional<Long> = maxUses.getOptional("max_uses")

    /**
     * Returns the raw JSON value of [allowedDomains].
     *
     * Unlike [allowedDomains], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("allowed_domains")
    @ExcludeMissing
    fun _allowedDomains(): JsonField<List<String>> = allowedDomains

    /**
     * Returns the raw JSON value of [blockedDomains].
     *
     * Unlike [blockedDomains], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("blocked_domains")
    @ExcludeMissing
    fun _blockedDomains(): JsonField<List<String>> = blockedDomains

    /**
     * Returns the raw JSON value of [cacheControl].
     *
     * Unlike [cacheControl], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("cache_control")
    @ExcludeMissing
    fun _cacheControl(): JsonField<BetaCacheControlEphemeral> = cacheControl

    /**
     * Returns the raw JSON value of [citations].
     *
     * Unlike [citations], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("citations")
    @ExcludeMissing
    fun _citations(): JsonField<BetaCitationsConfigParam> = citations

    /**
     * Returns the raw JSON value of [maxContentTokens].
     *
     * Unlike [maxContentTokens], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("max_content_tokens")
    @ExcludeMissing
    fun _maxContentTokens(): JsonField<Long> = maxContentTokens

    /**
     * Returns the raw JSON value of [maxUses].
     *
     * Unlike [maxUses], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("max_uses") @ExcludeMissing fun _maxUses(): JsonField<Long> = maxUses

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

        /** Returns a mutable builder for constructing an instance of [BetaWebFetchTool20250910]. */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaWebFetchTool20250910]. */
    class Builder internal constructor() {

        private var name: JsonValue = JsonValue.from("web_fetch")
        private var type: JsonValue = JsonValue.from("web_fetch_20250910")
        private var allowedDomains: JsonField<MutableList<String>>? = null
        private var blockedDomains: JsonField<MutableList<String>>? = null
        private var cacheControl: JsonField<BetaCacheControlEphemeral> = JsonMissing.of()
        private var citations: JsonField<BetaCitationsConfigParam> = JsonMissing.of()
        private var maxContentTokens: JsonField<Long> = JsonMissing.of()
        private var maxUses: JsonField<Long> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaWebFetchTool20250910: BetaWebFetchTool20250910) = apply {
            name = betaWebFetchTool20250910.name
            type = betaWebFetchTool20250910.type
            allowedDomains = betaWebFetchTool20250910.allowedDomains.map { it.toMutableList() }
            blockedDomains = betaWebFetchTool20250910.blockedDomains.map { it.toMutableList() }
            cacheControl = betaWebFetchTool20250910.cacheControl
            citations = betaWebFetchTool20250910.citations
            maxContentTokens = betaWebFetchTool20250910.maxContentTokens
            maxUses = betaWebFetchTool20250910.maxUses
            additionalProperties = betaWebFetchTool20250910.additionalProperties.toMutableMap()
        }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("web_fetch")
         * ```
         *
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun name(name: JsonValue) = apply { this.name = name }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("web_fetch_20250910")
         * ```
         *
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun type(type: JsonValue) = apply { this.type = type }

        /** List of domains to allow fetching from */
        fun allowedDomains(allowedDomains: List<String>?) =
            allowedDomains(JsonField.ofNullable(allowedDomains))

        /** Alias for calling [Builder.allowedDomains] with `allowedDomains.orElse(null)`. */
        fun allowedDomains(allowedDomains: Optional<List<String>>) =
            allowedDomains(allowedDomains.getOrNull())

        /**
         * Sets [Builder.allowedDomains] to an arbitrary JSON value.
         *
         * You should usually call [Builder.allowedDomains] with a well-typed `List<String>` value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun allowedDomains(allowedDomains: JsonField<List<String>>) = apply {
            this.allowedDomains = allowedDomains.map { it.toMutableList() }
        }

        /**
         * Adds a single [String] to [allowedDomains].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addAllowedDomain(allowedDomain: String) = apply {
            allowedDomains =
                (allowedDomains ?: JsonField.of(mutableListOf())).also {
                    checkKnown("allowedDomains", it).add(allowedDomain)
                }
        }

        /** List of domains to block fetching from */
        fun blockedDomains(blockedDomains: List<String>?) =
            blockedDomains(JsonField.ofNullable(blockedDomains))

        /** Alias for calling [Builder.blockedDomains] with `blockedDomains.orElse(null)`. */
        fun blockedDomains(blockedDomains: Optional<List<String>>) =
            blockedDomains(blockedDomains.getOrNull())

        /**
         * Sets [Builder.blockedDomains] to an arbitrary JSON value.
         *
         * You should usually call [Builder.blockedDomains] with a well-typed `List<String>` value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun blockedDomains(blockedDomains: JsonField<List<String>>) = apply {
            this.blockedDomains = blockedDomains.map { it.toMutableList() }
        }

        /**
         * Adds a single [String] to [blockedDomains].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addBlockedDomain(blockedDomain: String) = apply {
            blockedDomains =
                (blockedDomains ?: JsonField.of(mutableListOf())).also {
                    checkKnown("blockedDomains", it).add(blockedDomain)
                }
        }

        /** Create a cache control breakpoint at this content block. */
        fun cacheControl(cacheControl: BetaCacheControlEphemeral?) =
            cacheControl(JsonField.ofNullable(cacheControl))

        /** Alias for calling [Builder.cacheControl] with `cacheControl.orElse(null)`. */
        fun cacheControl(cacheControl: Optional<BetaCacheControlEphemeral>) =
            cacheControl(cacheControl.getOrNull())

        /**
         * Sets [Builder.cacheControl] to an arbitrary JSON value.
         *
         * You should usually call [Builder.cacheControl] with a well-typed
         * [BetaCacheControlEphemeral] value instead. This method is primarily for setting the field
         * to an undocumented or not yet supported value.
         */
        fun cacheControl(cacheControl: JsonField<BetaCacheControlEphemeral>) = apply {
            this.cacheControl = cacheControl
        }

        /** Citations configuration for fetched documents. Citations are disabled by default. */
        fun citations(citations: BetaCitationsConfigParam?) =
            citations(JsonField.ofNullable(citations))

        /** Alias for calling [Builder.citations] with `citations.orElse(null)`. */
        fun citations(citations: Optional<BetaCitationsConfigParam>) =
            citations(citations.getOrNull())

        /**
         * Sets [Builder.citations] to an arbitrary JSON value.
         *
         * You should usually call [Builder.citations] with a well-typed [BetaCitationsConfigParam]
         * value instead. This method is primarily for setting the field to an undocumented or not
         * yet supported value.
         */
        fun citations(citations: JsonField<BetaCitationsConfigParam>) = apply {
            this.citations = citations
        }

        /**
         * Maximum number of tokens used by including web page text content in the context. The
         * limit is approximate and does not apply to binary content such as PDFs.
         */
        fun maxContentTokens(maxContentTokens: Long?) =
            maxContentTokens(JsonField.ofNullable(maxContentTokens))

        /**
         * Alias for [Builder.maxContentTokens].
         *
         * This unboxed primitive overload exists for backwards compatibility.
         */
        fun maxContentTokens(maxContentTokens: Long) = maxContentTokens(maxContentTokens as Long?)

        /** Alias for calling [Builder.maxContentTokens] with `maxContentTokens.orElse(null)`. */
        fun maxContentTokens(maxContentTokens: Optional<Long>) =
            maxContentTokens(maxContentTokens.getOrNull())

        /**
         * Sets [Builder.maxContentTokens] to an arbitrary JSON value.
         *
         * You should usually call [Builder.maxContentTokens] with a well-typed [Long] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun maxContentTokens(maxContentTokens: JsonField<Long>) = apply {
            this.maxContentTokens = maxContentTokens
        }

        /** Maximum number of times the tool can be used in the API request. */
        fun maxUses(maxUses: Long?) = maxUses(JsonField.ofNullable(maxUses))

        /**
         * Alias for [Builder.maxUses].
         *
         * This unboxed primitive overload exists for backwards compatibility.
         */
        fun maxUses(maxUses: Long) = maxUses(maxUses as Long?)

        /** Alias for calling [Builder.maxUses] with `maxUses.orElse(null)`. */
        fun maxUses(maxUses: Optional<Long>) = maxUses(maxUses.getOrNull())

        /**
         * Sets [Builder.maxUses] to an arbitrary JSON value.
         *
         * You should usually call [Builder.maxUses] with a well-typed [Long] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun maxUses(maxUses: JsonField<Long>) = apply { this.maxUses = maxUses }

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
         * Returns an immutable instance of [BetaWebFetchTool20250910].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         */
        fun build(): BetaWebFetchTool20250910 =
            BetaWebFetchTool20250910(
                name,
                type,
                (allowedDomains ?: JsonMissing.of()).map { it.toImmutable() },
                (blockedDomains ?: JsonMissing.of()).map { it.toImmutable() },
                cacheControl,
                citations,
                maxContentTokens,
                maxUses,
                additionalProperties.toMutableMap(),
            )
    }

    private var validated: Boolean = false

    fun validate(): BetaWebFetchTool20250910 = apply {
        if (validated) {
            return@apply
        }

        _name().let {
            if (it != JsonValue.from("web_fetch")) {
                throw AnthropicInvalidDataException("'name' is invalid, received $it")
            }
        }
        _type().let {
            if (it != JsonValue.from("web_fetch_20250910")) {
                throw AnthropicInvalidDataException("'type' is invalid, received $it")
            }
        }
        allowedDomains()
        blockedDomains()
        cacheControl().ifPresent { it.validate() }
        citations().ifPresent { it.validate() }
        maxContentTokens()
        maxUses()
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
        name.let { if (it == JsonValue.from("web_fetch")) 1 else 0 } +
            type.let { if (it == JsonValue.from("web_fetch_20250910")) 1 else 0 } +
            (allowedDomains.asKnown().getOrNull()?.size ?: 0) +
            (blockedDomains.asKnown().getOrNull()?.size ?: 0) +
            (cacheControl.asKnown().getOrNull()?.validity() ?: 0) +
            (citations.asKnown().getOrNull()?.validity() ?: 0) +
            (if (maxContentTokens.asKnown().isPresent) 1 else 0) +
            (if (maxUses.asKnown().isPresent) 1 else 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaWebFetchTool20250910 &&
            name == other.name &&
            type == other.type &&
            allowedDomains == other.allowedDomains &&
            blockedDomains == other.blockedDomains &&
            cacheControl == other.cacheControl &&
            citations == other.citations &&
            maxContentTokens == other.maxContentTokens &&
            maxUses == other.maxUses &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(
            name,
            type,
            allowedDomains,
            blockedDomains,
            cacheControl,
            citations,
            maxContentTokens,
            maxUses,
            additionalProperties,
        )
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaWebFetchTool20250910{name=$name, type=$type, allowedDomains=$allowedDomains, blockedDomains=$blockedDomains, cacheControl=$cacheControl, citations=$citations, maxContentTokens=$maxContentTokens, maxUses=$maxUses, additionalProperties=$additionalProperties}"
}
