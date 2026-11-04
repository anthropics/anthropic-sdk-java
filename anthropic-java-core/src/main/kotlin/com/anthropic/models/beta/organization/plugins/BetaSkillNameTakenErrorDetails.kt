package com.anthropic.models.beta.organization.plugins

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

/** One of the Plugin's skills has the name of an organization skill. `skill_name` is that name. */
class BetaSkillNameTakenErrorDetails
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val errorCode: JsonValue,
    private val skillName: JsonField<String>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("error_code") @ExcludeMissing errorCode: JsonValue = JsonMissing.of(),
        @JsonProperty("skill_name") @ExcludeMissing skillName: JsonField<String> = JsonMissing.of(),
    ) : this(errorCode, skillName, mutableMapOf())

    /**
     * Expected to always return the following:
     * ```java
     * JsonValue.from("skill_name_taken")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("error_code") @ExcludeMissing fun _errorCode(): JsonValue = errorCode

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun skillName(): String = skillName.getRequired("skill_name")

    /**
     * Returns the raw JSON value of [skillName].
     *
     * Unlike [skillName], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("skill_name") @ExcludeMissing fun _skillName(): JsonField<String> = skillName

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
         * [BetaSkillNameTakenErrorDetails].
         *
         * The following fields are required:
         * ```java
         * .skillName()
         * ```
         */
        @JvmStatic fun builder() = Builder()

        /**
         * Returns an immutable instance of [BetaSkillNameTakenErrorDetails] with the required
         * [skillName] set to the given value.
         */
        @JvmStatic fun of(skillName: String) = builder().skillName(skillName).build()
    }

    /** A builder for [BetaSkillNameTakenErrorDetails]. */
    class Builder internal constructor() {

        private var errorCode: JsonValue = JsonValue.from("skill_name_taken")
        private var skillName: JsonField<String>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaSkillNameTakenErrorDetails: BetaSkillNameTakenErrorDetails) = apply {
            errorCode = betaSkillNameTakenErrorDetails.errorCode
            skillName = betaSkillNameTakenErrorDetails.skillName
            additionalProperties =
                betaSkillNameTakenErrorDetails.additionalProperties.toMutableMap()
        }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("skill_name_taken")
         * ```
         *
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun errorCode(errorCode: JsonValue) = apply { this.errorCode = errorCode }

        fun skillName(skillName: String) = skillName(JsonField.of(skillName))

        /**
         * Sets [Builder.skillName] to an arbitrary JSON value.
         *
         * You should usually call [Builder.skillName] with a well-typed [String] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun skillName(skillName: JsonField<String>) = apply { this.skillName = skillName }

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
         * Returns an immutable instance of [BetaSkillNameTakenErrorDetails].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .skillName()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaSkillNameTakenErrorDetails =
            BetaSkillNameTakenErrorDetails(
                errorCode,
                checkRequired("skillName", skillName),
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
    fun validate(): BetaSkillNameTakenErrorDetails = apply {
        if (validated) {
            return@apply
        }

        _errorCode().let {
            if (it != JsonValue.from("skill_name_taken")) {
                throw AnthropicInvalidDataException("'errorCode' is invalid, received $it")
            }
        }
        skillName()
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
        errorCode.let { if (it == JsonValue.from("skill_name_taken")) 1 else 0 } +
            (if (skillName.asKnown().isPresent) 1 else 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaSkillNameTakenErrorDetails &&
            errorCode == other.errorCode &&
            skillName == other.skillName &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy { Objects.hash(errorCode, skillName, additionalProperties) }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaSkillNameTakenErrorDetails{errorCode=$errorCode, skillName=$skillName, additionalProperties=$additionalProperties}"
}
