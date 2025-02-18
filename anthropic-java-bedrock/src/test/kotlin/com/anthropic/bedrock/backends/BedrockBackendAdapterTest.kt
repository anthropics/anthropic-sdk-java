package com.anthropic.bedrock.backends

import com.anthropic.core.http.HttpMethod
import com.anthropic.core.http.HttpRequest
import com.anthropic.core.jsonMapper
import com.anthropic.errors.AnthropicException
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.node.ObjectNode
import java.lang.System.clearProperty
import java.lang.System.setProperty
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials
import software.amazon.awssdk.auth.credentials.AwsSessionCredentials
import software.amazon.awssdk.regions.Region

/**
 * Unit tests for the [BedrockBackendAdapter] class.
 */
internal class BedrockBackendAdapterTest {
    companion object {
        /** An example of an access key ID. This is *not* a real ID. */
        private const val AWS_ACCESS_KEY_ID = "AKIAIOSFODNN7EXAMPLE"

        /** An example of a secret access key. This is *not* a real key. */
        private const val AWS_SECRET_ACCESS_KEY =
            "wJalrXUtnFEMI/K7MDENG/bPxRfiCYEXAMPLEKEY"

        /** An example of an AWS region name. */
        private const val AWS_REGION = "us-east-1"

        /** An example of an AWS session token. This is *not* a real token. */
        private const val AWS_SESSION_TOKEN = "FwoGZXIvYXdzEJr..."

        /** The ID of a model hosted on the Bedrock service. */
        private const val MODEL_ID = "anthropic.claude-3-5-sonnet-20240620-v1:0"

        // The names of the system properties recognised by AWS when resolving
        // credentials. The AWS SDK does not provide public constants for these
        // property names (see "software.amazon.awssdk.core.SdkSystemSetting").
        private const val PROP_AWS_ACCESS_KEY_ID     = "aws.accessKeyId"
        private const val PROP_AWS_SECRET_ACCESS_KEY = "aws.secretAccessKey"
        private const val PROP_AWS_SESSION_TOKEN     = "aws.sessionToken"
        private const val PROP_AWS_REGION            = "aws.region"
    }

    /**
     * Sets the system properties to a known state (all cleared) before each
     * test to avoid side effects.
     */
    @BeforeEach
    fun setUp() {
        clearEnv()
    }

    @Test
    fun awsCredentialsAllMissing() {
        // No system properties are set (see "setUp()").
        assertThatThrownBy { BedrockBackendAdapter.fromEnv() }
            .isExactlyInstanceOf(AnthropicException::class.java)
    }

    @Test
    fun awsCredentialsAccessKeyIDMissing() {
        initEnv(false, true, false, true)
        assertThatThrownBy { BedrockBackendAdapter.fromEnv() }
            .isExactlyInstanceOf(AnthropicException::class.java)
    }

    @Test
    fun awsCredentialsSecretAccessKeyMissing() {
        initEnv(true, false, false, true)
        assertThatThrownBy { BedrockBackendAdapter.fromEnv() }
            .isExactlyInstanceOf(AnthropicException::class.java)
    }

    @Test
    fun awsCredentialsFromEnv() {
        initEnv(true, true, false, true)
        val adapter = BedrockBackendAdapter.fromEnv()

        assertThat(adapter.awsCredentials)
            .isExactlyInstanceOf(AwsBasicCredentials::class.java)
        assertThat(adapter.awsCredentials.accessKeyId())
            .isEqualTo(AWS_ACCESS_KEY_ID)
        assertThat(adapter.awsCredentials.secretAccessKey())
            .isEqualTo(AWS_SECRET_ACCESS_KEY)
    }

    @Test
    fun awsSessionCredentialsAccessKeyIDMissing() {
        initEnv(false, true, true, true)
        assertThatThrownBy { BedrockBackendAdapter.fromEnv() }
            .isExactlyInstanceOf(AnthropicException::class.java)
    }

    @Test
    fun awsSessionCredentialsSecretAccessKeyMissing() {
        initEnv(true, false, true, true)
        assertThatThrownBy { BedrockBackendAdapter.fromEnv() }
            .isExactlyInstanceOf(AnthropicException::class.java)
    }

