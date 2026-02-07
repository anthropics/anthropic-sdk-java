package com.anthropic.bedrock.backends

import com.anthropic.core.http.HttpMethod
import com.anthropic.core.http.HttpRequest
import com.anthropic.core.http.bodyToJson
import com.anthropic.core.http.json
import com.anthropic.core.jsonMapper
import com.anthropic.errors.AnthropicException
import com.anthropic.errors.AnthropicInvalidDataException
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
internal class BedrockBackendTest {
    companion object {
        private const val API_KEY = "AKIAIOSFODNN7EXAMPLE"
        private const val AWS_ACCESS_KEY_ID = "AKIAIOSFODNN7EXAMPLE"
        private const val AWS_SECRET_ACCESS_KEY = "wJalrXUtnFEMI/K7MDENG/bPxRfiCYEXAMPLEKEY"
        private const val AWS_SESSION_TOKEN = "FwoGZXIvYXdzEJr..."
        private const val AWS_REGION = "us-east-1"
        private const val MODEL_ID = "anthropic.claude-3-5-sonnet-20240620-v1:0"

        // The names of the system properties recognised by AWS when resolving
        // credentials. The AWS SDK does not provide public constants for these
        // property names (see "software.amazon.awssdk.core.SdkSystemSetting").
        private const val PROP_AWS_ACCESS_KEY_ID = "aws.accessKeyId"
        private const val PROP_AWS_SECRET_ACCESS_KEY = "aws.secretAccessKey"
        private const val PROP_AWS_SESSION_TOKEN = "aws.sessionToken"
        private const val PROP_AWS_REGION = "aws.region"

        private const val ENV_API_KEY = "AWS_BEARER_TOKEN_BEDROCK"

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
        // Ensure a clean state before any `@DisabledIf` annotation is processed for the _next_
        // test, as that annotation is processed before `@BeforeEach fun setUp()` is invoked.
        clearEnv()
    }

    @Test
    fun awsSessionCredentialsFromEnv() {
        initEnv()
        val backend = BedrockBackend.builder().withoutApiKeyEnvVar().fromEnv().build()

        // When the session token is present, the provided credentials should
        // have a different type to the type when it is absent.
        assertThat(backend.awsCredentials).isExactlyInstanceOf(AwsSessionCredentials::class.java)

        assertThat(backend.awsCredentials.accessKeyId()).isEqualTo(AWS_ACCESS_KEY_ID)
        assertThat(backend.awsCredentials.secretAccessKey()).isEqualTo(AWS_SECRET_ACCESS_KEY)
        assertThat((backend.awsCredentials as AwsSessionCredentials).sessionToken())
            .isEqualTo(AWS_SESSION_TOKEN)
    }

    @Test
    fun awsCredentialsExplicitWithoutRegion() {
        assertThatThrownBy {
                BedrockBackend.builder()
                    .awsCredentials(
                        AwsBasicCredentials.create(AWS_ACCESS_KEY_ID, AWS_SECRET_ACCESS_KEY)
                    )
                    .build()
            }
            .isExactlyInstanceOf(IllegalStateException::class.java)
            .hasMessageContaining("region")
    }

    @Test
    fun awsCredentialsExplicitWithRegion() {
        val backend =
            BedrockBackend.builder()
                .awsCredentials(
                    AwsBasicCredentials.create(AWS_ACCESS_KEY_ID, AWS_SECRET_ACCESS_KEY)
                )
                .region(Region.EU_WEST_1)
                .build()

        assertThat(backend.awsCredentials.accessKeyId()).isEqualTo(AWS_ACCESS_KEY_ID)
        assertThat(backend.awsCredentials.secretAccessKey()).isEqualTo(AWS_SECRET_ACCESS_KEY)
        assertThat(backend.region).isEqualTo(Region.EU_WEST_1)
    }

    @Test
    fun awsCredentialsProviderExplicitToFromEnvWithRegion() {
        initEnv()
        // Set the API key (mock) environment variable. It should be ignored when the explicit
        // credentials provider is given.
        val backend =
            BedrockBackend.builder()
                .withApiKeyEnvVar()
                .fromEnv(
                    BedrockBackend.providerOf(
                        AwsBasicCredentials.create(
                            "Alt:$AWS_ACCESS_KEY_ID",
                            "Alt:$AWS_SECRET_ACCESS_KEY",
                        )
                    )
                )
                .build()

        assertThat(backend.awsCredentials).isExactlyInstanceOf(AwsBasicCredentials::class.java)
        assertThat(backend.awsCredentials.accessKeyId()).isEqualTo("Alt:$AWS_ACCESS_KEY_ID")
        assertThat(backend.awsCredentials.secretAccessKey()).isEqualTo("Alt:$AWS_SECRET_ACCESS_KEY")
        assertThat(backend.region.toString()).isEqualTo(AWS_REGION)
    }

