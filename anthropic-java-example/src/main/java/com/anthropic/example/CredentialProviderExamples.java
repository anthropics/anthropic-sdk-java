package com.anthropic.example;

import com.anthropic.client.AnthropicClient;
import com.anthropic.client.okhttp.AnthropicOkHttpClient;
import com.anthropic.config.AuthenticationConfig;
import com.anthropic.config.AuthenticationType;
import com.anthropic.config.IdentityTokenConfig;
import com.anthropic.config.InMemoryProfileConfigProvider;
import com.anthropic.config.ProfileConfig;
import com.anthropic.models.messages.Message;
import com.anthropic.models.messages.MessageCreateParams;
import com.anthropic.models.messages.Model;

/**
 * Workload Identity Federation & Credential Providers — comprehensive examples.
 *
 * <p>
 * When {@code fromEnv()} is invoked, the builder resolves auth from the
 * environment in this order (later sources only fill in if earlier ones are
 * absent):
 * <ol>
 * <li>Env vars: ANTHROPIC_API_KEY, ANTHROPIC_AUTH_TOKEN
 * <li>Explicit profile: ANTHROPIC_PROFILE env var
 * <li>Env federation vars: ANTHROPIC_FEDERATION_RULE_ID +
 * ANTHROPIC_ORGANIZATION_ID + identity token
 * <li>Fallback profile: active_config file or "default" profile
 * </ol>
 *
 * <p>
 * The client uses an AccessTokenProvider internally that returns an AccessToken
 * with token and optional expiration. The client caches the token and
 * proactively refreshes it before expiry.
 */
public class CredentialProviderExamples {

    // =============================================================================
    // Section 1: Zero-config (recommended for production workloads)
    // =============================================================================
    //
    // Just construct the client with fromEnv(). Auth is resolved from the environment.
    //
    // For Kubernetes / GitHub Actions / etc., set these env vars on the workload:
    //   ANTHROPIC_IDENTITY_TOKEN_FILE=/var/run/secrets/kubernetes.io/serviceaccount/token
    //   ANTHROPIC_FEDERATION_RULE_ID=fdrl_01...
    //   ANTHROPIC_ORGANIZATION_ID=00000000-0000-0000-0000-000000000000
    //   ANTHROPIC_SERVICE_ACCOUNT_ID=svac_01...   (optional)
    //
    // Or, if a sidecar/daemon writes a profile config + credentials file:
    //   ANTHROPIC_PROFILE=my-profile     (picks ~/.config/anthropic/configs/my-profile.json
    //                                     and ~/.config/anthropic/credentials/my-profile.json)
    //   ANTHROPIC_CONFIG_DIR=/etc/anthropic   (relocates the root; optional)
    //
    // Or, the existing env vars still work:
    //   ANTHROPIC_API_KEY=sk-ant-...
    //   ANTHROPIC_AUTH_TOKEN=sk-ant-oat01-...
    public static void zeroConfigExample() {
        AnthropicClient client = AnthropicOkHttpClient.fromEnv();

        Message message = client.messages()
                .create(MessageCreateParams.builder()
                        .model(Model.CLAUDE_SONNET_4_20250514)
                        .maxTokens(1024)
                        .addUserMessage("Hello!")
                        .build());

        System.out.println(message);
    }

    // =============================================================================
    // Section 2: Explicit credentials via constructor
    // =============================================================================
    // --- 2a. WorkloadIdentityCredentials: exchange an external OIDC JWT --------
    @SuppressWarnings("unused")
    public static void workloadIdentityFromFileExample() {
        // JWT source option i: read from a file (re-read on every refresh — handles k8s rotation)
        AnthropicClient client = AnthropicOkHttpClient.builder()
                .fromEnv()
                .configurationProvider(InMemoryProfileConfigProvider.of(ProfileConfig.builder()
                        .organizationId("00000000-0000-0000-0000-000000000000")
                        .authentication(AuthenticationConfig.builder()
                                .type(AuthenticationType.OIDC_FEDERATION)
                                .federationRuleId("fdrl_01...")
                                .serviceAccountId("svac_01...")
                                .identityToken(IdentityTokenConfig.builder()
                                        .source("file")
                                        .path("/var/run/secrets/kubernetes.io/serviceaccount/token")
                                        .build())
                                .build())
                        .build()))
                .build();
    }

