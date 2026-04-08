package com.anthropic.bedrock.backends

import com.anthropic.aws.auth.AwsAuthUtil
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
 * The Bedrock Mantle backend that manages the AWS credentials or API key required to access
 * Anthropic's AI models via the Bedrock Mantle service and signs requests using AWS SigV4.
 *
 * Only the Messages API (`/v1/messages`) and its subpaths are supported on Bedrock Mantle.
 *
 * Unless using an API key, requests are cryptographically signed using credentials issued by AWS.
 * These can be provided via system properties, environment variables, or other AWS facilities. They
 * can be resolved automatically by the default AWS provider chain by calling [Builder.fromEnv].
 * Alternatively, a custom AWS credentials provider can be configured on the builder.
 *
 * If using an API key, the key can be set directly, or provided via the `AWS_BEARER_TOKEN_BEDROCK`
 * environment variable (falls back to `ANTHROPIC_AWS_API_KEY`).
 *
 * Both the credentials (or API key) and the region can be resolved independently and passed to
 * [Builder.awsCredentials] (or [Builder.apiKey]) and [Builder.region] should an alternative method
 * of resolution be required.
 *
 * See the AWS documentation for details on how to configure AWS credentials.
 */
class BedrockMantleBackend
private constructor(
    private val awsCredentialsProvider: AwsCredentialsProvider?,
    private val apiKey: String?,
    private val region: Region?,
    private val skipAuth: Boolean,
    private val baseUrlOverride: String?,
) : Backend {

    fun awsCredentialsProvider(): Optional<AwsCredentialsProvider> =
        Optional.ofNullable(awsCredentialsProvider)

    fun apiKey(): Optional<String> = Optional.ofNullable(apiKey)

    fun region(): Optional<Region> = Optional.ofNullable(region)

    fun skipAuth(): Boolean = skipAuth

    companion object {
        /** The AWS SigV4 service signing name for the Bedrock Mantle service. */
        private const val SERVICE_SIGNING_NAME = "bedrock-mantle"

        private const val ANTHROPIC_VERSION = "2023-06-01"
        private const val HEADER_VERSION = "anthropic-version"
        private const val HEADER_AUTHORIZATION = "authorization"
        private const val ENV_API_KEY = "AWS_BEARER_TOKEN_BEDROCK"
        private const val ENV_API_KEY_FALLBACK = "ANTHROPIC_AWS_API_KEY"
        private const val ENV_BASE_URL = "ANTHROPIC_BEDROCK_MANTLE_BASE_URL"

        @JvmStatic fun builder() = Builder()

        @JvmStatic fun fromEnv() = builder().fromEnv().build()

        @JvmSynthetic
        internal fun providerOf(awsCredentials: AwsCredentials) =
            object : AwsCredentialsProvider {
                override fun resolveCredentials() = awsCredentials
            }
    }

    fun awsCredentials(): AwsCredentials =
        awsCredentialsProvider?.resolveCredentials()
            ?: throw IllegalStateException("AWS credentials provider was not set.")

    override fun baseUrl(): String {
        if (baseUrlOverride != null) return baseUrlOverride
        checkNotNull(region) { "No region configured to derive base URL." }
        return "https://bedrock-mantle.${region.id()}.api.aws/anthropic"
    }

    override fun prepareRequest(request: HttpRequest): HttpRequest {
        require(!request.headers.names().contains(HEADER_VERSION)) {
            "Request already prepared for Bedrock Mantle."
        }

        return request.toBuilder().putHeader(HEADER_VERSION, ANTHROPIC_VERSION).build()
    }

    override fun authorizeRequest(request: HttpRequest): HttpRequest {
        if (skipAuth) {
            return request
        }

        require(!request.headers.names().contains(HEADER_AUTHORIZATION)) {
            "Request already authorized for Bedrock Mantle."
        }

        if (awsCredentialsProvider != null) {
            return AwsAuthUtil.signRequestWithSigV4(
                request,
                awsCredentials(),
                checkRequired("region", region),
                SERVICE_SIGNING_NAME,
            )
        }

        if (apiKey != null) {
            return AwsAuthUtil.authorizeWithApiKey(request, apiKey)
        }

        throw IllegalStateException("AWS credentials provider or API key must be set.")
    }

    override fun close() {}

    /**
     * A builder for a [BedrockMantleBackend] used to connect an Anthropic client to the Bedrock
     * Mantle backend service.
     *
     * The AWS credentials and region can be extracted from the environment and set on the builder
     * by calling [fromEnv] before calling [build] to create the [BedrockMantleBackend].
     * Alternatively, set the AWS credentials and region explicitly via [awsCredentials] and
     * [region] before calling [build]. A custom AWS credentials provider can be passed to [fromEnv]
     * or [awsCredentialsProvider].
     *
     * You should set _either_ the AWS credentials provider _or_ the API key, but not both. If both
     * are set, an error will occur.
     */
    class Builder internal constructor() {
        private var awsCredentialsProvider: AwsCredentialsProvider? = null
        private var apiKey: String? = null
        private var region: Region? = null
        private var skipAuth: Boolean = false
        private var baseUrlOverride: String? = null
        private var awsAccessKey: String? = null
        private var awsSecretAccessKey: String? = null
        private var awsSessionToken: String? = null
        private var awsProfile: String? = null

        /**
         * Resolves the AWS credentials from the environment, or other sources configured by the
         * credentials provider.
         *
         * Auth resolution follows a 5-level precedence order:
         * 1. [apiKey] set directly on the builder -> API key mode
         * 2. [awsAccessKey] + [awsSecretAccessKey] set on the builder -> SigV4 with explicit creds
         * 3. [awsProfile] set on the builder -> SigV4 with named profile
         * 4. `AWS_BEARER_TOKEN_BEDROCK` env var (falls back to `ANTHROPIC_AWS_API_KEY`) -> API key
         *    mode (only when no explicit auth constructor args were provided)
         * 5. Default AWS credential chain -> SigV4
         *
         * The base URL is resolved from `ANTHROPIC_BEDROCK_MANTLE_BASE_URL`.
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
                    val envApiKey = getEnv(ENV_API_KEY) ?: getEnv(ENV_API_KEY_FALLBACK)

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

            getEnv(ENV_BASE_URL)?.let { baseUrlOverride = it }
        }

        @JvmSynthetic internal fun getEnv(name: String): String? = System.getenv(name)

        fun awsCredentialsProvider(awsCredentialsProvider: AwsCredentialsProvider) = apply {
            this.awsCredentialsProvider = awsCredentialsProvider
        }

        fun awsCredentials(awsCredentials: AwsCredentials) =
            awsCredentialsProvider(providerOf(awsCredentials))

        fun awsAccessKey(awsAccessKey: String) = apply { this.awsAccessKey = awsAccessKey }

        fun awsSecretAccessKey(awsSecretAccessKey: String) = apply {
            this.awsSecretAccessKey = awsSecretAccessKey
        }

        fun awsSessionToken(awsSessionToken: String?) = apply {
            this.awsSessionToken = awsSessionToken
        }

        fun awsProfile(awsProfile: String) = apply { this.awsProfile = awsProfile }

        fun apiKey(apiKey: String) = apply { this.apiKey = apiKey }

        fun region(region: Region) = apply { this.region = region }

        fun skipAuth(skipAuth: Boolean) = apply { this.skipAuth = skipAuth }

        fun baseUrl(baseUrl: String) = apply { this.baseUrlOverride = baseUrl }

        fun build(): BedrockMantleBackend {
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
            }
            return BedrockMantleBackend(
                awsCredentialsProvider,
                apiKey,
                region,
                skipAuth,
                baseUrlOverride,
            )
        }
    }
}
