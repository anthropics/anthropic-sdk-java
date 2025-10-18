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

/** Container parameters with skills to be loaded. */
class BetaContainerParams
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val id: JsonField<String>,
    private val skills: JsonField<List<BetaSkillParams>>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("id") @ExcludeMissing id: JsonField<String> = JsonMissing.of(),
        @JsonProperty("skills")
        @ExcludeMissing
        skills: JsonField<List<BetaSkillParams>> = JsonMissing.of(),
    ) : this(id, skills, mutableMapOf())

    /**
     * Container id
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun id(): Optional<String> = id.getOptional("id")

    /**
     * List of skills to load in the container
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun skills(): Optional<List<BetaSkillParams>> = skills.getOptional("skills")

    /**
     * Returns the raw JSON value of [id].
     *
     * Unlike [id], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("id") @ExcludeMissing fun _id(): JsonField<String> = id

    /**
     * Returns the raw JSON value of [skills].
     *
     * Unlike [skills], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("skills") @ExcludeMissing fun _skills(): JsonField<List<BetaSkillParams>> = skills

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

        /** Returns a mutable builder for constructing an instance of [BetaContainerParams]. */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaContainerParams]. */
    class Builder internal constructor() {

        private var id: JsonField<String> = JsonMissing.of()
        private var skills: JsonField<MutableList<BetaSkillParams>>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaContainerParams: BetaContainerParams) = apply {
            id = betaContainerParams.id
            skills = betaContainerParams.skills.map { it.toMutableList() }
            additionalProperties = betaContainerParams.additionalProperties.toMutableMap()
        }

        /** Container id */
        fun id(id: String?) = id(JsonField.ofNullable(id))

        /** Alias for calling [Builder.id] with `id.orElse(null)`. */
        fun id(id: Optional<String>) = id(id.getOrNull())

        /**
         * Sets [Builder.id] to an arbitrary JSON value.
         *
         * You should usually call [Builder.id] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun id(id: JsonField<String>) = apply { this.id = id }

        /** List of skills to load in the container */
        fun skills(skills: List<BetaSkillParams>?) = skills(JsonField.ofNullable(skills))

        /** Alias for calling [Builder.skills] with `skills.orElse(null)`. */
        fun skills(skills: Optional<List<BetaSkillParams>>) = skills(skills.getOrNull())

        /**
         * Sets [Builder.skills] to an arbitrary JSON value.
         *
         * You should usually call [Builder.skills] with a well-typed `List<BetaSkillParams>` value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun skills(skills: JsonField<List<BetaSkillParams>>) = apply {
            this.skills = skills.map { it.toMutableList() }
        }

        /**
         * Adds a single [BetaSkillParams] to [skills].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addSkill(skill: BetaSkillParams) = apply {
            skills =
                (skills ?: JsonField.of(mutableListOf())).also {
                    checkKnown("skills", it).add(skill)
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
         * Returns an immutable instance of [BetaContainerParams].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         */
        fun build(): BetaContainerParams =
            BetaContainerParams(
                id,
                (skills ?: JsonMissing.of()).map { it.toImmutable() },
                additionalProperties.toMutableMap(),
            )
    }

    private var validated: Boolean = false

    fun validate(): BetaContainerParams = apply {
        if (validated) {
            return@apply
        }

        id()
        skills().ifPresent { it.forEach { it.validate() } }
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
        (if (id.asKnown().isPresent) 1 else 0) +
            (skills.asKnown().getOrNull()?.sumOf { it.validity().toInt() } ?: 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaContainerParams &&
            id == other.id &&
            skills == other.skills &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy { Objects.hash(id, skills, additionalProperties) }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaContainerParams{id=$id, skills=$skills, additionalProperties=$additionalProperties}"
}
