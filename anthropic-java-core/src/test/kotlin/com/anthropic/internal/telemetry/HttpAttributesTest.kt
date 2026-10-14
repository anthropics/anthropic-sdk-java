package com.anthropic.internal.telemetry

import com.anthropic.backends.Backend
import com.anthropic.client.AnthropicClient
import com.anthropic.client.AnthropicClientAsync
import com.anthropic.client.AnthropicClientImpl
import com.anthropic.client.okhttp.AnthropicOkHttpClient
import com.anthropic.client.okhttp.AnthropicOkHttpClientAsync
import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.http.HttpMethod
import com.anthropic.core.http.HttpRequest
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.core.http.parseable
import com.anthropic.core.opentelemetry.OpenTelemetryContentMode
import com.anthropic.core.opentelemetry.OpenTelemetryOptions
import com.anthropic.core.opentelemetry.SpanData
import com.anthropic.core.opentelemetry.startApiCall
import com.anthropic.core.opentelemetry.traceApiCall
import com.anthropic.core.opentelemetry.traceApiCallAsync
import com.anthropic.errors.BadRequestException
import com.anthropic.errors.InternalServerException
import com.anthropic.helpers.FakeHttpClient
import com.anthropic.models.beta.agents.AgentListParams
import com.anthropic.models.beta.files.FileListParams
import com.anthropic.models.beta.memorystores.memories.MemoryDeleteParams
import com.anthropic.models.beta.memorystores.memories.MemoryListParams
import com.anthropic.models.beta.models.ModelListParams as BetaModelListParams
import com.anthropic.models.beta.organization.analytics.connectors.ConnectorListParams
import com.anthropic.models.beta.organization.analytics.plugins.PluginListParams
import com.anthropic.models.beta.organization.analytics.skills.SkillListParams
import com.anthropic.models.beta.organization.analytics.users.UserListParams as AnalyticsUserListParams
import com.anthropic.models.beta.organization.invites.InviteListParams
import com.anthropic.models.beta.organization.workspaces.WorkspaceListParams as BetaWorkspaceListParams
import com.anthropic.models.beta.sessions.threads.BetaManagedAgentsSessionThreadStatus
import com.anthropic.models.beta.sessions.threads.ThreadListParams
import com.anthropic.models.models.ModelListParams
import com.anthropic.models.organization.users.UserListParams
import com.anthropic.models.organization.workspaces.WorkspaceListParams
import com.github.tomakehurst.wiremock.client.WireMock.aResponse
import com.github.tomakehurst.wiremock.client.WireMock.any
import com.github.tomakehurst.wiremock.client.WireMock.anyRequestedFor
import com.github.tomakehurst.wiremock.client.WireMock.anyUrl
import com.github.tomakehurst.wiremock.client.WireMock.findAll
import com.github.tomakehurst.wiremock.client.WireMock.ok
import com.github.tomakehurst.wiremock.client.WireMock.post
import com.github.tomakehurst.wiremock.client.WireMock.stubFor
import com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo
import com.github.tomakehurst.wiremock.junit5.WireMockTest
import com.github.tomakehurst.wiremock.stubbing.Scenario
import io.opentelemetry.api.common.Attributes
import io.opentelemetry.api.trace.SpanKind
import io.opentelemetry.api.trace.StatusCode
import io.opentelemetry.context.Context
import io.opentelemetry.sdk.trace.SdkTracerProvider
import io.opentelemetry.sdk.trace.data.LinkData
import io.opentelemetry.sdk.trace.data.SpanData as RecordedSpan
import io.opentelemetry.sdk.trace.samplers.Sampler
import io.opentelemetry.sdk.trace.samplers.SamplingResult
import java.time.OffsetDateTime
import java.util.concurrent.CompletableFuture
import java.util.concurrent.CopyOnWriteArrayList
import java.util.concurrent.ExecutionException
import java.util.concurrent.TimeUnit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.parallel.ResourceLock

/**
 * The HTTP attributes of a call span: the method and the route that it starts with, the URL of the
 * call's first request, and the number of times the SDK resent it.
 */
@WireMockTest
@ResourceLock("https://github.com/wiremock/wiremock/issues/169")
internal class HttpAttributesTest : TracingTestSupport() {

    private companion object {
        const val LIST_JSON =
            """{"data": [], "has_more": false, "first_id": null, "last_id": null}"""

        val URL_ATTRIBUTES = arrayOf("url.scheme", "url.path", "url.query", "url.full")
    }

