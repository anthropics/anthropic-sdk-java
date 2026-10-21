package com.anthropic.internal.telemetry

import com.anthropic.client.AnthropicClient
import com.anthropic.client.AnthropicClientAsync
import com.anthropic.client.okhttp.AnthropicOkHttpClientAsync
import com.anthropic.core.RequestOptions
import com.anthropic.core.jsonMapper
import com.anthropic.core.opentelemetry.OpenTelemetryContentMode
import com.anthropic.core.opentelemetry.OpenTelemetryOptions
import com.anthropic.core.opentelemetry.OpenTelemetryRequestOptions
import com.anthropic.errors.BadRequestException
import com.anthropic.models.beta.agents.AgentCreateParams
import com.anthropic.models.beta.agents.BetaManagedAgentsModel
import com.anthropic.models.beta.environments.EnvironmentCreateParams
import com.anthropic.models.beta.environments.work.WorkRetrieveParams
import com.anthropic.models.beta.memorystores.MemoryStoreCreateParams
import com.anthropic.models.beta.memorystores.memories.MemoryCreateParams
import com.anthropic.models.beta.memorystores.memories.MemoryRetrieveParams
import com.anthropic.models.beta.memorystores.memoryversions.MemoryVersionRetrieveParams
import com.anthropic.models.beta.organization.federation.issuers.IssuerCreateParams
import com.anthropic.models.beta.organization.federation.rules.workspaces.WorkspaceAddParams as FederationRuleWorkspaceAddParams
import com.anthropic.models.beta.organization.federation.rules.workspaces.WorkspaceRemoveParams as FederationRuleWorkspaceRemoveParams
import com.anthropic.models.beta.organization.invites.InviteCreateParams
import com.anthropic.models.beta.organization.serviceaccounts.ServiceAccountCreateParams
import com.anthropic.models.beta.organization.serviceaccounts.workspaces.WorkspaceAddParams as ServiceAccountWorkspaceAddParams
import com.anthropic.models.beta.organization.serviceaccounts.workspaces.WorkspaceRemoveParams as ServiceAccountWorkspaceRemoveParams
import com.anthropic.models.beta.organization.workspaces.BetaNoBillingWorkspaceRole
import com.anthropic.models.beta.organization.workspaces.WorkspaceCreateParams
import com.anthropic.models.beta.organization.workspaces.members.MemberAddParams
import com.anthropic.models.beta.organization.workspaces.members.MemberRetrieveParams
import com.anthropic.models.beta.organization.workspaces.serviceaccounts.ServiceAccountAddParams as WorkspaceServiceAccountAddParams
import com.anthropic.models.beta.organization.workspaces.serviceaccounts.ServiceAccountRetrieveParams as WorkspaceServiceAccountRetrieveParams
import com.anthropic.models.beta.sessions.BetaManagedAgentsAgentParams
import com.anthropic.models.beta.sessions.BetaManagedAgentsAgentWithOverridesParams
import com.anthropic.models.beta.sessions.BetaManagedAgentsFileResourceParams
import com.anthropic.models.beta.sessions.SessionCreateParams
import com.anthropic.models.beta.sessions.events.BetaManagedAgentsTextBlock
import com.anthropic.models.beta.sessions.events.BetaManagedAgentsUserMessageEventParams
import com.anthropic.models.beta.sessions.events.EventSendParams
import com.anthropic.models.beta.sessions.resources.ResourceAddParams
import com.anthropic.models.beta.sessions.resources.ResourceRetrieveParams
import com.anthropic.models.beta.sessions.threads.ThreadArchiveParams
import com.anthropic.models.beta.sessions.threads.ThreadRetrieveParams
import com.anthropic.models.beta.sessions.threads.events.EventListParams as ThreadEventListParams
import com.anthropic.models.beta.sessions.threads.events.EventStreamParams as ThreadEventStreamParams
import com.anthropic.models.beta.skills.versions.VersionRetrieveParams
import com.anthropic.models.beta.tunnels.TunnelCreateParams
import com.anthropic.models.beta.tunnels.certificates.CertificateCreateParams
import com.anthropic.models.beta.tunnels.certificates.CertificateRetrieveParams
import com.anthropic.models.beta.userprofiles.UserProfileCreateParams
import com.anthropic.models.beta.vaults.VaultCreateParams
import com.anthropic.models.beta.vaults.credentials.CredentialRetrieveParams
import com.anthropic.models.messages.batches.BatchCreateParams
import com.fasterxml.jackson.databind.JsonNode
import com.github.tomakehurst.wiremock.client.MappingBuilder
import com.github.tomakehurst.wiremock.client.WireMock.aResponse
import com.github.tomakehurst.wiremock.client.WireMock.any
import com.github.tomakehurst.wiremock.client.WireMock.delete
import com.github.tomakehurst.wiremock.client.WireMock.get
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
import io.opentelemetry.sdk.trace.data.SpanData
import io.opentelemetry.sdk.trace.samplers.Sampler
import io.opentelemetry.sdk.trace.samplers.SamplingResult
import java.util.concurrent.CopyOnWriteArrayList
import java.util.concurrent.TimeUnit
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.entry
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.parallel.ResourceLock

/**
 * The resource IDs on the spans of the methods that are not Messages methods: one for each ID in
 * the request's path, and one for the object that a `create` made.
 */
@WireMockTest
@ResourceLock("https://github.com/wiremock/wiremock/issues/169")
internal class ResourceIdsTest : TracingTestSupport() {

    private companion object {

        /** The ID attributes that are not resource IDs. */
        val OTHER_IDS =
            setOf(
                "anthropic.request.id",
                "anthropic.organization.id",
                "anthropic.workspace.id",
                "gen_ai.response.id",
                "gen_ai.conversation.id",
            )

        /** A created Session: its agent's model is the one the Session resolved. */
        const val SESSION_JSON =
            """
            {
              "id": "sesn_created", "environment_id": "env_1",
              "agent": {
                "id": "agent_1", "name": "Support agent",
                "model": {"id": "claude-resolved", "speed": "standard"}
              }
            }
            """
    }

