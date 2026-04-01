package com.anthropic.aws.backends

import com.anthropic.aws.auth.AwsAuthUtil
import com.anthropic.aws.backends.AwsBackend.Companion.providerOf
import com.anthropic.backends.Backend
import com.anthropic.core.checkRequired
import com.anthropic.core.http.HttpRequest
import java.util.Optional
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials
import software.amazon.awssdk.auth.credentials.AwsCredentials
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider
import software.amazon.awssdk.auth.credentials.AwsSessionCredentials
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.regions.providers.DefaultAwsRegionProviderChain

/**
 * The AWS backend that manages the AWS credentials or API key required to access Anthropic's AI
 * models via the AWS API gateway and signs requests using AWS SigV4.
 *
 * Unless using an API key, requests are cryptographically signed using credentials issued by AWS.
 * These can be provided via system properties, environment variables, or other AWS facilities. They
 * can be resolved automatically by the default AWS provider chain by calling [Builder.fromEnv].
 * Alternatively, a custom AWS credentials provider can be configured on the builder.
 *
 * If using an API key, the key can be set directly, or provided via the `ANTHROPIC_AWS_API_KEY`
 * environment variable. If that variable is set, it will be resolved by [Builder.fromEnv] unless an
 * AWS credentials provider is specified explicitly to that method.
 *
 * Both the credentials (or API key) and the region can be resolved independently and passed to
 * [Builder.awsCredentials] (or [Builder.apiKey]) and [Builder.region] should an alternative method
 * of resolution be required.
 *
 * A workspace ID must be set via [Builder.workspaceId] or the `ANTHROPIC_AWS_WORKSPACE_ID`
 * environment variable. It is sent as the `anthropic-workspace-id` request header.
 *
 * See the AWS documentation for details on how to configure AWS credentials.
 */
