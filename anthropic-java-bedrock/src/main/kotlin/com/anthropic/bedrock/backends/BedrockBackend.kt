package com.anthropic.bedrock.backends

import com.anthropic.backends.Backend
import com.anthropic.bedrock.backends.BedrockBackend.Builder
import com.anthropic.core.checkRequired
import com.anthropic.core.http.Headers
import com.anthropic.core.http.HttpRequest
import com.anthropic.core.http.HttpRequestBody
import com.anthropic.core.http.HttpResponse
import com.anthropic.core.json
import com.anthropic.core.jsonMapper
import com.anthropic.errors.AnthropicException
import com.anthropic.errors.AnthropicInvalidDataException
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.node.ObjectNode
import java.io.ByteArrayOutputStream
import java.io.InputStream
import java.io.PipedInputStream
import java.io.PipedOutputStream
import java.util.Base64
import java.util.concurrent.Executors
import java.util.concurrent.ThreadFactory
import java.util.concurrent.TimeUnit
import java.util.concurrent.atomic.AtomicLong
import software.amazon.awssdk.auth.credentials.AwsCredentials
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
 * The Amazon Bedrock backend that manages the AWS credentials required to
 * access an Anthropic AI model on the Bedrock service and adapts requests and
 * responses to Bedrock's requirements.
 *
 * Amazon Bedrock requires cryptographically-signed requests using credentials
 * issued by AWS. These can be provided via system properties, environment
 * variables, or other AWS facilities. They can be resolved automatically by
 * the default AWS provider chains by calling [Builder.fromEnv]. Alternatively,
 * the AWS credentials and region can be resolved independently and passed to
 * [Builder.awsCredentials] and [Builder.region] should an alternative method
 * of resolution be required.
 */
