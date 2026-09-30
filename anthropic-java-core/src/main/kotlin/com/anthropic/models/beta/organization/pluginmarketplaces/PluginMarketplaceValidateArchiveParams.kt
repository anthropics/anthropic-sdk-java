package com.anthropic.models.beta.organization.pluginmarketplaces

import com.anthropic.core.ExcludeMissing
import com.anthropic.core.JsonValue
import com.anthropic.core.MultipartField
import com.anthropic.core.Params
import com.anthropic.core.checkRequired
import com.anthropic.core.http.Headers
import com.anthropic.core.http.PathInputStream
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
import kotlin.io.path.name
import kotlin.jvm.optionals.getOrNull

/**
 * Check whether a plugin marketplace, uploaded as a `.zip` of the marketplace directory, would
 * synchronize into claude.ai, without connecting or storing it.
 *
 * To check a public GitHub repository instead, use Validate Plugin Marketplace Repository.
 *
 * The report says whether `marketplace.json` is well-formed, which plugins a synchronization would
 * skip and why, and which plugins would synchronize only in part, with some files left out. An
 * archive that cannot be read as a marketplace is reported, not refused: the response is a report
 * with `valid: false`. Plugin sources outside the marketplace are fetched anonymously from GitHub,
 * so a private one is reported as not found; a source on any other host is not fetched here, and
 * the report notes that it will be checked when the marketplace actually synchronizes.
 *
 * Nothing is recorded on the Compliance API activity feed.
 *
 * For a worked example, see
 * [Validate marketplace content](/docs/en/manage-claude/plugins-api#validate-marketplace-content)
 * in the Plugins API guide.
 *
 * **Accepted credentials:** an Admin API key with the `read:plugins` or `write:plugins` scope;
 * `read:org_audit` and `read:compliance_org_data` do not grant it.
 *
 * Every request must include the beta header `anthropic-beta: ce-plugins-2026-09-01`. A request
 * without it returns `404`, exactly as if the endpoint did not exist. The Plugins API is in beta
 * and is available to Claude Enterprise organizations only. It is not available to Claude Platform
 * (Claude Console) organizations, or to organizations with HIPAA readiness enabled.
 */
