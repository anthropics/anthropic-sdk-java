// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.files

import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.MultipartField
import com.anthropic.core.Params
import com.anthropic.core.checkRequired
import com.anthropic.core.http.Headers
import com.anthropic.core.http.QueryParams
import com.anthropic.core.toImmutable
import com.anthropic.errors.AnthropicInvalidDataException
import com.anthropic.models.beta.AnthropicBeta
import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonProperty
import java.io.InputStream
import java.nio.file.Path
import java.util.Collections
import java.util.Objects
import java.util.Optional
import kotlin.io.path.inputStream
import kotlin.io.path.name
import kotlin.jvm.optionals.getOrNull

/** Upload File */
class FileUploadParams
private constructor(
    private val betas: List<AnthropicBeta>?,
    private val workspaceId: String?,
    private val body: Body,
    private val additionalHeaders: Headers,
    private val additionalQueryParams: QueryParams,
) : Params {

    /** Optional header to specify the beta version(s) you want to use. */
    fun betas(): Optional<List<AnthropicBeta>> = Optional.ofNullable(betas)

    fun workspaceId(): Optional<String> = Optional.ofNullable(workspaceId)

    /**
     * The file to upload. Only the final path component of the part's `filename` is kept; an absent
     * or empty `filename` is replaced with `unnamed` plus the extension for the file's stored
     * `mime_type`, when known.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun file(): InputStream = body.file()

    /**
     * Seconds from upload until the file expires and its bytes become permanently unavailable. Must
     * be between 3600 (one hour) and 7776000 (ninety days).
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun expiresInSeconds(): Optional<Long> = body.expiresInSeconds()

    /**
     * Returns the raw multipart value of [file].
     *
     * Unlike [file], this method doesn't throw if the multipart field has an unexpected type.
     */
    fun _file(): MultipartField<InputStream> = body._file()

    /**
     * Returns the raw multipart value of [expiresInSeconds].
     *
     * Unlike [expiresInSeconds], this method doesn't throw if the multipart field has an unexpected
     * type.
     */
    fun _expiresInSeconds(): MultipartField<Long> = body._expiresInSeconds()

    fun _additionalBodyProperties(): Map<String, JsonValue> = body._additionalProperties()

    /** Additional headers to send with the request. */
    fun _additionalHeaders(): Headers = additionalHeaders

    /** Additional query param to send with the request. */
    fun _additionalQueryParams(): QueryParams = additionalQueryParams

    fun toBuilder() = Builder().from(this)

    companion object {

        /**
         * Returns a mutable builder for constructing an instance of [FileUploadParams].
         *
         * The following fields are required:
         * ```java
         * .file()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [FileUploadParams]. */
    class Builder internal constructor() {

        private var betas: MutableList<AnthropicBeta>? = null
        private var workspaceId: String? = null
        private var body: Body.Builder = Body.builder()
        private var additionalHeaders: Headers.Builder = Headers.builder()
        private var additionalQueryParams: QueryParams.Builder = QueryParams.builder()

        @JvmSynthetic
        internal fun from(fileUploadParams: FileUploadParams) = apply {
            betas = fileUploadParams.betas?.toMutableList()
            workspaceId = fileUploadParams.workspaceId
            body = fileUploadParams.body.toBuilder()
            additionalHeaders = fileUploadParams.additionalHeaders.toBuilder()
            additionalQueryParams = fileUploadParams.additionalQueryParams.toBuilder()
        }

        /** Optional header to specify the beta version(s) you want to use. */
        fun betas(betas: List<AnthropicBeta>?) = apply { this.betas = betas?.toMutableList() }

        /** Alias for calling [Builder.betas] with `betas.orElse(null)`. */
        fun betas(betas: Optional<List<AnthropicBeta>>) = betas(betas.getOrNull())

        /**
         * Adds a single [AnthropicBeta] to [betas].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addBeta(beta: AnthropicBeta) = apply {
            betas = (betas ?: mutableListOf()).apply { add(beta) }
        }

        /**
         * Sets [addBeta] to an arbitrary [String].
         *
         * You should usually call [addBeta] with a well-typed [AnthropicBeta] constant instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun addBeta(value: String) = addBeta(AnthropicBeta.of(value))

        fun workspaceId(workspaceId: String?) = apply { this.workspaceId = workspaceId }

        /** Alias for calling [Builder.workspaceId] with `workspaceId.orElse(null)`. */
        fun workspaceId(workspaceId: Optional<String>) = workspaceId(workspaceId.getOrNull())

        /**
         * Sets the entire request body.
         *
         * This is generally only useful if you are already constructing the body separately.
         * Otherwise, it's more convenient to use the top-level setters instead:
         * - [file]
         * - [expiresInSeconds]
         */
        fun body(body: Body) = apply { this.body = body.toBuilder() }

        /**
         * The file to upload. Only the final path component of the part's `filename` is kept; an
         * absent or empty `filename` is replaced with `unnamed` plus the extension for the file's
         * stored `mime_type`, when known.
         */
        fun file(file: InputStream) = apply { body.file(file) }

        /**
         * Sets [Builder.file] to an arbitrary multipart value.
         *
         * You should usually call [Builder.file] with a well-typed [InputStream] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun file(file: MultipartField<InputStream>) = apply { body.file(file) }

        /**
         * The file to upload. Only the final path component of the part's `filename` is kept; an
         * absent or empty `filename` is replaced with `unnamed` plus the extension for the file's
         * stored `mime_type`, when known.
         */
        fun file(file: ByteArray) = apply { body.file(file) }

        /**
         * The file to upload. Only the final path component of the part's `filename` is kept; an
         * absent or empty `filename` is replaced with `unnamed` plus the extension for the file's
         * stored `mime_type`, when known.
         */
        fun file(path: Path) = apply { body.file(path) }

        /**
         * Seconds from upload until the file expires and its bytes become permanently unavailable.
         * Must be between 3600 (one hour) and 7776000 (ninety days).
         */
        fun expiresInSeconds(expiresInSeconds: Long) = apply {
            body.expiresInSeconds(expiresInSeconds)
        }

        /**
         * Sets [Builder.expiresInSeconds] to an arbitrary multipart value.
         *
         * You should usually call [Builder.expiresInSeconds] with a well-typed [Long] value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun expiresInSeconds(expiresInSeconds: MultipartField<Long>) = apply {
            body.expiresInSeconds(expiresInSeconds)
        }

        fun additionalBodyProperties(additionalBodyProperties: Map<String, JsonValue>) = apply {
            body.additionalProperties(additionalBodyProperties)
        }

        fun putAdditionalBodyProperty(key: String, value: JsonValue) = apply {
            body.putAdditionalProperty(key, value)
        }

        fun putAllAdditionalBodyProperties(additionalBodyProperties: Map<String, JsonValue>) =
            apply {
                body.putAllAdditionalProperties(additionalBodyProperties)
            }

        fun removeAdditionalBodyProperty(key: String) = apply { body.removeAdditionalProperty(key) }

        fun removeAllAdditionalBodyProperties(keys: Set<String>) = apply {
            body.removeAllAdditionalProperties(keys)
        }

        fun additionalHeaders(additionalHeaders: Headers) = apply {
            this.additionalHeaders.clear()
            putAllAdditionalHeaders(additionalHeaders)
        }

        fun additionalHeaders(additionalHeaders: Map<String, Iterable<String>>) = apply {
            this.additionalHeaders.clear()
            putAllAdditionalHeaders(additionalHeaders)
        }

        fun putAdditionalHeader(name: String, value: String) = apply {
            additionalHeaders.put(name, value)
        }

        fun putAdditionalHeaders(name: String, values: Iterable<String>) = apply {
            additionalHeaders.put(name, values)
        }

        fun putAllAdditionalHeaders(additionalHeaders: Headers) = apply {
            this.additionalHeaders.putAll(additionalHeaders)
        }

        fun putAllAdditionalHeaders(additionalHeaders: Map<String, Iterable<String>>) = apply {
            this.additionalHeaders.putAll(additionalHeaders)
        }

        fun replaceAdditionalHeaders(name: String, value: String) = apply {
            additionalHeaders.replace(name, value)
        }

        fun replaceAdditionalHeaders(name: String, values: Iterable<String>) = apply {
            additionalHeaders.replace(name, values)
        }

        fun replaceAllAdditionalHeaders(additionalHeaders: Headers) = apply {
            this.additionalHeaders.replaceAll(additionalHeaders)
        }

        fun replaceAllAdditionalHeaders(additionalHeaders: Map<String, Iterable<String>>) = apply {
            this.additionalHeaders.replaceAll(additionalHeaders)
        }

        fun removeAdditionalHeaders(name: String) = apply { additionalHeaders.remove(name) }

        fun removeAllAdditionalHeaders(names: Set<String>) = apply {
            additionalHeaders.removeAll(names)
        }

        fun additionalQueryParams(additionalQueryParams: QueryParams) = apply {
            this.additionalQueryParams.clear()
            putAllAdditionalQueryParams(additionalQueryParams)
        }

        fun additionalQueryParams(additionalQueryParams: Map<String, Iterable<String>>) = apply {
            this.additionalQueryParams.clear()
            putAllAdditionalQueryParams(additionalQueryParams)
        }

        fun putAdditionalQueryParam(key: String, value: String) = apply {
            additionalQueryParams.put(key, value)
        }

        fun putAdditionalQueryParams(key: String, values: Iterable<String>) = apply {
            additionalQueryParams.put(key, values)
        }

        fun putAllAdditionalQueryParams(additionalQueryParams: QueryParams) = apply {
            this.additionalQueryParams.putAll(additionalQueryParams)
        }

        fun putAllAdditionalQueryParams(additionalQueryParams: Map<String, Iterable<String>>) =
            apply {
                this.additionalQueryParams.putAll(additionalQueryParams)
            }

        fun replaceAdditionalQueryParams(key: String, value: String) = apply {
            additionalQueryParams.replace(key, value)
        }

        fun replaceAdditionalQueryParams(key: String, values: Iterable<String>) = apply {
            additionalQueryParams.replace(key, values)
        }

        fun replaceAllAdditionalQueryParams(additionalQueryParams: QueryParams) = apply {
            this.additionalQueryParams.replaceAll(additionalQueryParams)
        }

        fun replaceAllAdditionalQueryParams(additionalQueryParams: Map<String, Iterable<String>>) =
            apply {
                this.additionalQueryParams.replaceAll(additionalQueryParams)
            }

        fun removeAdditionalQueryParams(key: String) = apply { additionalQueryParams.remove(key) }

        fun removeAllAdditionalQueryParams(keys: Set<String>) = apply {
            additionalQueryParams.removeAll(keys)
        }

        /**
         * Returns an immutable instance of [FileUploadParams].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .file()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): FileUploadParams =
            FileUploadParams(
                betas?.toImmutable(),
                workspaceId,
                body.build(),
                additionalHeaders.build(),
                additionalQueryParams.build(),
            )
    }

    fun _body(): Map<String, MultipartField<*>> =
        (mapOf("file" to _file(), "expires_in_seconds" to _expiresInSeconds()) +
                _additionalBodyProperties().mapValues { (_, value) -> MultipartField.of(value) })
            .toImmutable()

    override fun _headers(): Headers =
        Headers.builder()
            .apply {
                betas?.forEach { put("anthropic-beta", it.toString()) }
                workspaceId?.let { put("anthropic-workspace-id", it) }
                putAll(additionalHeaders)
            }
            .build()

    override fun _queryParams(): QueryParams = additionalQueryParams

    class Body
    private constructor(
        private val file: MultipartField<InputStream>,
        private val expiresInSeconds: MultipartField<Long>,
        private val additionalProperties: MutableMap<String, JsonValue>,
    ) {

        /**
         * The file to upload. Only the final path component of the part's `filename` is kept; an
         * absent or empty `filename` is replaced with `unnamed` plus the extension for the file's
         * stored `mime_type`, when known.
         *
         * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
         *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
         */
        fun file(): InputStream = file.value.getRequired("file")

        /**
         * Seconds from upload until the file expires and its bytes become permanently unavailable.
         * Must be between 3600 (one hour) and 7776000 (ninety days).
         *
         * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if
         *   the server responded with an unexpected value).
         */
        fun expiresInSeconds(): Optional<Long> =
            expiresInSeconds.value.getOptional("expires_in_seconds")

        /**
         * Returns the raw multipart value of [file].
         *
         * Unlike [file], this method doesn't throw if the multipart field has an unexpected type.
         */
        @JsonProperty("file") @ExcludeMissing fun _file(): MultipartField<InputStream> = file

        /**
         * Returns the raw multipart value of [expiresInSeconds].
         *
         * Unlike [expiresInSeconds], this method doesn't throw if the multipart field has an
         * unexpected type.
         */
        @JsonProperty("expires_in_seconds")
        @ExcludeMissing
        fun _expiresInSeconds(): MultipartField<Long> = expiresInSeconds

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
             * Returns a mutable builder for constructing an instance of [Body].
             *
             * The following fields are required:
             * ```java
             * .file()
             * ```
             */
            @JvmStatic fun builder() = Builder()

            /**
             * Returns an immutable instance of [Body] with the required [file] set to the given
             * value.
             */
            @JvmStatic fun of(file: InputStream) = builder().file(file).build()
        }

        /** A builder for [Body]. */
        class Builder internal constructor() {

            private var file: MultipartField<InputStream>? = null
            private var expiresInSeconds: MultipartField<Long> = MultipartField.of(null)
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            @JvmSynthetic
            internal fun from(body: Body) = apply {
                file = body.file
                expiresInSeconds = body.expiresInSeconds
                additionalProperties = body.additionalProperties.toMutableMap()
            }

            /**
             * The file to upload. Only the final path component of the part's `filename` is kept;
             * an absent or empty `filename` is replaced with `unnamed` plus the extension for the
             * file's stored `mime_type`, when known.
             */
            fun file(file: InputStream) = file(MultipartField.of(file))

            /**
             * Sets [Builder.file] to an arbitrary multipart value.
             *
             * You should usually call [Builder.file] with a well-typed [InputStream] value instead.
             * This method is primarily for setting the field to an undocumented or not yet
             * supported value.
             */
            fun file(file: MultipartField<InputStream>) = apply { this.file = file }

            /**
             * The file to upload. Only the final path component of the part's `filename` is kept;
             * an absent or empty `filename` is replaced with `unnamed` plus the extension for the
             * file's stored `mime_type`, when known.
             */
            fun file(file: ByteArray) = file(file.inputStream())

            /**
             * The file to upload. Only the final path component of the part's `filename` is kept;
             * an absent or empty `filename` is replaced with `unnamed` plus the extension for the
             * file's stored `mime_type`, when known.
             */
            fun file(path: Path) =
                file(
                    MultipartField.builder<InputStream>()
                        .value(path.inputStream())
                        .filename(path.name)
                        .build()
                )

            /**
             * Seconds from upload until the file expires and its bytes become permanently
             * unavailable. Must be between 3600 (one hour) and 7776000 (ninety days).
             */
            fun expiresInSeconds(expiresInSeconds: Long) =
                expiresInSeconds(MultipartField.of(expiresInSeconds))

            /**
             * Sets [Builder.expiresInSeconds] to an arbitrary multipart value.
             *
             * You should usually call [Builder.expiresInSeconds] with a well-typed [Long] value
             * instead. This method is primarily for setting the field to an undocumented or not yet
             * supported value.
             */
            fun expiresInSeconds(expiresInSeconds: MultipartField<Long>) = apply {
                this.expiresInSeconds = expiresInSeconds
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
             * Returns an immutable instance of [Body].
             *
             * Further updates to this [Builder] will not mutate the returned instance.
             *
             * The following fields are required:
             * ```java
             * .file()
             * ```
             *
             * @throws IllegalStateException if any required field is unset.
             */
            fun build(): Body =
                Body(
                    checkRequired("file", file),
                    expiresInSeconds,
                    additionalProperties.toMutableMap(),
                )
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
        fun validate(): Body = apply {
            if (validated) {
                return@apply
            }

            file()
            expiresInSeconds()
            validated = true
        }

        fun isValid(): Boolean =
            try {
                validate()
                true
            } catch (e: AnthropicInvalidDataException) {
                false
            }

        override fun equals(other: Any?): Boolean {
            if (this === other) {
                return true
            }

            return other is Body &&
                file == other.file &&
                expiresInSeconds == other.expiresInSeconds &&
                additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy {
            Objects.hash(file, expiresInSeconds, additionalProperties)
        }

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "Body{file=$file, expiresInSeconds=$expiresInSeconds, additionalProperties=$additionalProperties}"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is FileUploadParams &&
            betas == other.betas &&
            workspaceId == other.workspaceId &&
            body == other.body &&
            additionalHeaders == other.additionalHeaders &&
            additionalQueryParams == other.additionalQueryParams
    }

    override fun hashCode(): Int =
        Objects.hash(betas, workspaceId, body, additionalHeaders, additionalQueryParams)

    override fun toString() =
        "FileUploadParams{betas=$betas, workspaceId=$workspaceId, body=$body, additionalHeaders=$additionalHeaders, additionalQueryParams=$additionalQueryParams}"
}
