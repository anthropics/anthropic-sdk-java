package com.anthropic.models.beta.agents

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

/** Every tool's results contribute URLs that may be fetched, except the named tools' results. */
class BetaManagedAgentsWebFetchUrlSourceExcept
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val tools: JsonField<List<BetaManagedAgentsWebFetchUrlSourceToolReference>>,
    private val type: JsonValue,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("tools")
        @ExcludeMissing
        tools: JsonField<List<BetaManagedAgentsWebFetchUrlSourceToolReference>> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
    ) : this(tools, type, mutableMapOf())

    /**
     * The tools whose results do not contribute. Between 1 and 128 entries, each with a different
     * name. An empty list is rejected; use "all" to leave out no tool's results.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun tools(): List<BetaManagedAgentsWebFetchUrlSourceToolReference> = tools.getRequired("tools")

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
    fun _tools(): JsonField<List<BetaManagedAgentsWebFetchUrlSourceToolReference>> = tools

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
         * [BetaManagedAgentsWebFetchUrlSourceExcept].
         *
         * The following fields are required:
         * ```java
         * .tools()
         * ```
         */
        @JvmStatic fun builder() = Builder()

        /**
         * Returns an immutable instance of [BetaManagedAgentsWebFetchUrlSourceExcept] with the
         * required [tools] set to the given value.
         */
        @JvmStatic
        fun of(tools: List<BetaManagedAgentsWebFetchUrlSourceToolReference>) =
            builder().tools(tools).build()
    }

    /** A builder for [BetaManagedAgentsWebFetchUrlSourceExcept]. */
    class Builder internal constructor() {

        private var tools:
            JsonField<MutableList<BetaManagedAgentsWebFetchUrlSourceToolReference>>? =
            null
        private var type: JsonValue = JsonValue.from("except")
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(
            betaManagedAgentsWebFetchUrlSourceExcept: BetaManagedAgentsWebFetchUrlSourceExcept
        ) = apply {
            tools =
                betaManagedAgentsWebFetchUrlSourceExcept.tools
                    .map { it.toMutableList() }
                    .takeUnless { it.isMissing() }
            type = betaManagedAgentsWebFetchUrlSourceExcept.type
            additionalProperties =
                betaManagedAgentsWebFetchUrlSourceExcept.additionalProperties.toMutableMap()
        }

        /**
         * The tools whose results do not contribute. Between 1 and 128 entries, each with a
         * different name. An empty list is rejected; use "all" to leave out no tool's results.
         */
        fun tools(tools: List<BetaManagedAgentsWebFetchUrlSourceToolReference>) =
            tools(JsonField.of(tools))

        /**
         * Sets [Builder.tools] to an arbitrary JSON value.
         *
         * You should usually call [Builder.tools] with a well-typed
         * `List<BetaManagedAgentsWebFetchUrlSourceToolReference>` value instead. This method is
         * primarily for setting the field to an undocumented or not yet supported value.
         */
        fun tools(tools: JsonField<List<BetaManagedAgentsWebFetchUrlSourceToolReference>>) = apply {
            this.tools = tools.map { it.toMutableList() }
        }

        /**
         * Adds a single [BetaManagedAgentsWebFetchUrlSourceToolReference] to [tools].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addTool(tool: BetaManagedAgentsWebFetchUrlSourceToolReference) = apply {
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
         * Returns an immutable instance of [BetaManagedAgentsWebFetchUrlSourceExcept].
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
        fun build(): BetaManagedAgentsWebFetchUrlSourceExcept =
            BetaManagedAgentsWebFetchUrlSourceExcept(
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
    fun validate(): BetaManagedAgentsWebFetchUrlSourceExcept = apply {
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

        return other is BetaManagedAgentsWebFetchUrlSourceExcept &&
            tools == other.tools &&
            type == other.type &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy { Objects.hash(tools, type, additionalProperties) }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaManagedAgentsWebFetchUrlSourceExcept{tools=$tools, type=$type, additionalProperties=$additionalProperties}"
}
