package com.anthropic.models.beta.compliance.review.cases

import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.Params
import com.anthropic.core.checkKnown
import com.anthropic.core.checkRequired
import com.anthropic.core.http.Headers
import com.anthropic.core.http.QueryParams
import com.anthropic.core.opentelemetry.SpanData
import com.anthropic.core.toImmutable
import com.anthropic.errors.AnthropicInvalidDataException
import com.anthropic.models.beta.AnthropicBeta
import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.Collections
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/**
 * Send your conclusion on a review case. A response closes the case, and a closed case can't be
 * reopened.
 *
 * Only the first response counts. A request is safe to send again: if the closed case has a
 * response with the same field values, in any list order, the API returns 200 and changes nothing.
 * If the closed case has a different response, or none, the API returns a 400 with `case_closed` in
 * `error.details.error_code`.
 *
 * If your end customer reviews this case instead of you, the API returns a 403 with
 * `case_reviewed_by_end_customer` in `error.details.error_code`.
 *
 * An Admin API key with the `write:compliance_review_cases` scope can call this endpoint.
 */
class CaseRespondParams
private constructor(
    private val caseId: String?,
    private val betas: List<AnthropicBeta>?,
    private val body: Body,
    private val additionalHeaders: Headers,
    private val additionalQueryParams: QueryParams,
) : Params {

    /** The ID of the case (`rvc_...`). */
    fun caseId(): Optional<String> = Optional.ofNullable(caseId)

    /** Optional header to specify the beta version(s) you want to use. */
    fun betas(): Optional<List<AnthropicBeta>> = Optional.ofNullable(betas)

    /**
     * Your conclusion on the case as a whole. Anthropic doesn't check it against `verdicts`.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun determination(): BetaComplianceReviewDetermination = body.determination()

    /**
     * The policy categories that you found were violated. Send one to four with `violation`, and
     * none with `no_violation`. They need not match the case's own. Each value is one of `cyber`,
     * `bio`, `chem`, `radnuc` (radiological or nuclear weapons), `cgw` (conventional weapons),
     * `mgw` (military-grade weapons systems), `io` (influence operations), `fraud`,
     * `violent_extremism`, `surveillance`, `human_exploitation`, `self_harm`, `adult_content`,
     * `reasoning_extraction`, `frontier_llm` (use of the model to accelerate frontier AI research
     * and development in restricted ways), `compromised_key` or `other`, and can appear once.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun policyCategories(): Optional<List<BetaComplianceReviewViolatedPolicyCategory>> =
        body.policyCategories()

    /**
     * Your conclusions on individual messages of the case. They need not cover every message, and
     * each `item_id` can appear once.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun verdicts(): Optional<List<BetaComplianceReviewVerdictParam>> = body.verdicts()

    /**
     * Returns the raw JSON value of [determination].
     *
     * Unlike [determination], this method doesn't throw if the JSON field has an unexpected type.
     */
    fun _determination(): JsonField<BetaComplianceReviewDetermination> = body._determination()

    /**
     * Returns the raw JSON value of [policyCategories].
     *
     * Unlike [policyCategories], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    fun _policyCategories(): JsonField<List<BetaComplianceReviewViolatedPolicyCategory>> =
        body._policyCategories()

    /**
     * Returns the raw JSON value of [verdicts].
     *
     * Unlike [verdicts], this method doesn't throw if the JSON field has an unexpected type.
     */
    fun _verdicts(): JsonField<List<BetaComplianceReviewVerdictParam>> = body._verdicts()

    fun _additionalBodyProperties(): Map<String, JsonValue> = body._additionalProperties()

    /** Additional headers to send with the request. */
    fun _additionalHeaders(): Headers = additionalHeaders

    /** Additional query param to send with the request. */
    fun _additionalQueryParams(): QueryParams = additionalQueryParams

    fun toBuilder() = Builder().from(this)

    companion object {

        /**
         * Returns a mutable builder for constructing an instance of [CaseRespondParams].
         *
         * The following fields are required:
         * ```java
         * .determination()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [CaseRespondParams]. */
    class Builder internal constructor() {

        private var caseId: String? = null
        private var betas: MutableList<AnthropicBeta>? = null
        private var body: Body.Builder = Body.builder()
        private var additionalHeaders: Headers.Builder = Headers.builder()
        private var additionalQueryParams: QueryParams.Builder = QueryParams.builder()

        @JvmSynthetic
        internal fun from(caseRespondParams: CaseRespondParams) = apply {
            caseId = caseRespondParams.caseId
            betas = caseRespondParams.betas?.toMutableList()
            body = caseRespondParams.body.toBuilder()
            additionalHeaders = caseRespondParams.additionalHeaders.toBuilder()
            additionalQueryParams = caseRespondParams.additionalQueryParams.toBuilder()
        }

        /** The ID of the case (`rvc_...`). */
        fun caseId(caseId: String?) = apply { this.caseId = caseId }

        /** Alias for calling [Builder.caseId] with `caseId.orElse(null)`. */
        fun caseId(caseId: Optional<String>) = caseId(caseId.getOrNull())

        /** Optional header to specify the beta version(s) you want to use. */
        fun betas(betas: List<AnthropicBeta>?) = apply { this.betas = betas?.toMutableList() }

        /** Alias for calling [Builder.betas] with `betas.orElse(null)`. */
        fun betas(betas: Optional<List<AnthropicBeta>>) = betas(betas.getOrNull())

        /**
         * Adds a single [AnthropicBeta] to [betas].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addBeta(beta: AnthropicBeta) = apply {
            betas = (betas ?: mutableListOf()).apply { add(beta) }
        }

        /**
         * Sets [addBeta] to an arbitrary [String].
         *
         * You should usually call [addBeta] with a well-typed [AnthropicBeta] constant instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun addBeta(value: String) = addBeta(AnthropicBeta.of(value))

        /**
         * Sets the entire request body.
         *
         * This is generally only useful if you are already constructing the body separately.
         * Otherwise, it's more convenient to use the top-level setters instead:
         * - [determination]
         * - [policyCategories]
         * - [verdicts]
         */
        fun body(body: Body) = apply { this.body = body.toBuilder() }

        /**
         * Your conclusion on the case as a whole. Anthropic doesn't check it against `verdicts`.
         */
        fun determination(determination: BetaComplianceReviewDetermination) = apply {
            body.determination(determination)
        }

        /**
         * Sets [Builder.determination] to an arbitrary JSON value.
         *
         * You should usually call [Builder.determination] with a well-typed
         * [BetaComplianceReviewDetermination] value instead. This method is primarily for setting
         * the field to an undocumented or not yet supported value.
         */
        fun determination(determination: JsonField<BetaComplianceReviewDetermination>) = apply {
            body.determination(determination)
        }

        /**
         * The policy categories that you found were violated. Send one to four with `violation`,
         * and none with `no_violation`. They need not match the case's own. Each value is one of
         * `cyber`, `bio`, `chem`, `radnuc` (radiological or nuclear weapons), `cgw` (conventional
         * weapons), `mgw` (military-grade weapons systems), `io` (influence operations), `fraud`,
         * `violent_extremism`, `surveillance`, `human_exploitation`, `self_harm`, `adult_content`,
         * `reasoning_extraction`, `frontier_llm` (use of the model to accelerate frontier AI
         * research and development in restricted ways), `compromised_key` or `other`, and can
         * appear once.
         */
        fun policyCategories(policyCategories: List<BetaComplianceReviewViolatedPolicyCategory>) =
            apply {
                body.policyCategories(policyCategories)
            }

        /**
         * Sets [Builder.policyCategories] to an arbitrary JSON value.
         *
         * You should usually call [Builder.policyCategories] with a well-typed
         * `List<BetaComplianceReviewViolatedPolicyCategory>` value instead. This method is
         * primarily for setting the field to an undocumented or not yet supported value.
         */
        fun policyCategories(
            policyCategories: JsonField<List<BetaComplianceReviewViolatedPolicyCategory>>
        ) = apply { body.policyCategories(policyCategories) }

        /**
         * Adds a single [BetaComplianceReviewViolatedPolicyCategory] to [policyCategories].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addPolicyCategory(policyCategory: BetaComplianceReviewViolatedPolicyCategory) = apply {
            body.addPolicyCategory(policyCategory)
        }

        /**
         * Your conclusions on individual messages of the case. They need not cover every message,
         * and each `item_id` can appear once.
         */
        fun verdicts(verdicts: List<BetaComplianceReviewVerdictParam>) = apply {
            body.verdicts(verdicts)
        }

        /**
         * Sets [Builder.verdicts] to an arbitrary JSON value.
         *
         * You should usually call [Builder.verdicts] with a well-typed
         * `List<BetaComplianceReviewVerdictParam>` value instead. This method is primarily for
         * setting the field to an undocumented or not yet supported value.
         */
        fun verdicts(verdicts: JsonField<List<BetaComplianceReviewVerdictParam>>) = apply {
            body.verdicts(verdicts)
        }

        /**
         * Adds a single [BetaComplianceReviewVerdictParam] to [verdicts].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addVerdict(verdict: BetaComplianceReviewVerdictParam) = apply {
            body.addVerdict(verdict)
        }

        fun additionalBodyProperties(additionalBodyProperties: Map<String, JsonValue>) = apply {
            body.additionalProperties(additionalBodyProperties)
        }

        fun putAdditionalBodyProperty(key: String, value: JsonValue) = apply {
            body.putAdditionalProperty(key, value)
        }

        fun putAllAdditionalBodyProperties(additionalBodyProperties: Map<String, JsonValue>) =
            apply {
                body.putAllAdditionalProperties(additionalBodyProperties)
            }

        fun removeAdditionalBodyProperty(key: String) = apply { body.removeAdditionalProperty(key) }

        fun removeAllAdditionalBodyProperties(keys: Set<String>) = apply {
            body.removeAllAdditionalProperties(keys)
        }

        fun additionalHeaders(additionalHeaders: Headers) = apply {
            this.additionalHeaders.clear()
            putAllAdditionalHeaders(additionalHeaders)
        }

        fun additionalHeaders(additionalHeaders: Map<String, Iterable<String>>) = apply {
            this.additionalHeaders.clear()
            putAllAdditionalHeaders(additionalHeaders)
        }

        fun putAdditionalHeader(name: String, value: String) = apply {
            additionalHeaders.put(name, value)
        }

        fun putAdditionalHeaders(name: String, values: Iterable<String>) = apply {
            additionalHeaders.put(name, values)
        }

        fun putAllAdditionalHeaders(additionalHeaders: Headers) = apply {
            this.additionalHeaders.putAll(additionalHeaders)
        }

        fun putAllAdditionalHeaders(additionalHeaders: Map<String, Iterable<String>>) = apply {
            this.additionalHeaders.putAll(additionalHeaders)
        }

        fun replaceAdditionalHeaders(name: String, value: String) = apply {
            additionalHeaders.replace(name, value)
        }

        fun replaceAdditionalHeaders(name: String, values: Iterable<String>) = apply {
            additionalHeaders.replace(name, values)
        }

        fun replaceAllAdditionalHeaders(additionalHeaders: Headers) = apply {
            this.additionalHeaders.replaceAll(additionalHeaders)
        }

        fun replaceAllAdditionalHeaders(additionalHeaders: Map<String, Iterable<String>>) = apply {
            this.additionalHeaders.replaceAll(additionalHeaders)
        }

        fun removeAdditionalHeaders(name: String) = apply { additionalHeaders.remove(name) }

        fun removeAllAdditionalHeaders(names: Set<String>) = apply {
            additionalHeaders.removeAll(names)
        }

        fun additionalQueryParams(additionalQueryParams: QueryParams) = apply {
            this.additionalQueryParams.clear()
            putAllAdditionalQueryParams(additionalQueryParams)
        }

        fun additionalQueryParams(additionalQueryParams: Map<String, Iterable<String>>) = apply {
            this.additionalQueryParams.clear()
            putAllAdditionalQueryParams(additionalQueryParams)
        }

        fun putAdditionalQueryParam(key: String, value: String) = apply {
            additionalQueryParams.put(key, value)
        }

        fun putAdditionalQueryParams(key: String, values: Iterable<String>) = apply {
            additionalQueryParams.put(key, values)
        }

        fun putAllAdditionalQueryParams(additionalQueryParams: QueryParams) = apply {
            this.additionalQueryParams.putAll(additionalQueryParams)
        }

        fun putAllAdditionalQueryParams(additionalQueryParams: Map<String, Iterable<String>>) =
            apply {
                this.additionalQueryParams.putAll(additionalQueryParams)
            }

        fun replaceAdditionalQueryParams(key: String, value: String) = apply {
            additionalQueryParams.replace(key, value)
        }

        fun replaceAdditionalQueryParams(key: String, values: Iterable<String>) = apply {
            additionalQueryParams.replace(key, values)
        }

        fun replaceAllAdditionalQueryParams(additionalQueryParams: QueryParams) = apply {
            this.additionalQueryParams.replaceAll(additionalQueryParams)
        }

        fun replaceAllAdditionalQueryParams(additionalQueryParams: Map<String, Iterable<String>>) =
            apply {
                this.additionalQueryParams.replaceAll(additionalQueryParams)
            }

        fun removeAdditionalQueryParams(key: String) = apply { additionalQueryParams.remove(key) }

        fun removeAllAdditionalQueryParams(keys: Set<String>) = apply {
            additionalQueryParams.removeAll(keys)
        }

        /**
         * Returns an immutable instance of [CaseRespondParams].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .determination()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): CaseRespondParams =
            CaseRespondParams(
                caseId,
                betas?.toImmutable(),
                body.build(),
                additionalHeaders.build(),
                additionalQueryParams.build(),
            )
    }

    fun _body(): Body = body

    fun _pathParam(index: Int): String =
        when (index) {
            0 -> caseId ?: ""
            else -> ""
        }

    override fun _headers(): Headers =
        Headers.builder()
            .apply {
                betas?.let {
                    if (it.isNotEmpty()) {
                        put("anthropic-beta", it.joinToString(","))
                    }
                }
                putAll(additionalHeaders)
            }
            .build()

    override fun _queryParams(): QueryParams = additionalQueryParams

    override fun _spanData(): SpanData =
        SpanData(
            name = "anthropic.compliance.review.cases.respond",
            route = "/v1/compliance/review/cases/{case_id}/respond",
            attributes = mapOf("anthropic.compliance.review.case.id" to _pathParam(0)),
        )

    class Body
    @JsonCreator(mode = JsonCreator.Mode.DISABLED)
    private constructor(
        private val determination: JsonField<BetaComplianceReviewDetermination>,
        private val policyCategories: JsonField<List<BetaComplianceReviewViolatedPolicyCategory>>,
        private val verdicts: JsonField<List<BetaComplianceReviewVerdictParam>>,
        private val additionalProperties: MutableMap<String, JsonValue>,
    ) {

        @JsonCreator
        private constructor(
            @JsonProperty("determination")
            @ExcludeMissing
            determination: JsonField<BetaComplianceReviewDetermination> = JsonMissing.of(),
            @JsonProperty("policy_categories")
            @ExcludeMissing
            policyCategories: JsonField<List<BetaComplianceReviewViolatedPolicyCategory>> =
                JsonMissing.of(),
            @JsonProperty("verdicts")
            @ExcludeMissing
            verdicts: JsonField<List<BetaComplianceReviewVerdictParam>> = JsonMissing.of(),
        ) : this(determination, policyCategories, verdicts, mutableMapOf())

        /**
         * Your conclusion on the case as a whole. Anthropic doesn't check it against `verdicts`.
         *
         * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
         *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
         */
        fun determination(): BetaComplianceReviewDetermination =
            determination.getRequired("determination")

        /**
         * The policy categories that you found were violated. Send one to four with `violation`,
         * and none with `no_violation`. They need not match the case's own. Each value is one of
         * `cyber`, `bio`, `chem`, `radnuc` (radiological or nuclear weapons), `cgw` (conventional
         * weapons), `mgw` (military-grade weapons systems), `io` (influence operations), `fraud`,
         * `violent_extremism`, `surveillance`, `human_exploitation`, `self_harm`, `adult_content`,
         * `reasoning_extraction`, `frontier_llm` (use of the model to accelerate frontier AI
         * research and development in restricted ways), `compromised_key` or `other`, and can
         * appear once.
         *
         * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if
         *   the server responded with an unexpected value).
         */
        fun policyCategories(): Optional<List<BetaComplianceReviewViolatedPolicyCategory>> =
            policyCategories.getOptional("policy_categories")

        /**
         * Your conclusions on individual messages of the case. They need not cover every message,
         * and each `item_id` can appear once.
         *
         * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if
         *   the server responded with an unexpected value).
         */
        fun verdicts(): Optional<List<BetaComplianceReviewVerdictParam>> =
            verdicts.getOptional("verdicts")

        /**
         * Returns the raw JSON value of [determination].
         *
         * Unlike [determination], this method doesn't throw if the JSON field has an unexpected
         * type.
         */
        @JsonProperty("determination")
        @ExcludeMissing
        fun _determination(): JsonField<BetaComplianceReviewDetermination> = determination

        /**
         * Returns the raw JSON value of [policyCategories].
         *
         * Unlike [policyCategories], this method doesn't throw if the JSON field has an unexpected
         * type.
         */
        @JsonProperty("policy_categories")
        @ExcludeMissing
        fun _policyCategories(): JsonField<List<BetaComplianceReviewViolatedPolicyCategory>> =
            policyCategories

        /**
         * Returns the raw JSON value of [verdicts].
         *
         * Unlike [verdicts], this method doesn't throw if the JSON field has an unexpected type.
         */
        @JsonProperty("verdicts")
        @ExcludeMissing
        fun _verdicts(): JsonField<List<BetaComplianceReviewVerdictParam>> = verdicts

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
             * Returns a mutable builder for constructing an instance of [Body].
             *
             * The following fields are required:
             * ```java
             * .determination()
             * ```
             */
            @JvmStatic fun builder() = Builder()

            /**
             * Returns an immutable instance of [Body] with the required [determination] set to the
             * given value.
             */
            @JvmStatic
            fun of(determination: BetaComplianceReviewDetermination) =
                builder().determination(determination).build()
        }

        /** A builder for [Body]. */
        class Builder internal constructor() {

            private var determination: JsonField<BetaComplianceReviewDetermination>? = null
            private var policyCategories:
                JsonField<MutableList<BetaComplianceReviewViolatedPolicyCategory>>? =
                null
            private var verdicts: JsonField<MutableList<BetaComplianceReviewVerdictParam>>? = null
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            @JvmSynthetic
            internal fun from(body: Body) = apply {
                determination = body.determination
                policyCategories =
                    body.policyCategories.map { it.toMutableList() }.takeUnless { it.isMissing() }
                verdicts = body.verdicts.map { it.toMutableList() }.takeUnless { it.isMissing() }
                additionalProperties = body.additionalProperties.toMutableMap()
            }

            /**
             * Your conclusion on the case as a whole. Anthropic doesn't check it against
             * `verdicts`.
             */
            fun determination(determination: BetaComplianceReviewDetermination) =
                determination(JsonField.of(determination))

            /**
             * Sets [Builder.determination] to an arbitrary JSON value.
             *
             * You should usually call [Builder.determination] with a well-typed
             * [BetaComplianceReviewDetermination] value instead. This method is primarily for
             * setting the field to an undocumented or not yet supported value.
             */
            fun determination(determination: JsonField<BetaComplianceReviewDetermination>) = apply {
                this.determination = determination
            }

            /**
             * The policy categories that you found were violated. Send one to four with
             * `violation`, and none with `no_violation`. They need not match the case's own. Each
             * value is one of `cyber`, `bio`, `chem`, `radnuc` (radiological or nuclear weapons),
             * `cgw` (conventional weapons), `mgw` (military-grade weapons systems), `io` (influence
             * operations), `fraud`, `violent_extremism`, `surveillance`, `human_exploitation`,
             * `self_harm`, `adult_content`, `reasoning_extraction`, `frontier_llm` (use of the
             * model to accelerate frontier AI research and development in restricted ways),
             * `compromised_key` or `other`, and can appear once.
             */
            fun policyCategories(
                policyCategories: List<BetaComplianceReviewViolatedPolicyCategory>
            ) = policyCategories(JsonField.of(policyCategories))

            /**
             * Sets [Builder.policyCategories] to an arbitrary JSON value.
             *
             * You should usually call [Builder.policyCategories] with a well-typed
             * `List<BetaComplianceReviewViolatedPolicyCategory>` value instead. This method is
             * primarily for setting the field to an undocumented or not yet supported value.
             */
            fun policyCategories(
                policyCategories: JsonField<List<BetaComplianceReviewViolatedPolicyCategory>>
            ) = apply { this.policyCategories = policyCategories.map { it.toMutableList() } }

            /**
             * Adds a single [BetaComplianceReviewViolatedPolicyCategory] to [policyCategories].
             *
             * @throws IllegalStateException if the field was previously set to a non-list.
             */
            fun addPolicyCategory(policyCategory: BetaComplianceReviewViolatedPolicyCategory) =
                apply {
                    policyCategories =
                        (policyCategories ?: JsonField.of(mutableListOf())).also {
                            checkKnown("policyCategories", it).add(policyCategory)
                        }
                }

            /**
             * Your conclusions on individual messages of the case. They need not cover every
             * message, and each `item_id` can appear once.
             */
            fun verdicts(verdicts: List<BetaComplianceReviewVerdictParam>) =
                verdicts(JsonField.of(verdicts))

            /**
             * Sets [Builder.verdicts] to an arbitrary JSON value.
             *
             * You should usually call [Builder.verdicts] with a well-typed
             * `List<BetaComplianceReviewVerdictParam>` value instead. This method is primarily for
             * setting the field to an undocumented or not yet supported value.
             */
            fun verdicts(verdicts: JsonField<List<BetaComplianceReviewVerdictParam>>) = apply {
                this.verdicts = verdicts.map { it.toMutableList() }
            }

            /**
             * Adds a single [BetaComplianceReviewVerdictParam] to [verdicts].
             *
             * @throws IllegalStateException if the field was previously set to a non-list.
             */
            fun addVerdict(verdict: BetaComplianceReviewVerdictParam) = apply {
                verdicts =
                    (verdicts ?: JsonField.of(mutableListOf())).also {
                        checkKnown("verdicts", it).add(verdict)
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
             * Returns an immutable instance of [Body].
             *
             * Further updates to this [Builder] will not mutate the returned instance.
             *
             * The following fields are required:
             * ```java
             * .determination()
             * ```
             *
             * @throws IllegalStateException if any required field is unset.
             */
            fun build(): Body =
                Body(
                    checkRequired("determination", determination),
                    (policyCategories ?: JsonMissing.of()).map { it.toImmutable() },
                    (verdicts ?: JsonMissing.of()).map { it.toImmutable() },
                    additionalProperties.toMutableMap(),
                )
        }

        private var validated: Boolean = false

        /**
         * Validates that the types of all values in this object match their expected types
         * recursively.
         *
         * This method is _not_ forwards compatible with new types from the API for existing fields.
         *
         * @throws AnthropicInvalidDataException if any value type in this object doesn't match its
         *   expected type.
         */
        fun validate(): Body = apply {
            if (validated) {
                return@apply
            }

            determination().validate()
            policyCategories().ifPresent { it.forEach { it.validate() } }
            verdicts().ifPresent { it.forEach { it.validate() } }
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
         * Returns a score indicating how many valid values are contained in this object
         * recursively.
         *
         * Used for best match union deserialization.
         */
        @JvmSynthetic
        internal fun validity(): Int =
            (determination.asKnown().getOrNull()?.validity() ?: 0) +
                (policyCategories.asKnown().getOrNull()?.sumOf { it.validity().toInt() } ?: 0) +
                (verdicts.asKnown().getOrNull()?.sumOf { it.validity().toInt() } ?: 0)

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Body &&
                determination == other.determination &&
                policyCategories == other.policyCategories &&
                verdicts == other.verdicts &&
                additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy {
            Objects.hash(determination, policyCategories, verdicts, additionalProperties)
        }

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "Body{determination=$determination, policyCategories=$policyCategories, verdicts=$verdicts, additionalProperties=$additionalProperties}"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is CaseRespondParams &&
            caseId == other.caseId &&
            betas == other.betas &&
            body == other.body &&
            additionalHeaders == other.additionalHeaders &&
            additionalQueryParams == other.additionalQueryParams
    }

    override fun hashCode(): Int =
        Objects.hash(caseId, betas, body, additionalHeaders, additionalQueryParams)

    override fun toString() =
        "CaseRespondParams{caseId=$caseId, betas=$betas, body=$body, additionalHeaders=$additionalHeaders, additionalQueryParams=$additionalQueryParams}"
}
