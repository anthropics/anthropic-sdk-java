// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.deployments

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
import kotlin.jvm.optionals.getOrNull

/** A scheduled fire recorded a failed run whose error auto-pauses the deployment. */
class BetaManagedAgentsErrorDeploymentPausedReason
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val error: JsonField<BetaManagedAgentsDeploymentPausedReasonError>,
    private val type: JsonField<Type>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("error")
        @ExcludeMissing
        error: JsonField<BetaManagedAgentsDeploymentPausedReasonError> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonField<Type> = JsonMissing.of(),
    ) : this(error, type, mutableMapOf())

    /**
     * The error that triggered an auto-pause. Matches the failed run's `error.type`.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun error(): BetaManagedAgentsDeploymentPausedReasonError = error.getRequired("error")

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun type(): Type = type.getRequired("type")

    /**
     * Returns the raw JSON value of [error].
     *
     * Unlike [error], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("error")
    @ExcludeMissing
    fun _error(): JsonField<BetaManagedAgentsDeploymentPausedReasonError> = error

    /**
     * Returns the raw JSON value of [type].
     *
     * Unlike [type], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonField<Type> = type

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
         * [BetaManagedAgentsErrorDeploymentPausedReason].
         *
         * The following fields are required:
         * ```java
         * .error()
         * .type()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaManagedAgentsErrorDeploymentPausedReason]. */
    class Builder internal constructor() {

        private var error: JsonField<BetaManagedAgentsDeploymentPausedReasonError>? = null
        private var type: JsonField<Type>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(
            betaManagedAgentsErrorDeploymentPausedReason:
                BetaManagedAgentsErrorDeploymentPausedReason
        ) = apply {
            error = betaManagedAgentsErrorDeploymentPausedReason.error
            type = betaManagedAgentsErrorDeploymentPausedReason.type
            additionalProperties =
                betaManagedAgentsErrorDeploymentPausedReason.additionalProperties.toMutableMap()
        }

        /** The error that triggered an auto-pause. Matches the failed run's `error.type`. */
        fun error(error: BetaManagedAgentsDeploymentPausedReasonError) = error(JsonField.of(error))

        /**
         * Sets [Builder.error] to an arbitrary JSON value.
         *
         * You should usually call [Builder.error] with a well-typed
         * [BetaManagedAgentsDeploymentPausedReasonError] value instead. This method is primarily
         * for setting the field to an undocumented or not yet supported value.
         */
        fun error(error: JsonField<BetaManagedAgentsDeploymentPausedReasonError>) = apply {
            this.error = error
        }

        /**
         * Alias for calling [error] with
         * `BetaManagedAgentsDeploymentPausedReasonError.ofEnvironmentArchived(environmentArchived)`.
         */
        fun error(
            environmentArchived: BetaManagedAgentsEnvironmentArchivedDeploymentPausedReasonError
        ) =
            error(
                BetaManagedAgentsDeploymentPausedReasonError.ofEnvironmentArchived(
                    environmentArchived
                )
            )

        /**
         * Alias for calling [error] with
         * `BetaManagedAgentsDeploymentPausedReasonError.ofAgentArchived(agentArchived)`.
         */
        fun error(agentArchived: BetaManagedAgentsAgentArchivedDeploymentPausedReasonError) =
            error(BetaManagedAgentsDeploymentPausedReasonError.ofAgentArchived(agentArchived))

        /**
         * Alias for calling [error] with
         * `BetaManagedAgentsDeploymentPausedReasonError.ofEnvironmentNotFound(environmentNotFound)`.
         */
        fun error(
            environmentNotFound: BetaManagedAgentsEnvironmentNotFoundDeploymentPausedReasonError
        ) =
            error(
                BetaManagedAgentsDeploymentPausedReasonError.ofEnvironmentNotFound(
                    environmentNotFound
                )
            )

        /**
         * Alias for calling [error] with
         * `BetaManagedAgentsDeploymentPausedReasonError.ofVaultNotFound(vaultNotFound)`.
         */
        fun error(vaultNotFound: BetaManagedAgentsVaultNotFoundDeploymentPausedReasonError) =
            error(BetaManagedAgentsDeploymentPausedReasonError.ofVaultNotFound(vaultNotFound))

        /**
         * Alias for calling [error] with
         * `BetaManagedAgentsDeploymentPausedReasonError.ofFileNotFound(fileNotFound)`.
         */
        fun error(fileNotFound: BetaManagedAgentsFileNotFoundDeploymentPausedReasonError) =
            error(BetaManagedAgentsDeploymentPausedReasonError.ofFileNotFound(fileNotFound))

        /**
         * Alias for calling [error] with
         * `BetaManagedAgentsDeploymentPausedReasonError.ofSessionResourceNotFound(sessionResourceNotFound)`.
         */
        fun error(
            sessionResourceNotFound:
                BetaManagedAgentsSessionResourceNotFoundDeploymentPausedReasonError
        ) =
            error(
                BetaManagedAgentsDeploymentPausedReasonError.ofSessionResourceNotFound(
                    sessionResourceNotFound
                )
            )

        /**
         * Alias for calling [error] with
         * `BetaManagedAgentsDeploymentPausedReasonError.ofWorkspaceArchived(workspaceArchived)`.
         */
        fun error(
            workspaceArchived: BetaManagedAgentsWorkspaceArchivedDeploymentPausedReasonError
        ) =
            error(
                BetaManagedAgentsDeploymentPausedReasonError.ofWorkspaceArchived(workspaceArchived)
            )

        /**
         * Alias for calling [error] with
         * `BetaManagedAgentsDeploymentPausedReasonError.ofOrganizationDisabled(organizationDisabled)`.
         */
        fun error(
            organizationDisabled: BetaManagedAgentsOrganizationDisabledDeploymentPausedReasonError
        ) =
            error(
                BetaManagedAgentsDeploymentPausedReasonError.ofOrganizationDisabled(
                    organizationDisabled
                )
            )

        /**
         * Alias for calling [error] with
         * `BetaManagedAgentsDeploymentPausedReasonError.ofMemoryStoreArchived(memoryStoreArchived)`.
         */
        fun error(
            memoryStoreArchived: BetaManagedAgentsMemoryStoreArchivedDeploymentPausedReasonError
        ) =
            error(
                BetaManagedAgentsDeploymentPausedReasonError.ofMemoryStoreArchived(
                    memoryStoreArchived
                )
            )

        /**
         * Alias for calling [error] with
         * `BetaManagedAgentsDeploymentPausedReasonError.ofSkillNotFound(skillNotFound)`.
         */
        fun error(skillNotFound: BetaManagedAgentsSkillNotFoundDeploymentPausedReasonError) =
            error(BetaManagedAgentsDeploymentPausedReasonError.ofSkillNotFound(skillNotFound))

        /**
         * Alias for calling [error] with
         * `BetaManagedAgentsDeploymentPausedReasonError.ofVaultArchived(vaultArchived)`.
         */
        fun error(vaultArchived: BetaManagedAgentsVaultArchivedDeploymentPausedReasonError) =
            error(BetaManagedAgentsDeploymentPausedReasonError.ofVaultArchived(vaultArchived))

        /**
         * Alias for calling [error] with
         * `BetaManagedAgentsDeploymentPausedReasonError.ofUnknown(unknown)`.
         */
        fun error(unknown: BetaManagedAgentsUnknownDeploymentPausedReasonError) =
            error(BetaManagedAgentsDeploymentPausedReasonError.ofUnknown(unknown))

        /**
         * Alias for calling [error] with
         * `BetaManagedAgentsDeploymentPausedReasonError.ofSelfHostedResourcesUnsupported(selfHostedResourcesUnsupported)`.
         */
        fun error(
            selfHostedResourcesUnsupported:
                BetaManagedAgentsSelfHostedResourcesUnsupportedDeploymentPausedReasonError
        ) =
            error(
                BetaManagedAgentsDeploymentPausedReasonError.ofSelfHostedResourcesUnsupported(
                    selfHostedResourcesUnsupported
                )
            )

        /**
         * Alias for calling [error] with
         * `BetaManagedAgentsDeploymentPausedReasonError.ofMcpEgressBlocked(mcpEgressBlocked)`.
         */
        fun error(mcpEgressBlocked: BetaManagedAgentsMcpEgressBlockedDeploymentPausedReasonError) =
            error(BetaManagedAgentsDeploymentPausedReasonError.ofMcpEgressBlocked(mcpEgressBlocked))

        fun type(type: Type) = type(JsonField.of(type))

        /**
         * Sets [Builder.type] to an arbitrary JSON value.
         *
         * You should usually call [Builder.type] with a well-typed [Type] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun type(type: JsonField<Type>) = apply { this.type = type }

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
         * Returns an immutable instance of [BetaManagedAgentsErrorDeploymentPausedReason].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .error()
         * .type()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaManagedAgentsErrorDeploymentPausedReason =
            BetaManagedAgentsErrorDeploymentPausedReason(
                checkRequired("error", error),
                checkRequired("type", type),
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
    fun validate(): BetaManagedAgentsErrorDeploymentPausedReason = apply {
        if (validated) {
            return@apply
        }

        error().validate()
        type().validate()
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
        (error.asKnown().getOrNull()?.validity() ?: 0) +
            (type.asKnown().getOrNull()?.validity() ?: 0)

    class Type @JsonCreator private constructor(private val value: JsonField<String>) : Enum {

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

            @JvmField val ERROR = of("error")

            @JvmStatic fun of(value: String) = Type(JsonField.of(value))
        }

        /** An enum containing [Type]'s known values. */
        enum class Known {
            ERROR
        }

        /**
         * An enum containing [Type]'s known values, as well as an [_UNKNOWN] member.
         *
         * An instance of [Type] can contain an unknown value in a couple of cases:
         * - It was deserialized from data that doesn't match any known member. For example, if the
         *   SDK is on an older version than the API, then the API may respond with new members that
         *   the SDK is unaware of.
         * - It was constructed with an arbitrary value using the [of] method.
         */
        enum class Value {
            ERROR,
            /** An enum member indicating that [Type] was instantiated with an unknown value. */
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
                ERROR -> Value.ERROR
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
                ERROR -> Known.ERROR
                else -> throw AnthropicInvalidDataException("Unknown Type: $value")
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
        fun validate(): Type = apply {
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

            return other is Type && value == other.value
        }

        override fun hashCode() = value.hashCode()

        override fun toString() = value.toString()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaManagedAgentsErrorDeploymentPausedReason &&
            error == other.error &&
            type == other.type &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy { Objects.hash(error, type, additionalProperties) }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaManagedAgentsErrorDeploymentPausedReason{error=$error, type=$type, additionalProperties=$additionalProperties}"
}