    @SuppressWarnings("unused")
    public static void workloadIdentityFromEnvVarExample() {
        // JWT source option ii: from an env var (CI systems that inject the token directly)
        AnthropicClient client = AnthropicOkHttpClient.builder()
                .fromEnv()
                .configurationProvider(InMemoryProfileConfigProvider.of(ProfileConfig.builder()
                        .organizationId("00000000-0000-0000-0000-000000000000")
                        .authentication(AuthenticationConfig.builder()
                                .type(AuthenticationType.OIDC_FEDERATION)
                                .federationRuleId("fdrl_01...")
                                .identityToken(IdentityTokenConfig.builder()
                                        .source("value")
                                        .path(System.getenv("ANTHROPIC_IDENTITY_TOKEN"))
                                        .build())
                                .build())
                        .build()))
                .build();
    }

    @SuppressWarnings("unused")
    public static void workloadIdentityCustomProviderExample() {
        // JWT source option iii: custom provider (secrets manager, internal token service, etc.)
        // Use InMemoryConfigurationProvider with a custom identity token source.
        // The identity token value will be read fresh each time from your custom logic.

        AnthropicClient client = AnthropicOkHttpClient.builder()
                .fromEnv()
                .configurationProvider(InMemoryProfileConfigProvider.of(ProfileConfig.builder()
                        .organizationId("00000000-0000-0000-0000-000000000000")
                        .authentication(AuthenticationConfig.builder()
                                .type(AuthenticationType.OIDC_FEDERATION)
                                .federationRuleId("fdrl_01...")
                                .serviceAccountId("svac_01...")
                                // For custom token sources, you can use "value" source with the token
                                .identityToken(IdentityTokenConfig.builder()
                                        .source("value")
                                        .path(fetchJwtFromVault())
                                        .build())
                                .build())
                        .build()))
                .build();
    }

    private static String fetchJwtFromVault() {
        // Your Vault/secrets manager logic here
        return "test";
    }

    // --- 2b. ConfigurationFileProvider: read auth config from a named profile on disk ----
    //
    // Profiles live under the config directory (default ~/.config/anthropic/,
    // override with ANTHROPIC_CONFIG_DIR) as a pair of files:
    //
    //   configs/<profile>.json       — non-secret. Shape:
    //
    //     {
    //       "authentication": {"type": "oidc_federation"|"user_oauth", ...},
    //       "organization_id": "00000000-0000-0000-0000-000000000000",
    //       "workspace_id": "wrkspc_01...",
    //       "base_url": "https://api.anthropic.com"
    //     }
    //
    //   The "authentication" object is a tagged union discriminated on "type":
    //
    //     {"type": "oidc_federation",
    //      "federation_rule_id": "fdrl_...",
    //      "service_account_id": "svac_...",
    //      "identity_token": {"source": "file", "path": "..."}}
    //       → SDK performs the jwt-bearer exchange itself. If "identity_token"
    //         is omitted, ANTHROPIC_IDENTITY_TOKEN_FILE is used instead.
    //         organization_id is read from the top level of the config.
    //
    //     {"type": "user_oauth", "client_id": "..."}
    //       → interactive PKCE login with refresh_token rotation. On access-token
    //         expiry the SDK performs a refresh_token grant against
    //         /v1/oauth/token and writes the new tokens back to
    //         credentials/<profile>.json (atomic replace).
    //
    //     {"type": "user_oauth"}  (no client_id)
    //       → credentials file is externally rotated by a sidecar/daemon. The
    //         SDK re-reads the file on every refresh and returns whatever
    //         access_token is there; no refresh grant is attempted.
    //
    //   credentials/<profile>.json   — secret (0600). Holds access_token,
    //                                  expires_at, and (for user_oauth with
    //                                  a client_id) refresh_token.
    @SuppressWarnings("unused")
    public static void configurationFileProviderExample() {
        // To use a specific profile from disk, set the ANTHROPIC_PROFILE env var:
        //   export ANTHROPIC_PROFILE=production
        //
        // This will load configuration from:
        //   ~/.config/anthropic/configs/production.json
        //
        // The SDK automatically handles profile-based configuration when you use fromEnv():
        AnthropicClient client = AnthropicOkHttpClient.fromEnv();
    }

