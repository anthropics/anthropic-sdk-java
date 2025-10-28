// File generated from our OpenAPI spec by Stainless.

package com.anthropic.models.beta.skills.versions

import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.MultipartField
import com.anthropic.core.Params
import com.anthropic.core.checkKnown
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

/** Create Skill Version */
class VersionCreateParams
private constructor(
    private val skillId: String?,
    private val betas: List<AnthropicBeta>?,
    private val body: Body,
    private val additionalHeaders: Headers,
    private val additionalQueryParams: QueryParams,
) : Params {

    /**
     * Unique identifier for the skill.
     *
     * The format and length of IDs may change over time.
     */
    fun skillId(): Optional<String> = Optional.ofNullable(skillId)

    /** Optional header to specify the beta version(s) you want to use. */
    fun betas(): Optional<List<AnthropicBeta>> = Optional.ofNullable(betas)

    /**
     * Files to upload for the skill.
     *
     * All files must be in the same top-level directory and must include a SKILL.md file at the
     * root of that directory.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun files(): Optional<List<InputStream>> = body.files()

    /**
     * Returns the raw multipart value of [files].
     *
     * Unlike [files], this method doesn't throw if the multipart field has an unexpected type.
     */
    fun _files(): MultipartField<List<InputStream>> = body._files()

    fun _additionalBodyProperties(): Map<String, JsonValue> = body._additionalProperties()

    /** Additional headers to send with the request. */
    fun _additionalHeaders(): Headers = additionalHeaders

    /** Additional query param to send with the request. */
    fun _additionalQueryParams(): QueryParams = additionalQueryParams

    fun toBuilder() = Builder().from(this)

    companion object {

        @JvmStatic fun none(): VersionCreateParams = builder().build()

        /** Returns a mutable builder for constructing an instance of [VersionCreateParams]. */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [VersionCreateParams]. */
    class Builder internal constructor() {

        private var skillId: String? = null
        private var betas: MutableList<AnthropicBeta>? = null
        private var body: Body.Builder = Body.builder()
        private var additionalHeaders: Headers.Builder = Headers.builder()
        private var additionalQueryParams: QueryParams.Builder = QueryParams.builder()

        @JvmSynthetic
        internal fun from(versionCreateParams: VersionCreateParams) = apply {
            skillId = versionCreateParams.skillId
            betas = versionCreateParams.betas?.toMutableList()
            body = versionCreateParams.body.toBuilder()
            additionalHeaders = versionCreateParams.additionalHeaders.toBuilder()
            additionalQueryParams = versionCreateParams.additionalQueryParams.toBuilder()
        }

        /**
         * Unique identifier for the skill.
         *
         * The format and length of IDs may change over time.
         */
        fun skillId(skillId: String?) = apply { this.skillId = skillId }

        /** Alias for calling [Builder.skillId] with `skillId.orElse(null)`. */
        fun skillId(skillId: Optional<String>) = skillId(skillId.getOrNull())

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

        /**
         * Sets the entire request body.
         *
         * This is generally only useful if you are already constructing the body separately.
         * Otherwise, it's more convenient to use the top-level setters instead:
         * - [files]
         */
        fun body(body: Body) = apply { this.body = body.toBuilder() }

        /**
         * Files to upload for the skill.
         *
         * All files must be in the same top-level directory and must include a SKILL.md file at the
         * root of that directory.
         */
        fun files(files: List<InputStream>?) = apply { body.files(files) }

        /** Alias for calling [Builder.files] with `files.orElse(null)`. */
        fun files(files: Optional<List<InputStream>>) = files(files.getOrNull())

        /**
         * Sets [Builder.files] to an arbitrary multipart value.
         *
         * You should usually call [Builder.files] with a well-typed `List<InputStream>` value
         * instead. This method is primarily for setting the field to an undocumented or not yet
         * supported value.
         */
        fun files(files: MultipartField<List<InputStream>>) = apply { body.files(files) }

        /**
         * Adds a single [InputStream] to [files].
         *
         * @throws IllegalStateException if the field was previously set to a non-list.
         */
        fun addFile(file: InputStream) = apply { body.addFile(file) }

        /**
         * Files to upload for the skill.
         *
         * All files must be in the same top-level directory and must include a SKILL.md file at the
         * root of that directory.
         */
        fun addFile(file: ByteArray) = apply { body.addFile(file) }

        /**
         * Files to upload for the skill.
         *
         * All files must be in the same top-level directory and must include a SKILL.md file at the
         * root of that directory.
         */
        fun addFile(path: Path) = apply { body.addFile(path) }

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
         * Returns an immutable instance of [VersionCreateParams].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         */
        fun build(): VersionCreateParams =
            VersionCreateParams(
                skillId,
                betas?.toImmutable(),
                body.build(),
                additionalHeaders.build(),
                additionalQueryParams.build(),
            )
    }

    fun _body(): Map<String, MultipartField<*>> =
        (mapOf("files" to _files()) +
                _additionalBodyProperties().mapValues { (_, value) -> MultipartField.of(value) })
            .toImmutable()

    fun _pathParam(index: Int): String =
        when (index) {
            0 -> skillId ?: ""
            else -> ""
        }

    override fun _headers(): Headers =
        Headers.builder()
            .apply {
                betas?.forEach { put("anthropic-beta", it.toString()) }
                putAll(additionalHeaders)
            }
            .build()

    override fun _queryParams(): QueryParams = additionalQueryParams

    class Body
    private constructor(
        private val files: MultipartField<List<InputStream>>,
        private val additionalProperties: MutableMap<String, JsonValue>,
    ) {

        /**
         * Files to upload for the skill.
         *
         * All files must be in the same top-level directory and must include a SKILL.md file at the
         * root of that directory.
         *
         * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if
         *   the server responded with an unexpected value).
         */
        fun files(): Optional<List<InputStream>> = files.value.getOptional("files")

        /**
         * Returns the raw multipart value of [files].
         *
         * Unlike [files], this method doesn't throw if the multipart field has an unexpected type.
         */
        @JsonProperty("files")
        @ExcludeMissing
        fun _files(): MultipartField<List<InputStream>> = files

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

            /** Returns a mutable builder for constructing an instance of [Body]. */
            @JvmStatic fun builder() = Builder()
        }

        /** A builder for [Body]. */
        class Builder internal constructor() {

            private var files: MultipartField<MutableList<InputStream>>? = null
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            @JvmSynthetic
            internal fun from(body: Body) = apply {
                files = body.files.map { it.toMutableList() }
                additionalProperties = body.additionalProperties.toMutableMap()
            }

            /**
             * Files to upload for the skill.
             *
             * All files must be in the same top-level directory and must include a SKILL.md file at
             * the root of that directory.
             */
            fun files(files: List<InputStream>?) =
                files(
                    MultipartField.builder<List<InputStream>>()
                        .value(files)
                        .contentType("application/octet-stream")
                        .build()
                )

            /** Alias for calling [Builder.files] with `files.orElse(null)`. */
            fun files(files: Optional<List<InputStream>>) = files(files.getOrNull())

            /**
             * Sets [Builder.files] to an arbitrary multipart value.
             *
             * You should usually call [Builder.files] with a well-typed `List<InputStream>` value
             * instead. This method is primarily for setting the field to an undocumented or not yet
             * supported value.
             */
            fun files(files: MultipartField<List<InputStream>>) = apply {
                this.files = files.map { it.toMutableList() }
            }

            /**
             * Adds a single [InputStream] to [files].
             *
             * @throws IllegalStateException if the field was previously set to a non-list.
             */
            fun addFile(file: InputStream) = apply {
                files =
                    (files
                            ?: MultipartField.builder<MutableList<InputStream>>()
                                .value(mutableListOf())
                                .contentType("application/octet-stream")
                                .build())
                        .also { checkKnown("files", it).add(file) }
            }

            /**
             * Files to upload for the skill.
             *
             * All files must be in the same top-level directory and must include a SKILL.md file at
             * the root of that directory.
             */
            fun addFile(file: ByteArray) = addFile(file.inputStream())

            /**
             * Files to upload for the skill.
             *
             * All files must be in the same top-level directory and must include a SKILL.md file at
             * the root of that directory.
             */
            fun addFile(path: Path) = addFile(path.inputStream())

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
             */
            fun build(): Body =
                Body(
                    (files ?: MultipartField.of(null)).map { it.toImmutable() },
                    additionalProperties.toMutableMap(),
                )
        }

        private var validated: Boolean = false

        fun validate(): Body = apply {
            if (validated) {
                return@apply
            }

            files()
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
                files == other.files &&
                additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy { Objects.hash(files, additionalProperties) }

        override fun hashCode(): Int = hashCode

        override fun toString() = "Body{files=$files, additionalProperties=$additionalProperties}"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is VersionCreateParams &&
            skillId == other.skillId &&
            betas == other.betas &&
            body == other.body &&
            additionalHeaders == other.additionalHeaders &&
            additionalQueryParams == other.additionalQueryParams
    }

    override fun hashCode(): Int =
        Objects.hash(skillId, betas, body, additionalHeaders, additionalQueryParams)

    override fun toString() =
        "VersionCreateParams{skillId=$skillId, betas=$betas, body=$body, additionalHeaders=$additionalHeaders, additionalQueryParams=$additionalQueryParams}"
}
