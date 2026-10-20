package com.anthropic.models.userprofiles

import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.checkRequired
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

/**
 * A user profile's standing in the Cyber Verification Program, which relaxes cyber safeguards for
 * requests that name the profile.
 */
class UserProfileCyberProgram
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val requirements: JsonField<UserProfileCyberRequirements>,
    private val status: JsonField<UserProfileProgramStatus>,
    private val tiers: JsonField<Tiers>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("requirements")
        @ExcludeMissing
        requirements: JsonField<UserProfileCyberRequirements> = JsonMissing.of(),
        @JsonProperty("status")
        @ExcludeMissing
        status: JsonField<UserProfileProgramStatus> = JsonMissing.of(),
        @JsonProperty("tiers") @ExcludeMissing tiers: JsonField<Tiers> = JsonMissing.of(),
    ) : this(requirements, status, tiers, mutableMapOf())

    /**
     * What a request that names the profile must allow to get the relaxed safeguards of every
     * `active` tier. `null` when no tier is `active`, and wherever `tiers` is `{}`.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun requirements(): Optional<UserProfileCyberRequirements> =
        requirements.getOptional("requirements")

    /**
     * `active` when at least one tier is `active`, or when the profile has the program's access to
     * earlier models. That access is not a tier: a profile that has it alone reads `active` with
     * every tier `inactive`, or with `tiers` `{}` on a response that does not report tiers. When
     * nothing is `active`, `pending` means that an application or enrollment is under review, or
     * that what Anthropic approved does not yet apply to this profile, and `rejected` means that
     * the application or enrollment was declined.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun status(): UserProfileProgramStatus = status.getRequired("status")

    /**
     * The program's tiers, keyed by tier: `a` (Specialized Access), `b` (Red Team Access) and `c`
     * (Defense Access). A response reports either every tier or none. When it reports them, as
     * retrieving the profile normally does, every tier has a key, whether or not it applies to the
     * profile. When it does not, as in the list of user profiles, `tiers` is `{}`, which does not
     * mean that the tiers are inactive. No tier implies another, so read the tier you need.
     * Anthropic may add tiers; ignore a key you do not recognize.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun tiers(): Tiers = tiers.getRequired("tiers")

    /**
     * Returns the raw JSON value of [requirements].
     *
     * Unlike [requirements], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("requirements")
    @ExcludeMissing
    fun _requirements(): JsonField<UserProfileCyberRequirements> = requirements

    /**
     * Returns the raw JSON value of [status].
     *
     * Unlike [status], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("status")
    @ExcludeMissing
    fun _status(): JsonField<UserProfileProgramStatus> = status

    /**
     * Returns the raw JSON value of [tiers].
     *
     * Unlike [tiers], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("tiers") @ExcludeMissing fun _tiers(): JsonField<Tiers> = tiers

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
         * Returns a mutable builder for constructing an instance of [UserProfileCyberProgram].
         *
         * The following fields are required:
         * ```java
         * .requirements()
         * .status()
         * .tiers()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [UserProfileCyberProgram]. */
    class Builder internal constructor() {

        private var requirements: JsonField<UserProfileCyberRequirements>? = null
        private var status: JsonField<UserProfileProgramStatus>? = null
        private var tiers: JsonField<Tiers>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(userProfileCyberProgram: UserProfileCyberProgram) = apply {
            requirements = userProfileCyberProgram.requirements
            status = userProfileCyberProgram.status
            tiers = userProfileCyberProgram.tiers
            additionalProperties = userProfileCyberProgram.additionalProperties.toMutableMap()
        }

        /**
         * What a request that names the profile must allow to get the relaxed safeguards of every
         * `active` tier. `null` when no tier is `active`, and wherever `tiers` is `{}`.
         */
        fun requirements(requirements: UserProfileCyberRequirements?) =
            requirements(JsonField.ofNullable(requirements))

        /** Alias for calling [Builder.requirements] with `requirements.orElse(null)`. */
        fun requirements(requirements: Optional<UserProfileCyberRequirements>) =
            requirements(requirements.getOrNull())

        /**
         * Sets [Builder.requirements] to an arbitrary JSON value.
         *
         * You should usually call [Builder.requirements] with a well-typed
         * [UserProfileCyberRequirements] value instead. This method is primarily for setting the
         * field to an undocumented or not yet supported value.
         */
        fun requirements(requirements: JsonField<UserProfileCyberRequirements>) = apply {
            this.requirements = requirements
        }

        /**
         * `active` when at least one tier is `active`, or when the profile has the program's access
         * to earlier models. That access is not a tier: a profile that has it alone reads `active`
         * with every tier `inactive`, or with `tiers` `{}` on a response that does not report
         * tiers. When nothing is `active`, `pending` means that an application or enrollment is
         * under review, or that what Anthropic approved does not yet apply to this profile, and
         * `rejected` means that the application or enrollment was declined.
         */
        fun status(status: UserProfileProgramStatus) = status(JsonField.of(status))

        /**
         * Sets [Builder.status] to an arbitrary JSON value.
         *
         * You should usually call [Builder.status] with a well-typed [UserProfileProgramStatus]
         * value instead. This method is primarily for setting the field to an undocumented or not
         * yet supported value.
         */
        fun status(status: JsonField<UserProfileProgramStatus>) = apply { this.status = status }

        /**
         * The program's tiers, keyed by tier: `a` (Specialized Access), `b` (Red Team Access) and
         * `c` (Defense Access). A response reports either every tier or none. When it reports them,
         * as retrieving the profile normally does, every tier has a key, whether or not it applies
         * to the profile. When it does not, as in the list of user profiles, `tiers` is `{}`, which
         * does not mean that the tiers are inactive. No tier implies another, so read the tier you
         * need. Anthropic may add tiers; ignore a key you do not recognize.
         */
        fun tiers(tiers: Tiers) = tiers(JsonField.of(tiers))

        /**
         * Sets [Builder.tiers] to an arbitrary JSON value.
         *
         * You should usually call [Builder.tiers] with a well-typed [Tiers] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun tiers(tiers: JsonField<Tiers>) = apply { this.tiers = tiers }

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
         * Returns an immutable instance of [UserProfileCyberProgram].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .requirements()
         * .status()
         * .tiers()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): UserProfileCyberProgram =
            UserProfileCyberProgram(
                checkRequired("requirements", requirements),
                checkRequired("status", status),
                checkRequired("tiers", tiers),
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
    fun validate(): UserProfileCyberProgram = apply {
        if (validated) {
            return@apply
        }

        requirements().ifPresent { it.validate() }
        status().validate()
        tiers().validate()
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
        (requirements.asKnown().getOrNull()?.validity() ?: 0) +
            (status.asKnown().getOrNull()?.validity() ?: 0) +
            (tiers.asKnown().getOrNull()?.validity() ?: 0)

    /**
     * The program's tiers, keyed by tier: `a` (Specialized Access), `b` (Red Team Access) and `c`
     * (Defense Access). A response reports either every tier or none. When it reports them, as
     * retrieving the profile normally does, every tier has a key, whether or not it applies to the
     * profile. When it does not, as in the list of user profiles, `tiers` is `{}`, which does not
     * mean that the tiers are inactive. No tier implies another, so read the tier you need.
     * Anthropic may add tiers; ignore a key you do not recognize.
     */
    class Tiers
    @JsonCreator
    private constructor(
        @com.fasterxml.jackson.annotation.JsonValue
        private val additionalProperties: Map<String, JsonValue>
    ) {

        @JsonAnyGetter
        @ExcludeMissing
        fun _additionalProperties(): Map<String, JsonValue> = additionalProperties

        fun toBuilder() = Builder().from(this)

        companion object {

            /** Returns a mutable builder for constructing an instance of [Tiers]. */
            @JvmStatic fun builder() = Builder()
        }

        /** A builder for [Tiers]. */
        class Builder internal constructor() {

            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            @JvmSynthetic
            internal fun from(tiers: Tiers) = apply {
                additionalProperties = tiers.additionalProperties.toMutableMap()
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
             * Returns an immutable instance of [Tiers].
             *
             * Further updates to this [Builder] will not mutate the returned instance.
             */
            fun build(): Tiers = Tiers(additionalProperties.toImmutable())
        }

        private var validated: Boolean = false

        /**
         * Validates that the types of all values in this object match their expected types
         * recursively.
         *
         * This method is _not_ forwards compatible with new types from the API for existing fields.
         *
         * @throws AnthropicInvalidDataException if any value type in this object doesn't match its
         *   expected type.
         */
        fun validate(): Tiers = apply {
            if (validated) {
                return@apply
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
         * Returns a score indicating how many valid values are contained in this object
         * recursively.
         *
         * Used for best match union deserialization.
         */
        @JvmSynthetic
        internal fun validity(): Int =
            additionalProperties.count { (_, value) -> !value.isNull() && !value.isMissing() }

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Tiers && additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy { Objects.hash(additionalProperties) }

        override fun hashCode(): Int = hashCode

        override fun toString() = "Tiers{additionalProperties=$additionalProperties}"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is UserProfileCyberProgram &&
            requirements == other.requirements &&
            status == other.status &&
            tiers == other.tiers &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(requirements, status, tiers, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "UserProfileCyberProgram{requirements=$requirements, status=$status, tiers=$tiers, additionalProperties=$additionalProperties}"
}
