package com.anthropic.googlecloud.backends

import com.anthropic.core.http.HttpMethod
import com.anthropic.core.http.HttpRequest
import com.google.auth.oauth2.AccessToken
import com.google.auth.oauth2.GoogleCredentials
import com.google.auth.oauth2.ServiceAccountCredentials
import java.util.function.Supplier
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatNoException
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.parallel.ResourceLock
import org.mockito.Mockito

@ResourceLock("environment")
internal class GoogleCloudBackendTest {
    companion object {
        private const val TOKEN = "test-google-token"
        private const val PROJECT = "test-project"
        private const val LOCATION = "us-central1"
        private const val WORKSPACE_ID = "ws-12345"
        private const val CUSTOM_BASE_URL = "https://gateway.example.com/v1"

        private const val ENV_PROJECT = "ANTHROPIC_GOOGLE_CLOUD_PROJECT"
        private const val ENV_PROJECT_FALLBACK = "GOOGLE_CLOUD_PROJECT"
        private const val ENV_LOCATION = "ANTHROPIC_GOOGLE_CLOUD_LOCATION"
        private const val ENV_WORKSPACE_ID = "ANTHROPIC_GOOGLE_CLOUD_WORKSPACE_ID"
        private const val ENV_BASE_URL = "ANTHROPIC_GOOGLE_CLOUD_BASE_URL"

        private val TOKEN_SUPPLIER: Supplier<String> = Supplier { TOKEN }
    }

    // ---------------------------------------------------------------------------------------------
    // Builder validation
    // ---------------------------------------------------------------------------------------------

    @Test
    fun builderMissingEverything() {
        assertThatThrownBy { GoogleCloudBackend.builder().build() }
            .isExactlyInstanceOf(IllegalStateException::class.java)
            .hasMessageContaining("No Google credentials found")
    }

    @Test
    fun builderMissingWorkspaceId() {
        assertThatThrownBy {
                GoogleCloudBackend.builder()
                    .bearerTokenSupplier(TOKEN_SUPPLIER)
                    .project(PROJECT)
                    .location(LOCATION)
                    .build()
            }
            .isExactlyInstanceOf(IllegalStateException::class.java)
            .hasMessageContaining("Workspace ID is required")
            .hasMessageContaining(ENV_WORKSPACE_ID)
    }

    @Test
    fun builderMissingProject() {
        assertThatThrownBy {
                GoogleCloudBackend.builder()
                    .bearerTokenSupplier(TOKEN_SUPPLIER)
                    .workspaceId(WORKSPACE_ID)
                    .location(LOCATION)
                    .build()
            }
            .isExactlyInstanceOf(IllegalStateException::class.java)
            .hasMessageContaining("No project found")
            .hasMessageContaining(ENV_PROJECT)
    }

    @Test
    fun builderMissingLocationDefaultsToGlobal() {
        val backend =
            GoogleCloudBackend.builder()
                .bearerTokenSupplier(TOKEN_SUPPLIER)
                .workspaceId(WORKSPACE_ID)
                .project(PROJECT)
                .build()

        assertThat(backend.location()).hasValue("global")
        assertThat(backend.baseUrl()).contains("/locations/global/")
    }

    @Test
    fun bearerTokenSupplierWinsOverGoogleCredentials() {
        val googleCredentials = Mockito.spy(credentials("creds-token"))
        val backend =
            GoogleCloudBackend.builder()
                .googleCredentials(googleCredentials)
                .bearerTokenSupplier(TOKEN_SUPPLIER)
                .workspaceId(WORKSPACE_ID)
                .project(PROJECT)
                .location(LOCATION)
                .build()

        val authorized = backend.authorizeRequest(createRequest())

        assertThat(authorized.headers.values("Authorization")).containsExactly("Bearer $TOKEN")
        Mockito.verify(googleCredentials, Mockito.never()).refreshIfExpired()
        Mockito.verify(googleCredentials, Mockito.never()).accessToken
    }