    /** Every request gets [body], whatever its method and its URL. */
    private fun stubEveryRequest(body: String = LIST_JSON) {
        stubFor(any(anyUrl()).willReturn(ok(body).withHeader("Content-Type", "application/json")))
    }

    /** The path and the query string of each request that the server got, as it got them. */
    private fun urlsSent(): List<String> = findAll(anyRequestedFor(anyUrl())).map { it.url }

    private fun asyncClientBuilder() =
        AnthropicOkHttpClientAsync.builder()
            .baseUrl(baseUrl)
            .apiKey("my-anthropic-api-key")
            .maxRetries(0)
            .openTelemetry(options())

    /**
     * The spans of [call], made with the blocking client, and of [callAsync], the same call made
     * with the asynchronous one.
     */
    private fun inEveryClient(
        client: AnthropicClient = this.client,
        asyncClient: AnthropicClientAsync = asyncClientBuilder().build(),
        call: (AnthropicClient) -> Unit,
        callAsync: (AnthropicClientAsync) -> CompletableFuture<*>,
    ): List<RecordedSpan> {
        call(client)
        callAsync(asyncClient).get(30, TimeUnit.SECONDS)
        return recorder.await(2)
    }

    @Test
    fun callSpan_startsWithTheMethodAndTheRouteThatASamplerDecidesBy() {
        stubEveryRequest("""{"id": "id_1"}""")
        stubMessage()
        val seenAtStart = CopyOnWriteArrayList<Attributes>()
        val sampler =
            object : Sampler {
                override fun shouldSample(
                    parentContext: Context,
                    traceId: String,
                    name: String,
                    spanKind: SpanKind,
                    attributes: Attributes,
                    parentLinks: List<LinkData>,
                ): SamplingResult {
                    // A copy, because the span goes on to add its later attributes to these.
                    seenAtStart.add(attributes.toBuilder().build())
                    return SamplingResult.recordAndSample()
                }

                override fun getDescription(): String = "records what it is given"
            }
        val openTelemetry =
            OpenTelemetryOptions.builder()
                .tracerProvider(SdkTracerProvider.builder().setSampler(sampler).build())
                .build()
        val client = clientBuilder().openTelemetry(openTelemetry).build()
        val async = asyncClientBuilder().openTelemetry(openTelemetry).build()

        client.messages().create(messageParams())
        client.messages().batches().retrieve("msgbatch_1")
        client.beta().files().delete("file_1")
        async.messages().create(messageParams()).get(30, TimeUnit.SECONDS)
        async.messages().batches().retrieve("msgbatch_1").get(30, TimeUnit.SECONDS)
        async.beta().files().delete("file_1").get(30, TimeUnit.SECONDS)

        val started =
            seenAtStart.map {
                it.asMap()
                    .mapKeys { (key, _) -> key.key }
                    .filterKeys { key -> key.startsWith("http.") }
            }
        val expected =
            listOf(
                mapOf("http.request.method" to "POST", "http.route" to "/v1/messages"),
                mapOf(
                    "http.request.method" to "GET",
                    "http.route" to "/v1/messages/batches/{message_batch_id}",
                ),
                mapOf("http.request.method" to "DELETE", "http.route" to "/v1/files/{file_id}"),
            )
        assertThat(started).isEqualTo(expected + expected)
    }

    @Test
    fun firstRequest_recordsTheUrlAsItIsSent() {
        stubEveryRequest("""{"id": "id_1"}""")

        val modelId = "vendor.model-v1:0@2024 preview/x"

        val spans =
            inEveryClient(
                call = { it.models().retrieve(modelId) },
                callAsync = { it.models().retrieve(modelId) },
            )

        val sent = urlsSent()
        assertThat(sent).hasSize(2).allMatch { it.startsWith("/v1/models/vendor.model-v1:0@2024") }
        assertThat(spans).allSatisfy { span ->
            assertThat(span.string("url.scheme")).isEqualTo("http")
            assertThat(span.string("url.path")).isEqualTo(sent[0])
            assertThat(span.string("url.full")).isEqualTo(baseUrl + sent[0])
            assertThat(attributesOf(span)).doesNotContainKey("url.query")
            assertThat(span.string("http.route")).isEqualTo("/v1/models/{model_id}")
        }
    }

