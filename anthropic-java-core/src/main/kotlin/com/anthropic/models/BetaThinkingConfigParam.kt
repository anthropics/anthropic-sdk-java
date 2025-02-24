// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models

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
 * Configuration for enabling Claude's extended thinking.
 *
 * When enabled, responses include `thinking` content blocks showing Claude's thinking process
 * before the final answer. Requires a minimum budget of 1,024 tokens and counts towards your
 * `max_tokens` limit.
 *
 * See [extended thinking](https://docs.anthropic.com/en/docs/build-with-claude/extended-thinking)
 * for details.
 */
@JsonDeserialize(using = BetaThinkingConfigParam.Deserializer::class)
@JsonSerialize(using = BetaThinkingConfigParam.Serializer::class)
class BetaThinkingConfigParam
private constructor(
    private val enabled: BetaThinkingConfigEnabled? = null,
    private val disabled: BetaThinkingConfigDisabled? = null,
    private val _json: JsonValue? = null,
) {

    fun enabled(): Optional<BetaThinkingConfigEnabled> = Optional.ofNullable(enabled)

    fun disabled(): Optional<BetaThinkingConfigDisabled> = Optional.ofNullable(disabled)

    fun isEnabled(): Boolean = enabled != null

    fun isDisabled(): Boolean = disabled != null

    fun asEnabled(): BetaThinkingConfigEnabled = enabled.getOrThrow("enabled")

    fun asDisabled(): BetaThinkingConfigDisabled = disabled.getOrThrow("disabled")

    fun _json(): Optional<JsonValue> = Optional.ofNullable(_json)

    fun <T> accept(visitor: Visitor<T>): T {
        return when {
            enabled != null -> visitor.visitEnabled(enabled)
            disabled != null -> visitor.visitDisabled(disabled)
            else -> visitor.unknown(_json)
        }
    }

    private var validated: Boolean = false

    fun validate(): BetaThinkingConfigParam = apply {
        if (validated) {
            return@apply
        }

        accept(
            object : Visitor<Unit> {
                override fun visitEnabled(enabled: BetaThinkingConfigEnabled) {
                    enabled.validate()
                }

                override fun visitDisabled(disabled: BetaThinkingConfigDisabled) {
                    disabled.validate()
                }
            }
        )
        validated = true
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return /* spotless:off */ other is BetaThinkingConfigParam && enabled == other.enabled && disabled == other.disabled /* spotless:on */
    }

    override fun hashCode(): Int = /* spotless:off */ Objects.hash(enabled, disabled) /* spotless:on */

    override fun toString(): String =
        when {
            enabled != null -> "BetaThinkingConfigParam{enabled=$enabled}"
            disabled != null -> "BetaThinkingConfigParam{disabled=$disabled}"
            _json != null -> "BetaThinkingConfigParam{_unknown=$_json}"
            else -> throw IllegalStateException("Invalid BetaThinkingConfigParam")
        }

    companion object {

        @JvmStatic
        fun ofEnabled(enabled: BetaThinkingConfigEnabled) =
            BetaThinkingConfigParam(enabled = enabled)

        @JvmStatic
        fun ofDisabled(disabled: BetaThinkingConfigDisabled) =
            BetaThinkingConfigParam(disabled = disabled)
    }

    /**
     * An interface that defines how to map each variant of [BetaThinkingConfigParam] to a value of
     * type [T].
     */
    interface Visitor<out T> {

        fun visitEnabled(enabled: BetaThinkingConfigEnabled): T

        fun visitDisabled(disabled: BetaThinkingConfigDisabled): T

        /**
         * Maps an unknown variant of [BetaThinkingConfigParam] to a value of type [T].
         *
         * An instance of [BetaThinkingConfigParam] can contain an unknown variant if it was
         * deserialized from data that doesn't match any known variant. For example, if the SDK is
         * on an older version than the API, then the API may respond with new variants that the SDK
         * is unaware of.
         *
         * @throws AnthropicInvalidDataException in the default implementation.
         */
        fun unknown(json: JsonValue?): T {
            throw AnthropicInvalidDataException("Unknown BetaThinkingConfigParam: $json")
        }
    }

    internal class Deserializer :
        BaseDeserializer<BetaThinkingConfigParam>(BetaThinkingConfigParam::class) {

        override fun ObjectCodec.deserialize(node: JsonNode): BetaThinkingConfigParam {
            val json = JsonValue.fromJsonNode(node)
            val type = json.asObject().getOrNull()?.get("type")?.asString()?.getOrNull()

            when (type) {
                "enabled" -> {
                    tryDeserialize(node, jacksonTypeRef<BetaThinkingConfigEnabled>()) {
                            it.validate()
                        }
                        ?.let {
                            return BetaThinkingConfigParam(enabled = it, _json = json)
                        }
                }
                "disabled" -> {
                    tryDeserialize(node, jacksonTypeRef<BetaThinkingConfigDisabled>()) {
                            it.validate()
                        }
                        ?.let {
                            return BetaThinkingConfigParam(disabled = it, _json = json)
                        }
                }
            }

            return BetaThinkingConfigParam(_json = json)
        }
    }

    internal class Serializer :
        BaseSerializer<BetaThinkingConfigParam>(BetaThinkingConfigParam::class) {

        override fun serialize(
            value: BetaThinkingConfigParam,
            generator: JsonGenerator,
            provider: SerializerProvider,
        ) {
            when {
                value.enabled != null -> generator.writeObject(value.enabled)
                value.disabled != null -> generator.writeObject(value.disabled)
                value._json != null -> generator.writeObject(value._json)
                else -> throw IllegalStateException("Invalid BetaThinkingConfigParam")
            }
        }
    }
}
