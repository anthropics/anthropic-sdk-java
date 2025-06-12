// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.blocking.beta

import com.anthropic.core.ClientOptions
import com.anthropic.core.JsonValue
import com.anthropic.core.RequestOptions
import com.anthropic.core.checkRequired
import com.anthropic.core.handlers.errorHandler
import com.anthropic.core.handlers.jsonHandler
import com.anthropic.core.handlers.withErrorHandler
import com.anthropic.core.http.Headers
import com.anthropic.core.http.HttpMethod
import com.anthropic.core.http.HttpRequest
import com.anthropic.core.http.HttpResponse
import com.anthropic.core.http.HttpResponse.Handler
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.core.http.json
import com.anthropic.core.http.multipartFormData
import com.anthropic.core.http.parseable
import com.anthropic.core.prepare
import com.anthropic.models.beta.files.DeletedFile
import com.anthropic.models.beta.files.FileDeleteParams
import com.anthropic.models.beta.files.FileDownloadParams
import com.anthropic.models.beta.files.FileListPage
import com.anthropic.models.beta.files.FileListPageResponse
import com.anthropic.models.beta.files.FileListParams
import com.anthropic.models.beta.files.FileMetadata
import com.anthropic.models.beta.files.FileRetrieveMetadataParams
import com.anthropic.models.beta.files.FileUploadParams
import java.util.function.Consumer
import kotlin.jvm.optionals.getOrNull

class FileServiceImpl internal constructor(private val clientOptions: ClientOptions) : FileService {

    companion object {

        private val DEFAULT_HEADERS =
            Headers.builder().put("anthropic-beta", "files-api-2025-04-14").build()
    }

    private val withRawResponse: FileService.WithRawResponse by lazy {
        WithRawResponseImpl(clientOptions)
    }

    override fun withRawResponse(): FileService.WithRawResponse = withRawResponse

    override fun withOptions(modifier: Consumer<ClientOptions.Builder>): FileService =
        FileServiceImpl(clientOptions.toBuilder().apply(modifier::accept).build())

    override fun list(params: FileListParams, requestOptions: RequestOptions): FileListPage =
        // get /v1/files?beta=true
        withRawResponse().list(params, requestOptions).parse()

    override fun delete(params: FileDeleteParams, requestOptions: RequestOptions): DeletedFile =
        // delete /v1/files/{file_id}?beta=true
        withRawResponse().delete(params, requestOptions).parse()

    override fun download(
        params: FileDownloadParams,
        requestOptions: RequestOptions,
    ): HttpResponse =
        // get /v1/files/{file_id}/content?beta=true
        withRawResponse().download(params, requestOptions)

    override fun retrieveMetadata(
        params: FileRetrieveMetadataParams,
        requestOptions: RequestOptions,
    ): FileMetadata =
        // get /v1/files/{file_id}?beta=true
        withRawResponse().retrieveMetadata(params, requestOptions).parse()

    override fun upload(params: FileUploadParams, requestOptions: RequestOptions): FileMetadata =
        // post /v1/files?beta=true
        withRawResponse().upload(params, requestOptions).parse()

    class WithRawResponseImpl internal constructor(private val clientOptions: ClientOptions) :
        FileService.WithRawResponse {

        private val errorHandler: Handler<JsonValue> = errorHandler(clientOptions.jsonMapper)

        override fun withOptions(
            modifier: Consumer<ClientOptions.Builder>
        ): FileService.WithRawResponse =
            FileServiceImpl.WithRawResponseImpl(
                clientOptions.toBuilder().apply(modifier::accept).build()
            )

        private val listHandler: Handler<FileListPageResponse> =
            jsonHandler<FileListPageResponse>(clientOptions.jsonMapper)
                .withErrorHandler(errorHandler)

        override fun list(
            params: FileListParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<FileListPage> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "files")
                    .putQueryParam("beta", "true")
                    .putAllHeaders(DEFAULT_HEADERS)
                    .build()
                    .prepare(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.execute(request, requestOptions)
            return response.parseable {
                response
                    .use { listHandler.handle(it) }
                    .also {
                        if (requestOptions.responseValidation!!) {
                            it.validate()
                        }
                    }
                    .let {
                        FileListPage.builder()
                            .service(FileServiceImpl(clientOptions))
                            .params(params)
                            .response(it)
                            .build()
                    }
            }
        }

        private val deleteHandler: Handler<DeletedFile> =
            jsonHandler<DeletedFile>(clientOptions.jsonMapper).withErrorHandler(errorHandler)

        override fun delete(
            params: FileDeleteParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<DeletedFile> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("fileId", params.fileId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.DELETE)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "files", params._pathParam(0))
                    .putQueryParam("beta", "true")
                    .putAllHeaders(DEFAULT_HEADERS)
                    .apply { params._body().ifPresent { body(json(clientOptions.jsonMapper, it)) } }
                    .build()
                    .prepare(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.execute(request, requestOptions)
            return response.parseable {
                response
                    .use { deleteHandler.handle(it) }
                    .also {
                        if (requestOptions.responseValidation!!) {
                            it.validate()
                        }
                    }
            }
        }

        override fun download(
            params: FileDownloadParams,
            requestOptions: RequestOptions,
        ): HttpResponse {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("fileId", params.fileId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "files", params._pathParam(0), "content")
                    .putQueryParam("beta", "true")
                    .putAllHeaders(DEFAULT_HEADERS)
                    .build()
                    .prepare(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            return clientOptions.httpClient.execute(request, requestOptions)
        }

        private val retrieveMetadataHandler: Handler<FileMetadata> =
            jsonHandler<FileMetadata>(clientOptions.jsonMapper).withErrorHandler(errorHandler)

        override fun retrieveMetadata(
            params: FileRetrieveMetadataParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<FileMetadata> {
            // We check here instead of in the params builder because this can be specified
            // positionally or in the params class.
            checkRequired("fileId", params.fileId().getOrNull())
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.GET)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "files", params._pathParam(0))
                    .putQueryParam("beta", "true")
                    .putAllHeaders(DEFAULT_HEADERS)
                    .build()
                    .prepare(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.execute(request, requestOptions)
            return response.parseable {
                response
                    .use { retrieveMetadataHandler.handle(it) }
                    .also {
                        if (requestOptions.responseValidation!!) {
                            it.validate()
                        }
                    }
            }
        }

        private val uploadHandler: Handler<FileMetadata> =
            jsonHandler<FileMetadata>(clientOptions.jsonMapper).withErrorHandler(errorHandler)

        override fun upload(
            params: FileUploadParams,
            requestOptions: RequestOptions,
        ): HttpResponseFor<FileMetadata> {
            val request =
                HttpRequest.builder()
                    .method(HttpMethod.POST)
                    .baseUrl(clientOptions.baseUrl())
                    .addPathSegments("v1", "files")
                    .putQueryParam("beta", "true")
                    .putAllHeaders(DEFAULT_HEADERS)
                    .body(multipartFormData(clientOptions.jsonMapper, params._body()))
                    .build()
                    .prepare(clientOptions, params)
            val requestOptions = requestOptions.applyDefaults(RequestOptions.from(clientOptions))
            val response = clientOptions.httpClient.execute(request, requestOptions)
            return response.parseable {
                response
                    .use { uploadHandler.handle(it) }
                    .also {
                        if (requestOptions.responseValidation!!) {
                            it.validate()
                        }
                    }
            }
        }
    }
}