    @Test
    fun urlFull_hasThePortOnlyWhenTheBaseUrlGivesOne() {
        val baseUrls =
            listOf(
                "https://api.anthropic.com",
                "http://[::1]:8080/proxy",
                "HTTPS://gateway.internal:443/anthropic/",
            )
        for (baseUrl in baseUrls) {
            val clientOptions =
                ClientOptions.builder()
                    .httpClient(FakeHttpClient(MESSAGE_JSON))
                    .baseUrl(baseUrl)
                    .openTelemetry(options())
                    .build()
            AnthropicClientImpl(clientOptions).messages().create(messageParams())
        }

        assertThat(
                recorder.spans.map {
                    listOf(it.string("url.scheme"), it.string("url.path"), it.string("url.full"))
                }
            )
            .containsExactly(
                listOf("https", "/v1/messages", "https://api.anthropic.com/v1/messages"),
                listOf("http", "/proxy/v1/messages", "http://[::1]:8080/proxy/v1/messages"),
                listOf(
                    "https",
                    "/anthropic/v1/messages",
                    "https://gateway.internal:443/anthropic/v1/messages",
                ),
            )
    }

    @Test
    fun urlQuery_keepsOnlyTheParametersThatTheEndpointDefines() {
        stubEveryRequest()
        val params =
            FileListParams.builder()
                .addId("file_1")
                .addId("file 2&3")
                .limit(5)
                .putAdditionalQueryParam("trace", "on")
                .build()

        val spans =
            inEveryClient(
                clientBuilder().putQueryParam("tenant", "acme").build(),
                asyncClientBuilder().putQueryParam("tenant", "acme").build(),
                call = { it.beta().files().list(params) },
                callAsync = { it.beta().files().list(params) },
            )

        // The pairs that the endpoint defines, with the bytes and in the order that they were sent.
        val query = "ids%5B%5D=file_1&ids%5B%5D=file%202%263&limit=5"
        assertThat(urlsSent())
            .hasSize(2)
            .containsOnly("/v1/files?beta=true&tenant=acme&$query&trace=on")
        assertThat(spans).allSatisfy { span ->
            assertThat(span.string("url.path")).isEqualTo("/v1/files")
            assertThat(span.string("url.query")).isEqualTo(query)
            assertThat(span.string("url.full")).isEqualTo("$baseUrl/v1/files?$query")
        }
    }

    @Test
    fun urlQuery_looksUpTheWholeNameBeforeTheNameWithoutItsBrackets() {
        stubEveryRequest()
        val params =
            AgentListParams.builder()
                .createdAtGte(OffsetDateTime.parse("2024-01-01T00:00:00Z"))
                // Sent in place of the value that the params would give `limit`.
                .putAdditionalQueryParam("limit", "7")
                // `created_at[gt]` is not a parameter of this endpoint, and neither is
                // `created_at`.
                .putAdditionalQueryParam("created_at[gt]", "2023-01-01")
                // The item of an array has the name of its parameter before the brackets.
                .putAdditionalQueryParam("limit[]", "8")
                // Any other name in brackets is not the endpoint's, whatever it starts with.
                .putAdditionalQueryParam("limit[x]", "9")
                .putAdditionalQueryParam("limit[x][y]", "10")
                .putAdditionalQueryParam("limit[x][]", "11")
                .build()

        val spans =
            inEveryClient(
                call = { it.beta().agents().list(params) },
                callAsync = { it.beta().agents().list(params) },
            )

        assertThat(urlsSent()).allSatisfy {
            assertThat(it)
                .contains("beta=true")
                .contains("created_at%5Bgt%5D=2023-01-01")
                .contains("limit%5Bx%5D=9")
                .contains("limit%5Bx%5D%5By%5D=10")
                .contains("limit%5Bx%5D%5B%5D=11")
        }
        assertThat(spans).allSatisfy { span ->
            assertThat(span.string("url.query"))
                .isEqualTo("created_at%5Bgte%5D=2024-01-01T00%3A00%3A00Z&limit=7&limit%5B%5D=8")
        }
    }