class AwsBackend
private constructor(
    private val awsCredentialsProvider: AwsCredentialsProvider?,
    private val apiKey: String?,
    private val region: Region?,
    private val workspaceId: String?,
    private val skipAuth: Boolean,
    private val baseUrlOverride: String?,
) : Backend {

    fun awsCredentialsProvider(): Optional<AwsCredentialsProvider> =
        Optional.ofNullable(awsCredentialsProvider)

    fun apiKey(): Optional<String> = Optional.ofNullable(apiKey)

    fun region(): Optional<Region> = Optional.ofNullable(region)

    fun workspaceId(): Optional<String> = Optional.ofNullable(workspaceId)

    fun skipAuth(): Boolean = skipAuth

    companion object {
        /**
         * The AWS SigV4 service signing name for the Anthropic API gateway. This is distinct from
         * the Bedrock service signing name.
         */
        private const val SERVICE_SIGNING_NAME = "aws-external-anthropic"

        private const val ANTHROPIC_VERSION = "2023-06-01"
        private const val HEADER_VERSION = "anthropic-version"
        private const val HEADER_AUTHORIZATION = "authorization"
        private const val HEADER_WORKSPACE_ID = "anthropic-workspace-id"

        /**
         * The name of the environment variable that may hold the API key for authorization. If this
         * variable is set, it will take precedence over the AWS credentials resolved from all other
         * sources.
         */
        private const val ENV_API_KEY = "ANTHROPIC_AWS_API_KEY"

        private const val ENV_BASE_URL = "ANTHROPIC_AWS_BASE_URL"
        private const val ENV_WORKSPACE_ID = "ANTHROPIC_AWS_WORKSPACE_ID"

        @JvmStatic fun builder() = Builder()

        /**
         * Creates an AWS Backend configured to use the default AWS credentials provider. See
         * [Builder.fromEnv] for more details, or to configure a different provider.
         */
        @JvmStatic fun fromEnv() = builder().fromEnv().build()

        @JvmSynthetic
        internal fun providerOf(awsCredentials: AwsCredentials) =
            object : AwsCredentialsProvider {
                override fun resolveCredentials() = awsCredentials
            }
    }

    /**
     * Gets the AWS credentials resolved from the configured AWS credentials provider. Calling this
     * will result in an error if an API key was used instead of an AWS credentials provider.
     */
    fun awsCredentials(): AwsCredentials =
        awsCredentialsProvider?.resolveCredentials()
            ?: throw IllegalStateException("AWS credentials provider was not set.")

    override fun baseUrl(): String {
        if (baseUrlOverride != null) return baseUrlOverride
        checkNotNull(region) { "No region configured to derive base URL." }
        return "https://aws-external-anthropic.${region.id()}.api.aws"
    }

    override fun prepareRequest(request: HttpRequest): HttpRequest {
        require(!request.headers.names().contains(HEADER_VERSION)) {
            "Request already prepared for AWS."
        }

        val builder = request.toBuilder().putHeader(HEADER_VERSION, ANTHROPIC_VERSION)

        if (!skipAuth && workspaceId != null) {
            builder.putHeader(HEADER_WORKSPACE_ID, workspaceId)
        }

        return builder.build()
    }

    override fun authorizeRequest(request: HttpRequest): HttpRequest {
        if (skipAuth) {
            return request
        }

        require(!request.headers.names().contains(HEADER_AUTHORIZATION)) {
            "Request already authorized for AWS."
        }

        if (awsCredentialsProvider != null) {
            return authorizeRequestWithCredentials(request)
        }

        if (apiKey != null) {
            return authorizeRequestWithApiKey(request)
        }

        throw IllegalStateException("AWS credentials provider or API key must be set.")
    }

    private fun authorizeRequestWithCredentials(request: HttpRequest): HttpRequest =
        AwsAuthUtil.signRequestWithSigV4(
            request,
            awsCredentials(),
            checkRequired("region", region),
            SERVICE_SIGNING_NAME,
        )

    private fun authorizeRequestWithApiKey(request: HttpRequest): HttpRequest =
        AwsAuthUtil.authorizeWithApiKey(request, requireNotNull(apiKey) { "API key not set." })

    override fun close() {}

    /**
     * A builder for an [AwsBackend] used to connect an Anthropic client to the AWS API gateway
     * backend service.
     *
     * The AWS credentials and region can be extracted from the environment and set on the builder
     * by calling [fromEnv] before calling [build] to create the [AwsBackend]. Alternatively, set
     * the AWS credentials and region explicitly via [awsCredentials] and [region] before calling
     * [build]. A custom AWS credentials provider can be passed to [fromEnv] or
     * [awsCredentialsProvider].
     *
     * You should set _either_ the AWS credentials provider _or_ the API key, but not both. If both
     * are set, an error will occur.
     */
    class Builder internal constructor() {
        private var awsCredentialsProvider: AwsCredentialsProvider? = null
        private var apiKey: String? = null
        private var region: Region? = null
        private var workspaceId: String? = null
        private var skipAuth: Boolean = false
        private var baseUrlOverride: String? = null
        private var awsAccessKey: String? = null
        private var awsSecretAccessKey: String? = null
        private var awsSessionToken: String? = null
        private var awsProfile: String? = null

        /**
         * Resolves the AWS credentials from the environment, or other sources configured by the
         * credentials provider. If no provider is given, the AWS `DefaultCredentialsProvider` is
         * used, which is suitable for many use cases. See the AWS documentation for details. For
         * other use cases, pass a credentials provider configured as required. The provider,
         * whether given explicitly or by default, overrides any provider set via
         * [awsCredentialsProvider] or [awsCredentials].
         *
         * Auth resolution follows a 5-level precedence order:
         * 1. [apiKey] set directly on the builder → API key mode
         * 2. [awsAccessKey] + [awsSecretAccessKey] set on the builder → SigV4 with explicit creds
         * 3. [awsProfile] set on the builder → SigV4 with named profile
         * 4. `ANTHROPIC_AWS_API_KEY` environment variable → API key mode (only when no explicit
         *    auth constructor args were provided)
         * 5. Default AWS credential chain → SigV4
         *
         * The region is also resolved immediately using the default AWS region provider chain. Once
         * resolved here, the region will not be changed again.
         *
         * The workspace ID is resolved from the `ANTHROPIC_AWS_WORKSPACE_ID` environment variable
         * if set. The base URL is resolved from the `ANTHROPIC_AWS_BASE_URL` environment variable
         * if set.
         *
         * When called, this method will immediately attempt to resolve the AWS credentials (or API
         * key) and region. An error will occur if they cannot be resolved.
         *
         * @param awsCredentialsProvider The AWS credentials provider to use. If `null` and no
         *   explicit auth was configured on the builder, the `ANTHROPIC_AWS_API_KEY` environment
         *   variable is checked first; otherwise the default AWS credentials provider is used.
         */
        @JvmOverloads
        fun fromEnv(awsCredentialsProvider: AwsCredentialsProvider? = null) = apply {
            var effectiveCredentialsProvider = awsCredentialsProvider

            if (effectiveCredentialsProvider == null) {
                val hasExplicitAuth =
                    this.awsCredentialsProvider != null ||
                        this.apiKey != null ||
                        (this.awsAccessKey != null && this.awsSecretAccessKey != null) ||
                        this.awsProfile != null

                if (!hasExplicitAuth) {
                    val envApiKey = getEnv(ENV_API_KEY)

                    if (envApiKey != null) {
                        apiKey(envApiKey)
                    } else {
                        effectiveCredentialsProvider = DefaultCredentialsProvider.builder().build()
                    }
                }
            }

            if (effectiveCredentialsProvider != null) {
                awsCredentialsProvider(effectiveCredentialsProvider)

                try {
                    effectiveCredentialsProvider.resolveCredentials()
                } catch (e: Exception) {
                    throw IllegalStateException(
                        "No AWS access key ID or AWS secret access key found.",
                        e,
                    )
                }
            }

            if (region == null) {
                try {
                    region = DefaultAwsRegionProviderChain.builder().build().region
                } catch (e: Exception) {
                    if (baseUrlOverride == null) {
                        throw IllegalStateException("No AWS region found.", e)
                    }
                }
            }

            getEnv(ENV_WORKSPACE_ID)?.let { workspaceId = it }
            getEnv(ENV_BASE_URL)?.let { baseUrlOverride = it }
        }

        /**
         * Wraps access to system environment variables to allow mocking of environment variables
         * when testing.
         */
        @JvmSynthetic internal fun getEnv(name: String): String? = System.getenv(name)

        /**
         * Sets the AWS credentials provider that will be used to resolve credentials. Credentials
         * will not be resolved immediately. If misconfigured, an error may not be reported until
         * the first client request is made. To set a credentials provider _and_ confirm that it can
         * resolve credentials, call [fromEnv]. If called after [fromEnv], this overrides the
         * credentials provider configured by [fromEnv].
         */
        fun awsCredentialsProvider(awsCredentialsProvider: AwsCredentialsProvider) = apply {
            this.awsCredentialsProvider = awsCredentialsProvider
        }

        /**
         * Creates and sets an AWS credentials provider that provides only the given credentials.
         * This is a convenience for simple use cases, such as when testing. The provider overrides
         * any provider previously set by [awsCredentialsProvider] or [fromEnv].
         */
        fun awsCredentials(awsCredentials: AwsCredentials) =
            awsCredentialsProvider(providerOf(awsCredentials))

        /**
         * Sets the AWS access key ID for SigV4 signing. Must be paired with [awsSecretAccessKey].
         * Conflicts with [apiKey].
         */
        fun awsAccessKey(awsAccessKey: String) = apply { this.awsAccessKey = awsAccessKey }

        /**
         * Sets the AWS secret access key for SigV4 signing. Must be paired with [awsAccessKey].
         * Conflicts with [apiKey].
         */
        fun awsSecretAccessKey(awsSecretAccessKey: String) = apply {
            this.awsSecretAccessKey = awsSecretAccessKey
        }

        /**
         * Sets the AWS session token for SigV4 signing. Only applicable when both [awsAccessKey]
         * and [awsSecretAccessKey] are also set.
         */
        fun awsSessionToken(awsSessionToken: String?) = apply {
            this.awsSessionToken = awsSessionToken
        }

        /**
         * Sets a named AWS profile for credential resolution via SigV4 signing. Conflicts with
         * [apiKey].
         */
        fun awsProfile(awsProfile: String) = apply { this.awsProfile = awsProfile }

        /**
         * Sets the API key to use for authorization. This will be set automatically from the
         * `ANTHROPIC_AWS_API_KEY` environment variable if that variable is set when [fromEnv] is
         * called.
         */
        fun apiKey(apiKey: String) = apply { this.apiKey = apiKey }

        /**
         * Sets the AWS region to use when constructing the base URL for requests. Alternatively,
         * this may be resolved from the environment by calling [fromEnv]. If called after
         * [fromEnv], this overrides the region resolved by [fromEnv].
         */
        fun region(region: Region) = apply { this.region = region }

        /**
         * Sets the workspace ID to send as the `anthropic-workspace-id` request header.
         * Alternatively, this may be resolved from the `ANTHROPIC_AWS_WORKSPACE_ID` environment
         * variable by calling [fromEnv].
         */
        fun workspaceId(workspaceId: String) = apply { this.workspaceId = workspaceId }

        /**
         * Skips all authentication (both SigV4 and API key). When enabled, no auth headers are
         * added to requests and the workspace ID is not required. This is useful when requests go
         * through a gateway or proxy that handles authentication on the caller's behalf.
         */
        fun skipAuth(skipAuth: Boolean) = apply { this.skipAuth = skipAuth }

        /**
         * Overrides the base URL for requests. When set, this takes precedence over any region
         * derived URL. Alternatively, this may be resolved from the `ANTHROPIC_AWS_BASE_URL`
         * environment variable by calling [fromEnv].
         */
        fun baseUrl(baseUrl: String) = apply { this.baseUrlOverride = baseUrl }

        fun build(): AwsBackend {
            // Build a static credentials provider from explicit access key / secret key if set.
            if (
                awsCredentialsProvider == null && awsAccessKey != null && awsSecretAccessKey != null
            ) {
                val creds =
                    if (awsSessionToken != null)
                        AwsSessionCredentials.create(
                            awsAccessKey!!,
                            awsSecretAccessKey!!,
                            awsSessionToken!!,
                        )
                    else AwsBasicCredentials.create(awsAccessKey!!, awsSecretAccessKey!!)
                awsCredentialsProvider = providerOf(creds)
            }
            // Build a profile credentials provider if set.
            if (awsCredentialsProvider == null && awsProfile != null) {
                awsCredentialsProvider = ProfileCredentialsProvider.create(awsProfile!!)
            }

            if (!skipAuth) {
                if (awsCredentialsProvider != null && apiKey != null) {
                    throw IllegalStateException(
                        "An AWS credentials provider or an API key must be set, but not both."
                    )
                }
                if (awsCredentialsProvider == null && apiKey == null) {
                    throw IllegalStateException("No AWS credentials provider or API key was set.")
                }
                if (awsCredentialsProvider != null && region == null && baseUrlOverride == null) {
                    throw IllegalStateException(
                        "A region or base URL must be set when using AWS credentials."
                    )
                }
                if (apiKey != null && region == null && baseUrlOverride == null) {
                    throw IllegalStateException(
                        "A region or base URL must be set when using an API key."
                    )
                }
                checkNotNull(workspaceId) {
                    "No workspace ID found. Set workspaceId on the builder or the " +
                        "ANTHROPIC_AWS_WORKSPACE_ID environment variable."
                }
            }
            return AwsBackend(
                awsCredentialsProvider,
                apiKey,
                region,
                workspaceId,
                skipAuth,
                baseUrlOverride,
            )
        }
    }
}
