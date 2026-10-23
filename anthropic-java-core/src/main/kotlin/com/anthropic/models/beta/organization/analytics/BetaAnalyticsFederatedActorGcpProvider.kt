package com.anthropic.models.beta.organization.analytics

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

class BetaAnalyticsFederatedActorGcpProvider
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val projectNumber: JsonField<String>,
    private val type: JsonValue,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("project_number")
        @ExcludeMissing
        projectNumber: JsonField<String> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
    ) : this(projectNumber, type, mutableMapOf())

    /**
     * The Google Cloud project the organization is bound to.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun projectNumber(): String = projectNumber.getRequired("project_number")

    /**
     * Expected to always return the following:
     * ```java
     * JsonValue.from("gcp")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    /**
     * Returns the raw JSON value of [projectNumber].
     *
     * Unlike [projectNumber], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("project_number")
    @ExcludeMissing
    fun _projectNumber(): JsonField<String> = projectNumber

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
         * [BetaAnalyticsFederatedActorGcpProvider].
         *
         * The following fields are required:
         * ```java
         * .projectNumber()
         * ```
         */
        @JvmStatic fun builder() = Builder()

        /**
         * Returns an immutable instance of [BetaAnalyticsFederatedActorGcpProvider] with the
         * required [projectNumber] set to the given value.
         */
        @JvmStatic fun of(projectNumber: String) = builder().projectNumber(projectNumber).build()
    }

    /** A builder for [BetaAnalyticsFederatedActorGcpProvider]. */
    class Builder internal constructor() {

        private var projectNumber: JsonField<String>? = null
        private var type: JsonValue = JsonValue.from("gcp")
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(
            betaAnalyticsFederatedActorGcpProvider: BetaAnalyticsFederatedActorGcpProvider
        ) = apply {
            projectNumber = betaAnalyticsFederatedActorGcpProvider.projectNumber
            type = betaAnalyticsFederatedActorGcpProvider.type
            additionalProperties =
                betaAnalyticsFederatedActorGcpProvider.additionalProperties.toMutableMap()
        }

        /** The Google Cloud project the organization is bound to. */
        fun projectNumber(projectNumber: String) = projectNumber(JsonField.of(projectNumber))

        /**
         * Sets [Builder.projectNumber] to an arbitrary JSON value.
         *
         * You should usually call [Builder.projectNumber] with a well-typed [String] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun projectNumber(projectNumber: JsonField<String>) = apply {
            this.projectNumber = projectNumber
        }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("gcp")
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
         * Returns an immutable instance of [BetaAnalyticsFederatedActorGcpProvider].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .projectNumber()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaAnalyticsFederatedActorGcpProvider =
            BetaAnalyticsFederatedActorGcpProvider(
                checkRequired("projectNumber", projectNumber),
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
    fun validate(): BetaAnalyticsFederatedActorGcpProvider = apply {
        if (validated) {
            return@apply
        }

        projectNumber()
        _type().let {
            if (it != JsonValue.from("gcp")) {
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
        (if (projectNumber.asKnown().isPresent) 1 else 0) +
            type.let { if (it == JsonValue.from("gcp")) 1 else 0 }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaAnalyticsFederatedActorGcpProvider &&
            projectNumber == other.projectNumber &&
            type == other.type &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy { Objects.hash(projectNumber, type, additionalProperties) }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaAnalyticsFederatedActorGcpProvider{projectNumber=$projectNumber, type=$type, additionalProperties=$additionalProperties}"
}