    @Test
    fun builderSkipAuthAndCredentialsMutuallyExclusive() {
        assertThatThrownBy {
                GoogleCloudBackend.builder()
                    .skipAuth(true)
                    .googleCredentials(credentials())
                    .baseUrl(CUSTOM_BASE_URL)
                    .build()
            }
            .isExactlyInstanceOf(IllegalStateException::class.java)
            .hasMessageContaining("`skipAuth` cannot be set together with")
    }

    // ---------------------------------------------------------------------------------------------
    // Precedence: arg > env > auto
    // ---------------------------------------------------------------------------------------------

    @Test
    fun projectArgBeatsEnv() {
        val builder = mockBuilder(project = "env-project", projectFallback = "fallback-project")
        val backend =
            builder
                .project(PROJECT)
                .location(LOCATION)
                .workspaceId(WORKSPACE_ID)
                .bearerTokenSupplier(TOKEN_SUPPLIER)
                .fromEnv()
                .build()

        assertThat(backend.project()).hasValue(PROJECT)
        // Lower tiers verifiably not consulted.
        Mockito.verify(builder, Mockito.never()).getEnv(ENV_PROJECT)
        Mockito.verify(builder, Mockito.never()).getEnv(ENV_PROJECT_FALLBACK)
    }

    @Test
    fun projectEnvBeatsFallbackEnv() {
        val backend =
            mockBuilder(project = "env-project", projectFallback = "fallback-project")
                .location(LOCATION)
                .workspaceId(WORKSPACE_ID)
                .bearerTokenSupplier(TOKEN_SUPPLIER)
                .fromEnv()
                .build()

        assertThat(backend.project()).hasValue("env-project")
    }

    @Test
    fun projectFallbackEnvUsedWhenPrimaryAbsent() {
        val backend =
            mockBuilder(projectFallback = "fallback-project")
                .location(LOCATION)
                .workspaceId(WORKSPACE_ID)
                .bearerTokenSupplier(TOKEN_SUPPLIER)
                .fromEnv()
                .build()

        assertThat(backend.project()).hasValue("fallback-project")
    }

    @Test
    fun projectFromServiceAccountCredentials() {
        val saCredentials = Mockito.mock(ServiceAccountCredentials::class.java)
        Mockito.`when`(saCredentials.projectId).thenReturn("sa-project")

        val backend =
            mockBuilder(adc = saCredentials)
                .location(LOCATION)
                .workspaceId(WORKSPACE_ID)
                .fromEnv()
                .build()

        assertThat(backend.project()).hasValue("sa-project")
    }

    @Test
    fun projectEnvBeatsServiceAccountCredentials() {
        val saCredentials = Mockito.mock(ServiceAccountCredentials::class.java)
        Mockito.`when`(saCredentials.projectId).thenReturn("sa-project")

        val backend =
            mockBuilder(project = "env-project", adc = saCredentials)
                .location(LOCATION)
                .workspaceId(WORKSPACE_ID)
                .fromEnv()
                .build()

        assertThat(backend.project()).hasValue("env-project")
    }

    @Test
    fun locationArgBeatsEnv() {
        val builder = mockBuilder(location = "env-location")
        val backend =
            builder
                .location(LOCATION)
                .project(PROJECT)
                .workspaceId(WORKSPACE_ID)
                .bearerTokenSupplier(TOKEN_SUPPLIER)
                .fromEnv()
                .build()

        assertThat(backend.location()).hasValue(LOCATION)
        Mockito.verify(builder, Mockito.never()).getEnv(ENV_LOCATION)
    }

    @Test
    fun locationFromEnv() {
        val backend =
            mockBuilder(location = "env-location")
                .project(PROJECT)
                .workspaceId(WORKSPACE_ID)
                .bearerTokenSupplier(TOKEN_SUPPLIER)
                .fromEnv()
                .build()

        assertThat(backend.location()).hasValue("env-location")
    }

