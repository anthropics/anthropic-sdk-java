package com.anthropic.models.messages

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

@JsonDeserialize(using = ToolsetToolUseBlock.Deserializer::class)
@JsonSerialize(using = ToolsetToolUseBlock.Serializer::class)
class ToolsetToolUseBlock
private constructor(
    private val browser: BrowserToolUseBlock? = null,
    private val computer: ComputerToolUseBlock? = null,
    private val toolUseBlock: ToolUseBlock? = null,
    private val _json: JsonValue? = null,
) {

    fun browser(): Optional<BrowserToolUseBlock> = Optional.ofNullable(browser)

    fun computer(): Optional<ComputerToolUseBlock> = Optional.ofNullable(computer)

    fun toolUseBlock(): Optional<ToolUseBlock> = Optional.ofNullable(toolUseBlock)

    fun isBrowser(): Boolean = browser != null

    fun isComputer(): Boolean = computer != null

    fun isToolUseBlock(): Boolean = toolUseBlock != null

    fun asBrowser(): BrowserToolUseBlock = browser.getOrThrow("browser")

    fun asComputer(): ComputerToolUseBlock = computer.getOrThrow("computer")

    fun asToolUseBlock(): ToolUseBlock = toolUseBlock.getOrThrow("toolUseBlock")

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
     * Optional<String> result = toolsetToolUseBlock.accept(new ToolsetToolUseBlock.Visitor<Optional<String>>() {
     *     @Override
     *     public Optional<String> visitBrowser(BrowserToolUseBlock browser) {
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
            toolUseBlock != null -> visitor.visitToolUseBlock(toolUseBlock)
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
    fun validate(): ToolsetToolUseBlock = apply {
        if (validated) {
            return@apply
        }

        when {
            browser != null -> browser.validate()
            computer != null -> computer.validate()
            toolUseBlock != null -> toolUseBlock.validate()
            else -> throw AnthropicInvalidDataException("Unknown ToolsetToolUseBlock: $_json")
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
            toolUseBlock != null -> toolUseBlock.validity()
            else -> 0
        }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is ToolsetToolUseBlock &&
            browser == other.browser &&
            computer == other.computer &&
            toolUseBlock == other.toolUseBlock
    }

    override fun hashCode(): Int = Objects.hash(browser, computer, toolUseBlock)

    override fun toString(): String =
        when {
            browser != null -> "ToolsetToolUseBlock{browser=$browser}"
            computer != null -> "ToolsetToolUseBlock{computer=$computer}"
            toolUseBlock != null -> "ToolsetToolUseBlock{toolUseBlock=$toolUseBlock}"
            _json != null -> "ToolsetToolUseBlock{_unknown=$_json}"
            else -> throw IllegalStateException("Invalid ToolsetToolUseBlock")
        }

    companion object {

        @JvmStatic
        fun ofBrowser(browser: BrowserToolUseBlock) = ToolsetToolUseBlock(browser = browser)

        @JvmStatic
        fun ofComputer(computer: ComputerToolUseBlock) = ToolsetToolUseBlock(computer = computer)

        @JvmStatic
        fun ofToolUseBlock(toolUseBlock: ToolUseBlock) =
            ToolsetToolUseBlock(toolUseBlock = toolUseBlock)
    }

    /**
     * An interface that defines how to map each variant of [ToolsetToolUseBlock] to a value of type
     * [T].
     */
    interface Visitor<out T> {

        fun visitBrowser(browser: BrowserToolUseBlock): T

        fun visitComputer(computer: ComputerToolUseBlock): T

        fun visitToolUseBlock(toolUseBlock: ToolUseBlock): T

        /**
         * Maps an unknown variant of [ToolsetToolUseBlock] to a value of type [T].
         *
         * An instance of [ToolsetToolUseBlock] can contain an unknown variant if it was
         * deserialized from data that doesn't match any known variant. For example, if the SDK is
         * on an older version than the API, then the API may respond with new variants that the SDK
         * is unaware of.
         *
         * @throws AnthropicInvalidDataException in the default implementation.
         */
        fun unknown(json: JsonValue?): T {
            throw AnthropicInvalidDataException("Unknown ToolsetToolUseBlock: $json")
        }
    }

    internal class Deserializer :
        BaseDeserializer<ToolsetToolUseBlock>(ToolsetToolUseBlock::class) {

        override fun ObjectCodec.deserialize(node: JsonNode): ToolsetToolUseBlock {
            val json = JsonValue.fromJsonNode(node)

            val bestMatches =
                sequenceOf(
                        tryDeserialize(node, jacksonTypeRef<BrowserToolUseBlock>())?.let {
                            ToolsetToolUseBlock(browser = it, _json = json)
                        },
                        tryDeserialize(node, jacksonTypeRef<ComputerToolUseBlock>())?.let {
                            ToolsetToolUseBlock(computer = it, _json = json)
                        },
                        tryDeserialize(node, jacksonTypeRef<ToolUseBlock>())?.let {
                            ToolsetToolUseBlock(toolUseBlock = it, _json = json)
                        },
                    )
                    .filterNotNull()
                    .allMaxBy { it.validity() }
                    .toList()
            return when (bestMatches.size) {
                // This can happen if what we're deserializing is completely incompatible with all
                // the possible variants (e.g. deserializing from boolean).
                0 -> ToolsetToolUseBlock(_json = json)
                1 -> bestMatches.single()
                // If there's more than one match with the highest validity, then use the first
                // completely valid match, or simply the first match if none are completely valid.
                else -> bestMatches.firstOrNull { it.isValid() } ?: bestMatches.first()
            }
        }
    }

    internal class Serializer : BaseSerializer<ToolsetToolUseBlock>(ToolsetToolUseBlock::class) {

        override fun serialize(
            value: ToolsetToolUseBlock,
            generator: JsonGenerator,
            provider: SerializerProvider,
        ) {
            when {
                value.browser != null -> generator.writeObject(value.browser)
                value.computer != null -> generator.writeObject(value.computer)
                value.toolUseBlock != null -> generator.writeObject(value.toolUseBlock)
                value._json != null -> generator.writeObject(value._json)
                else -> throw IllegalStateException("Invalid ToolsetToolUseBlock")
            }
        }
    }
}
