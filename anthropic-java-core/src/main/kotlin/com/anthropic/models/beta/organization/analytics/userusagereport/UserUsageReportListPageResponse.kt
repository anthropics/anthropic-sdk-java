package com.anthropic.models.beta.organization.analytics.userusagereport

import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.checkKnown
import com.anthropic.core.checkRequired
import com.anthropic.core.toImmutable
import com.anthropic.errors.AnthropicInvalidDataException
import com.anthropic.models.beta.organization.analytics.BetaAnalyticsUsageUsersItem
import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import java.time.OffsetDateTime
import java.util.Collections
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

class UserUsageReportListPageResponse
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val data: JsonField<List<BetaAnalyticsUsageUsersItem>>,
    private val dataRefreshedAt: JsonField<OffsetDateTime>,
    private val hasMore: JsonField<Boolean>,
    private val nextPage: JsonField<String>,
    private val organizationId: JsonField<String>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("data")
        @ExcludeMissing
        data: JsonField<List<BetaAnalyticsUsageUsersItem>> = JsonMissing.of(),
        @JsonProperty("data_refreshed_at")
        @ExcludeMissing
        dataRefreshedAt: JsonField<OffsetDateTime> = JsonMissing.of(),
        @JsonProperty("has_more") @ExcludeMissing hasMore: JsonField<Boolean> = JsonMissing.of(),
        @JsonProperty("next_page") @ExcludeMissing nextPage: JsonField<String> = JsonMissing.of(),
        @JsonProperty("organization_id")
        @ExcludeMissing
        organizationId: JsonField<String> = JsonMissing.of(),
    ) : this(data, dataRefreshedAt, hasMore, nextPage, organizationId, mutableMapOf())

    /**
     * Rows for this page, ranked by `order_by` in the `order` direction. One row per user, or
     * several per user when `group_by[]` or `bucket_width` breaks that user's usage or cost out
     * across rows. Rows split out by `cost_type` or `token_type` (cost endpoint only) stay adjacent
     * and are ranked as one unit.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun data(): List<BetaAnalyticsUsageUsersItem> = data.getRequired("data")

    /**
     * RFC 3339 timestamp of the export this response was served from. Null when no export yet
     * covers any part of the requested range, in which case `data` is empty. Data beyond this
     * watermark is incomplete; for stable results, set `ending_at` to this value or earlier. Data
     * is typically refreshed every 4 hours but not final until about 30 days after the usage date
     * (late-arriving events, reconciliation adjustments).
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun dataRefreshedAt(): Optional<OffsetDateTime> =
        dataRefreshedAt.getOptional("data_refreshed_at")

    /**
     * Whether another page is available. When true, pass `next_page` as the `page` parameter to
     * fetch it.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun hasMore(): Boolean = hasMore.getRequired("has_more")

    /**
     * Opaque cursor for the next page, or null when `has_more` is false. Pass it as the `page`
     * parameter, keeping the other parameters unchanged. A cursor can expire after the underlying
     * data refreshes; the request then returns HTTP 410 and pagination must restart from the first
     * page.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun nextPage(): Optional<String> = nextPage.getOptional("next_page")

    /**
     * ID of the Organization.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun organizationId(): String = organizationId.getRequired("organization_id")

    /**
     * Returns the raw JSON value of [data].
     *
     * Unlike [data], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("data")
    @ExcludeMissing
    fun _data(): JsonField<List<BetaAnalyticsUsageUsersItem>> = data

    /**
     * Returns the raw JSON value of [dataRefreshedAt].
     *
     * Unlike [dataRefreshedAt], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("data_refreshed_at")
    @ExcludeMissing
    fun _dataRefreshedAt(): JsonField<OffsetDateTime> = dataRefreshedAt

    /**
     * Returns the raw JSON value of [hasMore].
     *
     * Unlike [hasMore], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("has_more") @ExcludeMissing fun _hasMore(): JsonField<Boolean> = hasMore

    /**
     * Returns the raw JSON value of [nextPage].
     *
     * Unlike [nextPage], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("next_page") @ExcludeMissing fun _nextPage(): JsonField<String> = nextPage

    /**
     * Returns the raw JSON value of [organizationId].
     *
     * Unlike [organizationId], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("organization_id")
    @ExcludeMissing
    fun _organizationId(): JsonField<String> = organizationId

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
         * [UserUsageReportListPageResponse].
         *
         * The following fields are required:
         * ```java
         * .data()
         * .dataRefreshedAt()
         * .hasMore()
         * .nextPage()
         * .organizationId()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [UserUsageReportListPageResponse]. */
    class Builder internal constructor() {

        private var data: JsonField<MutableList<BetaAnalyticsUsageUsersItem>>? = null
        private var dataRefreshedAt: JsonField<OffsetDateTime>? = null
        private var hasMore: JsonField<Boolean>? = null
        private var nextPage: JsonField<String>? = null
        private var organizationId: JsonField<String>? = null
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(userUsageReportListPageResponse: UserUsageReportListPageResponse) =
            apply {
                data =
                    userUsageReportListPageResponse.data
                        .map { it.toMutableList() }
                        .takeUnless { it.isMissing() }
                dataRefreshedAt = userUsageReportListPageResponse.dataRefreshedAt
                hasMore = userUsageReportListPageResponse.hasMore
                nextPage = userUsageReportListPageResponse.nextPage
                organizationId = userUsageReportListPageResponse.organizationId
                additionalProperties =
                    userUsageReportListPageResponse.additionalProperties.toMutableMap()
            }

        /**
         * Rows for this page, ranked by `order_by` in the `order` direction. One row per user, or
         * several per user when `group_by[]` or `bucket_width` breaks that user's usage or cost out
         * across rows. Rows split out by `cost_type` or `token_type` (cost endpoint only) stay
         * adjacent and are ranked as one unit.
         */
        fun data(data: List<BetaAnalyticsUsageUsersItem>) = data(JsonField.of(data))

        /**
         * Sets [Builder.data] to an arbitrary JSON value.
         *
         * You should usually call [Builder.data] with a well-typed
         * `List<BetaAnalyticsUsageUsersItem>` value instead. This method is primarily for setting
         * the field to an undocumented or not yet supported value.
         */
        fun data(data: JsonField<List<BetaAnalyticsUsageUsersItem>>) = apply {
            this.data = data.map { it.toMutableList() }
        }

        /**
         * Adds a single [BetaAnalyticsUsageUsersItem] to [Builder.data].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addData(data: BetaAnalyticsUsageUsersItem) = apply {
            this.data =
                (this.data ?: JsonField.of(mutableListOf())).also {
                    checkKnown("data", it).add(data)
                }
        }

        /**
         * RFC 3339 timestamp of the export this response was served from. Null when no export yet
         * covers any part of the requested range, in which case `data` is empty. Data beyond this
         * watermark is incomplete; for stable results, set `ending_at` to this value or earlier.
         * Data is typically refreshed every 4 hours but not final until about 30 days after the
         * usage date (late-arriving events, reconciliation adjustments).
         */
        fun dataRefreshedAt(dataRefreshedAt: OffsetDateTime?) =
            dataRefreshedAt(JsonField.ofNullable(dataRefreshedAt))

        /** Alias for calling [Builder.dataRefreshedAt] with `dataRefreshedAt.orElse(null)`. */
        fun dataRefreshedAt(dataRefreshedAt: Optional<OffsetDateTime>) =
            dataRefreshedAt(dataRefreshedAt.getOrNull())

        /**
         * Sets [Builder.dataRefreshedAt] to an arbitrary JSON value.
         *
         * You should usually call [Builder.dataRefreshedAt] with a well-typed [OffsetDateTime]
         * value instead. This method is primarily for setting the field to an undocumented or not
         * yet supported value.
         */
        fun dataRefreshedAt(dataRefreshedAt: JsonField<OffsetDateTime>) = apply {
            this.dataRefreshedAt = dataRefreshedAt
        }

        /**
         * Whether another page is available. When true, pass `next_page` as the `page` parameter to
         * fetch it.
         */
        fun hasMore(hasMore: Boolean) = hasMore(JsonField.of(hasMore))

        /**
         * Sets [Builder.hasMore] to an arbitrary JSON value.
         *
         * You should usually call [Builder.hasMore] with a well-typed [Boolean] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun hasMore(hasMore: JsonField<Boolean>) = apply { this.hasMore = hasMore }

        /**
         * Opaque cursor for the next page, or null when `has_more` is false. Pass it as the `page`
         * parameter, keeping the other parameters unchanged. A cursor can expire after the
         * underlying data refreshes; the request then returns HTTP 410 and pagination must restart
         * from the first page.
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

        /** ID of the Organization. */
        fun organizationId(organizationId: String) = organizationId(JsonField.of(organizationId))

        /**
         * Sets [Builder.organizationId] to an arbitrary JSON value.
         *
         * You should usually call [Builder.organizationId] with a well-typed [String] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun organizationId(organizationId: JsonField<String>) = apply {
            this.organizationId = organizationId
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
         * Returns an immutable instance of [UserUsageReportListPageResponse].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .data()
         * .dataRefreshedAt()
         * .hasMore()
         * .nextPage()
         * .organizationId()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): UserUsageReportListPageResponse =
            UserUsageReportListPageResponse(
                checkRequired("data", data).map { it.toImmutable() },
                checkRequired("dataRefreshedAt", dataRefreshedAt),
                checkRequired("hasMore", hasMore),
                checkRequired("nextPage", nextPage),
                checkRequired("organizationId", organizationId),
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
    fun validate(): UserUsageReportListPageResponse = apply {
        if (validated) {
            return@apply
        }

        data().forEach { it.validate() }
        dataRefreshedAt()
        hasMore()
        nextPage()
        organizationId()
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
            (if (dataRefreshedAt.asKnown().isPresent) 1 else 0) +
            (if (hasMore.asKnown().isPresent) 1 else 0) +
            (if (nextPage.asKnown().isPresent) 1 else 0) +
            (if (organizationId.asKnown().isPresent) 1 else 0)

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is UserUsageReportListPageResponse &&
            data == other.data &&
            dataRefreshedAt == other.dataRefreshedAt &&
            hasMore == other.hasMore &&
            nextPage == other.nextPage &&
            organizationId == other.organizationId &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(data, dataRefreshedAt, hasMore, nextPage, organizationId, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "UserUsageReportListPageResponse{data=$data, dataRefreshedAt=$dataRefreshedAt, hasMore=$hasMore, nextPage=$nextPage, organizationId=$organizationId, additionalProperties=$additionalProperties}"
}
