package com.anthropic.bedrock.backends

import com.anthropic.backends.Backend
import com.anthropic.bedrock.backends.BedrockBackend.Companion.HEADER_PAYLOAD_CONTENT_TYPE
import com.anthropic.core.checkRequired
import com.anthropic.core.http.Headers
import com.anthropic.core.http.HttpRequest
import com.anthropic.core.http.HttpResponse
import com.anthropic.core.http.bodyToJson
import com.anthropic.core.http.json
import com.anthropic.core.jsonMapper
import com.anthropic.errors.AnthropicException
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.databind.node.ObjectNode
import java.io.ByteArrayOutputStream
import java.io.InputStream
import java.io.PipedInputStream
import java.io.PipedOutputStream
import java.util.Base64
import java.util.concurrent.Executors
import java.util.concurrent.ThreadFactory
import java.util.concurrent.atomic.AtomicLong
import software.amazon.awssdk.auth.credentials.AwsCredentials
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider
import software.amazon.awssdk.http.ContentStreamProvider
import software.amazon.awssdk.http.SdkHttpMethod
import software.amazon.awssdk.http.SdkHttpRequest
import software.amazon.awssdk.http.auth.aws.signer.AwsV4HttpSigner
import software.amazon.awssdk.http.auth.spi.signer.SignRequest
import software.amazon.awssdk.identity.spi.AwsCredentialsIdentity
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.regions.providers.DefaultAwsRegionProviderChain
import software.amazon.eventstream.MessageDecoder

/**
 * The Amazon Bedrock backend that manages the AWS credentials required to access an Anthropic AI
 * model on the Bedrock service and adapts requests and responses to Bedrock's requirements.
 *
 * Amazon Bedrock requires cryptographically-signed requests using credentials issued by AWS. These
 * can be provided via system properties, environment variables, or other AWS facilities. They can
 * be resolved automatically by the default AWS provider chain by calling [Builder.fromEnv].
 * Alternatively, a custom AWS credentials provider can be configured on the builder and used to
 * resolve the credentials. Both the credentials and the region can be resolved independently and
 * passed to [Builder.awsCredentials] and [Builder.region] should an alternative method of
 * resolution be required.
 *
 * See the Amazon Bedrock and AWS documentation for details on how to configure AWS credentials.
 */