    @Test
    fun workspaceIdArgBeatsEnv() {
        val backend =
            mockBuilder(workspaceId = "env-ws")
                .workspaceId(WORKSPACE_ID)
                .project(PROJECT)
                .location(LOCATION)
                .bearerTokenSupplier(TOKEN_SUPPLIER)
                .fromEnv()
                .build()

        assertThat(backend.workspaceId()).hasValue(WORKSPACE_ID)
    }

    @Test
    fun workspaceIdFromEnv() {
        val backend =
            mockBuilder(workspaceId = "env-ws")
                .project(PROJECT)
                .location(LOCATION)
                .bearerTokenSupplier(TOKEN_SUPPLIER)
                .fromEnv()
                .build()

        assertThat(backend.workspaceId()).hasValue("env-ws")
    }

    @Test
    fun baseUrlArgBeatsEnv() {
        val backend =
            mockBuilder(baseUrl = "https://env.example.com")
                .baseUrl(CUSTOM_BASE_URL)
                .workspaceId(WORKSPACE_ID)
                .bearerTokenSupplier(TOKEN_SUPPLIER)
                .fromEnv()
                .build()

        assertThat(backend.baseUrl()).isEqualTo(CUSTOM_BASE_URL)
    }

    @Test
    fun baseUrlFromEnv() {
        val backend =
            mockBuilder(baseUrl = "https://env.example.com")
                .workspaceId(WORKSPACE_ID)
                .bearerTokenSupplier(TOKEN_SUPPLIER)
                .fromEnv()
                .build()

        assertThat(backend.baseUrl()).isEqualTo("https://env.example.com")
    }

    // ---------------------------------------------------------------------------------------------
    // Credentials precedence: token supplier > explicit creds > ADC
    // ---------------------------------------------------------------------------------------------

    @Test
    fun explicitCredentialsSuppressAdc() {
        val builder =
            mockBuilder(
                project = PROJECT,
                location = LOCATION,
                workspaceId = WORKSPACE_ID,
                adc = credentials("adc-token"),
            )
        val explicit = credentials("explicit-token")
        val backend = builder.googleCredentials(explicit).fromEnv().build()

        assertThat(backend.googleCredentials()).hasValue(explicit)
        Mockito.verify(builder, Mockito.never()).resolveApplicationDefault()
    }

    @Test
    fun bearerTokenSupplierSuppressesAdc() {
        val builder =
            mockBuilder(
                project = PROJECT,
                location = LOCATION,
                workspaceId = WORKSPACE_ID,
                adc = credentials("adc-token"),
            )
        val backend = builder.bearerTokenSupplier(TOKEN_SUPPLIER).fromEnv().build()

        assertThat(backend.bearerTokenSupplier()).isPresent
        assertThat(backend.googleCredentials()).isEmpty
        Mockito.verify(builder, Mockito.never()).resolveApplicationDefault()
    }

    @Test
    fun adcUsedWhenNoExplicitCredentials() {
        val adc = credentials("adc-token")
        val backend =
            mockBuilder(
                    project = PROJECT,
                    location = LOCATION,
                    workspaceId = WORKSPACE_ID,
                    adc = adc,
                )
                .fromEnv()
                .build()

        assertThat(backend.googleCredentials()).hasValue(adc)
    }

    @Test
    fun adcFailureWrapsAsIllegalStateException() {
        val builder =
            mockBuilder(project = PROJECT, location = LOCATION, workspaceId = WORKSPACE_ID)
        Mockito.doThrow(RuntimeException("no adc")).`when`(builder).resolveApplicationDefault()

        assertThatThrownBy { builder.fromEnv() }
            .isExactlyInstanceOf(IllegalStateException::class.java)
            .hasMessageContaining("Google OAuth2 credentials could not be resolved")
    }

    @Test
    fun bearerTokenSupplierWinsAtAuthorize() {
        val backend = createBackendWithTokenSupplier()
        val authorized = backend.authorizeRequest(createRequest())

        assertThat(authorized.headers.values("Authorization")).containsExactly("Bearer $TOKEN")
    }

