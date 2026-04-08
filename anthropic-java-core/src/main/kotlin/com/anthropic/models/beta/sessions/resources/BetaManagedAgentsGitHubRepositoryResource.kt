// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.sessions.resources

import com.anthropic.core.BaseDeserializer
import com.anthropic.core.BaseSerializer
import com.anthropic.core.Enum
import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.checkRequired
import com.anthropic.core.getOrThrow
import com.anthropic.errors.AnthropicInvalidDataException
import com.anthropic.models.beta.sessions.BetaManagedAgentsBranchCheckout
import com.anthropic.models.beta.sessions.BetaManagedAgentsCommitCheckout
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
import java.time.OffsetDateTime
import java.util.Collections
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

class BetaManagedAgentsGitHubRepositoryResource
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val id: JsonField<String>,
    private val createdAt: JsonField<OffsetDateTime>,
    private val mountPath: JsonField<String>,
    private val type: JsonField<Type>,
    private val updatedAt: JsonField<OffsetDateTime>,
    private val url: JsonField<String>,
    private val checkout: JsonField<Checkout>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("id") @ExcludeMissing id: JsonField<String> = JsonMissing.of(),
        @JsonProperty("created_at")
        @ExcludeMissing
        createdAt: JsonField<OffsetDateTime> = JsonMissing.of(),
        @JsonProperty("mount_path") @ExcludeMissing mountPath: JsonField<String> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonField<Type> = JsonMissing.of(),
        @JsonProperty("updated_at")
        @ExcludeMissing
        updatedAt: JsonField<OffsetDateTime> = JsonMissing.of(),
        @JsonProperty("url") @ExcludeMissing url: JsonField<String> = JsonMissing.of(),
        @JsonProperty("checkout") @ExcludeMissing checkout: JsonField<Checkout> = JsonMissing.of(),
    ) : this(id, createdAt, mountPath, type, updatedAt, url, checkout, mutableMapOf())

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun id(): String = id.getRequired("id")

    /**
     * A timestamp in RFC 3339 format
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun createdAt(): OffsetDateTime = createdAt.getRequired("created_at")

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun mountPath(): String = mountPath.getRequired("mount_path")

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun type(): Type = type.getRequired("type")

    /**
     * A timestamp in RFC 3339 format
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun updatedAt(): OffsetDateTime = updatedAt.getRequired("updated_at")

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun url(): String = url.getRequired("url")

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun checkout(): Optional<Checkout> = checkout.getOptional("checkout")

    /**
     * Returns the raw JSON value of [id].
     *
     * Unlike [id], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("id") @ExcludeMissing fun _id(): JsonField<String> = id

    /**
     * Returns the raw JSON value of [createdAt].
     *
     * Unlike [createdAt], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("created_at")
    @ExcludeMissing
    fun _createdAt(): JsonField<OffsetDateTime> = createdAt

    /**
     * Returns the raw JSON value of [mountPath].
     *
     * Unlike [mountPath], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("mount_path") @ExcludeMissing fun _mountPath(): JsonField<String> = mountPath

    /**
     * Returns the raw JSON value of [type].
     *
     * Unlike [type], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonField<Type> = type

    /**
     * Returns the raw JSON value of [updatedAt].
     *
     * Unlike [updatedAt], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("updated_at")
    @ExcludeMissing
    fun _updatedAt(): JsonField<OffsetDateTime> = updatedAt

    /**
     * Returns the raw JSON value of [url].
     *
     * Unlike [url], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("url") @ExcludeMissing fun _url(): JsonField<String> = url

    /**
     * Returns the raw JSON value of [checkout].
     *
     * Unlike [checkout], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("checkout") @ExcludeMissing fun _checkout(): JsonField<Checkout> = checkout

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
         * [BetaManagedAgentsGitHubRepositoryResource].
         *
         * The following fields are required:
         * ```java
         * .id()
         * .createdAt()
         * .mountPath()
         * .type()
         * .updatedAt()
         * .url()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaManagedAgentsGitHubRepositoryResource]. */
    class Builder internal constructor() {

        private var id: JsonField<String>? = null
        private var createdAt: JsonField<OffsetDateTime>? = null
        private var mountPath: JsonField<String>? = null
        private var type: JsonField<Type>? = null
        private var updatedAt: JsonField<OffsetDateTime>? = null
        private var url: JsonField<String>? = null
        private var checkout: JsonField<Checkout> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(
            betaManagedAgentsGitHubRepositoryResource: BetaManagedAgentsGitHubRepositoryResource
        ) = apply {
            id = betaManagedAgentsGitHubRepositoryResource.id
            createdAt = betaManagedAgentsGitHubRepositoryResource.createdAt
            mountPath = betaManagedAgentsGitHubRepositoryResource.mountPath
            type = betaManagedAgentsGitHubRepositoryResource.type
            updatedAt = betaManagedAgentsGitHubRepositoryResource.updatedAt
            url = betaManagedAgentsGitHubRepositoryResource.url
            checkout = betaManagedAgentsGitHubRepositoryResource.checkout
            additionalProperties =
                betaManagedAgentsGitHubRepositoryResource.additionalProperties.toMutableMap()
        }

        fun id(id: String) = id(JsonField.of(id))

        /**
         * Sets [Builder.id] to an arbitrary JSON value.
         *
         * You should usually call [Builder.id] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun id(id: JsonField<String>) = apply { this.id = id }

        /** A timestamp in RFC 3339 format */
        fun createdAt(createdAt: OffsetDateTime) = createdAt(JsonField.of(createdAt))

        /**
         * Sets [Builder.createdAt] to an arbitrary JSON value.
         *
         * You should usually call [Builder.createdAt] with a well-typed [OffsetDateTime] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun createdAt(createdAt: JsonField<OffsetDateTime>) = apply { this.createdAt = createdAt }

        fun mountPath(mountPath: String) = mountPath(JsonField.of(mountPath))

        /**
         * Sets [Builder.mountPath] to an arbitrary JSON value.
         *
         * You should usually call [Builder.mountPath] with a well-typed [String] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun mountPath(mountPath: JsonField<String>) = apply { this.mountPath = mountPath }

        fun type(type: Type) = type(JsonField.of(type))

        /**
         * Sets [Builder.type] to an arbitrary JSON value.
         *
         * You should usually call [Builder.type] with a well-typed [Type] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun type(type: JsonField<Type>) = apply { this.type = type }

        /** A timestamp in RFC 3339 format */
        fun updatedAt(updatedAt: OffsetDateTime) = updatedAt(JsonField.of(updatedAt))

        /**
         * Sets [Builder.updatedAt] to an arbitrary JSON value.
         *
         * You should usually call [Builder.updatedAt] with a well-typed [OffsetDateTime] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun updatedAt(updatedAt: JsonField<OffsetDateTime>) = apply { this.updatedAt = updatedAt }

        fun url(url: String) = url(JsonField.of(url))

        /**
         * Sets [Builder.url] to an arbitrary JSON value.
         *
         * You should usually call [Builder.url] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun url(url: JsonField<String>) = apply { this.url = url }

        fun checkout(checkout: Checkout?) = checkout(JsonField.ofNullable(checkout))

        /** Alias for calling [Builder.checkout] with `checkout.orElse(null)`. */
        fun checkout(checkout: Optional<Checkout>) = checkout(checkout.getOrNull())

        /**
         * Sets [Builder.checkout] to an arbitrary JSON value.
         *
         * You should usually call [Builder.checkout] with a well-typed [Checkout] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun checkout(checkout: JsonField<Checkout>) = apply { this.checkout = checkout }

        /** Alias for calling [checkout] with `Checkout.ofBranch(branch)`. */
        fun checkout(branch: BetaManagedAgentsBranchCheckout) = checkout(Checkout.ofBranch(branch))

        /**
         * Alias for calling [checkout] with the following:
         * ```java
         * BetaManagedAgentsBranchCheckout.builder()
         *     .type(BetaManagedAgentsBranchCheckout.Type.BRANCH)
         *     .name(name)
         *     .build()
         * ```
         */
        fun branchCheckout(name: String) =
            checkout(
                BetaManagedAgentsBranchCheckout.builder()
                    .type(BetaManagedAgentsBranchCheckout.Type.BRANCH)
                    .name(name)
                    .build()
            )

        /** Alias for calling [checkout] with `Checkout.ofCommit(commit)`. */
        fun checkout(commit: BetaManagedAgentsCommitCheckout) = checkout(Checkout.ofCommit(commit))

        /**
         * Alias for calling [checkout] with the following:
         * ```java
         * BetaManagedAgentsCommitCheckout.builder()
         *     .type(BetaManagedAgentsCommitCheckout.Type.COMMIT)
         *     .sha(sha)
         *     .build()
         * ```
         */
        fun commitCheckout(sha: String) =
            checkout(
                BetaManagedAgentsCommitCheckout.builder()
                    .type(BetaManagedAgentsCommitCheckout.Type.COMMIT)
                    .sha(sha)
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
         * Returns an immutable instance of [BetaManagedAgentsGitHubRepositoryResource].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .id()
         * .createdAt()
         * .mountPath()
         * .type()
         * .updatedAt()
         * .url()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaManagedAgentsGitHubRepositoryResource =
            BetaManagedAgentsGitHubRepositoryResource(
                checkRequired("id", id),
                checkRequired("createdAt", createdAt),
                checkRequired("mountPath", mountPath),
                checkRequired("type", type),
                checkRequired("updatedAt", updatedAt),
                checkRequired("url", url),
                checkout,
                additionalProperties.toMutableMap(),
            )
    }

    private var validated: Boolean = false

    fun validate(): BetaManagedAgentsGitHubRepositoryResource = apply {
        if (validated) {
            return@apply
        }

        id()
        createdAt()
        mountPath()
        type().validate()
        updatedAt()
        url()
        checkout().ifPresent { it.validate() }
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
            (if (createdAt.asKnown().isPresent) 1 else 0) +
            (if (mountPath.asKnown().isPresent) 1 else 0) +
            (type.asKnown().getOrNull()?.validity() ?: 0) +
            (if (updatedAt.asKnown().isPresent) 1 else 0) +
            (if (url.asKnown().isPresent) 1 else 0) +
            (checkout.asKnown().getOrNull()?.validity() ?: 0)

    class Type @JsonCreator private constructor(private val value: JsonField<String>) : Enum {

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

            @JvmField val GITHUB_REPOSITORY = of("github_repository")

            @JvmStatic fun of(value: String) = Type(JsonField.of(value))
        }

        /** An enum containing [Type]'s known values. */
        enum class Known {
            GITHUB_REPOSITORY
        }

        /**
         * An enum containing [Type]'s known values, as well as an [_UNKNOWN] member.
         *
         * An instance of [Type] can contain an unknown value in a couple of cases:
         * - It was deserialized from data that doesn't match any known member. For example, if the
         *   SDK is on an older version than the API, then the API may respond with new members that
         *   the SDK is unaware of.
         * - It was constructed with an arbitrary value using the [of] method.
         */
        enum class Value {
            GITHUB_REPOSITORY,
            /** An enum member indicating that [Type] was instantiated with an unknown value. */
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
                GITHUB_REPOSITORY -> Value.GITHUB_REPOSITORY
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
                GITHUB_REPOSITORY -> Known.GITHUB_REPOSITORY
                else -> throw AnthropicInvalidDataException("Unknown Type: $value")
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

    @JsonDeserialize(using = Checkout.Deserializer::class)
    @JsonSerialize(using = Checkout.Serializer::class)
    class Checkout
    private constructor(
        private val branch: BetaManagedAgentsBranchCheckout? = null,
        private val commit: BetaManagedAgentsCommitCheckout? = null,
        private val _json: JsonValue? = null,
    ) {

        fun branch(): Optional<BetaManagedAgentsBranchCheckout> = Optional.ofNullable(branch)

        fun commit(): Optional<BetaManagedAgentsCommitCheckout> = Optional.ofNullable(commit)

        fun isBranch(): Boolean = branch != null

        fun isCommit(): Boolean = commit != null

        fun asBranch(): BetaManagedAgentsBranchCheckout = branch.getOrThrow("branch")

        fun asCommit(): BetaManagedAgentsCommitCheckout = commit.getOrThrow("commit")

        fun _json(): Optional<JsonValue> = Optional.ofNullable(_json)

        fun <T> accept(visitor: Visitor<T>): T =
            when {
                branch != null -> visitor.visitBranch(branch)
                commit != null -> visitor.visitCommit(commit)
                else -> visitor.unknown(_json)
            }

        private var validated: Boolean = false

        fun validate(): Checkout = apply {
            if (validated) {
                return@apply
            }

            accept(
                object : Visitor<Unit> {
                    override fun visitBranch(branch: BetaManagedAgentsBranchCheckout) {
                        branch.validate()
                    }

                    override fun visitCommit(commit: BetaManagedAgentsCommitCheckout) {
                        commit.validate()
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
                    override fun visitBranch(branch: BetaManagedAgentsBranchCheckout) =
                        branch.validity()

                    override fun visitCommit(commit: BetaManagedAgentsCommitCheckout) =
                        commit.validity()

                    override fun unknown(json: JsonValue?) = 0
                }
            )

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Checkout && branch == other.branch && commit == other.commit
        }

        override fun hashCode(): Int = Objects.hash(branch, commit)

        override fun toString(): String =
            when {
                branch != null -> "Checkout{branch=$branch}"
                commit != null -> "Checkout{commit=$commit}"
                _json != null -> "Checkout{_unknown=$_json}"
                else -> throw IllegalStateException("Invalid Checkout")
            }

        companion object {

            @JvmStatic
            fun ofBranch(branch: BetaManagedAgentsBranchCheckout) = Checkout(branch = branch)

            @JvmStatic
            fun ofCommit(commit: BetaManagedAgentsCommitCheckout) = Checkout(commit = commit)
        }

        /**
         * An interface that defines how to map each variant of [Checkout] to a value of type [T].
         */
        interface Visitor<out T> {

            fun visitBranch(branch: BetaManagedAgentsBranchCheckout): T

            fun visitCommit(commit: BetaManagedAgentsCommitCheckout): T

            /**
             * Maps an unknown variant of [Checkout] to a value of type [T].
             *
             * An instance of [Checkout] can contain an unknown variant if it was deserialized from
             * data that doesn't match any known variant. For example, if the SDK is on an older
             * version than the API, then the API may respond with new variants that the SDK is
             * unaware of.
             *
             * @throws AnthropicInvalidDataException in the default implementation.
             */
            fun unknown(json: JsonValue?): T {
                throw AnthropicInvalidDataException("Unknown Checkout: $json")
            }
        }

        internal class Deserializer : BaseDeserializer<Checkout>(Checkout::class) {

            override fun ObjectCodec.deserialize(node: JsonNode): Checkout {
                val json = JsonValue.fromJsonNode(node)
                val type = json.asObject().getOrNull()?.get("type")?.asString()?.getOrNull()

                when (type) {
                    "branch" -> {
                        return tryDeserialize(
                                node,
                                jacksonTypeRef<BetaManagedAgentsBranchCheckout>(),
                            )
                            ?.let { Checkout(branch = it, _json = json) } ?: Checkout(_json = json)
                    }
                    "commit" -> {
                        return tryDeserialize(
                                node,
                                jacksonTypeRef<BetaManagedAgentsCommitCheckout>(),
                            )
                            ?.let { Checkout(commit = it, _json = json) } ?: Checkout(_json = json)
                    }
                }

                return Checkout(_json = json)
            }
        }

        internal class Serializer : BaseSerializer<Checkout>(Checkout::class) {

            override fun serialize(
                value: Checkout,
                generator: JsonGenerator,
                provider: SerializerProvider,
            ) {
                when {
                    value.branch != null -> generator.writeObject(value.branch)
                    value.commit != null -> generator.writeObject(value.commit)
                    value._json != null -> generator.writeObject(value._json)
                    else -> throw IllegalStateException("Invalid Checkout")
                }
            }
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaManagedAgentsGitHubRepositoryResource &&
            id == other.id &&
            createdAt == other.createdAt &&
            mountPath == other.mountPath &&
            type == other.type &&
            updatedAt == other.updatedAt &&
            url == other.url &&
            checkout == other.checkout &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(id, createdAt, mountPath, type, updatedAt, url, checkout, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaManagedAgentsGitHubRepositoryResource{id=$id, createdAt=$createdAt, mountPath=$mountPath, type=$type, updatedAt=$updatedAt, url=$url, checkout=$checkout, additionalProperties=$additionalProperties}"
}