class BedrockBackend
private constructor(
    @get:JvmName("awsCredentialsProvider") val awsCredentialsProvider: AwsCredentialsProvider,
    @get:JvmName("region") val region: Region,
) : Backend {

    private val jsonMapper = jsonMapper()

    private val sseThreadPool =
        Executors.newCachedThreadPool(
            object : ThreadFactory {
                private val threadFactory = Executors.defaultThreadFactory()
                private val count = AtomicLong(0)

                override fun newThread(runnable: Runnable): Thread =
                    threadFactory.newThread(runnable).apply {
                        name = "bedrock-sse-pipeline-${count.getAndIncrement()}"
                    }
            }
        )

    /** Gets the AWS credentials resolved from the configured AWS credentials provider. */
    val awsCredentials: AwsCredentials
        // Depending on the source of the credentials, they may need to be periodically refreshed.
        // This can be done in the background if a credentials provider is configured to do so.
        // Typically, a provider will cache the periodically refreshed credentials, so resolving
        // them is a simple cache read and does not add much overhead.
        @JvmName("awsCredentials") get() = awsCredentialsProvider.resolveCredentials()

    companion object {
        private const val ANTHROPIC_VERSION = "bedrock-2023-05-31"
        private const val SERVICE_NAME = "bedrock-runtime"
        private const val HEADER_ANTHROPIC_BETA = "anthropic-beta"
        private const val HEADER_CONTENT_TYPE = "content-type"
        private const val CONTENT_TYPE_JSON = "application/json"

        /**
         * The name of the header that identifies the content type for the "payloads" of AWS
         * _EventStream_ messages in streaming responses from Bedrock.
         */
        private const val HEADER_PAYLOAD_CONTENT_TYPE = "x-amzn-bedrock-content-type"

        /**
         * The content type for Bedrock responses containing data in the AWS _EventStream_ format.
         * The value of the [HEADER_PAYLOAD_CONTENT_TYPE] header identifies the content type of the
         * "payloads" in this stream.
         */
        private const val CONTENT_TYPE_AWS_EVENT_STREAM = "application/vnd.amazon.eventstream"

        /**
         * The content type for Anthropic responses containing Bedrock data after it has been
         * translated into the Server-Sent Events (SSE) stream format.
         */
        private const val CONTENT_TYPE_SSE_STREAM = "text/event-stream; charset=utf-8"

        @JvmStatic fun builder() = Builder()

        /**
         * Creates a Bedrock Backend configured to use the default AWS credentials provider. See
         * [Builder.fromEnv] for more details, or to configure a different provider.
         */
        @JvmStatic fun fromEnv() = builder().fromEnv().build()

        @JvmSynthetic
        internal fun providerOf(awsCredentials: AwsCredentials) =
            object : AwsCredentialsProvider {
                override fun resolveCredentials() = awsCredentials
            }
    }

    override fun baseUrl(): String =
        // Could use the AWS "BedrockEndpointProvider" in "fromEnv()", but that is a large
        // dependency that brings in all the competing AWS SDK classes for Bedrock that are not
        // desired in this SDK.
        "https://$SERVICE_NAME.$region.amazonaws.com"

    override fun prepareRequest(request: HttpRequest): HttpRequest {
        val pathSegments = request.pathSegments

        if (pathSegments.isEmpty()) {
            throw AnthropicInvalidDataException("Request missing all path segments.")
        }

        require(pathSegments[0] != "model") { "Request already prepared for Bedrock." }

        if (pathSegments[0] != "v1") {
            throw AnthropicInvalidDataException("Expected first 'v1' path segment.")
        }

        if (pathSegments.size <= 1) {
            throw AnthropicInvalidDataException("Missing service name from request URL.")
        }

        when (pathSegments[1]) {
            "messages" -> {
                if (pathSegments.size > 2) {
                    when (pathSegments[2]) {
                        "batches" ->
                            throw AnthropicException("Batch API is not supported for Bedrock.")
                        "count_tokens" ->
                            throw AnthropicException("Token counting is not supported for Bedrock.")
                    } // For now, ignore any other path segments.
                }
            }
            "complete" -> {
                // Do nothing special.
            }
            else ->
                throw AnthropicException(
                    "Service is not supported for Bedrock: ${pathSegments[1]}."
                )
        }

        val jsonBody: ObjectNode =
            bodyToJson(jsonMapper, request.body)
                ?: throw AnthropicInvalidDataException("Request has no body")

        jsonBody.put("anthropic_version", ANTHROPIC_VERSION)

        val betaVersions =
            request.headers.values(HEADER_ANTHROPIC_BETA).flatMap { it.split(",") }.distinct()

        if (betaVersions.isNotEmpty()) {
            jsonBody.replace("anthropic_beta", jsonMapper.valueToTree(betaVersions))
        }

        val model =
            jsonBody.remove("model")
                ?: throw AnthropicInvalidDataException("No model found in body.")
        val modelId = model.asText()
        // For Bedrock, the "stream" property must be removed from the body.
        // This differs from Vertex where the property is retained.
        val isStream = jsonBody.remove("stream")?.asBoolean() ?: false

        return request
            .toBuilder()
            .replaceAllPathSegments("model", modelId)
            .addPathSegment(if (isStream) "invoke-with-response-stream" else "invoke")
            .body(json(jsonMapper, jsonBody))
            .build()
    }

    override fun authorizeRequest(request: HttpRequest): HttpRequest {
        require(!request.headers.names().contains("Authorization")) {
            "Request already authorized for Bedrock."
        }

        val awsSignRequest =
            SdkHttpRequest.builder()
                .uri(request.baseUrl)
                .method(SdkHttpMethod.fromValue(request.method.toString()))
                .apply {
                    // For the signature, copy the content type header from the body
                    // if the request object has no content type header. If there is
                    // no content type header, die. There needs to be one, otherwise
                    // the "sign()" call later will add a "content-type" header with
                    // a "null" value and crash "replaceAllHeaders". It is better to
                    // provide a meaningful error earlier in the execution.
                    if (request.headers.values(HEADER_CONTENT_TYPE).isEmpty()) {
                        if (request.body?.contentType() != null) {
                            appendHeader(HEADER_CONTENT_TYPE, request.body!!.contentType())
                        } else {
                            throw AnthropicInvalidDataException(
                                "No content type in request headers or body."
                            )
                        }
                    }
                    request.headers.names().forEach { name ->
                        request.headers.values(name).forEach { value -> appendHeader(name, value) }
                    }
                }
                .build()

        val bodyData = ByteArrayOutputStream()

        request.body?.writeTo(bodyData)

        val awsSignedRequest: SdkHttpRequest =
            AwsV4HttpSigner.create()
                .sign { r: SignRequest.Builder<AwsCredentialsIdentity?> ->
                    r.identity(awsCredentials)
                        .request(awsSignRequest)
                        .payload(ContentStreamProvider.fromByteArray(bodyData.toByteArray()))
                        // The service *signing* name "bedrock" is not the same as
                        // the service name in the URL ("bedrock-runtime").
                        .putProperty(AwsV4HttpSigner.SERVICE_SIGNING_NAME, "bedrock")
                        .putProperty(AwsV4HttpSigner.REGION_NAME, region.id())
                }
                .request()

        // Overwrite any headers with the same names already present.
        return request.toBuilder().replaceAllHeaders(awsSignedRequest.headers()).build()
    }

    override fun prepareResponse(response: HttpResponse): HttpResponse {
        if (
            !response.headers().values(HEADER_CONTENT_TYPE).contains(CONTENT_TYPE_AWS_EVENT_STREAM)
        ) {
            return response
        }

        val payloadContentType = response.headers().values(HEADER_PAYLOAD_CONTENT_TYPE)

        if (!payloadContentType.contains(CONTENT_TYPE_JSON)) {
            throw AnthropicInvalidDataException(
                "Expected streamed Bedrock events to have content type of " +
                    "$CONTENT_TYPE_JSON, but was $payloadContentType."
            )
        }

        val responseInput = response.body()
        val pipedInput = PipedInputStream()
        val pipedOutput = PipedOutputStream(pipedInput)

        // spotless:off
        //
        // A decoded AWS EventStream message's payload is JSON. It might look like this (abridged):
        //
        //   {"bytes":"eyJ0eXBlIjoi...ZXJlIn19","p":"abcdefghijkl"}
        //
        // The value of the "bytes" field is a base-64 encoded JSON string (UTF-8). When decoded, it
        // might look like this:
        //
        //   {"type":"content_block_delta","index":0,"delta":{"type":"text_delta","text":"Hello"}}
        //
        // Parse the "type" field to allow the construction of a server-sent event (SSE) that might
        // look like this:
        //
        //   event: content_block_delta
        //   data:
        // {"type":"content_block_delta","index":0,"delta":{"type":"text_delta","text":"Hello"}}
        //
        // Print the SSE (with a blank line after) to the piped output stream to complete the
        // translation process.
        //
        // A thread avoids deadlocking the pipe. If everything is on the same thread, a "read" that
        // blocks waiting for more data to be written, would block the thread from executing the
        // necessary "write" and cause a deadlock.
        //
        // spotless:on
        sseThreadPool.execute {
            responseInput.use { input ->
                // "use" closes the piped output stream when done, which signals
                // the end-of-file to the reader of the piped input stream.
                pipedOutput.use { output ->
                    // When fed enough data (see loop, below) to create a new
                    // "Message", the "Consumer.accept" lambda here is fired.
                    val messageDecoder = MessageDecoder { message ->
                        val sseJson =
                            String(
                                Base64.getDecoder()
                                    .decode(
                                        jsonMapper.readTree(message.payload).get("bytes").asText()
                                    )
                            )
                        val sseEventType = jsonMapper.readTree(sseJson).get("type").asText()

                        output.write("event: $sseEventType\ndata: $sseJson\n\n".toByteArray())
                        output.flush()
                    }

                    val buffer = ByteArray(4096)
                    var bytesRead: Int
                    while (input.read(buffer).also { bytesRead = it } != -1) {
                        messageDecoder.feed(buffer, 0, bytesRead)
                    }
                }
            }
        }

        return object : HttpResponse {
            override fun statusCode(): Int = response.statusCode()

            override fun headers(): Headers =
                response
                    .headers()
                    .toBuilder()
                    .replace(HEADER_CONTENT_TYPE, CONTENT_TYPE_SSE_STREAM)
                    .build()

            override fun body(): InputStream = pipedInput

            override fun close() = pipedInput.close()
        }
    }

    override fun close() {
        sseThreadPool.shutdown()
    }

    /**
     * A builder for a [BedrockBackend] used to connect an Anthropic client to an Amazon Bedrock
     * backend service.
     *
     * The AWS credentials and region can be extracted from the environment and set on the builder
     * by calling [fromEnv] before calling [build] to create the [BedrockBackend]. Alternatively,
     * set the AWS credentials and region explicitly via [awsCredentials] and [region] before
     * calling [build]. A custom AWS credentials provider can be passed to [fromEnv] or
     * [awsCredentialsProvider].
     */
    class Builder internal constructor() {
        private var awsCredentialsProvider: AwsCredentialsProvider? = null

        private var region: Region? = null

        /**
         * Resolves the AWS credentials from the environment, or other sources configured by the
         * credentials provider. If no provider is given, the AWS `DefaultCredentialsProvider` is
         * used, which is suitable for many use cases. The configuration of that provider follows
         * its usual defaults, so asynchronous refreshing is not enabled. See the AWS documentation
         * for details. For other use cases, pass a credentials provider configured as required. The
         * provider, whether given explicitly or by default, overrides any provider set via
         * [awsCredentialsProvider] or [awsCredentials].
         *
         * The region is also resolved immediately using the default AWS region provider chain. Once
         * resolved here, the region will not be changed again.
         *
         * When called, this method will immediately attempt to resolve the AWS credentials and
         * region. An error will occur if they cannot be resolved.
         */
        @JvmOverloads
        fun fromEnv(
            awsCredentialsProvider: AwsCredentialsProvider = DefaultCredentialsProvider.create()
        ) = apply {
            awsCredentialsProvider(awsCredentialsProvider)

            try {
                // A fail-fast check to ensure that the provider and the environment are properly
                // configured. There is no need to store the result.
                awsCredentialsProvider.resolveCredentials()
            } catch (e: Exception) {
                throw IllegalStateException(
                    "No AWS access key ID or AWS secret access key found.",
                    e,
                )
            }
            try {
                region = DefaultAwsRegionProviderChain.builder().build().region
            } catch (e: Exception) {
                throw IllegalStateException("No AWS region found.", e)
            }
        }

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
        fun awsCredentials(awsCredentials: AwsCredentials) = apply {
            awsCredentialsProvider = providerOf(awsCredentials)
        }

        /**
         * Sets the region to use when constructing the base URL for requests. Alternatively, this
         * may be resolved from the environment by calling [fromEnv]. If called after [fromEnv],
         * this overrides the region resolved by [fromEnv].
         */
        fun region(region: Region) = apply { this.region = region }

        fun build() =
            BedrockBackend(
                checkRequired("awsCredentialsProvider", awsCredentialsProvider),
                checkRequired("region", region),
            )
    }
}
