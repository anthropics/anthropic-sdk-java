package com.anthropic.models.beta.organization.plugins.versions

import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.MultipartField
import com.anthropic.core.Params
import com.anthropic.core.checkKnown
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
import kotlin.jvm.optionals.getOrNull

/**
 * Add a version to an organization-owned Plugin by uploading the new version's files; it becomes
 * the version served to members unless the Plugin's served version has been pinned.
 *
 * The upload is the same `multipart/form-data` as creating a Plugin: the version's files (`files`,
 * each part sent as `files[]`) and optional `release_notes`. The uploaded manifest's `name` must
 * equal the Plugin's `name`. Returns the stored version; read the Plugin back to see which version
 * it serves.
 *
 * Only a Plugin in a `manual` marketplace takes uploads; a Plugin synchronized from a repository
 * gets its versions from the repository. When the Plugin is in the organization's library
 * marketplace, a version that adds a skill with the name of an organization skill (a skill an
 * administrator uploaded for the whole organization in claude.ai) is refused with a 409:
 * `error_code` `skill_name_taken`, with that name in `details.skill_name`. A 503 with `error_code`
 * `registration_pending` means the version was stored but is not yet usable; a later version create
 * on the Plugin completes it.
 *
 * For a worked example, see [Create a version](/docs/en/manage-claude/plugins-api#create-a-version)
 * in the Plugins API guide.
 *
 * **Accepted credentials:** an Admin API key with the `write:plugins` scope.
 *
 * Every request must include the beta header `anthropic-beta: ce-plugins-2026-09-01`. A request
 * without it returns `404`, exactly as if the endpoint did not exist. The Plugins API is in beta
 * and is available to Claude Enterprise organizations only. It is not available to Claude Platform
 * (Claude Console) organizations, or to organizations with HIPAA readiness enabled.
 */