    @Test
    fun urlQuery_keepsTheParametersThatFilterAListOfModelsOfWorkspacesAndOfThreads() {
        stubEveryRequest()
        val models =
            ModelListParams.builder()
                .addLifecycle(ModelListParams.Lifecycle.ACTIVE)
                .addLifecycle(ModelListParams.Lifecycle.DEPRECATED)
                .limit(5)
                .build()
        val betaModels =
            BetaModelListParams.builder().addLifecycle(BetaModelListParams.Lifecycle.ACTIVE).build()
        val workspaces = WorkspaceListParams.builder().includeDefault(true).limit(5).build()
        val betaWorkspaces = BetaWorkspaceListParams.builder().includeDefault(false).build()
        val threads =
            ThreadListParams.builder()
                .sessionId("sesn_1")
                .limit(5)
                .addStatus(BetaManagedAgentsSessionThreadStatus.RUNNING)
                .addStatus(BetaManagedAgentsSessionThreadStatus.IDLE)
                .build()

        client.models().list(models)
        client.beta().models().list(betaModels)
        client.organization().workspaces().list(workspaces)
        client.beta().organization().workspaces().list(betaWorkspaces)
        client.beta().sessions().threads().list(threads)

        assertThat(recorder.spans.map { it.string("url.query") })
            .containsExactly(
                "lifecycle%5B%5D=active&lifecycle%5B%5D=deprecated&limit=5",
                "lifecycle%5B%5D=active",
                "include_default=true&limit=5",
                "include_default=false",
                "limit=5&statuses%5B%5D=running&statuses%5B%5D=idle",
            )
    }

    @Test
    fun urlQuery_isAbsentWhenNoParameterIsLeftOrNoneWasSent() {
        stubEveryRequest()
        stubMessage()

        client.beta().messages().create(betaMessageParams())
        client.models().list()

        assertThat(urlsSent()).containsExactlyInAnyOrder("/v1/messages?beta=true", "/v1/models")
        assertThat(recorder.spans.map { it.string("url.full") })
            .containsExactly("$baseUrl/v1/messages", "$baseUrl/v1/models")
        assertThat(recorder.spans).allSatisfy {
            assertThat(attributesOf(it)).doesNotContainKey("url.query")
        }
    }

    /**
     * A call whose query string has a parameter that makes it content, in the blocking and the
     * asynchronous client: what makes the call, and the path and the query string that it sends.
     */
    private class ContentQueryCall(
        val path: String,
        val query: String,
        val call: (AnthropicClient) -> Unit,
        val callAsync: (AnthropicClientAsync) -> CompletableFuture<*>,
    )

    private val contentQueryCalls: List<ContentQueryCall>
        get() {
            val users = UserListParams.builder().email("ada@example.com").limit(5).build()
            val memories =
                MemoryListParams.builder().memoryStoreId("memstore_1").pathPrefix("/notes/").build()
            val memory =
                MemoryDeleteParams.builder()
                    .memoryStoreId("memstore_1")
                    .memoryId("mem_1")
                    .expectedContentSha256("abc123")
                    .build()
            return listOf(
                ContentQueryCall(
                    "/v1/organizations/users",
                    "email=ada%40example.com&limit=5",
                    { it.organization().users().list(users) },
                    { it.organization().users().list(users) },
                ),
                ContentQueryCall(
                    "/v1/memory_stores/memstore_1/memories",
                    "path_prefix=%2Fnotes%2F",
                    { it.beta().memoryStores().memories().list(memories) },
                    { it.beta().memoryStores().memories().list(memories) },
                ),
                ContentQueryCall(
                    "/v1/memory_stores/memstore_1/memories/mem_1",
                    "expected_content_sha256=abc123",
                    { it.beta().memoryStores().memories().delete(memory) },
                    { it.beta().memoryStores().memories().delete(memory) },
                ),
            )
        }

    @Test
    fun urlQuery_thatIsContent_isAbsentWithTheUrlThatHasItFromASpanWithoutContent() {
        stubEveryRequest()
        val async = asyncClient()

        for (case in contentQueryCalls) {
            case.call(client)
            case.callAsync(async).get(30, TimeUnit.SECONDS)
        }

        val spans = recorder.await(2 * contentQueryCalls.size)
        assertThat(urlsSent().map { it.substringBefore('?') }.distinct())
            .containsExactlyInAnyOrderElementsOf(contentQueryCalls.map { it.path })
        assertThat(spans.map { it.string("url.path") })
            .containsExactlyElementsOf(contentQueryCalls.flatMap { listOf(it.path, it.path) })
        assertThat(spans).allSatisfy { span ->
            assertThat(attributesOf(span)).doesNotContainKeys("url.query", "url.full")
            assertThat(span.string("url.scheme")).isEqualTo("http")
            assertThat(attributesOf(span)).containsKeys("http.route", "http.request.method")
            assertThat(span.attributeText()).doesNotContain("ada", "example.com", "notes", "abc123")
        }
        assertThat(spans.takeLast(2)).allSatisfy { span ->
            assertThat(span.string("gen_ai.memory.store.id")).isEqualTo("memstore_1")
            assertThat(span.string("gen_ai.memory.record.id")).isEqualTo("mem_1")
        }
    }

