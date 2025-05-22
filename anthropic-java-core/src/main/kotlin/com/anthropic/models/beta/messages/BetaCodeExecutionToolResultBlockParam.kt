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

class BetaCodeExecutionToolResultBlockParam
private constructor(
    private val content: JsonField<BetaCodeExecutionToolResultBlockParamContent>,
    private val toolUseId: JsonField<String>,
    private val type: JsonValue,
    private val cacheControl: JsonField<BetaCacheControlEphemeral>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("content")
        @ExcludeMissing
        content: JsonField<BetaCodeExecutionToolResultBlockParamContent> = JsonMissing.of(),
        @JsonProperty("tool_use_id")
        @ExcludeMissing
        toolUseId: JsonField<String> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
        @JsonProperty("cache_control")
        @ExcludeMissing
        cacheControl: JsonField<BetaCacheControlEphemeral> = JsonMissing.of(),
    ) : this(content, toolUseId, type, cacheControl, mutableMapOf())

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun content(): BetaCodeExecutionToolResultBlockParamContent = content.getRequired("content")

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun toolUseId(): String = toolUseId.getRequired("tool_use_id")

    /**
     * Expected to always return the following:
     * ```java
     * JsonValue.from("code_execution_tool_result")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    /**
     * Create a cache control breakpoint at this content block.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun cacheControl(): Optional<BetaCacheControlEphemeral> =
        cacheControl.getOptional("cache_control")

    /**
     * Returns the raw JSON value of [content].
     *
     * Unlike [content], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("content")
    @ExcludeMissing
    fun _content(): JsonField<BetaCodeExecutionToolResultBlockParamContent> = content

    /**
     * Returns the raw JSON value of [toolUseId].
     *
     * Unlike [toolUseId], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("tool_use_id") @ExcludeMissing fun _toolUseId(): JsonField<String> = toolUseId

    /**
     * Returns the raw JSON value of [cacheControl].
     *
     * Unlike [cacheControl], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("cache_control")
    @ExcludeMissing
    fun _cacheControl(): JsonField<BetaCacheControlEphemeral> = cacheControl

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
         * Returns a mutable builder for constructing an instance of
         * [BetaCodeExecutionToolResultBlockParam].
         *
         * The following fields are required:
         * ```java
         * .content()
         * .toolUseId()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaCodeExecutionToolResultBlockParam]. */
    class Builder internal constructor() {

        private var content: JsonField<BetaCodeExecutionToolResultBlockParamContent>? = null
        private var toolUseId: JsonField<String>? = null
        private var type: JsonValue = JsonValue.from("code_execution_tool_result")
        private var cacheControl: JsonField<BetaCacheControlEphemeral> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(
            betaCodeExecutionToolResultBlockParam: BetaCodeExecutionToolResultBlockParam
        ) = apply {
            content = betaCodeExecutionToolResultBlockParam.content
            toolUseId = betaCodeExecutionToolResultBlockParam.toolUseId
            type = betaCodeExecutionToolResultBlockParam.type
            cacheControl = betaCodeExecutionToolResultBlockParam.cacheControl
            additionalProperties =
                betaCodeExecutionToolResultBlockParam.additionalProperties.toMutableMap()
        }

        fun content(content: BetaCodeExecutionToolResultBlockParamContent) =
            content(JsonField.of(content))

        /**
         * Sets [Builder.content] to an arbitrary JSON value.
         *
         * You should usually call [Builder.content] with a well-typed
         * [BetaCodeExecutionToolResultBlockParamContent] value instead. This method is primarily
         * for setting the field to an undocumented or not yet supported value.
         */
        fun content(content: JsonField<BetaCodeExecutionToolResultBlockParamContent>) = apply {
            this.content = content
        }

        /**
         * Alias for calling [content] with
         * `BetaCodeExecutionToolResultBlockParamContent.ofErrorParam(errorParam)`.
         */
        fun content(errorParam: BetaCodeExecutionToolResultErrorParam) =
            content(BetaCodeExecutionToolResultBlockParamContent.ofErrorParam(errorParam))

        /** Alias for calling [content] with `errorParam.toParam()`. */
        fun content(errorParam: BetaCodeExecutionToolResultError) = content(errorParam.toParam())

        /**
         * Alias for calling [content] with
         * `BetaCodeExecutionToolResultBlockParamContent.ofResultBlockParam(resultBlockParam)`.
         */
        fun content(resultBlockParam: BetaCodeExecutionResultBlockParam) =
            content(
                BetaCodeExecutionToolResultBlockParamContent.ofResultBlockParam(resultBlockParam)
            )

        /** Alias for calling [content] with `resultBlockParam.toParam()`. */
        fun content(resultBlockParam: BetaCodeExecutionResultBlock) =
            content(resultBlockParam.toParam())

        fun toolUseId(toolUseId: String) = toolUseId(JsonField.of(toolUseId))

        /**
         * Sets [Builder.toolUseId] to an arbitrary JSON value.
         *
         * You should usually call [Builder.toolUseId] with a well-typed [String] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun toolUseId(toolUseId: JsonField<String>) = apply { this.toolUseId = toolUseId }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("code_execution_tool_result")
         * ```
         *
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun type(type: JsonValue) = apply { this.type = type }

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
         * Returns an immutable instance of [BetaCodeExecutionToolResultBlockParam].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .content()
         * .toolUseId()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaCodeExecutionToolResultBlockParam =
            BetaCodeExecutionToolResultBlockParam(
                checkRequired("content", content),
                checkRequired("toolUseId", toolUseId),
                type,
                cacheControl,
                additionalProperties.toMutableMap(),
            )
    }

    private var validated: Boolean = false

    fun validate(): BetaCodeExecutionToolResultBlockParam = apply {
        if (validated) {
            return@apply
        }

        content().validate()
        toolUseId()
        _type().let {
            if (it != JsonValue.from("code_execution_tool_result")) {
                throw AnthropicInvalidDataException("'type' is invalid, received $it")
            }
        }
        cacheControl().ifPresent { it.validate() }
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
            (if (toolUseId.asKnown().isPresent) 1 else 0) +
            type.let { if (it == JsonValue.from("code_execution_tool_result")) 1 else 0 } +
            (cacheControl.asKnown().getOrNull()?.validity() ?: 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return /* spotless:off */ other is BetaCodeExecutionToolResultBlockParam && content == other.content && toolUseId == other.toolUseId && type == other.type && cacheControl == other.cacheControl && additionalProperties == other.additionalProperties /* spotless:on */
    }

    /* spotless:off */
    private val hashCode: Int by lazy { Objects.hash(content, toolUseId, type, cacheControl, additionalProperties) }
    /* spotless:on */

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaCodeExecutionToolResultBlockParam{content=$content, toolUseId=$toolUseId, type=$type, cacheControl=$cacheControl, additionalProperties=$additionalProperties}"
}