    @Test
    fun googleCredentialsAtAuthorize() {
        val backend = createBackendWithCredentials()
        val authorized = backend.authorizeRequest(createRequest())

        assertThat(authorized.headers.values("Authorization")).containsExactly("Bearer $TOKEN")
    }

    // ---------------------------------------------------------------------------------------------
    // Credential isolation from the base Anthropic backend
    // ---------------------------------------------------------------------------------------------

    @Test
    fun isolationFromBaseClientEnv() {
        // Set the env vars the base client honours; they must NOT influence this backend at all.
        val builder =
            Mockito.spy(GoogleCloudBackend.builder()).also {
                Mockito.doReturn(null).`when`(it).getEnv(Mockito.anyString())
                Mockito.doReturn("sk-ant-ignored").`when`(it).getEnv("ANTHROPIC_API_KEY")
                Mockito.doReturn("ignored-auth").`when`(it).getEnv("ANTHROPIC_AUTH_TOKEN")
                Mockito.doReturn("https://ignored.example.com")
                    .`when`(it)
                    .getEnv("ANTHROPIC_BASE_URL")
                Mockito.doReturn(PROJECT).`when`(it).getEnv(ENV_PROJECT)
                Mockito.doReturn(LOCATION).`when`(it).getEnv(ENV_LOCATION)
                Mockito.doReturn(WORKSPACE_ID).`when`(it).getEnv(ENV_WORKSPACE_ID)
                Mockito.doReturn(credentials()).`when`(it).resolveApplicationDefault()
            }
        val backend = builder.fromEnv().build()

        val request = backend.authorizeRequest(backend.prepareRequest(createRequest()))

        assertThat(request.headers.names()).doesNotContain("X-Api-Key")
        assertThat(request.headers.names()).doesNotContain("x-api-key")
        assertThat(request.headers.values("Authorization")).containsExactly("Bearer $TOKEN")
        assertThat(backend.baseUrl()).doesNotContain("ignored.example.com")
        Mockito.verify(builder, Mockito.never()).getEnv("ANTHROPIC_API_KEY")
        Mockito.verify(builder, Mockito.never()).getEnv("ANTHROPIC_AUTH_TOKEN")
        Mockito.verify(builder, Mockito.never()).getEnv("ANTHROPIC_BASE_URL")
    }

    // ---------------------------------------------------------------------------------------------
    // Base URL derivation
    // ---------------------------------------------------------------------------------------------

    @Test
    fun baseUrlDerivedFromProjectLocationAndWorkspaceId() {
        val backend = createBackendWithTokenSupplier()

        assertThat(backend.baseUrl())
            .isEqualTo(
                "https://claude.googleapis.com/v1alpha" +
                    "/projects/$PROJECT/locations/$LOCATION/workspaces/$WORKSPACE_ID/invoke"
            )
    }

    @Test
    fun baseUrlDerivedWithDefaultLocation() {
        val backend =
            GoogleCloudBackend.builder()
                .bearerTokenSupplier(TOKEN_SUPPLIER)
                .project(PROJECT)
                .workspaceId(WORKSPACE_ID)
                .build()

        assertThat(backend.baseUrl())
            .isEqualTo(
                "https://claude.googleapis.com/v1alpha" +
                    "/projects/$PROJECT/locations/global/workspaces/$WORKSPACE_ID/invoke"
            )
    }

    @Test
    fun baseUrlOverrideBeatsDerived() {
        val backend =
            GoogleCloudBackend.builder()
                .bearerTokenSupplier(TOKEN_SUPPLIER)
                .workspaceId(WORKSPACE_ID)
                .project(PROJECT)
                .location(LOCATION)
                .baseUrl(CUSTOM_BASE_URL)
                .build()

        assertThat(backend.baseUrl()).isEqualTo(CUSTOM_BASE_URL)
    }

