package com.anthropic.models.beta.userprofiles.providers.google.datacontrols

import com.anthropic.core.Enum
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

/** One check that a verification attempt failed. */
class BetaGoogleManagedStorageFailedCheck
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val check: JsonField<Check>,
    private val message: JsonField<String>,
    private val role: JsonField<Role>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("check") @ExcludeMissing check: JsonField<Check> = JsonMissing.of(),
        @JsonProperty("message") @ExcludeMissing message: JsonField<String> = JsonMissing.of(),
        @JsonProperty("role") @ExcludeMissing role: JsonField<Role> = JsonMissing.of(),
    ) : this(check, message, role, mutableMapOf())

    /**
     * Which check failed, such as `label_mismatch` or `grant_mismatch`. Anthropic may add values.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun check(): Check = check.getRequired("check")

    /**
     * What failed and, where the end customer can fix it, what to change.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun message(): String = message.getRequired("message")

    /**
     * On a check about one of the accounts that Anthropic uses to reach the bucket, that account's
     * kind of access, such as `ingest`. `null` on any other check. Anthropic may add values.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun role(): Optional<Role> = role.getOptional("role")

    /**
     * Returns the raw JSON value of [check].
     *
     * Unlike [check], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("check") @ExcludeMissing fun _check(): JsonField<Check> = check

    /**
     * Returns the raw JSON value of [message].
     *
     * Unlike [message], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("message") @ExcludeMissing fun _message(): JsonField<String> = message

    /**
     * Returns the raw JSON value of [role].
     *
     * Unlike [role], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("role") @ExcludeMissing fun _role(): JsonField<Role> = role

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
         * [BetaGoogleManagedStorageFailedCheck].
         *
         * The following fields are required:
         * ```java
         * .check()
         * .message()
         * .role()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaGoogleManagedStorageFailedCheck]. */
    class Builder internal constructor() {

        private var check: JsonField<Check>? = null
        private var message: JsonField<String>? = null
        private var role: JsonField<Role>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(
            betaGoogleManagedStorageFailedCheck: BetaGoogleManagedStorageFailedCheck
        ) = apply {
            check = betaGoogleManagedStorageFailedCheck.check
            message = betaGoogleManagedStorageFailedCheck.message
            role = betaGoogleManagedStorageFailedCheck.role
            additionalProperties =
                betaGoogleManagedStorageFailedCheck.additionalProperties.toMutableMap()
        }

        /**
         * Which check failed, such as `label_mismatch` or `grant_mismatch`. Anthropic may add
         * values.
         */
        fun check(check: Check) = check(JsonField.of(check))

        /**
         * Sets [Builder.check] to an arbitrary JSON value.
         *
         * You should usually call [Builder.check] with a well-typed [Check] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun check(check: JsonField<Check>) = apply { this.check = check }

        /** What failed and, where the end customer can fix it, what to change. */
        fun message(message: String) = message(JsonField.of(message))

        /**
         * Sets [Builder.message] to an arbitrary JSON value.
         *
         * You should usually call [Builder.message] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun message(message: JsonField<String>) = apply { this.message = message }

        /**
         * On a check about one of the accounts that Anthropic uses to reach the bucket, that
         * account's kind of access, such as `ingest`. `null` on any other check. Anthropic may add
         * values.
         */
        fun role(role: Role?) = role(JsonField.ofNullable(role))

        /** Alias for calling [Builder.role] with `role.orElse(null)`. */
        fun role(role: Optional<Role>) = role(role.getOrNull())

        /**
         * Sets [Builder.role] to an arbitrary JSON value.
         *
         * You should usually call [Builder.role] with a well-typed [Role] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun role(role: JsonField<Role>) = apply { this.role = role }

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
         * Returns an immutable instance of [BetaGoogleManagedStorageFailedCheck].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .check()
         * .message()
         * .role()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaGoogleManagedStorageFailedCheck =
            BetaGoogleManagedStorageFailedCheck(
                checkRequired("check", check),
                checkRequired("message", message),
                checkRequired("role", role),
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
    fun validate(): BetaGoogleManagedStorageFailedCheck = apply {
        if (validated) {
            return@apply
        }

        check().validate()
        message()
        role().ifPresent { it.validate() }
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
        (check.asKnown().getOrNull()?.validity() ?: 0) +
            (if (message.asKnown().isPresent) 1 else 0) +
            (role.asKnown().getOrNull()?.validity() ?: 0)

    /**
     * Which check failed, such as `label_mismatch` or `grant_mismatch`. Anthropic may add values.
     */
    class Check private constructor(private val value: JsonField<String>) : Enum {

        /**
         * Returns this class instance's raw value.
         *
         * This is usually only useful if this instance was deserialized from data that doesn't
         * match any known member, and you want to know that value. For example, if the SDK is on an
         * older version than the API, then the API may respond with new members that the SDK is
         * unaware of.
         */
        @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

        companion object {

            @JvmField val LABEL_MISMATCH = Check(JsonField.of("label_mismatch"))

            @JvmField val LOCATION_MISMATCH = Check(JsonField.of("location_mismatch"))

            @JvmField val ENCRYPTION = Check(JsonField.of("encryption"))

            @JvmField val VERSIONING = Check(JsonField.of("versioning"))

            @JvmField val BUCKET_SETTINGS = Check(JsonField.of("bucket_settings"))

            @JvmField val LIFECYCLE_RULES = Check(JsonField.of("lifecycle_rules"))

            @JvmField val DELETION_POLICY = Check(JsonField.of("deletion_policy"))

            @JvmField val GRANT_MISMATCH = Check(JsonField.of("grant_mismatch"))

            @JvmField val KMS_KEY_REFUSED = Check(JsonField.of("kms_key_refused"))

            @JvmField val PROBE_FAILED = Check(JsonField.of("probe_failed"))

            @JvmField val PROJECT_NOT_VERIFIED = Check(JsonField.of("project_not_verified"))

            @JvmStatic
            fun of(value: String): Check =
                // Intern known values so `==` works
                when (value) {
                    "label_mismatch" -> LABEL_MISMATCH
                    "location_mismatch" -> LOCATION_MISMATCH
                    "encryption" -> ENCRYPTION
                    "versioning" -> VERSIONING
                    "bucket_settings" -> BUCKET_SETTINGS
                    "lifecycle_rules" -> LIFECYCLE_RULES
                    "deletion_policy" -> DELETION_POLICY
                    "grant_mismatch" -> GRANT_MISMATCH
                    "kms_key_refused" -> KMS_KEY_REFUSED
                    "probe_failed" -> PROBE_FAILED
                    "project_not_verified" -> PROJECT_NOT_VERIFIED
                    else -> Check(JsonField.of(value))
                }

            @JsonCreator
            @JvmStatic
            fun of(value: JsonField<String>): Check =
                value.asString().getOrNull()?.let { of(it) } ?: Check(value)
        }

        /** An enum containing [Check]'s known values. */
        enum class Known {
            LABEL_MISMATCH,
            LOCATION_MISMATCH,
            ENCRYPTION,
            VERSIONING,
            BUCKET_SETTINGS,
            LIFECYCLE_RULES,
            DELETION_POLICY,
            GRANT_MISMATCH,
            KMS_KEY_REFUSED,
            PROBE_FAILED,
            PROJECT_NOT_VERIFIED,
        }

        /**
         * An enum containing [Check]'s known values, as well as an [_UNKNOWN] member.
         *
         * An instance of [Check] can contain an unknown value in a couple of cases:
         * - It was deserialized from data that doesn't match any known member. For example, if the
         *   SDK is on an older version than the API, then the API may respond with new members that
         *   the SDK is unaware of.
         * - It was constructed with an arbitrary value using the [of] method.
         */
        enum class Value {
            LABEL_MISMATCH,
            LOCATION_MISMATCH,
            ENCRYPTION,
            VERSIONING,
            BUCKET_SETTINGS,
            LIFECYCLE_RULES,
            DELETION_POLICY,
            GRANT_MISMATCH,
            KMS_KEY_REFUSED,
            PROBE_FAILED,
            PROJECT_NOT_VERIFIED,
            /** An enum member indicating that [Check] was instantiated with an unknown value. */
            _UNKNOWN,
        }

        /**
         * Returns an enum member corresponding to this class instance's value, or [Value._UNKNOWN]
         * if the class was instantiated with an unknown value.
         *
         * Use the [known] method instead if you're certain the value is always known or if you want
         * to throw for the unknown case.
         */
        fun value(): Value =
            when (this) {
                LABEL_MISMATCH -> Value.LABEL_MISMATCH
                LOCATION_MISMATCH -> Value.LOCATION_MISMATCH
                ENCRYPTION -> Value.ENCRYPTION
                VERSIONING -> Value.VERSIONING
                BUCKET_SETTINGS -> Value.BUCKET_SETTINGS
                LIFECYCLE_RULES -> Value.LIFECYCLE_RULES
                DELETION_POLICY -> Value.DELETION_POLICY
                GRANT_MISMATCH -> Value.GRANT_MISMATCH
                KMS_KEY_REFUSED -> Value.KMS_KEY_REFUSED
                PROBE_FAILED -> Value.PROBE_FAILED
                PROJECT_NOT_VERIFIED -> Value.PROJECT_NOT_VERIFIED
                else -> Value._UNKNOWN
            }

        /**
         * Returns an enum member corresponding to this class instance's value.
         *
         * Use the [value] method instead if you're uncertain the value is always known and don't
         * want to throw for the unknown case.
         *
         * @throws AnthropicInvalidDataException if this class instance's value is a not a known
         *   member.
         */
        fun known(): Known =
            when (this) {
                LABEL_MISMATCH -> Known.LABEL_MISMATCH
                LOCATION_MISMATCH -> Known.LOCATION_MISMATCH
                ENCRYPTION -> Known.ENCRYPTION
                VERSIONING -> Known.VERSIONING
                BUCKET_SETTINGS -> Known.BUCKET_SETTINGS
                LIFECYCLE_RULES -> Known.LIFECYCLE_RULES
                DELETION_POLICY -> Known.DELETION_POLICY
                GRANT_MISMATCH -> Known.GRANT_MISMATCH
                KMS_KEY_REFUSED -> Known.KMS_KEY_REFUSED
                PROBE_FAILED -> Known.PROBE_FAILED
                PROJECT_NOT_VERIFIED -> Known.PROJECT_NOT_VERIFIED
                else -> throw AnthropicInvalidDataException("Unknown Check: $value")
            }

        /**
         * Returns this class instance's primitive wire representation.
         *
         * This differs from the [toString] method because that method is primarily for debugging
         * and generally doesn't throw.
         *
         * @throws AnthropicInvalidDataException if this class instance's value does not have the
         *   expected primitive type.
         */
        fun asString(): String =
            _value().asString().orElseThrow {
                AnthropicInvalidDataException("Value is not a String")
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
        fun validate(): Check = apply {
            if (validated) {
                return@apply
            }

            known()
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
        @JvmSynthetic internal fun validity(): Int = if (value() == Value._UNKNOWN) 0 else 1

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Check && value == other.value
        }

        override fun hashCode() = value.hashCode()

        override fun toString() = value.toString()
    }

    /**
     * On a check about one of the accounts that Anthropic uses to reach the bucket, that account's
     * kind of access, such as `ingest`. `null` on any other check. Anthropic may add values.
     */
    class Role private constructor(private val value: JsonField<String>) : Enum {

        /**
         * Returns this class instance's raw value.
         *
         * This is usually only useful if this instance was deserialized from data that doesn't
         * match any known member, and you want to know that value. For example, if the SDK is on an
         * older version than the API, then the API may respond with new members that the SDK is
         * unaware of.
         */
        @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

        companion object {

            @JvmField val INGEST = Role(JsonField.of("ingest"))

            @JvmField val READ = Role(JsonField.of("read"))

            @JvmField val MAINTENANCE = Role(JsonField.of("maintenance"))

            @JvmField val MEDIA_ERASURE = Role(JsonField.of("media_erasure"))

            @JvmField val VERIFY = Role(JsonField.of("verify"))

            @JvmStatic
            fun of(value: String): Role =
                // Intern known values so `==` works
                when (value) {
                    "ingest" -> INGEST
                    "read" -> READ
                    "maintenance" -> MAINTENANCE
                    "media_erasure" -> MEDIA_ERASURE
                    "verify" -> VERIFY
                    else -> Role(JsonField.of(value))
                }

            @JsonCreator
            @JvmStatic
            fun of(value: JsonField<String>): Role =
                value.asString().getOrNull()?.let { of(it) } ?: Role(value)
        }

        /** An enum containing [Role]'s known values. */
        enum class Known {
            INGEST,
            READ,
            MAINTENANCE,
            MEDIA_ERASURE,
            VERIFY,
        }

        /**
         * An enum containing [Role]'s known values, as well as an [_UNKNOWN] member.
         *
         * An instance of [Role] can contain an unknown value in a couple of cases:
         * - It was deserialized from data that doesn't match any known member. For example, if the
         *   SDK is on an older version than the API, then the API may respond with new members that
         *   the SDK is unaware of.
         * - It was constructed with an arbitrary value using the [of] method.
         */
        enum class Value {
            INGEST,
            READ,
            MAINTENANCE,
            MEDIA_ERASURE,
            VERIFY,
            /** An enum member indicating that [Role] was instantiated with an unknown value. */
            _UNKNOWN,
        }

        /**
         * Returns an enum member corresponding to this class instance's value, or [Value._UNKNOWN]
         * if the class was instantiated with an unknown value.
         *
         * Use the [known] method instead if you're certain the value is always known or if you want
         * to throw for the unknown case.
         */
        fun value(): Value =
            when (this) {
                INGEST -> Value.INGEST
                READ -> Value.READ
                MAINTENANCE -> Value.MAINTENANCE
                MEDIA_ERASURE -> Value.MEDIA_ERASURE
                VERIFY -> Value.VERIFY
                else -> Value._UNKNOWN
            }

        /**
         * Returns an enum member corresponding to this class instance's value.
         *
         * Use the [value] method instead if you're uncertain the value is always known and don't
         * want to throw for the unknown case.
         *
         * @throws AnthropicInvalidDataException if this class instance's value is a not a known
         *   member.
         */
        fun known(): Known =
            when (this) {
                INGEST -> Known.INGEST
                READ -> Known.READ
                MAINTENANCE -> Known.MAINTENANCE
                MEDIA_ERASURE -> Known.MEDIA_ERASURE
                VERIFY -> Known.VERIFY
                else -> throw AnthropicInvalidDataException("Unknown Role: $value")
            }

        /**
         * Returns this class instance's primitive wire representation.
         *
         * This differs from the [toString] method because that method is primarily for debugging
         * and generally doesn't throw.
         *
         * @throws AnthropicInvalidDataException if this class instance's value does not have the
         *   expected primitive type.
         */
        fun asString(): String =
            _value().asString().orElseThrow {
                AnthropicInvalidDataException("Value is not a String")
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
        fun validate(): Role = apply {
            if (validated) {
                return@apply
            }

            known()
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
        @JvmSynthetic internal fun validity(): Int = if (value() == Value._UNKNOWN) 0 else 1

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Role && value == other.value
        }

        override fun hashCode() = value.hashCode()

        override fun toString() = value.toString()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaGoogleManagedStorageFailedCheck &&
            check == other.check &&
            message == other.message &&
            role == other.role &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy { Objects.hash(check, message, role, additionalProperties) }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaGoogleManagedStorageFailedCheck{check=$check, message=$message, role=$role, additionalProperties=$additionalProperties}"
}
