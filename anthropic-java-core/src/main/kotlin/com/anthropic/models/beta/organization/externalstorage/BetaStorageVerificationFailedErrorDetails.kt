package com.anthropic.models.beta.organization.externalstorage

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
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/**
 * The External Storage failed verification, so the request changed nothing. `failed_checks` lists
 * what to fix.
 */
class BetaStorageVerificationFailedErrorDetails
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val errorCode: JsonValue,
    private val failedChecks: JsonField<List<BetaExternalStorageFailedCheck>>,
    private val field: JsonField<String>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("error_code") @ExcludeMissing errorCode: JsonValue = JsonMissing.of(),
        @JsonProperty("failed_checks")
        @ExcludeMissing
        failedChecks: JsonField<List<BetaExternalStorageFailedCheck>> = JsonMissing.of(),
        @JsonProperty("field") @ExcludeMissing field: JsonField<String> = JsonMissing.of(),
    ) : this(errorCode, failedChecks, field, mutableMapOf())

    /**
     * Expected to always return the following:
     * ```java
     * JsonValue.from("storage_verification_failed")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("error_code") @ExcludeMissing fun _errorCode(): JsonValue = errorCode

    /**
     * One entry for each check that failed.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun failedChecks(): List<BetaExternalStorageFailedCheck> =
        failedChecks.getRequired("failed_checks")

    /**
     * The request field at fault. Sent when the refused request is a Workspace's create or update.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun field(): Optional<String> = field.getOptional("field")

    /**
     * Returns the raw JSON value of [failedChecks].
     *
     * Unlike [failedChecks], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("failed_checks")
    @ExcludeMissing
    fun _failedChecks(): JsonField<List<BetaExternalStorageFailedCheck>> = failedChecks

    /**
     * Returns the raw JSON value of [field].
     *
     * Unlike [field], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("field") @ExcludeMissing fun _field(): JsonField<String> = field

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
         * [BetaStorageVerificationFailedErrorDetails].
         *
         * The following fields are required:
         * ```java
         * .failedChecks()
         * ```
         */
        @JvmStatic fun builder() = Builder()

        /**
         * Returns an immutable instance of [BetaStorageVerificationFailedErrorDetails] with the
         * required [failedChecks] set to the given value.
         */
        @JvmStatic
        fun of(failedChecks: List<BetaExternalStorageFailedCheck>) =
            builder().failedChecks(failedChecks).build()
    }

    /** A builder for [BetaStorageVerificationFailedErrorDetails]. */
    class Builder internal constructor() {

        private var errorCode: JsonValue = JsonValue.from("storage_verification_failed")
        private var failedChecks: JsonField<MutableList<BetaExternalStorageFailedCheck>>? = null
        private var field: JsonField<String> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(
            betaStorageVerificationFailedErrorDetails: BetaStorageVerificationFailedErrorDetails
        ) = apply {
            errorCode = betaStorageVerificationFailedErrorDetails.errorCode
            failedChecks =
                betaStorageVerificationFailedErrorDetails.failedChecks
                    .map { it.toMutableList() }
                    .takeUnless { it.isMissing() }
            field = betaStorageVerificationFailedErrorDetails.field
            additionalProperties =
                betaStorageVerificationFailedErrorDetails.additionalProperties.toMutableMap()
        }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("storage_verification_failed")
         * ```
         *
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun errorCode(errorCode: JsonValue) = apply { this.errorCode = errorCode }

        /** One entry for each check that failed. */
        fun failedChecks(failedChecks: List<BetaExternalStorageFailedCheck>) =
            failedChecks(JsonField.of(failedChecks))

        /**
         * Sets [Builder.failedChecks] to an arbitrary JSON value.
         *
         * You should usually call [Builder.failedChecks] with a well-typed
         * `List<BetaExternalStorageFailedCheck>` value instead. This method is primarily for
         * setting the field to an undocumented or not yet supported value.
         */
        fun failedChecks(failedChecks: JsonField<List<BetaExternalStorageFailedCheck>>) = apply {
            this.failedChecks = failedChecks.map { it.toMutableList() }
        }

        /**
         * Adds a single [BetaExternalStorageFailedCheck] to [failedChecks].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addFailedCheck(failedCheck: BetaExternalStorageFailedCheck) = apply {
            failedChecks =
                (failedChecks ?: JsonField.of(mutableListOf())).also {
                    checkKnown("failedChecks", it).add(failedCheck)
                }
        }

        /**
         * The request field at fault. Sent when the refused request is a Workspace's create or
         * update.
         */
        fun field(field: String?) = field(JsonField.ofNullable(field))

        /** Alias for calling [Builder.field] with `field.orElse(null)`. */
        fun field(field: Optional<String>) = field(field.getOrNull())

        /**
         * Sets [Builder.field] to an arbitrary JSON value.
         *
         * You should usually call [Builder.field] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun field(field: JsonField<String>) = apply { this.field = field }

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
         * Returns an immutable instance of [BetaStorageVerificationFailedErrorDetails].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .failedChecks()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaStorageVerificationFailedErrorDetails =
            BetaStorageVerificationFailedErrorDetails(
                errorCode,
                checkRequired("failedChecks", failedChecks).map { it.toImmutable() },
                field,
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
    fun validate(): BetaStorageVerificationFailedErrorDetails = apply {
        if (validated) {
            return@apply
        }

        _errorCode().let {
            if (it != JsonValue.from("storage_verification_failed")) {
                throw AnthropicInvalidDataException("'errorCode' is invalid, received $it")
            }
        }
        failedChecks().forEach { it.validate() }
        field()
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
        errorCode.let { if (it == JsonValue.from("storage_verification_failed")) 1 else 0 } +
            (failedChecks.asKnown().getOrNull()?.sumOf { it.validity().toInt() } ?: 0) +
            (if (field.asKnown().isPresent) 1 else 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaStorageVerificationFailedErrorDetails &&
            errorCode == other.errorCode &&
            failedChecks == other.failedChecks &&
            field == other.field &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(errorCode, failedChecks, field, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaStorageVerificationFailedErrorDetails{errorCode=$errorCode, failedChecks=$failedChecks, field=$field, additionalProperties=$additionalProperties}"
}