    @Test
    fun urlQuery_thatIsContent_isRecordedOnASpanWithContent() {
        stubEveryRequest()
        val client = clientBuilder(OpenTelemetryContentMode.CONTENT).build()
        val async = asyncClient(OpenTelemetryContentMode.CONTENT)

        for (case in contentQueryCalls) {
            case.call(client)
            case.callAsync(async).get(30, TimeUnit.SECONDS)
        }

        val spans = recorder.await(2 * contentQueryCalls.size)
        val expected = contentQueryCalls.flatMap { listOf(it, it) }
        assertThat(spans.map { it.string("url.query") })
            .containsExactlyElementsOf(expected.map { it.query })
        assertThat(spans.map { it.string("url.full") })
            .containsExactlyElementsOf(expected.map { "$baseUrl${it.path}?${it.query}" })
    }

    @Test
    fun urlQuery_thatIsNotContent_isRecordedWhateverTheContentMode() {
        stubEveryRequest()
        val users = UserListParams.builder().limit(5).build()

        for (mode in OpenTelemetryContentMode.values()) {
            clientBuilder(mode).build().organization().users().list(users)
            asyncClient(mode).organization().users().list(users).get(30, TimeUnit.SECONDS)
        }

        assertThat(recorder.await(2 * OpenTelemetryContentMode.values().size)).allSatisfy { span ->
            assertThat(span.string("url.query")).isEqualTo("limit=5")
            assertThat(span.string("url.full")).isEqualTo("$baseUrl/v1/organizations/users?limit=5")
        }
    }

    @Test
    fun organizationMethod_hasItsNameAndRouteAndItsContentQueryOnlyOnASpanWithContent() {
        stubEveryRequest()
        val invites = InviteListParams.builder().email("ada@example.com").limit(5).build()
        val path = "/v1/organizations/invites"
        val query = "email=ada%40example.com&limit=5"

        clientBuilder().build().beta().organization().invites().list(invites)
        asyncClient().beta().organization().invites().list(invites).get(30, TimeUnit.SECONDS)
        recorder.await(2)
        val contentMode = OpenTelemetryContentMode.CONTENT
        clientBuilder(contentMode).build().beta().organization().invites().list(invites)
        asyncClient(contentMode)
            .beta()
            .organization()
            .invites()
            .list(invites)
            .get(30, TimeUnit.SECONDS)
        val spans = recorder.await(4)

        assertThat(urlsSent()).hasSize(4).containsOnly("$path?beta=true&$query")
        assertThat(spans).allSatisfy { span ->
            assertThat(span.name).isEqualTo("anthropic.organization.invites.list")
            assertThat(span.string("http.request.method")).isEqualTo("GET")
            assertThat(span.string("http.route")).isEqualTo(path)
            assertThat(span.string("url.path")).isEqualTo(path)
        }
        assertThat(spans.take(2)).allSatisfy { span ->
            assertThat(attributesOf(span)).doesNotContainKeys("url.query", "url.full")
            assertThat(span.attributeText()).doesNotContain("ada", "example.com")
        }
        assertThat(spans.drop(2)).allSatisfy { span ->
            assertThat(span.string("url.query")).isEqualTo(query)
            assertThat(span.string("url.full")).isEqualTo("$baseUrl$path?$query")
        }
    }

