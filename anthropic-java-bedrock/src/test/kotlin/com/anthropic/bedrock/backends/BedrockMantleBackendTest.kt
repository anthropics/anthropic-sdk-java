package com.anthropic.bedrock.backends

import com.anthropic.core.http.HttpMethod
import com.anthropic.core.http.HttpRequest
import com.anthropic.core.http.json
import com.anthropic.core.jsonMapper
import com.fasterxml.jackson.databind.node.ObjectNode
import java.lang.System.clearProperty
import java.lang.System.setProperty
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatNoException
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.condition.DisabledIf
import org.junit.jupiter.api.parallel.ResourceLock
import org.mockito.Mockito
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials
import software.amazon.awssdk.auth.credentials.AwsSessionCredentials
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.regions.providers.DefaultAwsRegionProviderChain

@ResourceLock("environment")
internal class BedrockMantleBackendTest {
    companion object {
        private const val API_KEY = "test-api-key"
        private const val AWS_ACCESS_KEY_ID = "AKIAIOSFODNN7EXAMPLE"
        private const val AWS_SECRET_ACCESS_KEY = "wJalrXUtnFEMI/K7MDENG/bPxRfiCYEXAMPLEKEY"
        private const val AWS_SESSION_TOKEN = "FwoGZXIvYXdzEJr..."
        private const val AWS_REGION = "us-east-1"

        private const val PROP_AWS_ACCESS_KEY_ID = "aws.accessKeyId"
        private const val PROP_AWS_SECRET_ACCESS_KEY = "aws.secretAccessKey"
        private const val PROP_AWS_SESSION_TOKEN = "aws.sessionToken"
        private const val PROP_AWS_REGION = "aws.region"

        private const val ENV_API_KEY = "AWS_BEARER_TOKEN_BEDROCK"
        private const val ENV_API_KEY_FALLBACK = "ANTHROPIC_AWS_API_KEY"

        private val hasRegionInEnvironment by lazy {
            try {
                DefaultAwsRegionProviderChain.builder().build().region
                true
            } catch (_: Exception) {
                false
            }
        }

        private val hasCredentialsInEnvironment by lazy {
            try {
                DefaultCredentialsProvider.builder().build().resolveCredentials()
                true
            } catch (_: Exception) {
                false
            }
        }
    }

    @BeforeEach
    fun setUp() {
        clearEnv()
    }

    @AfterEach
    fun tearDown() {
        clearEnv()
    }

    // --- Base URL tests ---

    @Test
    fun baseUrl() {
        initEnv()
        val backend = BedrockMantleBackend.builder().withoutApiKeyEnvVar().fromEnv().build()

        assertThat(backend.baseUrl())
            .isEqualTo("https://bedrock-mantle.$AWS_REGION.api.aws/anthropic")
    }

    @Test
    fun baseUrlOtherRegion() {
        initEnv()
        val otherRegion = "eu-west-1"
        setProperty(PROP_AWS_REGION, otherRegion)
        val backend = BedrockMantleBackend.builder().withoutApiKeyEnvVar().fromEnv().build()

        assertThat(backend.baseUrl())
            .isEqualTo("https://bedrock-mantle.$otherRegion.api.aws/anthropic")
    }

    @Test
    fun baseUrlOverride() {
        val backend =
            BedrockMantleBackend.builder()
                .apiKey(API_KEY)
                .baseUrl("https://custom.example.com")
                .build()

        assertThat(backend.baseUrl()).isEqualTo("https://custom.example.com")
    }

    // --- Credential tests ---

    @Test
    fun awsSessionCredentialsFromEnv() {
        initEnv()
        val backend = BedrockMantleBackend.builder().withoutApiKeyEnvVar().fromEnv().build()

        assertThat(backend.awsCredentials()).isExactlyInstanceOf(AwsSessionCredentials::class.java)
        assertThat(backend.awsCredentials().accessKeyId()).isEqualTo(AWS_ACCESS_KEY_ID)
        assertThat(backend.awsCredentials().secretAccessKey()).isEqualTo(AWS_SECRET_ACCESS_KEY)
    }

    @Test
    fun awsCredentialsExplicitWithRegion() {
        val backend =
            BedrockMantleBackend.builder()
                .awsCredentials(
                    AwsBasicCredentials.create(AWS_ACCESS_KEY_ID, AWS_SECRET_ACCESS_KEY)
                )
                .region(Region.EU_WEST_1)
                .build()

        assertThat(backend.awsCredentials().accessKeyId()).isEqualTo(AWS_ACCESS_KEY_ID)
        assertThat(backend.region()).hasValue(Region.EU_WEST_1)
    }

