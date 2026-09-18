package com.anthropic.models.beta.organization.ratelimits

import com.anthropic.core.BaseDeserializer
import com.anthropic.core.BaseSerializer
import com.anthropic.core.Enum
import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.checkKnown
import com.anthropic.core.checkRequired
import com.anthropic.core.getOrThrow
import com.anthropic.core.getProperty
import com.anthropic.core.toImmutable
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.ObjectCodec
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.util.Collections
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

class BetaOrganizationRateLimit
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val id: JsonField<String>,
    private val group: JsonField<Group>,
    private val groupType: JsonField<GroupType>,
    private val limits: JsonField<List<BetaOrganizationRateLimitValue>>,
    private val models: JsonField<List<String>>,
    private val type: JsonValue,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("id") @ExcludeMissing id: JsonField<String> = JsonMissing.of(),
        @JsonProperty("group") @ExcludeMissing group: JsonField<Group> = JsonMissing.of(),
        @JsonProperty("group_type")
        @ExcludeMissing
        groupType: JsonField<GroupType> = JsonMissing.of(),
        @JsonProperty("limits")
        @ExcludeMissing
        limits: JsonField<List<BetaOrganizationRateLimitValue>> = JsonMissing.of(),
        @JsonProperty("models") @ExcludeMissing models: JsonField<List<String>> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonValue = JsonMissing.of(),
    ) : this(id, group, groupType, limits, models, type, mutableMapOf())

    /**
     * Identifier of this rate-limit entry. It is stable within the organization and differs between
     * organizations; the group's own identifier is `group.id`.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun id(): String = id.getRequired("id")

    /**
     * The rate-limit group this entry's limits apply to. Its `type` equals `group_type`.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun group(): Group = group.getRequired("group")

    /**
     * Deprecated: use `group.type` instead. The kind of rate-limit group this entry represents.
     * `model_group` entries apply to a family of models (listed in `models`); other values apply to
     * an API-surface category and have `models` set to `null`. Always equal to `group.type`.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    @Deprecated(
        "Use `group.type` instead. `group_type` is still returned and always equals `group.type`."
    )
    fun groupType(): GroupType = groupType.getRequired("group_type")

    /**
     * The limiter values that apply to this group.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun limits(): List<BetaOrganizationRateLimitValue> = limits.getRequired("limits")

    /**
     * Model names this entry's limits apply to, including aliases. `null` when `group_type` is not
     * `"model_group"`.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun models(): Optional<List<String>> = models.getOptional("models")

    /**
     * Object type. Always `rate_limit` for organization rate-limit entries.
     *
     * Expected to always return the following:
     * ```java
     * JsonValue.from("rate_limit")
     * ```
     *
     * However, this method can be useful for debugging and logging (e.g. if the server responded
     * with an unexpected value).
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonValue = type

    /**
     * Returns the raw JSON value of [id].
     *
     * Unlike [id], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("id") @ExcludeMissing fun _id(): JsonField<String> = id

    /**
     * Returns the raw JSON value of [group].
     *
     * Unlike [group], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("group") @ExcludeMissing fun _group(): JsonField<Group> = group

    /**
     * Returns the raw JSON value of [groupType].
     *
     * Unlike [groupType], this method doesn't throw if the JSON field has an unexpected type.
     */
    @Deprecated(
        "Use `group.type` instead. `group_type` is still returned and always equals `group.type`."
    )
    @JsonProperty("group_type")
    @ExcludeMissing
    fun _groupType(): JsonField<GroupType> = groupType

    /**
     * Returns the raw JSON value of [limits].
     *
     * Unlike [limits], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("limits")
    @ExcludeMissing
    fun _limits(): JsonField<List<BetaOrganizationRateLimitValue>> = limits

    /**
     * Returns the raw JSON value of [models].
     *
     * Unlike [models], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("models") @ExcludeMissing fun _models(): JsonField<List<String>> = models

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
         * Returns a mutable builder for constructing an instance of [BetaOrganizationRateLimit].
         *
         * The following fields are required:
         * ```java
         * .id()
         * .group()
         * .groupType()
         * .limits()
         * .models()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaOrganizationRateLimit]. */
    class Builder internal constructor() {

        private var id: JsonField<String>? = null
        private var group: JsonField<Group>? = null
        private var groupType: JsonField<GroupType>? = null
        private var limits: JsonField<MutableList<BetaOrganizationRateLimitValue>>? = null
        private var models: JsonField<MutableList<String>>? = null
        private var type: JsonValue = JsonValue.from("rate_limit")
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(betaOrganizationRateLimit: BetaOrganizationRateLimit) = apply {
            id = betaOrganizationRateLimit.id
            group = betaOrganizationRateLimit.group
            groupType = betaOrganizationRateLimit.groupType
            limits =
                betaOrganizationRateLimit.limits
                    .map { it.toMutableList() }
                    .takeUnless { it.isMissing() }
            models =
                betaOrganizationRateLimit.models
                    .map { it.toMutableList() }
                    .takeUnless { it.isMissing() }
            type = betaOrganizationRateLimit.type
            additionalProperties = betaOrganizationRateLimit.additionalProperties.toMutableMap()
        }

        /**
         * Identifier of this rate-limit entry. It is stable within the organization and differs
         * between organizations; the group's own identifier is `group.id`.
         */
        fun id(id: String) = id(JsonField.of(id))

        /**
         * Sets [Builder.id] to an arbitrary JSON value.
         *
         * You should usually call [Builder.id] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun id(id: JsonField<String>) = apply { this.id = id }

        /** The rate-limit group this entry's limits apply to. Its `type` equals `group_type`. */
        fun group(group: Group) = group(JsonField.of(group))

        /**
         * Sets [Builder.group] to an arbitrary JSON value.
         *
         * You should usually call [Builder.group] with a well-typed [Group] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun group(group: JsonField<Group>) = apply { this.group = group }

        /** Alias for calling [group] with `Group.ofModel(model)`. */
        fun group(model: BetaOrganizationRateLimitModelGroup) = group(Group.ofModel(model))

        /** Alias for calling [group] with `Group.ofBatch(batch)`. */
        fun group(batch: BetaOrganizationRateLimitBatchGroup) = group(Group.ofBatch(batch))

        /**
         * Alias for calling [group] with the following:
         * ```java
         * BetaOrganizationRateLimitBatchGroup.builder()
         *     .id(id)
         *     .build()
         * ```
         */
        fun batchGroup(id: String) =
            group(BetaOrganizationRateLimitBatchGroup.builder().id(id).build())

        /** Alias for calling [group] with `Group.ofTokenCount(tokenCount)`. */
        fun group(tokenCount: BetaOrganizationRateLimitTokenCountGroup) =
            group(Group.ofTokenCount(tokenCount))

        /**
         * Alias for calling [group] with the following:
         * ```java
         * BetaOrganizationRateLimitTokenCountGroup.builder()
         *     .id(id)
         *     .build()
         * ```
         */
        fun tokenCountGroup(id: String) =
            group(BetaOrganizationRateLimitTokenCountGroup.builder().id(id).build())

        /** Alias for calling [group] with `Group.ofFiles(files)`. */
        fun group(files: BetaOrganizationRateLimitFilesGroup) = group(Group.ofFiles(files))

        /**
         * Alias for calling [group] with the following:
         * ```java
         * BetaOrganizationRateLimitFilesGroup.builder()
         *     .id(id)
         *     .build()
         * ```
         */
        fun filesGroup(id: String) =
            group(BetaOrganizationRateLimitFilesGroup.builder().id(id).build())

        /** Alias for calling [group] with `Group.ofSkills(skills)`. */
        fun group(skills: BetaOrganizationRateLimitSkillsGroup) = group(Group.ofSkills(skills))

        /**
         * Alias for calling [group] with the following:
         * ```java
         * BetaOrganizationRateLimitSkillsGroup.builder()
         *     .id(id)
         *     .build()
         * ```
         */
        fun skillsGroup(id: String) =
            group(BetaOrganizationRateLimitSkillsGroup.builder().id(id).build())

        /** Alias for calling [group] with `Group.ofWebSearch(webSearch)`. */
        fun group(webSearch: BetaOrganizationRateLimitWebSearchGroup) =
            group(Group.ofWebSearch(webSearch))

        /**
         * Alias for calling [group] with the following:
         * ```java
         * BetaOrganizationRateLimitWebSearchGroup.builder()
         *     .id(id)
         *     .build()
         * ```
         */
        fun webSearchGroup(id: String) =
            group(BetaOrganizationRateLimitWebSearchGroup.builder().id(id).build())

        /**
         * Deprecated: use `group.type` instead. The kind of rate-limit group this entry represents.
         * `model_group` entries apply to a family of models (listed in `models`); other values
         * apply to an API-surface category and have `models` set to `null`. Always equal to
         * `group.type`.
         */
        @Deprecated(
            "Use `group.type` instead. `group_type` is still returned and always equals `group.type`."
        )
        fun groupType(groupType: GroupType) = groupType(JsonField.of(groupType))

        /**
         * Sets [Builder.groupType] to an arbitrary JSON value.
         *
         * You should usually call [Builder.groupType] with a well-typed [GroupType] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        @Deprecated(
            "Use `group.type` instead. `group_type` is still returned and always equals `group.type`."
        )
        fun groupType(groupType: JsonField<GroupType>) = apply { this.groupType = groupType }

        /** The limiter values that apply to this group. */
        fun limits(limits: List<BetaOrganizationRateLimitValue>) = limits(JsonField.of(limits))

        /**
         * Sets [Builder.limits] to an arbitrary JSON value.
         *
         * You should usually call [Builder.limits] with a well-typed
         * `List<BetaOrganizationRateLimitValue>` value instead. This method is primarily for
         * setting the field to an undocumented or not yet supported value.
         */
        fun limits(limits: JsonField<List<BetaOrganizationRateLimitValue>>) = apply {
            this.limits = limits.map { it.toMutableList() }
        }

        /**
         * Adds a single [BetaOrganizationRateLimitValue] to [limits].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addLimit(limit: BetaOrganizationRateLimitValue) = apply {
            limits =
                (limits ?: JsonField.of(mutableListOf())).also {
                    checkKnown("limits", it).add(limit)
                }
        }

        /**
         * Model names this entry's limits apply to, including aliases. `null` when `group_type` is
         * not `"model_group"`.
         */
        fun models(models: List<String>?) = models(JsonField.ofNullable(models))

        /** Alias for calling [Builder.models] with `models.orElse(null)`. */
        fun models(models: Optional<List<String>>) = models(models.getOrNull())

        /**
         * Sets [Builder.models] to an arbitrary JSON value.
         *
         * You should usually call [Builder.models] with a well-typed `List<String>` value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun models(models: JsonField<List<String>>) = apply {
            this.models = models.map { it.toMutableList() }
        }

        /**
         * Adds a single [String] to [models].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addModel(model: String) = apply {
            models =
                (models ?: JsonField.of(mutableListOf())).also {
                    checkKnown("models", it).add(model)
                }
        }

        /**
         * Sets the field to an arbitrary JSON value.
         *
         * It is usually unnecessary to call this method because the field defaults to the
         * following:
         * ```java
         * JsonValue.from("rate_limit")
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
         * Returns an immutable instance of [BetaOrganizationRateLimit].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .id()
         * .group()
         * .groupType()
         * .limits()
         * .models()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaOrganizationRateLimit =
            BetaOrganizationRateLimit(
                checkRequired("id", id),
                checkRequired("group", group),
                checkRequired("groupType", groupType),
                checkRequired("limits", limits).map { it.toImmutable() },
                checkRequired("models", models).map { it.toImmutable() },
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
    fun validate(): BetaOrganizationRateLimit = apply {
        if (validated) {
            return@apply
        }

        id()
        group().validate()
        groupType().validate()
        limits().forEach { it.validate() }
        models()
        _type().let {
            if (it != JsonValue.from("rate_limit")) {
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
        (if (id.asKnown().isPresent) 1 else 0) +
            (group.asKnown().getOrNull()?.validity() ?: 0) +
            (groupType.asKnown().getOrNull()?.validity() ?: 0) +
            (limits.asKnown().getOrNull()?.sumOf { it.validity().toInt() } ?: 0) +
            (models.asKnown().getOrNull()?.size ?: 0) +
            type.let { if (it == JsonValue.from("rate_limit")) 1 else 0 }

    /** The rate-limit group this entry's limits apply to. Its `type` equals `group_type`. */
    @JsonDeserialize(using = Group.Deserializer::class)
    @JsonSerialize(using = Group.Serializer::class)
    class Group
    private constructor(
        private val model: BetaOrganizationRateLimitModelGroup? = null,
        private val batch: BetaOrganizationRateLimitBatchGroup? = null,
        private val tokenCount: BetaOrganizationRateLimitTokenCountGroup? = null,
        private val files: BetaOrganizationRateLimitFilesGroup? = null,
        private val skills: BetaOrganizationRateLimitSkillsGroup? = null,
        private val webSearch: BetaOrganizationRateLimitWebSearchGroup? = null,
        private val _json: JsonValue? = null,
    ) {

        fun type(): Type =
            accept(
                object : Visitor<Type> {
                    override fun visitModel(model: BetaOrganizationRateLimitModelGroup): Type =
                        Type.MODEL_GROUP

                    override fun visitBatch(batch: BetaOrganizationRateLimitBatchGroup): Type =
                        Type.BATCH

                    override fun visitTokenCount(
                        tokenCount: BetaOrganizationRateLimitTokenCountGroup
                    ): Type = Type.TOKEN_COUNT

                    override fun visitFiles(files: BetaOrganizationRateLimitFilesGroup): Type =
                        Type.FILES

                    override fun visitSkills(skills: BetaOrganizationRateLimitSkillsGroup): Type =
                        Type.SKILLS

                    override fun visitWebSearch(
                        webSearch: BetaOrganizationRateLimitWebSearchGroup
                    ): Type = Type.WEB_SEARCH

                    override fun unknown(json: JsonValue?): Type =
                        Type.of(json?.asObject()?.getOrNull()?.get("type") ?: JsonMissing.of())
                }
            )

        fun id(): String =
            accept(
                object : Visitor<String> {
                    override fun visitModel(model: BetaOrganizationRateLimitModelGroup): String =
                        model.id()

                    override fun visitBatch(batch: BetaOrganizationRateLimitBatchGroup): String =
                        batch.id()

                    override fun visitTokenCount(
                        tokenCount: BetaOrganizationRateLimitTokenCountGroup
                    ): String = tokenCount.id()

                    override fun visitFiles(files: BetaOrganizationRateLimitFilesGroup): String =
                        files.id()

                    override fun visitSkills(skills: BetaOrganizationRateLimitSkillsGroup): String =
                        skills.id()

                    override fun visitWebSearch(
                        webSearch: BetaOrganizationRateLimitWebSearchGroup
                    ): String = webSearch.id()

                    override fun unknown(json: JsonValue?): String =
                        json.getProperty<String>("id").getRequired("id")
                }
            )

        fun model(): Optional<BetaOrganizationRateLimitModelGroup> = Optional.ofNullable(model)

        fun batch(): Optional<BetaOrganizationRateLimitBatchGroup> = Optional.ofNullable(batch)

        fun tokenCount(): Optional<BetaOrganizationRateLimitTokenCountGroup> =
            Optional.ofNullable(tokenCount)

        fun files(): Optional<BetaOrganizationRateLimitFilesGroup> = Optional.ofNullable(files)

        fun skills(): Optional<BetaOrganizationRateLimitSkillsGroup> = Optional.ofNullable(skills)

        fun webSearch(): Optional<BetaOrganizationRateLimitWebSearchGroup> =
            Optional.ofNullable(webSearch)

        fun isModel(): Boolean = model != null

        fun isBatch(): Boolean = batch != null

        fun isTokenCount(): Boolean = tokenCount != null

        fun isFiles(): Boolean = files != null

        fun isSkills(): Boolean = skills != null

        fun isWebSearch(): Boolean = webSearch != null

        fun asModel(): BetaOrganizationRateLimitModelGroup = model.getOrThrow("model")

        fun asBatch(): BetaOrganizationRateLimitBatchGroup = batch.getOrThrow("batch")

        fun asTokenCount(): BetaOrganizationRateLimitTokenCountGroup =
            tokenCount.getOrThrow("tokenCount")

        fun asFiles(): BetaOrganizationRateLimitFilesGroup = files.getOrThrow("files")

        fun asSkills(): BetaOrganizationRateLimitSkillsGroup = skills.getOrThrow("skills")

        fun asWebSearch(): BetaOrganizationRateLimitWebSearchGroup =
            webSearch.getOrThrow("webSearch")

        fun _json(): Optional<JsonValue> = Optional.ofNullable(_json)

        /**
         * Maps this instance's current variant to a value of type [T] using the given [visitor].
         *
         * Note that this method is _not_ forwards compatible with new variants from the API, unless
         * [visitor] overrides [Visitor.unknown]. To handle variants not known to this version of
         * the SDK gracefully, consider overriding [Visitor.unknown]:
         * ```java
         * import com.anthropic.core.JsonValue;
         * import java.util.Optional;
         *
         * Optional<String> result = group.accept(new Group.Visitor<Optional<String>>() {
         *     @Override
         *     public Optional<String> visitModel(BetaOrganizationRateLimitModelGroup model) {
         *         return Optional.of(model.toString());
         *     }
         *
         *     // ...
         *
         *     @Override
         *     public Optional<String> unknown(JsonValue json) {
         *         // Or inspect the `json`.
         *         return Optional.empty();
         *     }
         * });
         * ```
         *
         * @throws AnthropicInvalidDataException if [Visitor.unknown] is not overridden in [visitor]
         *   and the current variant is unknown.
         */
        fun <T> accept(visitor: Visitor<T>): T =
            when {
                model != null -> visitor.visitModel(model)
                batch != null -> visitor.visitBatch(batch)
                tokenCount != null -> visitor.visitTokenCount(tokenCount)
                files != null -> visitor.visitFiles(files)
                skills != null -> visitor.visitSkills(skills)
                webSearch != null -> visitor.visitWebSearch(webSearch)
                else -> visitor.unknown(_json)
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
        fun validate(): Group = apply {
            if (validated) {
                return@apply
            }

            accept(
                object : Visitor<Unit> {
                    override fun visitModel(model: BetaOrganizationRateLimitModelGroup) {
                        model.validate()
                    }

                    override fun visitBatch(batch: BetaOrganizationRateLimitBatchGroup) {
                        batch.validate()
                    }

                    override fun visitTokenCount(
                        tokenCount: BetaOrganizationRateLimitTokenCountGroup
                    ) {
                        tokenCount.validate()
                    }

                    override fun visitFiles(files: BetaOrganizationRateLimitFilesGroup) {
                        files.validate()
                    }

                    override fun visitSkills(skills: BetaOrganizationRateLimitSkillsGroup) {
                        skills.validate()
                    }

                    override fun visitWebSearch(
                        webSearch: BetaOrganizationRateLimitWebSearchGroup
                    ) {
                        webSearch.validate()
                    }
                }
            )
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
            accept(
                object : Visitor<Int> {
                    override fun visitModel(model: BetaOrganizationRateLimitModelGroup) =
                        model.validity()

                    override fun visitBatch(batch: BetaOrganizationRateLimitBatchGroup) =
                        batch.validity()

                    override fun visitTokenCount(
                        tokenCount: BetaOrganizationRateLimitTokenCountGroup
                    ) = tokenCount.validity()

                    override fun visitFiles(files: BetaOrganizationRateLimitFilesGroup) =
                        files.validity()

                    override fun visitSkills(skills: BetaOrganizationRateLimitSkillsGroup) =
                        skills.validity()

                    override fun visitWebSearch(
                        webSearch: BetaOrganizationRateLimitWebSearchGroup
                    ) = webSearch.validity()

                    override fun unknown(json: JsonValue?) = 0
                }
            )

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Group &&
                model == other.model &&
                batch == other.batch &&
                tokenCount == other.tokenCount &&
                files == other.files &&
                skills == other.skills &&
                webSearch == other.webSearch
        }

        override fun hashCode(): Int =
            Objects.hash(model, batch, tokenCount, files, skills, webSearch)

        override fun toString(): String =
            when {
                model != null -> "Group{model=$model}"
                batch != null -> "Group{batch=$batch}"
                tokenCount != null -> "Group{tokenCount=$tokenCount}"
                files != null -> "Group{files=$files}"
                skills != null -> "Group{skills=$skills}"
                webSearch != null -> "Group{webSearch=$webSearch}"
                _json != null -> "Group{_unknown=$_json}"
                else -> throw IllegalStateException("Invalid Group")
            }

        companion object {

            @JvmStatic
            fun ofModel(model: BetaOrganizationRateLimitModelGroup) = Group(model = model)

            @JvmStatic
            fun ofBatch(batch: BetaOrganizationRateLimitBatchGroup) = Group(batch = batch)

            /**
             * Returns an immutable instance of [Group] whose [ofBatch] variant is built from the
             * given required [id].
             */
            @JvmStatic fun ofBatch(id: String) = ofBatch(BetaOrganizationRateLimitBatchGroup.of(id))

            @JvmStatic
            fun ofTokenCount(tokenCount: BetaOrganizationRateLimitTokenCountGroup) =
                Group(tokenCount = tokenCount)

            /**
             * Returns an immutable instance of [Group] whose [ofTokenCount] variant is built from
             * the given required [id].
             */
            @JvmStatic
            fun ofTokenCount(id: String) =
                ofTokenCount(BetaOrganizationRateLimitTokenCountGroup.of(id))

            @JvmStatic
            fun ofFiles(files: BetaOrganizationRateLimitFilesGroup) = Group(files = files)

            /**
             * Returns an immutable instance of [Group] whose [ofFiles] variant is built from the
             * given required [id].
             */
            @JvmStatic fun ofFiles(id: String) = ofFiles(BetaOrganizationRateLimitFilesGroup.of(id))

            @JvmStatic
            fun ofSkills(skills: BetaOrganizationRateLimitSkillsGroup) = Group(skills = skills)

            /**
             * Returns an immutable instance of [Group] whose [ofSkills] variant is built from the
             * given required [id].
             */
            @JvmStatic
            fun ofSkills(id: String) = ofSkills(BetaOrganizationRateLimitSkillsGroup.of(id))

            @JvmStatic
            fun ofWebSearch(webSearch: BetaOrganizationRateLimitWebSearchGroup) =
                Group(webSearch = webSearch)

            /**
             * Returns an immutable instance of [Group] whose [ofWebSearch] variant is built from
             * the given required [id].
             */
            @JvmStatic
            fun ofWebSearch(id: String) =
                ofWebSearch(BetaOrganizationRateLimitWebSearchGroup.of(id))
        }

        /** An interface that defines how to map each variant of [Group] to a value of type [T]. */
        interface Visitor<out T> {

            fun visitModel(model: BetaOrganizationRateLimitModelGroup): T

            fun visitBatch(batch: BetaOrganizationRateLimitBatchGroup): T

            fun visitTokenCount(tokenCount: BetaOrganizationRateLimitTokenCountGroup): T

            fun visitFiles(files: BetaOrganizationRateLimitFilesGroup): T

            fun visitSkills(skills: BetaOrganizationRateLimitSkillsGroup): T

            fun visitWebSearch(webSearch: BetaOrganizationRateLimitWebSearchGroup): T

            /**
             * Maps an unknown variant of [Group] to a value of type [T].
             *
             * An instance of [Group] can contain an unknown variant if it was deserialized from
             * data that doesn't match any known variant. For example, if the SDK is on an older
             * version than the API, then the API may respond with new variants that the SDK is
             * unaware of.
             *
             * @throws AnthropicInvalidDataException in the default implementation.
             */
            fun unknown(json: JsonValue?): T {
                throw AnthropicInvalidDataException("Unknown Group: $json")
            }
        }

        internal class Deserializer : BaseDeserializer<Group>(Group::class) {

            override fun ObjectCodec.deserialize(node: JsonNode): Group {
                val json = JsonValue.fromJsonNode(node)
                val type = json.asObject().getOrNull()?.get("type")?.asString()?.getOrNull()

                when (type) {
                    "model_group" -> {
                        return tryDeserialize(
                                node,
                                jacksonTypeRef<BetaOrganizationRateLimitModelGroup>(),
                            )
                            ?.let { Group(model = it, _json = json) } ?: Group(_json = json)
                    }
                    "batch" -> {
                        return tryDeserialize(
                                node,
                                jacksonTypeRef<BetaOrganizationRateLimitBatchGroup>(),
                            )
                            ?.let { Group(batch = it, _json = json) } ?: Group(_json = json)
                    }
                    "token_count" -> {
                        return tryDeserialize(
                                node,
                                jacksonTypeRef<BetaOrganizationRateLimitTokenCountGroup>(),
                            )
                            ?.let { Group(tokenCount = it, _json = json) } ?: Group(_json = json)
                    }
                    "files" -> {
                        return tryDeserialize(
                                node,
                                jacksonTypeRef<BetaOrganizationRateLimitFilesGroup>(),
                            )
                            ?.let { Group(files = it, _json = json) } ?: Group(_json = json)
                    }
                    "skills" -> {
                        return tryDeserialize(
                                node,
                                jacksonTypeRef<BetaOrganizationRateLimitSkillsGroup>(),
                            )
                            ?.let { Group(skills = it, _json = json) } ?: Group(_json = json)
                    }
                    "web_search" -> {
                        return tryDeserialize(
                                node,
                                jacksonTypeRef<BetaOrganizationRateLimitWebSearchGroup>(),
                            )
                            ?.let { Group(webSearch = it, _json = json) } ?: Group(_json = json)
                    }
                }

                return Group(_json = json)
            }
        }

        internal class Serializer : BaseSerializer<Group>(Group::class) {

            override fun serialize(
                value: Group,
                generator: JsonGenerator,
                provider: SerializerProvider,
            ) {
                when {
                    value.model != null -> generator.writeObject(value.model)
                    value.batch != null -> generator.writeObject(value.batch)
                    value.tokenCount != null -> generator.writeObject(value.tokenCount)
                    value.files != null -> generator.writeObject(value.files)
                    value.skills != null -> generator.writeObject(value.skills)
                    value.webSearch != null -> generator.writeObject(value.webSearch)
                    value._json != null -> generator.writeObject(value._json)
                    else -> throw IllegalStateException("Invalid Group")
                }
            }
        }

        class Type @JsonCreator private constructor(private val value: JsonField<String>) : Enum {

            /**
             * Returns this class instance's raw value.
             *
             * This is usually only useful if this instance was deserialized from data that doesn't
             * match any known member, and you want to know that value. For example, if the SDK is
             * on an older version than the API, then the API may respond with new members that the
             * SDK is unaware of.
             */
            @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

            companion object {

                @JvmField val MODEL_GROUP = of("model_group")

                @JvmField val BATCH = of("batch")

                @JvmField val TOKEN_COUNT = of("token_count")

                @JvmField val FILES = of("files")

                @JvmField val SKILLS = of("skills")

                @JvmField val WEB_SEARCH = of("web_search")

                @JvmStatic fun of(value: String) = Type(JsonField.of(value))

                @JvmSynthetic
                internal fun of(value: JsonField<String>): Type =
                    value.asString().getOrNull()?.let { of(it) } ?: Type(value)
            }

            /** An enum containing [Type]'s known values. */
            enum class Known {
                MODEL_GROUP,
                BATCH,
                TOKEN_COUNT,
                FILES,
                SKILLS,
                WEB_SEARCH,
            }

            /**
             * An enum containing [Type]'s known values, as well as an [_UNKNOWN] member.
             *
             * An instance of [Type] can contain an unknown value in a couple of cases:
             * - It was deserialized from data that doesn't match any known member. For example, if
             *   the SDK is on an older version than the API, then the API may respond with new
             *   members that the SDK is unaware of.
             * - It was constructed with an arbitrary value using the [of] method.
             */
            enum class Value {
                MODEL_GROUP,
                BATCH,
                TOKEN_COUNT,
                FILES,
                SKILLS,
                WEB_SEARCH,
                /** An enum member indicating that [Type] was instantiated with an unknown value. */
                _UNKNOWN,
            }

            /**
             * Returns an enum member corresponding to this class instance's value, or
             * [Value._UNKNOWN] if the class was instantiated with an unknown value.
             *
             * Use the [known] method instead if you're certain the value is always known or if you
             * want to throw for the unknown case.
             */
            fun value(): Value =
                when (this) {
                    MODEL_GROUP -> Value.MODEL_GROUP
                    BATCH -> Value.BATCH
                    TOKEN_COUNT -> Value.TOKEN_COUNT
                    FILES -> Value.FILES
                    SKILLS -> Value.SKILLS
                    WEB_SEARCH -> Value.WEB_SEARCH
                    else -> Value._UNKNOWN
                }

            /**
             * Returns an enum member corresponding to this class instance's value.
             *
             * Use the [value] method instead if you're uncertain the value is always known and
             * don't want to throw for the unknown case.
             *
             * @throws AnthropicInvalidDataException if this class instance's value is a not a known
             *   member.
             */
            fun known(): Known =
                when (this) {
                    MODEL_GROUP -> Known.MODEL_GROUP
                    BATCH -> Known.BATCH
                    TOKEN_COUNT -> Known.TOKEN_COUNT
                    FILES -> Known.FILES
                    SKILLS -> Known.SKILLS
                    WEB_SEARCH -> Known.WEB_SEARCH
                    else -> throw AnthropicInvalidDataException("Unknown Type: $value")
                }

            /**
             * Returns this class instance's primitive wire representation.
             *
             * This differs from the [toString] method because that method is primarily for
             * debugging and generally doesn't throw.
             *
             * @throws AnthropicInvalidDataException if this class instance's value does not have
             *   the expected primitive type.
             */
            fun asString(): String =
                _value().asString().orElseThrow {
                    AnthropicInvalidDataException("Value is not a String")
                }

            private var validated: Boolean = false

            /**
             * Validates that the types of all values in this object match their expected types
             * recursively.
             *
             * This method is _not_ forwards compatible with new types from the API for existing
             * fields.
             *
             * @throws AnthropicInvalidDataException if any value type in this object doesn't match
             *   its expected type.
             */
            fun validate(): Type = apply {
                if (validated) {
                    return@apply
                }

                known()
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
            @JvmSynthetic internal fun validity(): Int = if (value() == Value._UNKNOWN) 0 else 1

            override fun equals(other: Any?): Boolean {
                if (this === other) {
                    return true
                }

                return other is Type && value == other.value
            }

            override fun hashCode() = value.hashCode()

            override fun toString() = value.toString()
        }
    }

    /**
     * Deprecated: use `group.type` instead. The kind of rate-limit group this entry represents.
     * `model_group` entries apply to a family of models (listed in `models`); other values apply to
     * an API-surface category and have `models` set to `null`. Always equal to `group.type`.
     */
    @Deprecated(
        "Use `group.type` instead. `group_type` is still returned and always equals `group.type`."
    )
    class GroupType @JsonCreator private constructor(private val value: JsonField<String>) : Enum {

        /**
         * Returns this class instance's raw value.
         *
         * This is usually only useful if this instance was deserialized from data that doesn't
         * match any known member, and you want to know that value. For example, if the SDK is on an
         * older version than the API, then the API may respond with new members that the SDK is
         * unaware of.
         */
        @com.fasterxml.jackson.annotation.JsonValue fun _value(): JsonField<String> = value

        companion object {

            @JvmField val BATCH = of("batch")

            @JvmField val FILES = of("files")

            @JvmField val MODEL_GROUP = of("model_group")

            @JvmField val SKILLS = of("skills")

            @JvmField val TOKEN_COUNT = of("token_count")

            @JvmField val WEB_SEARCH = of("web_search")

            @JvmStatic fun of(value: String) = GroupType(JsonField.of(value))

            @JvmSynthetic
            internal fun of(value: JsonField<String>): GroupType =
                value.asString().getOrNull()?.let { of(it) } ?: GroupType(value)
        }

        /** An enum containing [GroupType]'s known values. */
        enum class Known {
            BATCH,
            FILES,
            MODEL_GROUP,
            SKILLS,
            TOKEN_COUNT,
            WEB_SEARCH,
        }

        /**
         * An enum containing [GroupType]'s known values, as well as an [_UNKNOWN] member.
         *
         * An instance of [GroupType] can contain an unknown value in a couple of cases:
         * - It was deserialized from data that doesn't match any known member. For example, if the
         *   SDK is on an older version than the API, then the API may respond with new members that
         *   the SDK is unaware of.
         * - It was constructed with an arbitrary value using the [of] method.
         */
        enum class Value {
            BATCH,
            FILES,
            MODEL_GROUP,
            SKILLS,
            TOKEN_COUNT,
            WEB_SEARCH,
            /**
             * An enum member indicating that [GroupType] was instantiated with an unknown value.
             */
            _UNKNOWN,
        }

        /**
         * Returns an enum member corresponding to this class instance's value, or [Value._UNKNOWN]
         * if the class was instantiated with an unknown value.
         *
         * Use the [known] method instead if you're certain the value is always known or if you want
         * to throw for the unknown case.
         */
        fun value(): Value =
            when (this) {
                BATCH -> Value.BATCH
                FILES -> Value.FILES
                MODEL_GROUP -> Value.MODEL_GROUP
                SKILLS -> Value.SKILLS
                TOKEN_COUNT -> Value.TOKEN_COUNT
                WEB_SEARCH -> Value.WEB_SEARCH
                else -> Value._UNKNOWN
            }

        /**
         * Returns an enum member corresponding to this class instance's value.
         *
         * Use the [value] method instead if you're uncertain the value is always known and don't
         * want to throw for the unknown case.
         *
         * @throws AnthropicInvalidDataException if this class instance's value is a not a known
         *   member.
         */
        fun known(): Known =
            when (this) {
                BATCH -> Known.BATCH
                FILES -> Known.FILES
                MODEL_GROUP -> Known.MODEL_GROUP
                SKILLS -> Known.SKILLS
                TOKEN_COUNT -> Known.TOKEN_COUNT
                WEB_SEARCH -> Known.WEB_SEARCH
                else -> throw AnthropicInvalidDataException("Unknown GroupType: $value")
            }

        /**
         * Returns this class instance's primitive wire representation.
         *
         * This differs from the [toString] method because that method is primarily for debugging
         * and generally doesn't throw.
         *
         * @throws AnthropicInvalidDataException if this class instance's value does not have the
         *   expected primitive type.
         */
        fun asString(): String =
            _value().asString().orElseThrow {
                AnthropicInvalidDataException("Value is not a String")
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
        fun validate(): GroupType = apply {
            if (validated) {
                return@apply
            }

            known()
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
        @JvmSynthetic internal fun validity(): Int = if (value() == Value._UNKNOWN) 0 else 1

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is GroupType && value == other.value
        }

        override fun hashCode() = value.hashCode()

        override fun toString() = value.toString()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaOrganizationRateLimit &&
            id == other.id &&
            group == other.group &&
            groupType == other.groupType &&
            limits == other.limits &&
            models == other.models &&
            type == other.type &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(id, group, groupType, limits, models, type, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaOrganizationRateLimit{id=$id, group=$group, groupType=$groupType, limits=$limits, models=$models, type=$type, additionalProperties=$additionalProperties}"
}
