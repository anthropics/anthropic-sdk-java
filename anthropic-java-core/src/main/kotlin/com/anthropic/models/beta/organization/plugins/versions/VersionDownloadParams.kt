package com.anthropic.models.beta.organization.plugins.versions

import com.anthropic.core.Params
import com.anthropic.core.checkRequired
import com.anthropic.core.http.Headers
import com.anthropic.core.http.QueryParams
import com.anthropic.core.toImmutable
import com.anthropic.models.beta.AnthropicBeta
import java.util.Objects
import java.util.Optional
import kotlin.jvm.optionals.getOrNull

/**
 * Download one version's `.zip` archive, exactly as stored. Each download of a Plugin from a
 * member's personal plugin marketplace is recorded on the Compliance API activity feed.
 *
 * The response body is the archive (`Content-Type: application/zip`), sent as an attachment whose
 * filename is derived from the Plugin's name; name saved files from the IDs in the request path,
 * since that filename is not unique.
 *
 * **Accepted credentials:** an Admin API key with the `read:plugins` or `read:org_audit` scope, or
 * a Compliance Access Key with the `read:compliance_org_data` scope.
 *
 * Every read scope above (`read:plugins`, `read:org_audit`, and `read:compliance_org_data`) can
 * download the files of plugins in members' personal marketplaces, including files that claude.ai's
 * admin settings do not show, and a `read:org_audit` or `read:compliance_org_data` key created for
 * all of your parent organization's linked organizations can do this in any organization under it
 * that has access to this API, by passing `organization_id`. Each such download records a
 * `claude_plugin_archive_accessed` event on the Compliance API activity feed, identifying the key,
 * the plugin, the version, and the member. Downloads of organization-owned plugins are not
 * recorded.
 *
 * Every request must include the beta header `anthropic-beta: ce-plugins-2026-09-01`. A request
 * without it returns `404`, exactly as if the endpoint did not exist. The Plugins API is in beta
 * and is available to Claude Enterprise organizations only. It is not available to Claude Platform
 * (Claude Console) organizations, or to organizations with HIPAA readiness enabled.
 */
