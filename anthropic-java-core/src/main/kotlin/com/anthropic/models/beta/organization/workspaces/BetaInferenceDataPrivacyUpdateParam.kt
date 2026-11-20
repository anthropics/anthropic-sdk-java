package com.anthropic.models.beta.organization.workspaces

import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
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
 * Changes to where the Workspace's retained inference data and transcript copies go, and to whether
 * Anthropic's human review applies. An omitted member is unchanged; a member cannot be null.
 */
class BetaInferenceDataPrivacyUpdateParam
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val humanReview: JsonField<BetaAnthropicHumanReviewParam>,
    private val safeguardsStorage: JsonField<BetaStorageMemberParam>,
    private val transcriptsStorage: JsonField<BetaStorageMemberParam>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("human_review")
        @ExcludeMissing
        humanReview: JsonField<BetaAnthropicHumanReviewParam> = JsonMissing.of(),
        @JsonProperty("safeguards_storage")
        @ExcludeMissing
        safeguardsStorage: JsonField<BetaStorageMemberParam> = JsonMissing.of(),
        @JsonProperty("transcripts_storage")
        @ExcludeMissing
        transcriptsStorage: JsonField<BetaStorageMemberParam> = JsonMissing.of(),
    ) : this(humanReview, safeguardsStorage, transcriptsStorage, mutableMapOf())

    /**
     * Whether Anthropic's human review applies to the Workspace from now on. `disabled` needs
     * Anthropic's approval. When omitted, unchanged. Cannot be null.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun humanReview(): Optional<BetaAnthropicHumanReviewParam> =
        humanReview.getOptional("human_review")

    /**
     * Where the data Anthropic's safeguards systems retain for the Workspace goes once the change
     * takes effect. `external` sends it to the External Storage that `external_storage_id` names.
     * `internal` means no External Storage: it goes to Anthropic's storage or, on a cloud
     * provider's platform, to that provider's storage. When omitted, unchanged. Cannot be null.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun safeguardsStorage(): Optional<BetaStorageMemberParam> =
        safeguardsStorage.getOptional("safeguards_storage")

    /**
     * Where copies of the Workspace's transcripts and its cases (Anthropic's safety findings about
     * those transcripts) go once the change takes effect. `external` sends them to the External
     * Storage that `external_storage_id` names. `internal` means no External Storage, so no more
     * copies are made. When omitted, unchanged. Cannot be null.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun transcriptsStorage(): Optional<BetaStorageMemberParam> =
        transcriptsStorage.getOptional("transcripts_storage")

    /**
     * Returns the raw JSON value of [humanReview].
     *
     * Unlike [humanReview], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("human_review")
    @ExcludeMissing
    fun _humanReview(): JsonField<BetaAnthropicHumanReviewParam> = humanReview

    /**
     * Returns the raw JSON value of [safeguardsStorage].
     *
     * Unlike [safeguardsStorage], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("safeguards_storage")
    @ExcludeMissing
    fun _safeguardsStorage(): JsonField<BetaStorageMemberParam> = safeguardsStorage

    /**
     * Returns the raw JSON value of [transcriptsStorage].
     *
     * Unlike [transcriptsStorage], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("transcripts_storage")
    @ExcludeMissing
    fun _transcriptsStorage(): JsonField<BetaStorageMemberParam> = transcriptsStorage

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
         * [BetaInferenceDataPrivacyUpdateParam].
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaInferenceDataPrivacyUpdateParam]. */
    class Builder internal constructor() {

        private var humanReview: JsonField<BetaAnthropicHumanReviewParam> = JsonMissing.of()
        private var safeguardsStorage: JsonField<BetaStorageMemberParam> = JsonMissing.of()
        private var transcriptsStorage: JsonField<BetaStorageMemberParam> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(
            betaInferenceDataPrivacyUpdateParam: BetaInferenceDataPrivacyUpdateParam
        ) = apply {
            humanReview = betaInferenceDataPrivacyUpdateParam.humanReview
            safeguardsStorage = betaInferenceDataPrivacyUpdateParam.safeguardsStorage
            transcriptsStorage = betaInferenceDataPrivacyUpdateParam.transcriptsStorage
            additionalProperties =
                betaInferenceDataPrivacyUpdateParam.additionalProperties.toMutableMap()
        }

        /**
         * Whether Anthropic's human review applies to the Workspace from now on. `disabled` needs
         * Anthropic's approval. When omitted, unchanged. Cannot be null.
         */
        fun humanReview(humanReview: BetaAnthropicHumanReviewParam) =
            humanReview(JsonField.of(humanReview))

        /**
         * Sets [Builder.humanReview] to an arbitrary JSON value.
         *
         * You should usually call [Builder.humanReview] with a well-typed
         * [BetaAnthropicHumanReviewParam] value instead. This method is primarily for setting the
         * field to an undocumented or not yet supported value.
         */
        fun humanReview(humanReview: JsonField<BetaAnthropicHumanReviewParam>) = apply {
            this.humanReview = humanReview
        }

        /**
         * Where the data Anthropic's safeguards systems retain for the Workspace goes once the
         * change takes effect. `external` sends it to the External Storage that
         * `external_storage_id` names. `internal` means no External Storage: it goes to Anthropic's
         * storage or, on a cloud provider's platform, to that provider's storage. When omitted,
         * unchanged. Cannot be null.
         */
        fun safeguardsStorage(safeguardsStorage: BetaStorageMemberParam) =
            safeguardsStorage(JsonField.of(safeguardsStorage))

        /**
         * Sets [Builder.safeguardsStorage] to an arbitrary JSON value.
         *
         * You should usually call [Builder.safeguardsStorage] with a well-typed
         * [BetaStorageMemberParam] value instead. This method is primarily for setting the field to
         * an undocumented or not yet supported value.
         */
        fun safeguardsStorage(safeguardsStorage: JsonField<BetaStorageMemberParam>) = apply {
            this.safeguardsStorage = safeguardsStorage
        }

        /**
         * Alias for calling [safeguardsStorage] with
         * `BetaStorageMemberParam.ofInternal(internal_)`.
         */
        fun safeguardsStorage(internal_: BetaInternalStorageParam) =
            safeguardsStorage(BetaStorageMemberParam.ofInternal(internal_))

        /**
         * Alias for calling [safeguardsStorage] with `BetaStorageMemberParam.ofExternal(external)`.
         */
        fun safeguardsStorage(external: BetaExternalStorageMemberParam) =
            safeguardsStorage(BetaStorageMemberParam.ofExternal(external))

        /**
         * Alias for calling [safeguardsStorage] with the following:
         * ```java
         * BetaExternalStorageMemberParam.builder()
         *     .externalStorageId(externalStorageId)
         *     .build()
         * ```
         */
        fun externalSafeguardsStorage(externalStorageId: String) =
            safeguardsStorage(
                BetaExternalStorageMemberParam.builder()
                    .externalStorageId(externalStorageId)
                    .build()
            )

        /**
         * Where copies of the Workspace's transcripts and its cases (Anthropic's safety findings
         * about those transcripts) go once the change takes effect. `external` sends them to the
         * External Storage that `external_storage_id` names. `internal` means no External Storage,
         * so no more copies are made. When omitted, unchanged. Cannot be null.
         */
        fun transcriptsStorage(transcriptsStorage: BetaStorageMemberParam) =
            transcriptsStorage(JsonField.of(transcriptsStorage))

        /**
         * Sets [Builder.transcriptsStorage] to an arbitrary JSON value.
         *
         * You should usually call [Builder.transcriptsStorage] with a well-typed
         * [BetaStorageMemberParam] value instead. This method is primarily for setting the field to
         * an undocumented or not yet supported value.
         */
        fun transcriptsStorage(transcriptsStorage: JsonField<BetaStorageMemberParam>) = apply {
            this.transcriptsStorage = transcriptsStorage
        }

        /**
         * Alias for calling [transcriptsStorage] with
         * `BetaStorageMemberParam.ofInternal(internal_)`.
         */
        fun transcriptsStorage(internal_: BetaInternalStorageParam) =
            transcriptsStorage(BetaStorageMemberParam.ofInternal(internal_))

        /**
         * Alias for calling [transcriptsStorage] with
         * `BetaStorageMemberParam.ofExternal(external)`.
         */
        fun transcriptsStorage(external: BetaExternalStorageMemberParam) =
            transcriptsStorage(BetaStorageMemberParam.ofExternal(external))

        /**
         * Alias for calling [transcriptsStorage] with the following:
         * ```java
         * BetaExternalStorageMemberParam.builder()
         *     .externalStorageId(externalStorageId)
         *     .build()
         * ```
         */
        fun externalTranscriptsStorage(externalStorageId: String) =
            transcriptsStorage(
                BetaExternalStorageMemberParam.builder()
                    .externalStorageId(externalStorageId)
                    .build()
            )

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
         * Returns an immutable instance of [BetaInferenceDataPrivacyUpdateParam].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         */
        fun build(): BetaInferenceDataPrivacyUpdateParam =
            BetaInferenceDataPrivacyUpdateParam(
                humanReview,
                safeguardsStorage,
                transcriptsStorage,
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
    fun validate(): BetaInferenceDataPrivacyUpdateParam = apply {
        if (validated) {
            return@apply
        }

        humanReview().ifPresent { it.validate() }
        safeguardsStorage().ifPresent { it.validate() }
        transcriptsStorage().ifPresent { it.validate() }
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
        (humanReview.asKnown().getOrNull()?.validity() ?: 0) +
            (safeguardsStorage.asKnown().getOrNull()?.validity() ?: 0) +
            (transcriptsStorage.asKnown().getOrNull()?.validity() ?: 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaInferenceDataPrivacyUpdateParam &&
            humanReview == other.humanReview &&
            safeguardsStorage == other.safeguardsStorage &&
            transcriptsStorage == other.transcriptsStorage &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(humanReview, safeguardsStorage, transcriptsStorage, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaInferenceDataPrivacyUpdateParam{humanReview=$humanReview, safeguardsStorage=$safeguardsStorage, transcriptsStorage=$transcriptsStorage, additionalProperties=$additionalProperties}"
}