class VersionCreateParams
private constructor(
    private val pluginId: String?,
    private val betas: List<AnthropicBeta>?,
    private val body: Body,
    private val additionalHeaders: Headers,
    private val additionalQueryParams: QueryParams,
) : Params {

    /** ID of the Plugin (prefixed `plugin_`). */
    fun pluginId(): Optional<String> = Optional.ofNullable(pluginId)

    /** This endpoint is in beta: requests must send `ce-plugins-2026-09-01` in this header. */
    fun betas(): Optional<List<AnthropicBeta>> = Optional.ofNullable(betas)

    /**
     * The version's files: one part per file, the part's filename being the file's path within the
     * Plugin (for example `skills/review-pr/SKILL.md`), or a single `.zip` or `.plugin` archive
     * holding them all. On the wire each part is named `files[]`, and a part named plain `files` is
     * not read; with cURL, `-F 'files[]=@SKILL.md;filename=skills/review-pr/SKILL.md'`. The files
     * must include the manifest, `.claude-plugin/plugin.json`.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun files(): List<InputStream> = body.files()

    /**
     * Release notes stored with the version and shown in its version history in claude.ai; up to
     * 5,000 characters.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if the
     *   server responded with an unexpected value).
     */
    fun releaseNotes(): Optional<String> = body.releaseNotes()

    /**
     * Returns the raw multipart value of [files].
     *
     * Unlike [files], this method doesn't throw if the multipart field has an unexpected type.
     */
    fun _files(): MultipartField<List<InputStream>> = body._files()

    /**
     * Returns the raw multipart value of [releaseNotes].
     *
     * Unlike [releaseNotes], this method doesn't throw if the multipart field has an unexpected
     * type.
     */
    fun _releaseNotes(): MultipartField<String> = body._releaseNotes()

    fun _additionalBodyProperties(): Map<String, JsonValue> = body._additionalProperties()

    /** Additional headers to send with the request. */
    fun _additionalHeaders(): Headers = additionalHeaders

    /** Additional query param to send with the request. */
    fun _additionalQueryParams(): QueryParams = additionalQueryParams

    fun toBuilder() = Builder().from(this)

    companion object {

        /**
         * Returns a mutable builder for constructing an instance of [VersionCreateParams].
         *
         * The following fields are required:
         * ```java
         * .files()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [VersionCreateParams]. */
    class Builder internal constructor() {

        private var pluginId: String? = null
        private var betas: MutableList<AnthropicBeta>? = null
        private var body: Body.Builder = Body.builder()
        private var additionalHeaders: Headers.Builder = Headers.builder()
        private var additionalQueryParams: QueryParams.Builder = QueryParams.builder()

        @JvmSynthetic
        internal fun from(versionCreateParams: VersionCreateParams) = apply {
            pluginId = versionCreateParams.pluginId
            betas = versionCreateParams.betas?.toMutableList()
            body = versionCreateParams.body.toBuilder()
            additionalHeaders = versionCreateParams.additionalHeaders.toBuilder()
            additionalQueryParams = versionCreateParams.additionalQueryParams.toBuilder()
        }

        /** ID of the Plugin (prefixed `plugin_`). */
        fun pluginId(pluginId: String?) = apply { this.pluginId = pluginId }

        /** Alias for calling [Builder.pluginId] with `pluginId.orElse(null)`. */
        fun pluginId(pluginId: Optional<String>) = pluginId(pluginId.getOrNull())

        /** This endpoint is in beta: requests must send `ce-plugins-2026-09-01` in this header. */
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
         * - [releaseNotes]
         */
        fun body(body: Body) = apply { this.body = body.toBuilder() }

        /**
         * The version's files: one part per file, the part's filename being the file's path within
         * the Plugin (for example `skills/review-pr/SKILL.md`), or a single `.zip` or `.plugin`
         * archive holding them all. On the wire each part is named `files[]`, and a part named
         * plain `files` is not read; with cURL, `-F
         * 'files[]=@SKILL.md;filename=skills/review-pr/SKILL.md'`. The files must include the
         * manifest, `.claude-plugin/plugin.json`.
         */
        fun files(files: List<InputStream>) = apply { body.files(files) }

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
         * The version's files: one part per file, the part's filename being the file's path within
         * the Plugin (for example `skills/review-pr/SKILL.md`), or a single `.zip` or `.plugin`
         * archive holding them all. On the wire each part is named `files[]`, and a part named
         * plain `files` is not read; with cURL, `-F
         * 'files[]=@SKILL.md;filename=skills/review-pr/SKILL.md'`. The files must include the
         * manifest, `.claude-plugin/plugin.json`.
         */
        fun addFile(file: ByteArray) = apply { body.addFile(file) }

        /**
         * The version's files: one part per file, the part's filename being the file's path within
         * the Plugin (for example `skills/review-pr/SKILL.md`), or a single `.zip` or `.plugin`
         * archive holding them all. On the wire each part is named `files[]`, and a part named
         * plain `files` is not read; with cURL, `-F
         * 'files[]=@SKILL.md;filename=skills/review-pr/SKILL.md'`. The files must include the
         * manifest, `.claude-plugin/plugin.json`.
         */
        fun addFile(path: Path) = apply { body.addFile(path) }

        /**
         * Release notes stored with the version and shown in its version history in claude.ai; up
         * to 5,000 characters.
         */
        fun releaseNotes(releaseNotes: String) = apply { body.releaseNotes(releaseNotes) }

        /**
         * Sets [Builder.releaseNotes] to an arbitrary multipart value.
         *
         * You should usually call [Builder.releaseNotes] with a well-typed [String] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun releaseNotes(releaseNotes: MultipartField<String>) = apply {
            body.releaseNotes(releaseNotes)
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
         * Returns an immutable instance of [VersionCreateParams].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .files()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): VersionCreateParams =
            VersionCreateParams(
                pluginId,
                betas?.toImmutable(),
                body.build(),
                additionalHeaders.build(),
                additionalQueryParams.build(),
            )
    }

    fun _body(): Map<String, MultipartField<*>> =
        (mapOf("files" to _files(), "release_notes" to _releaseNotes()) +
                _additionalBodyProperties().mapValues { (_, value) -> MultipartField.of(value) })
            .toImmutable()

    fun _pathParam(index: Int): String =
        when (index) {
            0 -> pluginId ?: ""
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
        private val releaseNotes: MultipartField<String>,
        private val additionalProperties: MutableMap<String, JsonValue>,
    ) {

        /**
         * The version's files: one part per file, the part's filename being the file's path within
         * the Plugin (for example `skills/review-pr/SKILL.md`), or a single `.zip` or `.plugin`
         * archive holding them all. On the wire each part is named `files[]`, and a part named
         * plain `files` is not read; with cURL, `-F
         * 'files[]=@SKILL.md;filename=skills/review-pr/SKILL.md'`. The files must include the
         * manifest, `.claude-plugin/plugin.json`.
         *
         * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
         *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
         */
        fun files(): List<InputStream> = files.value.getRequired("files")

        /**
         * Release notes stored with the version and shown in its version history in claude.ai; up
         * to 5,000 characters.
         *
         * @throws AnthropicInvalidDataException if the JSON field has an unexpected type (e.g. if
         *   the server responded with an unexpected value).
         */
        fun releaseNotes(): Optional<String> = releaseNotes.value.getOptional("release_notes")

        /**
         * Returns the raw multipart value of [files].
         *
         * Unlike [files], this method doesn't throw if the multipart field has an unexpected type.
         */
        @JsonProperty("files")
        @ExcludeMissing
        fun _files(): MultipartField<List<InputStream>> = files

        /**
         * Returns the raw multipart value of [releaseNotes].
         *
         * Unlike [releaseNotes], this method doesn't throw if the multipart field has an unexpected
         * type.
         */
        @JsonProperty("release_notes")
        @ExcludeMissing
        fun _releaseNotes(): MultipartField<String> = releaseNotes

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
             * .files()
             * ```
             */
            @JvmStatic fun builder() = Builder()

            /**
             * Returns an immutable instance of [Body] with the required [files] set to the given
             * value.
             */
            @JvmStatic fun of(files: List<InputStream>) = builder().files(files).build()
        }

        /** A builder for [Body]. */
        class Builder internal constructor() {

            private var files: MultipartField<MutableList<InputStream>>? = null
            private var releaseNotes: MultipartField<String> = MultipartField.of(null)
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            @JvmSynthetic
            internal fun from(body: Body) = apply {
                files = body.files.map { it.toMutableList() }
                releaseNotes = body.releaseNotes
                additionalProperties = body.additionalProperties.toMutableMap()
            }

            /**
             * The version's files: one part per file, the part's filename being the file's path
             * within the Plugin (for example `skills/review-pr/SKILL.md`), or a single `.zip` or
             * `.plugin` archive holding them all. On the wire each part is named `files[]`, and a
             * part named plain `files` is not read; with cURL, `-F
             * 'files[]=@SKILL.md;filename=skills/review-pr/SKILL.md'`. The files must include the
             * manifest, `.claude-plugin/plugin.json`.
             */
            fun files(files: List<InputStream>) =
                files(
                    MultipartField.builder<List<InputStream>>()
                        .value(files)
                        .contentType("application/octet-stream")
                        .build()
                )

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
             * The version's files: one part per file, the part's filename being the file's path
             * within the Plugin (for example `skills/review-pr/SKILL.md`), or a single `.zip` or
             * `.plugin` archive holding them all. On the wire each part is named `files[]`, and a
             * part named plain `files` is not read; with cURL, `-F
             * 'files[]=@SKILL.md;filename=skills/review-pr/SKILL.md'`. The files must include the
             * manifest, `.claude-plugin/plugin.json`.
             */
            fun addFile(file: ByteArray) = addFile(file.inputStream())

            /**
             * The version's files: one part per file, the part's filename being the file's path
             * within the Plugin (for example `skills/review-pr/SKILL.md`), or a single `.zip` or
             * `.plugin` archive holding them all. On the wire each part is named `files[]`, and a
             * part named plain `files` is not read; with cURL, `-F
             * 'files[]=@SKILL.md;filename=skills/review-pr/SKILL.md'`. The files must include the
             * manifest, `.claude-plugin/plugin.json`.
             */
            fun addFile(path: Path) = addFile(path.inputStream())

            /**
             * Release notes stored with the version and shown in its version history in claude.ai;
             * up to 5,000 characters.
             */
            fun releaseNotes(releaseNotes: String) = releaseNotes(MultipartField.of(releaseNotes))

            /**
             * Sets [Builder.releaseNotes] to an arbitrary multipart value.
             *
             * You should usually call [Builder.releaseNotes] with a well-typed [String] value
             * instead. This method is primarily for setting the field to an undocumented or not yet
             * supported value.
             */
            fun releaseNotes(releaseNotes: MultipartField<String>) = apply {
                this.releaseNotes = releaseNotes
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
             * .files()
             * ```
             *
             * @throws IllegalStateException if any required field is unset.
             */
            fun build(): Body =
                Body(
                    checkRequired("files", files).map { it.toImmutable() },
                    releaseNotes,
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

            files()
            releaseNotes()
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
                releaseNotes == other.releaseNotes &&
                additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy {
            Objects.hash(files, releaseNotes, additionalProperties)
        }

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "Body{files=$files, releaseNotes=$releaseNotes, additionalProperties=$additionalProperties}"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is VersionCreateParams &&
            pluginId == other.pluginId &&
            betas == other.betas &&
            body == other.body &&
            additionalHeaders == other.additionalHeaders &&
            additionalQueryParams == other.additionalQueryParams
    }

    override fun hashCode(): Int =
        Objects.hash(pluginId, betas, body, additionalHeaders, additionalQueryParams)

    override fun toString() =
        "VersionCreateParams{pluginId=$pluginId, betas=$betas, body=$body, additionalHeaders=$additionalHeaders, additionalQueryParams=$additionalQueryParams}"
}
