package com.anthropic.models.beta.organization.plugins

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

class BetaPluginContentScan
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val assessment: JsonField<Assessment>,
    private val reason: JsonField<String>,
    private val status: JsonField<Status>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("assessment")
        @ExcludeMissing
        assessment: JsonField<Assessment> = JsonMissing.of(),
        @JsonProperty("reason") @ExcludeMissing reason: JsonField<String> = JsonMissing.of(),
        @JsonProperty("status") @ExcludeMissing status: JsonField<Status> = JsonMissing.of(),
    ) : this(assessment, reason, status, mutableMapOf())

    /**
     * The scan's verdict; set only when `status` is `completed`.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun assessment(): Optional<Assessment> = assessment.getOptional("assessment")

    /**
     * The primary mechanism behind a `warn` or `fail`, such as `credential-exposure` or
     * `guardrail-tampering`; a mechanism this API does not yet name reads as `other`. Null on a
     * `pass`, whenever `assessment` is null, and when no mechanism is reported for the verdict.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun reason(): Optional<String> = reason.getOptional("reason")

    /**
     * `processing` while a scan runs, `completed` when it ran to completion, `errored` when it
     * could not run or its outcome cannot be read.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun status(): Status = status.getRequired("status")

    /**
     * Returns the raw JSON value of [assessment].
     *
     * Unlike [assessment], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("assessment")
    @ExcludeMissing
    fun _assessment(): JsonField<Assessment> = assessment

    /**
     * Returns the raw JSON value of [reason].
     *
     * Unlike [reason], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("reason") @ExcludeMissing fun _reason(): JsonField<String> = reason

    /**
     * Returns the raw JSON value of [status].
     *
     * Unlike [status], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("status") @ExcludeMissing fun _status(): JsonField<Status> = status

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
         * Returns a mutable builder for constructing an instance of [BetaPluginContentScan].
         *
         * The following fields are required:
         * ```java
         * .assessment()
         * .reason()
         * .status()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaPluginContentScan]. */
    class Builder internal constructor() {

        private var assessment: JsonField<Assessment>? = null
        private var reason: JsonField<String>? = null
        private var status: JsonField<Status>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaPluginContentScan: BetaPluginContentScan) = apply {
            assessment = betaPluginContentScan.assessment
            reason = betaPluginContentScan.reason
            status = betaPluginContentScan.status
            additionalProperties = betaPluginContentScan.additionalProperties.toMutableMap()
        }

        /** The scan's verdict; set only when `status` is `completed`. */
        fun assessment(assessment: Assessment?) = assessment(JsonField.ofNullable(assessment))

        /** Alias for calling [Builder.assessment] with `assessment.orElse(null)`. */
        fun assessment(assessment: Optional<Assessment>) = assessment(assessment.getOrNull())

        /**
         * Sets [Builder.assessment] to an arbitrary JSON value.
         *
         * You should usually call [Builder.assessment] with a well-typed [Assessment] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun assessment(assessment: JsonField<Assessment>) = apply { this.assessment = assessment }

        /**
         * The primary mechanism behind a `warn` or `fail`, such as `credential-exposure` or
         * `guardrail-tampering`; a mechanism this API does not yet name reads as `other`. Null on a
         * `pass`, whenever `assessment` is null, and when no mechanism is reported for the verdict.
         */
        fun reason(reason: String?) = reason(JsonField.ofNullable(reason))

        /** Alias for calling [Builder.reason] with `reason.orElse(null)`. */
        fun reason(reason: Optional<String>) = reason(reason.getOrNull())

        /**
         * Sets [Builder.reason] to an arbitrary JSON value.
         *
         * You should usually call [Builder.reason] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun reason(reason: JsonField<String>) = apply { this.reason = reason }

        /**
         * `processing` while a scan runs, `completed` when it ran to completion, `errored` when it
         * could not run or its outcome cannot be read.
         */
        fun status(status: Status) = status(JsonField.of(status))

        /**
         * Sets [Builder.status] to an arbitrary JSON value.
         *
         * You should usually call [Builder.status] with a well-typed [Status] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun status(status: JsonField<Status>) = apply { this.status = status }

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
         * Returns an immutable instance of [BetaPluginContentScan].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .assessment()
         * .reason()
         * .status()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaPluginContentScan =
            BetaPluginContentScan(
                checkRequired("assessment", assessment),
                checkRequired("reason", reason),
                checkRequired("status", status),
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
    fun validate(): BetaPluginContentScan = apply {
        if (validated) {
            return@apply
        }

        assessment().ifPresent { it.validate() }
        reason()
        status().validate()
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
        (assessment.asKnown().getOrNull()?.validity() ?: 0) +
            (if (reason.asKnown().isPresent) 1 else 0) +
            (status.asKnown().getOrNull()?.validity() ?: 0)

    /** The scan's verdict; set only when `status` is `completed`. */
    class Assessment private constructor(private val value: JsonField<String>) : Enum {

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

            @JvmField val FAIL = Assessment(JsonField.of("fail"))

            @JvmField val PASS = Assessment(JsonField.of("pass"))

            @JvmField val UNKNOWN = Assessment(JsonField.of("unknown"))

            @JvmField val WARN = Assessment(JsonField.of("warn"))

            @JvmStatic
            fun of(value: String): Assessment =
                // Intern known values so `==` works
                when (value) {
                    "fail" -> FAIL
                    "pass" -> PASS
                    "unknown" -> UNKNOWN
                    "warn" -> WARN
                    else -> Assessment(JsonField.of(value))
                }

            @JsonCreator
            @JvmStatic
            fun of(value: JsonField<String>): Assessment =
                value.asString().getOrNull()?.let { of(it) } ?: Assessment(value)
        }

        /** An enum containing [Assessment]'s known values. */
        enum class Known {
            FAIL,
            PASS,
            UNKNOWN,
            WARN,
        }

        /**
         * An enum containing [Assessment]'s known values, as well as an [_UNKNOWN] member.
         *
         * An instance of [Assessment] can contain an unknown value in a couple of cases:
         * - It was deserialized from data that doesn't match any known member. For example, if the
         *   SDK is on an older version than the API, then the API may respond with new members that
         *   the SDK is unaware of.
         * - It was constructed with an arbitrary value using the [of] method.
         */
        enum class Value {
            FAIL,
            PASS,
            UNKNOWN,
            WARN,
            /**
             * An enum member indicating that [Assessment] was instantiated with an unknown value.
             */
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
                FAIL -> Value.FAIL
                PASS -> Value.PASS
                UNKNOWN -> Value.UNKNOWN
                WARN -> Value.WARN
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
                FAIL -> Known.FAIL
                PASS -> Known.PASS
                UNKNOWN -> Known.UNKNOWN
                WARN -> Known.WARN
                else -> throw AnthropicInvalidDataException("Unknown Assessment: $value")
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
        fun validate(): Assessment = apply {
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

            return other is Assessment && value == other.value
        }

        override fun hashCode() = value.hashCode()

        override fun toString() = value.toString()
    }

    /**
     * `processing` while a scan runs, `completed` when it ran to completion, `errored` when it
     * could not run or its outcome cannot be read.
     */
    class Status private constructor(private val value: JsonField<String>) : Enum {

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

            @JvmField val COMPLETED = Status(JsonField.of("completed"))

            @JvmField val ERRORED = Status(JsonField.of("errored"))

            @JvmField val PROCESSING = Status(JsonField.of("processing"))

            @JvmStatic
            fun of(value: String): Status =
                // Intern known values so `==` works
                when (value) {
                    "completed" -> COMPLETED
                    "errored" -> ERRORED
                    "processing" -> PROCESSING
                    else -> Status(JsonField.of(value))
                }

            @JsonCreator
            @JvmStatic
            fun of(value: JsonField<String>): Status =
                value.asString().getOrNull()?.let { of(it) } ?: Status(value)
        }

        /** An enum containing [Status]'s known values. */
        enum class Known {
            COMPLETED,
            ERRORED,
            PROCESSING,
        }

        /**
         * An enum containing [Status]'s known values, as well as an [_UNKNOWN] member.
         *
         * An instance of [Status] can contain an unknown value in a couple of cases:
         * - It was deserialized from data that doesn't match any known member. For example, if the
         *   SDK is on an older version than the API, then the API may respond with new members that
         *   the SDK is unaware of.
         * - It was constructed with an arbitrary value using the [of] method.
         */
        enum class Value {
            COMPLETED,
            ERRORED,
            PROCESSING,
            /** An enum member indicating that [Status] was instantiated with an unknown value. */
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
                COMPLETED -> Value.COMPLETED
                ERRORED -> Value.ERRORED
                PROCESSING -> Value.PROCESSING
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
                COMPLETED -> Known.COMPLETED
                ERRORED -> Known.ERRORED
                PROCESSING -> Known.PROCESSING
                else -> throw AnthropicInvalidDataException("Unknown Status: $value")
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
        fun validate(): Status = apply {
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

            return other is Status && value == other.value
        }

        override fun hashCode() = value.hashCode()

        override fun toString() = value.toString()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaPluginContentScan &&
            assessment == other.assessment &&
            reason == other.reason &&
            status == other.status &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(assessment, reason, status, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaPluginContentScan{assessment=$assessment, reason=$reason, status=$status, additionalProperties=$additionalProperties}"
}