    @Test
    // Skip test if AWS credentials are defined in the environment (either in environment variables
    // or an AWS configuration), otherwise the test will fail as it expects nothing to be set.
    @DisabledIf("hasCredentialsInEnvironment")
    fun awsCredentialsProviderExplicitToFromEnvButNotResolvedWithRegion() {
        initEnv(
            isSetAccessKeyID = false,
            isSetSecretAccessKey = false,
            isSetSessionToken = false,
            isSetRegion = true,
        )
        assertThatThrownBy {
                BedrockBackend.builder()
                    .fromEnv(DefaultCredentialsProvider.builder().build())
                    .build()
            }
            .isExactlyInstanceOf(IllegalStateException::class.java)
            .hasMessage("No AWS access key ID or AWS secret access key found.")
    }

    @Test
    // Skip test if AWS region is defined in the environment (either in an environment variable or
    // an AWS configuration), otherwise the test will fail as it expects nothing to be set.
    @DisabledIf("hasRegionInEnvironment")
    fun awsCredentialsProviderViaFromEnvWithNoRegion() {
        initEnv(
            isSetAccessKeyID = true,
            isSetSecretAccessKey = true,
            isSetSessionToken = true,
            isSetRegion = false,
        )
        assertThatThrownBy { BedrockBackend.builder().fromEnv().build() }
            .isExactlyInstanceOf(IllegalStateException::class.java)
            .hasMessage("No AWS region found.")
    }

    @Test
    fun awsCredentialsProviderClashWithApiKey() {
        initEnv()
        assertThatThrownBy {
                BedrockBackend.builder()
                    .apiKey(API_KEY)
                    .awsCredentialsProvider(DefaultCredentialsProvider.builder().build())
                    .build()
            }
            .isExactlyInstanceOf(IllegalStateException::class.java)
            .hasMessage("An AWS credentials provider or an API key must be set, but not both.")
    }

    @Test
    fun awsCredentialsProviderClashWithApiKeyViaFromEnv() {
        initEnv()
        assertThatThrownBy {
                BedrockBackend.builder().withoutApiKeyEnvVar().apiKey(API_KEY).fromEnv().build()
            }
            .isExactlyInstanceOf(IllegalStateException::class.java)
            .hasMessage("An AWS credentials provider or an API key must be set, but not both.")
    }

    @Test
    fun apiKeyClashWithAwsCredentialsProvider() {
        initEnv()
        assertThatThrownBy {
                BedrockBackend.builder()
                    .awsCredentialsProvider(DefaultCredentialsProvider.builder().build())
                    .apiKey(API_KEY)
                    .build()
            }
            .isExactlyInstanceOf(IllegalStateException::class.java)
            .hasMessage("Credentials provider already set.")
    }

    @Test
    fun apiKeyClashWithAwsCredentialsProviderViaFromEnv() {
        initEnv()
        val builder = BedrockBackend.builder().withApiKeyEnvVar()

        assertThatThrownBy {
                builder
                    .awsCredentialsProvider(DefaultCredentialsProvider.builder().build())
                    .fromEnv()
                    .build()
            }
            .isExactlyInstanceOf(IllegalStateException::class.java)
            .hasMessage("Credentials provider already set.")
    }

    @Test
    fun apiKeySetSoNoCredentialsProviderToAccessCredentials() {
        val backend = BedrockBackend.builder().apiKey(API_KEY).region(Region.EU_WEST_1).build()

        assertThat(backend.apiKey).isEqualTo(API_KEY)
        assertThat(backend.awsCredentialsProvider).isNull()

        assertThatThrownBy { backend.awsCredentials }
            .isExactlyInstanceOf(IllegalStateException::class.java)
            .hasMessage("AWS credentials provider was not set.")
    }

    @Test
    fun awsCredentialsProviderExplicitWithRegion() {
        val backend =
            BedrockBackend.builder()
                .awsCredentialsProvider(
                    BedrockBackend.providerOf(
                        AwsBasicCredentials.create(AWS_ACCESS_KEY_ID, AWS_SECRET_ACCESS_KEY)
                    )
                )
                .region(Region.EU_WEST_1)
                .build()

        assertThat(backend.awsCredentials).isExactlyInstanceOf(AwsBasicCredentials::class.java)
        assertThat(backend.awsCredentials.accessKeyId()).isEqualTo(AWS_ACCESS_KEY_ID)
        assertThat(backend.awsCredentials.secretAccessKey()).isEqualTo(AWS_SECRET_ACCESS_KEY)
        assertThat(backend.region).isEqualTo(Region.EU_WEST_1)
    }