    @Test
    fun awsCredentialsExplicitWithoutRegionOrBaseUrl() {
        assertThatThrownBy {
                BedrockMantleBackend.builder()
                    .awsCredentials(
                        AwsBasicCredentials.create(AWS_ACCESS_KEY_ID, AWS_SECRET_ACCESS_KEY)
                    )
                    .build()
            }
            .isExactlyInstanceOf(IllegalStateException::class.java)
            .hasMessageContaining("region or base URL")
    }

    @Test
    fun awsAccessKeyAndSecretKeyExplicit() {
        val backend =
            BedrockMantleBackend.builder()
                .awsAccessKey(AWS_ACCESS_KEY_ID)
                .awsSecretAccessKey(AWS_SECRET_ACCESS_KEY)
                .region(Region.US_EAST_1)
                .build()

        assertThat(backend.awsCredentials()).isExactlyInstanceOf(AwsBasicCredentials::class.java)
        assertThat(backend.awsCredentials().accessKeyId()).isEqualTo(AWS_ACCESS_KEY_ID)
        assertThat(backend.apiKey()).isEmpty
    }

    @Test
    fun awsAccessKeyAndSecretKeyWithSessionTokenExplicit() {
        val backend =
            BedrockMantleBackend.builder()
                .awsAccessKey(AWS_ACCESS_KEY_ID)
                .awsSecretAccessKey(AWS_SECRET_ACCESS_KEY)
                .awsSessionToken(AWS_SESSION_TOKEN)
                .region(Region.US_EAST_1)
                .build()

        assertThat(backend.awsCredentials()).isExactlyInstanceOf(AwsSessionCredentials::class.java)
        assertThat((backend.awsCredentials() as AwsSessionCredentials).sessionToken())
            .isEqualTo(AWS_SESSION_TOKEN)
    }

    @Test
    fun awsProfileExplicit() {
        val backend =
            BedrockMantleBackend.builder().awsProfile("default").region(Region.US_EAST_1).build()

        assertThat(backend.awsCredentialsProvider()).isPresent
        assertThat(backend.apiKey()).isEmpty
    }

    // --- API key tests ---

    @Test
    fun apiKeyFromMantleEnv() {
        initEnv()
        val backend = BedrockMantleBackend.builder().withMantleApiKeyEnvVar().fromEnv().build()

        assertThat(backend.apiKey()).hasValue(API_KEY)
        assertThat(backend.region().get().toString()).isEqualTo(AWS_REGION)
    }

    @Test
    fun apiKeyFallbackToAwsEnv() {
        initEnv()
        val backend = BedrockMantleBackend.builder().withAwsFallbackApiKeyEnvVar().fromEnv().build()

        assertThat(backend.apiKey()).hasValue("aws-fallback-key")
    }

    @Test
    fun apiKeyMantleEnvOverridesAwsFallback() {
        initEnv()
        val builder = Mockito.spy(BedrockMantleBackend.builder())
        Mockito.doReturn(API_KEY).`when`(builder).getEnv(ENV_API_KEY)
        Mockito.doReturn("aws-fallback-key").`when`(builder).getEnv(ENV_API_KEY_FALLBACK)
        val backend = builder.fromEnv().build()

        assertThat(backend.apiKey()).hasValue(API_KEY)
    }

    @Test
    fun apiKeySetBeforeFromEnvIsRespected() {
        initEnv()
        val backend =
            BedrockMantleBackend.builder().withoutApiKeyEnvVar().apiKey(API_KEY).fromEnv().build()

        assertThat(backend.apiKey()).hasValue(API_KEY)
        assertThat(backend.awsCredentialsProvider()).isEmpty
    }

    @Test
    fun apiKeyClashWithAwsCredentialsProvider() {
        assertThatThrownBy {
                BedrockMantleBackend.builder()
                    .awsCredentialsProvider(DefaultCredentialsProvider.builder().build())
                    .apiKey(API_KEY)
                    .build()
            }
            .isExactlyInstanceOf(IllegalStateException::class.java)
            .hasMessage("An AWS credentials provider or an API key must be set, but not both.")
    }

    @Test
    fun apiKeySetSoNoCredentialsProviderToAccessCredentials() {
        val backend =
            BedrockMantleBackend.builder().apiKey(API_KEY).region(Region.EU_WEST_1).build()

        assertThat(backend.apiKey()).hasValue(API_KEY)
        assertThatThrownBy { backend.awsCredentials() }
            .isExactlyInstanceOf(IllegalStateException::class.java)
            .hasMessage("AWS credentials provider was not set.")
    }