    @Test
    fun awsSessionCredentialsFromEnv() {
        initEnv()
        val adapter = BedrockBackendAdapter.fromEnv()

        // When the session token is present, the provided credentials should
        // have a different type to the type when it is absent.
        assertThat(adapter.awsCredentials)
            .isExactlyInstanceOf(AwsSessionCredentials::class.java)

        assertThat(adapter.awsCredentials.accessKeyId())
            .isEqualTo(AWS_ACCESS_KEY_ID)
        assertThat(adapter.awsCredentials.secretAccessKey())
            .isEqualTo(AWS_SECRET_ACCESS_KEY)
        assertThat((adapter.awsCredentials as AwsSessionCredentials)
            .sessionToken()).isEqualTo(AWS_SESSION_TOKEN)
    }

    @Test
    fun awsCredentialsExplicitWithoutRegion() {
        assertThatThrownBy {
            BedrockBackendAdapter.builder()
                .awsCredentials(AwsBasicCredentials.create(
                    AWS_ACCESS_KEY_ID, AWS_SECRET_ACCESS_KEY))
                .build()
        }
            .isExactlyInstanceOf(IllegalStateException::class.java)
            .hasMessageContaining("region")
    }

    @Test
    fun awsCredentialsExplicitWithRegion() {
        val adapter = BedrockBackendAdapter.builder()
            .awsCredentials(AwsBasicCredentials.create(
                AWS_ACCESS_KEY_ID, AWS_SECRET_ACCESS_KEY))
            .region(Region.EU_WEST_1)
            .build()

        assertThat(adapter.awsCredentials.accessKeyId())
            .isEqualTo(AWS_ACCESS_KEY_ID)
        assertThat(adapter.awsCredentials.secretAccessKey())
            .isEqualTo(AWS_SECRET_ACCESS_KEY)
        assertThat(adapter.region).isEqualTo(Region.EU_WEST_1)
    }

    @Test
    fun regionMissing() {
        initEnv(true, true, true, false)
        // This test runs slowly for some reason. Perhaps there is a long chain
        // of fall-backs in the region provider used in the class under test, or
        // some step in the chain performs some slow network operation.
        assertThatThrownBy { BedrockBackendAdapter.fromEnv() }
            .isExactlyInstanceOf(AnthropicException::class.java)
    }

    @Test
    fun regionPresent() {
        initEnv()
        val adapter = BedrockBackendAdapter.fromEnv()

        assertThat(adapter.awsCredentials)
            .isExactlyInstanceOf(AwsSessionCredentials::class.java)
        assertThat(adapter.region.toString()).isEqualTo(AWS_REGION)
    }

    @Test
    fun builderMissingCredentials() {
        // Make credentials available from the environment, but do not use them
        // when building the backend adapter.
        initEnv()
        assertThatThrownBy { BedrockBackendAdapter.builder().build() }
            .isExactlyInstanceOf(IllegalStateException::class.java)
            .hasMessageContaining("awsCredentials")
    }

    @Test
    fun builderMissingEverything() {
        // Make credentials available from the environment, but do not use them
        // when building the backend adapter.
        initEnv()
        assertThatThrownBy { BedrockBackendAdapter.builder().build() }
            .isExactlyInstanceOf(IllegalStateException::class.java)
    }

    @Test
    fun regionExplicitWithoutAwsCredentials() {
        assertThatThrownBy {
            BedrockBackendAdapter.builder()
                .region(Region.US_EAST_1)
                .build()
        }
            .isExactlyInstanceOf(IllegalStateException::class.java)
            .hasMessageContaining("awsCredentials")
    }

    @Test
    fun baseUrl() {
        initEnv()
        val adapter = BedrockBackendAdapter.fromEnv()

        assertThat(adapter.awsCredentials)
            .isExactlyInstanceOf(AwsSessionCredentials::class.java)
        assertThat(adapter.baseUrl())
            .isEqualTo("https://bedrock-runtime.$AWS_REGION.amazonaws.com")

        // Try with a *different* region to confirm that it is not hard-coded.
        val otherRegion = "eu-west-1"

        assertThat(AWS_REGION).isNotEqualTo(otherRegion)
        setProperty(PROP_AWS_REGION, otherRegion)

        val adapter2 = BedrockBackendAdapter.fromEnv()

        assertThat(adapter2.baseUrl())
            .isEqualTo("https://bedrock-runtime.$otherRegion.amazonaws.com")
    }

