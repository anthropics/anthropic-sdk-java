package com.anthropic.models.beta.dreams

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
import kotlin.jvm.optionals.getOrNull

/** The sessions that a dream reads, given as an entry in `inputs`. */
class BetaDreamSessionsInput
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val sessionIds: JsonField<List<String>>,
    private val type: JsonValue,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("session_ids")
        @ExcludeMissing
        sessionIds: JsonField<List<String>> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
    ) : this(sessionIds, type, mutableMapOf())

    /**
     * The IDs of the sessions whose transcripts the dream reads (`sesn_...`).
     *
     * Give 1 to 100 IDs, with no duplicates. Each session must be in the same workspace as the
     * dream. Responses list the IDs in sorted order.
     *
     * The
     * [limits table in the Dreams guide](https://platform.claude.com/docs/en/managed-agents/dreams#limits)
     * lists all the limits on a dream.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun sessionIds(): List<String> = sessionIds.getRequired("session_ids")

    /**
     * Expected to always return the following:
     * ```java
     * JsonValue.from("sessions")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    /**
     * Returns the raw JSON value of [sessionIds].
     *
     * Unlike [sessionIds], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("session_ids")
    @ExcludeMissing
    fun _sessionIds(): JsonField<List<String>> = sessionIds

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
         * Returns a mutable builder for constructing an instance of [BetaDreamSessionsInput].
         *
         * The following fields are required:
         * ```java
         * .sessionIds()
         * ```
         */
        @JvmStatic fun builder() = Builder()

        /**
         * Returns an immutable instance of [BetaDreamSessionsInput] with the required [sessionIds]
         * set to the given value.
         */
        @JvmStatic fun of(sessionIds: List<String>) = builder().sessionIds(sessionIds).build()
    }

    /** A builder for [BetaDreamSessionsInput]. */
    class Builder internal constructor() {

        private var sessionIds: JsonField<MutableList<String>>? = null
        private var type: JsonValue = JsonValue.from("sessions")
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaDreamSessionsInput: BetaDreamSessionsInput) = apply {
            sessionIds =
                betaDreamSessionsInput.sessionIds
                    .map { it.toMutableList() }
                    .takeUnless { it.isMissing() }
            type = betaDreamSessionsInput.type
            additionalProperties = betaDreamSessionsInput.additionalProperties.toMutableMap()
        }

        /**
         * The IDs of the sessions whose transcripts the dream reads (`sesn_...`).
         *
         * Give 1 to 100 IDs, with no duplicates. Each session must be in the same workspace as the
         * dream. Responses list the IDs in sorted order.
         *
         * The
         * [limits table in the Dreams guide](https://platform.claude.com/docs/en/managed-agents/dreams#limits)
         * lists all the limits on a dream.
         */
        fun sessionIds(sessionIds: List<String>) = sessionIds(JsonField.of(sessionIds))

        /**
         * Sets [Builder.sessionIds] to an arbitrary JSON value.
         *
         * You should usually call [Builder.sessionIds] with a well-typed `List<String>` value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun sessionIds(sessionIds: JsonField<List<String>>) = apply {
            this.sessionIds = sessionIds.map { it.toMutableList() }
        }

        /**
         * Adds a single [String] to [sessionIds].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addSessionId(sessionId: String) = apply {
            sessionIds =
                (sessionIds ?: JsonField.of(mutableListOf())).also {
                    checkKnown("sessionIds", it).add(sessionId)
                }
        }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("sessions")
         * ```
         *
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun type(type: JsonValue) = apply { this.type = type }

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
         * Returns an immutable instance of [BetaDreamSessionsInput].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .sessionIds()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaDreamSessionsInput =
            BetaDreamSessionsInput(
                checkRequired("sessionIds", sessionIds).map { it.toImmutable() },
                type,
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
    fun validate(): BetaDreamSessionsInput = apply {
        if (validated) {
            return@apply
        }

        sessionIds()
        _type().let {
            if (it != JsonValue.from("sessions")) {
                throw AnthropicInvalidDataException("'type' is invalid, received $it")
            }
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
     * Returns a score indicating how many valid values are contained in this object recursively.
     *
     * Used for best match union deserialization.
     */
    @JvmSynthetic
    internal fun validity(): Int =
        (sessionIds.asKnown().getOrNull()?.size ?: 0) +
            type.let { if (it == JsonValue.from("sessions")) 1 else 0 }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaDreamSessionsInput &&
            sessionIds == other.sessionIds &&
            type == other.type &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy { Objects.hash(sessionIds, type, additionalProperties) }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaDreamSessionsInput{sessionIds=$sessionIds, type=$type, additionalProperties=$additionalProperties}"
}