    /** The resource IDs of [span], by attribute. */
    private fun resourceIdsOf(span: SpanData): Map<String, Any> =
        attributesOf(span).filterKeys { it.endsWith(".id") && it !in OTHER_IDS }

    private fun stubJson(request: MappingBuilder, body: String) {
        stubFor(request.willReturn(ok(body).withHeader("Content-Type", "application/json")))
    }

    private fun stubError(request: MappingBuilder) {
        stubFor(
            request.willReturn(
                aResponse()
                    .withStatus(400)
                    .withHeader("Content-Type", "application/json")
                    .withBody(ERROR_JSON.replace("rate_limit_error", "invalid_request_error"))
            )
        )
    }

    private fun stubEventStream(path: String) {
        stubFor(
            get(urlPathEqualTo(path))
                .willReturn(ok(EVENTS_BODY).withHeader("Content-Type", "text/event-stream"))
        )
    }

    private fun sessionParams(): SessionCreateParams =
        SessionCreateParams.builder().agent("agent_1").environmentId("env_1").build()

    private fun agentParams(): AgentCreateParams =
        AgentCreateParams.builder()
            .model(BetaManagedAgentsModel.CLAUDE_OPUS_5)
            .name("My First Agent")
            .build()

    private fun sendParams(): EventSendParams =
        EventSendParams.builder()
            .sessionId("sesn_1")
            .addUserMessageEvent(
                listOf(
                    BetaManagedAgentsUserMessageEventParams.Content.ofText(
                        BetaManagedAgentsTextBlock.builder()
                            .text("Where is my order? USER-SECRET")
                            .build()
                    )
                )
            )
            .build()

    @Test
    fun sessions_recordTheSessionIdOfTheirPath() {
        val session = """{"id": "sesn_from_the_response"}"""
        stubJson(get(urlPathEqualTo("/v1/sessions/sesn_1")), session)
        stubJson(post(urlPathEqualTo("/v1/sessions/sesn_1")), session)
        stubJson(delete(urlPathEqualTo("/v1/sessions/sesn_1")), session)
        stubJson(post(urlPathEqualTo("/v1/sessions/sesn_1/archive")), session)
        val async = asyncClient()

        client.beta().sessions().retrieve("sesn_1")
        client.beta().sessions().update("sesn_1")
        client.beta().sessions().delete("sesn_1")
        client.beta().sessions().archive("sesn_1")
        async.beta().sessions().retrieve("sesn_1").get(30, TimeUnit.SECONDS)
        async.beta().sessions().update("sesn_1").get(30, TimeUnit.SECONDS)
        async.beta().sessions().delete("sesn_1").get(30, TimeUnit.SECONDS)
        async.beta().sessions().archive("sesn_1").get(30, TimeUnit.SECONDS)

        val spans = recorder.await(8)
        assertThat(spans.map { it.name })
            .containsExactlyInAnyOrder(
                "anthropic.sessions.retrieve",
                "anthropic.sessions.update",
                "anthropic.sessions.delete",
                "anthropic.sessions.archive",
                "anthropic.sessions.retrieve",
                "anthropic.sessions.update",
                "anthropic.sessions.delete",
                "anthropic.sessions.archive",
            )
        // The ID is the path's, and not the one of the object that the response has.
        assertThat(spans).allSatisfy {
            assertThat(resourceIdsOf(it)).containsExactly(entry("anthropic.session.id", "sesn_1"))
        }
    }

    @Test
    fun sessionsCreate_recordsTheIdOfTheCreatedSession() {
        stubJson(post(urlPathEqualTo("/v1/sessions")), """{"id": "sesn_created"}""")

        client.beta().sessions().create(sessionParams())
        asyncClient().beta().sessions().create(sessionParams()).get(30, TimeUnit.SECONDS)

        assertThat(recorder.await(2)).allSatisfy {
            assertThat(it.name).isEqualTo("anthropic.sessions.create")
            assertThat(it.status.statusCode).isEqualTo(StatusCode.OK)
            assertThat(resourceIdsOf(it))
                .containsOnly(
                    entry("anthropic.session.id", "sesn_created"),
                    entry("gen_ai.agent.id", "agent_1"),
                    entry("anthropic.session.environment.id", "env_1"),
                )
        }
    }

    @Test
    fun sessionsCreate_recordsTheAgentItsModelAndTheEnvironment() {
        stubJson(post(urlPathEqualTo("/v1/sessions")), SESSION_JSON)

        client.beta().sessions().create(sessionParams())
        asyncClient().beta().sessions().create(sessionParams()).get(30, TimeUnit.SECONDS)

        assertThat(recorder.await(2)).allSatisfy {
            assertThat(it.name).isEqualTo("anthropic.sessions.create")
            assertThat(it.string("gen_ai.agent.id")).isEqualTo("agent_1")
            assertThat(it.string("anthropic.session.environment.id")).isEqualTo("env_1")
            assertThat(it.string("gen_ai.agent.name")).isEqualTo("Support agent")
            assertThat(it.string("gen_ai.request.model")).isEqualTo("claude-resolved")
        }
    }

    @Test
    fun sessionsCreate_agentGivenAsAnObject_recordsItsId() {
        stubJson(post(urlPathEqualTo("/v1/sessions")), SESSION_JSON)
        val agent = BetaManagedAgentsAgentParams.builder().id("agent_pinned").version(2).build()
        val agentWithOverrides =
            BetaManagedAgentsAgentWithOverridesParams.builder()
                .id("agent_overridden")
                .model(BetaManagedAgentsModel.of("claude-requested"))
                .build()

        client.beta().sessions().create(sessionParams().toBuilder().agent(agent).build())
        asyncClient()
            .beta()
            .sessions()
            .create(sessionParams().toBuilder().agent(agentWithOverrides).build())
            .get(30, TimeUnit.SECONDS)

        val spans = recorder.await(2)
        assertThat(spans.map { it.string("gen_ai.agent.id") })
            .containsExactlyInAnyOrder("agent_pinned", "agent_overridden")
        // The model is the one that the Session resolved, and not the request's override.
        assertThat(spans).allSatisfy {
            assertThat(it.string("gen_ai.request.model")).isEqualTo("claude-resolved")
        }
    }