    @Test
    fun prepareRequestMissingJSON() {
        initEnv()
        val adapter = BedrockBackendAdapter.fromEnv()
        val request = createRequest(null, "v1", "messages")

        assertThat(request.body).isNull()
        assertThatThrownBy { adapter.prepareRequest(request) }
            .isExactlyInstanceOf(AnthropicException::class.java)
            .hasMessageStartingWith("Request has no body")
    }

    @Test
    fun prepareRequestEmptyJSON() {
        initEnv()
        val adapter = BedrockBackendAdapter.fromEnv()
        val request = createRequest("{}", "v1", "messages")

        assertThat(request.body).isNotNull()
        assertThatThrownBy { adapter.prepareRequest(request) }
            .isExactlyInstanceOf(AnthropicException::class.java)
            .hasMessageStartingWith("No model found in body")
    }

    @Test
    fun prepareRequestMissingV1() {
        initEnv()
        val adapter = BedrockBackendAdapter.fromEnv()

        // Request does not contain a "v1" path segment.
        val request1 = createRequest(
            """{"model":"$MODEL_ID"}""", "d1", "messages")
        assertThatThrownBy { adapter.prepareRequest(request1) }
            .isExactlyInstanceOf(AnthropicException::class.java)
            .hasMessageStartingWith("Expected first 'v1'")

        // Request contains a "v1" path segment, but not in first place.
        val request2 = createRequest(
            """{"model":"$MODEL_ID"}""", "messages", "v1")
        assertThatThrownBy { adapter.prepareRequest(request2) }
            .isExactlyInstanceOf(AnthropicException::class.java)
            .hasMessageStartingWith("Expected first 'v1'")
    }

    @Test
    fun prepareRequestMissingServiceName() {
        initEnv()
        val adapter = BedrockBackendAdapter.fromEnv()

        // Request does not contain a "messages" or "complete" path segment.
        val request = createRequest("""{"model":"$MODEL_ID"}""", "v1")
        assertThatThrownBy { adapter.prepareRequest(request) }
            .isExactlyInstanceOf(AnthropicException::class.java)
            .hasMessageStartingWith("Missing service name")
    }

    @Test
    fun prepareRequestUnsupportedServiceName() {
        initEnv()
        val adapter = BedrockBackendAdapter.fromEnv()
        val request = createRequest(
            """{"model":"$MODEL_ID"}""", "v1", "not-messages")

        assertThatThrownBy { adapter.prepareRequest(request) }
            .isExactlyInstanceOf(AnthropicException::class.java)
            .hasMessageStartingWith(
                "Service is not supported for Bedrock: not-messages")
    }

    @Test
    fun prepareRequestMessagesBatchesNotSupported() {
        initEnv()
        val adapter = BedrockBackendAdapter.fromEnv()
        val request = createRequest(
            """{"model":"$MODEL_ID"}""", "v1", "messages", "batches")

        assertThatThrownBy { adapter.prepareRequest(request) }
            .isExactlyInstanceOf(AnthropicException::class.java)
            .hasMessageStartingWith("Batch API is not supported")
    }

    @Test
    fun prepareRequestMessagesCountTokensNotSupported() {
        initEnv()
        val adapter = BedrockBackendAdapter.fromEnv()
        val request = createRequest(
            """{"model":"$MODEL_ID"}""", "v1", "messages", "count_tokens")

        assertThatThrownBy { adapter.prepareRequest(request) }
            .isExactlyInstanceOf(AnthropicException::class.java)
            .hasMessageStartingWith("Token counting is not supported")
    }

    @Test
    fun prepareRequestMessages() {
        initEnv()
        val adapter = BedrockBackendAdapter.fromEnv()
        val request = createRequest(
            """{"model":"$MODEL_ID"}""", "v1", "messages")
        val preparedRequest = adapter.prepareRequest(request)
        val pathSegments = preparedRequest.pathSegments

        // Path segments: "/model/<MODEL_ID>/invoke"
        assertThat(pathSegments.size).isEqualTo(3)
        assertThat(pathSegments[0]).isEqualTo("model")
        assertThat(pathSegments[1]).isEqualTo(MODEL_ID)
        assertThat(pathSegments[2]).isEqualTo("invoke")

        // "model" should be removed from JSON body and "anthropic_version"
        // should be added.
        val json = BedrockBackendAdapter.bodyToJson(
            preparedRequest.body, jsonMapper())

        assertThat(json).isNotNull()
        assertThat(json!!.get("model")).isNull()
        assertThat(json.get("anthropic_version").asText())
            .isEqualTo("bedrock-2023-05-31")
    }