    @SuppressWarnings("unused")
    public static void configurationFileProviderFromEnvExample() {
        // Or resolve the profile from ANTHROPIC_PROFILE / <config_dir>/active_config / "default"
        // by using fromEnv() which automatically looks for profile configuration:
        AnthropicClient client = AnthropicOkHttpClient.fromEnv();
    }

    // --- 2c. Static Token: you already have a bearer token ---------------------
    @SuppressWarnings("unused")
    public static void staticTokenExample() {
        // If you have a static API key or auth token:
        AnthropicClient client =
                AnthropicOkHttpClient.builder().apiKey("sk-ant-api03-...").build();

        // Or with an OAuth access token:
        AnthropicClient clientWithAuthToken =
                AnthropicOkHttpClient.builder().authToken("sk-ant-oat01-...").build();
    }

    // =============================================================================
    // Section 3: Async Client
    // =============================================================================
    @SuppressWarnings("unused")
    public static void asyncExample() {
        var asyncClient = com.anthropic.client.okhttp.AnthropicOkHttpClientAsync.builder()
                .fromEnv()
                .configurationProvider(InMemoryProfileConfigProvider.of(ProfileConfig.builder()
                        .organizationId("00000000-0000-0000-0000-000000000000")
                        .authentication(AuthenticationConfig.builder()
                                .type(AuthenticationType.OIDC_FEDERATION)
                                .federationRuleId("fdrl_01...")
                                .identityToken(IdentityTokenConfig.builder()
                                        .source("file")
                                        .path("/var/run/secrets/kubernetes.io/serviceaccount/token")
                                        .build())
                                .build())
                        .build()))
                .build();

        asyncClient
                .messages()
                .create(MessageCreateParams.builder()
                        .model(Model.CLAUDE_SONNET_4_20250514)
                        .maxTokens(1024)
                        .addUserMessage("Hello!")
                        .build())
                .thenAccept(message -> System.out.println(message))
                .join();
    }

    // =============================================================================
    // Section 4: Kubernetes / Cloud Provider Examples
    // =============================================================================
    @SuppressWarnings("unused")
    public static void kubernetesExample() {
        // For Kubernetes workloads with projected service account tokens:
        //
        // 1. Configure your pod with a projected service account token volume:
        //
        //    volumes:
        //    - name: anthropic-token
        //      projected:
        //        sources:
        //        - serviceAccountToken:
        //            path: token
        //            expirationSeconds: 3600
        //            audience: api.anthropic.com
        //
        //    volumeMounts:
        //    - name: anthropic-token
        //      mountPath: /var/run/secrets/anthropic
        //      readOnly: true
        //
        // 2. Set environment variables:
        //    ANTHROPIC_IDENTITY_TOKEN_FILE=/var/run/secrets/anthropic/token
        //    ANTHROPIC_FEDERATION_RULE_ID=fdrl_01...
        //    ANTHROPIC_ORGANIZATION_ID=00000000-0000-0000-0000-000000000000
        //
        // 3. Use zero-config:

        AnthropicClient client = AnthropicOkHttpClient.fromEnv();
    }

    @SuppressWarnings("unused")
    public static void githubActionsExample() {
        // For GitHub Actions with OIDC:
        //
        // 1. Configure your workflow:
        //
        //    permissions:
        //      id-token: write
        //
        //    steps:
        //    - name: Get OIDC token
        //      run: |
        //        ANTHROPIC_IDENTITY_TOKEN=$(curl -H "Authorization: bearer $ACTIONS_ID_TOKEN_REQUEST_TOKEN" \
        //          "$ACTIONS_ID_TOKEN_REQUEST_URL&audience=api.anthropic.com" | jq -r '.value')
        //        echo "ANTHROPIC_IDENTITY_TOKEN=$ANTHROPIC_IDENTITY_TOKEN" >> $GITHUB_ENV
        //
        // 2. Set additional env vars in your workflow:
        //    ANTHROPIC_FEDERATION_RULE_ID: ${{ secrets.ANTHROPIC_FEDERATION_RULE_ID }}
        //    ANTHROPIC_ORGANIZATION_ID: ${{ secrets.ANTHROPIC_ORGANIZATION_ID }}
        //
        // 3. Use zero-config or explicit token value:

        AnthropicClient client = AnthropicOkHttpClient.fromEnv();
    }

    public static void main(String[] args) {
        // Run the zero-config example (requires appropriate env vars)
        zeroConfigExample();
    }
}
