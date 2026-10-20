package com.anthropic.vertex.backends

import com.anthropic.backends.Backend
import com.anthropic.client.AnthropicClient
import com.anthropic.client.AnthropicClientImpl
import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.http.HttpClient
import com.anthropic.core.http.HttpRequest
import com.anthropic.core.http.HttpResponse
import com.anthropic.errors.AnthropicException
import com.anthropic.models.messages.MessageCountTokensParams
import com.anthropic.models.messages.MessageCreateParams
import com.anthropic.models.userprofiles.actionurls.ActionUrlCreateParams
import com.anthropic.models.userprofiles.actionurls.UserProfileEnrollEndUserAction
import com.google.auth.oauth2.AccessToken
import com.google.auth.oauth2.GoogleCredentials
import java.util.concurrent.CompletableFuture
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test

/** A client of a Vertex AI backend sends the profile header but does not manage user profiles. */
internal class UserProfilesTest {

    companion object {
        private const val MODEL = "claude-sonnet-4-5"
        private const val USER_PROFILE_ID = "uprof_123"
        private const val HEADER_USER_PROFILE_ID = "anthropic-user-profile-id"
    }

    private class Stopped : RuntimeException()

    /** Keeps the request that [backend] prepared, and fails the call before it is sent. */
    private class PreparingHttpClient(private val backend: Backend) : HttpClient {
        var prepared: HttpRequest? = null

        override fun execute(request: HttpRequest, requestOptions: RequestOptions): HttpResponse {
            prepared = backend.prepareRequest(request)
            throw Stopped()
        }

        override fun executeAsync(
            request: HttpRequest,
            requestOptions: RequestOptions,
        ): CompletableFuture<HttpResponse> = throw Stopped()

        override fun close() {}
    }

    private val backend =
        VertexBackend.builder()
            .googleCredentials(
                GoogleCredentials.create(
                    AccessToken.newBuilder().setTokenValue("not-a-real-token").build()
                )
            )
            .region("us-central1")
            .project("vertex-project-12345-b6")
            .build()

    private val httpClient = PreparingHttpClient(backend)

    private val client: AnthropicClient =
        AnthropicClientImpl(
            ClientOptions.builder()
                .httpClient(httpClient)
                .baseUrl(backend.baseUrl())
                .maxRetries(0)
                .build()
        )

    private fun assertNotSupported(call: () -> Unit) {
        assertThatThrownBy { call() }
            .isExactlyInstanceOf(AnthropicException::class.java)
            .hasMessage("Service is not supported for Vertex: user_profiles.")
    }

    @Test
    fun userProfiles_areNotSupported() {
        assertNotSupported { client.userProfiles().create() }
        assertNotSupported { client.userProfiles().list() }
        assertNotSupported { client.userProfiles().retrieve(USER_PROFILE_ID) }
        assertNotSupported { client.userProfiles().update(USER_PROFILE_ID) }
        assertNotSupported { client.userProfiles().delete(USER_PROFILE_ID) }
        assertNotSupported {
            client
                .userProfiles()
                .actionUrls()
                .create(
                    USER_PROFILE_ID,
                    ActionUrlCreateParams.builder()
                        .action(UserProfileEnrollEndUserAction.builder().build())
                        .build(),
                )
        }
        assertNotSupported { client.beta().userProfiles().create() }
    }

    @Test
    fun messages_sendTheUserProfileHeader() {
        assertThatThrownBy {
                client
                    .messages()
                    .create(
                        MessageCreateParams.builder()
                            .model(MODEL)
                            .maxTokens(1)
                            .addUserMessage("hello")
                            .userProfileId(USER_PROFILE_ID)
                            .build()
                    )
            }
            .isExactlyInstanceOf(Stopped::class.java)

        val request = httpClient.prepared!!
        assertThat(request.pathSegments.last()).isEqualTo("$MODEL:rawPredict")
        assertThat(request.headers.values(HEADER_USER_PROFILE_ID)).containsExactly(USER_PROFILE_ID)
    }

    @Test
    fun countTokens_sendsTheUserProfileHeader() {
        assertThatThrownBy {
                client
                    .messages()
                    .countTokens(
                        MessageCountTokensParams.builder()
                            .model(MODEL)
                            .addUserMessage("hello")
                            .userProfileId(USER_PROFILE_ID)
                            .build()
                    )
            }
            .isExactlyInstanceOf(Stopped::class.java)

        val request = httpClient.prepared!!
        assertThat(request.pathSegments.last()).isEqualTo("count-tokens:rawPredict")
        assertThat(request.headers.values(HEADER_USER_PROFILE_ID)).containsExactly(USER_PROFILE_ID)
    }
}
