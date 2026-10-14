package com.anthropic.models.beta.organization.telemetrydestinations

import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.checkKnown
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

/** Cover only the workspaces on the list. */
class BetaTelemetryDestinationWorkspacesIncludeParams
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val type: JsonValue,
    private val workspaceIds: JsonField<List<String>>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
        @JsonProperty("workspace_ids")
        @ExcludeMissing
        workspaceIds: JsonField<List<String>> = JsonMissing.of(),
    ) : this(type, workspaceIds, mutableMapOf())

    /**
     * Expected to always return the following:
     * ```java
     * JsonValue.from("include")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    /**
     * The IDs of the workspaces on the destination's list (`wrkspc_...`). At most 100 per request.
     *
     * On create, these workspaces become the destination's list. If you leave `workspace_ids` out,
     * the list starts empty. If the API refuses one of the IDs, it doesn't create the destination.
     *
     * On update, `workspace_ids` replaces the whole list. A workspace that stays on the list keeps
     * its entry and its `created_at`. An empty array empties the list. If you leave `workspace_ids`
     * out and the type stays the same, the list doesn't change. If you leave it out and the type
     * changes, the new list starts empty. After a change to `include` without `workspace_ids`, the
     * destination doesn't cover any workspace until one is added. After a change to `exclude`
     * without `workspace_ids`, it covers every workspace.
     *
     * An ID sent more than once is added once. An archived workspace can't be added to a list. An
     * update can keep an archived workspace that is already on the list.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun workspaceIds(): Optional<List<String>> = workspaceIds.getOptional("workspace_ids")

    /**
     * Returns the raw JSON value of [workspaceIds].
     *
     * Unlike [workspaceIds], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("workspace_ids")
    @ExcludeMissing
    fun _workspaceIds(): JsonField<List<String>> = workspaceIds

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
         * [BetaTelemetryDestinationWorkspacesIncludeParams].
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaTelemetryDestinationWorkspacesIncludeParams]. */
    class Builder internal constructor() {

        private var type: JsonValue = JsonValue.from("include")
        private var workspaceIds: JsonField<MutableList<String>>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(
            betaTelemetryDestinationWorkspacesIncludeParams:
                BetaTelemetryDestinationWorkspacesIncludeParams
        ) = apply {
            type = betaTelemetryDestinationWorkspacesIncludeParams.type
            workspaceIds =
                betaTelemetryDestinationWorkspacesIncludeParams.workspaceIds
                    .map { it.toMutableList() }
                    .takeUnless { it.isMissing() }
            additionalProperties =
                betaTelemetryDestinationWorkspacesIncludeParams.additionalProperties.toMutableMap()
        }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("include")
         * ```
         *
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun type(type: JsonValue) = apply { this.type = type }

        /**
         * The IDs of the workspaces on the destination's list (`wrkspc_...`). At most 100 per
         * request.
         *
         * On create, these workspaces become the destination's list. If you leave `workspace_ids`
         * out, the list starts empty. If the API refuses one of the IDs, it doesn't create the
         * destination.
         *
         * On update, `workspace_ids` replaces the whole list. A workspace that stays on the list
         * keeps its entry and its `created_at`. An empty array empties the list. If you leave
         * `workspace_ids` out and the type stays the same, the list doesn't change. If you leave it
         * out and the type changes, the new list starts empty. After a change to `include` without
         * `workspace_ids`, the destination doesn't cover any workspace until one is added. After a
         * change to `exclude` without `workspace_ids`, it covers every workspace.
         *
         * An ID sent more than once is added once. An archived workspace can't be added to a list.
         * An update can keep an archived workspace that is already on the list.
         */
        fun workspaceIds(workspaceIds: List<String>) = workspaceIds(JsonField.of(workspaceIds))

        /**
         * Sets [Builder.workspaceIds] to an arbitrary JSON value.
         *
         * You should usually call [Builder.workspaceIds] with a well-typed `List<String>` value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun workspaceIds(workspaceIds: JsonField<List<String>>) = apply {
            this.workspaceIds = workspaceIds.map { it.toMutableList() }
        }

        /**
         * Adds a single [String] to [workspaceIds].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addWorkspaceId(workspaceId: String) = apply {
            workspaceIds =
                (workspaceIds ?: JsonField.of(mutableListOf())).also {
                    checkKnown("workspaceIds", it).add(workspaceId)
                }
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
         * Returns an immutable instance of [BetaTelemetryDestinationWorkspacesIncludeParams].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         */
        fun build(): BetaTelemetryDestinationWorkspacesIncludeParams =
            BetaTelemetryDestinationWorkspacesIncludeParams(
                type,
                (workspaceIds ?: JsonMissing.of()).map { it.toImmutable() },
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
    fun validate(): BetaTelemetryDestinationWorkspacesIncludeParams = apply {
        if (validated) {
            return@apply
        }

        _type().let {
            if (it != JsonValue.from("include")) {
                throw AnthropicInvalidDataException("'type' is invalid, received $it")
            }
        }
        workspaceIds()
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
        type.let { if (it == JsonValue.from("include")) 1 else 0 } +
            (workspaceIds.asKnown().getOrNull()?.size ?: 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaTelemetryDestinationWorkspacesIncludeParams &&
            type == other.type &&
            workspaceIds == other.workspaceIds &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy { Objects.hash(type, workspaceIds, additionalProperties) }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaTelemetryDestinationWorkspacesIncludeParams{type=$type, workspaceIds=$workspaceIds, additionalProperties=$additionalProperties}"
}