    @Test
    fun baseUrlOverrideMakesProjectAndLocationOptional() {
        val backend =
            GoogleCloudBackend.builder()
                .bearerTokenSupplier(TOKEN_SUPPLIER)
                .workspaceId(WORKSPACE_ID)
                .baseUrl(CUSTOM_BASE_URL)
                .build()

        assertThat(backend.baseUrl()).isEqualTo(CUSTOM_BASE_URL)
        assertThat(backend.project()).isEmpty
        // The location default still applies; it is simply unused with an override in place.
        assertThat(backend.location()).hasValue("global")
    }

    // ---------------------------------------------------------------------------------------------
    // prepareRequest / authorizeRequest
    // ---------------------------------------------------------------------------------------------

    @Test
    fun prepareRequestAddsVersionHeaderOnly() {
        val backend = createBackendWithTokenSupplier()
        val prepared = backend.prepareRequest(createRequest())

        assertThat(prepared.headers.values("anthropic-version")).containsExactly("2023-06-01")
        // The workspace ID travels in the derived base URL path, never as a header.
        assertThat(prepared.headers.names()).doesNotContain("anthropic-workspace-id")
    }

    @Test
    fun prepareRequestKeepsUserSuppliedHeaders() {
        val backend = createBackendWithTokenSupplier()
        val request =
            createRequest()
                .toBuilder()
                .putHeader("anthropic-version", "2024-01-01")
                .putHeader("anthropic-workspace-id", "user-ws")
                .build()

        val prepared = backend.prepareRequest(request)

        assertThat(prepared.headers.values("anthropic-version")).containsExactly("2024-01-01")
        assertThat(prepared.headers.values("anthropic-workspace-id")).containsExactly("user-ws")
    }

    @Test
    fun prepareRequestIsIdempotent() {
        val backend = createBackendWithTokenSupplier()
        val once = backend.prepareRequest(createRequest())
        val twice = backend.prepareRequest(once)

        assertThat(twice.headers.names()).isEqualTo(once.headers.names())
        assertThat(twice.headers.values("anthropic-version"))
            .isEqualTo(once.headers.values("anthropic-version"))
    }

    @Test
    fun authorizeRequestAlreadyAuthorized() {
        val backend = createBackendWithTokenSupplier()
        val request =
            createRequest().toBuilder().putHeader("Authorization", "Bearer already").build()

        // A request that already carries an authorization header (e.g. added by an interceptor) is
        // passed through unchanged instead of being authorized again.
        assertThat(backend.authorizeRequest(request)).isSameAs(request)
    }

    @Test
    fun authorizeRequestNoApiKeyHeader() {
        val backend = createBackendWithTokenSupplier()
        val authorized = backend.authorizeRequest(createRequest())

        assertThat(authorized.headers.names()).doesNotContain("x-api-key")
        assertThat(authorized.headers.names()).doesNotContain("X-Api-Key")
    }

    // ---------------------------------------------------------------------------------------------
    // skipAuth
    // ---------------------------------------------------------------------------------------------

    @Test
    fun skipAuthWithBaseUrlBuildWithoutCredentialsOrWorkspaceId() {
        val backend = GoogleCloudBackend.builder().skipAuth(true).baseUrl(CUSTOM_BASE_URL).build()

        assertThat(backend.skipAuth()).isTrue()
        assertThat(backend.googleCredentials()).isEmpty
        assertThat(backend.bearerTokenSupplier()).isEmpty
        assertThat(backend.workspaceId()).isEmpty
    }

    @Test
    fun skipAuthWithoutBaseUrlRequiresWorkspaceId() {
        assertThatThrownBy { GoogleCloudBackend.builder().skipAuth(true).project(PROJECT).build() }
            .isExactlyInstanceOf(IllegalStateException::class.java)
            .hasMessageContaining("Workspace ID is required")
            .hasMessageContaining(ENV_WORKSPACE_ID)
    }