    /**
     * The lists with a parameter that is content there and not everywhere: a `filter` that matches
     * names, and a `page` cursor that holds a value of the last row before it. Each with such a
     * value, in the blocking and the asynchronous client.
     */
    private val endpointContentQueryCalls: List<ContentQueryCall>
        get() {
            val skills =
                SkillListParams.builder().addFilter("skill_name:Quarterly").limit(5).build()
            val plugins =
                PluginListParams.builder().addFilter("plugin_name:Ledger").limit(5).build()
            val connectors =
                ConnectorListParams.builder().addFilter("connector_name:Payroll").limit(5).build()
            val users = AnalyticsUserListParams.builder().limit(5).page("cursorOfAda").build()
            val memories =
                MemoryListParams.builder()
                    .memoryStoreId("memstore_1")
                    .limit(5)
                    .page("cursorOfNotes")
                    .build()
            return listOf(
                ContentQueryCall(
                    "/v1/organizations/analytics/skills",
                    "filter%5B%5D=skill_name%3AQuarterly&limit=5",
                    { it.beta().organization().analytics().skills().list(skills) },
                    { it.beta().organization().analytics().skills().list(skills) },
                ),
                ContentQueryCall(
                    "/v1/organizations/analytics/plugins",
                    "filter%5B%5D=plugin_name%3ALedger&limit=5",
                    { it.beta().organization().analytics().plugins().list(plugins) },
                    { it.beta().organization().analytics().plugins().list(plugins) },
                ),
                ContentQueryCall(
                    "/v1/organizations/analytics/connectors",
                    "filter%5B%5D=connector_name%3APayroll&limit=5",
                    { it.beta().organization().analytics().connectors().list(connectors) },
                    { it.beta().organization().analytics().connectors().list(connectors) },
                ),
                ContentQueryCall(
                    "/v1/organizations/analytics/users",
                    "limit=5&page=cursorOfAda",
                    { it.beta().organization().analytics().users().list(users) },
                    { it.beta().organization().analytics().users().list(users) },
                ),
                ContentQueryCall(
                    "/v1/memory_stores/memstore_1/memories",
                    "limit=5&page=cursorOfNotes",
                    { it.beta().memoryStores().memories().list(memories) },
                    { it.beta().memoryStores().memories().list(memories) },
                ),
            )
        }

    @Test
    fun urlQuery_withAParameterThatIsContentOnItsEndpoint_isAbsentFromASpanWithoutContent() {
        stubEveryRequest()
        val async = asyncClient()

        for (case in endpointContentQueryCalls) {
            case.call(client)
            case.callAsync(async).get(30, TimeUnit.SECONDS)
        }

        val spans = recorder.await(2 * endpointContentQueryCalls.size)
        assertThat(urlsSent())
            .containsExactlyInAnyOrderElementsOf(
                endpointContentQueryCalls.flatMap { case ->
                    List(2) { "${case.path}?beta=true&${case.query}" }
                }
            )
        assertThat(spans.map { it.string("url.path") })
            .containsExactlyElementsOf(
                endpointContentQueryCalls.flatMap { listOf(it.path, it.path) }
            )
        assertThat(spans).allSatisfy { span ->
            assertThat(attributesOf(span)).doesNotContainKeys("url.query", "url.full")
            assertThat(span.string("url.scheme")).isEqualTo("http")
            assertThat(span.attributeText())
                .doesNotContain("Quarterly", "Ledger", "Payroll", "cursorOf")
        }
    }

    @Test
    fun urlQuery_withAParameterThatIsContentOnItsEndpoint_isRecordedOnASpanWithContent() {
        stubEveryRequest()
        val client = clientBuilder(OpenTelemetryContentMode.CONTENT).build()
        val async = asyncClient(OpenTelemetryContentMode.CONTENT)

        for (case in endpointContentQueryCalls) {
            case.call(client)
            case.callAsync(async).get(30, TimeUnit.SECONDS)
        }

        val spans = recorder.await(2 * endpointContentQueryCalls.size)
        val expected = endpointContentQueryCalls.flatMap { listOf(it, it) }
        assertThat(spans.map { it.string("url.query") })
            .containsExactlyElementsOf(expected.map { it.query })
        assertThat(spans.map { it.string("url.full") })
            .containsExactlyElementsOf(expected.map { "$baseUrl${it.path}?${it.query}" })
    }

