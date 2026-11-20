package com.anthropic.models.beta.organization.workspaces

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
import kotlin.jvm.optionals.getOrNull

/**
 * Another request changed the Workspace, so this request left the setting named in `field` as it
 * is. The error's `message` says which of this request's other changes are in place. Read the
 * Workspace and send the request again if you still want the change.
 */
class BetaWorkspaceChangedConcurrentlyErrorDetails
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val errorCode: JsonValue,
    private val field: JsonField<BetaWorkspaceChangedConcurrentlyField>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("error_code") @ExcludeMissing errorCode: JsonValue = JsonMissing.of(),
        @JsonProperty("field")
        @ExcludeMissing
        field: JsonField<BetaWorkspaceChangedConcurrentlyField> = JsonMissing.of(),
    ) : this(errorCode, field, mutableMapOf())

    /**
     * Expected to always return the following:
     * ```java
     * JsonValue.from("workspace_changed_concurrently")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("error_code") @ExcludeMissing fun _errorCode(): JsonValue = errorCode

    /**
     * The request field whose change was refused. Other values may be returned. The value is a name
     * with no data of its own.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun field(): BetaWorkspaceChangedConcurrentlyField = field.getRequired("field")

    /**
     * Returns the raw JSON value of [field].
     *
     * Unlike [field], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("field")
    @ExcludeMissing
    fun _field(): JsonField<BetaWorkspaceChangedConcurrentlyField> = field

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
         * [BetaWorkspaceChangedConcurrentlyErrorDetails].
         *
         * The following fields are required:
         * ```java
         * .field()
         * ```
         */
        @JvmStatic fun builder() = Builder()

        /**
         * Returns an immutable instance of [BetaWorkspaceChangedConcurrentlyErrorDetails] with the
         * required [field] set to the given value.
         */
        @JvmStatic
        fun of(field: BetaWorkspaceChangedConcurrentlyField) = builder().field(field).build()
    }

    /** A builder for [BetaWorkspaceChangedConcurrentlyErrorDetails]. */
    class Builder internal constructor() {

        private var errorCode: JsonValue = JsonValue.from("workspace_changed_concurrently")
        private var field: JsonField<BetaWorkspaceChangedConcurrentlyField>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(
            betaWorkspaceChangedConcurrentlyErrorDetails:
                BetaWorkspaceChangedConcurrentlyErrorDetails
        ) = apply {
            errorCode = betaWorkspaceChangedConcurrentlyErrorDetails.errorCode
            field = betaWorkspaceChangedConcurrentlyErrorDetails.field
            additionalProperties =
                betaWorkspaceChangedConcurrentlyErrorDetails.additionalProperties.toMutableMap()
        }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("workspace_changed_concurrently")
         * ```
         *
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun errorCode(errorCode: JsonValue) = apply { this.errorCode = errorCode }

        /**
         * The request field whose change was refused. Other values may be returned. The value is a
         * name with no data of its own.
         */
        fun field(field: BetaWorkspaceChangedConcurrentlyField) = field(JsonField.of(field))

        /**
         * Sets [Builder.field] to an arbitrary JSON value.
         *
         * You should usually call [Builder.field] with a well-typed
         * [BetaWorkspaceChangedConcurrentlyField] value instead. This method is primarily for
         * setting the field to an undocumented or not yet supported value.
         */
        fun field(field: JsonField<BetaWorkspaceChangedConcurrentlyField>) = apply {
            this.field = field
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
         * Returns an immutable instance of [BetaWorkspaceChangedConcurrentlyErrorDetails].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .field()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaWorkspaceChangedConcurrentlyErrorDetails =
            BetaWorkspaceChangedConcurrentlyErrorDetails(
                errorCode,
                checkRequired("field", field),
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
    fun validate(): BetaWorkspaceChangedConcurrentlyErrorDetails = apply {
        if (validated) {
            return@apply
        }

        _errorCode().let {
            if (it != JsonValue.from("workspace_changed_concurrently")) {
                throw AnthropicInvalidDataException("'errorCode' is invalid, received $it")
            }
        }
        field().validate()
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
        errorCode.let { if (it == JsonValue.from("workspace_changed_concurrently")) 1 else 0 } +
            (field.asKnown().getOrNull()?.validity() ?: 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaWorkspaceChangedConcurrentlyErrorDetails &&
            errorCode == other.errorCode &&
            field == other.field &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy { Objects.hash(errorCode, field, additionalProperties) }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaWorkspaceChangedConcurrentlyErrorDetails{errorCode=$errorCode, field=$field, additionalProperties=$additionalProperties}"
}