    @Test
    fun prepareRequestMessagesStreaming() {
        initEnv()
        val adapter = BedrockBackendAdapter.fromEnv()
        val request = createRequest(
            """{"model":"$MODEL_ID", "stream":true}""", "v1", "messages")
        val preparedRequest = adapter.prepareRequest(request)
        val pathSegments = preparedRequest.pathSegments

        // Path segments: "/model/<MODEL_ID>/invoke-with-response-stream"
        assertThat(pathSegments.size).isEqualTo(3)
        assertThat(pathSegments[0]).isEqualTo("model")
        assertThat(pathSegments[1]).isEqualTo(MODEL_ID)
        assertThat(pathSegments[2]).isEqualTo("invoke-with-response-stream")

        // "model" and "stream" should be removed from JSON body and
        // "anthropic_version" should be added.
        val json = BedrockBackendAdapter.bodyToJson(
            preparedRequest.body, jsonMapper())

        assertThat(json).isNotNull()
        assertThat(json!!.get("model")).isNull()
        assertThat(json.get("stream")).isNull()
        assertThat(json.get("anthropic_version").asText())
            .isEqualTo("bedrock-2023-05-31")
    }

    @Test
    fun prepareRequestMessagesStreamingExplicitlyDisabled() {
        initEnv()
        val adapter = BedrockBackendAdapter.fromEnv()
        val request = createRequest(
            """{"model":"$MODEL_ID", "stream":false}""", "v1", "messages")
        val preparedRequest = adapter.prepareRequest(request)
        val pathSegments = preparedRequest.pathSegments

        // Check that it is the value of the "stream" property, not its mere
        // presence that causes a streaming request to be prepared. Similar
        // request details are tested more completely elsewhere.
        assertThat(pathSegments.size).isEqualTo(3)
        assertThat(pathSegments[2]).isEqualTo("invoke")

        // "stream" should be removed from JSON body.
        val json = BedrockBackendAdapter.bodyToJson(
            preparedRequest.body, jsonMapper())

        assertThat(json).isNotNull()
        assertThat(json!!.get("stream")).isNull()
    }

    @Test
    fun prepareRequestComplete() {
        initEnv()
        val adapter = BedrockBackendAdapter.fromEnv()
        val request = createRequest(
            """{"model":"$MODEL_ID"}""", "v1", "complete")
        val preparedRequest = adapter.prepareRequest(request)
        val pathSegments = preparedRequest.pathSegments

        // Path segments: "/model/<MODEL_ID>/invoke"
        assertThat(pathSegments.size).isEqualTo(3)
        assertThat(pathSegments[0]).isEqualTo("model")
        assertThat(pathSegments[1]).isEqualTo(MODEL_ID)
        assertThat(pathSegments[2]).isEqualTo("invoke")

        // "model" should be removed from JSON body and "anthropic_version"
        // should be added.
        val json = BedrockBackendAdapter.bodyToJson(
            preparedRequest.body, jsonMapper())

        assertThat(json).isNotNull()
        assertThat(json!!.get("model")).isNull()
        assertThat(json.get("stream")).isNull()
        assertThat(json.get("anthropic_version").asText())
            .isEqualTo("bedrock-2023-05-31")
    }

    @Test
    fun prepareRequestCompleteStreaming() {
        initEnv()
        val adapter = BedrockBackendAdapter.fromEnv()
        val request = createRequest(
            """{"model":"$MODEL_ID", "stream":true}""", "v1", "complete")
        val preparedRequest = adapter.prepareRequest(request)
        val pathSegments = preparedRequest.pathSegments

        // Path segments: "/model/<MODEL_ID>/invoke-with-response-stream"
        assertThat(pathSegments.size).isEqualTo(3)
        assertThat(pathSegments[0]).isEqualTo("model")
        assertThat(pathSegments[1]).isEqualTo(MODEL_ID)
        assertThat(pathSegments[2]).isEqualTo("invoke-with-response-stream")

        // "model" and "stream" should be removed from JSON body and
        // "anthropic_version" should be added.
        val json = BedrockBackendAdapter.bodyToJson(
            preparedRequest.body, jsonMapper())

        assertThat(json).isNotNull()
        assertThat(json!!.get("model")).isNull()
        assertThat(json.get("stream")).isNull()
        assertThat(json.get("anthropic_version").asText())
            .isEqualTo("bedrock-2023-05-31")
    }

