package com.anthropic.models.beta.organization.pluginmarketplaces

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

class BetaPluginMarketplaceValidationPluginWarnings
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val name: JsonField<String>,
    private val warnings: JsonField<List<BetaPluginMarketplaceValidationPluginWarning>>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("name") @ExcludeMissing name: JsonField<String> = JsonMissing.of(),
        @JsonProperty("warnings")
        @ExcludeMissing
        warnings: JsonField<List<BetaPluginMarketplaceValidationPluginWarning>> = JsonMissing.of(),
    ) : this(name, warnings, mutableMapOf())

    /**
     * The plugin's name, as its entry in marketplace.json declares it.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun name(): String = name.getRequired("name")

    /**
     * The parts of the plugin a synchronization would leave out.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun warnings(): List<BetaPluginMarketplaceValidationPluginWarning> =
        warnings.getRequired("warnings")

    /**
     * Returns the raw JSON value of [name].
     *
     * Unlike [name], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("name") @ExcludeMissing fun _name(): JsonField<String> = name

    /**
     * Returns the raw JSON value of [warnings].
     *
     * Unlike [warnings], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("warnings")
    @ExcludeMissing
    fun _warnings(): JsonField<List<BetaPluginMarketplaceValidationPluginWarning>> = warnings

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
         * [BetaPluginMarketplaceValidationPluginWarnings].
         *
         * The following fields are required:
         * ```java
         * .name()
         * .warnings()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaPluginMarketplaceValidationPluginWarnings]. */
    class Builder internal constructor() {

        private var name: JsonField<String>? = null
        private var warnings:
            JsonField<MutableList<BetaPluginMarketplaceValidationPluginWarning>>? =
            null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(
            betaPluginMarketplaceValidationPluginWarnings:
                BetaPluginMarketplaceValidationPluginWarnings
        ) = apply {
            name = betaPluginMarketplaceValidationPluginWarnings.name
            warnings =
                betaPluginMarketplaceValidationPluginWarnings.warnings
                    .map { it.toMutableList() }
                    .takeUnless { it.isMissing() }
            additionalProperties =
                betaPluginMarketplaceValidationPluginWarnings.additionalProperties.toMutableMap()
        }

        /** The plugin's name, as its entry in marketplace.json declares it. */
        fun name(name: String) = name(JsonField.of(name))

        /**
         * Sets [Builder.name] to an arbitrary JSON value.
         *
         * You should usually call [Builder.name] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun name(name: JsonField<String>) = apply { this.name = name }

        /** The parts of the plugin a synchronization would leave out. */
        fun warnings(warnings: List<BetaPluginMarketplaceValidationPluginWarning>) =
            warnings(JsonField.of(warnings))

        /**
         * Sets [Builder.warnings] to an arbitrary JSON value.
         *
         * You should usually call [Builder.warnings] with a well-typed
         * `List<BetaPluginMarketplaceValidationPluginWarning>` value instead. This method is
         * primarily for setting the field to an undocumented or not yet supported value.
         */
        fun warnings(warnings: JsonField<List<BetaPluginMarketplaceValidationPluginWarning>>) =
            apply {
                this.warnings = warnings.map { it.toMutableList() }
            }

        /**
         * Adds a single [BetaPluginMarketplaceValidationPluginWarning] to [warnings].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addWarning(warning: BetaPluginMarketplaceValidationPluginWarning) = apply {
            warnings =
                (warnings ?: JsonField.of(mutableListOf())).also {
                    checkKnown("warnings", it).add(warning)
                }
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
         * Returns an immutable instance of [BetaPluginMarketplaceValidationPluginWarnings].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .name()
         * .warnings()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaPluginMarketplaceValidationPluginWarnings =
            BetaPluginMarketplaceValidationPluginWarnings(
                checkRequired("name", name),
                checkRequired("warnings", warnings).map { it.toImmutable() },
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
    fun validate(): BetaPluginMarketplaceValidationPluginWarnings = apply {
        if (validated) {
            return@apply
        }

        name()
        warnings().forEach { it.validate() }
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
        (if (name.asKnown().isPresent) 1 else 0) +
            (warnings.asKnown().getOrNull()?.sumOf { it.validity().toInt() } ?: 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaPluginMarketplaceValidationPluginWarnings &&
            name == other.name &&
            warnings == other.warnings &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy { Objects.hash(name, warnings, additionalProperties) }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaPluginMarketplaceValidationPluginWarnings{name=$name, warnings=$warnings, additionalProperties=$additionalProperties}"
}
