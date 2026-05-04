// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.memorystores.memoryversions

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

/**
 * Identifies who performed a write or redact operation. Captured at write time on the
 * `memory_version` row. The API key that created a session is not recorded on agent writes;
 * attribution answers who made the write, not who is ultimately responsible. Look up session
 * provenance separately via the [Sessions API](/en/api/sessions-retrieve).
 */
@JsonDeserialize(using = BetaManagedAgentsActor.Deserializer::class)
@JsonSerialize(using = BetaManagedAgentsActor.Serializer::class)
class BetaManagedAgentsActor
private constructor(
    private val session: BetaManagedAgentsSessionActor? = null,
    private val api: BetaManagedAgentsApiActor? = null,
    private val user: BetaManagedAgentsUserActor? = null,
    private val _json: JsonValue? = null,
) {

    /**
     * Attribution for a write made by an agent during a session, through the mounted filesystem at
     * `/mnt/memory/`.
     */
    fun session(): Optional<BetaManagedAgentsSessionActor> = Optional.ofNullable(session)

    /** Attribution for a write made directly via the public API (outside of any session). */
    fun api(): Optional<BetaManagedAgentsApiActor> = Optional.ofNullable(api)

    /** Attribution for a write made by a human user through the Anthropic Console. */
    fun user(): Optional<BetaManagedAgentsUserActor> = Optional.ofNullable(user)

    fun isSession(): Boolean = session != null

    fun isApi(): Boolean = api != null

    fun isUser(): Boolean = user != null

    /**
     * Attribution for a write made by an agent during a session, through the mounted filesystem at
     * `/mnt/memory/`.
     */
    fun asSession(): BetaManagedAgentsSessionActor = session.getOrThrow("session")

    /** Attribution for a write made directly via the public API (outside of any session). */
    fun asApi(): BetaManagedAgentsApiActor = api.getOrThrow("api")

    /** Attribution for a write made by a human user through the Anthropic Console. */
    fun asUser(): BetaManagedAgentsUserActor = user.getOrThrow("user")

    fun _json(): Optional<JsonValue> = Optional.ofNullable(_json)

    /**
     * Maps this instance's current variant to a value of type [T] using the given [visitor].
     *
     * Note that this method is _not_ forwards compatible with new variants from the API, unless
     * [visitor] overrides [Visitor.unknown]. To handle variants not known to this version of the
     * SDK gracefully, consider overriding [Visitor.unknown]:
     * ```java
     * import com.anthropic.core.JsonValue;
     * import java.util.Optional;
     *
     * Optional<String> result = betaManagedAgentsActor.accept(new BetaManagedAgentsActor.Visitor<Optional<String>>() {
     *     @Override
     *     public Optional<String> visitSession(BetaManagedAgentsSessionActor session) {
     *         return Optional.of(session.toString());
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
     * @throws AnthropicInvalidDataException if [Visitor.unknown] is not overridden in [visitor] and
     *   the current variant is unknown.
     */
    fun <T> accept(visitor: Visitor<T>): T =
        when {
            session != null -> visitor.visitSession(session)
            api != null -> visitor.visitApi(api)
            user != null -> visitor.visitUser(user)
            else -> visitor.unknown(_json)
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
    fun validate(): BetaManagedAgentsActor = apply {
        if (validated) {
            return@apply
        }

        accept(
            object : Visitor<Unit> {
                override fun visitSession(session: BetaManagedAgentsSessionActor) {
                    session.validate()
                }

                override fun visitApi(api: BetaManagedAgentsApiActor) {
                    api.validate()
                }

                override fun visitUser(user: BetaManagedAgentsUserActor) {
                    user.validate()
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
                override fun visitSession(session: BetaManagedAgentsSessionActor) =
                    session.validity()

                override fun visitApi(api: BetaManagedAgentsApiActor) = api.validity()

                override fun visitUser(user: BetaManagedAgentsUserActor) = user.validity()

                override fun unknown(json: JsonValue?) = 0
            }
        )

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaManagedAgentsActor &&
            session == other.session &&
            api == other.api &&
            user == other.user
    }

    override fun hashCode(): Int = Objects.hash(session, api, user)

    override fun toString(): String =
        when {
            session != null -> "BetaManagedAgentsActor{session=$session}"
            api != null -> "BetaManagedAgentsActor{api=$api}"
            user != null -> "BetaManagedAgentsActor{user=$user}"
            _json != null -> "BetaManagedAgentsActor{_unknown=$_json}"
            else -> throw IllegalStateException("Invalid BetaManagedAgentsActor")
        }

    companion object {

        /**
         * Attribution for a write made by an agent during a session, through the mounted filesystem
         * at `/mnt/memory/`.
         */
        @JvmStatic
        fun ofSession(session: BetaManagedAgentsSessionActor) =
            BetaManagedAgentsActor(session = session)

        /** Attribution for a write made directly via the public API (outside of any session). */
        @JvmStatic fun ofApi(api: BetaManagedAgentsApiActor) = BetaManagedAgentsActor(api = api)

        /** Attribution for a write made by a human user through the Anthropic Console. */
        @JvmStatic
        fun ofUser(user: BetaManagedAgentsUserActor) = BetaManagedAgentsActor(user = user)
    }

    /**
     * An interface that defines how to map each variant of [BetaManagedAgentsActor] to a value of
     * type [T].
     */
    interface Visitor<out T> {

        /**
         * Attribution for a write made by an agent during a session, through the mounted filesystem
         * at `/mnt/memory/`.
         */
        fun visitSession(session: BetaManagedAgentsSessionActor): T

        /** Attribution for a write made directly via the public API (outside of any session). */
        fun visitApi(api: BetaManagedAgentsApiActor): T

        /** Attribution for a write made by a human user through the Anthropic Console. */
        fun visitUser(user: BetaManagedAgentsUserActor): T

        /**
         * Maps an unknown variant of [BetaManagedAgentsActor] to a value of type [T].
         *
         * An instance of [BetaManagedAgentsActor] can contain an unknown variant if it was
         * deserialized from data that doesn't match any known variant. For example, if the SDK is
         * on an older version than the API, then the API may respond with new variants that the SDK
         * is unaware of.
         *
         * @throws AnthropicInvalidDataException in the default implementation.
         */
        fun unknown(json: JsonValue?): T {
            throw AnthropicInvalidDataException("Unknown BetaManagedAgentsActor: $json")
        }
    }

    internal class Deserializer :
        BaseDeserializer<BetaManagedAgentsActor>(BetaManagedAgentsActor::class) {

        override fun ObjectCodec.deserialize(node: JsonNode): BetaManagedAgentsActor {
            val json = JsonValue.fromJsonNode(node)
            val type = json.asObject().getOrNull()?.get("type")?.asString()?.getOrNull()

            when (type) {
                "session_actor" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaManagedAgentsSessionActor>())
                        ?.let { BetaManagedAgentsActor(session = it, _json = json) }
                        ?: BetaManagedAgentsActor(_json = json)
                }
                "api_actor" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaManagedAgentsApiActor>())?.let {
                        BetaManagedAgentsActor(api = it, _json = json)
                    } ?: BetaManagedAgentsActor(_json = json)
                }
                "user_actor" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaManagedAgentsUserActor>())?.let {
                        BetaManagedAgentsActor(user = it, _json = json)
                    } ?: BetaManagedAgentsActor(_json = json)
                }
            }

            return BetaManagedAgentsActor(_json = json)
        }
    }

    internal class Serializer :
        BaseSerializer<BetaManagedAgentsActor>(BetaManagedAgentsActor::class) {

        override fun serialize(
            value: BetaManagedAgentsActor,
            generator: JsonGenerator,
            provider: SerializerProvider,
        ) {
            when {
                value.session != null -> generator.writeObject(value.session)
                value.api != null -> generator.writeObject(value.api)
                value.user != null -> generator.writeObject(value.user)
                value._json != null -> generator.writeObject(value._json)
                else -> throw IllegalStateException("Invalid BetaManagedAgentsActor")
            }
        }
    }
}