    // --- Auth tests ---

    @Test
    fun authorizeRequestWithApiKey() {
        val backend =
            BedrockMantleBackend.builder().apiKey(API_KEY).region(Region.EU_WEST_1).build()
        val request = createRequest()
        val authorizedRequest = backend.authorizeRequest(request)

        assertThat(authorizedRequest.headers.values("authorization")[0])
            .isEqualTo("Bearer $API_KEY")
    }

    @Test
    fun authorizeRequestWithCredentials() {
        val backend =
            BedrockMantleBackend.builder()
                .awsAccessKey(AWS_ACCESS_KEY_ID)
                .awsSecretAccessKey(AWS_SECRET_ACCESS_KEY)
                .region(Region.US_EAST_1)
                .build()

        val request = createRequest()
        val authorized = backend.authorizeRequest(request)

        assertThat(authorized.headers.values("authorization")[0]).startsWith("AWS4-HMAC-SHA256")
        assertThat(authorized.headers.values("authorization")[0]).contains("bedrock-mantle")
        assertThat(authorized.headers.values("x-amz-date")).isNotEmpty()
    }

    @Test
    fun authorizeRequestWithCredentialsIncludesQueryParams() {
        val backend =
            BedrockMantleBackend.builder()
                .awsAccessKey(AWS_ACCESS_KEY_ID)
                .awsSecretAccessKey(AWS_SECRET_ACCESS_KEY)
                .region(Region.US_EAST_1)
                .build()

        val requestWithoutParams = createRequest()
        // In the real OkHttp flow, resolveUrl() folds query params into baseUrl before
        // authorizeRequest is called. Simulate that by putting them directly in the URL.
        val requestWithParams =
            HttpRequest.builder()
                .method(HttpMethod.POST)
                .baseUrl("https://bedrock-mantle.us-east-1.api.aws/anthropic/v1/messages?foo=bar")
                .body(json(jsonMapper(), jsonMapper().readValue("{}", ObjectNode::class.java)))
                .build()

        val authWithoutParams = backend.authorizeRequest(requestWithoutParams)
        val authWithParams = backend.authorizeRequest(requestWithParams)

        assertThat(authWithoutParams.headers.values("authorization")[0])
            .startsWith("AWS4-HMAC-SHA256")
        assertThat(authWithParams.headers.values("authorization")[0]).startsWith("AWS4-HMAC-SHA256")
        assertThat(authWithParams.headers.values("authorization")[0])
            .isNotEqualTo(authWithoutParams.headers.values("authorization")[0])
    }

    @Test
    fun authorizeRequestAlreadyAuthorized() {
        initEnv()
        val backend = BedrockMantleBackend.builder().withoutApiKeyEnvVar().fromEnv().build()
        val request =
            HttpRequest.builder()
                .method(HttpMethod.POST)
                .baseUrl("https://bedrock-mantle.us-east-1.api.aws/anthropic")
                .putHeader("content-type", "application/json")
                .putHeader("authorization", "Bearer already")
                .build()

        assertThatThrownBy { backend.authorizeRequest(request) }
            .isExactlyInstanceOf(IllegalArgumentException::class.java)
            .hasMessageStartingWith("Request already authorized for Bedrock Mantle")
    }

    // --- Prepare request tests ---

    @Test
    fun prepareRequestAddsVersionHeader() {
        val backend =
            BedrockMantleBackend.builder().apiKey(API_KEY).region(Region.US_EAST_1).build()
        val request = createRequest()
        val prepared = backend.prepareRequest(request)

        assertThat(prepared.headers.values("anthropic-version")).containsExactly("2023-06-01")
    }

    @Test
    fun prepareRequestAlreadyPrepared() {
        val backend =
            BedrockMantleBackend.builder().apiKey(API_KEY).region(Region.US_EAST_1).build()
        val request = createRequest()
        val prepared = backend.prepareRequest(request)

        assertThatThrownBy { backend.prepareRequest(prepared) }
            .isExactlyInstanceOf(IllegalArgumentException::class.java)
            .hasMessageStartingWith("Request already prepared for Bedrock Mantle")
    }

    // --- Skip auth tests ---

    @Test
    fun skipAuthBuildWithoutCredentials() {
        val backend =
            BedrockMantleBackend.builder()
                .skipAuth(true)
                .baseUrl("https://bedrock-mantle.us-east-1.api.aws/anthropic")
                .build()

        assertThat(backend.skipAuth()).isTrue()
        assertThat(backend.awsCredentialsProvider()).isEmpty
        assertThat(backend.apiKey()).isEmpty
    }

