package com.anthropic.vertex.backends

import com.anthropic.backends.Backend
import com.anthropic.core.checkRequired
import com.anthropic.core.http.HttpRequest
import com.anthropic.core.http.HttpRequestBody
import com.anthropic.core.json
import com.anthropic.core.jsonMapper
import com.anthropic.errors.AnthropicException
import com.anthropic.vertex.backends.VertexBackend.Builder
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.node.ObjectNode
import com.google.auth.oauth2.GoogleCredentials
import java.io.ByteArrayOutputStream

/**
 * The Google Cloud Vertex AI backend that manages the Google OAuth2 credentials
 * and other information required to access an Anthropic AI model on Vertex AI
 * and adapts requests to Vertex AI's requirements.
 *
 * Google Cloud Vertex AI requires authentication credentials issued by Google.
 * These can be provided via environment variables, or other Google facilities,
 * such as the Google Cloud CLI. The application default credentials (ADC) can
 * be resolved by calling [Builder.fromEnv]. For other types of credentials,
 * create them separately and supply them to [Builder.googleCredentials].
 */
class VertexBackend private constructor(
    /**
     * The Google OAuth2 credentials required to access the Vertex service.
     */
    @get:JvmName("googleCredentials") val googleCredentials: GoogleCredentials,

    /**
     * The name of the Google Cloud region hosting the Vertex service.
     */
    @get:JvmName("region") val region: String,

    /**
     * The name of the Google Cloud project on the Vertex service.
     */
    @get:JvmName("project") val project: String,
) : Backend {

    /**
     * A mapper for serializing and deserializing JSON data. For efficiency,
     * this is created once and then reused for the life of this instance. The
     * instance is thread-safe.
     */
    private val jsonMapper = jsonMapper()

    companion object {
        private const val ANTHROPIC_VERSION = "vertex-2023-10-16"

        /**
         * The name of the environment variable used to retrieve the Google
         * Cloud region for the Vertex AI service.
         */
        private const val ENV_REGION = "CLOUD_ML_REGION"

        /**
         * The name of the environment variable used to retrieve the Google
         * Cloud project ID for the Vertex AI service.
         */
        private const val ENV_PROJECT = "ANTHROPIC_VERTEX_PROJECT_ID"

        /**
         * The name of the header used to authorize requests to Vertex AI. A new
         * header will be added when authorizing requests. The presence of an
         * existing header with the same name will trigger an error.
         */
        private const val HEADER_AUTHORIZATION = "Authorization"

        /**
         * Creates a new builder for configuring and creating a new instance of
         * [VertexBackend].
         */
        @JvmStatic fun builder() = Builder()

        /**
         * Creates new [VertexBackend] with credentials resolved from the
         * environment or configuration files.
         *
         * This is a convenience method that is the equivalent of calling:
         *
         * ```
         * VertexBackend.builder().fromEnv().build()
         * ```
         *
         * See [Builder.fromEnv] for more details.
         *
         * @throws AnthropicException See [Builder.build] for details.
         */
        @JvmStatic fun fromEnv(): VertexBackend {
            return builder().fromEnv().build()
        }

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
        @JvmSynthetic
        internal fun bodyToJson(
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
     * Gets the base URL for the Google Cloud Vertex AI service. The base URL
     * includes the Google Cloud region.
     */
    override fun baseUrl(): String = "https://$region-aiplatform.googleapis.com"

    /**
     * Prepares the request for use with Google Cloud Vertex AI.
     *
     * A number of changes are made to support the requirements of requests to
     * the Vertex AI backend:
     *
     * * The model ID is moved from the JSON body content to the path segments.
     * * The path segments are changed to identify the project, region and AI
     *     model endpoint.
     * * The Anthropic version is added to the JSON body content.
     *
     * @param request The request to prepare. This request will not be modified.
     *
     * @return A new request, copied from the given request, and then prepared
     *     and modified to conform to the Vertex requirements.
     *
     * @throws AnthropicException If the path segments describe an operation
     *     that is not yet supported by Anthropic models hosted on the Vertex AI
     *     service. If the JSON body is not present. If the request has already
     *     been prepared.
     */
    override fun prepareRequest(request: HttpRequest): HttpRequest {
        val pathSegments = request.pathSegments

        // Check that the request is valid has not been prepared already.
        if (pathSegments.isEmpty() || pathSegments[0] != "v1") {
            throw AnthropicException("Expected first 'v1' path segment.")
        }

        if (pathSegments.size <= 1) {
            throw AnthropicException("Missing service name from request URL.")
        }

        var isCountTokens = false

        when (pathSegments[1]) {
            "projects" -> {
                throw AnthropicException("Request already prepared for Vertex.")
            }
            "messages" -> {
                if (pathSegments.size > 2) {
                    when (pathSegments[2]) {
                        "batches" -> throw AnthropicException(
                            "Batch API is not supported for Vertex.")
                        "count_tokens" -> isCountTokens = true
                    } // For now, ignore any other path segments.
                }
            }
            "complete" -> {
                // Do nothing special.
            }
            else -> throw AnthropicException(
                "Service is not supported for Vertex: ${pathSegments[1]}.")
        }

        val jsonBody: ObjectNode? = bodyToJson(request.body, jsonMapper)

        if (jsonBody != null) {
            jsonBody.put("anthropic_version", ANTHROPIC_VERSION)
        } else {
            throw AnthropicException("Request has no body")
        }

        val endpoint: String

        if (isCountTokens) {
            endpoint = "count-tokens:rawPredict"
        } else {
            val model = jsonBody.remove("model")
                ?: throw AnthropicException("No model found in body.")
            val modelId = model.asText()
            // For Vertex, the "stream" property must be retained in the body.
            // This differs from Bedrock where the property is removed.
            val isStream = jsonBody["stream"]?.asBoolean() ?: false
            val specifier = if (isStream) "streamRawPredict" else "rawPredict"

            endpoint = "$modelId:$specifier"
        }

        return request.toBuilder()
            .replaceAllPathSegments(
                "v1",
                "projects", project,
                "locations", region,
                "publishers", "anthropic",
                "models", endpoint)
            .body(json(jsonMapper, jsonBody))
            .build()
    }

    /**
     * Authorizes a Google Cloud Vertex AI request. Requests to Vertex AI
     * services are authorized using the access token from the Google
     * credentials by adding a new `Authorization` header.
     *
     * @param request The request to be authorized. This will not be modified;
     *     it will be copied and the modified copy will be returned.
     *
     * @return A new request including the authorization header.
     *
     * @throws AnthropicException If the request has already been authorized as
     *     evidenced by the existing presence of an authorization header.
     */
    override fun authorizeRequest(request: HttpRequest): HttpRequest {
        googleCredentials.refreshIfExpired()

        if (request.headers.names().contains(HEADER_AUTHORIZATION)) {
            throw AnthropicException("Request is already authorized.")
        }

        return request.toBuilder()
            .putHeader(
                HEADER_AUTHORIZATION,
                "Bearer ${googleCredentials.accessToken.tokenValue}")
            .build()
    }

    /**
     * A builder for a [VertexBackend] used to connect an Anthropic client to a
     * Google Cloud Vertex AI backend service.
     *
     * The credentials can be extracted from the environment and set on the
     * builder by calling [fromEnv] before calling [build] to create the
     * [VertexBackend].Alternatively, set the Google credentials, region and
     * project explicitly via [googleCredentials], [region] and [project] before
     * calling [build].
     */
    class Builder internal constructor() {
        /**
         * The Google OAuth2 credentials required to access the Vertex AI
         * backend service.
         */
        private var googleCredentials: GoogleCredentials? = null

        /**
         * The name of the Google Cloud region hosting the Vertex service.
         */
        private var region: String? = null

        /**
         * The ID (name) of the Google Cloud project on the Vertex service.
         */
        private var project: String? = null

        /**
         * Creates a new [VertexBackend] with Google OAuth2 credentials and the
         * Google Cloud region and project ID resolved automatically. Sources
         * for the values may include environment variables and Google Cloud
         * configuration files from the Google Cloud CLI.
         *
         * If available from the environment, this method will identify the
         * following:
         *
         *  * Application Default Credentials for Google Cloud. These may be
         *      configured using the Google Cloud CLI. See the Google Cloud
         *      documentation for details on how to configure these credentials.
         *  * Google Cloud region from the `CLOUD_ML_REGION` environment
         *      variable.
         *  * Google Cloud project ID from the `ANTHROPIC_VERTEX_PROJECT_ID`
         *      environment variable.
         *
         * The credentials and environment variables *must* all be set or an
         * error will occur.
         *
         * This method is provided as a convenience. If its behavior does not
         * suit requirements, the credentials, region and project ID can all be
         * set explicitly via the other methods of this [Builder]. For example,
         * if credentials other than the application default credentials are
         * required, or if the region will be hard-coded, etc.
         *
         * If [fromEnv] is called after calling any of [googleCredentials],
         * [region] or [project], it will override the values of _all_ of those
         * properties. If any of the latter three methods is called after
         * [fromEnv], each will override only that element resolved by
         * [fromEnv].
         *
         * @throws AnthropicException If the application default credentials,
         *     the Google Cloud region, or the Vertex AI project ID cannot be
         *     resolved from the Google cloud configuration and the environment.
         */
        fun fromEnv() = apply {
            try {
                googleCredentials = GoogleCredentials.getApplicationDefault()
            } catch (e: Exception) {
                throw AnthropicException(
                    "Google OAuth2 credentials could not be resolved.", e)
            }

            region = System.getenv(ENV_REGION)
                ?: throw AnthropicException(
                    "No region set in $ENV_REGION environment variable.")

            project = System.getenv(ENV_PROJECT)
                ?: throw AnthropicException(
                    "No project set in $ENV_PROJECT environment variable.")
        }

        /**
         * Sets the Google Cloud OAuth2 credentials to use to authenticate and
         * authorize requests to the Vertex AI service.
         *
         * Alternatively, use [fromEnv] to resolve the credentials automatically
         * from the environment or local Google Cloud configuration.
         *
         * If this method is called after [fromEnv] it will override any Google
         * credentials resolved by that method. If this method is called before
         * [fromEnv], the latter will override the credentials passed here.
         *
         * @param googleCredentials The Google credentials. See the Google Cloud
         *     documentation for details on how to configure and resolve or
         *     create Google credentials.
         */
        fun googleCredentials(googleCredentials: GoogleCredentials) = apply {
            this.googleCredentials = googleCredentials
        }

        /**
         * Sets the Google Cloud region hosting the Vertex AI service.
         *
         * Alternatively, use [fromEnv] to resolve the region automatically from
         * the environment or local Google Cloud configuration.
         *
         * If this method is called after [fromEnv] it will override any region
         * resolved by that method. If this method is called before [fromEnv],
         * the latter will override the region passed here.
         *
         * @param region The Google Cloud region to be used.
         */
        fun region(region: String) = apply { this.region = region }

        /**
         * Sets the project ID for the Vertex AI service.
         *
         * Alternatively, use [fromEnv] to resolve the project ID automatically
         * from the environment or local Google Cloud configuration.
         *
         * If this method is called after [fromEnv] it will override any project
         * ID resolved by that method. If this method is called before
         * [fromEnv], the latter will override the project ID passed here.
         *
         * @param project The Vertex AI project ID to be used.
         */
        fun project(project: String) = apply { this.project = project }

        /**
         * Builds the new [VertexBackend] from the data provided to the builder.
         *
         * @throws IllegalStateException If the required Google credentials,
         *     Google Cloud region, or Google Cloud project ID have not been
         *     resolved from the environment by calling [fromEnv[; or,
         *     alternatively, have not been passed explicitly by calling
         *     [googleCredentials], [region] or [project].
         */
        fun build(): VertexBackend = VertexBackend(
            checkRequired("googleCredentials", googleCredentials),
            checkRequired("region", region),
            checkRequired("project", project),
        )
    }
}
