package com.anthropic.aws.backends

import com.anthropic.backends.Backend
import com.anthropic.client.AnthropicClient
import com.anthropic.client.okhttp.AnthropicOkHttpClient
import com.anthropic.core.http.HttpMethod
import com.anthropic.core.http.HttpRequest
import com.anthropic.errors.AnthropicException
import com.anthropic.models.beta.userprofiles.actionurls.BetaUserProfileEnrollEndUserAction
import com.anthropic.models.messages.MessageCountTokensParams
import com.anthropic.models.messages.MessageCreateParams
import com.anthropic.models.userprofiles.actionurls.ActionUrlCreateParams
import com.anthropic.models.userprofiles.actionurls.UserProfileEnrollEndUserAction
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials
import software.amazon.awssdk.regions.Region

/**
 * A client of an AWS backend manages user profiles and sends the profile header, but has no action
 * URLs.
 */
internal class UserProfilesTest {

    companion object {
        private const val HOST = "https://aws-external-anthropic.us-east-1.api.aws"
        private const val MODEL = "claude-sonnet-4-5"
        private const val USER_PROFILE_ID = "uprof_123"
        private const val WORKSPACE_ID = "wrkspc_1"
        private const val HEADER_USER_PROFILE_ID = "anthropic-user-profile-id"
        private const val ACTION_URLS_NOT_SUPPORTED =
            "User profile action URLs are not supported for AWS."
    }

    private class Captured : RuntimeException()

    /** Keeps the request that [backend] authorized, and fails the call before it is sent. */
    private class CapturingBackend(private val backend: Backend) : Backend by backend {
        var authorized: HttpRequest? = null

        override fun authorizeRequest(request: HttpRequest): HttpRequest {
            authorized = backend.authorizeRequest(request)
            throw Captured()
        }
    }

    private val backend =
        CapturingBackend(
            AwsBackend.builder()
                .awsCredentials(
                    AwsBasicCredentials.create(
                        "AKIAIOSFODNN7EXAMPLE",
                        "wJalrXUtnFEMI/K7MDENG/bPxRfiCYEXAMPLEKEY",
                    )
                )
                .region(Region.US_EAST_1)
                .workspaceId(WORKSPACE_ID)
                .build()
        )

    private val client: AnthropicClient =
        AnthropicOkHttpClient.builder().backend(backend).maxRetries(0).build()

    @AfterEach
    fun tearDown() {
        client.close()
    }

    private fun signedRequest(call: () -> Unit): HttpRequest {
        assertThatThrownBy { call() }.isExactlyInstanceOf(Captured::class.java)

        val request = backend.authorized!!
        assertThat(request.headers.values("Authorization").single()).startsWith("AWS4-HMAC-SHA256")
        assertThat(request.headers.values("anthropic-workspace-id")).containsExactly(WORKSPACE_ID)
        return request
    }

    private fun assertSigned(method: HttpMethod, url: String, call: () -> Unit) {
        val request = signedRequest(call)

        assertThat(request.method).isEqualTo(method)
        assertThat(request.baseUrl).isEqualTo(url)
    }

    @Test
    fun userProfiles_areSigned() {
        val collection = "$HOST/v1/user_profiles"
        val profile = "$collection/$USER_PROFILE_ID"

        assertSigned(HttpMethod.POST, collection) { client.userProfiles().create() }
        assertSigned(HttpMethod.GET, collection) { client.userProfiles().list() }
        assertSigned(HttpMethod.GET, profile) { client.userProfiles().retrieve(USER_PROFILE_ID) }
        assertSigned(HttpMethod.POST, profile) { client.userProfiles().update(USER_PROFILE_ID) }
        assertSigned(HttpMethod.DELETE, profile) { client.userProfiles().delete(USER_PROFILE_ID) }
    }

    @Test
    fun messages_sendTheUserProfileHeader() {
        val request = signedRequest {
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

        assertThat(request.baseUrl).isEqualTo("$HOST/v1/messages")
        assertThat(request.headers.values(HEADER_USER_PROFILE_ID)).containsExactly(USER_PROFILE_ID)
    }

    @Test
    fun countTokens_sendsTheUserProfileHeader() {
        val request = signedRequest {
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

        assertThat(request.baseUrl).isEqualTo("$HOST/v1/messages/count_tokens")
        assertThat(request.headers.values(HEADER_USER_PROFILE_ID)).containsExactly(USER_PROFILE_ID)
    }

    @Test
    fun actionUrls_areNotSupported() {
        assertThatThrownBy {
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
            .isExactlyInstanceOf(AnthropicException::class.java)
            .hasMessage(ACTION_URLS_NOT_SUPPORTED)
        assertThat(backend.authorized).isNull()
    }

    @Test
    fun betaActionUrls_areNotSupported() {
        assertThatThrownBy {
                client
                    .beta()
                    .userProfiles()
                    .actionUrls()
                    .create(
                        USER_PROFILE_ID,
                        com.anthropic.models.beta.userprofiles.actionurls.ActionUrlCreateParams
                            .builder()
                            .action(BetaUserProfileEnrollEndUserAction.builder().build())
                            .build(),
                    )
            }
            .isExactlyInstanceOf(AnthropicException::class.java)
            .hasMessage(ACTION_URLS_NOT_SUPPORTED)
        assertThat(backend.authorized).isNull()
    }

    @Test
    fun userProfileNamedLikeActionUrls_isSent() {
        assertSigned(HttpMethod.GET, "$HOST/v1/user_profiles/action_urls") {
            client.userProfiles().retrieve("action_urls")
        }
    }
}
