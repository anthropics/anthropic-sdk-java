package com.anthropic.models.beta.messages

import com.anthropic.core.BaseDeserializer
import com.anthropic.core.BaseSerializer
import com.anthropic.core.JsonValue
import com.anthropic.core.allMaxBy
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

@JsonDeserialize(using = BetaToolsetToolUseBlock.Deserializer::class)
@JsonSerialize(using = BetaToolsetToolUseBlock.Serializer::class)
class BetaToolsetToolUseBlock
private constructor(
    private val browser: BetaBrowserToolUseBlock? = null,
    private val computer: BetaComputerToolUseBlock? = null,
    private val betaToolUseBlock: BetaToolUseBlock? = null,
    private val _json: JsonValue? = null,
) {

    fun browser(): Optional<BetaBrowserToolUseBlock> = Optional.ofNullable(browser)

    fun computer(): Optional<BetaComputerToolUseBlock> = Optional.ofNullable(computer)

    fun betaToolUseBlock(): Optional<BetaToolUseBlock> = Optional.ofNullable(betaToolUseBlock)

    fun isBrowser(): Boolean = browser != null

    fun isComputer(): Boolean = computer != null

    fun isBetaToolUseBlock(): Boolean = betaToolUseBlock != null

    fun asBrowser(): BetaBrowserToolUseBlock = browser.getOrThrow("browser")

    fun asComputer(): BetaComputerToolUseBlock = computer.getOrThrow("computer")

    fun asBetaToolUseBlock(): BetaToolUseBlock = betaToolUseBlock.getOrThrow("betaToolUseBlock")

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
     * Optional<String> result = betaToolsetToolUseBlock.accept(new BetaToolsetToolUseBlock.Visitor<Optional<String>>() {
     *     @Override
     *     public Optional<String> visitBrowser(BetaBrowserToolUseBlock browser) {
     *         return Optional.of(browser.toString());
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
            browser != null -> visitor.visitBrowser(browser)
            computer != null -> visitor.visitComputer(computer)
            betaToolUseBlock != null -> visitor.visitBetaToolUseBlock(betaToolUseBlock)
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
    fun validate(): BetaToolsetToolUseBlock = apply {
        if (validated) {
            return@apply
        }

        when {
            browser != null -> browser.validate()
            computer != null -> computer.validate()
            betaToolUseBlock != null -> betaToolUseBlock.validate()
            else -> throw AnthropicInvalidDataException("Unknown BetaToolsetToolUseBlock: $_json")
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
        when {
            browser != null -> browser.validity()
            computer != null -> computer.validity()
            betaToolUseBlock != null -> betaToolUseBlock.validity()
            else -> 0
        }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaToolsetToolUseBlock &&
            browser == other.browser &&
            computer == other.computer &&
            betaToolUseBlock == other.betaToolUseBlock
    }

    override fun hashCode(): Int = Objects.hash(browser, computer, betaToolUseBlock)

    override fun toString(): String =
        when {
            browser != null -> "BetaToolsetToolUseBlock{browser=$browser}"
            computer != null -> "BetaToolsetToolUseBlock{computer=$computer}"
            betaToolUseBlock != null ->
                "BetaToolsetToolUseBlock{betaToolUseBlock=$betaToolUseBlock}"
            _json != null -> "BetaToolsetToolUseBlock{_unknown=$_json}"
            else -> throw IllegalStateException("Invalid BetaToolsetToolUseBlock")
        }

    companion object {

        @JvmStatic
        fun ofBrowser(browser: BetaBrowserToolUseBlock) = BetaToolsetToolUseBlock(browser = browser)

        @JvmStatic
        fun ofComputer(computer: BetaComputerToolUseBlock) =
            BetaToolsetToolUseBlock(computer = computer)

        @JvmStatic
        fun ofBetaToolUseBlock(betaToolUseBlock: BetaToolUseBlock) =
            BetaToolsetToolUseBlock(betaToolUseBlock = betaToolUseBlock)
    }

    /**
     * An interface that defines how to map each variant of [BetaToolsetToolUseBlock] to a value of
     * type [T].
     */
    interface Visitor<out T> {

        fun visitBrowser(browser: BetaBrowserToolUseBlock): T

        fun visitComputer(computer: BetaComputerToolUseBlock): T

        fun visitBetaToolUseBlock(betaToolUseBlock: BetaToolUseBlock): T

        /**
         * Maps an unknown variant of [BetaToolsetToolUseBlock] to a value of type [T].
         *
         * An instance of [BetaToolsetToolUseBlock] can contain an unknown variant if it was
         * deserialized from data that doesn't match any known variant. For example, if the SDK is
         * on an older version than the API, then the API may respond with new variants that the SDK
         * is unaware of.
         *
         * @throws AnthropicInvalidDataException in the default implementation.
         */
        fun unknown(json: JsonValue?): T {
            throw AnthropicInvalidDataException("Unknown BetaToolsetToolUseBlock: $json")
        }
    }

    internal class Deserializer :
        BaseDeserializer<BetaToolsetToolUseBlock>(BetaToolsetToolUseBlock::class) {

        override fun ObjectCodec.deserialize(node: JsonNode): BetaToolsetToolUseBlock {
            val json = JsonValue.fromJsonNode(node)

            val bestMatches =
                sequenceOf(
                        tryDeserialize(node, jacksonTypeRef<BetaBrowserToolUseBlock>())?.let {
                            BetaToolsetToolUseBlock(browser = it, _json = json)
                        },
                        tryDeserialize(node, jacksonTypeRef<BetaComputerToolUseBlock>())?.let {
                            BetaToolsetToolUseBlock(computer = it, _json = json)
                        },
                        tryDeserialize(node, jacksonTypeRef<BetaToolUseBlock>())?.let {
                            BetaToolsetToolUseBlock(betaToolUseBlock = it, _json = json)
                        },
                    )
                    .filterNotNull()
                    .allMaxBy { it.validity() }
                    .toList()
            return when (bestMatches.size) {
                // This can happen if what we're deserializing is completely incompatible with all
                // the possible variants (e.g. deserializing from boolean).
                0 -> BetaToolsetToolUseBlock(_json = json)
                1 -> bestMatches.single()
                // If there's more than one match with the highest validity, then use the first
                // completely valid match, or simply the first match if none are completely valid.
                else -> bestMatches.firstOrNull { it.isValid() } ?: bestMatches.first()
            }
        }
    }

    internal class Serializer :
        BaseSerializer<BetaToolsetToolUseBlock>(BetaToolsetToolUseBlock::class) {

        override fun serialize(
            value: BetaToolsetToolUseBlock,
            generator: JsonGenerator,
            provider: SerializerProvider,
        ) {
            when {
                value.browser != null -> generator.writeObject(value.browser)
                value.computer != null -> generator.writeObject(value.computer)
                value.betaToolUseBlock != null -> generator.writeObject(value.betaToolUseBlock)
                value._json != null -> generator.writeObject(value._json)
                else -> throw IllegalStateException("Invalid BetaToolsetToolUseBlock")
            }
        }
    }
}