    @Test
    fun skipAuthNoAuthHeaders() {
        val backend =
            BedrockMantleBackend.builder()
                .skipAuth(true)
                .baseUrl("https://bedrock-mantle.us-east-1.api.aws/anthropic")
                .build()

        val request = createRequest()
        val authorized = backend.authorizeRequest(request)

        assertThat(authorized.headers.names()).doesNotContain("authorization")
    }

    // --- Builder validation tests ---

    @Test
    fun builderMissingCredentials() {
        assertThatThrownBy { BedrockMantleBackend.builder().build() }
            .isExactlyInstanceOf(IllegalStateException::class.java)
            .hasMessageStartingWith("No AWS credentials provider or API key was set.")
    }

    @Test
    @DisabledIf("hasRegionInEnvironment")
    fun fromEnvWithNoRegion() {
        initEnv(
            isSetAccessKeyID = true,
            isSetSecretAccessKey = true,
            isSetSessionToken = true,
            isSetRegion = false,
        )
        assertThatThrownBy { BedrockMantleBackend.builder().fromEnv().build() }
            .isExactlyInstanceOf(IllegalStateException::class.java)
            .hasMessage("No AWS region found.")
    }

    @Test
    fun closeIsNoOp() {
        initEnv()
        val backend = BedrockMantleBackend.builder().withoutApiKeyEnvVar().fromEnv().build()

        assertThatNoException().isThrownBy { backend.close() }
    }

    // --- Helpers ---

    private fun initEnv() {
        initEnv(
            isSetAccessKeyID = true,
            isSetSecretAccessKey = true,
            isSetSessionToken = true,
            isSetRegion = true,
        )
    }

    private fun initEnv(
        isSetAccessKeyID: Boolean,
        isSetSecretAccessKey: Boolean,
        isSetSessionToken: Boolean,
        isSetRegion: Boolean,
    ) {
        if (isSetAccessKeyID) setProperty(PROP_AWS_ACCESS_KEY_ID, AWS_ACCESS_KEY_ID)
        else clearProperty(PROP_AWS_ACCESS_KEY_ID)

        if (isSetSecretAccessKey) setProperty(PROP_AWS_SECRET_ACCESS_KEY, AWS_SECRET_ACCESS_KEY)
        else clearProperty(PROP_AWS_SECRET_ACCESS_KEY)

        if (isSetSessionToken) setProperty(PROP_AWS_SESSION_TOKEN, AWS_SESSION_TOKEN)
        else clearProperty(PROP_AWS_SESSION_TOKEN)

        if (isSetRegion) setProperty(PROP_AWS_REGION, AWS_REGION)
        else clearProperty(PROP_AWS_REGION)
    }

    private fun clearEnv() {
        initEnv(
            isSetAccessKeyID = false,
            isSetSecretAccessKey = false,
            isSetSessionToken = false,
            isSetRegion = false,
        )
    }

    private fun BedrockMantleBackend.Builder.withMantleApiKeyEnvVar():
        BedrockMantleBackend.Builder {
        val builder = Mockito.spy(this)
        Mockito.doReturn(API_KEY).`when`(builder).getEnv(ENV_API_KEY)
        Mockito.doReturn(null).`when`(builder).getEnv(ENV_API_KEY_FALLBACK)
        return builder
    }

    private fun BedrockMantleBackend.Builder.withAwsFallbackApiKeyEnvVar():
        BedrockMantleBackend.Builder {
        val builder = Mockito.spy(this)
        Mockito.doReturn(null).`when`(builder).getEnv(ENV_API_KEY)
        Mockito.doReturn("aws-fallback-key").`when`(builder).getEnv(ENV_API_KEY_FALLBACK)
        return builder
    }

    private fun BedrockMantleBackend.Builder.withoutApiKeyEnvVar(): BedrockMantleBackend.Builder {
        val builder = Mockito.spy(this)
        Mockito.doReturn(null).`when`(builder).getEnv(ENV_API_KEY)
        Mockito.doReturn(null).`when`(builder).getEnv(ENV_API_KEY_FALLBACK)
        return builder
    }

    @Suppress("unused") private fun hasRegionInEnvironment() = hasRegionInEnvironment

    @Suppress("unused") private fun hasCredentialsInEnvironment() = hasCredentialsInEnvironment

    private fun createRequest(): HttpRequest =
        HttpRequest.builder()
            .method(HttpMethod.POST)
            .baseUrl("https://bedrock-mantle.us-east-1.api.aws/anthropic/v1/messages")
            .body(json(jsonMapper(), jsonMapper().readValue("{}", ObjectNode::class.java)))
            .build()
}