    @Test
    fun urlQuery_withTheSameParameterWhereItIsNotContent_isRecordedWhateverTheContentMode() {
        stubEveryRequest()
        val users = AnalyticsUserListParams.builder().addFilter("user_id:user_1").limit(5).build()
        val path = "/v1/organizations/analytics/users"
        val query = "filter%5B%5D=user_id%3Auser_1&limit=5"

        for (mode in OpenTelemetryContentMode.values()) {
            clientBuilder(mode).build().beta().organization().analytics().users().list(users)
            asyncClient(mode)
                .beta()
                .organization()
                .analytics()
                .users()
                .list(users)
                .get(30, TimeUnit.SECONDS)
        }

        assertThat(recorder.await(2 * OpenTelemetryContentMode.values().size)).allSatisfy { span ->
            assertThat(span.string("url.path")).isEqualTo(path)
            assertThat(span.string("url.query")).isEqualTo(query)
            assertThat(span.string("url.full")).isEqualTo("$baseUrl$path?$query")
        }
    }

    @Test
    fun failedRequest_keepsTheUrlOfTheRequest() {
        stubFor(
            any(anyUrl())
                .willReturn(
                    aResponse()
                        .withStatus(400)
                        .withHeader("Content-Type", "application/json")
                        .withBody(ERROR_JSON)
                )
        )
        val params = FileListParams.builder().limit(5).build()

        assertThrows<BadRequestException> { client.beta().files().list(params) }
        assertThrows<ExecutionException> {
            asyncClientBuilder().build().beta().files().list(params).get(30, TimeUnit.SECONDS)
        }

        assertThat(recorder.await(2)).allSatisfy { span ->
            assertThat(span.status.statusCode).isEqualTo(StatusCode.ERROR)
            assertThat(span.long("http.response.status_code")).isEqualTo(400)
            assertThat(span.string("url.scheme")).isEqualTo("http")
            assertThat(span.string("url.path")).isEqualTo("/v1/files")
            assertThat(span.string("url.query")).isEqualTo("limit=5")
            assertThat(span.string("url.full")).isEqualTo("$baseUrl/v1/files?limit=5")
        }
    }

    @Test
    fun retriedCall_keepsTheUrlOfItsFirstRequestAndCountsTheResends() {
        // Two failures and then a success, which starts over for the next client.
        val failed = aResponse().withStatus(500).withHeader("Retry-After-Ms", "1")
        val succeeded = ok(MESSAGE_JSON.trimIndent()).withHeader("Content-Type", "application/json")
        val states = listOf(Scenario.STARTED, "failed once", "failed twice", Scenario.STARTED)
        for ((index, response) in listOf(failed, failed, succeeded).withIndex()) {
            stubFor(
                post(urlPathEqualTo("/v1/messages"))
                    .inScenario("retries")
                    .whenScenarioStateIs(states[index])
                    .willSetStateTo(states[index + 1])
                    .willReturn(response)
            )
        }

        val spans =
            inEveryClient(
                clientBuilder().maxRetries(2).build(),
                asyncClientBuilder().maxRetries(2).build(),
                call = { it.messages().create(messageParams()) },
                callAsync = { it.messages().create(messageParams()) },
            )

        assertThat(urlsSent()).hasSize(6)
        assertThat(spans).allSatisfy { span ->
            assertThat(span.status.statusCode).isEqualTo(StatusCode.OK)
            assertThat(span.long("http.request.resend_count")).isEqualTo(2)
            assertThat(span.string("url.full")).isEqualTo("$baseUrl/v1/messages")
        }
    }

    @Test
    fun callThatRunsOutOfRetries_countsTheResends() {
        stubFor(
            post(urlPathEqualTo("/v1/messages"))
                .willReturn(aResponse().withStatus(500).withHeader("Retry-After-Ms", "1"))
        )
        val client = clientBuilder().maxRetries(1).build()

        assertThrows<InternalServerException> { client.messages().create(messageParams()) }

        val span = recorder.only()
        assertThat(span.string("error.type")).isEqualTo("500")
        assertThat(span.long("http.request.resend_count")).isEqualTo(1)
    }

    @Test
    fun callSentOnce_hasNoResendCount() {
        stubMessage()

        val spans =
            inEveryClient(
                call = { it.messages().create(messageParams()) },
                callAsync = { it.messages().create(messageParams()) },
            )

        assertThat(spans).allSatisfy {
            assertThat(attributesOf(it)).doesNotContainKey("http.request.resend_count")
        }
    }