    @Test
    fun apiKeyFromEnv() {
        // The AWS credentials are set, but they should be ignored because the API key environment
        // variable is set and `fromEnv()` is not explicitly passed an AWS credentials provider.
        initEnv()
        val backend = BedrockBackend.builder().withApiKeyEnvVar().fromEnv().build()

        assertThat(backend.apiKey).isEqualTo(API_KEY)
        assertThat(backend.region.toString()).isEqualTo(AWS_REGION)
    }

    @Test
    fun regionPresent() {
        initEnv()
        val backend = BedrockBackend.builder().withoutApiKeyEnvVar().fromEnv().build()

        assertThat(backend.awsCredentials).isExactlyInstanceOf(AwsSessionCredentials::class.java)
        assertThat(backend.region.toString()).isEqualTo(AWS_REGION)
    }

    @Test
    fun builderMissingCredentials() {
        // Make credentials available from the environment, but do not use them when building the
        // backend.
        initEnv()

        assertThatThrownBy { BedrockBackend.builder().build() }
            .isExactlyInstanceOf(IllegalStateException::class.java)
            .hasMessageStartingWith("No AWS credentials provider or API key was set.")
    }

    @Test
    fun builderMissingEverything() {
        // Make credentials available from the environment, but do not use them
        // when building the backend.
        initEnv()

        assertThatThrownBy { BedrockBackend.builder().build() }
            .isExactlyInstanceOf(IllegalStateException::class.java)
    }

    @Test
    fun regionExplicitWithoutAwsCredentials() {
        assertThatThrownBy { BedrockBackend.builder().region(Region.US_EAST_1).build() }
            .isExactlyInstanceOf(IllegalStateException::class.java)
            .hasMessage("No AWS credentials provider or API key was set.")
    }

    @Test
    fun baseUrl() {
        initEnv()
        val backend = BedrockBackend.builder().withoutApiKeyEnvVar().fromEnv().build()

        assertThat(backend.awsCredentials).isExactlyInstanceOf(AwsSessionCredentials::class.java)
        assertThat(backend.baseUrl()).isEqualTo("https://bedrock-runtime.$AWS_REGION.amazonaws.com")
    }

    @Test
    fun baseUrlOtherRegion() {
        initEnv()
        // Try with a *different* region to confirm that it is not hard-coded.
        val otherRegion = "eu-west-1"
        setProperty(PROP_AWS_REGION, otherRegion)
        val backend = BedrockBackend.fromEnv()

        assertThat(AWS_REGION).isNotEqualTo(otherRegion)
        assertThat(backend.baseUrl())
            .isEqualTo("https://bedrock-runtime.$otherRegion.amazonaws.com")
    }

    @Test
    fun prepareRequestMissingJSON() {
        initEnv()
        val backend = BedrockBackend.fromEnv()
        val request = createRequest(null, "v1", "messages")

        assertThat(request.body).isNull()
        assertThatThrownBy { backend.prepareRequest(request) }
            .isExactlyInstanceOf(AnthropicInvalidDataException::class.java)
            .hasMessageStartingWith("Request has no body")
    }

    @Test
    fun prepareRequestEmptyJSON() {
        initEnv()
        val backend = BedrockBackend.fromEnv()
        val request = createRequest("{}", "v1", "messages")

        assertThat(request.body).isNotNull()
        assertThatThrownBy { backend.prepareRequest(request) }
            .isExactlyInstanceOf(AnthropicInvalidDataException::class.java)
            .hasMessageStartingWith("No model found in body")
    }

    @Test
    fun prepareRequestNoPathSegments() {
        initEnv()
        val backend = BedrockBackend.fromEnv()
        // Request does not contain any path segments.
        val request = createRequest("""{"model":"$MODEL_ID"}""")

        assertThatThrownBy { backend.prepareRequest(request) }
            .isExactlyInstanceOf(AnthropicInvalidDataException::class.java)
            .hasMessageStartingWith("Request missing all path segments")
    }

    @Test
    fun prepareRequestAlreadyPrepared() {
        initEnv()
        val backend = BedrockBackend.fromEnv()
        // Request contains "model" as first path segment, which strongly
        // suggests it has already been through the "prepareRequest" function.
        val request = createRequest("""{}""", "model", MODEL_ID)

        assertThatThrownBy { backend.prepareRequest(request) }
            .isExactlyInstanceOf(IllegalArgumentException::class.java)
            .hasMessageStartingWith("Request already prepared")
    }

