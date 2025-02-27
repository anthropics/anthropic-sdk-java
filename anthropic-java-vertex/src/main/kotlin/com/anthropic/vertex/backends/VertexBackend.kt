package com.anthropic.vertex.backends

import com.anthropic.backends.Backend
import com.anthropic.core.bodyToJson
import com.anthropic.core.checkRequired
import com.anthropic.core.http.HttpRequest
import com.anthropic.core.json
import com.anthropic.core.jsonMapper
import com.anthropic.errors.AnthropicException
import com.anthropic.errors.AnthropicInvalidDataException
import com.anthropic.vertex.backends.VertexBackend.Builder
import com.fasterxml.jackson.databind.node.ObjectNode
import com.google.auth.oauth2.GoogleCredentials

/**
 * The Google Cloud Vertex AI backend that manages the Google OAuth2 credentials and other
 * information required to access an Anthropic AI model on Vertex AI and adapts requests to Vertex
 * AI's requirements.
 *
 * Google Cloud Vertex AI requires authentication credentials issued by Google. These can be
 * provided via environment variables, or other Google facilities, such as the Google Cloud CLI. The
 * application default credentials (ADC) can be resolved by calling [Builder.fromEnv]. For other
 * types of credentials, create them separately and supply them to [Builder.googleCredentials].
 *
 * See the Google Cloud documentation for details on how to configure Google credentials.
 */
class VertexBackend
private constructor(
    @get:JvmName("googleCredentials") val googleCredentials: GoogleCredentials,
    @get:JvmName("region") val region: String,
    @get:JvmName("project") val project: String,
) : Backend {

    private val jsonMapper = jsonMapper()

    companion object {
        private const val ANTHROPIC_VERSION = "vertex-2023-10-16"
        private const val ENV_REGION = "CLOUD_ML_REGION"
        private const val ENV_PROJECT = "ANTHROPIC_VERTEX_PROJECT_ID"
        private const val HEADER_AUTHORIZATION = "Authorization"

        @JvmStatic fun builder() = Builder()

        @JvmStatic fun fromEnv(): VertexBackend = builder().fromEnv().build()
    }

    override fun baseUrl(): String = "https://$region-aiplatform.googleapis.com"

    override fun prepareRequest(request: HttpRequest): HttpRequest {
        val pathSegments = request.pathSegments

        // Check that the request is valid. Unlike for Bedrock, Vertex *will*
        // still have a first "v1" path segment.
        if (pathSegments.isEmpty() || pathSegments[0] != "v1") {
            throw AnthropicInvalidDataException("Expected first 'v1' path segment.")
        }

        if (pathSegments.size <= 1) {
            throw AnthropicInvalidDataException("Missing service name from request URL.")
        }

        var isCountTokens = false

        when (pathSegments[1]) {
            "projects" -> {
                throw IllegalArgumentException("Request already prepared for Vertex.")
            }
            "messages" -> {
                if (pathSegments.size > 2) {
                    when (pathSegments[2]) {
                        "batches" ->
                            throw AnthropicException("Batch API is not supported for Vertex.")
                        "count_tokens" -> isCountTokens = true
                    } // For now, ignore any other path segments.
                }
            }
            "complete" -> {
                // Do nothing special.
            }
            else ->
                throw AnthropicException("Service is not supported for Vertex: ${pathSegments[1]}.")
        }

        val jsonBody: ObjectNode? = bodyToJson(jsonMapper, request.body)

        if (jsonBody != null) {
            jsonBody.put("anthropic_version", ANTHROPIC_VERSION)
        } else {
            throw AnthropicInvalidDataException("Request has no body.")
        }

        val endpoint: String

        if (isCountTokens) {
            endpoint = "count-tokens:rawPredict"
        } else {
            val model =
                jsonBody.remove("model")
                    ?: throw AnthropicInvalidDataException("No model found in body.")
            val modelId = model.asText()
            // For Vertex, the "stream" property must be retained in the body.
            // This differs from Bedrock where the property is removed.
            val isStream = jsonBody["stream"]?.asBoolean() ?: false
            val specifier = if (isStream) "streamRawPredict" else "rawPredict"

            endpoint = "$modelId:$specifier"
        }

        return request
            .toBuilder()
            .replaceAllPathSegments(
                "v1",
                "projects",
                project,
                "locations",
                region,
                "publishers",
                "anthropic",
                "models",
                endpoint,
            )
            .body(json(jsonMapper, jsonBody))
            .build()
    }

    override fun authorizeRequest(request: HttpRequest): HttpRequest {
        googleCredentials.refreshIfExpired()

        require(!request.headers.names().contains(HEADER_AUTHORIZATION)) {
            "Request already authorized for Vertex."
        }

        return request
            .toBuilder()
            .putHeader(HEADER_AUTHORIZATION, "Bearer ${googleCredentials.accessToken.tokenValue}")
            .build()
    }

    override fun close() {}

    /**
     * A builder for a [VertexBackend] used to connect an Anthropic client to an Anthropic model on
     * the Google Cloud Vertex AI backend service.
     *
     * The credentials can be extracted from the environment and set on the builder by calling
     * [fromEnv] before calling [build] to create the [VertexBackend]. Alternatively, set the Google
     * credentials, region and project explicitly via [googleCredentials], [region] and [project]
     * before calling [build].
     */
    class Builder internal constructor() {
        private var googleCredentials: GoogleCredentials? = null

        private var region: String? = null

        private var project: String? = null

        fun fromEnv() = apply {
            try {
                googleCredentials = GoogleCredentials.getApplicationDefault()
            } catch (e: Exception) {
                throw IllegalStateException("Google OAuth2 credentials could not be resolved.", e)
            }

            region =
                System.getenv(ENV_REGION)
                    ?: throw IllegalStateException(
                        "No region set in $ENV_REGION environment variable."
                    )

            project =
                System.getenv(ENV_PROJECT)
                    ?: throw IllegalStateException(
                        "No project set in $ENV_PROJECT environment variable."
                    )
        }

        fun googleCredentials(googleCredentials: GoogleCredentials) = apply {
            this.googleCredentials = googleCredentials
        }

        fun region(region: String) = apply { this.region = region }

        fun project(project: String) = apply { this.project = project }

        fun build(): VertexBackend =
            VertexBackend(
                checkRequired("googleCredentials", googleCredentials),
                checkRequired("region", region),
                checkRequired("project", project),
            )
    }
}
