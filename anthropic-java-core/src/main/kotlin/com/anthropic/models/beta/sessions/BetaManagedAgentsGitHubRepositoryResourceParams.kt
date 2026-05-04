// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.sessions

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

/** Mount a GitHub repository into the session's container. */
class BetaManagedAgentsGitHubRepositoryResourceParams
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val authorizationToken: JsonField<String>,
    private val type: JsonField<Type>,
    private val url: JsonField<String>,
    private val checkout: JsonField<Checkout>,
    private val mountPath: JsonField<String>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("authorization_token")
        @ExcludeMissing
        authorizationToken: JsonField<String> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonField<Type> = JsonMissing.of(),
        @JsonProperty("url") @ExcludeMissing url: JsonField<String> = JsonMissing.of(),
        @JsonProperty("checkout") @ExcludeMissing checkout: JsonField<Checkout> = JsonMissing.of(),
        @JsonProperty("mount_path") @ExcludeMissing mountPath: JsonField<String> = JsonMissing.of(),
    ) : this(authorizationToken, type, url, checkout, mountPath, mutableMapOf())

    /**
     * GitHub authorization token used to clone the repository.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun authorizationToken(): String = authorizationToken.getRequired("authorization_token")

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun type(): Type = type.getRequired("type")

    /**
     * Github URL of the repository
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun url(): String = url.getRequired("url")

    /**
     * Branch or commit to check out. Defaults to the repository's default branch.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun checkout(): Optional<Checkout> = checkout.getOptional("checkout")

    /**
     * Mount path in the container. Defaults to `/workspace/<repo-name>`.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun mountPath(): Optional<String> = mountPath.getOptional("mount_path")

    /**
     * Returns the raw JSON value of [authorizationToken].
     *
     * Unlike [authorizationToken], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("authorization_token")
    @ExcludeMissing
    fun _authorizationToken(): JsonField<String> = authorizationToken

    /**
     * Returns the raw JSON value of [type].
     *
     * Unlike [type], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonField<Type> = type

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

    /**
     * Returns the raw JSON value of [mountPath].
     *
     * Unlike [mountPath], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("mount_path") @ExcludeMissing fun _mountPath(): JsonField<String> = mountPath

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
         * [BetaManagedAgentsGitHubRepositoryResourceParams].
         *
         * The following fields are required:
         * ```java
         * .authorizationToken()
         * .type()
         * .url()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaManagedAgentsGitHubRepositoryResourceParams]. */
    class Builder internal constructor() {

        private var authorizationToken: JsonField<String>? = null
        private var type: JsonField<Type>? = null
        private var url: JsonField<String>? = null
        private var checkout: JsonField<Checkout> = JsonMissing.of()
        private var mountPath: JsonField<String> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(
            betaManagedAgentsGitHubRepositoryResourceParams:
                BetaManagedAgentsGitHubRepositoryResourceParams
        ) = apply {
            authorizationToken = betaManagedAgentsGitHubRepositoryResourceParams.authorizationToken
            type = betaManagedAgentsGitHubRepositoryResourceParams.type
            url = betaManagedAgentsGitHubRepositoryResourceParams.url
            checkout = betaManagedAgentsGitHubRepositoryResourceParams.checkout
            mountPath = betaManagedAgentsGitHubRepositoryResourceParams.mountPath
            additionalProperties =
                betaManagedAgentsGitHubRepositoryResourceParams.additionalProperties.toMutableMap()
        }

        /** GitHub authorization token used to clone the repository. */
        fun authorizationToken(authorizationToken: String) =
            authorizationToken(JsonField.of(authorizationToken))

        /**
         * Sets [Builder.authorizationToken] to an arbitrary JSON value.
         *
         * You should usually call [Builder.authorizationToken] with a well-typed [String] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun authorizationToken(authorizationToken: JsonField<String>) = apply {
            this.authorizationToken = authorizationToken
        }

        fun type(type: Type) = type(JsonField.of(type))

        /**
         * Sets [Builder.type] to an arbitrary JSON value.
         *
         * You should usually call [Builder.type] with a well-typed [Type] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun type(type: JsonField<Type>) = apply { this.type = type }

        /** Github URL of the repository */
        fun url(url: String) = url(JsonField.of(url))

        /**
         * Sets [Builder.url] to an arbitrary JSON value.
         *
         * You should usually call [Builder.url] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun url(url: JsonField<String>) = apply { this.url = url }

        /** Branch or commit to check out. Defaults to the repository's default branch. */
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

        /** Mount path in the container. Defaults to `/workspace/<repo-name>`. */
        fun mountPath(mountPath: String?) = mountPath(JsonField.ofNullable(mountPath))

        /** Alias for calling [Builder.mountPath] with `mountPath.orElse(null)`. */
        fun mountPath(mountPath: Optional<String>) = mountPath(mountPath.getOrNull())

        /**
         * Sets [Builder.mountPath] to an arbitrary JSON value.
         *
         * You should usually call [Builder.mountPath] with a well-typed [String] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun mountPath(mountPath: JsonField<String>) = apply { this.mountPath = mountPath }

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
         * Returns an immutable instance of [BetaManagedAgentsGitHubRepositoryResourceParams].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .authorizationToken()
         * .type()
         * .url()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaManagedAgentsGitHubRepositoryResourceParams =
            BetaManagedAgentsGitHubRepositoryResourceParams(
                checkRequired("authorizationToken", authorizationToken),
                checkRequired("type", type),
                checkRequired("url", url),
                checkout,
                mountPath,
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
    fun validate(): BetaManagedAgentsGitHubRepositoryResourceParams = apply {
        if (validated) {
            return@apply
        }

        authorizationToken()
        type().validate()
        url()
        checkout().ifPresent { it.validate() }
        mountPath()
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
        (if (authorizationToken.asKnown().isPresent) 1 else 0) +
            (type.asKnown().getOrNull()?.validity() ?: 0) +
            (if (url.asKnown().isPresent) 1 else 0) +
            (checkout.asKnown().getOrNull()?.validity() ?: 0) +
            (if (mountPath.asKnown().isPresent) 1 else 0)

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

        /**
         * Validates that the types of all values in this object match their expected types
         * recursively.
         *
         * This method is _not_ forwards compatible with new types from the API for existing fields.
         *
         * @throws AnthropicInvalidDataException if any value type in this object doesn't match its
         *   expected type.
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

    /** Branch or commit to check out. Defaults to the repository's default branch. */
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
         * Optional<String> result = checkout.accept(new Checkout.Visitor<Optional<String>>() {
         *     @Override
         *     public Optional<String> visitBranch(BetaManagedAgentsBranchCheckout branch) {
         *         return Optional.of(branch.toString());
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
                branch != null -> visitor.visitBranch(branch)
                commit != null -> visitor.visitCommit(commit)
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

        return other is BetaManagedAgentsGitHubRepositoryResourceParams &&
            authorizationToken == other.authorizationToken &&
            type == other.type &&
            url == other.url &&
            checkout == other.checkout &&
            mountPath == other.mountPath &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(authorizationToken, type, url, checkout, mountPath, additionalProperties)
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaManagedAgentsGitHubRepositoryResourceParams{authorizationToken=$authorizationToken, type=$type, url=$url, checkout=$checkout, mountPath=$mountPath, additionalProperties=$additionalProperties}"
}