    @Test
    fun prepareRequestMissingV1() {
        initEnv()
        val backend = BedrockBackend.fromEnv()
        // Request does not contain a "v1" path segment.
        val request1 = createRequest("""{"model":"$MODEL_ID"}""", "d1", "messages")

        assertThatThrownBy { backend.prepareRequest(request1) }
            .isExactlyInstanceOf(AnthropicInvalidDataException::class.java)
            .hasMessageStartingWith("Expected first 'v1'")

        // Request contains a "v1" path segment, but not in first place.
        val request2 = createRequest("""{"model":"$MODEL_ID"}""", "messages", "v1")

        assertThatThrownBy { backend.prepareRequest(request2) }
            .isExactlyInstanceOf(AnthropicInvalidDataException::class.java)
            .hasMessageStartingWith("Expected first 'v1'")
    }

    @Test
    fun prepareRequestMissingServiceName() {
        initEnv()
        val backend = BedrockBackend.fromEnv()
        // Request does not contain a "messages" or "complete" path segment.
        val request = createRequest("""{"model":"$MODEL_ID"}""", "v1")

        assertThatThrownBy { backend.prepareRequest(request) }
            .isExactlyInstanceOf(AnthropicInvalidDataException::class.java)
            .hasMessageStartingWith("Missing service name")
    }

    @Test
    fun prepareRequestUnsupportedServiceName() {
        initEnv()
        val backend = BedrockBackend.fromEnv()
        val request = createRequest("""{"model":"$MODEL_ID"}""", "v1", "not-messages")

        assertThatThrownBy { backend.prepareRequest(request) }
            .isExactlyInstanceOf(AnthropicException::class.java)
            .hasMessageStartingWith("Service is not supported for Bedrock: not-messages")
    }

    @Test
    fun prepareRequestMessagesBatchesNotSupported() {
        initEnv()
        val backend = BedrockBackend.fromEnv()
        val request = createRequest("""{"model":"$MODEL_ID"}""", "v1", "messages", "batches")

        assertThatThrownBy { backend.prepareRequest(request) }
            .isExactlyInstanceOf(AnthropicException::class.java)
            .hasMessageStartingWith("Batch API is not supported")
    }

    @Test
    fun prepareRequestMessagesCountTokensNotSupported() {
        initEnv()
        val backend = BedrockBackend.fromEnv()
        val request = createRequest("""{"model":"$MODEL_ID"}""", "v1", "messages", "count_tokens")

        assertThatThrownBy { backend.prepareRequest(request) }
            .isExactlyInstanceOf(AnthropicException::class.java)
            .hasMessageStartingWith("Token counting is not supported")
    }

    @Test
    fun prepareRequestMessagesOtherEndpointsIgnored() {
        initEnv()
        val backend = BedrockBackend.fromEnv()
        val request = createRequest("""{"model":"$MODEL_ID"}""", "v1", "messages", "new_endpoint")

        assertThatNoException().isThrownBy { backend.prepareRequest(request) }
    }

    @Test
    fun prepareRequestMessages() {
        initEnv()
        val backend = BedrockBackend.fromEnv()
        val request = createRequest("""{"model":"$MODEL_ID"}""", "v1", "messages")
        val preparedRequest = backend.prepareRequest(request)
        val pathSegments = preparedRequest.pathSegments

        // Path segments: "/model/<MODEL_ID>/invoke"
        assertThat(pathSegments.size).isEqualTo(3)
        assertThat(pathSegments[0]).isEqualTo("model")
        assertThat(pathSegments[1]).isEqualTo(MODEL_ID)
        assertThat(pathSegments[2]).isEqualTo("invoke")

        // "model" should be removed from JSON body and "anthropic_version"
        // should be added.
        val json = bodyToJson(jsonMapper(), preparedRequest.body)

        assertThat(json).isNotNull()
        assertThat(json!!.get("model")).isNull()
        assertThat(json.get("anthropic_version").asText()).isEqualTo("bedrock-2023-05-31")
    }

    @Test
    fun prepareRequestMessagesStreaming() {
        initEnv()
        val backend = BedrockBackend.fromEnv()
        val request = createRequest("""{"model":"$MODEL_ID", "stream":true}""", "v1", "messages")
        val preparedRequest = backend.prepareRequest(request)
        val pathSegments = preparedRequest.pathSegments

        // Path segments: "/model/<MODEL_ID>/invoke-with-response-stream"
        assertThat(pathSegments.size).isEqualTo(3)
        assertThat(pathSegments[0]).isEqualTo("model")
        assertThat(pathSegments[1]).isEqualTo(MODEL_ID)
        assertThat(pathSegments[2]).isEqualTo("invoke-with-response-stream")

        // "model" and "stream" should be removed from JSON body and
        // "anthropic_version" should be added.
        val json = bodyToJson(jsonMapper(), preparedRequest.body)

        assertThat(json).isNotNull()
        assertThat(json!!.get("model")).isNull()
        assertThat(json.get("stream")).isNull()
        assertThat(json.get("anthropic_version").asText()).isEqualTo("bedrock-2023-05-31")
    }