class BedrockBackend private constructor(
    /**
     * The AWS credentials required to access the Bedrock service.
     */
    @get:JvmName("awsCredentials") val awsCredentials: AwsCredentials,

    /**
     * The name of the AWS region hosting the Bedrock service.
     */
    @get:JvmName("region") val region: Region,
) : Backend {

    /**
     * A mapper for serializing and deserializing JSON data. For efficiency,
     * this is created once and then reused for the life of this instance. The
     * instance is thread-safe.
     */
    private val jsonMapper = jsonMapper()

    /**
     * The thread pool used by [prepareResponse] for piped I/O that translates
     * AWS _EventStream_ data into Server-Sent Events (SSE) data. When this
     * backend is done, calling [close] will shut down this thread pool.
     *
     * This thread pool will grow as necessary to ensure that there is never a
     * wait for a thread to become available.
     */
    private val threadPool = Executors.newCachedThreadPool(
        object : ThreadFactory {
            private val threadFactory = Executors.defaultThreadFactory()
            private val count = AtomicLong(0)

            override fun newThread(runnable: Runnable): Thread =
                threadFactory.newThread(runnable).apply {
                    name = "bedrock-sse-pipeline-${count.getAndIncrement()}"
                }
        }
    )

    companion object {
        private const val ANTHROPIC_VERSION = "bedrock-2023-05-31"

        /**
         * The Amazon Bedrock service name used for model inferences.
         */
        private const val SERVICE_NAME = "bedrock-runtime"

        /**
         * The name of the header that identifies the Anthropic beta versions.
         * To specify multiple beta versions, there can be more than one header,
         * or the value of a header can be a comma-separated list of beta
         * versions.
         */
        private const val HEADER_ANTHROPIC_BETA = "anthropic-beta"

        private const val HEADER_CONTENT_TYPE = "content-type"

        /**
         * The name of the header that identifies the content type for the
         * "payloads" of AWS _EventStream_ messages in streaming responses from
         * Bedrock.
         */
        private const val HEADER_PAYLOAD_CONTENT_TYPE =
            "x-amzn-bedrock-content-type"

        /**
         * The content type for Bedrock responses containing data in the AWS
         * _EventStream_ format. The value of the [HEADER_PAYLOAD_CONTENT_TYPE]
         * header identifies the content type of the "payloads" in this stream.
         */
        private const val CONTENT_TYPE_AWS_EVENT_STREAM =
            "application/vnd.amazon.eventstream"

        /**
         * The content type for Anthropic responses containing Bedrock data
         * after it has been translated into the Server-Sent Events (SSE) stream
         * format.
         */
        private const val CONTENT_TYPE_SSE_STREAM =
            "text/event-stream; charset=utf-8"

        /**
         * The content type for data in JSON format. The bodies of all requests
         * are expected to have this content type. For responses using the AWS
         * _EventStream_ content type, the "payloads" of the event messages are
         * expected to have this content type.
         */
        private const val CONTENT_TYPE_JSON = "application/json"

        /**
         * Creates a new builder for configuring and creating a new instance of
         * [BedrockBackend].
         */
        @JvmStatic fun builder() = Builder()

        /**
         * Creates new [BedrockBackend] with credentials resolved from the
         * environment or configuration files.
         *
         * This is a convenience method that is the equivalent of calling:
         *
         * ```
         * BedrockBackend.builder().fromEnv().build()
         * ```
         *
         * See [Builder.fromEnv] for more details.
         *
         * @throws AnthropicException See [Builder.build] for details.
         */
        @JvmStatic fun fromEnv(): BedrockBackend = builder().fromEnv().build()

        /**
         * Creates a JSON [ObjectNode] representing the JSON data parsed from a
         * [HttpRequestBody].
         *
         * @param body A new request body holding the JSON data.
         * @param jsonMapper The mapper to use to deserialize the JSON data.
         *
         * @return The JSON object data to form the request body, or `null` if
         *     the request does not have a body or if the body is empty.
         */
        // NOTE: "internal" visibility allows this method to be used in unit
        // tests to create test fixtures.
        @JvmSynthetic internal fun bodyToJson(
                body: HttpRequestBody?, jsonMapper: ObjectMapper): ObjectNode? {
            val jsonData = ByteArrayOutputStream()

            body?.writeTo(jsonData)
            if (jsonData.size() > 0) {
                return jsonMapper.readValue(
                    jsonData.toByteArray(), ObjectNode::class.java)
            }
            return null
        }
    }

    /**
     * Gets the base URL for the Amazon Bedrock runtime service. The base URL
     * includes the Bedrock runtime service name and the AWS region.
     */
    override fun baseUrl(): String =
        // Could use the AWS "BedrockEndpointProvider" in "fromEnv()", but that
        // is a large dependency that brings in all the competing AWS SDK
        // classes for Bedrock that are not desired in this SDK.
        "https://$SERVICE_NAME.$region.amazonaws.com"

    /**
     * Prepares the request for use with Amazon Bedrock.
     *
     * A number of changes are made to support the requirements of AWS requests
     * to the Bedrock backend:
     *
     * * The model ID is moved from the JSON body content to the path segments.
     * * The AWS action to be performed is added to the path segments.
     * * The Anthropic version is added to the JSON body content.
     * * The Anthropic beta versions (if present) are copied from the headers
     *     to the JSON body content. Beta versions are specified in (optional)
     *     `anthropic-beta` headers. There can be more than one header with more
     *     than one version per header (comma-separated).
     *
     * @param request The request to prepare. This request will not be modified.
     *
     * @return A new request, copied from the given request, and then prepared
     *     and modified to conform to the Bedrock requirements.
     *
     * @throws AnthropicException If the path segments describe an operation
     *     that is not yet supported by Anthropic models hosted on the Bedrock
     *     service.
     * @throws AnthropicInvalidDataException If the JSON body is not present.
     *     If the model ID is missing from the JSON body. If the request has
     *     already been prepared. If the service name is missing from the path
     *     segments.
     */
    override fun prepareRequest(request: HttpRequest): HttpRequest {
        val pathSegments = request.pathSegments

        // Check that the request is valid has not been prepared already.
        if (pathSegments.isEmpty() || pathSegments[0] != "v1") {
            throw AnthropicInvalidDataException(
                "Expected first 'v1' path segment.")
        }

        if (pathSegments.size <= 1) {
            throw AnthropicInvalidDataException(
                "Missing service name from request URL.")
        }

        when (pathSegments[1]) {
            "messages" -> {
                if (pathSegments.size > 2) {
                    when (pathSegments[2]) {
                        "batches" -> throw AnthropicException(
                            "Batch API is not supported for Bedrock.")
                        "count_tokens" -> throw AnthropicException(
                            "Token counting is not supported for Bedrock.")
                    } // For now, ignore any other path segments.
                }
            }
            "complete" -> {
                // Do nothing special.
            }
            else -> throw AnthropicException(
                "Service is not supported for Bedrock: ${pathSegments[1]}.")
        }

        val jsonBody: ObjectNode = bodyToJson(request.body, jsonMapper)
            ?: throw AnthropicInvalidDataException("Request has no body")

        jsonBody.put("anthropic_version", ANTHROPIC_VERSION)

        val betaVersions = request.headers.values(HEADER_ANTHROPIC_BETA)
            .flatMap { it.split(",") }.distinct()

        if (betaVersions.isNotEmpty()) {
            jsonBody.replace(
                "anthropic_beta", jsonMapper.valueToTree(betaVersions))
        }

        val model = jsonBody.remove("model")
            ?: throw AnthropicInvalidDataException("No model found in body.")
        val modelId = model.asText()
        // For Bedrock, the "stream" property must be removed from the body.
        // This differs from Vertex where the property is retained.
        val isStream = jsonBody.remove("stream")?.asBoolean() ?: false

        return request.toBuilder()
            .replaceAllPathSegments("model", modelId)
            .addPathSegment(
                if (isStream) "invoke-with-response-stream" else "invoke")
            .body(json(jsonMapper, jsonBody))
            .build()
    }

    /**
     * Signs and authorizes an Amazon Bedrock request. Requests to AWS services
     * are signed using the AWS secret access key (one of the required
     * credentials). This operation adds new headers to the request that allow
     * AWS to detect tampering or replay attacks.
     *
     * @param request The request to be signed and authorized. This will not be
     *     modified; it will be copied and the modified copy will be returned.
     *
     * @return The signed request including the signature and authorization
     *     headers.
     *
     * @throws AnthropicInvalidDataException If the request has already been
     *     authorized as evidenced by the existing presence of an authorization
     *     header. If there is no content type header either on the request or
     *     on the request body.
     */
    override fun authorizeRequest(request: HttpRequest): HttpRequest {
        if (request.headers.names().contains("Authorization")) {
            throw AnthropicInvalidDataException(
                "Request is already authorized.")
        }

        val awsSignRequest = SdkHttpRequest.builder()
            .uri(request.url)
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
                        appendHeader(HEADER_CONTENT_TYPE,
                            request.body!!.contentType())
                    } else {
                        throw AnthropicInvalidDataException(
                            "No content type in request headers or body.")
                    }
                }
                request.headers.names().forEach { name ->
                    request.headers.values(name).forEach { value ->
                        appendHeader(name, value)
                    }
                }
            }
            .build()

        val bodyData = ByteArrayOutputStream()

        request.body?.writeTo(bodyData)

        val awsSignedRequest: SdkHttpRequest =
            AwsV4HttpSigner.create().sign {
                    r: SignRequest.Builder<AwsCredentialsIdentity?> ->
                r.identity(awsCredentials)
                    .request(awsSignRequest)
                    .payload(ContentStreamProvider.fromByteArray(
                        bodyData.toByteArray()))
                    // The service *signing* name "bedrock" is not the same as
                    // the service name in the URL ("bedrock-runtime").
                    .putProperty(
                        AwsV4HttpSigner.SERVICE_SIGNING_NAME, "bedrock")
                    .putProperty(AwsV4HttpSigner.REGION_NAME, region.id())
            }.request()

        // Overwrite any headers with the same names already present.
        return request.toBuilder()
            .replaceAllHeaders(awsSignedRequest.headers())
            .build()
    }

    /**
     * Prepares a response from an Amazon Bedrock backend service.
     *
     * Where the response content type indicates an AWS EventStream, the stream
     * is translated into a stream of Server-Sent Events, adapting the response
     * to the Anthropic requirements. For other content types, no changes to the
     * response are required.
     *
     * @param response The response from the Bedrock backend service.
     *
     * @return A new response with a body [InputStream] that translates an AWS
     *     EventStream into an SSE stream, or the given response instance if no
     *     translation is required.
     *
     * @throws AnthropicInvalidDataException If the response content type is an
     *     AWS _EventStream_, but the "payloads" of the messages in that stream
     *     are not JSON strings.
     */
    override fun prepareResponse(response: HttpResponse): HttpResponse {
        if (!response.headers().values(HEADER_CONTENT_TYPE)
                .contains(CONTENT_TYPE_AWS_EVENT_STREAM)) {
            return response
        }

        val payloadContentType =
            response.headers().values(HEADER_PAYLOAD_CONTENT_TYPE)

        if (!payloadContentType.contains(CONTENT_TYPE_JSON)) {
            throw AnthropicInvalidDataException(
                "Expected streamed Bedrock events to have content type of " +
                        "$CONTENT_TYPE_JSON, but was $payloadContentType.")
        }

        val responseInput = response.body()
        val pipedInput = PipedInputStream()
        val pipedOutput = PipedOutputStream(pipedInput)

        // A decoded AWS EventStream message's payload is JSON. It might look
        // like this (abridged):
        //
        //   {"bytes":"eyJ0eXBlIjoi...ZXJlIn19","p":"abcdefghijkl"}
        //
        // The value of the "bytes" field is a base-64 encoded JSON string
        // (UTF-8). When decoded, it might look like this:
        //
        //   {"type":"content_block_delta","index":0,"delta":{"type":"text_delta","text":"Hello"}}
        //
        // Parse the "type" field to allow the construction of a server-sent
        // event (SSE) that might look like this:
        //
        //   event: content_block_delta
        //   data: {"type":"content_block_delta","index":0,"delta":{"type":"text_delta","text":"Hello"}}
        //
        // Print the SSE (with a blank line after) to the piped output stream to
        // complete the translation process.
        //
        // A thread avoids deadlocking the pipe. If everything is on the same
        // thread, a "read" that blocks waiting for more data to be written,
        // would block the thread from executing the necessary "write" and cause
        // a deadlock.
        threadPool.execute {
            responseInput.use { input ->
                // "use" closes the piped output stream when done, which signals
                // the end-of-file to the reader of the piped input stream.
                pipedOutput.use { output ->
                    // When fed enough data (see loop, below) to create a new
                    // "Message", the "Consumer.accept" lambda here is fired.
                    val messageDecoder = MessageDecoder { message ->
                        val sseJson = String(
                            Base64.getDecoder().decode(
                                jsonMapper.readTree(message.payload)
                                    .get("bytes").asText()
                            )
                        )
                        val sseEventType = jsonMapper.readTree(sseJson)
                            .get("type").asText()

                        output.write(
                            "event: $sseEventType\ndata: $sseJson\n\n"
                                .toByteArray()
                        )
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
                response.headers().toBuilder()
                    .replace(HEADER_CONTENT_TYPE, CONTENT_TYPE_SSE_STREAM)
                    .build()

            override fun body(): InputStream = pipedInput

            override fun close() = pipedInput.close()
        }
    }

    /**
     * Releases resources used by this backend when they are no longer needed.
     */
    override fun close() {
        threadPool.shutdown()
    }

    /**
     * A builder for a [BedrockBackend] used to connect an Anthropic client to
     * an Amazon Bedrock backend service.
     *
     * The AWS credentials and region can be extracted from the environment and
     * set on the builder by calling [fromEnv] before calling [build] to create
     * the [BedrockBackend]. Alternatively, set the AWS credentials and region
     * explicitly via [awsCredentials] and [region] before calling [build].
     */
    class Builder internal constructor() {
        /**
         * The AWS credentials required to access the Bedrock service.
         */
        private var awsCredentials: AwsCredentials? = null

        /**
         * The name of the AWS region hosting the Bedrock service.
         */
        private var region: Region? = null

        /**
         * Creates a new [BedrockBackend] with credential values and the AWS
         * region resolved automatically. Sources for the values may include
         * system properties, environment variables, AWS CLI configuration
         * files, AWS SSO resources, and more.
         *
         * If available from the environment, this method will identify the
         * following:
         *
         *  * Access key ID
         *  * Secret access key
         *  * Session token
         *  * Region
         *
         * The session token is optional, but the other credentials *must* all
         * be set in the environment or in the appropriate AWS configuration
         * files, or an error will occur. For the credentials, resolution
         * follows the default AWS credentials provider chain. For the region,
         * it follows the default AWS region provider chain. See the AWS Bedrock
         * documentation for details on how to configure the credentials and
         * region.
         *
         * Alternatively, set the AWS credentials and region explicitly using
         * the [awsCredentials] and [region] methods. In that case, there is no
         * need to call [fromEnv]. Instead, resolve or create the AWS
         * credentials and set them on the builder along with the region before
         * calling [build].
         *
         * If [fromEnv] is called after calling either [awsCredentials] or
         * [region], it will override the values of _both_ of those properties.
         * If either of the latter two methods is called after [fromEnv], each
         * will override only that element resolved by [fromEnv].
         *
         * @throws AnthropicException If the access key ID, secret access key,
         *     or AWS region cannot be resolved from the environment.
         */
        fun fromEnv() = apply {
            try {
                awsCredentials =
                    DefaultCredentialsProvider.create().resolveCredentials()
            } catch (e: Exception) {
                throw AnthropicException(
                    "No AWS access key ID or AWS secret access key found.", e
                )
            }
            try {
                region = DefaultAwsRegionProviderChain.builder().build().region
            } catch (e: Exception) {
                throw AnthropicException("No AWS region found.", e)
            }
        }

        /**
         * Sets the AWS credentials to use to authenticate and authorize
         * requests to the Bedrock service.
         *
         * Alternatively, use [fromEnv] to resolve the credentials automatically
         * from the environment or local AWS configuration.
         *
         * If this method is called after [fromEnv] it will override any AWS
         * credentials resolved by that method. If this method is called before
         * [fromEnv], the latter will override the credentials passed here.
         *
         * @param awsCredentials The AWS credentials. See the AWS documentation
         *     for details on how to configure and resolve or create AWS
         *     credentials.
         */
        fun awsCredentials(awsCredentials: AwsCredentials) = apply {
            this.awsCredentials = awsCredentials
        }

        /**
         * Sets the AWS region hosting the Bedrock service.
         *
         * Alternatively, use [fromEnv] to resolve the region automatically from
         * the environment or local AWS configuration.
         *
         * If this method is called after [fromEnv] it will override any AWS
         * region resolved by that method. If this method is called before
         * [fromEnv], the latter will override the region passed here.
         *
         * @param region The AWS region to be used.
         */
        fun region(region: Region) = apply { this.region = region }

        /**
         * Builds the new [BedrockBackend] from the data provided to the
         * builder.
         *
         * @throws IllegalStateException If the required AWS credentials and
         *     AWS region have not been resolved from the environment by calling
         *     [fromEnv]; or, alternatively, have not been passed explicitly by
         *     calling [awsCredentials] or [region].
         */
        fun build(): BedrockBackend = BedrockBackend(
            checkRequired("awsCredentials", awsCredentials),
            checkRequired("region", region),
        )
    }
}