    @Test
    fun sessionsCreate_failed_recordsTheAgentAndTheEnvironmentOfItsRequest() {
        stubError(post(urlPathEqualTo("/v1/sessions")))
        val async = asyncClient().beta().sessions().create(sessionParams())

        assertThrows<BadRequestException> { client.beta().sessions().create(sessionParams()) }
        assertThat(async).failsWithin(30, TimeUnit.SECONDS)

        assertThat(recorder.await(2)).allSatisfy {
            assertThat(it.name).isEqualTo("anthropic.sessions.create")
            assertThat(it.status.statusCode).isEqualTo(StatusCode.ERROR)
            assertThat(it.string("error.type")).isEqualTo("invalid_request_error")
            assertThat(resourceIdsOf(it))
                .containsOnly(
                    entry("gen_ai.agent.id", "agent_1"),
                    entry("anthropic.session.environment.id", "env_1"),
                )
            assertThat(attributesOf(it))
                .doesNotContainKeys("gen_ai.agent.name", "gen_ai.request.model")
        }
    }

    @Test
    fun sessionsRetrieveAndUpdate_recordNothingOfTheSessionTheyReturn() {
        stubJson(get(urlPathEqualTo("/v1/sessions/sesn_created")), SESSION_JSON)
        stubJson(post(urlPathEqualTo("/v1/sessions/sesn_created")), SESSION_JSON)

        client.beta().sessions().retrieve("sesn_created")
        client.beta().sessions().update("sesn_created")
        asyncClient().beta().sessions().retrieve("sesn_created").get(30, TimeUnit.SECONDS)

        assertThat(recorder.await(3)).allSatisfy {
            assertThat(attributesOf(it))
                .doesNotContainKeys(
                    "gen_ai.agent.id",
                    "gen_ai.agent.name",
                    "gen_ai.request.model",
                    "anthropic.session.environment.id",
                )
        }
    }

    @Test
    fun sessionsCreate_responseWithoutAStringId_recordsNoId() {
        stubJson(post(urlPathEqualTo("/v1/sessions")), """{"id": 7}""")
        client.beta().sessions().create(sessionParams())
        stubJson(post(urlPathEqualTo("/v1/sessions")), """{"type": "session"}""")
        client.beta().sessions().create(sessionParams())

        assertThat(recorder.spans).hasSize(2).allSatisfy {
            assertThat(it.status.statusCode).isEqualTo(StatusCode.OK)
            assertThat(resourceIdsOf(it)).doesNotContainKey("anthropic.session.id")
        }
    }

    @Test
    fun sessionsCreate_rawResponse_recordsTheIdOnceParsed() {
        stubJson(post(urlPathEqualTo("/v1/sessions")), """{"id": "sesn_created"}""")

        client.beta().sessions().withRawResponse().create(sessionParams()).use {
            assertThat(recorder.spans).isEmpty()
            it.parse()
        }

        assertThat(resourceIdsOf(recorder.only()))
            .containsEntry("anthropic.session.id", "sesn_created")
    }

    @Test
    fun lists_recordOnlyTheIdsOfTheirPath() {
        val sessions = """{"data": [{"id": "sesn_listed"}], "next_page": null}"""
        stubJson(get(urlPathEqualTo("/v1/sessions")), sessions)
        stubJson(get(urlPathEqualTo("/v1/agents")), """{"data": [{"id": "agent_listed"}]}""")
        stubJson(get(urlPathEqualTo("/v1/messages/batches")), """{"data": [{"id": "b_listed"}]}""")
        stubJson(get(urlPathEqualTo("/v1/sessions/sesn_1/events")), """{"data": []}""")
        stubJson(get(urlPathEqualTo("/v1/sessions/sesn_1/threads")), """{"data": []}""")
        stubJson(get(urlPathEqualTo("/v1/agents/agent_1/versions")), """{"data": []}""")
        stubJson(
            get(urlPathEqualTo("/v1/sessions/sesn_1/threads/sthr_1/events")),
            """{"data": []}""",
        )
        val threadEvents = ThreadEventListParams.builder().sessionId("sesn_1").build()

        client.beta().sessions().list()
        client.beta().agents().list()
        client.messages().batches().list()
        client.beta().sessions().events().list("sesn_1")
        client.beta().sessions().threads().list("sesn_1")
        client.beta().agents().versions().list("agent_1")
        client.beta().sessions().threads().events().list("sthr_1", threadEvents)

        val session = entry("anthropic.session.id", "sesn_1")
        assertThat(resourceIdsOf(recorder.named("anthropic.sessions.list"))).isEmpty()
        assertThat(resourceIdsOf(recorder.named("anthropic.agents.list"))).isEmpty()
        assertThat(resourceIdsOf(recorder.named("anthropic.messages.batches.list"))).isEmpty()
        assertThat(resourceIdsOf(recorder.named("anthropic.sessions.events.list")))
            .containsExactly(session)
        assertThat(resourceIdsOf(recorder.named("anthropic.sessions.threads.list")))
            .containsExactly(session)
        assertThat(resourceIdsOf(recorder.named("anthropic.agents.versions.list")))
            .containsExactly(entry("gen_ai.agent.id", "agent_1"))
        assertThat(resourceIdsOf(recorder.named("anthropic.sessions.threads.events.list")))
            .containsOnly(session, entry("anthropic.session.thread.id", "sthr_1"))
    }