    @Test
    fun prepareRequestMessagesStreamingExplicitlyDisabled() {
        initEnv()
        val backend = BedrockBackend.fromEnv()
        val request = createRequest("""{"model":"$MODEL_ID", "stream":false}""", "v1", "messages")
        val preparedRequest = backend.prepareRequest(request)
        val pathSegments = preparedRequest.pathSegments
        val json = bodyToJson(jsonMapper(), preparedRequest.body)

        // Check that it is the value of the "stream" property, not its mere
        // presence that causes a streaming request to be prepared. Similar
        // request details are tested more completely elsewhere.
        assertThat(pathSegments.size).isEqualTo(3)
        assertThat(pathSegments[2]).isEqualTo("invoke")

        // "stream" should be removed from JSON body.
        assertThat(json).isNotNull()
        assertThat(json!!.get("stream")).isNull()
    }

    @Test
    fun prepareRequestComplete() {
        initEnv()
        val backend = BedrockBackend.fromEnv()
        val request = createRequest("""{"model":"$MODEL_ID"}""", "v1", "complete")
        val preparedRequest = backend.prepareRequest(request)
        val pathSegments = preparedRequest.pathSegments
        val json = bodyToJson(jsonMapper(), preparedRequest.body)

        // Path segments: "/model/<MODEL_ID>/invoke"
        assertThat(pathSegments.size).isEqualTo(3)
        assertThat(pathSegments[0]).isEqualTo("model")
        assertThat(pathSegments[1]).isEqualTo(MODEL_ID)
        assertThat(pathSegments[2]).isEqualTo("invoke")

        // "model" should be removed from JSON body and "anthropic_version"
        // should be added.
        assertThat(json).isNotNull()
        assertThat(json!!.get("model")).isNull()
        assertThat(json.get("stream")).isNull()
        assertThat(json.get("anthropic_version").asText()).isEqualTo("bedrock-2023-05-31")
    }

    @Test
    fun prepareRequestCompleteStreaming() {
        initEnv()
        val backend = BedrockBackend.fromEnv()
        val request = createRequest("""{"model":"$MODEL_ID", "stream":true}""", "v1", "complete")
        val preparedRequest = backend.prepareRequest(request)
        val pathSegments = preparedRequest.pathSegments
        val json = bodyToJson(jsonMapper(), preparedRequest.body)

        // Path segments: "/model/<MODEL_ID>/invoke-with-response-stream"
        assertThat(pathSegments.size).isEqualTo(3)
        assertThat(pathSegments[0]).isEqualTo("model")
        assertThat(pathSegments[1]).isEqualTo(MODEL_ID)
        assertThat(pathSegments[2]).isEqualTo("invoke-with-response-stream")

        // "model" and "stream" should be removed from JSON body and
        // "anthropic_version" should be added.
        assertThat(json).isNotNull()
        assertThat(json!!.get("model")).isNull()
        assertThat(json.get("stream")).isNull()
        assertThat(json.get("anthropic_version").asText()).isEqualTo("bedrock-2023-05-31")
    }

    @Test
    fun prepareRequestNotBeta() {
        initEnv()
        val backend = BedrockBackend.fromEnv()
        val request = createRequest("""{"model":"$MODEL_ID"}""", "v1", "messages")
        val preparedRequest = backend.prepareRequest(request)
        val json = bodyToJson(jsonMapper(), preparedRequest.body)

        // Only check that there is no header and no property in the body
        // specifying a beta version. The rest is tested elsewhere.
        assertThat(preparedRequest.headers.values("anthropic-beta").size).isEqualTo(0)

        // "model" and "stream" should be removed from JSON body and
        // "anthropic_version" should be added.
        assertThat(json).isNotNull()
        assertThat(json!!.get("anthropic_beta")).isNull()
    }

    @Test
    fun prepareRequestBetaOneHeaderOneVersion() {
        initEnv()
        val backend = BedrockBackend.fromEnv()
        val request =
            createRequest("""{"model":"$MODEL_ID"}""", "v1", "messages")
                .toBuilder()
                .putHeader("anthropic-beta", "b1")
                .build()
        val preparedRequest = backend.prepareRequest(request)
        val json = bodyToJson(jsonMapper(), preparedRequest.body)

        assertThat(json).isNotNull()

        val betaJson = json!!.get("anthropic_beta")

        // The headers are not modified (that is not tested as it is not
        // important whether they are or not). The beta versions from headers
        // should be listed in a JSON array in the body.
        assertThat(betaJson).isNotNull()
        assertThat(betaJson.size()).isEqualTo(1)
        assertThat(betaJson[0].asText()).isEqualTo("b1")
    }

