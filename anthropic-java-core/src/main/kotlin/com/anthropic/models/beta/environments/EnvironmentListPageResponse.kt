// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.environments

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
 * Response when listing environments.
 *
 * This response model uses opaque cursor-based pagination. Use the `page` query parameter with the
 * value from `next_page` to fetch the next page.
 */
class EnvironmentListPageResponse
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val data: JsonField<List<BetaEnvironment>>,
    private val nextPage: JsonField<String>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("data")
        @ExcludeMissing
        data: JsonField<List<BetaEnvironment>> = JsonMissing.of(),
        @JsonProperty("next_page") @ExcludeMissing nextPage: JsonField<String> = JsonMissing.of(),
    ) : this(data, nextPage, mutableMapOf())

    /**
     * List of environments.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun data(): List<BetaEnvironment> = data.getRequired("data")

    /**
     * Token for fetching the next page of results. If `null`, there are no more results available.
     * Pass this value to the `page` parameter in the next request.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun nextPage(): Optional<String> = nextPage.getOptional("next_page")

    /**
     * Returns the raw JSON value of [data].
     *
     * Unlike [data], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("data") @ExcludeMissing fun _data(): JsonField<List<BetaEnvironment>> = data

    /**
     * Returns the raw JSON value of [nextPage].
     *
     * Unlike [nextPage], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("next_page") @ExcludeMissing fun _nextPage(): JsonField<String> = nextPage

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
         * Returns a mutable builder for constructing an instance of [EnvironmentListPageResponse].
         *
         * The following fields are required:
         * ```java
         * .data()
         * .nextPage()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [EnvironmentListPageResponse]. */
    class Builder internal constructor() {

        private var data: JsonField<MutableList<BetaEnvironment>>? = null
        private var nextPage: JsonField<String>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(environmentListPageResponse: EnvironmentListPageResponse) = apply {
            data = environmentListPageResponse.data.map { it.toMutableList() }
            nextPage = environmentListPageResponse.nextPage
            additionalProperties = environmentListPageResponse.additionalProperties.toMutableMap()
        }

        /** List of environments. */
        fun data(data: List<BetaEnvironment>) = data(JsonField.of(data))

        /**
         * Sets [Builder.data] to an arbitrary JSON value.
         *
         * You should usually call [Builder.data] with a well-typed `List<BetaEnvironment>` value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun data(data: JsonField<List<BetaEnvironment>>) = apply {
            this.data = data.map { it.toMutableList() }
        }

        /**
         * Adds a single [BetaEnvironment] to [Builder.data].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addData(data: BetaEnvironment) = apply {
            this.data =
                (this.data ?: JsonField.of(mutableListOf())).also {
                    checkKnown("data", it).add(data)
                }
        }

        /**
         * Token for fetching the next page of results. If `null`, there are no more results
         * available. Pass this value to the `page` parameter in the next request.
         */
        fun nextPage(nextPage: String?) = nextPage(JsonField.ofNullable(nextPage))

        /** Alias for calling [Builder.nextPage] with `nextPage.orElse(null)`. */
        fun nextPage(nextPage: Optional<String>) = nextPage(nextPage.getOrNull())

        /**
         * Sets [Builder.nextPage] to an arbitrary JSON value.
         *
         * You should usually call [Builder.nextPage] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun nextPage(nextPage: JsonField<String>) = apply { this.nextPage = nextPage }

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
         * Returns an immutable instance of [EnvironmentListPageResponse].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .data()
         * .nextPage()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): EnvironmentListPageResponse =
            EnvironmentListPageResponse(
                checkRequired("data", data).map { it.toImmutable() },
                checkRequired("nextPage", nextPage),
                additionalProperties.toMutableMap(),
            )
    }

    private var validated: Boolean = false

    fun validate(): EnvironmentListPageResponse = apply {
        if (validated) {
            return@apply
        }

        data().forEach { it.validate() }
        nextPage()
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
        (data.asKnown().getOrNull()?.sumOf { it.validity().toInt() } ?: 0) +
            (if (nextPage.asKnown().isPresent) 1 else 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is EnvironmentListPageResponse &&
            data == other.data &&
            nextPage == other.nextPage &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy { Objects.hash(data, nextPage, additionalProperties) }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "EnvironmentListPageResponse{data=$data, nextPage=$nextPage, additionalProperties=$additionalProperties}"
}
