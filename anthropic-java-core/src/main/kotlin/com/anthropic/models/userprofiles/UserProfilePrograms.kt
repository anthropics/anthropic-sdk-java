package com.anthropic.models.userprofiles

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

/**
 * A user profile's standing in each program. A program is a set of capabilities or exemptions that
 * Anthropic approves for the entity the profile represents. A program reads `null` when the profile
 * has no status in it, when the caller's organization is not entitled to read programs, or when the
 * response does not report that program. Retrieving the profile reports every program. Anthropic
 * may add programs; ignore a key you do not recognize.
 */
class UserProfilePrograms
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val cyber: JsonField<UserProfileCyberProgram>,
    private val efs: JsonField<UserProfileEfsProgram>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("cyber")
        @ExcludeMissing
        cyber: JsonField<UserProfileCyberProgram> = JsonMissing.of(),
        @JsonProperty("efs")
        @ExcludeMissing
        efs: JsonField<UserProfileEfsProgram> = JsonMissing.of(),
    ) : this(cyber, efs, mutableMapOf())

    /**
     * The profile's standing in the Cyber Verification Program. `null` when the profile has no
     * status in it. Only retrieving the profile gives the full answer: a list or update response
     * can show `null`, or a status other than `active`, for a profile that reads `active` when
     * retrieved.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun cyber(): Optional<UserProfileCyberProgram> = cyber.getOptional("cyber")

    /**
     * The profile's standing in Enterprise Frontier Safeguards. `null` unless the platform's
     * attestation for the program is in place on this profile, or the entity it represents has
     * applied to the program. A list of user profiles does not report this program: there it reads
     * `null` for every profile, so retrieve the profile to read it.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun efs(): Optional<UserProfileEfsProgram> = efs.getOptional("efs")

    /**
     * Returns the raw JSON value of [cyber].
     *
     * Unlike [cyber], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("cyber") @ExcludeMissing fun _cyber(): JsonField<UserProfileCyberProgram> = cyber

    /**
     * Returns the raw JSON value of [efs].
     *
     * Unlike [efs], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("efs") @ExcludeMissing fun _efs(): JsonField<UserProfileEfsProgram> = efs

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
         * Returns a mutable builder for constructing an instance of [UserProfilePrograms].
         *
         * The following fields are required:
         * ```java
         * .cyber()
         * .efs()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [UserProfilePrograms]. */
    class Builder internal constructor() {

        private var cyber: JsonField<UserProfileCyberProgram>? = null
        private var efs: JsonField<UserProfileEfsProgram>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(userProfilePrograms: UserProfilePrograms) = apply {
            cyber = userProfilePrograms.cyber
            efs = userProfilePrograms.efs
            additionalProperties = userProfilePrograms.additionalProperties.toMutableMap()
        }

        /**
         * The profile's standing in the Cyber Verification Program. `null` when the profile has no
         * status in it. Only retrieving the profile gives the full answer: a list or update
         * response can show `null`, or a status other than `active`, for a profile that reads
         * `active` when retrieved.
         */
        fun cyber(cyber: UserProfileCyberProgram?) = cyber(JsonField.ofNullable(cyber))

        /** Alias for calling [Builder.cyber] with `cyber.orElse(null)`. */
        fun cyber(cyber: Optional<UserProfileCyberProgram>) = cyber(cyber.getOrNull())

        /**
         * Sets [Builder.cyber] to an arbitrary JSON value.
         *
         * You should usually call [Builder.cyber] with a well-typed [UserProfileCyberProgram] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun cyber(cyber: JsonField<UserProfileCyberProgram>) = apply { this.cyber = cyber }

        /**
         * The profile's standing in Enterprise Frontier Safeguards. `null` unless the platform's
         * attestation for the program is in place on this profile, or the entity it represents has
         * applied to the program. A list of user profiles does not report this program: there it
         * reads `null` for every profile, so retrieve the profile to read it.
         */
        fun efs(efs: UserProfileEfsProgram?) = efs(JsonField.ofNullable(efs))

        /** Alias for calling [Builder.efs] with `efs.orElse(null)`. */
        fun efs(efs: Optional<UserProfileEfsProgram>) = efs(efs.getOrNull())

        /**
         * Sets [Builder.efs] to an arbitrary JSON value.
         *
         * You should usually call [Builder.efs] with a well-typed [UserProfileEfsProgram] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun efs(efs: JsonField<UserProfileEfsProgram>) = apply { this.efs = efs }

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
         * Returns an immutable instance of [UserProfilePrograms].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .cyber()
         * .efs()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): UserProfilePrograms =
            UserProfilePrograms(
                checkRequired("cyber", cyber),
                checkRequired("efs", efs),
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
    fun validate(): UserProfilePrograms = apply {
        if (validated) {
            return@apply
        }

        cyber().ifPresent { it.validate() }
        efs().ifPresent { it.validate() }
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
        (cyber.asKnown().getOrNull()?.validity() ?: 0) +
            (efs.asKnown().getOrNull()?.validity() ?: 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is UserProfilePrograms &&
            cyber == other.cyber &&
            efs == other.efs &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy { Objects.hash(cyber, efs, additionalProperties) }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "UserProfilePrograms{cyber=$cyber, efs=$efs, additionalProperties=$additionalProperties}"
}