    @Test
    fun prepareRequestBetaThreeHeadersTwoVersions() {
        initEnv()
        val backend = BedrockBackend.fromEnv()
        val request =
            createRequest("""{"model":"$MODEL_ID"}""", "v1", "messages")
                .toBuilder()
                .putHeader("anthropic-beta", "b1")
                .putHeader("anthropic-beta", "b2")
                .putHeader("anthropic-beta", "b2") // Deliberate duplicate
                .build()
        val preparedRequest = backend.prepareRequest(request)
        val json = bodyToJson(jsonMapper(), preparedRequest.body)

        assertThat(json).isNotNull()

        val betaJson = json!!.get("anthropic_beta")

        assertThat(betaJson).isNotNull()

        // The order of the versions is indeterminate, so convert to a list (not
        // a set, as we want to see if duplicates were removed) and check that
        // the expected values are contained.
        val betaVersions = betaJson.map { it.asText() }.toList()

        assertThat(betaVersions.size).isEqualTo(2) // Duplicate removed
        assertThat(betaVersions).contains("b1")
        assertThat(betaVersions).contains("b2")
    }

    @Test
    fun prepareRequestBetaFourHeadersSixVersions() {
        initEnv()
        val backend = BedrockBackend.fromEnv()
        val request =
            createRequest("""{"model":"$MODEL_ID"}""", "v1", "messages")
                .toBuilder()
                .putHeader("anthropic-beta", "b1,b5,b6")
                .putHeader("anthropic-beta", "b3,b4,b2,b2")
                .putHeader("anthropic-beta", "b2")
                .putHeader("anthropic-beta", "b5")
                .build()
        val preparedRequest = backend.prepareRequest(request)
        val json = bodyToJson(jsonMapper(), preparedRequest.body)

        assertThat(json).isNotNull()

        val betaJson = json!!.get("anthropic_beta")

        assertThat(betaJson).isNotNull()

        val betaVersions = betaJson.map { it.asText() }.toList()

        assertThat(betaVersions.size).isEqualTo(6)
        assertThat(betaVersions).contains("b1")
        assertThat(betaVersions).contains("b2")
        assertThat(betaVersions).contains("b3")
        assertThat(betaVersions).contains("b4")
        assertThat(betaVersions).contains("b5")
        assertThat(betaVersions).contains("b6")
    }

    @Test
    fun authorizeRequestAlreadyAuthorized() {
        initEnv()
        val backend = BedrockBackend.fromEnv()
        val request =
            HttpRequest.builder()
                .method(HttpMethod.POST)
                .baseUrl("https://bedrock-runtime.us-east-1.amazonaws.com/path1/path2")
                .addPathSegment("path-1")
                .putQueryParam("param-1", "param-value-1")
                .putHeader("content-type", "on/request")
                .putHeader("X-Test", "header-value")
                .build()
        val authorizedRequest = backend.authorizeRequest(request)

        assertThatThrownBy { backend.authorizeRequest(authorizedRequest) }
            .isExactlyInstanceOf(IllegalArgumentException::class.java)
            .hasMessageStartingWith("Request already authorized for Bedrock")
    }

