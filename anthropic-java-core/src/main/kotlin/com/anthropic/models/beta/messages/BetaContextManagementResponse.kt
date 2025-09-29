// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

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

/** Information about context management operations applied during the request. */
class BetaContextManagementResponse
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val appliedEdits: JsonField<List<BetaClearToolUses20250919EditResponse>>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("applied_edits")
        @ExcludeMissing
        appliedEdits: JsonField<List<BetaClearToolUses20250919EditResponse>> = JsonMissing.of()
    ) : this(appliedEdits, mutableMapOf())

    /**
     * List of context management edits that were applied.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun appliedEdits(): List<BetaClearToolUses20250919EditResponse> =
        appliedEdits.getRequired("applied_edits")

    /**
     * Returns the raw JSON value of [appliedEdits].
     *
     * Unlike [appliedEdits], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("applied_edits")
    @ExcludeMissing
    fun _appliedEdits(): JsonField<List<BetaClearToolUses20250919EditResponse>> = appliedEdits

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
         * [BetaContextManagementResponse].
         *
         * The following fields are required:
         * ```java
         * .appliedEdits()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaContextManagementResponse]. */
    class Builder internal constructor() {

        private var appliedEdits: JsonField<MutableList<BetaClearToolUses20250919EditResponse>>? =
            null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaContextManagementResponse: BetaContextManagementResponse) = apply {
            appliedEdits = betaContextManagementResponse.appliedEdits.map { it.toMutableList() }
            additionalProperties = betaContextManagementResponse.additionalProperties.toMutableMap()
        }

        /** List of context management edits that were applied. */
        fun appliedEdits(appliedEdits: List<BetaClearToolUses20250919EditResponse>) =
            appliedEdits(JsonField.of(appliedEdits))

        /**
         * Sets [Builder.appliedEdits] to an arbitrary JSON value.
         *
         * You should usually call [Builder.appliedEdits] with a well-typed
         * `List<BetaClearToolUses20250919EditResponse>` value instead. This method is primarily for
         * setting the field to an undocumented or not yet supported value.
         */
        fun appliedEdits(appliedEdits: JsonField<List<BetaClearToolUses20250919EditResponse>>) =
            apply {
                this.appliedEdits = appliedEdits.map { it.toMutableList() }
            }

        /**
         * Adds a single [BetaClearToolUses20250919EditResponse] to [appliedEdits].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addAppliedEdit(appliedEdit: BetaClearToolUses20250919EditResponse) = apply {
            appliedEdits =
                (appliedEdits ?: JsonField.of(mutableListOf())).also {
                    checkKnown("appliedEdits", it).add(appliedEdit)
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
         * Returns an immutable instance of [BetaContextManagementResponse].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .appliedEdits()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaContextManagementResponse =
            BetaContextManagementResponse(
                checkRequired("appliedEdits", appliedEdits).map { it.toImmutable() },
                additionalProperties.toMutableMap(),
            )
    }

    private var validated: Boolean = false

    fun validate(): BetaContextManagementResponse = apply {
        if (validated) {
            return@apply
        }

        appliedEdits().forEach { it.validate() }
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
        (appliedEdits.asKnown().getOrNull()?.sumOf { it.validity().toInt() } ?: 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaContextManagementResponse &&
            appliedEdits == other.appliedEdits &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy { Objects.hash(appliedEdits, additionalProperties) }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaContextManagementResponse{appliedEdits=$appliedEdits, additionalProperties=$additionalProperties}"
}
