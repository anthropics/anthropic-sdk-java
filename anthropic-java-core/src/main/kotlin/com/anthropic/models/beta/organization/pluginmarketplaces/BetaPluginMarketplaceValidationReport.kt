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
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/**
 * The outcome of validating plugin marketplace content: a report, not a stored object, so nothing
 * in it can be retrieved afterwards.
 */
class BetaPluginMarketplaceValidationReport
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val commitSha: JsonField<String>,
    private val manifestError: JsonField<String>,
    private val manifestErrorCode: JsonField<String>,
    private val pluginErrors: JsonField<List<BetaPluginMarketplaceValidationPluginError>>,
    private val pluginWarnings: JsonField<List<BetaPluginMarketplaceValidationPluginWarnings>>,
    private val ref: JsonField<String>,
    private val totalPluginCount: JsonField<Long>,
    private val type: JsonValue,
    private val valid: JsonField<Boolean>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("commit_sha") @ExcludeMissing commitSha: JsonField<String> = JsonMissing.of(),
        @JsonProperty("manifest_error")
        @ExcludeMissing
        manifestError: JsonField<String> = JsonMissing.of(),
        @JsonProperty("manifest_error_code")
        @ExcludeMissing
        manifestErrorCode: JsonField<String> = JsonMissing.of(),
        @JsonProperty("plugin_errors")
        @ExcludeMissing
        pluginErrors: JsonField<List<BetaPluginMarketplaceValidationPluginError>> =
            JsonMissing.of(),
        @JsonProperty("plugin_warnings")
        @ExcludeMissing
        pluginWarnings: JsonField<List<BetaPluginMarketplaceValidationPluginWarnings>> =
            JsonMissing.of(),
        @JsonProperty("ref") @ExcludeMissing ref: JsonField<String> = JsonMissing.of(),
        @JsonProperty("total_plugin_count")
        @ExcludeMissing
        totalPluginCount: JsonField<Long> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
        @JsonProperty("valid") @ExcludeMissing valid: JsonField<Boolean> = JsonMissing.of(),
    ) : this(
        commitSha,
        manifestError,
        manifestErrorCode,
        pluginErrors,
        pluginWarnings,
        ref,
        totalPluginCount,
        type,
        valid,
        mutableMapOf(),
    )

    /**
     * The full SHA of the commit that was validated: for a repository, the commit that was read;
     * for an uploaded archive, the commit recorded in the archive's comment (as a Git host's
     * download writes it; not verified), else null.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun commitSha(): Optional<String> = commitSha.getOptional("commit_sha")

    /**
     * Set when nothing could be validated: the repository or archive could not be read, or
     * marketplace.json is missing, malformed or over a limit. Null otherwise.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun manifestError(): Optional<String> = manifestError.getOptional("manifest_error")

    /**
     * A stable identifier for `manifest_error`; null when that is.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun manifestErrorCode(): Optional<String> = manifestErrorCode.getOptional("manifest_error_code")

    /**
     * One entry per plugin a synchronization would skip entirely, keyed by the plugin's name in
     * marketplace.json.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun pluginErrors(): List<BetaPluginMarketplaceValidationPluginError> =
        pluginErrors.getRequired("plugin_errors")

    /**
     * One entry per plugin that would synchronize with some of its contents left out, keyed by the
     * plugin's name in marketplace.json.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun pluginWarnings(): List<BetaPluginMarketplaceValidationPluginWarnings> =
        pluginWarnings.getRequired("plugin_warnings")

    /**
     * For a repository, the branch that was read by name: the one requested, or else the branch a
     * synchronization of this repository is set to read. Null when no branch is named or set and
     * the repository's default branch was read, for a request by commit SHA, and for an uploaded
     * archive.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun ref(): Optional<String> = ref.getOptional("ref")

    /**
     * How many plugins marketplace.json declares; 0 when it could not be read.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun totalPluginCount(): Long = totalPluginCount.getRequired("total_plugin_count")

    /**
     * Always `plugin_marketplace_validation_report`.
     *
     * Expected to always return the following:
     * ```java
     * JsonValue.from("plugin_marketplace_validation_report")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    /**
     * True when marketplace.json is well-formed and no plugin would be skipped; warnings never make
     * it false.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun valid(): Boolean = valid.getRequired("valid")

    /**
     * Returns the raw JSON value of [commitSha].
     *
     * Unlike [commitSha], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("commit_sha") @ExcludeMissing fun _commitSha(): JsonField<String> = commitSha

    /**
     * Returns the raw JSON value of [manifestError].
     *
     * Unlike [manifestError], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("manifest_error")
    @ExcludeMissing
    fun _manifestError(): JsonField<String> = manifestError

    /**
     * Returns the raw JSON value of [manifestErrorCode].
     *
     * Unlike [manifestErrorCode], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("manifest_error_code")
    @ExcludeMissing
    fun _manifestErrorCode(): JsonField<String> = manifestErrorCode

    /**
     * Returns the raw JSON value of [pluginErrors].
     *
     * Unlike [pluginErrors], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("plugin_errors")
    @ExcludeMissing
    fun _pluginErrors(): JsonField<List<BetaPluginMarketplaceValidationPluginError>> = pluginErrors

    /**
     * Returns the raw JSON value of [pluginWarnings].
     *
     * Unlike [pluginWarnings], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("plugin_warnings")
    @ExcludeMissing
    fun _pluginWarnings(): JsonField<List<BetaPluginMarketplaceValidationPluginWarnings>> =
        pluginWarnings

    /**
     * Returns the raw JSON value of [ref].
     *
     * Unlike [ref], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("ref") @ExcludeMissing fun _ref(): JsonField<String> = ref

    /**
     * Returns the raw JSON value of [totalPluginCount].
     *
     * Unlike [totalPluginCount], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("total_plugin_count")
    @ExcludeMissing
    fun _totalPluginCount(): JsonField<Long> = totalPluginCount

    /**
     * Returns the raw JSON value of [valid].
     *
     * Unlike [valid], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("valid") @ExcludeMissing fun _valid(): JsonField<Boolean> = valid

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
         * [BetaPluginMarketplaceValidationReport].
         *
         * The following fields are required:
         * ```java
         * .commitSha()
         * .manifestError()
         * .manifestErrorCode()
         * .pluginErrors()
         * .pluginWarnings()
         * .ref()
         * .totalPluginCount()
         * .valid()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaPluginMarketplaceValidationReport]. */
    class Builder internal constructor() {

        private var commitSha: JsonField<String>? = null
        private var manifestError: JsonField<String>? = null
        private var manifestErrorCode: JsonField<String>? = null
        private var pluginErrors:
            JsonField<MutableList<BetaPluginMarketplaceValidationPluginError>>? =
            null
        private var pluginWarnings:
            JsonField<MutableList<BetaPluginMarketplaceValidationPluginWarnings>>? =
            null
        private var ref: JsonField<String>? = null
        private var totalPluginCount: JsonField<Long>? = null
        private var type: JsonValue = JsonValue.from("plugin_marketplace_validation_report")
        private var valid: JsonField<Boolean>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(
            betaPluginMarketplaceValidationReport: BetaPluginMarketplaceValidationReport
        ) = apply {
            commitSha = betaPluginMarketplaceValidationReport.commitSha
            manifestError = betaPluginMarketplaceValidationReport.manifestError
            manifestErrorCode = betaPluginMarketplaceValidationReport.manifestErrorCode
            pluginErrors =
                betaPluginMarketplaceValidationReport.pluginErrors
                    .map { it.toMutableList() }
                    .takeUnless { it.isMissing() }
            pluginWarnings =
                betaPluginMarketplaceValidationReport.pluginWarnings
                    .map { it.toMutableList() }
                    .takeUnless { it.isMissing() }
            ref = betaPluginMarketplaceValidationReport.ref
            totalPluginCount = betaPluginMarketplaceValidationReport.totalPluginCount
            type = betaPluginMarketplaceValidationReport.type
            valid = betaPluginMarketplaceValidationReport.valid
            additionalProperties =
                betaPluginMarketplaceValidationReport.additionalProperties.toMutableMap()
        }

        /**
         * The full SHA of the commit that was validated: for a repository, the commit that was
         * read; for an uploaded archive, the commit recorded in the archive's comment (as a Git
         * host's download writes it; not verified), else null.
         */
        fun commitSha(commitSha: String?) = commitSha(JsonField.ofNullable(commitSha))

        /** Alias for calling [Builder.commitSha] with `commitSha.orElse(null)`. */
        fun commitSha(commitSha: Optional<String>) = commitSha(commitSha.getOrNull())

        /**
         * Sets [Builder.commitSha] to an arbitrary JSON value.
         *
         * You should usually call [Builder.commitSha] with a well-typed [String] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun commitSha(commitSha: JsonField<String>) = apply { this.commitSha = commitSha }

        /**
         * Set when nothing could be validated: the repository or archive could not be read, or
         * marketplace.json is missing, malformed or over a limit. Null otherwise.
         */
        fun manifestError(manifestError: String?) =
            manifestError(JsonField.ofNullable(manifestError))

        /** Alias for calling [Builder.manifestError] with `manifestError.orElse(null)`. */
        fun manifestError(manifestError: Optional<String>) =
            manifestError(manifestError.getOrNull())

        /**
         * Sets [Builder.manifestError] to an arbitrary JSON value.
         *
         * You should usually call [Builder.manifestError] with a well-typed [String] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun manifestError(manifestError: JsonField<String>) = apply {
            this.manifestError = manifestError
        }

        /** A stable identifier for `manifest_error`; null when that is. */
        fun manifestErrorCode(manifestErrorCode: String?) =
            manifestErrorCode(JsonField.ofNullable(manifestErrorCode))

        /** Alias for calling [Builder.manifestErrorCode] with `manifestErrorCode.orElse(null)`. */
        fun manifestErrorCode(manifestErrorCode: Optional<String>) =
            manifestErrorCode(manifestErrorCode.getOrNull())

        /**
         * Sets [Builder.manifestErrorCode] to an arbitrary JSON value.
         *
         * You should usually call [Builder.manifestErrorCode] with a well-typed [String] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun manifestErrorCode(manifestErrorCode: JsonField<String>) = apply {
            this.manifestErrorCode = manifestErrorCode
        }

        /**
         * One entry per plugin a synchronization would skip entirely, keyed by the plugin's name in
         * marketplace.json.
         */
        fun pluginErrors(pluginErrors: List<BetaPluginMarketplaceValidationPluginError>) =
            pluginErrors(JsonField.of(pluginErrors))

        /**
         * Sets [Builder.pluginErrors] to an arbitrary JSON value.
         *
         * You should usually call [Builder.pluginErrors] with a well-typed
         * `List<BetaPluginMarketplaceValidationPluginError>` value instead. This method is
         * primarily for setting the field to an undocumented or not yet supported value.
         */
        fun pluginErrors(
            pluginErrors: JsonField<List<BetaPluginMarketplaceValidationPluginError>>
        ) = apply { this.pluginErrors = pluginErrors.map { it.toMutableList() } }

        /**
         * Adds a single [BetaPluginMarketplaceValidationPluginError] to [pluginErrors].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addPluginError(pluginError: BetaPluginMarketplaceValidationPluginError) = apply {
            pluginErrors =
                (pluginErrors ?: JsonField.of(mutableListOf())).also {
                    checkKnown("pluginErrors", it).add(pluginError)
                }
        }

        /**
         * One entry per plugin that would synchronize with some of its contents left out, keyed by
         * the plugin's name in marketplace.json.
         */
        fun pluginWarnings(pluginWarnings: List<BetaPluginMarketplaceValidationPluginWarnings>) =
            pluginWarnings(JsonField.of(pluginWarnings))

        /**
         * Sets [Builder.pluginWarnings] to an arbitrary JSON value.
         *
         * You should usually call [Builder.pluginWarnings] with a well-typed
         * `List<BetaPluginMarketplaceValidationPluginWarnings>` value instead. This method is
         * primarily for setting the field to an undocumented or not yet supported value.
         */
        fun pluginWarnings(
            pluginWarnings: JsonField<List<BetaPluginMarketplaceValidationPluginWarnings>>
        ) = apply { this.pluginWarnings = pluginWarnings.map { it.toMutableList() } }

        /**
         * Adds a single [BetaPluginMarketplaceValidationPluginWarnings] to [pluginWarnings].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addPluginWarning(pluginWarning: BetaPluginMarketplaceValidationPluginWarnings) = apply {
            pluginWarnings =
                (pluginWarnings ?: JsonField.of(mutableListOf())).also {
                    checkKnown("pluginWarnings", it).add(pluginWarning)
                }
        }

        /**
         * For a repository, the branch that was read by name: the one requested, or else the branch
         * a synchronization of this repository is set to read. Null when no branch is named or set
         * and the repository's default branch was read, for a request by commit SHA, and for an
         * uploaded archive.
         */
        fun ref(ref: String?) = ref(JsonField.ofNullable(ref))

        /** Alias for calling [Builder.ref] with `ref.orElse(null)`. */
        fun ref(ref: Optional<String>) = ref(ref.getOrNull())

        /**
         * Sets [Builder.ref] to an arbitrary JSON value.
         *
         * You should usually call [Builder.ref] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun ref(ref: JsonField<String>) = apply { this.ref = ref }

        /** How many plugins marketplace.json declares; 0 when it could not be read. */
        fun totalPluginCount(totalPluginCount: Long) =
            totalPluginCount(JsonField.of(totalPluginCount))

        /**
         * Sets [Builder.totalPluginCount] to an arbitrary JSON value.
         *
         * You should usually call [Builder.totalPluginCount] with a well-typed [Long] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun totalPluginCount(totalPluginCount: JsonField<Long>) = apply {
            this.totalPluginCount = totalPluginCount
        }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("plugin_marketplace_validation_report")
         * ```
         *
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun type(type: JsonValue) = apply { this.type = type }

        /**
         * True when marketplace.json is well-formed and no plugin would be skipped; warnings never
         * make it false.
         */
        fun valid(valid: Boolean) = valid(JsonField.of(valid))

        /**
         * Sets [Builder.valid] to an arbitrary JSON value.
         *
         * You should usually call [Builder.valid] with a well-typed [Boolean] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun valid(valid: JsonField<Boolean>) = apply { this.valid = valid }

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
         * Returns an immutable instance of [BetaPluginMarketplaceValidationReport].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .commitSha()
         * .manifestError()
         * .manifestErrorCode()
         * .pluginErrors()
         * .pluginWarnings()
         * .ref()
         * .totalPluginCount()
         * .valid()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaPluginMarketplaceValidationReport =
            BetaPluginMarketplaceValidationReport(
                checkRequired("commitSha", commitSha),
                checkRequired("manifestError", manifestError),
                checkRequired("manifestErrorCode", manifestErrorCode),
                checkRequired("pluginErrors", pluginErrors).map { it.toImmutable() },
                checkRequired("pluginWarnings", pluginWarnings).map { it.toImmutable() },
                checkRequired("ref", ref),
                checkRequired("totalPluginCount", totalPluginCount),
                type,
                checkRequired("valid", valid),
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
    fun validate(): BetaPluginMarketplaceValidationReport = apply {
        if (validated) {
            return@apply
        }

        commitSha()
        manifestError()
        manifestErrorCode()
        pluginErrors().forEach { it.validate() }
        pluginWarnings().forEach { it.validate() }
        ref()
        totalPluginCount()
        _type().let {
            if (it != JsonValue.from("plugin_marketplace_validation_report")) {
                throw AnthropicInvalidDataException("'type' is invalid, received $it")
            }
        }
        valid()
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
        (if (commitSha.asKnown().isPresent) 1 else 0) +
            (if (manifestError.asKnown().isPresent) 1 else 0) +
            (if (manifestErrorCode.asKnown().isPresent) 1 else 0) +
            (pluginErrors.asKnown().getOrNull()?.sumOf { it.validity().toInt() } ?: 0) +
            (pluginWarnings.asKnown().getOrNull()?.sumOf { it.validity().toInt() } ?: 0) +
            (if (ref.asKnown().isPresent) 1 else 0) +
            (if (totalPluginCount.asKnown().isPresent) 1 else 0) +
            type.let {
                if (it == JsonValue.from("plugin_marketplace_validation_report")) 1 else 0
            } +
            (if (valid.asKnown().isPresent) 1 else 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaPluginMarketplaceValidationReport &&
            commitSha == other.commitSha &&
            manifestError == other.manifestError &&
            manifestErrorCode == other.manifestErrorCode &&
            pluginErrors == other.pluginErrors &&
            pluginWarnings == other.pluginWarnings &&
            ref == other.ref &&
            totalPluginCount == other.totalPluginCount &&
            type == other.type &&
            valid == other.valid &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(
            commitSha,
            manifestError,
            manifestErrorCode,
            pluginErrors,
            pluginWarnings,
            ref,
            totalPluginCount,
            type,
            valid,
            additionalProperties,
        )
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaPluginMarketplaceValidationReport{commitSha=$commitSha, manifestError=$manifestError, manifestErrorCode=$manifestErrorCode, pluginErrors=$pluginErrors, pluginWarnings=$pluginWarnings, ref=$ref, totalPluginCount=$totalPluginCount, type=$type, valid=$valid, additionalProperties=$additionalProperties}"
}
