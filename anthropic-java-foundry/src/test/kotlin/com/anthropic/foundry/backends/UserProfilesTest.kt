package com.anthropic.foundry.backends

import com.anthropic.backends.Backend
import com.anthropic.client.AnthropicClient
import com.anthropic.client.AnthropicClientImpl
import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.http.HttpClient
import com.anthropic.core.http.HttpMethod
import com.anthropic.core.http.HttpRequest
import com.anthropic.core.http.HttpResponse
import com.anthropic.models.messages.MessageCountTokensParams
import com.anthropic.models.messages.MessageCreateParams
import java.util.concurrent.CompletableFuture
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test

/** A client of a Foundry backend manages user profiles and sends the profile header. */
internal class UserProfilesTest {

    companion object {
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

    private fun preparedRequest(call: (AnthropicClient) -> Unit): HttpRequest {
        val backend =
            FoundryBackend.builder().apiKey("AKIAIOSFODNN7EXAMPLE").resource("my-resource").build()
        val httpClient = PreparingHttpClient(backend)
        val client =
            AnthropicClientImpl(
                ClientOptions.builder()
                    .httpClient(httpClient)
                    .baseUrl(backend.baseUrl())
                    .maxRetries(0)
                    .build()
            )

        assertThatThrownBy { call(client) }.isExactlyInstanceOf(Stopped::class.java)

        return httpClient.prepared!!
    }

    private fun assertPrepared(
        method: HttpMethod,
        vararg pathSegments: String,
        call: (AnthropicClient) -> Unit,
    ) {
        val request = preparedRequest(call)

        assertThat(request.method).isEqualTo(method)
        assertThat(request.pathSegments).containsExactly(*pathSegments)
    }

    @Test
    fun userProfiles_areSent() {
        assertPrepared(HttpMethod.POST, "v1", "user_profiles") { it.userProfiles().create() }
        assertPrepared(HttpMethod.GET, "v1", "user_profiles") { it.userProfiles().list() }
        assertPrepared(HttpMethod.GET, "v1", "user_profiles", USER_PROFILE_ID) {
            it.userProfiles().retrieve(USER_PROFILE_ID)
        }
        assertPrepared(HttpMethod.POST, "v1", "user_profiles", USER_PROFILE_ID) {
            it.userProfiles().update(USER_PROFILE_ID)
        }
        assertPrepared(HttpMethod.DELETE, "v1", "user_profiles", USER_PROFILE_ID) {
            it.userProfiles().delete(USER_PROFILE_ID)
        }
    }

    @Test
    fun messages_sendTheUserProfileHeader() {
        val request = preparedRequest {
            it.messages()
                .create(
                    MessageCreateParams.builder()
                        .model("claude-sonnet-4-5")
                        .maxTokens(1)
                        .addUserMessage("hello")
                        .userProfileId(USER_PROFILE_ID)
                        .build()
                )
        }

        assertThat(request.pathSegments).containsExactly("v1", "messages")
        assertThat(request.headers.values(HEADER_USER_PROFILE_ID)).containsExactly(USER_PROFILE_ID)
    }

    @Test
    fun countTokens_sendsTheUserProfileHeader() {
        val request = preparedRequest {
            it.messages()
                .countTokens(
                    MessageCountTokensParams.builder()
                        .model("claude-sonnet-4-5")
                        .addUserMessage("hello")
                        .userProfileId(USER_PROFILE_ID)
                        .build()
                )
        }

        assertThat(request.pathSegments).containsExactly("v1", "messages", "count_tokens")
        assertThat(request.headers.values(HEADER_USER_PROFILE_ID)).containsExactly(USER_PROFILE_ID)
    }
}
