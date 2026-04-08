// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.sessions.resources

import com.anthropic.core.BaseDeserializer
import com.anthropic.core.BaseSerializer
import com.anthropic.core.JsonValue
import com.anthropic.core.getOrThrow
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.ObjectCodec
import com.fasterxml.jackson.databind.JsonNode
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

@JsonDeserialize(using = BetaManagedAgentsSessionResource.Deserializer::class)
@JsonSerialize(using = BetaManagedAgentsSessionResource.Serializer::class)
class BetaManagedAgentsSessionResource
private constructor(
    private val githubRepository: BetaManagedAgentsGitHubRepositoryResource? = null,
    private val file: BetaManagedAgentsFileResource? = null,
    private val _json: JsonValue? = null,
) {

    fun githubRepository(): Optional<BetaManagedAgentsGitHubRepositoryResource> =
        Optional.ofNullable(githubRepository)

    fun file(): Optional<BetaManagedAgentsFileResource> = Optional.ofNullable(file)

    fun isGitHubRepository(): Boolean = githubRepository != null

    fun isFile(): Boolean = file != null

    fun asGitHubRepository(): BetaManagedAgentsGitHubRepositoryResource =
        githubRepository.getOrThrow("githubRepository")

    fun asFile(): BetaManagedAgentsFileResource = file.getOrThrow("file")

    fun _json(): Optional<JsonValue> = Optional.ofNullable(_json)

    fun <T> accept(visitor: Visitor<T>): T =
        when {
            githubRepository != null -> visitor.visitGitHubRepository(githubRepository)
            file != null -> visitor.visitFile(file)
            else -> visitor.unknown(_json)
        }

    private var validated: Boolean = false

    fun validate(): BetaManagedAgentsSessionResource = apply {
        if (validated) {
            return@apply
        }

        accept(
            object : Visitor<Unit> {
                override fun visitGitHubRepository(
                    githubRepository: BetaManagedAgentsGitHubRepositoryResource
                ) {
                    githubRepository.validate()
                }

                override fun visitFile(file: BetaManagedAgentsFileResource) {
                    file.validate()
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
     * Returns a score indicating how many valid values are contained in this object recursively.
     *
     * Used for best match union deserialization.
     */
    @JvmSynthetic
    internal fun validity(): Int =
        accept(
            object : Visitor<Int> {
                override fun visitGitHubRepository(
                    githubRepository: BetaManagedAgentsGitHubRepositoryResource
                ) = githubRepository.validity()

                override fun visitFile(file: BetaManagedAgentsFileResource) = file.validity()

                override fun unknown(json: JsonValue?) = 0
            }
        )

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaManagedAgentsSessionResource &&
            githubRepository == other.githubRepository &&
            file == other.file
    }

    override fun hashCode(): Int = Objects.hash(githubRepository, file)

    override fun toString(): String =
        when {
            githubRepository != null ->
                "BetaManagedAgentsSessionResource{githubRepository=$githubRepository}"
            file != null -> "BetaManagedAgentsSessionResource{file=$file}"
            _json != null -> "BetaManagedAgentsSessionResource{_unknown=$_json}"
            else -> throw IllegalStateException("Invalid BetaManagedAgentsSessionResource")
        }

    companion object {

        @JvmStatic
        fun ofGitHubRepository(githubRepository: BetaManagedAgentsGitHubRepositoryResource) =
            BetaManagedAgentsSessionResource(githubRepository = githubRepository)

        @JvmStatic
        fun ofFile(file: BetaManagedAgentsFileResource) =
            BetaManagedAgentsSessionResource(file = file)
    }

    /**
     * An interface that defines how to map each variant of [BetaManagedAgentsSessionResource] to a
     * value of type [T].
     */
    interface Visitor<out T> {

        fun visitGitHubRepository(githubRepository: BetaManagedAgentsGitHubRepositoryResource): T

        fun visitFile(file: BetaManagedAgentsFileResource): T

        /**
         * Maps an unknown variant of [BetaManagedAgentsSessionResource] to a value of type [T].
         *
         * An instance of [BetaManagedAgentsSessionResource] can contain an unknown variant if it
         * was deserialized from data that doesn't match any known variant. For example, if the SDK
         * is on an older version than the API, then the API may respond with new variants that the
         * SDK is unaware of.
         *
         * @throws AnthropicInvalidDataException in the default implementation.
         */
        fun unknown(json: JsonValue?): T {
            throw AnthropicInvalidDataException("Unknown BetaManagedAgentsSessionResource: $json")
        }
    }

    internal class Deserializer :
        BaseDeserializer<BetaManagedAgentsSessionResource>(
            BetaManagedAgentsSessionResource::class
        ) {

        override fun ObjectCodec.deserialize(node: JsonNode): BetaManagedAgentsSessionResource {
            val json = JsonValue.fromJsonNode(node)
            val type = json.asObject().getOrNull()?.get("type")?.asString()?.getOrNull()

            when (type) {
                "github_repository" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaManagedAgentsGitHubRepositoryResource>(),
                        )
                        ?.let {
                            BetaManagedAgentsSessionResource(githubRepository = it, _json = json)
                        } ?: BetaManagedAgentsSessionResource(_json = json)
                }
                "file" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaManagedAgentsFileResource>())
                        ?.let { BetaManagedAgentsSessionResource(file = it, _json = json) }
                        ?: BetaManagedAgentsSessionResource(_json = json)
                }
            }

            return BetaManagedAgentsSessionResource(_json = json)
        }
    }

    internal class Serializer :
        BaseSerializer<BetaManagedAgentsSessionResource>(BetaManagedAgentsSessionResource::class) {

        override fun serialize(
            value: BetaManagedAgentsSessionResource,
            generator: JsonGenerator,
            provider: SerializerProvider,
        ) {
            when {
                value.githubRepository != null -> generator.writeObject(value.githubRepository)
                value.file != null -> generator.writeObject(value.file)
                value._json != null -> generator.writeObject(value._json)
                else -> throw IllegalStateException("Invalid BetaManagedAgentsSessionResource")
            }
        }
    }
}
