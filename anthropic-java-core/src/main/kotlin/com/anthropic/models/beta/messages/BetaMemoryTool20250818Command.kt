// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.messages

import com.anthropic.core.BaseDeserializer
import com.anthropic.core.BaseSerializer
import com.anthropic.core.Enum
import com.anthropic.core.JsonField
import com.anthropic.core.JsonMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.getOrThrow
import com.anthropic.core.getProperty
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.annotation.JsonCreator
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

@JsonDeserialize(using = BetaMemoryTool20250818Command.Deserializer::class)
@JsonSerialize(using = BetaMemoryTool20250818Command.Serializer::class)
class BetaMemoryTool20250818Command
private constructor(
    private val view: BetaMemoryTool20250818ViewCommand? = null,
    private val create: BetaMemoryTool20250818CreateCommand? = null,
    private val strReplace: BetaMemoryTool20250818StrReplaceCommand? = null,
    private val insert: BetaMemoryTool20250818InsertCommand? = null,
    private val delete: BetaMemoryTool20250818DeleteCommand? = null,
    private val rename: BetaMemoryTool20250818RenameCommand? = null,
    private val _json: JsonValue? = null,
) {

    fun command(): Command =
        accept(
            object : Visitor<Command> {
                override fun visitView(view: BetaMemoryTool20250818ViewCommand): Command =
                    Command.VIEW

                override fun visitCreate(create: BetaMemoryTool20250818CreateCommand): Command =
                    Command.CREATE

                override fun visitStrReplace(
                    strReplace: BetaMemoryTool20250818StrReplaceCommand
                ): Command = Command.STR_REPLACE

                override fun visitInsert(insert: BetaMemoryTool20250818InsertCommand): Command =
                    Command.INSERT

                override fun visitDelete(delete: BetaMemoryTool20250818DeleteCommand): Command =
                    Command.DELETE

                override fun visitRename(rename: BetaMemoryTool20250818RenameCommand): Command =
                    Command.RENAME

                override fun unknown(json: JsonValue?): Command =
                    Command.of(json?.asObject()?.getOrNull()?.get("command") ?: JsonMissing.of())
            }
        )

    fun path(): Optional<String> =
        accept(
            object : Visitor<Optional<String>> {
                override fun visitView(view: BetaMemoryTool20250818ViewCommand): Optional<String> =
                    Optional.of(view.path())

                override fun visitCreate(
                    create: BetaMemoryTool20250818CreateCommand
                ): Optional<String> = Optional.of(create.path())

                override fun visitStrReplace(
                    strReplace: BetaMemoryTool20250818StrReplaceCommand
                ): Optional<String> = Optional.of(strReplace.path())

                override fun visitInsert(
                    insert: BetaMemoryTool20250818InsertCommand
                ): Optional<String> = Optional.of(insert.path())

                override fun visitDelete(
                    delete: BetaMemoryTool20250818DeleteCommand
                ): Optional<String> = Optional.of(delete.path())

                override fun visitRename(
                    rename: BetaMemoryTool20250818RenameCommand
                ): Optional<String> = Optional.empty()

                override fun unknown(json: JsonValue?): Optional<String> =
                    json.getProperty<String>("path").asKnown()
            }
        )

    fun view(): Optional<BetaMemoryTool20250818ViewCommand> = Optional.ofNullable(view)

    fun create(): Optional<BetaMemoryTool20250818CreateCommand> = Optional.ofNullable(create)

    fun strReplace(): Optional<BetaMemoryTool20250818StrReplaceCommand> =
        Optional.ofNullable(strReplace)

    fun insert(): Optional<BetaMemoryTool20250818InsertCommand> = Optional.ofNullable(insert)

    fun delete(): Optional<BetaMemoryTool20250818DeleteCommand> = Optional.ofNullable(delete)

    fun rename(): Optional<BetaMemoryTool20250818RenameCommand> = Optional.ofNullable(rename)

    fun isView(): Boolean = view != null

    fun isCreate(): Boolean = create != null

    fun isStrReplace(): Boolean = strReplace != null

    fun isInsert(): Boolean = insert != null

    fun isDelete(): Boolean = delete != null

    fun isRename(): Boolean = rename != null

    fun asView(): BetaMemoryTool20250818ViewCommand = view.getOrThrow("view")

    fun asCreate(): BetaMemoryTool20250818CreateCommand = create.getOrThrow("create")

    fun asStrReplace(): BetaMemoryTool20250818StrReplaceCommand =
        strReplace.getOrThrow("strReplace")

    fun asInsert(): BetaMemoryTool20250818InsertCommand = insert.getOrThrow("insert")

    fun asDelete(): BetaMemoryTool20250818DeleteCommand = delete.getOrThrow("delete")

    fun asRename(): BetaMemoryTool20250818RenameCommand = rename.getOrThrow("rename")

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
     * Optional<String> result = betaMemoryTool20250818Command.accept(new BetaMemoryTool20250818Command.Visitor<Optional<String>>() {
     *     @Override
     *     public Optional<String> visitView(BetaMemoryTool20250818ViewCommand view) {
     *         return Optional.of(view.toString());
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
            view != null -> visitor.visitView(view)
            create != null -> visitor.visitCreate(create)
            strReplace != null -> visitor.visitStrReplace(strReplace)
            insert != null -> visitor.visitInsert(insert)
            delete != null -> visitor.visitDelete(delete)
            rename != null -> visitor.visitRename(rename)
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
    fun validate(): BetaMemoryTool20250818Command = apply {
        if (validated) {
            return@apply
        }

        accept(
            object : Visitor<Unit> {
                override fun visitView(view: BetaMemoryTool20250818ViewCommand) {
                    view.validate()
                }

                override fun visitCreate(create: BetaMemoryTool20250818CreateCommand) {
                    create.validate()
                }

                override fun visitStrReplace(strReplace: BetaMemoryTool20250818StrReplaceCommand) {
                    strReplace.validate()
                }

                override fun visitInsert(insert: BetaMemoryTool20250818InsertCommand) {
                    insert.validate()
                }

                override fun visitDelete(delete: BetaMemoryTool20250818DeleteCommand) {
                    delete.validate()
                }

                override fun visitRename(rename: BetaMemoryTool20250818RenameCommand) {
                    rename.validate()
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
                override fun visitView(view: BetaMemoryTool20250818ViewCommand) = view.validity()

                override fun visitCreate(create: BetaMemoryTool20250818CreateCommand) =
                    create.validity()

                override fun visitStrReplace(strReplace: BetaMemoryTool20250818StrReplaceCommand) =
                    strReplace.validity()

                override fun visitInsert(insert: BetaMemoryTool20250818InsertCommand) =
                    insert.validity()

                override fun visitDelete(delete: BetaMemoryTool20250818DeleteCommand) =
                    delete.validity()

                override fun visitRename(rename: BetaMemoryTool20250818RenameCommand) =
                    rename.validity()

                override fun unknown(json: JsonValue?) = 0
            }
        )

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaMemoryTool20250818Command &&
            view == other.view &&
            create == other.create &&
            strReplace == other.strReplace &&
            insert == other.insert &&
            delete == other.delete &&
            rename == other.rename
    }

    override fun hashCode(): Int = Objects.hash(view, create, strReplace, insert, delete, rename)

    override fun toString(): String =
        when {
            view != null -> "BetaMemoryTool20250818Command{view=$view}"
            create != null -> "BetaMemoryTool20250818Command{create=$create}"
            strReplace != null -> "BetaMemoryTool20250818Command{strReplace=$strReplace}"
            insert != null -> "BetaMemoryTool20250818Command{insert=$insert}"
            delete != null -> "BetaMemoryTool20250818Command{delete=$delete}"
            rename != null -> "BetaMemoryTool20250818Command{rename=$rename}"
            _json != null -> "BetaMemoryTool20250818Command{_unknown=$_json}"
            else -> throw IllegalStateException("Invalid BetaMemoryTool20250818Command")
        }

    companion object {

        @JvmStatic
        fun ofView(view: BetaMemoryTool20250818ViewCommand) =
            BetaMemoryTool20250818Command(view = view)

        /**
         * Returns an immutable instance of [BetaMemoryTool20250818Command] whose [ofView] variant
         * is built from the given required [path].
         */
        @JvmStatic fun ofView(path: String) = ofView(BetaMemoryTool20250818ViewCommand.of(path))

        @JvmStatic
        fun ofCreate(create: BetaMemoryTool20250818CreateCommand) =
            BetaMemoryTool20250818Command(create = create)

        @JvmStatic
        fun ofStrReplace(strReplace: BetaMemoryTool20250818StrReplaceCommand) =
            BetaMemoryTool20250818Command(strReplace = strReplace)

        @JvmStatic
        fun ofInsert(insert: BetaMemoryTool20250818InsertCommand) =
            BetaMemoryTool20250818Command(insert = insert)

        @JvmStatic
        fun ofDelete(delete: BetaMemoryTool20250818DeleteCommand) =
            BetaMemoryTool20250818Command(delete = delete)

        /**
         * Returns an immutable instance of [BetaMemoryTool20250818Command] whose [ofDelete] variant
         * is built from the given required [path].
         */
        @JvmStatic
        fun ofDelete(path: String) = ofDelete(BetaMemoryTool20250818DeleteCommand.of(path))

        @JvmStatic
        fun ofRename(rename: BetaMemoryTool20250818RenameCommand) =
            BetaMemoryTool20250818Command(rename = rename)
    }

    /**
     * An interface that defines how to map each variant of [BetaMemoryTool20250818Command] to a
     * value of type [T].
     */
    interface Visitor<out T> {

        fun visitView(view: BetaMemoryTool20250818ViewCommand): T

        fun visitCreate(create: BetaMemoryTool20250818CreateCommand): T

        fun visitStrReplace(strReplace: BetaMemoryTool20250818StrReplaceCommand): T

        fun visitInsert(insert: BetaMemoryTool20250818InsertCommand): T

        fun visitDelete(delete: BetaMemoryTool20250818DeleteCommand): T

        fun visitRename(rename: BetaMemoryTool20250818RenameCommand): T

        /**
         * Maps an unknown variant of [BetaMemoryTool20250818Command] to a value of type [T].
         *
         * An instance of [BetaMemoryTool20250818Command] can contain an unknown variant if it was
         * deserialized from data that doesn't match any known variant. For example, if the SDK is
         * on an older version than the API, then the API may respond with new variants that the SDK
         * is unaware of.
         *
         * @throws AnthropicInvalidDataException in the default implementation.
         */
        fun unknown(json: JsonValue?): T {
            throw AnthropicInvalidDataException("Unknown BetaMemoryTool20250818Command: $json")
        }
    }

    internal class Deserializer :
        BaseDeserializer<BetaMemoryTool20250818Command>(BetaMemoryTool20250818Command::class) {

        override fun ObjectCodec.deserialize(node: JsonNode): BetaMemoryTool20250818Command {
            val json = JsonValue.fromJsonNode(node)
            val command = json.asObject().getOrNull()?.get("command")?.asString()?.getOrNull()

            when (command) {
                "view" -> {
                    return tryDeserialize(node, jacksonTypeRef<BetaMemoryTool20250818ViewCommand>())
                        ?.let { BetaMemoryTool20250818Command(view = it, _json = json) }
                        ?: BetaMemoryTool20250818Command(_json = json)
                }
                "create" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaMemoryTool20250818CreateCommand>(),
                        )
                        ?.let { BetaMemoryTool20250818Command(create = it, _json = json) }
                        ?: BetaMemoryTool20250818Command(_json = json)
                }
                "str_replace" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaMemoryTool20250818StrReplaceCommand>(),
                        )
                        ?.let { BetaMemoryTool20250818Command(strReplace = it, _json = json) }
                        ?: BetaMemoryTool20250818Command(_json = json)
                }
                "insert" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaMemoryTool20250818InsertCommand>(),
                        )
                        ?.let { BetaMemoryTool20250818Command(insert = it, _json = json) }
                        ?: BetaMemoryTool20250818Command(_json = json)
                }
                "delete" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaMemoryTool20250818DeleteCommand>(),
                        )
                        ?.let { BetaMemoryTool20250818Command(delete = it, _json = json) }
                        ?: BetaMemoryTool20250818Command(_json = json)
                }
                "rename" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BetaMemoryTool20250818RenameCommand>(),
                        )
                        ?.let { BetaMemoryTool20250818Command(rename = it, _json = json) }
                        ?: BetaMemoryTool20250818Command(_json = json)
                }
            }

            return BetaMemoryTool20250818Command(_json = json)
        }
    }

    internal class Serializer :
        BaseSerializer<BetaMemoryTool20250818Command>(BetaMemoryTool20250818Command::class) {

        override fun serialize(
            value: BetaMemoryTool20250818Command,
            generator: JsonGenerator,
            provider: SerializerProvider,
        ) {
            when {
                value.view != null -> generator.writeObject(value.view)
                value.create != null -> generator.writeObject(value.create)
                value.strReplace != null -> generator.writeObject(value.strReplace)
                value.insert != null -> generator.writeObject(value.insert)
                value.delete != null -> generator.writeObject(value.delete)
                value.rename != null -> generator.writeObject(value.rename)
                value._json != null -> generator.writeObject(value._json)
                else -> throw IllegalStateException("Invalid BetaMemoryTool20250818Command")
            }
        }
    }

    class Command @JsonCreator private constructor(private val value: JsonField<String>) : Enum {

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

            @JvmField val VIEW = of("view")

            @JvmField val CREATE = of("create")

            @JvmField val STR_REPLACE = of("str_replace")

            @JvmField val INSERT = of("insert")

            @JvmField val DELETE = of("delete")

            @JvmField val RENAME = of("rename")

            @JvmStatic fun of(value: String) = Command(JsonField.of(value))

            @JvmSynthetic
            internal fun of(value: JsonField<String>): Command =
                value.asString().getOrNull()?.let { of(it) } ?: Command(value)
        }

        /** An enum containing [Command]'s known values. */
        enum class Known {
            VIEW,
            CREATE,
            STR_REPLACE,
            INSERT,
            DELETE,
            RENAME,
        }

        /**
         * An enum containing [Command]'s known values, as well as an [_UNKNOWN] member.
         *
         * An instance of [Command] can contain an unknown value in a couple of cases:
         * - It was deserialized from data that doesn't match any known member. For example, if the
         *   SDK is on an older version than the API, then the API may respond with new members that
         *   the SDK is unaware of.
         * - It was constructed with an arbitrary value using the [of] method.
         */
        enum class Value {
            VIEW,
            CREATE,
            STR_REPLACE,
            INSERT,
            DELETE,
            RENAME,
            /** An enum member indicating that [Command] was instantiated with an unknown value. */
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
                VIEW -> Value.VIEW
                CREATE -> Value.CREATE
                STR_REPLACE -> Value.STR_REPLACE
                INSERT -> Value.INSERT
                DELETE -> Value.DELETE
                RENAME -> Value.RENAME
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
                VIEW -> Known.VIEW
                CREATE -> Known.CREATE
                STR_REPLACE -> Known.STR_REPLACE
                INSERT -> Known.INSERT
                DELETE -> Known.DELETE
                RENAME -> Known.RENAME
                else -> throw AnthropicInvalidDataException("Unknown Command: $value")
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
        fun validate(): Command = apply {
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

            return other is Command && value == other.value
        }

        override fun hashCode() = value.hashCode()

        override fun toString() = value.toString()
    }
}