    @Test
    fun laterPage_recordsTheIdsOfItsPath() {
        stubFor(
            get(urlPathEqualTo("/v1/sessions/sesn_1/threads"))
                .inScenario("pages")
                .whenScenarioStateIs(Scenario.STARTED)
                .willSetStateTo("second")
                .willReturn(
                    ok("""{"data": [{"id": "sthr_1"}], "next_page": "page_2"}""")
                        .withHeader("Content-Type", "application/json")
                )
        )
        stubFor(
            get(urlPathEqualTo("/v1/sessions/sesn_1/threads"))
                .inScenario("pages")
                .whenScenarioStateIs("second")
                .willReturn(
                    ok("""{"data": [], "next_page": null}""")
                        .withHeader("Content-Type", "application/json")
                )
        )

        val page = client.beta().sessions().threads().list("sesn_1")
        assertThat(page.hasNextPage()).isTrue()
        page.nextPage()

        assertThat(recorder.spans).hasSize(2).allSatisfy {
            assertThat(it.name).isEqualTo("anthropic.sessions.threads.list")
            assertThat(resourceIdsOf(it)).containsExactly(entry("anthropic.session.id", "sesn_1"))
        }
    }

    @Test
    fun sessionEvents_recordTheSessionIdAndNoIdOfTheEventsTheySend() {
        stubJson(
            post(urlPathEqualTo("/v1/sessions/sesn_1/events")),
            """{"data": [{"id": "sevt_created", "type": "user.message"}]}""",
        )
        stubEventStream("/v1/sessions/sesn_1/events/stream")
        val async = asyncClient()

        client.beta().sessions().events().send(sendParams())
        async.beta().sessions().events().send(sendParams()).get(30, TimeUnit.SECONDS)
        client.beta().sessions().events().streamStreaming("sesn_1").use { it.stream().count() }

        val spans = recorder.await(3)
        assertThat(spans.map { it.name })
            .containsExactlyInAnyOrder(
                "anthropic.sessions.events.send",
                "anthropic.sessions.events.send",
                "anthropic.sessions.events.stream",
            )
        assertThat(spans).allSatisfy {
            assertThat(resourceIdsOf(it)).containsExactly(entry("anthropic.session.id", "sesn_1"))
            assertThat(it.attributeText()).doesNotContain("sevt_created")
        }
    }

    @Test
    fun sessionThreads_recordTheSessionIdAndTheThreadId() {
        val thread = """{"id": "sthr_from_the_response"}"""
        stubJson(get(urlPathEqualTo("/v1/sessions/sesn_1/threads/sthr_1")), thread)
        stubJson(post(urlPathEqualTo("/v1/sessions/sesn_1/threads/sthr_1/archive")), thread)
        stubEventStream("/v1/sessions/sesn_1/threads/sthr_1/stream")
        val retrieve = ThreadRetrieveParams.builder().sessionId("sesn_1").threadId("sthr_1").build()
        val archive = ThreadArchiveParams.builder().sessionId("sesn_1").threadId("sthr_1").build()
        val stream =
            ThreadEventStreamParams.builder().sessionId("sesn_1").threadId("sthr_1").build()
        val async = asyncClient()

        client.beta().sessions().threads().retrieve(retrieve)
        client.beta().sessions().threads().archive(archive)
        async.beta().sessions().threads().retrieve(retrieve).get(30, TimeUnit.SECONDS)
        async.beta().sessions().threads().archive(archive).get(30, TimeUnit.SECONDS)
        client.beta().sessions().threads().events().streamStreaming(stream).use {
            it.stream().count()
        }

        val spans = recorder.await(5)
        assertThat(spans.map { it.name })
            .containsExactlyInAnyOrder(
                "anthropic.sessions.threads.retrieve",
                "anthropic.sessions.threads.archive",
                "anthropic.sessions.threads.retrieve",
                "anthropic.sessions.threads.archive",
                "anthropic.sessions.threads.events.stream",
            )
        assertThat(spans).allSatisfy {
            assertThat(resourceIdsOf(it))
                .containsOnly(
                    entry("anthropic.session.id", "sesn_1"),
                    entry("anthropic.session.thread.id", "sthr_1"),
                )
        }
    }

    /** Every method whose path has a Session thread's ID, in the blocking and the async client. */
    private fun callEverySessionThreadMethod(
        client: AnthropicClient,
        async: AnthropicClientAsync,
        requestOptions: RequestOptions = RequestOptions.none(),
    ) {
        val thread = """{"id": "sthr_from_the_response"}"""
        stubJson(get(urlPathEqualTo("/v1/sessions/sesn_1/threads/sthr_1")), thread)
        stubJson(post(urlPathEqualTo("/v1/sessions/sesn_1/threads/sthr_1/archive")), thread)
        stubJson(
            get(urlPathEqualTo("/v1/sessions/sesn_1/threads/sthr_1/events")),
            """{"data": []}""",
        )
        stubEventStream("/v1/sessions/sesn_1/threads/sthr_1/stream")
        val retrieve = ThreadRetrieveParams.builder().sessionId("sesn_1").threadId("sthr_1").build()
        val archive = ThreadArchiveParams.builder().sessionId("sesn_1").threadId("sthr_1").build()
        val list = ThreadEventListParams.builder().sessionId("sesn_1").threadId("sthr_1").build()
        val stream =
            ThreadEventStreamParams.builder().sessionId("sesn_1").threadId("sthr_1").build()

        val threads = client.beta().sessions().threads()
        threads.retrieve(retrieve, requestOptions)
        threads.archive(archive, requestOptions)
        threads.events().list(list, requestOptions)
        threads.events().streamStreaming(stream, requestOptions).use { it.stream().count() }
        val asyncThreads = async.beta().sessions().threads()
        asyncThreads.retrieve(retrieve, requestOptions).get(30, TimeUnit.SECONDS)
        asyncThreads.archive(archive, requestOptions).get(30, TimeUnit.SECONDS)
        asyncThreads.events().list(list, requestOptions).get(30, TimeUnit.SECONDS)
        asyncThreads
            .events()
            .streamStreaming(stream, requestOptions)
            .subscribe {}
            .onCompleteFuture()
            .get(30, TimeUnit.SECONDS)
    }