    @Test
    fun streamedCall_retried_countsTheResendOnceTheStreamEnds() {
        stubFor(
            post(urlPathEqualTo("/v1/messages"))
                .inScenario("retry")
                .whenScenarioStateIs(Scenario.STARTED)
                .willSetStateTo("retried")
                .willReturn(aResponse().withStatus(529).withHeader("Retry-After-Ms", "1"))
        )
        stubFor(
            post(urlPathEqualTo("/v1/messages"))
                .inScenario("retry")
                .whenScenarioStateIs("retried")
                .willReturn(ok(STREAM_BODY).withHeader("Content-Type", "text/event-stream"))
        )
        val client = clientBuilder().maxRetries(1).build()

        client.messages().createStreaming(messageParams()).use { it.stream().forEach {} }

        val span = recorder.only()
        assertThat(span.status.statusCode).isEqualTo(StatusCode.OK)
        assertThat(span.long("http.request.resend_count")).isEqualTo(1)
    }

    @Test
    fun laterRequestOfACall_leavesTheUrlOfTheFirst() {
        val clientOptions =
            ClientOptions.builder()
                .httpClient(FakeHttpClient("{}", "{}"))
                .openTelemetry(options())
                .build()
        val request = { path: String ->
            HttpRequest.builder()
                .method(HttpMethod.GET)
                .baseUrl("https://api.anthropic.com")
                .addPathSegments("v1", path)
                .putQueryParam("limit", "1")
                .build()
        }
        val spanData =
            SpanData(
                name = "anthropic.models.list",
                route = "/v1/models",
                queryParams = listOf("limit"),
            )
        val call = startApiCall(clientOptions, spanData, HttpMethod.GET, RequestOptions.none())

        traceApiCall(clientOptions, call) { client ->
                client.execute(request("first"), RequestOptions.none()).close()
                client.execute(request("second"), RequestOptions.none()).parseable { "parsed" }
            }
            .parse()

        val span = recorder.only()
        assertThat(span.string("url.full")).isEqualTo("https://api.anthropic.com/v1/first?limit=1")
    }

    @Test
    fun callThatFailsBeforeItsRequestIsBuilt_hasTheMethodAndTheRouteAndNoUrl() {
        val clientOptions =
            ClientOptions.builder().httpClient(FakeHttpClient()).openTelemetry(options()).build()
        val spanData = SpanData(name = "anthropic.messages.create", route = "/v1/messages")
        val failure = IllegalStateException("The request could not be built.")

        assertThrows<IllegalStateException> {
            val call = startApiCall(clientOptions, spanData, HttpMethod.POST, RequestOptions.none())
            traceApiCall<String>(clientOptions, call) { throw failure }
        }
        assertThrows<ExecutionException> {
            val call = startApiCall(clientOptions, spanData, HttpMethod.POST, RequestOptions.none())
            traceApiCallAsync(clientOptions, call) {
                    CompletableFuture<HttpResponseFor<String>>().apply {
                        completeExceptionally(failure)
                    }
                }
                .get()
        }

        assertThat(recorder.await(2)).allSatisfy { span ->
            assertThat(span.status.statusCode).isEqualTo(StatusCode.ERROR)
            assertThat(span.string("http.request.method")).isEqualTo("POST")
            assertThat(span.string("http.route")).isEqualTo("/v1/messages")
            assertThat(attributesOf(span))
                .doesNotContainKeys(*URL_ATTRIBUTES)
                .doesNotContainKey("http.request.resend_count")
        }
    }

    /**
     * The backends of Amazon Bedrock and of Vertex AI rewrite the path as this one does: in the
     * HTTP client, below the tracing, which so records the path that the SDK built.
     */
    @Test
    fun urlPath_whenTheBackendRewritesThePathInTheHttpClient_isThePathBeforeTheRewrite() {
        stubEveryRequest(MESSAGE_JSON.trimIndent())
        val backend =
            object : Backend {
                override fun baseUrl(): String = baseUrl

                override fun prepareRequest(request: HttpRequest): HttpRequest =
                    request
                        .toBuilder()
                        .replaceAllPathSegments("model", "claude-requested", "invoke")
                        .build()

                override fun close() {}
            }
        val client =
            AnthropicOkHttpClient.builder()
                .backend(backend)
                .maxRetries(0)
                .openTelemetry(options())
                .build()

        client.messages().create(messageParams())

        assertThat(urlsSent()).containsExactly("/model/claude-requested/invoke")
        val span = recorder.only()
        assertThat(span.string("http.route")).isEqualTo("/v1/messages")
        assertThat(span.string("url.path")).isEqualTo("/v1/messages")
        assertThat(span.string("url.full")).isEqualTo("$baseUrl/v1/messages")
    }
}
