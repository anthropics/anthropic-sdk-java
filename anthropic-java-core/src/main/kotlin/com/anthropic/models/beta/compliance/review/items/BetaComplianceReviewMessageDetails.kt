package com.anthropic.models.beta.compliance.review.items

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
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/** A message sent to Claude. */
class BetaComplianceReviewMessageDetails
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val content: JsonField<BetaComplianceReviewMessageContent>,
    private val contentUnavailableReason: JsonField<BetaComplianceReviewContentUnavailableReason>,
    private val model: JsonField<String>,
    private val type: JsonValue,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("content")
        @ExcludeMissing
        content: JsonField<BetaComplianceReviewMessageContent> = JsonMissing.of(),
        @JsonProperty("content_unavailable_reason")
        @ExcludeMissing
        contentUnavailableReason: JsonField<BetaComplianceReviewContentUnavailableReason> =
            JsonMissing.of(),
        @JsonProperty("model") @ExcludeMissing model: JsonField<String> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
    ) : this(content, contentUnavailableReason, model, type, mutableMapOf())

    /**
     * The stored request and response. Null when `content_unavailable_reason` is set. New types can
     * appear, so accept a type that you don't know.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun content(): Optional<BetaComplianceReviewMessageContent> = content.getOptional("content")

    /**
     * Why `content` is null. Null when `content` is set. New types can appear, so accept a type
     * that you don't know.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun contentUnavailableReason(): Optional<BetaComplianceReviewContentUnavailableReason> =
        contentUnavailableReason.getOptional("content_unavailable_reason")

    /**
     * The model that the call named. Null when none was recorded.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun model(): Optional<String> = model.getOptional("model")

    /**
     * Expected to always return the following:
     * ```java
     * JsonValue.from("message")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    /**
     * Returns the raw JSON value of [content].
     *
     * Unlike [content], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("content")
    @ExcludeMissing
    fun _content(): JsonField<BetaComplianceReviewMessageContent> = content

    /**
     * Returns the raw JSON value of [contentUnavailableReason].
     *
     * Unlike [contentUnavailableReason], this method doesn't throw if the JSON field has an
     * unexpected type.
     */
    @JsonProperty("content_unavailable_reason")
    @ExcludeMissing
    fun _contentUnavailableReason(): JsonField<BetaComplianceReviewContentUnavailableReason> =
        contentUnavailableReason

    /**
     * Returns the raw JSON value of [model].
     *
     * Unlike [model], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("model") @ExcludeMissing fun _model(): JsonField<String> = model

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
         * [BetaComplianceReviewMessageDetails].
         *
         * The following fields are required:
         * ```java
         * .content()
         * .contentUnavailableReason()
         * .model()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaComplianceReviewMessageDetails]. */
    class Builder internal constructor() {

        private var content: JsonField<BetaComplianceReviewMessageContent>? = null
        private var contentUnavailableReason:
            JsonField<BetaComplianceReviewContentUnavailableReason>? =
            null
        private var model: JsonField<String>? = null
        private var type: JsonValue = JsonValue.from("message")
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaComplianceReviewMessageDetails: BetaComplianceReviewMessageDetails) =
            apply {
                content = betaComplianceReviewMessageDetails.content
                contentUnavailableReason =
                    betaComplianceReviewMessageDetails.contentUnavailableReason
                model = betaComplianceReviewMessageDetails.model
                type = betaComplianceReviewMessageDetails.type
                additionalProperties =
                    betaComplianceReviewMessageDetails.additionalProperties.toMutableMap()
            }

        /**
         * The stored request and response. Null when `content_unavailable_reason` is set. New types
         * can appear, so accept a type that you don't know.
         */
        fun content(content: BetaComplianceReviewMessageContent?) =
            content(JsonField.ofNullable(content))

        /** Alias for calling [Builder.content] with `content.orElse(null)`. */
        fun content(content: Optional<BetaComplianceReviewMessageContent>) =
            content(content.getOrNull())

        /**
         * Sets [Builder.content] to an arbitrary JSON value.
         *
         * You should usually call [Builder.content] with a well-typed
         * [BetaComplianceReviewMessageContent] value instead. This method is primarily for setting
         * the field to an undocumented or not yet supported value.
         */
        fun content(content: JsonField<BetaComplianceReviewMessageContent>) = apply {
            this.content = content
        }

        /**
         * Alias for calling [content] with
         * `BetaComplianceReviewMessageContent.ofCreateMessage(createMessage)`.
         */
        fun content(createMessage: BetaComplianceReviewCreateMessageContent) =
            content(BetaComplianceReviewMessageContent.ofCreateMessage(createMessage))

        /**
         * Why `content` is null. Null when `content` is set. New types can appear, so accept a type
         * that you don't know.
         */
        fun contentUnavailableReason(
            contentUnavailableReason: BetaComplianceReviewContentUnavailableReason?
        ) = contentUnavailableReason(JsonField.ofNullable(contentUnavailableReason))

        /**
         * Alias for calling [Builder.contentUnavailableReason] with
         * `contentUnavailableReason.orElse(null)`.
         */
        fun contentUnavailableReason(
            contentUnavailableReason: Optional<BetaComplianceReviewContentUnavailableReason>
        ) = contentUnavailableReason(contentUnavailableReason.getOrNull())

        /**
         * Sets [Builder.contentUnavailableReason] to an arbitrary JSON value.
         *
         * You should usually call [Builder.contentUnavailableReason] with a well-typed
         * [BetaComplianceReviewContentUnavailableReason] value instead. This method is primarily
         * for setting the field to an undocumented or not yet supported value.
         */
        fun contentUnavailableReason(
            contentUnavailableReason: JsonField<BetaComplianceReviewContentUnavailableReason>
        ) = apply { this.contentUnavailableReason = contentUnavailableReason }

        /**
         * Alias for calling [contentUnavailableReason] with
         * `BetaComplianceReviewContentUnavailableReason.ofNotCaptured(notCaptured)`.
         */
        fun contentUnavailableReason(notCaptured: BetaComplianceReviewContentNotCaptured) =
            contentUnavailableReason(
                BetaComplianceReviewContentUnavailableReason.ofNotCaptured(notCaptured)
            )

        /**
         * Alias for calling [contentUnavailableReason] with
         * `BetaComplianceReviewContentUnavailableReason.ofStoredCopyUnavailable(storedCopyUnavailable)`.
         */
        fun contentUnavailableReason(
            storedCopyUnavailable: BetaComplianceReviewStoredCopyUnavailable
        ) =
            contentUnavailableReason(
                BetaComplianceReviewContentUnavailableReason.ofStoredCopyUnavailable(
                    storedCopyUnavailable
                )
            )

        /** The model that the call named. Null when none was recorded. */
        fun model(model: String?) = model(JsonField.ofNullable(model))

        /** Alias for calling [Builder.model] with `model.orElse(null)`. */
        fun model(model: Optional<String>) = model(model.getOrNull())

        /**
         * Sets [Builder.model] to an arbitrary JSON value.
         *
         * You should usually call [Builder.model] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun model(model: JsonField<String>) = apply { this.model = model }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("message")
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
         * Returns an immutable instance of [BetaComplianceReviewMessageDetails].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .content()
         * .contentUnavailableReason()
         * .model()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaComplianceReviewMessageDetails =
            BetaComplianceReviewMessageDetails(
                checkRequired("content", content),
                checkRequired("contentUnavailableReason", contentUnavailableReason),
                checkRequired("model", model),
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
    fun validate(): BetaComplianceReviewMessageDetails = apply {
        if (validated) {
            return@apply
        }

        content().ifPresent { it.validate() }
        contentUnavailableReason().ifPresent { it.validate() }
        model()
        _type().let {
            if (it != JsonValue.from("message")) {
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
        (content.asKnown().getOrNull()?.validity() ?: 0) +
            (contentUnavailableReason.asKnown().getOrNull()?.validity() ?: 0) +
            (if (model.asKnown().isPresent) 1 else 0) +
            type.let { if (it == JsonValue.from("message")) 1 else 0 }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaComplianceReviewMessageDetails &&
            content == other.content &&
            contentUnavailableReason == other.contentUnavailableReason &&
            model == other.model &&
            type == other.type &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(content, contentUnavailableReason, model, type, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaComplianceReviewMessageDetails{content=$content, contentUnavailableReason=$contentUnavailableReason, model=$model, type=$type, additionalProperties=$additionalProperties}"
}