    @Test
    fun sessionThreads_startWithTheThreadIdAsTheConversationId() {
        val seenAtStart = CopyOnWriteArrayList<Map<String, Any>>()
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
                    seenAtStart.add(
                        attributes
                            .asMap()
                            .mapKeys { it.key.key }
                            .filterKeys {
                                it.endsWith("thread.id") || it.endsWith("conversation.id")
                            }
                    )
                    return SamplingResult.recordAndSample()
                }

                override fun getDescription(): String = "records what it is given"
            }
        val openTelemetry =
            OpenTelemetryOptions.builder()
                .tracerProvider(SdkTracerProvider.builder().setSampler(sampler).build())
                .build()

        callEverySessionThreadMethod(
            clientBuilder().openTelemetry(openTelemetry).build(),
            AnthropicOkHttpClientAsync.builder()
                .baseUrl(baseUrl)
                .apiKey("my-anthropic-api-key")
                .maxRetries(0)
                .openTelemetry(openTelemetry)
                .build(),
        )

        assertThat(seenAtStart).hasSize(8).allSatisfy {
            assertThat(it)
                .containsOnly(
                    entry("anthropic.session.thread.id", "sthr_1"),
                    entry("gen_ai.conversation.id", "sthr_1"),
                )
        }
    }

    @Test
    fun sessionThreads_conversationIdOfTheApplication_replacesTheThreadId() {
        val requestOptions =
            RequestOptions.builder()
                .openTelemetry(
                    OpenTelemetryRequestOptions.builder().conversationId("conv_1").build()
                )
                .build()

        callEverySessionThreadMethod(client, asyncClient(), requestOptions)

        assertThat(recorder.await(8)).allSatisfy {
            assertThat(it.string("anthropic.session.thread.id")).isEqualTo("sthr_1")
            assertThat(it.string("gen_ai.conversation.id")).isEqualTo("conv_1")
        }
    }

    @Test
    fun pathWithoutAThreadId_hasNoConversationId() {
        stubJson(get(urlPathEqualTo("/v1/sessions/sesn_1")), """{"id": "sesn_1"}""")
        stubJson(get(urlPathEqualTo("/v1/sessions/sesn_1/threads")), """{"data": []}""")
        val async = asyncClient()

        client.beta().sessions().retrieve("sesn_1")
        client.beta().sessions().threads().list("sesn_1")
        async.beta().sessions().retrieve("sesn_1").get(30, TimeUnit.SECONDS)
        async.beta().sessions().threads().list("sesn_1").get(30, TimeUnit.SECONDS)

        assertThat(recorder.await(4)).allSatisfy {
            assertThat(it.string("anthropic.session.id")).isEqualTo("sesn_1")
            assertThat(attributesOf(it)).doesNotContainKey("gen_ai.conversation.id")
        }
    }

    @Test
    fun agents_recordTheAgentIdAsTheConventionsNameIt() {
        val agent = """{"id": "agent_from_the_response"}"""
        stubJson(post(urlPathEqualTo("/v1/agents")), """{"id": "agent_created"}""")
        stubJson(get(urlPathEqualTo("/v1/agents/agent_1")), agent)
        stubJson(post(urlPathEqualTo("/v1/agents/agent_1")), agent)
        stubJson(post(urlPathEqualTo("/v1/agents/agent_1/archive")), agent)
        val async = asyncClient()

        client.beta().agents().create(agentParams())
        async.beta().agents().create(agentParams()).get(30, TimeUnit.SECONDS)
        client.beta().agents().retrieve("agent_1")
        client.beta().agents().update("agent_1")
        client.beta().agents().archive("agent_1")
        async.beta().agents().retrieve("agent_1").get(30, TimeUnit.SECONDS)

        val spans = recorder.await(6)
        assertThat(spans.filter { it.name == "anthropic.agents.create" }).hasSize(2).allSatisfy {
            assertThat(resourceIdsOf(it)).containsExactly(entry("gen_ai.agent.id", "agent_created"))
        }
        assertThat(spans.filter { it.name != "anthropic.agents.create" }).hasSize(4).allSatisfy {
            assertThat(resourceIdsOf(it)).containsExactly(entry("gen_ai.agent.id", "agent_1"))
        }
    }

    @Test
    fun agentsCreate_failed_recordsNoId() {
        stubError(post(urlPathEqualTo("/v1/agents")))

        assertThrows<BadRequestException> { client.beta().agents().create(agentParams()) }

        assertThat(resourceIdsOf(recorder.only())).isEmpty()
    }

    @Test
    fun messageBatchesCreate_failed_recordsNoId() {
        stubError(post(urlPathEqualTo("/v1/messages/batches")))
        val params =
            BatchCreateParams.builder()
                .addRequest(
                    BatchCreateParams.Request.builder()
                        .customId("a")
                        .params(
                            BatchCreateParams.Request.Params.builder()
                                .model("claude-requested")
                                .maxTokens(1)
                                .addUserMessage("hello")
                                .build()
                        )
                        .build()
                )
                .build()

        assertThrows<BadRequestException> { client.messages().batches().create(params) }

        val span = recorder.only()
        assertThat(span.name).isEqualTo("anthropic.messages.batches.create")
        assertThat(resourceIdsOf(span)).isEmpty()
    }

    @Test
    fun resourceIds_areMetadata() {
        // The client records no content, and the IDs are on the spans all the same; content mode
        // adds nothing to them.
        stubJson(get(urlPathEqualTo("/v1/sessions/sesn_1")), """{"id": "sesn_1"}""")
        stubJson(post(urlPathEqualTo("/v1/sessions")), SESSION_JSON)
        val withContent = clientBuilder(OpenTelemetryContentMode.CONTENT).build()

        for (client in listOf(client, withContent)) {
            client.beta().sessions().retrieve("sesn_1")
            client.beta().sessions().create(sessionParams())
        }

        val (retrieved, created, retrievedWithContent, createdWithContent) = recorder.spans
        assertThat(attributesOf(retrieved)).containsEntry("anthropic.session.id", "sesn_1")
        assertThat(attributesOf(created))
            .containsEntry("anthropic.session.id", "sesn_created")
            .containsEntry("gen_ai.agent.id", "agent_1")
            .containsEntry("gen_ai.agent.name", "Support agent")
            .containsEntry("gen_ai.request.model", "claude-resolved")
            .containsEntry("anthropic.session.environment.id", "env_1")
        assertThat(attributesOf(retrievedWithContent)).isEqualTo(attributesOf(retrieved))
        assertThat(attributesOf(createdWithContent)).isEqualTo(attributesOf(created))
    }

    /**
     * What `telemetry/resource-ids.json` says of a method's span: the HTTP method and the route of
     * its endpoint, the path of its request, with an index for each parameter, the ID attribute of
     * each parameter, and, for a method that creates an object, the attribute of that object's ID
     * and the response field that has it. The file is generated from the API's description,
     * together with the IDs that the service methods set.
     */
    private class Expectation(node: JsonNode) {
        val method: String = node.get("method").asText()
        val route: String = node.get("route").asText()
        val path: String = node.get("path").asText()
        val pathIds: List<String> = node.get("path_ids").map { it.asText() }
        val createdId: String? = node.get("created_id").get("attribute")?.asText()
        val createdField: String? = node.get("created_id").get("response_field")?.asText()
    }

    private val expectations: Map<String, Expectation> by lazy {
        val tree =
            ResourceIdsTest::class.java.getResourceAsStream("/telemetry/resource-ids.json").use {
                jsonMapper().readTree(it)
            }
        tree.fields().asSequence().associate { (span, node) -> span to Expectation(node) }
    }

    /**
     * One call of a method whose span is named [span]: [call] makes it with [pathIds], the IDs in
     * its path in order, against a server that answers with [body]. By default the body is the
     * object [created], with that ID in the field that the expectations name, or any object for a
     * method that creates none.
     */
    private class Case(
        val span: String,
        val pathIds: List<String> = emptyList(),
        val created: String? = null,
        val body: String? = null,
        val call: (AnthropicClient) -> Unit,
    )

    /**
     * Makes each call, and checks that its span has the ID attributes that the expectations give
     * its method, with the values of the case, and no other resource ID, and that it has the
     * method, the route and the path of the request.
     */
    private fun check(cases: List<Case>) {
        for (case in cases) {
            val expected = expectations.getValue(case.span)
            assertThat(case.pathIds).describedAs(case.span).hasSameSizeAs(expected.pathIds)
            val path =
                case.pathIds.foldIndexed(expected.path) { index, path, id ->
                    path.replace("{$index}", id)
                }
            val ids = expected.pathIds.zip(case.pathIds).toMap().toMutableMap()
            if (case.created != null) {
                ids[checkNotNull(expected.createdId)] = case.created
            }
            val body =
                case.body
                    ?: case.created?.let { """{"${checkNotNull(expected.createdField)}": "$it"}""" }
                    ?: """{"id": "id_from_the_response"}"""
            recorder.spans.clear()
            stubJson(any(urlPathEqualTo(path)), body)

            case.call(client)

            val span = recorder.named(case.span)
            assertThat(resourceIdsOf(span)).describedAs(case.span).isEqualTo(ids)
            assertThat(
                    listOf("http.request.method", "http.route", "url.path").map { span.string(it) }
                )
                .describedAs(case.span)
                .containsExactly(expected.method, expected.route, path)
        }
    }

    @Test
    fun everyResource_recordsTheIdsOfItsPath() {
        val beta = { client: AnthropicClient -> client.beta() }
        val org = { client: AnthropicClient -> client.beta().organization() }
        check(
            listOf(
                Case("anthropic.deployments.retrieve", listOf("depl_1")) {
                    beta(it).deployments().retrieve("depl_1")
                },
                Case("anthropic.deployment_runs.retrieve", listOf("drun_1")) {
                    beta(it).deploymentRuns().retrieve("drun_1")
                },
                Case("anthropic.dreams.retrieve", listOf("dream_1")) {
                    beta(it).dreams().retrieve("dream_1")
                },
                Case("anthropic.environments.retrieve", listOf("env_1")) {
                    beta(it).environments().retrieve("env_1")
                },
                Case("anthropic.environments.work.retrieve", listOf("env_1", "work_1")) {
                    beta(it)
                        .environments()
                        .work()
                        .retrieve(
                            WorkRetrieveParams.builder()
                                .environmentId("env_1")
                                .workId("work_1")
                                .build()
                        )
                },
                Case("anthropic.files.retrieve_metadata", listOf("file_1")) {
                    it.files().retrieveMetadata("file_1")
                },
                Case("anthropic.files.retrieve_metadata", listOf("file_1")) {
                    beta(it).files().retrieveMetadata("file_1")
                },
                Case("anthropic.memory_stores.retrieve", listOf("memstore_1")) {
                    beta(it).memoryStores().retrieve("memstore_1")
                },
                Case("anthropic.memory_stores.memories.retrieve", listOf("memstore_1", "mem_1")) {
                    beta(it)
                        .memoryStores()
                        .memories()
                        .retrieve(
                            MemoryRetrieveParams.builder()
                                .memoryStoreId("memstore_1")
                                .memoryId("mem_1")
                                .build()
                        )
                },
                Case(
                    "anthropic.memory_stores.memory_versions.retrieve",
                    listOf("memstore_1", "memver_1"),
                ) {
                    beta(it)
                        .memoryStores()
                        .memoryVersions()
                        .retrieve(
                            MemoryVersionRetrieveParams.builder()
                                .memoryStoreId("memstore_1")
                                .memoryVersionId("memver_1")
                                .build()
                        )
                },
                // The ID is the path's, also when it is an alias.
                Case("anthropic.models.retrieve", listOf("claude-latest")) {
                    it.models().retrieve("claude-latest")
                },
                Case("anthropic.organization.api_keys.retrieve", listOf("apikey_1")) {
                    org(it).apiKeys().retrieve("apikey_1")
                },
                Case("anthropic.organization.external_keys.retrieve", listOf("ekey_1")) {
                    org(it).externalKeys().retrieve("ekey_1")
                },
                Case("anthropic.organization.federation.issuers.retrieve", listOf("fiss_1")) {
                    org(it).federation().issuers().retrieve("fiss_1")
                },
                Case("anthropic.organization.federation.rules.retrieve", listOf("frul_1")) {
                    org(it).federation().rules().retrieve("frul_1")
                },
                // A Workspace in the path is not the Workspace of the request, which
                // `anthropic.workspace.id` is.
                Case(
                    "anthropic.organization.federation.rules.workspaces.remove",
                    listOf("frul_1", "wrkspc_1"),
                    body = "{}",
                ) {
                    org(it)
                        .federation()
                        .rules()
                        .workspaces()
                        .remove(
                            FederationRuleWorkspaceRemoveParams.builder()
                                .federationRuleId("frul_1")
                                .workspaceId("wrkspc_1")
                                .build()
                        )
                },
                Case("anthropic.organization.invites.retrieve", listOf("invite_1")) {
                    org(it).invites().retrieve("invite_1")
                },
                Case("anthropic.organization.service_accounts.retrieve", listOf("svac_1")) {
                    org(it).serviceAccounts().retrieve("svac_1")
                },
                Case(
                    "anthropic.organization.service_accounts.workspaces.remove",
                    listOf("svac_1", "wrkspc_1"),
                    body = "{}",
                ) {
                    org(it)
                        .serviceAccounts()
                        .workspaces()
                        .remove(
                            ServiceAccountWorkspaceRemoveParams.builder()
                                .serviceAccountId("svac_1")
                                .workspaceId("wrkspc_1")
                                .build()
                        )
                },
                Case("anthropic.organization.users.retrieve", listOf("user_1")) {
                    org(it).users().retrieve("user_1")
                },
                Case("anthropic.organization.workspaces.retrieve", listOf("wrkspc_1")) {
                    org(it).workspaces().retrieve("wrkspc_1")
                },
                Case(
                    "anthropic.organization.workspaces.members.retrieve",
                    listOf("wrkspc_1", "user_1"),
                ) {
                    org(it)
                        .workspaces()
                        .members()
                        .retrieve(
                            MemberRetrieveParams.builder()
                                .workspaceId("wrkspc_1")
                                .userId("user_1")
                                .build()
                        )
                },
                Case(
                    "anthropic.organization.workspaces.rate_limits.list",
                    listOf("wrkspc_1"),
                    body = """{"data": []}""",
                ) {
                    org(it).workspaces().rateLimits().list("wrkspc_1")
                },
                Case(
                    "anthropic.organization.workspaces.service_accounts.retrieve",
                    listOf("wrkspc_1", "svac_1"),
                ) {
                    org(it)
                        .workspaces()
                        .serviceAccounts()
                        .retrieve(
                            WorkspaceServiceAccountRetrieveParams.builder()
                                .workspaceId("wrkspc_1")
                                .serviceAccountId("svac_1")
                                .build()
                        )
                },
                Case("anthropic.sessions.resources.retrieve", listOf("sesn_1", "sesrsc_1")) {
                    beta(it)
                        .sessions()
                        .resources()
                        .retrieve(
                            ResourceRetrieveParams.builder()
                                .sessionId("sesn_1")
                                .resourceId("sesrsc_1")
                                .build()
                        )
                },
                Case("anthropic.skills.retrieve", listOf("skill_1")) {
                    it.skills().retrieve("skill_1")
                },
                // A parameter of the path that is not an ID has an attribute too, with the path's
                // value.
                Case("anthropic.skills.versions.retrieve", listOf("skill_1", "latest")) {
                    beta(it)
                        .skills()
                        .versions()
                        .retrieve(
                            VersionRetrieveParams.builder()
                                .skillId("skill_1")
                                .version("latest")
                                .build()
                        )
                },
                Case("anthropic.tunnels.retrieve", listOf("tunnel_1")) {
                    beta(it).tunnels().retrieve("tunnel_1")
                },
                Case("anthropic.tunnels.certificates.retrieve", listOf("tunnel_1", "cert_1")) {
                    beta(it)
                        .tunnels()
                        .certificates()
                        .retrieve(
                            CertificateRetrieveParams.builder()
                                .tunnelId("tunnel_1")
                                .certificateId("cert_1")
                                .build()
                        )
                },
                Case("anthropic.user_profiles.retrieve", listOf("uprof_1")) {
                    beta(it).userProfiles().retrieve("uprof_1")
                },
                Case("anthropic.vaults.retrieve", listOf("vault_1")) {
                    beta(it).vaults().retrieve("vault_1")
                },
                Case("anthropic.vaults.credentials.retrieve", listOf("vault_1", "cred_1")) {
                    beta(it)
                        .vaults()
                        .credentials()
                        .retrieve(
                            CredentialRetrieveParams.builder()
                                .vaultId("vault_1")
                                .credentialId("cred_1")
                                .build()
                        )
                },
            )
        )
    }

    @Test
    fun creates_recordTheIdOfTheCreatedObject() {
        val beta = { client: AnthropicClient -> client.beta() }
        val org = { client: AnthropicClient -> client.beta().organization() }
        check(
            listOf(
                Case("anthropic.environments.create", created = "env_created") {
                    beta(it)
                        .environments()
                        .create(EnvironmentCreateParams.builder().name("python").build())
                },
                Case("anthropic.memory_stores.create", created = "memstore_created") {
                    beta(it)
                        .memoryStores()
                        .create(MemoryStoreCreateParams.builder().name("notes").build())
                },
                // A create under another object has that object's ID, from the path, as well.
                Case(
                    "anthropic.memory_stores.memories.create",
                    listOf("memstore_1"),
                    created = "mem_created",
                ) {
                    beta(it)
                        .memoryStores()
                        .memories()
                        .create(
                            MemoryCreateParams.builder()
                                .memoryStoreId("memstore_1")
                                .content("CONTENT-SECRET")
                                .path("notes")
                                .build()
                        )
                },
                Case("anthropic.organization.federation.issuers.create", created = "fiss_created") {
                    org(it)
                        .federation()
                        .issuers()
                        .create(
                            IssuerCreateParams.builder()
                                .issuerUrl("https://issuer.example")
                                .name("ci")
                                .build()
                        )
                },
                Case("anthropic.organization.invites.create", created = "invite_created") {
                    org(it)
                        .invites()
                        .create(
                            InviteCreateParams.builder()
                                .email("user@example.com")
                                .role(InviteCreateParams.Role.USER)
                                .build()
                        )
                },
                Case("anthropic.organization.service_accounts.create", created = "svac_created") {
                    org(it)
                        .serviceAccounts()
                        .create(ServiceAccountCreateParams.builder().name("ci-bot").build())
                },
                Case("anthropic.organization.workspaces.create", created = "wrkspc_created") {
                    org(it)
                        .workspaces()
                        .create(WorkspaceCreateParams.builder().name("prod").build())
                },
                Case(
                    "anthropic.sessions.resources.add",
                    listOf("sesn_1"),
                    created = "sesrsc_created",
                    body = """{"id": "sesrsc_created", "type": "file", "file_id": "file_1"}""",
                ) {
                    beta(it)
                        .sessions()
                        .resources()
                        .add(
                            ResourceAddParams.builder()
                                .sessionId("sesn_1")
                                .betaManagedAgentsFileResourceParams(
                                    BetaManagedAgentsFileResourceParams.builder()
                                        .fileId("file_1")
                                        .build()
                                )
                                .build()
                        )
                },
                Case("anthropic.tunnels.create", created = "tunnel_created") {
                    beta(it).tunnels().create(TunnelCreateParams.builder().build())
                },
                Case(
                    "anthropic.tunnels.certificates.create",
                    listOf("tunnel_1"),
                    created = "cert_created",
                ) {
                    beta(it)
                        .tunnels()
                        .certificates()
                        .create(
                            CertificateCreateParams.builder()
                                .tunnelId("tunnel_1")
                                .caCertificatePem("PEM")
                                .build()
                        )
                },
                Case("anthropic.user_profiles.create", created = "uprof_created") {
                    beta(it).userProfiles().create(UserProfileCreateParams.builder().build())
                },
                Case("anthropic.vaults.create", created = "vault_created") {
                    beta(it).vaults().create(VaultCreateParams.builder().displayName("v").build())
                },
            )
        )
    }

    @Test
    fun adds_recordTheIdThatTheResponseNamesAfterTheObject() {
        // A membership has no `id` of its own: the response names the object that was added.
        val org = { client: AnthropicClient -> client.beta().organization() }
        val role = BetaNoBillingWorkspaceRole.WORKSPACE_ADMIN
        check(
            listOf(
                Case(
                    "anthropic.organization.workspaces.members.add",
                    listOf("wrkspc_1"),
                    created = "user_added",
                    body = """{"user_id": "user_added", "workspace_id": "wrkspc_1"}""",
                ) {
                    org(it)
                        .workspaces()
                        .members()
                        .add(
                            MemberAddParams.builder()
                                .workspaceId("wrkspc_1")
                                .userId("user_added")
                                .workspaceRole(role)
                                .build()
                        )
                },
                Case(
                    "anthropic.organization.workspaces.service_accounts.add",
                    listOf("wrkspc_1"),
                    created = "svac_added",
                    body = """{"service_account_id": "svac_added", "workspace_id": "wrkspc_1"}""",
                ) {
                    org(it)
                        .workspaces()
                        .serviceAccounts()
                        .add(
                            WorkspaceServiceAccountAddParams.builder()
                                .workspaceId("wrkspc_1")
                                .serviceAccountId("svac_added")
                                .workspaceRole(role)
                                .build()
                        )
                },
                Case(
                    "anthropic.organization.service_accounts.workspaces.add",
                    listOf("svac_1"),
                    created = "wrkspc_added",
                    body = """{"service_account_id": "svac_1", "workspace_id": "wrkspc_added"}""",
                ) {
                    org(it)
                        .serviceAccounts()
                        .workspaces()
                        .add(
                            ServiceAccountWorkspaceAddParams.builder()
                                .serviceAccountId("svac_1")
                                .workspaceId("wrkspc_added")
                                .workspaceRole(role)
                                .build()
                        )
                },
                Case(
                    "anthropic.organization.federation.rules.workspaces.add",
                    listOf("frul_1"),
                    created = "wrkspc_added",
                ) {
                    org(it)
                        .federation()
                        .rules()
                        .workspaces()
                        .add(
                            FederationRuleWorkspaceAddParams.builder()
                                .federationRuleId("frul_1")
                                .workspaceId("wrkspc_added")
                                .build()
                        )
                },
                // The response names no object, so the span has the path's ID only.
                Case(
                    "anthropic.organization.workspaces.members.add",
                    listOf("wrkspc_1"),
                    body = """{"workspace_role": "workspace_admin"}""",
                ) {
                    org(it)
                        .workspaces()
                        .members()
                        .add(
                            MemberAddParams.builder()
                                .workspaceId("wrkspc_1")
                                .userId("user_added")
                                .workspaceRole(role)
                                .build()
                        )
                },
            )
        )
    }
}
