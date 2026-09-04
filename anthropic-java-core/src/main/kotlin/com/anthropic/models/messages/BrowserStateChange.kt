// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.messages

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

/**
 * A tab this call's execution opened that remains open at its end — the creation delta of the
 * `tabs` inventory, not an event log.
 *
 * Carries only the `tab_id`; the tab's `title` and `url` live on its `tabs` entry, which must
 * include the same `tab_id`. A tab opened during a failed call gets no deferred `tab_opened`; it
 * simply appears in the next result's `tabs` inventory.
 */
@JsonDeserialize(using = BrowserStateChange.Deserializer::class)
@JsonSerialize(using = BrowserStateChange.Serializer::class)
class BrowserStateChange
private constructor(
    private val tabOpened: BrowserStateChangeTabOpened? = null,
    private val downloadStarted: BrowserStateChangeDownloadStarted? = null,
    private val downloadCompleted: BrowserStateChangeDownloadCompleted? = null,
    private val downloadFailed: BrowserStateChangeDownloadFailed? = null,
    private val _json: JsonValue? = null,
) {

    fun type(): Type =
        accept(
            object : Visitor<Type> {
                override fun visitTabOpened(tabOpened: BrowserStateChangeTabOpened): Type =
                    Type.TAB_OPENED

                override fun visitDownloadStarted(
                    downloadStarted: BrowserStateChangeDownloadStarted
                ): Type = Type.DOWNLOAD_STARTED

                override fun visitDownloadCompleted(
                    downloadCompleted: BrowserStateChangeDownloadCompleted
                ): Type = Type.DOWNLOAD_COMPLETED

                override fun visitDownloadFailed(
                    downloadFailed: BrowserStateChangeDownloadFailed
                ): Type = Type.DOWNLOAD_FAILED

                override fun unknown(json: JsonValue?): Type =
                    Type.of(json?.asObject()?.getOrNull()?.get("type") ?: JsonMissing.of())
            }
        )

    fun downloadId(): Optional<String> =
        accept(
            object : Visitor<Optional<String>> {
                override fun visitTabOpened(
                    tabOpened: BrowserStateChangeTabOpened
                ): Optional<String> = Optional.empty()

                override fun visitDownloadStarted(
                    downloadStarted: BrowserStateChangeDownloadStarted
                ): Optional<String> = Optional.of(downloadStarted.downloadId())

                override fun visitDownloadCompleted(
                    downloadCompleted: BrowserStateChangeDownloadCompleted
                ): Optional<String> = Optional.of(downloadCompleted.downloadId())

                override fun visitDownloadFailed(
                    downloadFailed: BrowserStateChangeDownloadFailed
                ): Optional<String> = Optional.of(downloadFailed.downloadId())

                override fun unknown(json: JsonValue?): Optional<String> =
                    json.getProperty<String>("download_id").asKnown()
            }
        )

    fun url(): Optional<String> =
        accept(
            object : Visitor<Optional<String>> {
                override fun visitTabOpened(
                    tabOpened: BrowserStateChangeTabOpened
                ): Optional<String> = Optional.empty()

                override fun visitDownloadStarted(
                    downloadStarted: BrowserStateChangeDownloadStarted
                ): Optional<String> = Optional.of(downloadStarted.url())

                override fun visitDownloadCompleted(
                    downloadCompleted: BrowserStateChangeDownloadCompleted
                ): Optional<String> = Optional.of(downloadCompleted.url())

                override fun visitDownloadFailed(
                    downloadFailed: BrowserStateChangeDownloadFailed
                ): Optional<String> = Optional.of(downloadFailed.url())

                override fun unknown(json: JsonValue?): Optional<String> =
                    json.getProperty<String>("url").asKnown()
            }
        )

    /**
     * A tab this call's execution opened that remains open at its end — the creation delta of the
     * `tabs` inventory, not an event log.
     *
     * Carries only the `tab_id`; the tab's `title` and `url` live on its `tabs` entry, which must
     * include the same `tab_id`. A tab opened during a failed call gets no deferred `tab_opened`;
     * it simply appears in the next result's `tabs` inventory.
     */
    fun tabOpened(): Optional<BrowserStateChangeTabOpened> = Optional.ofNullable(tabOpened)

    /** A file download that started during this call. */
    fun downloadStarted(): Optional<BrowserStateChangeDownloadStarted> =
        Optional.ofNullable(downloadStarted)

    /**
     * A file download that finished during this call, reported with the same `download_id` as its
     * `download_started` — or without a prior `download_started`, when the download finished during
     * the call that started it (at most one state change per `download_id` per result).
     */
    fun downloadCompleted(): Optional<BrowserStateChangeDownloadCompleted> =
        Optional.ofNullable(downloadCompleted)

    /** A file download that failed — or was cancelled — during this call. */
    fun downloadFailed(): Optional<BrowserStateChangeDownloadFailed> =
        Optional.ofNullable(downloadFailed)

    fun isTabOpened(): Boolean = tabOpened != null

    fun isDownloadStarted(): Boolean = downloadStarted != null

    fun isDownloadCompleted(): Boolean = downloadCompleted != null

    fun isDownloadFailed(): Boolean = downloadFailed != null

    /**
     * A tab this call's execution opened that remains open at its end — the creation delta of the
     * `tabs` inventory, not an event log.
     *
     * Carries only the `tab_id`; the tab's `title` and `url` live on its `tabs` entry, which must
     * include the same `tab_id`. A tab opened during a failed call gets no deferred `tab_opened`;
     * it simply appears in the next result's `tabs` inventory.
     */
    fun asTabOpened(): BrowserStateChangeTabOpened = tabOpened.getOrThrow("tabOpened")

    /** A file download that started during this call. */
    fun asDownloadStarted(): BrowserStateChangeDownloadStarted =
        downloadStarted.getOrThrow("downloadStarted")

    /**
     * A file download that finished during this call, reported with the same `download_id` as its
     * `download_started` — or without a prior `download_started`, when the download finished during
     * the call that started it (at most one state change per `download_id` per result).
     */
    fun asDownloadCompleted(): BrowserStateChangeDownloadCompleted =
        downloadCompleted.getOrThrow("downloadCompleted")

    /** A file download that failed — or was cancelled — during this call. */
    fun asDownloadFailed(): BrowserStateChangeDownloadFailed =
        downloadFailed.getOrThrow("downloadFailed")

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
     * Optional<String> result = browserStateChange.accept(new BrowserStateChange.Visitor<Optional<String>>() {
     *     @Override
     *     public Optional<String> visitTabOpened(BrowserStateChangeTabOpened tabOpened) {
     *         return Optional.of(tabOpened.toString());
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
            tabOpened != null -> visitor.visitTabOpened(tabOpened)
            downloadStarted != null -> visitor.visitDownloadStarted(downloadStarted)
            downloadCompleted != null -> visitor.visitDownloadCompleted(downloadCompleted)
            downloadFailed != null -> visitor.visitDownloadFailed(downloadFailed)
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
    fun validate(): BrowserStateChange = apply {
        if (validated) {
            return@apply
        }

        accept(
            object : Visitor<Unit> {
                override fun visitTabOpened(tabOpened: BrowserStateChangeTabOpened) {
                    tabOpened.validate()
                }

                override fun visitDownloadStarted(
                    downloadStarted: BrowserStateChangeDownloadStarted
                ) {
                    downloadStarted.validate()
                }

                override fun visitDownloadCompleted(
                    downloadCompleted: BrowserStateChangeDownloadCompleted
                ) {
                    downloadCompleted.validate()
                }

                override fun visitDownloadFailed(downloadFailed: BrowserStateChangeDownloadFailed) {
                    downloadFailed.validate()
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
                override fun visitTabOpened(tabOpened: BrowserStateChangeTabOpened) =
                    tabOpened.validity()

                override fun visitDownloadStarted(
                    downloadStarted: BrowserStateChangeDownloadStarted
                ) = downloadStarted.validity()

                override fun visitDownloadCompleted(
                    downloadCompleted: BrowserStateChangeDownloadCompleted
                ) = downloadCompleted.validity()

                override fun visitDownloadFailed(downloadFailed: BrowserStateChangeDownloadFailed) =
                    downloadFailed.validity()

                override fun unknown(json: JsonValue?) = 0
            }
        )

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BrowserStateChange &&
            tabOpened == other.tabOpened &&
            downloadStarted == other.downloadStarted &&
            downloadCompleted == other.downloadCompleted &&
            downloadFailed == other.downloadFailed
    }

    override fun hashCode(): Int =
        Objects.hash(tabOpened, downloadStarted, downloadCompleted, downloadFailed)

    override fun toString(): String =
        when {
            tabOpened != null -> "BrowserStateChange{tabOpened=$tabOpened}"
            downloadStarted != null -> "BrowserStateChange{downloadStarted=$downloadStarted}"
            downloadCompleted != null -> "BrowserStateChange{downloadCompleted=$downloadCompleted}"
            downloadFailed != null -> "BrowserStateChange{downloadFailed=$downloadFailed}"
            _json != null -> "BrowserStateChange{_unknown=$_json}"
            else -> throw IllegalStateException("Invalid BrowserStateChange")
        }

    companion object {

        /**
         * A tab this call's execution opened that remains open at its end — the creation delta of
         * the `tabs` inventory, not an event log.
         *
         * Carries only the `tab_id`; the tab's `title` and `url` live on its `tabs` entry, which
         * must include the same `tab_id`. A tab opened during a failed call gets no deferred
         * `tab_opened`; it simply appears in the next result's `tabs` inventory.
         */
        @JvmStatic
        fun ofTabOpened(tabOpened: BrowserStateChangeTabOpened) =
            BrowserStateChange(tabOpened = tabOpened)

        /**
         * Returns an immutable instance of [BrowserStateChange] whose [ofTabOpened] variant is
         * built from the given required [tabId].
         */
        @JvmStatic
        fun ofTabOpened(tabId: String) = ofTabOpened(BrowserStateChangeTabOpened.of(tabId))

        /** A file download that started during this call. */
        @JvmStatic
        fun ofDownloadStarted(downloadStarted: BrowserStateChangeDownloadStarted) =
            BrowserStateChange(downloadStarted = downloadStarted)

        /**
         * A file download that finished during this call, reported with the same `download_id` as
         * its `download_started` — or without a prior `download_started`, when the download
         * finished during the call that started it (at most one state change per `download_id` per
         * result).
         */
        @JvmStatic
        fun ofDownloadCompleted(downloadCompleted: BrowserStateChangeDownloadCompleted) =
            BrowserStateChange(downloadCompleted = downloadCompleted)

        /** A file download that failed — or was cancelled — during this call. */
        @JvmStatic
        fun ofDownloadFailed(downloadFailed: BrowserStateChangeDownloadFailed) =
            BrowserStateChange(downloadFailed = downloadFailed)
    }

    /**
     * An interface that defines how to map each variant of [BrowserStateChange] to a value of type
     * [T].
     */
    interface Visitor<out T> {

        /**
         * A tab this call's execution opened that remains open at its end — the creation delta of
         * the `tabs` inventory, not an event log.
         *
         * Carries only the `tab_id`; the tab's `title` and `url` live on its `tabs` entry, which
         * must include the same `tab_id`. A tab opened during a failed call gets no deferred
         * `tab_opened`; it simply appears in the next result's `tabs` inventory.
         */
        fun visitTabOpened(tabOpened: BrowserStateChangeTabOpened): T

        /** A file download that started during this call. */
        fun visitDownloadStarted(downloadStarted: BrowserStateChangeDownloadStarted): T

        /**
         * A file download that finished during this call, reported with the same `download_id` as
         * its `download_started` — or without a prior `download_started`, when the download
         * finished during the call that started it (at most one state change per `download_id` per
         * result).
         */
        fun visitDownloadCompleted(downloadCompleted: BrowserStateChangeDownloadCompleted): T

        /** A file download that failed — or was cancelled — during this call. */
        fun visitDownloadFailed(downloadFailed: BrowserStateChangeDownloadFailed): T

        /**
         * Maps an unknown variant of [BrowserStateChange] to a value of type [T].
         *
         * An instance of [BrowserStateChange] can contain an unknown variant if it was deserialized
         * from data that doesn't match any known variant. For example, if the SDK is on an older
         * version than the API, then the API may respond with new variants that the SDK is unaware
         * of.
         *
         * @throws AnthropicInvalidDataException in the default implementation.
         */
        fun unknown(json: JsonValue?): T {
            throw AnthropicInvalidDataException("Unknown BrowserStateChange: $json")
        }
    }

    internal class Deserializer : BaseDeserializer<BrowserStateChange>(BrowserStateChange::class) {

        override fun ObjectCodec.deserialize(node: JsonNode): BrowserStateChange {
            val json = JsonValue.fromJsonNode(node)
            val type = json.asObject().getOrNull()?.get("type")?.asString()?.getOrNull()

            when (type) {
                "tab_opened" -> {
                    return tryDeserialize(node, jacksonTypeRef<BrowserStateChangeTabOpened>())
                        ?.let { BrowserStateChange(tabOpened = it, _json = json) }
                        ?: BrowserStateChange(_json = json)
                }
                "download_started" -> {
                    return tryDeserialize(node, jacksonTypeRef<BrowserStateChangeDownloadStarted>())
                        ?.let { BrowserStateChange(downloadStarted = it, _json = json) }
                        ?: BrowserStateChange(_json = json)
                }
                "download_completed" -> {
                    return tryDeserialize(
                            node,
                            jacksonTypeRef<BrowserStateChangeDownloadCompleted>(),
                        )
                        ?.let { BrowserStateChange(downloadCompleted = it, _json = json) }
                        ?: BrowserStateChange(_json = json)
                }
                "download_failed" -> {
                    return tryDeserialize(node, jacksonTypeRef<BrowserStateChangeDownloadFailed>())
                        ?.let { BrowserStateChange(downloadFailed = it, _json = json) }
                        ?: BrowserStateChange(_json = json)
                }
            }

            return BrowserStateChange(_json = json)
        }
    }

    internal class Serializer : BaseSerializer<BrowserStateChange>(BrowserStateChange::class) {

        override fun serialize(
            value: BrowserStateChange,
            generator: JsonGenerator,
            provider: SerializerProvider,
        ) {
            when {
                value.tabOpened != null -> generator.writeObject(value.tabOpened)
                value.downloadStarted != null -> generator.writeObject(value.downloadStarted)
                value.downloadCompleted != null -> generator.writeObject(value.downloadCompleted)
                value.downloadFailed != null -> generator.writeObject(value.downloadFailed)
                value._json != null -> generator.writeObject(value._json)
                else -> throw IllegalStateException("Invalid BrowserStateChange")
            }
        }
    }

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

            @JvmField val TAB_OPENED = of("tab_opened")

            @JvmField val DOWNLOAD_STARTED = of("download_started")

            @JvmField val DOWNLOAD_COMPLETED = of("download_completed")

            @JvmField val DOWNLOAD_FAILED = of("download_failed")

            @JvmStatic fun of(value: String) = Type(JsonField.of(value))

            @JvmSynthetic
            internal fun of(value: JsonField<String>): Type =
                value.asString().getOrNull()?.let { of(it) } ?: Type(value)
        }

        /** An enum containing [Type]'s known values. */
        enum class Known {
            TAB_OPENED,
            DOWNLOAD_STARTED,
            DOWNLOAD_COMPLETED,
            DOWNLOAD_FAILED,
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
            TAB_OPENED,
            DOWNLOAD_STARTED,
            DOWNLOAD_COMPLETED,
            DOWNLOAD_FAILED,
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
                TAB_OPENED -> Value.TAB_OPENED
                DOWNLOAD_STARTED -> Value.DOWNLOAD_STARTED
                DOWNLOAD_COMPLETED -> Value.DOWNLOAD_COMPLETED
                DOWNLOAD_FAILED -> Value.DOWNLOAD_FAILED
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
                TAB_OPENED -> Known.TAB_OPENED
                DOWNLOAD_STARTED -> Known.DOWNLOAD_STARTED
                DOWNLOAD_COMPLETED -> Known.DOWNLOAD_COMPLETED
                DOWNLOAD_FAILED -> Known.DOWNLOAD_FAILED
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
}
