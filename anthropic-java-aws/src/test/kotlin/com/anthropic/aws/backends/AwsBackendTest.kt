package com.anthropic.aws.backends

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
internal class AwsBackendTest {
    companion object {
        private const val API_KEY = "test-api-key"
        private const val AWS_ACCESS_KEY_ID = "AKIAIOSFODNN7EXAMPLE"
        private const val AWS_SECRET_ACCESS_KEY = "wJalrXUtnFEMI/K7MDENG/bPxRfiCYEXAMPLEKEY"
        private const val AWS_SESSION_TOKEN = "FwoGZXIvYXdzEJr..."
        private const val AWS_REGION = "us-east-1"
        private const val WORKSPACE_ID = "ws-12345"

        private const val PROP_AWS_ACCESS_KEY_ID = "aws.accessKeyId"
        private const val PROP_AWS_SECRET_ACCESS_KEY = "aws.secretAccessKey"
        private const val PROP_AWS_SESSION_TOKEN = "aws.sessionToken"
        private const val PROP_AWS_REGION = "aws.region"

        private const val ENV_API_KEY = "ANTHROPIC_AWS_API_KEY"
        private const val ENV_WORKSPACE_ID = "ANTHROPIC_AWS_WORKSPACE_ID"

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

    @Test
    fun awsSessionCredentialsFromEnv() {
        initEnv()
        val backend = AwsBackend.builder().withoutApiKeyEnvVar().fromEnv().build()

        assertThat(backend.awsCredentials()).isExactlyInstanceOf(AwsSessionCredentials::class.java)
        assertThat(backend.awsCredentials().accessKeyId()).isEqualTo(AWS_ACCESS_KEY_ID)
        assertThat(backend.awsCredentials().secretAccessKey()).isEqualTo(AWS_SECRET_ACCESS_KEY)
        assertThat((backend.awsCredentials() as AwsSessionCredentials).sessionToken())
            .isEqualTo(AWS_SESSION_TOKEN)
    }

    @Test
    fun awsCredentialsExplicitWithoutRegionOrBaseUrl() {
        assertThatThrownBy {
                AwsBackend.builder()
                    .awsCredentials(
                        AwsBasicCredentials.create(AWS_ACCESS_KEY_ID, AWS_SECRET_ACCESS_KEY)
                    )
                    .build()
            }
            .isExactlyInstanceOf(IllegalStateException::class.java)
            .hasMessageContaining("region or base URL")
    }

    @Test
    fun awsCredentialsExplicitWithRegion() {
        val backend =
            AwsBackend.builder()
                .awsCredentials(
                    AwsBasicCredentials.create(AWS_ACCESS_KEY_ID, AWS_SECRET_ACCESS_KEY)
                )
                .region(Region.EU_WEST_1)
                .workspaceId(WORKSPACE_ID)
                .build()

        assertThat(backend.awsCredentials().accessKeyId()).isEqualTo(AWS_ACCESS_KEY_ID)
        assertThat(backend.awsCredentials().secretAccessKey()).isEqualTo(AWS_SECRET_ACCESS_KEY)
        assertThat(backend.region()).hasValue(Region.EU_WEST_1)
    }

    @Test
    fun awsCredentialsProviderExplicitToFromEnvWithRegion() {
        initEnv()
        val backend =
            AwsBackend.builder()
                .withApiKeyEnvVar()
                .fromEnv(
                    AwsBackend.providerOf(
                        AwsBasicCredentials.create(
                            "Alt:$AWS_ACCESS_KEY_ID",
                            "Alt:$AWS_SECRET_ACCESS_KEY",
                        )
                    )
                )
                .build()

        assertThat(backend.awsCredentials()).isExactlyInstanceOf(AwsBasicCredentials::class.java)
        assertThat(backend.awsCredentials().accessKeyId()).isEqualTo("Alt:$AWS_ACCESS_KEY_ID")
        assertThat(backend.awsCredentials().secretAccessKey())
            .isEqualTo("Alt:$AWS_SECRET_ACCESS_KEY")
        assertThat(backend.region().get().toString()).isEqualTo(AWS_REGION)
    }

    @Test
    @DisabledIf("hasCredentialsInEnvironment")
    fun awsCredentialsProviderExplicitToFromEnvButNotResolvedWithRegion() {
        initEnv(
            isSetAccessKeyID = false,
            isSetSecretAccessKey = false,
            isSetSessionToken = false,
            isSetRegion = true,
        )
        assertThatThrownBy {
                AwsBackend.builder().fromEnv(DefaultCredentialsProvider.builder().build()).build()
            }
            .isExactlyInstanceOf(IllegalStateException::class.java)
            .hasMessage("No AWS access key ID or AWS secret access key found.")
    }

    @Test
    @DisabledIf("hasRegionInEnvironment")
    fun awsCredentialsProviderViaFromEnvWithNoRegion() {
        initEnv(
            isSetAccessKeyID = true,
            isSetSecretAccessKey = true,
            isSetSessionToken = true,
            isSetRegion = false,
        )
        assertThatThrownBy { AwsBackend.builder().fromEnv().build() }
            .isExactlyInstanceOf(IllegalStateException::class.java)
            .hasMessage("No AWS region found.")
    }

    @Test
    fun awsCredentialsProviderClashWithApiKey() {
        initEnv()
        assertThatThrownBy {
                AwsBackend.builder()
                    .apiKey(API_KEY)
                    .awsCredentialsProvider(DefaultCredentialsProvider.builder().build())
                    .build()
            }
            .isExactlyInstanceOf(IllegalStateException::class.java)
            .hasMessage("An AWS credentials provider or an API key must be set, but not both.")
    }

    @Test
    fun apiKeySetBeforeFromEnvIsRespected() {
        initEnv()
        val backend = AwsBackend.builder().withoutApiKeyEnvVar().apiKey(API_KEY).fromEnv().build()

        assertThat(backend.apiKey()).hasValue(API_KEY)
        assertThat(backend.awsCredentialsProvider()).isEmpty
        assertThat(backend.region().get().toString()).isEqualTo(AWS_REGION)
    }

    @Test
    fun apiKeyClashWithAwsCredentialsProvider() {
        initEnv()
        assertThatThrownBy {
                AwsBackend.builder()
                    .awsCredentialsProvider(DefaultCredentialsProvider.builder().build())
                    .apiKey(API_KEY)
                    .build()
            }
            .isExactlyInstanceOf(IllegalStateException::class.java)
            .hasMessage("An AWS credentials provider or an API key must be set, but not both.")
    }

    @Test
    fun credentialsProviderSetBeforeFromEnvIgnoresApiKeyEnvVar() {
        initEnv()
        val backend =
            AwsBackend.builder()
                .withApiKeyEnvVar()
                .awsCredentialsProvider(
                    AwsBackend.providerOf(
                        AwsBasicCredentials.create(AWS_ACCESS_KEY_ID, AWS_SECRET_ACCESS_KEY)
                    )
                )
                .fromEnv()
                .build()

        assertThat(backend.awsCredentials().accessKeyId()).isEqualTo(AWS_ACCESS_KEY_ID)
        assertThat(backend.apiKey()).isEmpty
        assertThat(backend.region().get().toString()).isEqualTo(AWS_REGION)
    }

    @Test
    fun apiKeySetSoNoCredentialsProviderToAccessCredentials() {
        val backend =
            AwsBackend.builder()
                .apiKey(API_KEY)
                .region(Region.EU_WEST_1)
                .workspaceId(WORKSPACE_ID)
                .build()

        assertThat(backend.apiKey()).hasValue(API_KEY)
        assertThat(backend.awsCredentialsProvider()).isEmpty

        assertThatThrownBy { backend.awsCredentials() }
            .isExactlyInstanceOf(IllegalStateException::class.java)
            .hasMessage("AWS credentials provider was not set.")
    }

    @Test
    fun awsCredentialsProviderExplicitWithRegion() {
        val backend =
            AwsBackend.builder()
                .awsCredentialsProvider(
                    AwsBackend.providerOf(
                        AwsBasicCredentials.create(AWS_ACCESS_KEY_ID, AWS_SECRET_ACCESS_KEY)
                    )
                )
                .region(Region.EU_WEST_1)
                .workspaceId(WORKSPACE_ID)
                .build()

        assertThat(backend.awsCredentials()).isExactlyInstanceOf(AwsBasicCredentials::class.java)
        assertThat(backend.awsCredentials().accessKeyId()).isEqualTo(AWS_ACCESS_KEY_ID)
        assertThat(backend.awsCredentials().secretAccessKey()).isEqualTo(AWS_SECRET_ACCESS_KEY)
        assertThat(backend.region()).hasValue(Region.EU_WEST_1)
    }

    @Test
    fun apiKeyFromEnv() {
        initEnv()
        val backend = AwsBackend.builder().withApiKeyEnvVar().fromEnv().build()

        assertThat(backend.apiKey()).hasValue(API_KEY)
        assertThat(backend.region().get().toString()).isEqualTo(AWS_REGION)
    }

    @Test
    fun regionPresent() {
        initEnv()
        val backend = AwsBackend.builder().withoutApiKeyEnvVar().fromEnv().build()

        assertThat(backend.awsCredentials()).isExactlyInstanceOf(AwsSessionCredentials::class.java)
        assertThat(backend.region().get().toString()).isEqualTo(AWS_REGION)
    }

    @Test
    fun builderMissingCredentials() {
        initEnv()
        assertThatThrownBy { AwsBackend.builder().build() }
            .isExactlyInstanceOf(IllegalStateException::class.java)
            .hasMessageStartingWith("No AWS credentials provider or API key was set.")
    }

    @Test
    fun regionExplicitWithoutAwsCredentials() {
        assertThatThrownBy { AwsBackend.builder().region(Region.US_EAST_1).build() }
            .isExactlyInstanceOf(IllegalStateException::class.java)
            .hasMessage("No AWS credentials provider or API key was set.")
    }

    @Test
    fun baseUrl() {
        initEnv()
        val backend = AwsBackend.builder().withoutApiKeyEnvVar().fromEnv().build()

        assertThat(backend.awsCredentials()).isExactlyInstanceOf(AwsSessionCredentials::class.java)
        assertThat(backend.baseUrl())
            .isEqualTo("https://aws-external-anthropic.$AWS_REGION.api.aws")
    }

    @Test
    fun baseUrlOtherRegion() {
        initEnv()
        val otherRegion = "eu-west-1"
        setProperty(PROP_AWS_REGION, otherRegion)
        val backend = AwsBackend.builder().withoutApiKeyEnvVar().fromEnv().build()

        assertThat(AWS_REGION).isNotEqualTo(otherRegion)
        assertThat(backend.baseUrl())
            .isEqualTo("https://aws-external-anthropic.$otherRegion.api.aws")
    }

    @Test
    fun baseUrlOverride() {
        val backend =
            AwsBackend.builder()
                .apiKey(API_KEY)
                .workspaceId(WORKSPACE_ID)
                .baseUrl("https://custom.example.com")
                .build()

        assertThat(backend.baseUrl()).isEqualTo("https://custom.example.com")
    }

    @Test
    fun workspaceIdSetOnPrepareRequest() {
        val backend =
            AwsBackend.builder()
                .apiKey(API_KEY)
                .region(Region.US_EAST_1)
                .workspaceId(WORKSPACE_ID)
                .build()

        val request = createRequest()
        val prepared = backend.prepareRequest(request)

        assertThat(prepared.headers.values("anthropic-workspace-id")).containsExactly(WORKSPACE_ID)
    }

    @Test
    fun workspaceIdRequiredWhenNotSet() {
        assertThatThrownBy { AwsBackend.builder().apiKey(API_KEY).region(Region.US_EAST_1).build() }
            .isExactlyInstanceOf(IllegalStateException::class.java)
            .hasMessageContaining("workspace ID")
    }

    @Test
    fun workspaceIdFromEnv() {
        initEnv()
        val builder = Mockito.spy(AwsBackend.builder())
        Mockito.doReturn(API_KEY).`when`(builder).getEnv(ENV_API_KEY)
        Mockito.doReturn(WORKSPACE_ID).`when`(builder).getEnv("ANTHROPIC_AWS_WORKSPACE_ID")
        val backend = builder.fromEnv().build()

        assertThat(backend.workspaceId()).hasValue(WORKSPACE_ID)
    }

    @Test
    fun authorizeRequestAlreadyAuthorized() {
        initEnv()
        val backend = AwsBackend.builder().withoutApiKeyEnvVar().fromEnv().build()
        val request =
            HttpRequest.builder()
                .method(HttpMethod.POST)
                .baseUrl("https://aws-external-anthropic.us-east-1.api.aws")
                .putHeader("content-type", "application/json")
                .putHeader("authorization", "Bearer already")
                .build()

        assertThatThrownBy { backend.authorizeRequest(request) }
            .isExactlyInstanceOf(IllegalArgumentException::class.java)
            .hasMessageStartingWith("Request already authorized for AWS")
    }

    @Test
    fun authorizeRequestWithApiKey() {
        val backend =
            AwsBackend.builder()
                .apiKey(API_KEY)
                .region(Region.EU_WEST_1)
                .workspaceId(WORKSPACE_ID)
                .build()
        val request =
            HttpRequest.builder()
                .method(HttpMethod.POST)
                .baseUrl("https://aws-external-anthropic.eu-west-1.api.aws")
                .addPathSegment("v1")
                .addPathSegment("messages")
                .putHeader("X-Test", "header-value")
                .build()
        val authorizedRequest = backend.authorizeRequest(request)

        assertThat(authorizedRequest.headers.names().contains("X-Test")).isTrue
        assertThat(authorizedRequest.headers.names().contains("authorization")).isTrue
        assertThat(authorizedRequest.headers.values("authorization")[0])
            .isEqualTo("Bearer $API_KEY")
    }

    @Test
    fun authorizeRequestWithCredentials() {
        val backend =
            AwsBackend.builder()
                .awsAccessKey(AWS_ACCESS_KEY_ID)
                .awsSecretAccessKey(AWS_SECRET_ACCESS_KEY)
                .region(Region.US_EAST_1)
                .workspaceId(WORKSPACE_ID)
                .build()

        val request = createRequest()
        val authorized = backend.authorizeRequest(request)

        assertThat(authorized.headers.values("authorization")[0]).startsWith("AWS4-HMAC-SHA256")
        assertThat(authorized.headers.values("x-amz-date")).isNotEmpty()
    }

    @Test
    fun authorizeRequestWithCredentialsIncludesQueryParams() {
        val backend =
            AwsBackend.builder()
                .awsAccessKey(AWS_ACCESS_KEY_ID)
                .awsSecretAccessKey(AWS_SECRET_ACCESS_KEY)
                .region(Region.US_EAST_1)
                .workspaceId(WORKSPACE_ID)
                .build()

        val requestWithoutParams = createRequest()
        // In the real OkHttp flow, resolveUrl() folds query params into baseUrl before
        // authorizeRequest is called. Simulate that by putting them directly in the URL.
        val requestWithParams =
            HttpRequest.builder()
                .method(HttpMethod.POST)
                .baseUrl("https://aws-external-anthropic.us-east-1.api.aws/v1/messages?foo=bar")
                .body(json(jsonMapper(), jsonMapper().readValue("{}", ObjectNode::class.java)))
                .build()

        val authWithoutParams = backend.authorizeRequest(requestWithoutParams)
        val authWithParams = backend.authorizeRequest(requestWithParams)

        // Both should produce valid SigV4 signatures.
        assertThat(authWithoutParams.headers.values("authorization")[0])
            .startsWith("AWS4-HMAC-SHA256")
        assertThat(authWithParams.headers.values("authorization")[0]).startsWith("AWS4-HMAC-SHA256")

        // The signatures must differ because query params are included in signing.
        assertThat(authWithParams.headers.values("authorization")[0])
            .isNotEqualTo(authWithoutParams.headers.values("authorization")[0])
    }

    @Test
    fun closeIsNoOp() {
        initEnv()
        val backend = AwsBackend.builder().withoutApiKeyEnvVar().fromEnv().build()

        assertThatNoException().isThrownBy { backend.close() }
    }

    @Test
    fun prepareRequestAddsVersionHeader() {
        val backend =
            AwsBackend.builder()
                .apiKey(API_KEY)
                .region(Region.US_EAST_1)
                .workspaceId(WORKSPACE_ID)
                .build()
        val request = createRequest()
        val prepared = backend.prepareRequest(request)

        assertThat(prepared.headers.values("anthropic-version")).containsExactly("2023-06-01")
    }

    @Test
    fun prepareRequestAlreadyPrepared() {
        val backend =
            AwsBackend.builder()
                .apiKey(API_KEY)
                .region(Region.US_EAST_1)
                .workspaceId(WORKSPACE_ID)
                .build()
        val request = createRequest()
        val prepared = backend.prepareRequest(request)

        assertThatThrownBy { backend.prepareRequest(prepared) }
            .isExactlyInstanceOf(IllegalArgumentException::class.java)
            .hasMessageStartingWith("Request already prepared for AWS")
    }

    @Test
    fun awsAccessKeyAndSecretKeyExplicit() {
        val backend =
            AwsBackend.builder()
                .awsAccessKey(AWS_ACCESS_KEY_ID)
                .awsSecretAccessKey(AWS_SECRET_ACCESS_KEY)
                .region(Region.US_EAST_1)
                .workspaceId(WORKSPACE_ID)
                .build()

        assertThat(backend.awsCredentials()).isExactlyInstanceOf(AwsBasicCredentials::class.java)
        assertThat(backend.awsCredentials().accessKeyId()).isEqualTo(AWS_ACCESS_KEY_ID)
        assertThat(backend.awsCredentials().secretAccessKey()).isEqualTo(AWS_SECRET_ACCESS_KEY)
        assertThat(backend.apiKey()).isEmpty
    }

    @Test
    fun awsAccessKeyAndSecretKeyWithSessionTokenExplicit() {
        val backend =
            AwsBackend.builder()
                .awsAccessKey(AWS_ACCESS_KEY_ID)
                .awsSecretAccessKey(AWS_SECRET_ACCESS_KEY)
                .awsSessionToken(AWS_SESSION_TOKEN)
                .region(Region.US_EAST_1)
                .workspaceId(WORKSPACE_ID)
                .build()

        assertThat(backend.awsCredentials()).isExactlyInstanceOf(AwsSessionCredentials::class.java)
        assertThat(backend.awsCredentials().accessKeyId()).isEqualTo(AWS_ACCESS_KEY_ID)
        assertThat((backend.awsCredentials() as AwsSessionCredentials).sessionToken())
            .isEqualTo(AWS_SESSION_TOKEN)
    }

    @Test
    fun awsProfileExplicit() {
        val backend =
            AwsBackend.builder()
                .awsProfile("default")
                .region(Region.US_EAST_1)
                .workspaceId(WORKSPACE_ID)
                .build()

        assertThat(backend.awsCredentialsProvider()).isPresent
        assertThat(backend.apiKey()).isEmpty
    }

    @Test
    fun fromEnvWithExplicitAccessKeysSuppressesApiKeyEnvVar() {
        initEnv()
        val backend =
            AwsBackend.builder()
                .awsAccessKey(AWS_ACCESS_KEY_ID)
                .awsSecretAccessKey(AWS_SECRET_ACCESS_KEY)
                .withApiKeyEnvVar()
                .fromEnv()
                .build()

        assertThat(backend.awsCredentials().accessKeyId()).isEqualTo(AWS_ACCESS_KEY_ID)
        assertThat(backend.apiKey()).isEmpty
    }

    @Test
    fun fromEnvWithExplicitProfileSuppressesApiKeyEnvVar() {
        initEnv()
        val backend =
            AwsBackend.builder().awsProfile("default").withApiKeyEnvVar().fromEnv().build()

        assertThat(backend.awsCredentialsProvider()).isPresent
        assertThat(backend.apiKey()).isEmpty
    }

    @Test
    fun skipAuthBuildWithoutCredentialsOrWorkspaceId() {
        val backend =
            AwsBackend.builder()
                .skipAuth(true)
                .baseUrl("https://aws-external-anthropic.us-east-1.api.aws")
                .build()

        assertThat(backend.skipAuth()).isTrue()
        assertThat(backend.awsCredentialsProvider()).isEmpty
        assertThat(backend.apiKey()).isEmpty
        assertThat(backend.workspaceId()).isEmpty
    }

    @Test
    fun skipAuthNoAuthHeaders() {
        val backend =
            AwsBackend.builder()
                .skipAuth(true)
                .baseUrl("https://aws-external-anthropic.us-east-1.api.aws")
                .build()

        val request = createRequest()
        val authorized = backend.authorizeRequest(request)

        assertThat(authorized.headers.names()).doesNotContain("authorization")
    }

    @Test
    fun skipAuthNoWorkspaceIdHeader() {
        val backend =
            AwsBackend.builder()
                .skipAuth(true)
                .baseUrl("https://aws-external-anthropic.us-east-1.api.aws")
                .build()

        val request = createRequest()
        val prepared = backend.prepareRequest(request)

        assertThat(prepared.headers.values("anthropic-version")).containsExactly("2023-06-01")
        assertThat(prepared.headers.names()).doesNotContain("anthropic-workspace-id")
    }

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

    private fun AwsBackend.Builder.withApiKeyEnvVar(): AwsBackend.Builder {
        val builder = Mockito.spy(this)
        Mockito.doReturn(API_KEY).`when`(builder).getEnv(ENV_API_KEY)
        Mockito.doReturn(WORKSPACE_ID).`when`(builder).getEnv(ENV_WORKSPACE_ID)
        return builder
    }

    private fun AwsBackend.Builder.withoutApiKeyEnvVar(): AwsBackend.Builder {
        val builder = Mockito.spy(this)
        Mockito.doReturn(null).`when`(builder).getEnv(ENV_API_KEY)
        Mockito.doReturn(WORKSPACE_ID).`when`(builder).getEnv(ENV_WORKSPACE_ID)
        return builder
    }

    @Suppress("unused") private fun hasRegionInEnvironment() = hasRegionInEnvironment

    @Suppress("unused") private fun hasCredentialsInEnvironment() = hasCredentialsInEnvironment

    private fun createRequest(): HttpRequest =
        HttpRequest.builder()
            .method(HttpMethod.POST)
            .baseUrl("https://aws-external-anthropic.us-east-1.api.aws/v1/messages")
            .body(json(jsonMapper(), jsonMapper().readValue("{}", ObjectNode::class.java)))
            .build()
}