    @Test
    fun prepareRequestNotBeta() {
        initEnv()
        val adapter = BedrockBackendAdapter.fromEnv()
        val request = createRequest(
            """{"model":"$MODEL_ID"}""", "v1", "messages")
        val preparedRequest = adapter.prepareRequest(request)

        // Only check that there is no header and no property in the body
        // specifying a beta version. The rest is tested elsewhere.
        assertThat(preparedRequest.headers.values("anthropic-beta").size)
            .isEqualTo(0)

        // "model" and "stream" should be removed from JSON body and
        // "anthropic_version" should be added.
        val json = BedrockBackendAdapter.bodyToJson(
            preparedRequest.body, jsonMapper())

        assertThat(json).isNotNull()
        assertThat(json!!.get("anthropic_beta")).isNull()
    }

    @Test
    fun prepareRequestBetaOneHeaderOneVersion() {
        initEnv()
        val adapter = BedrockBackendAdapter.fromEnv()
        val request = createRequest(
            """{"model":"$MODEL_ID"}""", "v1", "messages")
            .toBuilder()
            .putHeader("anthropic-beta", "b1")
            .build()
        val preparedRequest = adapter.prepareRequest(request)

        // The headers are not modified (that is not tested as it is not
        // important whether they are or not). The beta versions from headers
        // should be listed in a JSON array in the body.
        val json = BedrockBackendAdapter.bodyToJson(
            preparedRequest.body, jsonMapper())

        assertThat(json).isNotNull()

        val betaJson = json!!.get("anthropic_beta")

        assertThat(betaJson).isNotNull()
        assertThat(betaJson.size()).isEqualTo(1)
        assertThat(betaJson[0].asText()).isEqualTo("b1")
    }

