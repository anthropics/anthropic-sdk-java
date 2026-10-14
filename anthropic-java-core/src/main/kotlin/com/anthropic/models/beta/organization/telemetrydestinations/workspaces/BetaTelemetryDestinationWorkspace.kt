package com.anthropic.models.beta.organization.telemetrydestinations.workspaces

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
import java.time.OffsetDateTime
import java.util.Collections
import java.util.Objects

/**
 * A workspace on a telemetry destination's workspace list.
 *
 * Under `include`, the destination covers the workspace. Under `exclude`, the destination covers
 * every workspace except this one. The entry doesn't have an `id`. Its `telemetry_destination_id`
 * and `workspace_id` together identify it.
 */
class BetaTelemetryDestinationWorkspace
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val createdAt: JsonField<OffsetDateTime>,
    private val telemetryDestinationId: JsonField<String>,
    private val type: JsonValue,
    private val workspaceId: JsonField<String>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("created_at")
        @ExcludeMissing
        createdAt: JsonField<OffsetDateTime> = JsonMissing.of(),
        @JsonProperty("telemetry_destination_id")
        @ExcludeMissing
        telemetryDestinationId: JsonField<String> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
        @JsonProperty("workspace_id")
        @ExcludeMissing
        workspaceId: JsonField<String> = JsonMissing.of(),
    ) : this(createdAt, telemetryDestinationId, type, workspaceId, mutableMapOf())

    /**
     * Time the workspace was added to the list.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun createdAt(): OffsetDateTime = createdAt.getRequired("created_at")

    /**
     * ID of the telemetry destination (`tdst_...`).
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun telemetryDestinationId(): String =
        telemetryDestinationId.getRequired("telemetry_destination_id")

    /**
     * Expected to always return the following:
     * ```java
     * JsonValue.from("telemetry_destination_workspace")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    /**
     * ID of the workspace (`wrkspc_...`).
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun workspaceId(): String = workspaceId.getRequired("workspace_id")

    /**
     * Returns the raw JSON value of [createdAt].
     *
     * Unlike [createdAt], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("created_at")
    @ExcludeMissing
    fun _createdAt(): JsonField<OffsetDateTime> = createdAt

    /**
     * Returns the raw JSON value of [telemetryDestinationId].
     *
     * Unlike [telemetryDestinationId], this method doesn't throw if the JSON field has an
     * unexpected type.
     */
    @JsonProperty("telemetry_destination_id")
    @ExcludeMissing
    fun _telemetryDestinationId(): JsonField<String> = telemetryDestinationId

    /**
     * Returns the raw JSON value of [workspaceId].
     *
     * Unlike [workspaceId], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("workspace_id")
    @ExcludeMissing
    fun _workspaceId(): JsonField<String> = workspaceId

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
         * [BetaTelemetryDestinationWorkspace].
         *
         * The following fields are required:
         * ```java
         * .createdAt()
         * .telemetryDestinationId()
         * .workspaceId()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaTelemetryDestinationWorkspace]. */
    class Builder internal constructor() {

        private var createdAt: JsonField<OffsetDateTime>? = null
        private var telemetryDestinationId: JsonField<String>? = null
        private var type: JsonValue = JsonValue.from("telemetry_destination_workspace")
        private var workspaceId: JsonField<String>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaTelemetryDestinationWorkspace: BetaTelemetryDestinationWorkspace) =
            apply {
                createdAt = betaTelemetryDestinationWorkspace.createdAt
                telemetryDestinationId = betaTelemetryDestinationWorkspace.telemetryDestinationId
                type = betaTelemetryDestinationWorkspace.type
                workspaceId = betaTelemetryDestinationWorkspace.workspaceId
                additionalProperties =
                    betaTelemetryDestinationWorkspace.additionalProperties.toMutableMap()
            }

        /** Time the workspace was added to the list. */
        fun createdAt(createdAt: OffsetDateTime) = createdAt(JsonField.of(createdAt))

        /**
         * Sets [Builder.createdAt] to an arbitrary JSON value.
         *
         * You should usually call [Builder.createdAt] with a well-typed [OffsetDateTime] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun createdAt(createdAt: JsonField<OffsetDateTime>) = apply { this.createdAt = createdAt }

        /** ID of the telemetry destination (`tdst_...`). */
        fun telemetryDestinationId(telemetryDestinationId: String) =
            telemetryDestinationId(JsonField.of(telemetryDestinationId))

        /**
         * Sets [Builder.telemetryDestinationId] to an arbitrary JSON value.
         *
         * You should usually call [Builder.telemetryDestinationId] with a well-typed [String] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun telemetryDestinationId(telemetryDestinationId: JsonField<String>) = apply {
            this.telemetryDestinationId = telemetryDestinationId
        }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("telemetry_destination_workspace")
         * ```
         *
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun type(type: JsonValue) = apply { this.type = type }

        /** ID of the workspace (`wrkspc_...`). */
        fun workspaceId(workspaceId: String) = workspaceId(JsonField.of(workspaceId))

        /**
         * Sets [Builder.workspaceId] to an arbitrary JSON value.
         *
         * You should usually call [Builder.workspaceId] with a well-typed [String] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun workspaceId(workspaceId: JsonField<String>) = apply { this.workspaceId = workspaceId }

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
         * Returns an immutable instance of [BetaTelemetryDestinationWorkspace].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .createdAt()
         * .telemetryDestinationId()
         * .workspaceId()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaTelemetryDestinationWorkspace =
            BetaTelemetryDestinationWorkspace(
                checkRequired("createdAt", createdAt),
                checkRequired("telemetryDestinationId", telemetryDestinationId),
                type,
                checkRequired("workspaceId", workspaceId),
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
    fun validate(): BetaTelemetryDestinationWorkspace = apply {
        if (validated) {
            return@apply
        }

        createdAt()
        telemetryDestinationId()
        _type().let {
            if (it != JsonValue.from("telemetry_destination_workspace")) {
                throw AnthropicInvalidDataException("'type' is invalid, received $it")
            }
        }
        workspaceId()
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
        (if (createdAt.asKnown().isPresent) 1 else 0) +
            (if (telemetryDestinationId.asKnown().isPresent) 1 else 0) +
            type.let { if (it == JsonValue.from("telemetry_destination_workspace")) 1 else 0 } +
            (if (workspaceId.asKnown().isPresent) 1 else 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaTelemetryDestinationWorkspace &&
            createdAt == other.createdAt &&
            telemetryDestinationId == other.telemetryDestinationId &&
            type == other.type &&
            workspaceId == other.workspaceId &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(createdAt, telemetryDestinationId, type, workspaceId, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaTelemetryDestinationWorkspace{createdAt=$createdAt, telemetryDestinationId=$telemetryDestinationId, type=$type, workspaceId=$workspaceId, additionalProperties=$additionalProperties}"
}
