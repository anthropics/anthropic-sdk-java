package com.anthropic.aws.auth

import com.anthropic.core.http.HttpRequest
import com.anthropic.errors.AnthropicInvalidDataException
import java.io.ByteArrayOutputStream
import software.amazon.awssdk.auth.credentials.AwsCredentials
import software.amazon.awssdk.http.ContentStreamProvider
import software.amazon.awssdk.http.SdkHttpMethod
import software.amazon.awssdk.http.SdkHttpRequest
import software.amazon.awssdk.http.auth.aws.signer.AwsV4HttpSigner
import software.amazon.awssdk.http.auth.spi.signer.SignRequest
import software.amazon.awssdk.identity.spi.AwsCredentialsIdentity
import software.amazon.awssdk.regions.Region

/**
 * Shared AWS authentication utilities used by both the AWS gateway backend and the Bedrock Mantle
 * backend. Provides SigV4 request signing and API key authorization.
 *
 * **This is an internal utility class.** Although it is in a published package, its API is not
 * stable and may change without notice. Do not depend on it directly.
 *
 * This object is thread-safe. The cached [AwsV4HttpSigner] instance is stateless and safe for
 * concurrent use, and all methods are pure functions over their arguments.
 */
object AwsAuthUtil {

    private const val HEADER_CONTENT_TYPE = "content-type"
    private const val HEADER_AUTHORIZATION = "authorization"
    private val signer = AwsV4HttpSigner.create()

    /**
     * Signs an HTTP request using AWS SigV4 with the given credentials, region, and service name.
     *
     * The `connection` header is excluded from signing because it may be stripped by proxies and
     * must not be part of the SigV4 signature.
     *
     * A `content-type` header is only required when the request has a body. Body-less requests
     * (e.g. `GET`, `DELETE`) are signed without one, as SigV4 does not require it.
     */
    @JvmStatic
    fun signRequestWithSigV4(
        request: HttpRequest,
        credentials: AwsCredentials,
        region: Region,
        serviceName: String,
    ): HttpRequest {
        val awsSignRequest =
            SdkHttpRequest.builder()
                .uri(request.baseUrl)
                .method(SdkHttpMethod.fromValue(request.method.toString()))
                .apply {
                    if (request.headers.values(HEADER_CONTENT_TYPE).isEmpty()) {
                        val contentType = request.body?.contentType()
                        if (contentType != null) {
                            appendHeader(HEADER_CONTENT_TYPE, contentType)
                        } else if (request.body != null) {
                            throw AnthropicInvalidDataException(
                                "Request has a body but no content type."
                            )
                        }
                        // Body-less request (e.g. GET, DELETE): no content type required.
                    }
                    request.headers.names().forEach { name ->
                        // The `connection` header may be stripped by proxies, so it must
                        // not be included in the SigV4 signature.
                        if (!name.equals("connection", ignoreCase = true)) {
                            request.headers.values(name).forEach { value ->
                                appendHeader(name, value)
                            }
                        }
                    }
                }
                .build()

        val bodyData = ByteArrayOutputStream()
        request.body?.writeTo(bodyData)

        val awsSignedRequest: SdkHttpRequest =
            signer
                .sign { r: SignRequest.Builder<AwsCredentialsIdentity?> ->
                    r.identity(credentials)
                        .request(awsSignRequest)
                        .payload(ContentStreamProvider.fromByteArray(bodyData.toByteArray()))
                        .putProperty(AwsV4HttpSigner.SERVICE_SIGNING_NAME, serviceName)
                        .putProperty(AwsV4HttpSigner.REGION_NAME, region.id())
                }
                .request()

        return request.toBuilder().replaceAllHeaders(awsSignedRequest.headers()).build()
    }

    /** Authorizes an HTTP request using an API key sent as a Bearer token. */
    @JvmStatic
    fun authorizeWithApiKey(request: HttpRequest, apiKey: String): HttpRequest =
        request.toBuilder().replaceHeaders(HEADER_AUTHORIZATION, "Bearer $apiKey").build()
}
