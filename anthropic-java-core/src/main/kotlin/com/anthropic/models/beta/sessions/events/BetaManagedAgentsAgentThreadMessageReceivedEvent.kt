// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.sessions.events

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
import java.time.OffsetDateTime
import java.util.Collections
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/**
 * Delivery event written to the target thread's input stream when an agent-to-agent message
 * arrives.
 */
class BetaManagedAgentsAgentThreadMessageReceivedEvent
@JsonCreator(mode = JsonCreator.Mode.DISABLED)
private constructor(
    private val id: JsonField<String>,
    private val content: JsonField<List<Content>>,
    private val fromSessionThreadId: JsonField<String>,
    private val processedAt: JsonField<OffsetDateTime>,
    private val type: JsonField<Type>,
    private val fromAgentName: JsonField<String>,
    private val additionalProperties: MutableMap<String, JsonValue>,
) {

    @JsonCreator
    private constructor(
        @JsonProperty("id") @ExcludeMissing id: JsonField<String> = JsonMissing.of(),
        @JsonProperty("content")
        @ExcludeMissing
        content: JsonField<List<Content>> = JsonMissing.of(),
        @JsonProperty("from_session_thread_id")
        @ExcludeMissing
        fromSessionThreadId: JsonField<String> = JsonMissing.of(),
        @JsonProperty("processed_at")
        @ExcludeMissing
        processedAt: JsonField<OffsetDateTime> = JsonMissing.of(),
        @JsonProperty("type") @ExcludeMissing type: JsonField<Type> = JsonMissing.of(),
        @JsonProperty("from_agent_name")
        @ExcludeMissing
        fromAgentName: JsonField<String> = JsonMissing.of(),
    ) : this(id, content, fromSessionThreadId, processedAt, type, fromAgentName, mutableMapOf())

    /**
     * Unique identifier for this event.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun id(): String = id.getRequired("id")

    /**
     * Message content blocks.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun content(): List<Content> = content.getRequired("content")

    /**
     * Public `sthr_` ID of the thread that sent the message.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun fromSessionThreadId(): String = fromSessionThreadId.getRequired("from_session_thread_id")

    /**
     * A timestamp in RFC 3339 format
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun processedAt(): OffsetDateTime = processedAt.getRequired("processed_at")

    /**
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun type(): Type = type.getRequired("type")

    /**
     * Name of the callable agent this message came from. Absent when received from the primary
     * agent.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun fromAgentName(): Optional<String> = fromAgentName.getOptional("from_agent_name")

    /**
     * Returns the raw JSON value of [id].
     *
     * Unlike [id], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("id") @ExcludeMissing fun _id(): JsonField<String> = id

    /**
     * Returns the raw JSON value of [content].
     *
     * Unlike [content], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("content") @ExcludeMissing fun _content(): JsonField<List<Content>> = content

    /**
     * Returns the raw JSON value of [fromSessionThreadId].
     *
     * Unlike [fromSessionThreadId], this method doesn't throw if the JSON field has an unexpected
     * type.
     */
    @JsonProperty("from_session_thread_id")
    @ExcludeMissing
    fun _fromSessionThreadId(): JsonField<String> = fromSessionThreadId

    /**
     * Returns the raw JSON value of [processedAt].
     *
     * Unlike [processedAt], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("processed_at")
    @ExcludeMissing
    fun _processedAt(): JsonField<OffsetDateTime> = processedAt

    /**
     * Returns the raw JSON value of [type].
     *
     * Unlike [type], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("type") @ExcludeMissing fun _type(): JsonField<Type> = type

    /**
     * Returns the raw JSON value of [fromAgentName].
     *
     * Unlike [fromAgentName], this method doesn't throw if the JSON field has an unexpected type.
     */
    @JsonProperty("from_agent_name")
    @ExcludeMissing
    fun _fromAgentName(): JsonField<String> = fromAgentName

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
         * [BetaManagedAgentsAgentThreadMessageReceivedEvent].
         *
         * The following fields are required:
         * ```java
         * .id()
         * .content()
         * .fromSessionThreadId()
         * .processedAt()
         * .type()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [BetaManagedAgentsAgentThreadMessageReceivedEvent]. */
    class Builder internal constructor() {

        private var id: JsonField<String>? = null
        private var content: JsonField<MutableList<Content>>? = null
        private var fromSessionThreadId: JsonField<String>? = null
        private var processedAt: JsonField<OffsetDateTime>? = null
        private var type: JsonField<Type>? = null
        private var fromAgentName: JsonField<String> = JsonMissing.of()
        private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

        @JvmSynthetic
        internal fun from(
            betaManagedAgentsAgentThreadMessageReceivedEvent:
                BetaManagedAgentsAgentThreadMessageReceivedEvent
        ) = apply {
            id = betaManagedAgentsAgentThreadMessageReceivedEvent.id
            content =
                betaManagedAgentsAgentThreadMessageReceivedEvent.content.map { it.toMutableList() }
            fromSessionThreadId =
                betaManagedAgentsAgentThreadMessageReceivedEvent.fromSessionThreadId
            processedAt = betaManagedAgentsAgentThreadMessageReceivedEvent.processedAt
            type = betaManagedAgentsAgentThreadMessageReceivedEvent.type
            fromAgentName = betaManagedAgentsAgentThreadMessageReceivedEvent.fromAgentName
            additionalProperties =
                betaManagedAgentsAgentThreadMessageReceivedEvent.additionalProperties.toMutableMap()
        }

        /** Unique identifier for this event. */
        fun id(id: String) = id(JsonField.of(id))

        /**
         * Sets [Builder.id] to an arbitrary JSON value.
         *
         * You should usually call [Builder.id] with a well-typed [String] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun id(id: JsonField<String>) = apply { this.id = id }

        /** Message content blocks. */
        fun content(content: List<Content>) = content(JsonField.of(content))

        /**
         * Sets [Builder.content] to an arbitrary JSON value.
         *
         * You should usually call [Builder.content] with a well-typed `List<Content>` value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun content(content: JsonField<List<Content>>) = apply {
            this.content = content.map { it.toMutableList() }
        }

        /**
         * Adds a single [Content] to [Builder.content].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addContent(content: Content) = apply {
            this.content =
                (this.content ?: JsonField.of(mutableListOf())).also {
                    checkKnown("content", it).add(content)
                }
        }

        /** Alias for calling [addContent] with `Content.ofText(text)`. */
        fun addContent(text: BetaManagedAgentsTextBlock) = addContent(Content.ofText(text))

        /**
         * Alias for calling [addContent] with the following:
         * ```java
         * BetaManagedAgentsTextBlock.builder()
         *     .type(BetaManagedAgentsTextBlock.Type.TEXT)
         *     .text(text)
         *     .build()
         * ```
         */
        fun addTextContent(text: String) =
            addContent(
                BetaManagedAgentsTextBlock.builder()
                    .type(BetaManagedAgentsTextBlock.Type.TEXT)
                    .text(text)
                    .build()
            )

        /** Alias for calling [addContent] with `Content.ofImage(image)`. */
        fun addContent(image: BetaManagedAgentsImageBlock) = addContent(Content.ofImage(image))

        /**
         * Alias for calling [addContent] with the following:
         * ```java
         * BetaManagedAgentsImageBlock.builder()
         *     .type(BetaManagedAgentsImageBlock.Type.IMAGE)
         *     .source(source)
         *     .build()
         * ```
         */
        fun addImageContent(source: BetaManagedAgentsImageBlock.Source) =
            addContent(
                BetaManagedAgentsImageBlock.builder()
                    .type(BetaManagedAgentsImageBlock.Type.IMAGE)
                    .source(source)
                    .build()
            )

        /**
         * Alias for calling [addImageContent] with
         * `BetaManagedAgentsImageBlock.Source.ofBase64(base64)`.
         */
        fun addImageContent(base64: BetaManagedAgentsBase64ImageSource) =
            addImageContent(BetaManagedAgentsImageBlock.Source.ofBase64(base64))

        /**
         * Alias for calling [addImageContent] with `BetaManagedAgentsImageBlock.Source.ofUrl(url)`.
         */
        fun addImageContent(url: BetaManagedAgentsUrlImageSource) =
            addImageContent(BetaManagedAgentsImageBlock.Source.ofUrl(url))

        /**
         * Alias for calling [addImageContent] with the following:
         * ```java
         * BetaManagedAgentsUrlImageSource.builder()
         *     .type(BetaManagedAgentsUrlImageSource.Type.URL)
         *     .url(url)
         *     .build()
         * ```
         */
        fun addUrlImageContent(url: String) =
            addImageContent(
                BetaManagedAgentsUrlImageSource.builder()
                    .type(BetaManagedAgentsUrlImageSource.Type.URL)
                    .url(url)
                    .build()
            )

        /**
         * Alias for calling [addImageContent] with
         * `BetaManagedAgentsImageBlock.Source.ofFile(file)`.
         */
        fun addImageContent(file: BetaManagedAgentsFileImageSource) =
            addImageContent(BetaManagedAgentsImageBlock.Source.ofFile(file))

        /**
         * Alias for calling [addImageContent] with the following:
         * ```java
         * BetaManagedAgentsFileImageSource.builder()
         *     .type(BetaManagedAgentsFileImageSource.Type.FILE)
         *     .fileId(fileId)
         *     .build()
         * ```
         */
        fun addFileImageContent(fileId: String) =
            addImageContent(
                BetaManagedAgentsFileImageSource.builder()
                    .type(BetaManagedAgentsFileImageSource.Type.FILE)
                    .fileId(fileId)
                    .build()
            )

        /** Alias for calling [addContent] with `Content.ofDocument(document)`. */
        fun addContent(document: BetaManagedAgentsDocumentBlock) =
            addContent(Content.ofDocument(document))

        /**
         * Alias for calling [addContent] with the following:
         * ```java
         * BetaManagedAgentsDocumentBlock.builder()
         *     .type(BetaManagedAgentsDocumentBlock.Type.DOCUMENT)
         *     .source(source)
         *     .build()
         * ```
         */
        fun addDocumentContent(source: BetaManagedAgentsDocumentBlock.Source) =
            addContent(
                BetaManagedAgentsDocumentBlock.builder()
                    .type(BetaManagedAgentsDocumentBlock.Type.DOCUMENT)
                    .source(source)
                    .build()
            )

        /**
         * Alias for calling [addDocumentContent] with
         * `BetaManagedAgentsDocumentBlock.Source.ofBase64(base64)`.
         */
        fun addDocumentContent(base64: BetaManagedAgentsBase64DocumentSource) =
            addDocumentContent(BetaManagedAgentsDocumentBlock.Source.ofBase64(base64))

        /**
         * Alias for calling [addDocumentContent] with
         * `BetaManagedAgentsDocumentBlock.Source.ofText(text)`.
         */
        fun addDocumentContent(text: BetaManagedAgentsPlainTextDocumentSource) =
            addDocumentContent(BetaManagedAgentsDocumentBlock.Source.ofText(text))

        /**
         * Alias for calling [addDocumentContent] with
         * `BetaManagedAgentsDocumentBlock.Source.ofUrl(url)`.
         */
        fun addDocumentContent(url: BetaManagedAgentsUrlDocumentSource) =
            addDocumentContent(BetaManagedAgentsDocumentBlock.Source.ofUrl(url))

        /**
         * Alias for calling [addDocumentContent] with the following:
         * ```java
         * BetaManagedAgentsUrlDocumentSource.builder()
         *     .type(BetaManagedAgentsUrlDocumentSource.Type.URL)
         *     .url(url)
         *     .build()
         * ```
         */
        fun addUrlDocumentContent(url: String) =
            addDocumentContent(
                BetaManagedAgentsUrlDocumentSource.builder()
                    .type(BetaManagedAgentsUrlDocumentSource.Type.URL)
                    .url(url)
                    .build()
            )

        /**
         * Alias for calling [addDocumentContent] with
         * `BetaManagedAgentsDocumentBlock.Source.ofFile(file)`.
         */
        fun addDocumentContent(file: BetaManagedAgentsFileDocumentSource) =
            addDocumentContent(BetaManagedAgentsDocumentBlock.Source.ofFile(file))

        /**
         * Alias for calling [addDocumentContent] with the following:
         * ```java
         * BetaManagedAgentsFileDocumentSource.builder()
         *     .type(BetaManagedAgentsFileDocumentSource.Type.FILE)
         *     .fileId(fileId)
         *     .build()
         * ```
         */
        fun addFileDocumentContent(fileId: String) =
            addDocumentContent(
                BetaManagedAgentsFileDocumentSource.builder()
                    .type(BetaManagedAgentsFileDocumentSource.Type.FILE)
                    .fileId(fileId)
                    .build()
            )

        /** Public `sthr_` ID of the thread that sent the message. */
        fun fromSessionThreadId(fromSessionThreadId: String) =
            fromSessionThreadId(JsonField.of(fromSessionThreadId))

        /**
         * Sets [Builder.fromSessionThreadId] to an arbitrary JSON value.
         *
         * You should usually call [Builder.fromSessionThreadId] with a well-typed [String] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun fromSessionThreadId(fromSessionThreadId: JsonField<String>) = apply {
            this.fromSessionThreadId = fromSessionThreadId
        }

        /** A timestamp in RFC 3339 format */
        fun processedAt(processedAt: OffsetDateTime) = processedAt(JsonField.of(processedAt))

        /**
         * Sets [Builder.processedAt] to an arbitrary JSON value.
         *
         * You should usually call [Builder.processedAt] with a well-typed [OffsetDateTime] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun processedAt(processedAt: JsonField<OffsetDateTime>) = apply {
            this.processedAt = processedAt
        }

        fun type(type: Type) = type(JsonField.of(type))

        /**
         * Sets [Builder.type] to an arbitrary JSON value.
         *
         * You should usually call [Builder.type] with a well-typed [Type] value instead. This
         * method is primarily for setting the field to an undocumented or not yet supported value.
         */
        fun type(type: JsonField<Type>) = apply { this.type = type }

        /**
         * Name of the callable agent this message came from. Absent when received from the primary
         * agent.
         */
        fun fromAgentName(fromAgentName: String?) =
            fromAgentName(JsonField.ofNullable(fromAgentName))

        /** Alias for calling [Builder.fromAgentName] with `fromAgentName.orElse(null)`. */
        fun fromAgentName(fromAgentName: Optional<String>) =
            fromAgentName(fromAgentName.getOrNull())

        /**
         * Sets [Builder.fromAgentName] to an arbitrary JSON value.
         *
         * You should usually call [Builder.fromAgentName] with a well-typed [String] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun fromAgentName(fromAgentName: JsonField<String>) = apply {
            this.fromAgentName = fromAgentName
        }

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
         * Returns an immutable instance of [BetaManagedAgentsAgentThreadMessageReceivedEvent].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .id()
         * .content()
         * .fromSessionThreadId()
         * .processedAt()
         * .type()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): BetaManagedAgentsAgentThreadMessageReceivedEvent =
            BetaManagedAgentsAgentThreadMessageReceivedEvent(
                checkRequired("id", id),
                checkRequired("content", content).map { it.toImmutable() },
                checkRequired("fromSessionThreadId", fromSessionThreadId),
                checkRequired("processedAt", processedAt),
                checkRequired("type", type),
                fromAgentName,
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
    fun validate(): BetaManagedAgentsAgentThreadMessageReceivedEvent = apply {
        if (validated) {
            return@apply
        }

        id()
        content().forEach { it.validate() }
        fromSessionThreadId()
        processedAt()
        type().validate()
        fromAgentName()
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
            (content.asKnown().getOrNull()?.sumOf { it.validity().toInt() } ?: 0) +
            (if (fromSessionThreadId.asKnown().isPresent) 1 else 0) +
            (if (processedAt.asKnown().isPresent) 1 else 0) +
            (type.asKnown().getOrNull()?.validity() ?: 0) +
            (if (fromAgentName.asKnown().isPresent) 1 else 0)

    /** Content block in a user message. Can be `text`, `image`, or `document`. */
    @JsonDeserialize(using = Content.Deserializer::class)
    @JsonSerialize(using = Content.Serializer::class)
    class Content
    private constructor(
        private val text: BetaManagedAgentsTextBlock? = null,
        private val image: BetaManagedAgentsImageBlock? = null,
        private val document: BetaManagedAgentsDocumentBlock? = null,
        private val _json: JsonValue? = null,
    ) {

        /** Regular text content. */
        fun text(): Optional<BetaManagedAgentsTextBlock> = Optional.ofNullable(text)

        /** Image content specified directly as base64 data or as a reference via a URL. */
        fun image(): Optional<BetaManagedAgentsImageBlock> = Optional.ofNullable(image)

        /**
         * Document content, either specified directly as base64 data, as text, or as a reference
         * via a URL.
         */
        fun document(): Optional<BetaManagedAgentsDocumentBlock> = Optional.ofNullable(document)

        fun isText(): Boolean = text != null

        fun isImage(): Boolean = image != null

        fun isDocument(): Boolean = document != null

        /** Regular text content. */
        fun asText(): BetaManagedAgentsTextBlock = text.getOrThrow("text")

        /** Image content specified directly as base64 data or as a reference via a URL. */
        fun asImage(): BetaManagedAgentsImageBlock = image.getOrThrow("image")

        /**
         * Document content, either specified directly as base64 data, as text, or as a reference
         * via a URL.
         */
        fun asDocument(): BetaManagedAgentsDocumentBlock = document.getOrThrow("document")

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
         * Optional<String> result = content.accept(new Content.Visitor<Optional<String>>() {
         *     @Override
         *     public Optional<String> visitText(BetaManagedAgentsTextBlock text) {
         *         return Optional.of(text.toString());
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
                text != null -> visitor.visitText(text)
                image != null -> visitor.visitImage(image)
                document != null -> visitor.visitDocument(document)
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
        fun validate(): Content = apply {
            if (validated) {
                return@apply
            }

            accept(
                object : Visitor<Unit> {
                    override fun visitText(text: BetaManagedAgentsTextBlock) {
                        text.validate()
                    }

                    override fun visitImage(image: BetaManagedAgentsImageBlock) {
                        image.validate()
                    }

                    override fun visitDocument(document: BetaManagedAgentsDocumentBlock) {
                        document.validate()
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
                    override fun visitText(text: BetaManagedAgentsTextBlock) = text.validity()

                    override fun visitImage(image: BetaManagedAgentsImageBlock) = image.validity()

                    override fun visitDocument(document: BetaManagedAgentsDocumentBlock) =
                        document.validity()

                    override fun unknown(json: JsonValue?) = 0
                }
            )

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Content &&
                text == other.text &&
                image == other.image &&
                document == other.document
        }

        override fun hashCode(): Int = Objects.hash(text, image, document)

        override fun toString(): String =
            when {
                text != null -> "Content{text=$text}"
                image != null -> "Content{image=$image}"
                document != null -> "Content{document=$document}"
                _json != null -> "Content{_unknown=$_json}"
                else -> throw IllegalStateException("Invalid Content")
            }

        companion object {

            /** Regular text content. */
            @JvmStatic fun ofText(text: BetaManagedAgentsTextBlock) = Content(text = text)

            /** Image content specified directly as base64 data or as a reference via a URL. */
            @JvmStatic fun ofImage(image: BetaManagedAgentsImageBlock) = Content(image = image)

            /**
             * Document content, either specified directly as base64 data, as text, or as a
             * reference via a URL.
             */
            @JvmStatic
            fun ofDocument(document: BetaManagedAgentsDocumentBlock) = Content(document = document)
        }

        /**
         * An interface that defines how to map each variant of [Content] to a value of type [T].
         */
        interface Visitor<out T> {

            /** Regular text content. */
            fun visitText(text: BetaManagedAgentsTextBlock): T

            /** Image content specified directly as base64 data or as a reference via a URL. */
            fun visitImage(image: BetaManagedAgentsImageBlock): T

            /**
             * Document content, either specified directly as base64 data, as text, or as a
             * reference via a URL.
             */
            fun visitDocument(document: BetaManagedAgentsDocumentBlock): T

            /**
             * Maps an unknown variant of [Content] to a value of type [T].
             *
             * An instance of [Content] can contain an unknown variant if it was deserialized from
             * data that doesn't match any known variant. For example, if the SDK is on an older
             * version than the API, then the API may respond with new variants that the SDK is
             * unaware of.
             *
             * @throws AnthropicInvalidDataException in the default implementation.
             */
            fun unknown(json: JsonValue?): T {
                throw AnthropicInvalidDataException("Unknown Content: $json")
            }
        }

        internal class Deserializer : BaseDeserializer<Content>(Content::class) {

            override fun ObjectCodec.deserialize(node: JsonNode): Content {
                val json = JsonValue.fromJsonNode(node)
                val type = json.asObject().getOrNull()?.get("type")?.asString()?.getOrNull()

                when (type) {
                    "text" -> {
                        return tryDeserialize(node, jacksonTypeRef<BetaManagedAgentsTextBlock>())
                            ?.let { Content(text = it, _json = json) } ?: Content(_json = json)
                    }
                    "image" -> {
                        return tryDeserialize(node, jacksonTypeRef<BetaManagedAgentsImageBlock>())
                            ?.let { Content(image = it, _json = json) } ?: Content(_json = json)
                    }
                    "document" -> {
                        return tryDeserialize(
                                node,
                                jacksonTypeRef<BetaManagedAgentsDocumentBlock>(),
                            )
                            ?.let { Content(document = it, _json = json) } ?: Content(_json = json)
                    }
                }

                return Content(_json = json)
            }
        }

        internal class Serializer : BaseSerializer<Content>(Content::class) {

            override fun serialize(
                value: Content,
                generator: JsonGenerator,
                provider: SerializerProvider,
            ) {
                when {
                    value.text != null -> generator.writeObject(value.text)
                    value.image != null -> generator.writeObject(value.image)
                    value.document != null -> generator.writeObject(value.document)
                    value._json != null -> generator.writeObject(value._json)
                    else -> throw IllegalStateException("Invalid Content")
                }
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

            @JvmField val AGENT_THREAD_MESSAGE_RECEIVED = of("agent.thread_message_received")

            @JvmStatic fun of(value: String) = Type(JsonField.of(value))
        }

        /** An enum containing [Type]'s known values. */
        enum class Known {
            AGENT_THREAD_MESSAGE_RECEIVED
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
            AGENT_THREAD_MESSAGE_RECEIVED,
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
                AGENT_THREAD_MESSAGE_RECEIVED -> Value.AGENT_THREAD_MESSAGE_RECEIVED
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
                AGENT_THREAD_MESSAGE_RECEIVED -> Known.AGENT_THREAD_MESSAGE_RECEIVED
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

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is BetaManagedAgentsAgentThreadMessageReceivedEvent &&
            id == other.id &&
            content == other.content &&
            fromSessionThreadId == other.fromSessionThreadId &&
            processedAt == other.processedAt &&
            type == other.type &&
            fromAgentName == other.fromAgentName &&
            additionalProperties == other.additionalProperties
    }

    private val hashCode: Int by lazy {
        Objects.hash(
            id,
            content,
            fromSessionThreadId,
            processedAt,
            type,
            fromAgentName,
            additionalProperties,
        )
    }

    override fun hashCode(): Int = hashCode

    override fun toString() =
        "BetaManagedAgentsAgentThreadMessageReceivedEvent{id=$id, content=$content, fromSessionThreadId=$fromSessionThreadId, processedAt=$processedAt, type=$type, fromAgentName=$fromAgentName, additionalProperties=$additionalProperties}"
}