class PluginMarketplaceValidateArchiveParams
private constructor(
    private val betas: List<AnthropicBeta>?,
    private val body: Body,
    private val additionalHeaders: Headers,
    private val additionalQueryParams: QueryParams,
) : Params {

    /** This endpoint is in beta: requests must send `ce-plugins-2026-09-01` in this header. */
    fun betas(): Optional<List<AnthropicBeta>> = Optional.ofNullable(betas)

    /**
     * A .zip of the marketplace directory (its contents at the root, or wrapped in one folder as a
     * Git host's download produces), sent as a file part with a filename; DEFLATE- or
     * STORE-compressed, at most 32 MB. A part sent without a filename, a second archive part, or
     * any other form field is a 400; a larger archive is a 413.
     *
     * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
     *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
     */
    fun archive(): InputStream = body.archive()

    /**
     * Returns the raw multipart value of [archive].
     *
     * Unlike [archive], this method doesn't throw if the multipart field has an unexpected type.
     */
    fun _archive(): MultipartField<InputStream> = body._archive()

    fun _additionalBodyProperties(): Map<String, JsonValue> = body._additionalProperties()

    /** Additional headers to send with the request. */
    fun _additionalHeaders(): Headers = additionalHeaders

    /** Additional query param to send with the request. */
    fun _additionalQueryParams(): QueryParams = additionalQueryParams

    fun toBuilder() = Builder().from(this)

    companion object {

        /**
         * Returns a mutable builder for constructing an instance of
         * [PluginMarketplaceValidateArchiveParams].
         *
         * The following fields are required:
         * ```java
         * .archive()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [PluginMarketplaceValidateArchiveParams]. */
    class Builder internal constructor() {

        private var betas: MutableList<AnthropicBeta>? = null
        private var body: Body.Builder = Body.builder()
        private var additionalHeaders: Headers.Builder = Headers.builder()
        private var additionalQueryParams: QueryParams.Builder = QueryParams.builder()

        @JvmSynthetic
        internal fun from(
            pluginMarketplaceValidateArchiveParams: PluginMarketplaceValidateArchiveParams
        ) = apply {
            betas = pluginMarketplaceValidateArchiveParams.betas?.toMutableList()
            body = pluginMarketplaceValidateArchiveParams.body.toBuilder()
            additionalHeaders = pluginMarketplaceValidateArchiveParams.additionalHeaders.toBuilder()
            additionalQueryParams =
                pluginMarketplaceValidateArchiveParams.additionalQueryParams.toBuilder()
        }

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
         * - [archive]
         */
        fun body(body: Body) = apply { this.body = body.toBuilder() }

        /**
         * A .zip of the marketplace directory (its contents at the root, or wrapped in one folder
         * as a Git host's download produces), sent as a file part with a filename; DEFLATE- or
         * STORE-compressed, at most 32 MB. A part sent without a filename, a second archive part,
         * or any other form field is a 400; a larger archive is a 413.
         */
        fun archive(archive: InputStream) = apply { body.archive(archive) }

        /**
         * Sets [Builder.archive] to an arbitrary multipart value.
         *
         * You should usually call [Builder.archive] with a well-typed [InputStream] value instead.
         * This method is primarily for setting the field to an undocumented or not yet supported
         * value.
         */
        fun archive(archive: MultipartField<InputStream>) = apply { body.archive(archive) }

        /**
         * A .zip of the marketplace directory (its contents at the root, or wrapped in one folder
         * as a Git host's download produces), sent as a file part with a filename; DEFLATE- or
         * STORE-compressed, at most 32 MB. A part sent without a filename, a second archive part,
         * or any other form field is a 400; a larger archive is a 413.
         */
        fun archive(archive: ByteArray) = apply { body.archive(archive) }

        /**
         * A .zip of the marketplace directory (its contents at the root, or wrapped in one folder
         * as a Git host's download produces), sent as a file part with a filename; DEFLATE- or
         * STORE-compressed, at most 32 MB. A part sent without a filename, a second archive part,
         * or any other form field is a 400; a larger archive is a 413.
         */
        fun archive(path: Path) = apply { body.archive(path) }

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
         * Returns an immutable instance of [PluginMarketplaceValidateArchiveParams].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .archive()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): PluginMarketplaceValidateArchiveParams =
            PluginMarketplaceValidateArchiveParams(
                betas?.toImmutable(),
                body.build(),
                additionalHeaders.build(),
                additionalQueryParams.build(),
            )
    }

    fun _body(): Map<String, MultipartField<*>> =
        (mapOf("archive" to _archive()) +
                _additionalBodyProperties().mapValues { (_, value) -> MultipartField.of(value) })
            .toImmutable()

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
        private val archive: MultipartField<InputStream>,
        private val additionalProperties: MutableMap<String, JsonValue>,
    ) {

        /**
         * A .zip of the marketplace directory (its contents at the root, or wrapped in one folder
         * as a Git host's download produces), sent as a file part with a filename; DEFLATE- or
         * STORE-compressed, at most 32 MB. A part sent without a filename, a second archive part,
         * or any other form field is a 400; a larger archive is a 413.
         *
         * @throws AnthropicInvalidDataException if the JSON field has an unexpected type or is
         *   unexpectedly missing or null (e.g. if the server responded with an unexpected value).
         */
        fun archive(): InputStream = archive.value.getRequired("archive")

        /**
         * Returns the raw multipart value of [archive].
         *
         * Unlike [archive], this method doesn't throw if the multipart field has an unexpected
         * type.
         */
        @JsonProperty("archive")
        @ExcludeMissing
        fun _archive(): MultipartField<InputStream> = archive

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
             * .archive()
             * ```
             */
            @JvmStatic fun builder() = Builder()

            /**
             * Returns an immutable instance of [Body] with the required [archive] set to the given
             * value.
             */
            @JvmStatic fun of(archive: InputStream) = builder().archive(archive).build()
        }

        /** A builder for [Body]. */
        class Builder internal constructor() {

            private var archive: MultipartField<InputStream>? = null
            private var additionalProperties: MutableMap<String, JsonValue> = mutableMapOf()

            @JvmSynthetic
            internal fun from(body: Body) = apply {
                archive = body.archive
                additionalProperties = body.additionalProperties.toMutableMap()
            }

            /**
             * A .zip of the marketplace directory (its contents at the root, or wrapped in one
             * folder as a Git host's download produces), sent as a file part with a filename;
             * DEFLATE- or STORE-compressed, at most 32 MB. A part sent without a filename, a second
             * archive part, or any other form field is a 400; a larger archive is a 413.
             */
            fun archive(archive: InputStream) = archive(MultipartField.of(archive))

            /**
             * Sets [Builder.archive] to an arbitrary multipart value.
             *
             * You should usually call [Builder.archive] with a well-typed [InputStream] value
             * instead. This method is primarily for setting the field to an undocumented or not yet
             * supported value.
             */
            fun archive(archive: MultipartField<InputStream>) = apply { this.archive = archive }

            /**
             * A .zip of the marketplace directory (its contents at the root, or wrapped in one
             * folder as a Git host's download produces), sent as a file part with a filename;
             * DEFLATE- or STORE-compressed, at most 32 MB. A part sent without a filename, a second
             * archive part, or any other form field is a 400; a larger archive is a 413.
             */
            fun archive(archive: ByteArray) = archive(archive.inputStream())

            /**
             * A .zip of the marketplace directory (its contents at the root, or wrapped in one
             * folder as a Git host's download produces), sent as a file part with a filename;
             * DEFLATE- or STORE-compressed, at most 32 MB. A part sent without a filename, a second
             * archive part, or any other form field is a 400; a larger archive is a 413.
             */
            fun archive(path: Path) =
                archive(
                    MultipartField.builder<InputStream>()
                        .value(PathInputStream(path))
                        .filename(path.name)
                        .build()
                )

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
             * .archive()
             * ```
             *
             * @throws IllegalStateException if any required field is unset.
             */
            fun build(): Body =
                Body(checkRequired("archive", archive), additionalProperties.toMutableMap())
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

            archive()
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
                archive == other.archive &&
                additionalProperties == other.additionalProperties
        }

        private val hashCode: Int by lazy { Objects.hash(archive, additionalProperties) }

        override fun hashCode(): Int = hashCode

        override fun toString() =
            "Body{archive=$archive, additionalProperties=$additionalProperties}"
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is PluginMarketplaceValidateArchiveParams &&
            betas == other.betas &&
            body == other.body &&
            additionalHeaders == other.additionalHeaders &&
            additionalQueryParams == other.additionalQueryParams
    }

    override fun hashCode(): Int =
        Objects.hash(betas, body, additionalHeaders, additionalQueryParams)

    override fun toString() =
        "PluginMarketplaceValidateArchiveParams{betas=$betas, body=$body, additionalHeaders=$additionalHeaders, additionalQueryParams=$additionalQueryParams}"
}
