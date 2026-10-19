package com.anthropic.bedrock.backends

import com.anthropic.backends.Backend
import com.anthropic.client.AnthropicClient
import com.anthropic.client.okhttp.AnthropicOkHttpClient
import com.anthropic.core.http.HttpRequest
import com.anthropic.core.http.bodyToJson
import com.anthropic.core.jsonMapper
import com.anthropic.errors.AnthropicException
import com.anthropic.models.messages.MessageCountTokensParams
import com.anthropic.models.messages.MessageCreateParams
import com.anthropic.models.userprofiles.actionurls.ActionUrlCreateParams
import com.anthropic.models.userprofiles.actionurls.UserProfileEnrollEndUserAction
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials
import software.amazon.awssdk.regions.Region

/**
 * Neither Bedrock backend manages user profiles. Bedrock Mantle takes the profile ID on Messages,
 * in the body.
 */
internal class UserProfilesTest {

    companion object {
        private const val MANTLE_HOST = "https://bedrock-mantle.us-east-1.api.aws/anthropic"
        private const val MODEL = "claude-sonnet-4-5"
        private const val USER_PROFILE_ID = "uprof_123"
        private const val HEADER_USER_PROFILE_ID = "anthropic-user-profile-id"
        private val CREDENTIALS =
            AwsBasicCredentials.create(
                "AKIAIOSFODNN7EXAMPLE",
                "wJalrXUtnFEMI/K7MDENG/bPxRfiCYEXAMPLEKEY",
            )
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

    private fun bedrock(): Backend =
        BedrockBackend.builder().awsCredentials(CREDENTIALS).region(Region.US_EAST_1).build()

    private fun mantle(): Backend =
        BedrockMantleBackend.builder().awsCredentials(CREDENTIALS).region(Region.US_EAST_1).build()

    private fun assertUserProfilesNotSupported(backend: Backend, message: String) {
        val capturing = CapturingBackend(backend)
        val client = AnthropicOkHttpClient.builder().backend(capturing).maxRetries(0).build()
        val calls: List<() -> Unit> =
            listOf(
                { client.userProfiles().create() },
                { client.userProfiles().list() },
                { client.userProfiles().retrieve(USER_PROFILE_ID) },
                { client.userProfiles().update(USER_PROFILE_ID) },
                { client.userProfiles().delete(USER_PROFILE_ID) },
                {
                    client
                        .userProfiles()
                        .actionUrls()
                        .create(
                            USER_PROFILE_ID,
                            ActionUrlCreateParams.builder()
                                .action(UserProfileEnrollEndUserAction.builder().build())
                                .build(),
                        )
                },
                { client.beta().userProfiles().create() },
            )

        try {
            calls.forEach { call ->
                assertThatThrownBy { call() }
                    .isExactlyInstanceOf(AnthropicException::class.java)
                    .hasMessage(message)
            }
        } finally {
            client.close()
        }
        assertThat(capturing.authorized).isNull()
    }

    /** The request that a client of the Mantle backend signs for [call]. */
    private fun signedByMantle(call: (AnthropicClient) -> Unit): HttpRequest {
        val capturing = CapturingBackend(mantle())
        val client = AnthropicOkHttpClient.builder().backend(capturing).maxRetries(0).build()
        try {
            assertThatThrownBy { call(client) }.isExactlyInstanceOf(Captured::class.java)
        } finally {
            client.close()
        }
        return capturing.authorized!!
    }

    private fun assertProfileIdInBody(request: HttpRequest, url: String) {
        assertThat(request.baseUrl).isEqualTo(url)
        assertThat(request.headers.names()).doesNotContain(HEADER_USER_PROFILE_ID)
        assertThat(bodyToJson(jsonMapper(), request.body)!!.get("user_profile_id").asText())
            .isEqualTo(USER_PROFILE_ID)
    }

    @Test
    fun bedrock_userProfilesAreNotSupported() =
        assertUserProfilesNotSupported(
            bedrock(),
            "Service is not supported for Bedrock: user_profiles.",
        )

    @Test
    fun mantle_userProfilesAreNotSupported() =
        assertUserProfilesNotSupported(
            mantle(),
            "Service is not supported for Bedrock Mantle: user_profiles.",
        )

    @Test
    fun mantle_messagesSendTheProfileIdInTheBody() {
        val request = signedByMantle {
            it.messages()
                .create(
                    MessageCreateParams.builder()
                        .model(MODEL)
                        .maxTokens(1)
                        .addUserMessage("hello")
                        .userProfileId(USER_PROFILE_ID)
                        .build()
                )
        }

        assertProfileIdInBody(request, "$MANTLE_HOST/v1/messages")
    }

    @Test
    fun mantle_betaMessagesSendTheProfileIdInTheBody() {
        val request = signedByMantle {
            it.beta()
                .messages()
                .create(
                    com.anthropic.models.beta.messages.MessageCreateParams.builder()
                        .model(MODEL)
                        .maxTokens(1)
                        .addUserMessage("hello")
                        .userProfileId(USER_PROFILE_ID)
                        .build()
                )
        }

        assertProfileIdInBody(request, "$MANTLE_HOST/v1/messages?beta=true")
    }

    @Test
    fun mantle_countTokensSendsTheProfileIdInTheBody() {
        val request = signedByMantle {
            it.messages()
                .countTokens(
                    MessageCountTokensParams.builder()
                        .model(MODEL)
                        .addUserMessage("hello")
                        .userProfileId(USER_PROFILE_ID)
                        .build()
                )
        }

        assertProfileIdInBody(request, "$MANTLE_HOST/v1/messages/count_tokens")
    }
}