class VersionDownloadParams
private constructor(
    private val pluginId: String,
    private val version: String?,
    private val organizationId: String?,
    private val betas: List<AnthropicBeta>?,
    private val additionalHeaders: Headers,
    private val additionalQueryParams: QueryParams,
) : Params {

    /** ID of the Plugin (prefixed `plugin_`). */
    fun pluginId(): String = pluginId

    /** ID of the Plugin Version (prefixed `pluginver_`). `latest` is not accepted here. */
    fun version(): Optional<String> = Optional.ofNullable(version)

    /**
     * For a `read:org_audit` or `read:compliance_org_data` key created for all of a parent
     * organization's linked organizations: a child organization of that parent to read instead of
     * the organization the key was created in, given as the organization's UUID or its
     * `org_`-prefixed ID. A value that is neither returns a 400; an organization that is not a
     * child of the key's parent, or where the Plugins API is not available, returns a 404. Any
     * other key may pass only its own organization's ID here; another organization returns a 404.
     */
    fun organizationId(): Optional<String> = Optional.ofNullable(organizationId)

    /** This endpoint is in beta: requests must send `ce-plugins-2026-09-01` in this header. */
    fun betas(): Optional<List<AnthropicBeta>> = Optional.ofNullable(betas)

    /** Additional headers to send with the request. */
    fun _additionalHeaders(): Headers = additionalHeaders

    /** Additional query param to send with the request. */
    fun _additionalQueryParams(): QueryParams = additionalQueryParams

    fun toBuilder() = Builder().from(this)

    companion object {

        /**
         * Returns a mutable builder for constructing an instance of [VersionDownloadParams].
         *
         * The following fields are required:
         * ```java
         * .pluginId()
         * ```
         */
        @JvmStatic fun builder() = Builder()
    }

    /** A builder for [VersionDownloadParams]. */
    class Builder internal constructor() {

        private var pluginId: String? = null
        private var version: String? = null
        private var organizationId: String? = null
        private var betas: MutableList<AnthropicBeta>? = null
        private var additionalHeaders: Headers.Builder = Headers.builder()
        private var additionalQueryParams: QueryParams.Builder = QueryParams.builder()

        @JvmSynthetic
        internal fun from(versionDownloadParams: VersionDownloadParams) = apply {
            pluginId = versionDownloadParams.pluginId
            version = versionDownloadParams.version
            organizationId = versionDownloadParams.organizationId
            betas = versionDownloadParams.betas?.toMutableList()
            additionalHeaders = versionDownloadParams.additionalHeaders.toBuilder()
            additionalQueryParams = versionDownloadParams.additionalQueryParams.toBuilder()
        }

        /** ID of the Plugin (prefixed `plugin_`). */
        fun pluginId(pluginId: String) = apply { this.pluginId = pluginId }

        /** ID of the Plugin Version (prefixed `pluginver_`). `latest` is not accepted here. */
        fun version(version: String?) = apply { this.version = version }

        /** Alias for calling [Builder.version] with `version.orElse(null)`. */
        fun version(version: Optional<String>) = version(version.getOrNull())

        /**
         * For a `read:org_audit` or `read:compliance_org_data` key created for all of a parent
         * organization's linked organizations: a child organization of that parent to read instead
         * of the organization the key was created in, given as the organization's UUID or its
         * `org_`-prefixed ID. A value that is neither returns a 400; an organization that is not a
         * child of the key's parent, or where the Plugins API is not available, returns a 404. Any
         * other key may pass only its own organization's ID here; another organization returns
         * a 404.
         */
        fun organizationId(organizationId: String?) = apply { this.organizationId = organizationId }

        /** Alias for calling [Builder.organizationId] with `organizationId.orElse(null)`. */
        fun organizationId(organizationId: Optional<String>) =
            organizationId(organizationId.getOrNull())

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
         * Returns an immutable instance of [VersionDownloadParams].
         *
         * Further updates to this [Builder] will not mutate the returned instance.
         *
         * The following fields are required:
         * ```java
         * .pluginId()
         * ```
         *
         * @throws IllegalStateException if any required field is unset.
         */
        fun build(): VersionDownloadParams =
            VersionDownloadParams(
                checkRequired("pluginId", pluginId),
                version,
                organizationId,
                betas?.toImmutable(),
                additionalHeaders.build(),
                additionalQueryParams.build(),
            )
    }

    fun _pathParam(index: Int): String =
        when (index) {
            0 -> pluginId
            1 -> version ?: ""
            else -> ""
        }

    override fun _headers(): Headers =
        Headers.builder()
            .apply {
                betas?.let {
                    if (it.isNotEmpty()) {
                        put(
                            "anthropic-beta",
                            it.map { it.toString() }
                                .let { it + (listOf("ce-plugins-2026-09-01") - it) }
                                .joinToString(","),
                        )
                    }
                }
                putAll(additionalHeaders)
            }
            .build()

    override fun _queryParams(): QueryParams =
        QueryParams.builder()
            .apply {
                organizationId?.let { put("organization_id", it) }
                putAll(additionalQueryParams)
            }
            .build()

    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }

        return other is VersionDownloadParams &&
            pluginId == other.pluginId &&
            version == other.version &&
            organizationId == other.organizationId &&
            betas == other.betas &&
            additionalHeaders == other.additionalHeaders &&
            additionalQueryParams == other.additionalQueryParams
    }

    override fun hashCode(): Int =
        Objects.hash(
            pluginId,
            version,
            organizationId,
            betas,
            additionalHeaders,
            additionalQueryParams,
        )

    override fun toString() =
        "VersionDownloadParams{pluginId=$pluginId, version=$version, organizationId=$organizationId, betas=$betas, additionalHeaders=$additionalHeaders, additionalQueryParams=$additionalQueryParams}"
}
