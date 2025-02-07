package com.anthropic.bedrock.credentials

import com.anthropic.bedrock.credentials.BedrockCredentials.Builder
import com.anthropic.core.http.HttpRequest
import com.anthropic.core.http.HttpRequestBody
import com.anthropic.credentials.Credentials
import com.anthropic.errors.AnthropicException
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.node.ObjectNode
import java.io.ByteArrayOutputStream
import java.io.OutputStream
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

/**
 * A holder for credentials required to access an Anthropic AI model on the
 * Amazon Bedrock service.
 *
 * Amazon Bedrock requires cryptographically-signed requests using credentials
 * issued by AWS. These can be provided via system properties, environment
 * variables, or other AWS facilities. They can be resolved by calling
 * [Builder.fromEnv].
 */
class BedrockCredentials private constructor(
    /**
     * The AWS credentials required to access the Bedrock service.
     */
    @get:JvmName("awsCredentials") val awsCredentials: AwsCredentials,

    /**
     * The name of the AWS region hosting the Bedrock service.
     */
    @get:JvmName("region")  val region: Region,
) : Credentials {

    companion object {
        /**
         * The Amazon Bedrock service name used for model inferences.
         */
        private const val SERVICE_NAME = "bedrock-runtime"

        /**
         * The Anthropic version for the Bedrock service.
         */
        private const val ANTHROPIC_VERSION = "bedrock-2023-05-31"

        /**
         * Creates a new builder for configuring and creating a new instance of
         * [BedrockCredentials].
         *
         * @return The new builder.
         */
        @JvmStatic fun builder() = Builder()

        /**
         * Creates new [BedrockCredentials] with credentials resolved from the
         * environment or configuration files.
         *
         * This is a convenience method that is the equivalent of calling:
         *
         * ```
         * BedrockCredentials.builder().fromEnv().build()
         * ```
         *
         * @return The new [BedrockCredentials].
         *
         * @throws AnthropicException See [Builder.build] for details.
         */
        @JvmStatic fun fromEnv(): BedrockCredentials {
            return builder().fromEnv().build()
        }

        /**
         * Creates a [HttpRequestBody] holding JSON object data.
         *
         * @param json The JSON object data to form the request body.
         *
         * @return A new request body holding the JSON data. The content type
         *     will be set to `application/json`.
         */
        // NOTE: "internal" visibility allows this method to be used in unit
        // tests to create test fixtures.
        @JvmSynthetic
        internal fun jsonToBody(json: ObjectNode): HttpRequestBody {
            // TODO: Code copied from "HttpRequestBodies.json", an "internal"
            //   function. Should that function be made visible for use here?
            return object : HttpRequestBody {
                private var cachedBytes: ByteArray? = null

                private fun serialize(): ByteArray {
                    if (cachedBytes != null) return cachedBytes!!

                    val buffer = ByteArrayOutputStream()
                    try {
                        ObjectMapper().writeValue(buffer, json)
                        cachedBytes = buffer.toByteArray()
                        return cachedBytes!!
                    } catch (e: Exception) {
                        throw AnthropicException("Error writing request", e)
                    }
                }

                override fun writeTo(outputStream: OutputStream) {
                    outputStream.write(serialize())
                }

                override fun contentType(): String = "application/json"

                override fun contentLength(): Long {
                    return serialize().size.toLong()
                }

                override fun repeatable(): Boolean = true

                override fun close() {}
            }
        }

        /**
         * Creates a JSON [ObjectNode] representing the JSON data parsed from a
         * [HttpRequestBody].
         *
         * @param body A new request body holding the JSON data.
         *
         * @return The JSON object data to form the request body, or `null` if
         *     the request does not have a body or if the body is empty.
         */
        // NOTE: "internal" visibility allows this method to be used in unit
        // tests to create test fixtures.
        @JvmSynthetic
        internal fun bodyToJson(body: HttpRequestBody?): ObjectNode? {
            val jsonData = ByteArrayOutputStream()

            body?.writeTo(jsonData)
            if (jsonData.size() > 0) {
                return ObjectMapper().readValue(
                    jsonData.toByteArray(), ObjectNode::class.java)
            }
            return null
        }
    }

    /**
     * Gets the base URL for the Amazon Bedrock runtime service. The base URL
     * includes the Bedrock runtime service name and the AWS region.
     *
     * @return The base URL for the Bedrock runtime service.
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
     * to the Bedrock service:
     *
     * * The model ID is moved from the JSON body content to the path segments.
     * * The Anthropic version is added to the JSON body content.
     * * The AWS action to be performed is added to the path segments.
     *
     * @param request The request to prepare. This request will not be modified.
     *
     * @return A new request, copied from the given request, and then prepared
     *     and modified to conform to the Bedrock requirements.
     *
     * @throws AnthropicException If the path segments describe an operation
     *     that is not yet supported by Anthropic models hosted on the Bedrock
     *     service. If the JSON body is not present. If the request has already
     *     been prepared.
     */
    override fun prepare(request: HttpRequest): HttpRequest {
        val json: ObjectNode? = bodyToJson(request.body)
        val modelId: String
        val isStreaming: Boolean

        // Interpret and modify the JSON body.
        if (json != null) {
            val model = json.remove("model")
                ?: throw AnthropicException("No model found in body.")

            modelId = model.asText()
            isStreaming = json.remove("stream") != null
            json.put("anthropic_version", ANTHROPIC_VERSION)

            // TODO: Support "anthropic_beta". This may need to be set from a
            //   value in a request header. See the Python code.
        } else {
            throw AnthropicException("Request has no body")
        }

        val pathSegments = request.pathSegments

        // Checks that the request is valid has not been prepared already.
        if (pathSegments.isEmpty() || pathSegments[0] != "v1") {
            throw AnthropicException("Expected first 'v1' path segment.")
        }

        // Validate the supported path segments.
        if (pathSegments.size > 1) {
            if (pathSegments[1] == "messages") {
                if (pathSegments.size > 2) {
                    if (pathSegments[2] == "batches") {
                        throw AnthropicException(
                            "Batch API is not supported for Bedrock.")
                    }
                    if (pathSegments[2] == "count_tokens") {
                        throw AnthropicException(
                            "Token counting is not supported for Bedrock.")
                    }
                    // For now, ignore any other path segments.
                }
            } else if (pathSegments[1] != "complete") {
                throw AnthropicException(
                    "Service is not supported for Bedrock: ${pathSegments[1]}.")
            }
        } else {
            throw AnthropicException("Missing service name from request URL.")
        }

        // Build a new, modified HttpRequest with the appropriate changes made
        // to the path segments and the body to support Anthropic on Bedrock.
        return request.toBuilder()
            .replaceAllPathSegments("model", modelId)
            .addPathSegment(
                if (isStreaming) "invoke-with-response-stream" else "invoke")
            .body(jsonToBody(json))
            .build()
    }

    /**
     * Signs the Amazon Bedrock request. Requests to AWS services are signed
     * using the AWS secret access key (one of the required credentials). This
     * operation adds new headers to the request that allow AWS to detect
     * tampering or replay attacks.
     *
     * @param request The request to be signed. This will not be modified; it
     *     will be copied and the modified copy will be returned.
     *
     * @return The signed request including the signature headers.
     */
    override fun sign(request: HttpRequest): HttpRequest {
        val awsSignRequest = SdkHttpRequest.builder()
            .uri(request.url)
            .method(SdkHttpMethod.fromValue(request.method.toString()))
            .apply {
                // For the signature, copy the content type header from the body
                // if the request object has no content type header.
                if (request.headers.values("Content-Type").isEmpty()) {
                    request.body?.contentType().let {
                        appendHeader("Content-Type", it)
                    }
                }
                request.headers.names().forEach { name ->
                    request.headers.values(name).forEach { value ->
                        appendHeader(name, value)
                    }
                }
            }
            .build()

        val signer: AwsV4HttpSigner = AwsV4HttpSigner.create()
        val bodyData = ByteArrayOutputStream()

        request.body?.writeTo(bodyData)

        val awsSignedRequest: SdkHttpRequest =
            signer.sign { r: SignRequest.Builder<AwsCredentialsIdentity?> ->
                r.identity(awsCredentials)
                    .request(awsSignRequest)
                    .payload(ContentStreamProvider.fromByteArray(
                        bodyData.toByteArray()))
                    // The service signing name "bedrock" is not the same as the
                    // service name in the URL "bedrock-runtime".
                    .putProperty(
                        AwsV4HttpSigner.SERVICE_SIGNING_NAME, "bedrock"
                    )
                    .putProperty(AwsV4HttpSigner.REGION_NAME, region.id())
            }.request()

        return mergeRequests(request, awsSignedRequest)
    }

    /**
     * Creates a new [HttpRequest] by merging an existing request with its
     * corresponding (and abbreviated) signed request.
     *
     * @param request The request to be merged with the signed request.
     * @param awsSignedRequest The signed request to be merged with the request.
     *
     * @return A new [HttpRequest] that contains all the details of the given
     *     request and the signature headers of the AWS signed request.
     */
    private fun mergeRequests(
            request: HttpRequest, awsSignedRequest: SdkHttpRequest)
            : HttpRequest {
        // Overwrite any headers with the same names already present.
        return request.toBuilder()
            .replaceAllHeaders(awsSignedRequest.headers())
            .build()
    }

    /**
     * A builder for [BedrockCredentials].
     *
     * The credentials can be extracted from the environment and set on the
     * builder by calling [fromEnv] before calling [build] to create the
     * [BedrockCredentials].
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
         * Creates new [BedrockCredentials] with credential values and the AWS
         * region resolved automatically. Sources for the values may include
         * system properties, environment variables, AWS CLI configuration
         * files, AWS SSO resources, and more.
         *
         * If available from the environment, this method will identify the
         * following AWS credentials:
         *
         *  * Access key ID
         *  * Secret access key
         *  * Session token
         *  * Region
         *
         * The session token is optional, but the other credentials *must* all
         * be set in the environment or in the appropriate AWS configuration
         * files, or an error will occur. See the AWS Bedrock documentation for
         * details on how to configure these credentials.
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
                    "No AWS access key ID or AWS secret access key found.", e)
            }
            try {
                region = DefaultAwsRegionProviderChain.builder().build().region
            } catch (e: Exception) {
                throw AnthropicException("No AWS region found.", e)
            }
        }

        /**
         * Builds the new [BedrockCredentials] from the data provided to the
         * builder.
         *
         * @return The new credentials.
         *
         * @throws AnthropicException If the credentials have not been resolved
         *     from the environment.
         */
        fun build(): BedrockCredentials {
            // Copy properties to local variables for thread safety.
            val myAwsCredentials = awsCredentials
            val myRegion = region

            if (myAwsCredentials == null || myRegion == null) {
                throw AnthropicException(
                    "No credentials set from the environment. Call fromEnv().")
            }

            return BedrockCredentials(myAwsCredentials, myRegion)
        }
    }
}