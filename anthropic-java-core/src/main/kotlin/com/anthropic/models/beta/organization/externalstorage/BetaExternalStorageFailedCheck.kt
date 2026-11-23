package com.anthropic.models.beta.organization.externalstorage

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

/** One check that an External Storage's verification failed. */
class BetaExternalStorageFailedCheck
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
     * Name of the check that failed, such as `versioning`, for code to branch on. New names may be
     * added; `other` is a check this API does not name. The value is a name with no data of its
     * own.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun check(): Check = check.getRequired("check")

    /**
     * What failed and how to fix it, in plain words.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun message(): String = message.getRequired("message")

    /**
     * The part of your access grant that the check is about, such as `ingest`: on AWS, one of the
     * IAM roles your setup created; on Google Cloud, the grant to one of Anthropic's service
     * accounts. `null` when the check is not about one. New names may be added. The value is a name
     * with no data of its own.
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
         * [BetaExternalStorageFailedCheck].
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

    /** A builder for [BetaExternalStorageFailedCheck]. */
    class Builder internal constructor() {

        private var check: JsonField<Check>? = null
        private var message: JsonField<String>? = null
        private var role: JsonField<Role>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaExternalStorageFailedCheck: BetaExternalStorageFailedCheck) = apply {
            check = betaExternalStorageFailedCheck.check
            message = betaExternalStorageFailedCheck.message
            role = betaExternalStorageFailedCheck.role
            additionalProperties =
                betaExternalStorageFailedCheck.additionalProperties.toMutableMap()
        }

        /**
         * Name of the check that failed, such as `versioning`, for code to branch on. New names may
         * be added; `other` is a check this API does not name. The value is a name with no data of
         * its own.
         */
        fun check(check: Check) = check(JsonField.of(check))

        /**
         * Sets [Builder.check] to an arbitrary JSON value.
         *
         * You should usually call [Builder.check] with a well-typed [Check] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun check(check: JsonField<Check>) = apply { this.check = check }

        /** What failed and how to fix it, in plain words. */
        fun message(message: String) = message(JsonField.of(message))

        /**
         * Sets [Builder.message] to an arbitrary JSON value.
         *
         * You should usually call [Builder.message] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun message(message: JsonField<String>) = apply { this.message = message }

        /**
         * The part of your access grant that the check is about, such as `ingest`: on AWS, one of
         * the IAM roles your setup created; on Google Cloud, the grant to one of Anthropic's
         * service accounts. `null` when the check is not about one. New names may be added. The
         * value is a name with no data of its own.
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
         * Returns an immutable instance of [BetaExternalStorageFailedCheck].
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
        fun build(): BetaExternalStorageFailedCheck =
            BetaExternalStorageFailedCheck(
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
    fun validate(): BetaExternalStorageFailedCheck = apply {
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
     * Name of the check that failed, such as `versioning`, for code to branch on. New names may be
     * added; `other` is a check this API does not name. The value is a name with no data of its
     * own.
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

            @JvmField val ALREADY_REGISTERED = Check(JsonField.of("already_registered"))

            @JvmField val ASSUME_ROLE = Check(JsonField.of("assume_role"))

            @JvmField val BUCKET_EMPTY = Check(JsonField.of("bucket_empty"))

            @JvmField val BUCKET_NOT_CONFIRMED = Check(JsonField.of("bucket_not_confirmed"))

            @JvmField val BUCKET_POLICY = Check(JsonField.of("bucket_policy"))

            @JvmField val BUCKET_SETTINGS = Check(JsonField.of("bucket_settings"))

            @JvmField val DEFAULT_ENCRYPTION = Check(JsonField.of("default_encryption"))

            @JvmField val DELETION_POLICY = Check(JsonField.of("deletion_policy"))

            @JvmField val ENCRYPTION = Check(JsonField.of("encryption"))

            @JvmField val GRANT_MISMATCH = Check(JsonField.of("grant_mismatch"))

            @JvmField val KEY_USABLE = Check(JsonField.of("key_usable"))

            @JvmField val KMS_KEY_REFUSED = Check(JsonField.of("kms_key_refused"))

            @JvmField val LIFECYCLE_NO_ARCHIVE = Check(JsonField.of("lifecycle_no_archive"))

            @JvmField val LIFECYCLE_RULES = Check(JsonField.of("lifecycle_rules"))

            @JvmField val LIFECYCLE_TEMPLATE = Check(JsonField.of("lifecycle_template"))

            @JvmField val LOCATION_MISMATCH = Check(JsonField.of("location_mismatch"))

            @JvmField val OBJECT_LOCK = Check(JsonField.of("object_lock"))

            @JvmField val OTHER = Check(JsonField.of("other"))

            @JvmField val OWNERSHIP_ENFORCED = Check(JsonField.of("ownership_enforced"))

            @JvmField val PROBE_FAILED = Check(JsonField.of("probe_failed"))

            @JvmField val PROBE_OBJECT = Check(JsonField.of("probe_object"))

            @JvmField val PROBE_STORAGE_CLASS = Check(JsonField.of("probe_storage_class"))

            @JvmField val PUBLIC_ACCESS_BLOCK = Check(JsonField.of("public_access_block"))

            @JvmField val REGION = Check(JsonField.of("region"))

            @JvmField val REPLICATION = Check(JsonField.of("replication"))

            @JvmField val REQUEST_METRICS = Check(JsonField.of("request_metrics"))

            @JvmField val REQUEST_PAYMENT = Check(JsonField.of("request_payment"))

            @JvmField val TRANSCRIPTS_SUPPORT = Check(JsonField.of("transcripts_support"))

            @JvmField val TRUST_INGEST_ROLE = Check(JsonField.of("trust_ingest_role"))

            @JvmField val TRUST_MAINTENANCE_ROLE = Check(JsonField.of("trust_maintenance_role"))

            @JvmField val TRUST_MEDIA_ERASURE_ROLE = Check(JsonField.of("trust_media_erasure_role"))

            @JvmField val TRUST_READ_ROLE = Check(JsonField.of("trust_read_role"))

            @JvmField val TRUST_TRANSCRIPTS_ROLE = Check(JsonField.of("trust_transcripts_role"))

            @JvmField
            val TRUST_TRANSCRIPTS_VERIFY_ROLE = Check(JsonField.of("trust_transcripts_verify_role"))

            @JvmField val TRUST_VERIFY_ROLE = Check(JsonField.of("trust_verify_role"))

            @JvmField val VERSIONING = Check(JsonField.of("versioning"))

            @JvmStatic
            fun of(value: String): Check =
                // Intern known values so `==` works
                when (value) {
                    "already_registered" -> ALREADY_REGISTERED
                    "assume_role" -> ASSUME_ROLE
                    "bucket_empty" -> BUCKET_EMPTY
                    "bucket_not_confirmed" -> BUCKET_NOT_CONFIRMED
                    "bucket_policy" -> BUCKET_POLICY
                    "bucket_settings" -> BUCKET_SETTINGS
                    "default_encryption" -> DEFAULT_ENCRYPTION
                    "deletion_policy" -> DELETION_POLICY
                    "encryption" -> ENCRYPTION
                    "grant_mismatch" -> GRANT_MISMATCH
                    "key_usable" -> KEY_USABLE
                    "kms_key_refused" -> KMS_KEY_REFUSED
                    "lifecycle_no_archive" -> LIFECYCLE_NO_ARCHIVE
                    "lifecycle_rules" -> LIFECYCLE_RULES
                    "lifecycle_template" -> LIFECYCLE_TEMPLATE
                    "location_mismatch" -> LOCATION_MISMATCH
                    "object_lock" -> OBJECT_LOCK
                    "other" -> OTHER
                    "ownership_enforced" -> OWNERSHIP_ENFORCED
                    "probe_failed" -> PROBE_FAILED
                    "probe_object" -> PROBE_OBJECT
                    "probe_storage_class" -> PROBE_STORAGE_CLASS
                    "public_access_block" -> PUBLIC_ACCESS_BLOCK
                    "region" -> REGION
                    "replication" -> REPLICATION
                    "request_metrics" -> REQUEST_METRICS
                    "request_payment" -> REQUEST_PAYMENT
                    "transcripts_support" -> TRANSCRIPTS_SUPPORT
                    "trust_ingest_role" -> TRUST_INGEST_ROLE
                    "trust_maintenance_role" -> TRUST_MAINTENANCE_ROLE
                    "trust_media_erasure_role" -> TRUST_MEDIA_ERASURE_ROLE
                    "trust_read_role" -> TRUST_READ_ROLE
                    "trust_transcripts_role" -> TRUST_TRANSCRIPTS_ROLE
                    "trust_transcripts_verify_role" -> TRUST_TRANSCRIPTS_VERIFY_ROLE
                    "trust_verify_role" -> TRUST_VERIFY_ROLE
                    "versioning" -> VERSIONING
                    else -> Check(JsonField.of(value))
                }

            @JsonCreator
            @JvmStatic
            fun of(value: JsonField<String>): Check =
                value.asString().getOrNull()?.let { of(it) } ?: Check(value)
        }

        /** An enum containing [Check]'s known values. */
        enum class Known {
            ALREADY_REGISTERED,
            ASSUME_ROLE,
            BUCKET_EMPTY,
            BUCKET_NOT_CONFIRMED,
            BUCKET_POLICY,
            BUCKET_SETTINGS,
            DEFAULT_ENCRYPTION,
            DELETION_POLICY,
            ENCRYPTION,
            GRANT_MISMATCH,
            KEY_USABLE,
            KMS_KEY_REFUSED,
            LIFECYCLE_NO_ARCHIVE,
            LIFECYCLE_RULES,
            LIFECYCLE_TEMPLATE,
            LOCATION_MISMATCH,
            OBJECT_LOCK,
            OTHER,
            OWNERSHIP_ENFORCED,
            PROBE_FAILED,
            PROBE_OBJECT,
            PROBE_STORAGE_CLASS,
            PUBLIC_ACCESS_BLOCK,
            REGION,
            REPLICATION,
            REQUEST_METRICS,
            REQUEST_PAYMENT,
            TRANSCRIPTS_SUPPORT,
            TRUST_INGEST_ROLE,
            TRUST_MAINTENANCE_ROLE,
            TRUST_MEDIA_ERASURE_ROLE,
            TRUST_READ_ROLE,
            TRUST_TRANSCRIPTS_ROLE,
            TRUST_TRANSCRIPTS_VERIFY_ROLE,
            TRUST_VERIFY_ROLE,
            VERSIONING,
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
            ALREADY_REGISTERED,
            ASSUME_ROLE,
            BUCKET_EMPTY,
            BUCKET_NOT_CONFIRMED,
            BUCKET_POLICY,
            BUCKET_SETTINGS,
            DEFAULT_ENCRYPTION,
            DELETION_POLICY,
            ENCRYPTION,
            GRANT_MISMATCH,
            KEY_USABLE,
            KMS_KEY_REFUSED,
            LIFECYCLE_NO_ARCHIVE,
            LIFECYCLE_RULES,
            LIFECYCLE_TEMPLATE,
            LOCATION_MISMATCH,
            OBJECT_LOCK,
            OTHER,
            OWNERSHIP_ENFORCED,
            PROBE_FAILED,
            PROBE_OBJECT,
            PROBE_STORAGE_CLASS,
            PUBLIC_ACCESS_BLOCK,
            REGION,
            REPLICATION,
            REQUEST_METRICS,
            REQUEST_PAYMENT,
            TRANSCRIPTS_SUPPORT,
            TRUST_INGEST_ROLE,
            TRUST_MAINTENANCE_ROLE,
            TRUST_MEDIA_ERASURE_ROLE,
            TRUST_READ_ROLE,
            TRUST_TRANSCRIPTS_ROLE,
            TRUST_TRANSCRIPTS_VERIFY_ROLE,
            TRUST_VERIFY_ROLE,
            VERSIONING,
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
                ALREADY_REGISTERED -> Value.ALREADY_REGISTERED
                ASSUME_ROLE -> Value.ASSUME_ROLE
                BUCKET_EMPTY -> Value.BUCKET_EMPTY
                BUCKET_NOT_CONFIRMED -> Value.BUCKET_NOT_CONFIRMED
                BUCKET_POLICY -> Value.BUCKET_POLICY
                BUCKET_SETTINGS -> Value.BUCKET_SETTINGS
                DEFAULT_ENCRYPTION -> Value.DEFAULT_ENCRYPTION
                DELETION_POLICY -> Value.DELETION_POLICY
                ENCRYPTION -> Value.ENCRYPTION
                GRANT_MISMATCH -> Value.GRANT_MISMATCH
                KEY_USABLE -> Value.KEY_USABLE
                KMS_KEY_REFUSED -> Value.KMS_KEY_REFUSED
                LIFECYCLE_NO_ARCHIVE -> Value.LIFECYCLE_NO_ARCHIVE
                LIFECYCLE_RULES -> Value.LIFECYCLE_RULES
                LIFECYCLE_TEMPLATE -> Value.LIFECYCLE_TEMPLATE
                LOCATION_MISMATCH -> Value.LOCATION_MISMATCH
                OBJECT_LOCK -> Value.OBJECT_LOCK
                OTHER -> Value.OTHER
                OWNERSHIP_ENFORCED -> Value.OWNERSHIP_ENFORCED
                PROBE_FAILED -> Value.PROBE_FAILED
                PROBE_OBJECT -> Value.PROBE_OBJECT
                PROBE_STORAGE_CLASS -> Value.PROBE_STORAGE_CLASS
                PUBLIC_ACCESS_BLOCK -> Value.PUBLIC_ACCESS_BLOCK
                REGION -> Value.REGION
                REPLICATION -> Value.REPLICATION
                REQUEST_METRICS -> Value.REQUEST_METRICS
                REQUEST_PAYMENT -> Value.REQUEST_PAYMENT
                TRANSCRIPTS_SUPPORT -> Value.TRANSCRIPTS_SUPPORT
                TRUST_INGEST_ROLE -> Value.TRUST_INGEST_ROLE
                TRUST_MAINTENANCE_ROLE -> Value.TRUST_MAINTENANCE_ROLE
                TRUST_MEDIA_ERASURE_ROLE -> Value.TRUST_MEDIA_ERASURE_ROLE
                TRUST_READ_ROLE -> Value.TRUST_READ_ROLE
                TRUST_TRANSCRIPTS_ROLE -> Value.TRUST_TRANSCRIPTS_ROLE
                TRUST_TRANSCRIPTS_VERIFY_ROLE -> Value.TRUST_TRANSCRIPTS_VERIFY_ROLE
                TRUST_VERIFY_ROLE -> Value.TRUST_VERIFY_ROLE
                VERSIONING -> Value.VERSIONING
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
                ALREADY_REGISTERED -> Known.ALREADY_REGISTERED
                ASSUME_ROLE -> Known.ASSUME_ROLE
                BUCKET_EMPTY -> Known.BUCKET_EMPTY
                BUCKET_NOT_CONFIRMED -> Known.BUCKET_NOT_CONFIRMED
                BUCKET_POLICY -> Known.BUCKET_POLICY
                BUCKET_SETTINGS -> Known.BUCKET_SETTINGS
                DEFAULT_ENCRYPTION -> Known.DEFAULT_ENCRYPTION
                DELETION_POLICY -> Known.DELETION_POLICY
                ENCRYPTION -> Known.ENCRYPTION
                GRANT_MISMATCH -> Known.GRANT_MISMATCH
                KEY_USABLE -> Known.KEY_USABLE
                KMS_KEY_REFUSED -> Known.KMS_KEY_REFUSED
                LIFECYCLE_NO_ARCHIVE -> Known.LIFECYCLE_NO_ARCHIVE
                LIFECYCLE_RULES -> Known.LIFECYCLE_RULES
                LIFECYCLE_TEMPLATE -> Known.LIFECYCLE_TEMPLATE
                LOCATION_MISMATCH -> Known.LOCATION_MISMATCH
                OBJECT_LOCK -> Known.OBJECT_LOCK
                OTHER -> Known.OTHER
                OWNERSHIP_ENFORCED -> Known.OWNERSHIP_ENFORCED
                PROBE_FAILED -> Known.PROBE_FAILED
                PROBE_OBJECT -> Known.PROBE_OBJECT
                PROBE_STORAGE_CLASS -> Known.PROBE_STORAGE_CLASS
                PUBLIC_ACCESS_BLOCK -> Known.PUBLIC_ACCESS_BLOCK
                REGION -> Known.REGION
                REPLICATION -> Known.REPLICATION
                REQUEST_METRICS -> Known.REQUEST_METRICS
                REQUEST_PAYMENT -> Known.REQUEST_PAYMENT
                TRANSCRIPTS_SUPPORT -> Known.TRANSCRIPTS_SUPPORT
                TRUST_INGEST_ROLE -> Known.TRUST_INGEST_ROLE
                TRUST_MAINTENANCE_ROLE -> Known.TRUST_MAINTENANCE_ROLE
                TRUST_MEDIA_ERASURE_ROLE -> Known.TRUST_MEDIA_ERASURE_ROLE
                TRUST_READ_ROLE -> Known.TRUST_READ_ROLE
                TRUST_TRANSCRIPTS_ROLE -> Known.TRUST_TRANSCRIPTS_ROLE
                TRUST_TRANSCRIPTS_VERIFY_ROLE -> Known.TRUST_TRANSCRIPTS_VERIFY_ROLE
                TRUST_VERIFY_ROLE -> Known.TRUST_VERIFY_ROLE
                VERSIONING -> Known.VERSIONING
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
     * The part of your access grant that the check is about, such as `ingest`: on AWS, one of the
     * IAM roles your setup created; on Google Cloud, the grant to one of Anthropic's service
     * accounts. `null` when the check is not about one. New names may be added. The value is a name
     * with no data of its own.
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

            @JvmField val MAINTENANCE = Role(JsonField.of("maintenance"))

            @JvmField val MEDIA_ERASURE = Role(JsonField.of("media_erasure"))

            @JvmField val READ = Role(JsonField.of("read"))

            @JvmField val TRANSCRIPTS = Role(JsonField.of("transcripts"))

            @JvmField val TRANSCRIPTS_VERIFY = Role(JsonField.of("transcripts_verify"))

            @JvmField val VERIFY = Role(JsonField.of("verify"))

            @JvmStatic
            fun of(value: String): Role =
                // Intern known values so `==` works
                when (value) {
                    "ingest" -> INGEST
                    "maintenance" -> MAINTENANCE
                    "media_erasure" -> MEDIA_ERASURE
                    "read" -> READ
                    "transcripts" -> TRANSCRIPTS
                    "transcripts_verify" -> TRANSCRIPTS_VERIFY
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
            MAINTENANCE,
            MEDIA_ERASURE,
            READ,
            TRANSCRIPTS,
            TRANSCRIPTS_VERIFY,
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
            MAINTENANCE,
            MEDIA_ERASURE,
            READ,
            TRANSCRIPTS,
            TRANSCRIPTS_VERIFY,
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
                MAINTENANCE -> Value.MAINTENANCE
                MEDIA_ERASURE -> Value.MEDIA_ERASURE
                READ -> Value.READ
                TRANSCRIPTS -> Value.TRANSCRIPTS
                TRANSCRIPTS_VERIFY -> Value.TRANSCRIPTS_VERIFY
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
                MAINTENANCE -> Known.MAINTENANCE
                MEDIA_ERASURE -> Known.MEDIA_ERASURE
                READ -> Known.READ
                TRANSCRIPTS -> Known.TRANSCRIPTS
                TRANSCRIPTS_VERIFY -> Known.TRANSCRIPTS_VERIFY
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

        return other is BetaExternalStorageFailedCheck &&
            check == other.check &&
            message == other.message &&
            role == other.role &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy { Objects.hash(check, message, role, additionalProperties) }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaExternalStorageFailedCheck{check=$check, message=$message, role=$role, additionalProperties=$additionalProperties}"
}
