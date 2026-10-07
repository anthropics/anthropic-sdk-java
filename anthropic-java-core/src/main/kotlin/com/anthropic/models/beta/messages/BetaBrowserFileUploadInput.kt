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
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/**
 * Set the value of a file-input element to one or more files. The target must be an element
 * reference; at least one of paths or document_ids is required.
 */
class BetaBrowserFileUploadInput
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val target: JsonField<BetaBrowserRefTarget>,
    private val documentIds: JsonField<List<String>>,
    private val paths: JsonField<List<String>>,
    private val tabId: JsonField<String>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("target")
        @ExcludeMissing
        target: JsonField<BetaBrowserRefTarget> = JsonMissing.of(),
        @JsonProperty("document_ids")
        @ExcludeMissing
        documentIds: JsonField<List<String>> = JsonMissing.of(),
        @JsonProperty("paths") @ExcludeMissing paths: JsonField<List<String>> = JsonMissing.of(),
        @JsonProperty("tab_id") @ExcludeMissing tabId: JsonField<String> = JsonMissing.of(),
    ) : this(target, documentIds, paths, tabId, mutableMapOf())

    /**
     * An element on the page, identified by a reference from a prior `read_page` or `find` result.
     * References are scoped to the tab that produced them and become stale after navigation or a
     * major re-render.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun target(): BetaBrowserRefTarget = target.getRequired("target")

    /**
     * References to files the harness has staged, for deployments where the browser executor cannot
     * read the caller's filesystem.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun documentIds(): Optional<List<String>> = documentIds.getOptional("document_ids")

    /**
     * File paths on the browser executor's filesystem.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun paths(): Optional<List<String>> = paths.getOptional("paths")

    /**
     * Tab to act on. Defaults to the active tab when omitted.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun tabId(): Optional<String> = tabId.getOptional("tab_id")

    /**
     * Returns the raw JSON value of [target].
     *
     * Unlike [target], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("target") @ExcludeMissing fun _target(): JsonField<BetaBrowserRefTarget> = target

    /**
     * Returns the raw JSON value of [documentIds].
     *
     * Unlike [documentIds], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("document_ids")
    @ExcludeMissing
    fun _documentIds(): JsonField<List<String>> = documentIds

    /**
     * Returns the raw JSON value of [paths].
     *
     * Unlike [paths], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("paths") @ExcludeMissing fun _paths(): JsonField<List<String>> = paths

    /**
     * Returns the raw JSON value of [tabId].
     *
     * Unlike [tabId], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("tab_id") @ExcludeMissing fun _tabId(): JsonField<String> = tabId

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
         * Returns a mutable builder for constructing an instance of [BetaBrowserFileUploadInput].
         *
         * The following fields are required:
         * ```java
         * .target()
         * ```
         */
        @JvmStatic fun builder() = Builder()

        /**
         * Returns an immutable instance of [BetaBrowserFileUploadInput] with the required [target]
         * set to the given value.
         */
        @JvmStatic fun of(target: BetaBrowserRefTarget) = builder().target(target).build()
    }

    /** A builder for [BetaBrowserFileUploadInput]. */
    class Builder internal constructor() {

        private var target: JsonField<BetaBrowserRefTarget>? = null
        private var documentIds: JsonField<MutableList<String>>? = null
        private var paths: JsonField<MutableList<String>>? = null
        private var tabId: JsonField<String> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaBrowserFileUploadInput: BetaBrowserFileUploadInput) = apply {
            target = betaBrowserFileUploadInput.target
            documentIds =
                betaBrowserFileUploadInput.documentIds
                    .map { it.toMutableList() }
                    .takeUnless { it.isMissing() }
            paths =
                betaBrowserFileUploadInput.paths
                    .map { it.toMutableList() }
                    .takeUnless { it.isMissing() }
            tabId = betaBrowserFileUploadInput.tabId
            additionalProperties = betaBrowserFileUploadInput.additionalProperties.toMutableMap()
        }

        /**
         * An element on the page, identified by a reference from a prior `read_page` or `find`
         * result. References are scoped to the tab that produced them and become stale after
         * navigation or a major re-render.
         */
        fun target(target: BetaBrowserRefTarget) = target(JsonField.of(target))

        /**
         * Sets [Builder.target] to an arbitrary JSON value.
         *
         * You should usually call [Builder.target] with a well-typed [BetaBrowserRefTarget] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun target(target: JsonField<BetaBrowserRefTarget>) = apply { this.target = target }

        /**
         * References to files the harness has staged, for deployments where the browser executor
         * cannot read the caller's filesystem.
         */
        fun documentIds(documentIds: List<String>?) = documentIds(JsonField.ofNullable(documentIds))

        /** Alias for calling [Builder.documentIds] with `documentIds.orElse(null)`. */
        fun documentIds(documentIds: Optional<List<String>>) = documentIds(documentIds.getOrNull())

        /**
         * Sets [Builder.documentIds] to an arbitrary JSON value.
         *
         * You should usually call [Builder.documentIds] with a well-typed `List<String>` value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun documentIds(documentIds: JsonField<List<String>>) = apply {
            this.documentIds = documentIds.map { it.toMutableList() }
        }

        /**
         * Adds a single [String] to [documentIds].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addDocumentId(documentId: String) = apply {
            documentIds =
                (documentIds ?: JsonField.of(mutableListOf())).also {
                    checkKnown("documentIds", it).add(documentId)
                }
        }

        /** File paths on the browser executor's filesystem. */
        fun paths(paths: List<String>?) = paths(JsonField.ofNullable(paths))

        /** Alias for calling [Builder.paths] with `paths.orElse(null)`. */
        fun paths(paths: Optional<List<String>>) = paths(paths.getOrNull())

        /**
         * Sets [Builder.paths] to an arbitrary JSON value.
         *
         * You should usually call [Builder.paths] with a well-typed `List<String>` value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun paths(paths: JsonField<List<String>>) = apply {
            this.paths = paths.map { it.toMutableList() }
        }

        /**
         * Adds a single [String] to [paths].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addPath(path: String) = apply {
            paths =
                (paths ?: JsonField.of(mutableListOf())).also { checkKnown("paths", it).add(path) }
        }

        /** Tab to act on. Defaults to the active tab when omitted. */
        fun tabId(tabId: String?) = tabId(JsonField.ofNullable(tabId))

        /** Alias for calling [Builder.tabId] with `tabId.orElse(null)`. */
        fun tabId(tabId: Optional<String>) = tabId(tabId.getOrNull())

        /**
         * Sets [Builder.tabId] to an arbitrary JSON value.
         *
         * You should usually call [Builder.tabId] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun tabId(tabId: JsonField<String>) = apply { this.tabId = tabId }

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
         * Returns an immutable instance of [BetaBrowserFileUploadInput].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .target()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaBrowserFileUploadInput =
            BetaBrowserFileUploadInput(
                checkRequired("target", target),
                (documentIds ?: JsonMissing.of()).map { it.toImmutable() },
                (paths ?: JsonMissing.of()).map { it.toImmutable() },
                tabId,
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
    fun validate(): BetaBrowserFileUploadInput = apply {
        if (validated) {
            return@apply
        }

        target().validate()
        documentIds()
        paths()
        tabId()
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
        (target.asKnown().getOrNull()?.validity() ?: 0) +
            (documentIds.asKnown().getOrNull()?.size ?: 0) +
            (paths.asKnown().getOrNull()?.size ?: 0) +
            (if (tabId.asKnown().isPresent) 1 else 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaBrowserFileUploadInput &&
            target == other.target &&
            documentIds == other.documentIds &&
            paths == other.paths &&
            tabId == other.tabId &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(target, documentIds, paths, tabId, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaBrowserFileUploadInput{target=$target, documentIds=$documentIds, paths=$paths, tabId=$tabId, additionalProperties=$additionalProperties}"
}