    @Test
    fun authorizeRequestContentTypeFromHeaders() {
        initEnv()
        val backend = BedrockBackend.builder().withoutApiKeyEnvVar().fromEnv().build()
        val request =
            HttpRequest.builder()
                .method(HttpMethod.POST)
                .baseUrl("https://bedrock-runtime.us-east-1.amazonaws.com/path1/path2")
                .addPathSegment("path-1")
                .addPathSegment("path-2")
                .putQueryParam("param-1", "param-value-1a")
                .putQueryParam("param-1", "param-value-1b")
                .putQueryParam("param-2", "param-value-2")
                // Content type in a header will be used in the signed request.
                .putHeader("content-type", "on/request")
                .putHeader("X-Test", "header-value-a")
                .putHeader("X-Test", "header-value-b")
                .build()
        val signedRequest = backend.authorizeRequest(request)
        val headers = signedRequest.headers

        // Check that the signed request contains all the same elements that
        // were in the original request plus the new signature-related headers.
        assertThat(signedRequest.method).isEqualTo(request.method)
        assertThat(signedRequest.baseUrl).isEqualTo(request.baseUrl)
        assertThat(signedRequest.body).isEqualTo(request.body)

        assertThat(signedRequest.pathSegments.size).isEqualTo(2)
        assertThat(signedRequest.pathSegments[0]).isEqualTo("path-1")
        assertThat(signedRequest.pathSegments[1]).isEqualTo("path-2")

        // There is an assumption here that query parameters with multiple
        // values store those values in order of insertion. That assumption
        // holds for the current implementation.
        assertThat(signedRequest.queryParams.keys().size).isEqualTo(2)
        assertThat(signedRequest.queryParams.values("param-1").size).isEqualTo(2)
        assertThat(signedRequest.queryParams.values("param-1")[0]).isEqualTo("param-value-1a")
        assertThat(signedRequest.queryParams.values("param-1")[1]).isEqualTo("param-value-1b")
        assertThat(signedRequest.queryParams.values("param-2").size).isEqualTo(1)
        assertThat(signedRequest.queryParams.values("param-2")[0]).isEqualTo("param-value-2")

        assertThat(headers.names()).contains("content-type")
        assertThat(headers.values("content-type").size).isEqualTo(1)
        assertThat(headers.values("content-type")[0]).isEqualTo("on/request")

        // Check that headers with more than one value were properly preserved.
        // There is an assumption here that headers with multiple values store
        // those values in order of insertion. That assumption holds for the
        // current implementation.
        assertThat(headers.names()).contains("X-Test")
        assertThat(headers.values("X-Test").size).isEqualTo(2)
        assertThat(headers.values("X-Test")[0]).isEqualTo("header-value-a")
        assertThat(headers.values("X-Test")[1]).isEqualTo("header-value-b")

        // Check that the AWS signature-related headers were added to the
        // request. Their values are not of particular interest. The
        // inconsistent capitalization is unfortunately real.
        assertThat(headers.names()).contains("Authorization")
        assertThat(headers.names()).contains("Host")
        assertThat(headers.names()).contains("x-amz-content-sha256")
        assertThat(headers.names()).contains("X-Amz-Date")
    }

    @Test
    fun authorizeRequestContentTypeFromBody() {
        initEnv()
        val backend = BedrockBackend.builder().withoutApiKeyEnvVar().fromEnv().build()
        val request =
            HttpRequest.builder()
                .method(HttpMethod.POST)
                .baseUrl("https://bedrock-runtime.us-east-1.amazonaws.com/path1/path2")
                .addPathSegment("path-1")
                .addPathSegment("path-2")
                .putQueryParam("param-1", "param-value-1a")
                .putQueryParam("param-1", "param-value-1b")
                .putQueryParam("param-2", "param-value-2")
                .putHeader("X-Test", "header-value-a")
                .putHeader("X-Test", "header-value-b")
                // Should create a new body holding the "content-type" of
                // "application/json"
                .body(json(jsonMapper(), parseJson("{}")))
                .build()
        val signedRequest = backend.authorizeRequest(request)
        val headers = signedRequest.headers

        // Only test the content type; the rest should be the same as the main
        // test of signing.
        assertThat(headers.names()).contains("content-type")
        assertThat(headers.values("content-type").size).isEqualTo(1)
        assertThat(headers.values("content-type")[0]).isEqualTo("application/json")
    }

    @Test
    fun authorizeRequestNoBodyNoContentType() {
        initEnv()
        val backend = BedrockBackend.builder().withoutApiKeyEnvVar().fromEnv().build()
        val request =
            HttpRequest.builder()
                .method(HttpMethod.POST)
                .baseUrl("https://bedrock-runtime.us-east-1.amazonaws.com/path1/path2")
                .addPathSegment("path-1")
                .putQueryParam("param-1", "param-value-1")
                .putHeader("X-Test", "header-value-a")
                .build()

        // Only test the content type; the rest should be the same as the main
        // test of signing. If the request does not have a body and the request
        // does not have a content type, then no content type can be inferred.
        // This should be a fatal condition.
        assertThatThrownBy { backend.authorizeRequest(request) }
            .isExactlyInstanceOf(AnthropicInvalidDataException::class.java)
            .hasMessageStartingWith("No content type")
    }

    @Test
    fun authorizeRequestWithApiKey() {
        initEnv()
        val backend = BedrockBackend.builder().apiKey(API_KEY).region(Region.EU_WEST_1).build()
        // Include a header in the request to ensure that the authorization procedure does not
        // replace all (delete) existing headers. This request is not signed, so not much else is
        // required to be set.
        val request =
            HttpRequest.builder()
                .method(HttpMethod.POST)
                .baseUrl("https://bedrock-runtime.us-east-1.amazonaws.com/path1/path2")
                .addPathSegment("path-1")
                .putQueryParam("param-1", "param-value-1")
                .putHeader("X-Test", "header-value-a")
                .build()
        val authorizedRequest = backend.authorizeRequest(request)

        assertThat(authorizedRequest.headers.names().contains("X-Test")).isTrue
        assertThat(authorizedRequest.headers.names().contains("Authorization")).isTrue
        assertThat(authorizedRequest.headers.values("Authorization").size).isEqualTo(1)
        assertThat(authorizedRequest.headers.values("Authorization")[0])
            .isEqualTo("Bearer $API_KEY")
    }

