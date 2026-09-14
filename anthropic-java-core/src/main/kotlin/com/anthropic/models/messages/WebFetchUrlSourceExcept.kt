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

/** The tool filter variant under which every result but the named tools' contributes. */
class WebFetchUrlSourceExcept
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val tools: JsonField<List<WebFetchUrlSourceToolReference>>,
    private val type: JsonValue,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("tools")
        @ExcludeMissing
        tools: JsonField<List<WebFetchUrlSourceToolReference>> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
    ) : this(tools, type, mutableMapOf())

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun tools(): List<WebFetchUrlSourceToolReference> = tools.getRequired("tools")

    /**
     * Expected to always return the following:
     * ```java
     * JsonValue.from("except")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    /**
     * Returns the raw JSON value of [tools].
     *
     * Unlike [tools], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("tools")
    @ExcludeMissing
    fun _tools(): JsonField<List<WebFetchUrlSourceToolReference>> = tools

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
         * Returns a mutable builder for constructing an instance of [WebFetchUrlSourceExcept].
         *
         * The following fields are required:
         * ```java
         * .tools()
         * ```
         */
        @JvmStatic fun builder() = Builder()

        /**
         * Returns an immutable instance of [WebFetchUrlSourceExcept] with the required [tools] set
         * to the given value.
         */
        @JvmStatic
        fun of(tools: List<WebFetchUrlSourceToolReference>) = builder().tools(tools).build()
    }

    /** A builder for [WebFetchUrlSourceExcept]. */
    class Builder internal constructor() {

        private var tools: JsonField<MutableList<WebFetchUrlSourceToolReference>>? = null
        private var type: JsonValue = JsonValue.from("except")
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(webFetchUrlSourceExcept: WebFetchUrlSourceExcept) = apply {
            tools =
                webFetchUrlSourceExcept.tools
                    .map { it.toMutableList() }
                    .takeUnless { it.isMissing() }
            type = webFetchUrlSourceExcept.type
            additionalProperties = webFetchUrlSourceExcept.additionalProperties.toMutableMap()
        }

        fun tools(tools: List<WebFetchUrlSourceToolReference>) = tools(JsonField.of(tools))

        /**
         * Sets [Builder.tools] to an arbitrary JSON value.
         *
         * You should usually call [Builder.tools] with a well-typed
         * `List<WebFetchUrlSourceToolReference>` value instead. This method is primarily for
         * setting the field to an undocumented or not yet supported value.
         */
        fun tools(tools: JsonField<List<WebFetchUrlSourceToolReference>>) = apply {
            this.tools = tools.map { it.toMutableList() }
        }

        /**
         * Adds a single [WebFetchUrlSourceToolReference] to [tools].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addTool(tool: WebFetchUrlSourceToolReference) = apply {
            tools =
                (tools ?: JsonField.of(mutableListOf())).also { checkKnown("tools", it).add(tool) }
        }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("except")
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
         * Returns an immutable instance of [WebFetchUrlSourceExcept].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .tools()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): WebFetchUrlSourceExcept =
            WebFetchUrlSourceExcept(
                checkRequired("tools", tools).map { it.toImmutable() },
                type,
                additionalProperties.toMutableMap(),
            )
    }

    private var validated: Boolean = false

    /**
     * Validates that the types of all values in this object match their expected types recursively.
     *
     * This method is _not_ forwards compatible with new types from the API for existing fields.
     *
     * @throws AnthropicInvalidDataException if any value type in this object doesn't match its
     *   expected type.
     */
    fun validate(): WebFetchUrlSourceExcept = apply {
        if (validated) {
            return@apply
        }

        tools().forEach { it.validate() }
        _type().let {
            if (it != JsonValue.from("except")) {
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
        (tools.asKnown().getOrNull()?.sumOf { it.validity().toInt() } ?: 0) +
            type.let { if (it == JsonValue.from("except")) 1 else 0 }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is WebFetchUrlSourceExcept &&
            tools == other.tools &&
            type == other.type &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy { Objects.hash(tools, type, additionalProperties) }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "WebFetchUrlSourceExcept{tools=$tools, type=$type, additionalProperties=$additionalProperties}"
}
