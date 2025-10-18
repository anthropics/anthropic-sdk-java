// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

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

class BetaContextManagementConfig
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val edits: JsonField<List<BetaClearToolUses20250919Edit>>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("edits")
        @ExcludeMissing
        edits: JsonField<List<BetaClearToolUses20250919Edit>> = JsonMissing.of()
    ) : this(edits, mutableMapOf())

    /**
     * List of context management edits to apply
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun edits(): Optional<List<BetaClearToolUses20250919Edit>> = edits.getOptional("edits")

    /**
     * Returns the raw JSON value of [edits].
     *
     * Unlike [edits], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("edits")
    @ExcludeMissing
    fun _edits(): JsonField<List<BetaClearToolUses20250919Edit>> = edits

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
         * Returns a mutable builder for constructing an instance of [BetaContextManagementConfig].
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaContextManagementConfig]. */
    class Builder internal constructor() {

        private var edits: JsonField<MutableList<BetaClearToolUses20250919Edit>>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaContextManagementConfig: BetaContextManagementConfig) = apply {
            edits = betaContextManagementConfig.edits.map { it.toMutableList() }
            additionalProperties = betaContextManagementConfig.additionalProperties.toMutableMap()
        }

        /** List of context management edits to apply */
        fun edits(edits: List<BetaClearToolUses20250919Edit>) = edits(JsonField.of(edits))

        /**
         * Sets [Builder.edits] to an arbitrary JSON value.
         *
         * You should usually call [Builder.edits] with a well-typed
         * `List<BetaClearToolUses20250919Edit>` value instead. This method is primarily for setting
         * the field to an undocumented or not yet supported value.
         */
        fun edits(edits: JsonField<List<BetaClearToolUses20250919Edit>>) = apply {
            this.edits = edits.map { it.toMutableList() }
        }

        /**
         * Adds a single [BetaClearToolUses20250919Edit] to [edits].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addEdit(edit: BetaClearToolUses20250919Edit) = apply {
            edits =
                (edits ?: JsonField.of(mutableListOf())).also { checkKnown("edits", it).add(edit) }
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
         * Returns an immutable instance of [BetaContextManagementConfig].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         */
        fun build(): BetaContextManagementConfig =
            BetaContextManagementConfig(
                (edits ?: JsonMissing.of()).map { it.toImmutable() },
                additionalProperties.toMutableMap(),
            )
    }

    private var validated: Boolean = false

    fun validate(): BetaContextManagementConfig = apply {
        if (validated) {
            return@apply
        }

        edits().ifPresent { it.forEach { it.validate() } }
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
        (edits.asKnown().getOrNull()?.sumOf { it.validity().toInt() } ?: 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaContextManagementConfig &&
            edits == other.edits &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy { Objects.hash(edits, additionalProperties) }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaContextManagementConfig{edits=$edits, additionalProperties=$additionalProperties}"
}
