package com.anthropic.googlecloud.backends

import com.anthropic.backends.Backend
import com.anthropic.core.http.HttpRequest
import com.google.auth.oauth2.GoogleCredentials
import com.google.auth.oauth2.ServiceAccountCredentials
import java.util.Optional
import java.util.function.Supplier

/**
 * The backend for Claude Platform on Google Cloud that manages the Google OAuth2 credentials
 * required to access Anthropic's AI models via the Google Cloud API gateway and authorizes requests
 * with a Google OAuth2 bearer token.
 *
 * Google Cloud requires authentication credentials issued by Google. These can be provided via
 * environment variables or other Google facilities such as the Google Cloud CLI. The application
 * default credentials (ADC) can be resolved by calling [Builder.fromEnv]. For other types of
 * credentials, create them separately and supply them to [Builder.googleCredentials], or provide a
 * bearer-token supplier to [Builder.bearerTokenSupplier].
 *
 * A workspace ID must be set via [Builder.workspaceId] or the `ANTHROPIC_GOOGLE_CLOUD_WORKSPACE_ID`
 * environment variable. It is embedded in the path of the derived base URL.
 *
 * See the Google Cloud documentation for details on how to configure Google credentials.
 */
class GoogleCloudBackend
private constructor(
    private val googleCredentials: GoogleCredentials?,
    private val bearerTokenSupplier: Supplier<String>?,
    private val project: String?,
    private val location: String?,
    private val workspaceId: String?,
    private val skipAuth: Boolean,
    private val baseUrlOverride: String?,
) : Backend {

    fun googleCredentials(): Optional<GoogleCredentials> = Optional.ofNullable(googleCredentials)

    fun bearerTokenSupplier(): Optional<Supplier<String>> = Optional.ofNullable(bearerTokenSupplier)

    fun project(): Optional<String> = Optional.ofNullable(project)

    fun location(): Optional<String> = Optional.ofNullable(location)

    fun workspaceId(): Optional<String> = Optional.ofNullable(workspaceId)

    fun skipAuth(): Boolean = skipAuth

    fun toBuilder(): Builder = Builder().from(this)

    companion object {
        private const val ANTHROPIC_VERSION = "2023-06-01"
        private const val HEADER_VERSION = "anthropic-version"
        private const val HEADER_AUTHORIZATION = "Authorization"

        private const val ENV_PROJECT = "ANTHROPIC_GOOGLE_CLOUD_PROJECT"
        private const val ENV_LOCATION = "ANTHROPIC_GOOGLE_CLOUD_LOCATION"
        private const val ENV_WORKSPACE_ID = "ANTHROPIC_GOOGLE_CLOUD_WORKSPACE_ID"
        private const val ENV_BASE_URL = "ANTHROPIC_GOOGLE_CLOUD_BASE_URL"

        /**
         * The de-facto standard Google Cloud project environment variable, consulted only when
         * [ENV_PROJECT] is not set.
         */
        private const val ENV_PROJECT_FALLBACK = "GOOGLE_CLOUD_PROJECT"

        @JvmSynthetic internal const val SCOPE = "https://www.googleapis.com/auth/cloud-platform"

        private const val DEFAULT_LOCATION = "global"

        private const val URL_TEMPLATE =
            "https://claude.googleapis.com/v1alpha" +
                "/projects/{project}/locations/{location}/workspaces/{workspaceId}/invoke"

        @JvmStatic fun builder() = Builder()

        /**
         * Creates a Google Cloud backend configured from environment variables and application
         * default credentials. See [Builder.fromEnv] for details.
         */
        @JvmStatic fun fromEnv(): GoogleCloudBackend = builder().fromEnv().build()
    }

    override fun baseUrl(): String {
        if (baseUrlOverride != null) return baseUrlOverride
        checkNotNull(project) { "No project configured to derive base URL." }
        checkNotNull(location) { "No location configured to derive base URL." }
        checkNotNull(workspaceId) { "No workspace ID configured to derive base URL." }
        return URL_TEMPLATE.replace("{project}", project)
            .replace("{location}", location)
            .replace("{workspaceId}", workspaceId)
    }

    override fun prepareRequest(request: HttpRequest): HttpRequest {
        val builder = request.toBuilder()

        // User-supplied headers (e.g. set by an interceptor) win over the defaults.
        if (!request.headers.names().contains(HEADER_VERSION)) {
            builder.putHeader(HEADER_VERSION, ANTHROPIC_VERSION)
        }

        return builder.build()
    }

    override fun authorizeRequest(request: HttpRequest): HttpRequest {
        if (skipAuth) {
            return request
        }

        // Authorization already provided (e.g. by an interceptor) wins over backend credentials.
        if (request.headers.names().contains(HEADER_AUTHORIZATION)) {
            return request
        }

        val token =
            if (bearerTokenSupplier != null) {
                // Call `bearerTokenSupplier.get()` _each_ time to allow the implementation of the
                // supplier to refresh the token as necessary.
                bearerTokenSupplier.get()
            } else if (googleCredentials != null) {
                googleCredentials.refreshIfExpired()
                googleCredentials.accessToken.tokenValue
            } else {
                error("Google credentials or bearer token supplier must be set.")
            }

        return request.toBuilder().putHeader(HEADER_AUTHORIZATION, "Bearer $token").build()
    }

    override fun close() {}

    /**
     * A builder for a [GoogleCloudBackend] used to connect an Anthropic client to the Google Cloud
     * API gateway backend service.
     *
     * The Google credentials, project, location and workspace ID can be resolved from the
     * environment by calling [fromEnv] before calling [build]. Alternatively, set them explicitly
     * via [googleCredentials] (or [bearerTokenSupplier]), [project], [location] and [workspaceId]
     * before calling [build].
     *
     * If both [bearerTokenSupplier] and [googleCredentials] are set, the bearer token supplier
     * takes precedence and the Google credentials are not consulted.
     */
    class Builder internal constructor() {
        private var googleCredentials: GoogleCredentials? = null
        private var bearerTokenSupplier: Supplier<String>? = null
        private var project: String? = null
        private var location: String? = null
        private var workspaceId: String? = null
        private var skipAuth: Boolean = false
        private var baseUrlOverride: String? = null

        @JvmSynthetic
        internal fun from(backend: GoogleCloudBackend) = apply {
            googleCredentials = backend.googleCredentials
            bearerTokenSupplier = backend.bearerTokenSupplier
            project = backend.project
            location = backend.location
            workspaceId = backend.workspaceId
            skipAuth = backend.skipAuth
            baseUrlOverride = backend.baseUrlOverride
        }

        /**
         * Resolves configuration from environment variables and, when no credentials have been set
         * explicitly, from Google application default credentials.
         *
         * Values already set on the builder are kept; only unset values are filled from the
         * environment, so explicit setters always win regardless of call order.
         *
         * Environment variables consulted:
         * - `ANTHROPIC_GOOGLE_CLOUD_PROJECT` (falling back to `GOOGLE_CLOUD_PROJECT`)
         * - `ANTHROPIC_GOOGLE_CLOUD_LOCATION`
         * - `ANTHROPIC_GOOGLE_CLOUD_WORKSPACE_ID`
         * - `ANTHROPIC_GOOGLE_CLOUD_BASE_URL`
         *
         * @throws IllegalStateException If application default credentials are required (no
         *   [googleCredentials], [bearerTokenSupplier], or [skipAuth] set) and cannot be resolved.
         */
        fun fromEnv() = apply {
            if (project == null) {
                project = getEnv(ENV_PROJECT) ?: getEnv(ENV_PROJECT_FALLBACK)
            }
            if (location == null) {
                location = getEnv(ENV_LOCATION)
            }
            if (workspaceId == null) {
                workspaceId = getEnv(ENV_WORKSPACE_ID)
            }
            if (baseUrlOverride == null) {
                baseUrlOverride = getEnv(ENV_BASE_URL)
            }

            if (!skipAuth && googleCredentials == null && bearerTokenSupplier == null) {
                googleCredentials =
                    try {
                        resolveApplicationDefault()
                    } catch (e: Exception) {
                        throw IllegalStateException(
                            "Google OAuth2 credentials could not be resolved. Set " +
                                "googleCredentials or bearerTokenSupplier on the builder, or " +
                                "configure application default credentials.",
                            e,
                        )
                    }
            }
        }

        /**
         * Wraps access to system environment variables to allow mocking of environment variables
         * when testing.
         */
        @JvmSynthetic internal fun getEnv(name: String): String? = System.getenv(name)

        /**
         * Wraps resolution of Google application default credentials to allow mocking when testing.
         */
        @JvmSynthetic
        internal fun resolveApplicationDefault(): GoogleCredentials =
            GoogleCredentials.getApplicationDefault().createScoped(SCOPE)

        /**
         * Sets the Google credentials that will be used to authorize requests. Calling
         * `refreshIfExpired()` on these credentials before each request keeps the access token
         * fresh. Ignored when a [bearerTokenSupplier] is also set. Conflicts with [skipAuth].
         */
        fun googleCredentials(googleCredentials: GoogleCredentials) = apply {
            this.googleCredentials = googleCredentials
        }

        /**
         * Sets a bearer token supplier that will be responsible for supplying authorization tokens
         * when authorizing requests. `Supplier.get()` will be called for each request, allowing the
         * supplier to implement its own token caching/refreshing logic. Takes precedence over
         * [googleCredentials] when both are set. Conflicts with [skipAuth].
         */
        fun bearerTokenSupplier(bearerTokenSupplier: Supplier<String>) = apply {
            this.bearerTokenSupplier = bearerTokenSupplier
        }

        /**
         * Sets the Google Cloud project ID. Alternatively, this may be resolved from the
         * `ANTHROPIC_GOOGLE_CLOUD_PROJECT` (or `GOOGLE_CLOUD_PROJECT`) environment variable, or
         * from service-account credentials, by calling [fromEnv].
         */
        fun project(project: String) = apply { this.project = project }

        /**
         * Sets the Google Cloud location used to construct the base URL. Defaults to `"global"`
         * when not set. Alternatively, this may be resolved from the
         * `ANTHROPIC_GOOGLE_CLOUD_LOCATION` environment variable by calling [fromEnv].
         */
        fun location(location: String) = apply { this.location = location }

        /**
         * Sets the workspace ID used to construct the base URL. Alternatively, this may be resolved
         * from the `ANTHROPIC_GOOGLE_CLOUD_WORKSPACE_ID` environment variable by calling [fromEnv].
         */
        fun workspaceId(workspaceId: String) = apply { this.workspaceId = workspaceId }

        /**
         * Skips all authentication. When enabled, no auth headers are added to requests. This is
         * useful when requests go through a gateway or proxy that handles authentication on the
         * caller's behalf. A workspace ID is still required to derive the base URL unless [baseUrl]
         * is set explicitly. Conflicts with [googleCredentials] and [bearerTokenSupplier].
         */
        fun skipAuth(skipAuth: Boolean) = apply { this.skipAuth = skipAuth }

        /**
         * Overrides the base URL for requests. When set, this takes precedence over any URL derived
         * from [project] and [location]. Alternatively, this may be resolved from the
         * `ANTHROPIC_GOOGLE_CLOUD_BASE_URL` environment variable by calling [fromEnv].
         */
        fun baseUrl(baseUrl: String) = apply { this.baseUrlOverride = baseUrl }

        fun build(): GoogleCloudBackend {
            // Auto-tier: derive the project from service-account credentials when nothing else
            // supplied one.
            var resolvedProject = project
            if (resolvedProject == null) {
                val credentials = googleCredentials
                if (credentials is ServiceAccountCredentials) {
                    resolvedProject = credentials.projectId
                }
            }

            if (skipAuth) {
                check(googleCredentials == null && bearerTokenSupplier == null) {
                    "`skipAuth` cannot be set together with `googleCredentials` or " +
                        "`bearerTokenSupplier`."
                }
            } else {
                check(googleCredentials != null || bearerTokenSupplier != null) {
                    "No Google credentials found. Set googleCredentials or bearerTokenSupplier " +
                        "on the builder, or configure application default credentials and call " +
                        "fromEnv()."
                }
                checkNotNull(workspaceId) {
                    "Workspace ID is required; set workspaceId or the $ENV_WORKSPACE_ID " +
                        "environment variable."
                }
            }

            if (baseUrlOverride == null) {
                checkNotNull(resolvedProject) {
                    "No project found. Set project on the builder, set the $ENV_PROJECT " +
                        "environment variable, or configure application default credentials " +
                        "with a project."
                }
                checkNotNull(workspaceId) {
                    "Workspace ID is required; set workspaceId or the $ENV_WORKSPACE_ID " +
                        "environment variable."
                }
            }

            return GoogleCloudBackend(
                googleCredentials,
                bearerTokenSupplier,
                resolvedProject,
                location ?: DEFAULT_LOCATION,
                workspaceId,
                skipAuth,
                baseUrlOverride,
            )
        }
    }
}