    @Test
    fun closeWithoutUsingThreads() {
        initEnv()
        val backend = BedrockBackend.fromEnv()

        assertThatNoException().isThrownBy { backend.close() }
    }

    /**
     * Initializes the environment to simulate the presence of AWS authentication and region values
     * that can be resolved into the credentials of the backend.
     *
     * For the simulation, system properties are set. This tests that the AWS credentials provider
     * is being used, as it will resolve credentials from system properties as part of its chain of
     * potential sources. System properties are the first source to be resolved by the default
     * provider chain. System properties are also easier to set and get when testing than
     * environment variables.
     *
     * This method sets all the properties to non-empty values. To set properties more selectively,
     * see the other [initEnv] method.
     */
    private fun initEnv() {
        initEnv(
            isSetAccessKeyID = true,
            isSetSecretAccessKey = true,
            isSetSessionToken = true,
            isSetRegion = true,
        )
    }

    /**
     * Initializes the environment selectively to simulate the presence of AWS authentication and
     * region values that can be resolved into the credentials of the backend. See [initEnv] for
     * more details, or to set all properties together.
     */
    private fun initEnv(
        isSetAccessKeyID: Boolean,
        isSetSecretAccessKey: Boolean,
        isSetSessionToken: Boolean,
        isSetRegion: Boolean,
    ) {
        if (isSetAccessKeyID) {
            setProperty(PROP_AWS_ACCESS_KEY_ID, AWS_ACCESS_KEY_ID)
        } else {
            clearProperty(PROP_AWS_ACCESS_KEY_ID)
        }

        if (isSetSecretAccessKey) {
            setProperty(PROP_AWS_SECRET_ACCESS_KEY, AWS_SECRET_ACCESS_KEY)
        } else {
            clearProperty(PROP_AWS_SECRET_ACCESS_KEY)
        }

        if (isSetSessionToken) {
            setProperty(PROP_AWS_SESSION_TOKEN, AWS_SESSION_TOKEN)
        } else {
            clearProperty(PROP_AWS_SESSION_TOKEN)
        }

        if (isSetRegion) {
            setProperty(PROP_AWS_REGION, AWS_REGION)
        } else {
            clearProperty(PROP_AWS_REGION)
        }
    }

    /** Clears all system properties set by [initEnv]. */
    private fun clearEnv() {
        initEnv(
            isSetAccessKeyID = false,
            isSetSecretAccessKey = false,
            isSetSessionToken = false,
            isSetRegion = false,
        )
    }

    /**
     * Creates a new backend builder wrapping this builder with a mock non-`null`value set for the
     * API key environment variable. This overrides any variable set in the actual system
     * environment.
     */
    private fun BedrockBackend.Builder.withApiKeyEnvVar(): BedrockBackend.Builder {
        val builder = Mockito.spy(this)

        Mockito.doReturn(API_KEY).`when`(builder).getEnv(ENV_API_KEY)

        return builder
    }

    /**
     * Creates a new backend builder wrapping this builder with a mock `null` value set for the API
     * key environment variable. This overrides any variable set in the actual system environment.
     */
    private fun BedrockBackend.Builder.withoutApiKeyEnvVar(): BedrockBackend.Builder {
        val builder = Mockito.spy(this)

        Mockito.doReturn(null).`when`(builder).getEnv(ENV_API_KEY)

        return builder
    }

    @Suppress("unused") private fun hasRegionInEnvironment() = hasRegionInEnvironment

    @Suppress("unused") private fun hasCredentialsInEnvironment() = hasCredentialsInEnvironment

    private fun parseJson(jsonData: String): ObjectNode =
        jsonMapper().readValue(jsonData, ObjectNode::class.java)

    /**
     * @param jsonData The JSON data to add to the body of request. If `null` a body will not be
     *   added to the request. If not `null`, the data must represent a valid JSON model, even a
     *   minimal `{}`, or an error will occur.
     */
    private fun createRequest(jsonData: String?, vararg pathSegments: String): HttpRequest =
        HttpRequest.builder()
            .method(HttpMethod.POST) // A method is required.
            .addPathSegments(*pathSegments)
            .apply { jsonData?.let { body(json(jsonMapper(), parseJson(it))) } }
            .build()
}
