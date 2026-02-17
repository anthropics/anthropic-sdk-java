// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.checkKnown
import com.anthropic.core.checkRequired
import com.anthropic.core.toImmutable
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.Collections
import java.util.Objects
import kotlin.jvm.optionals.getOrNull

class ToolSearchToolSearchResultBlockParam
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val toolReferences: JsonField<List<ToolReferenceBlockParam>>,
    private val type: JsonValue,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("tool_references")
        @ExcludeMissing
        toolReferences: JsonField<List<ToolReferenceBlockParam>> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
    ) : this(toolReferences, type, mutableMapOf())

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun toolReferences(): List<ToolReferenceBlockParam> =
        toolReferences.getRequired("tool_references")

    /**
     * Expected to always return the following:
     * ```java
     * JsonValue.from("tool_search_tool_search_result")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    /**
     * Returns the raw JSON value of [toolReferences].
     *
     * Unlike [toolReferences], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("tool_references")
    @ExcludeMissing
    fun _toolReferences(): JsonField<List<ToolReferenceBlockParam>> = toolReferences

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
         * [ToolSearchToolSearchResultBlockParam].
         *
         * The following fields are required:
         * ```java
         * .toolReferences()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [ToolSearchToolSearchResultBlockParam]. */
    class Builder internal constructor() {

        private var toolReferences: JsonField<MutableList<ToolReferenceBlockParam>>? = null
        private var type: JsonValue = JsonValue.from("tool_search_tool_search_result")
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(
            toolSearchToolSearchResultBlockParam: ToolSearchToolSearchResultBlockParam
        ) = apply {
            toolReferences =
                toolSearchToolSearchResultBlockParam.toolReferences.map { it.toMutableList() }
            type = toolSearchToolSearchResultBlockParam.type
            additionalProperties =
                toolSearchToolSearchResultBlockParam.additionalProperties.toMutableMap()
        }

        fun toolReferences(toolReferences: List<ToolReferenceBlockParam>) =
            toolReferences(JsonField.of(toolReferences))

        /**
         * Sets [Builder.toolReferences] to an arbitrary JSON value.
         *
         * You should usually call [Builder.toolReferences] with a well-typed
         * `List<ToolReferenceBlockParam>` value instead. This method is primarily for setting the
         * field to an undocumented or not yet supported value.
         */
        fun toolReferences(toolReferences: JsonField<List<ToolReferenceBlockParam>>) = apply {
            this.toolReferences = toolReferences.map { it.toMutableList() }
        }

        /**
         * Adds a single [ToolReferenceBlockParam] to [toolReferences].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addToolReference(toolReference: ToolReferenceBlockParam) = apply {
            toolReferences =
                (toolReferences ?: JsonField.of(mutableListOf())).also {
                    checkKnown("toolReferences", it).add(toolReference)
                }
        }

        /** Alias for calling [addToolReference] with `toolReference.toParam()`. */
        fun addToolReference(toolReference: ToolReferenceBlock) =
            addToolReference(toolReference.toParam())

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("tool_search_tool_search_result")
         * ```
         *
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun type(type: JsonValue) = apply { this.type = type }

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
         * Returns an immutable instance of [ToolSearchToolSearchResultBlockParam].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .toolReferences()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): ToolSearchToolSearchResultBlockParam =
            ToolSearchToolSearchResultBlockParam(
                checkRequired("toolReferences", toolReferences).map { it.toImmutable() },
                type,
                additionalProperties.toMutableMap(),
            )
    }

    private var validated: Boolean = false

    fun validate(): ToolSearchToolSearchResultBlockParam = apply {
        if (validated) {
            return@apply
        }

        toolReferences().forEach { it.validate() }
        _type().let {
            if (it != JsonValue.from("tool_search_tool_search_result")) {
                throw AnthropicInvalidDataException("'type' is invalid, received $it")
            }
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
     * Returns a score indicating how many valid values are contained in this object recursively.
     *
     * Used for best match union deserialization.
     */
    @JvmSynthetic
    internal fun validity(): Int =
        (toolReferences.asKnown().getOrNull()?.sumOf { it.validity().toInt() } ?: 0) +
            type.let { if (it == JsonValue.from("tool_search_tool_search_result")) 1 else 0 }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is ToolSearchToolSearchResultBlockParam &&
            toolReferences == other.toolReferences &&
            type == other.type &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy { Objects.hash(toolReferences, type, additionalProperties) }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "ToolSearchToolSearchResultBlockParam{toolReferences=$toolReferences, type=$type, additionalProperties=$additionalProperties}"
}