    @Test
    fun prepareRequestBetaThreeHeadersTwoVersions() {
        initEnv()
        val adapter = BedrockBackendAdapter.fromEnv()
        val request = createRequest(
            """{"model":"$MODEL_ID"}""", "v1", "messages")
            .toBuilder()
            .putHeader("anthropic-beta", "b1")
            .putHeader("anthropic-beta", "b2")
            .putHeader("anthropic-beta", "b2") // Deliberate duplicate
            .build()
        val preparedRequest = adapter.prepareRequest(request)
        val json = BedrockBackendAdapter.bodyToJson(
            preparedRequest.body, jsonMapper())

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
        val adapter = BedrockBackendAdapter.fromEnv()
        val request = createRequest(
            """{"model":"$MODEL_ID"}""", "v1", "messages")
            .toBuilder()
            .putHeader("anthropic-beta", "b1,b5,b6")
            .putHeader("anthropic-beta", "b3,b4,b2,b2")
            .putHeader("anthropic-beta", "b2")
            .putHeader("anthropic-beta", "b5")
            .build()
        val preparedRequest = adapter.prepareRequest(request)
        val json = BedrockBackendAdapter.bodyToJson(
            preparedRequest.body, jsonMapper())

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
    fun signRequestContentTypeFromHeaders() {
        initEnv()

        val adapter = BedrockBackendAdapter.fromEnv()
        val request = HttpRequest.builder()
            .method(HttpMethod.POST)
            .url("https://bedrock-runtime.us-east-1.amazonaws.com/path1/path2")
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
        val signedRequest = adapter.signRequest(request)

        // Check that the signed request contains all the same elements that
        // were in the original request plus the new signature-related headers.
        assertThat(signedRequest.method).isEqualTo(request.method)
        assertThat(signedRequest.url).isEqualTo(request.url)
        assertThat(signedRequest.body).isEqualTo(request.body)

        assertThat(signedRequest.pathSegments.size).isEqualTo(2)
        assertThat(signedRequest.pathSegments[0]).isEqualTo("path-1")
        assertThat(signedRequest.pathSegments[1]).isEqualTo("path-2")

        // There is an assumption here that query parameters with multiple
        // values store those values in order of insertion. That assumption
        // holds for the current implementation.
        assertThat(signedRequest.queryParams.keys().size).isEqualTo(2)
        assertThat(signedRequest.queryParams.values("param-1").size)
            .isEqualTo(2)
        assertThat(signedRequest.queryParams.values("param-1")[0])
            .isEqualTo("param-value-1a")
        assertThat(signedRequest.queryParams.values("param-1")[1])
            .isEqualTo("param-value-1b")
        assertThat(signedRequest.queryParams.values("param-2").size)
            .isEqualTo(1)
        assertThat(signedRequest.queryParams.values("param-2")[0])
            .isEqualTo("param-value-2")

        val headers = signedRequest.headers

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
    fun signRequestContentTypeFromBody() {
        initEnv()

        val adapter = BedrockBackendAdapter.fromEnv()
        val request = HttpRequest.builder()
            .method(HttpMethod.POST)
            .url("https://bedrock-runtime.us-east-1.amazonaws.com/path1/path2")
            .addPathSegment("path-1")
            .addPathSegment("path-2")
            .putQueryParam("param-1", "param-value-1a")
            .putQueryParam("param-1", "param-value-1b")
            .putQueryParam("param-2", "param-value-2")
            .putHeader("X-Test", "header-value-a")
            .putHeader("X-Test", "header-value-b")
            // Should create a new body holding the "content-type" of
            // "application/json"
            .body(BedrockBackendAdapter.jsonToBody(json("{}"), jsonMapper()))
            .build()
        val signedRequest = adapter.signRequest(request)

        // Only test the content type; the rest should be the same as the main
        // test of signing.
        val headers = signedRequest.headers

        assertThat(headers.names()).contains("content-type")
        assertThat(headers.values("content-type").size).isEqualTo(1)
        assertThat(headers.values("content-type")[0])
            .isEqualTo("application/json")
    }

    /**
     * Initializes the environment to simulate the presence of AWS
     * authentication and region values that can be resolved into the
     * credentials of the backend adapter.
     *
     * For the simulation, system properties are set. This tests that the AWS
     * credentials provider is being used, as it will resolve credentials from
     * system properties as part of its chain of potential sources. System
     * properties are also easier to set and get when testing than environment
     * variables.
     *
     * This method sets all the properties to non-empty values. To set
     * properties more selectively, see the other [initEnv] method.
     */
    private fun initEnv() {
        initEnv(true, true, true, true)
    }

    /**
     * Initializes the environment to simulate the presence of AWS
     * authentication and region values that can be resolved into the
     * credentials of the backend adapter. See [initEnv] for more details,
     * or to set all properties together.
     *
     * @param isSetAccessKeyID `true` to set the access key ID property, or
     *     `false` to clear that property.
     * @param isSetSecretAccessKey `true` to set the secret access key property,
     *     or `false` to clear that property.
     * @param isSetSessionToken `true` to set the session token property, or
     *     `false` to clear that property.
     * @param isSetRegion `true` to set the region property, or `false` to clear
     *     that property.
     */
    private fun initEnv(isSetAccessKeyID: Boolean, isSetSecretAccessKey:Boolean,
                        isSetSessionToken: Boolean, isSetRegion: Boolean) {
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

    /**
     * Clears all system properties set by [initEnv].
     */
    private fun clearEnv() {
        initEnv(false, false, false, false)
    }

    /**
     * Parses the given JSON data (in string form) to a JSON object model..
     *
     * @param jsonData The JSON data in string form.
     *
     * @return The JSON data as an object model.
     */
    private fun json(jsonData: String): ObjectNode {
        return ObjectMapper().readValue(jsonData, ObjectNode::class.java)
    }

    /**
     * Creates a new [HttpRequest] with the given path segments and JSON body.
     *
     * @param jsonData The JSON data to add to the body of request. If `null`
     *     a body will not be added to the request. If not `null`, the data must
     *     represent a valid JSON model, even a minimal `{}`, or an error will
     *     occur.
     * @param pathSegments The path segments to add to the new request. May be
     *     empty if none are required.
     *
     * @return A new request with the given path segments and body.
     */
    private fun createRequest(
            jsonData: String?, vararg pathSegments: String): HttpRequest {
        return HttpRequest.builder()
            .method(HttpMethod.POST) // A method is required.
            .addPathSegments(*pathSegments)
            .apply {
                jsonData?.let {
                    body(BedrockBackendAdapter.jsonToBody(
                        json(it), jsonMapper()))
                }
            }
            .build()
    }
}