    @Test
    fun skipAuthWithoutBaseUrlDerivesUrlFromWorkspaceId() {
        val backend =
            GoogleCloudBackend.builder()
                .skipAuth(true)
                .project(PROJECT)
                .workspaceId(WORKSPACE_ID)
                .build()

        assertThat(backend.baseUrl())
            .isEqualTo(
                "https://claude.googleapis.com/v1alpha" +
                    "/projects/$PROJECT/locations/global/workspaces/$WORKSPACE_ID/invoke"
            )
    }

    @Test
    fun skipAuthNoAuthHeaders() {
        val backend = GoogleCloudBackend.builder().skipAuth(true).baseUrl(CUSTOM_BASE_URL).build()
        val authorized = backend.authorizeRequest(createRequest())

        assertThat(authorized.headers.names()).doesNotContain("Authorization")
    }

    @Test
    fun skipAuthNoWorkspaceIdHeader() {
        val backend = GoogleCloudBackend.builder().skipAuth(true).baseUrl(CUSTOM_BASE_URL).build()
        val prepared = backend.prepareRequest(createRequest())

        assertThat(prepared.headers.values("anthropic-version")).containsExactly("2023-06-01")
        assertThat(prepared.headers.names()).doesNotContain("anthropic-workspace-id")
    }

    @Test
    fun skipAuthSuppressesAdc() {
        val builder = mockBuilder(baseUrl = CUSTOM_BASE_URL)
        builder.skipAuth(true).fromEnv().build()

        Mockito.verify(builder, Mockito.never()).resolveApplicationDefault()
    }

    @Test
    fun closeIsNoOp() {
        assertThatNoException().isThrownBy { createBackendWithTokenSupplier().close() }
    }

    // ---------------------------------------------------------------------------------------------
    // Helpers
    // ---------------------------------------------------------------------------------------------

    private fun credentials(token: String = TOKEN): GoogleCredentials =
        GoogleCredentials.create(AccessToken.newBuilder().setTokenValue(token).build())

    private fun createBackendWithTokenSupplier(): GoogleCloudBackend =
        GoogleCloudBackend.builder()
            .bearerTokenSupplier(TOKEN_SUPPLIER)
            .project(PROJECT)
            .location(LOCATION)
            .workspaceId(WORKSPACE_ID)
            .build()

    private fun createBackendWithCredentials(): GoogleCloudBackend =
        GoogleCloudBackend.builder()
            .googleCredentials(credentials())
            .project(PROJECT)
            .location(LOCATION)
            .workspaceId(WORKSPACE_ID)
            .build()

    private fun createRequest(): HttpRequest =
        HttpRequest.builder()
            .method(HttpMethod.POST)
            .baseUrl(CUSTOM_BASE_URL)
            .addPathSegment("v1")
            .addPathSegment("messages")
            .build()

    /**
     * Returns a spied builder whose [GoogleCloudBackend.Builder.getEnv] returns the given values
     * (and `null` for everything else, isolating tests from the host environment).
     */
    private fun mockBuilder(
        project: String? = null,
        projectFallback: String? = null,
        location: String? = null,
        workspaceId: String? = null,
        baseUrl: String? = null,
        adc: GoogleCredentials? = null,
    ): GoogleCloudBackend.Builder =
        Mockito.spy(GoogleCloudBackend.builder()).also {
            Mockito.doReturn(null).`when`(it).getEnv(Mockito.anyString())
            Mockito.doReturn(project).`when`(it).getEnv(ENV_PROJECT)
            Mockito.doReturn(projectFallback).`when`(it).getEnv(ENV_PROJECT_FALLBACK)
            Mockito.doReturn(location).`when`(it).getEnv(ENV_LOCATION)
            Mockito.doReturn(workspaceId).`when`(it).getEnv(ENV_WORKSPACE_ID)
            Mockito.doReturn(baseUrl).`when`(it).getEnv(ENV_BASE_URL)
            if (adc != null) {
                Mockito.doReturn(adc).`when`(it).resolveApplicationDefault()
            }
        }
}
