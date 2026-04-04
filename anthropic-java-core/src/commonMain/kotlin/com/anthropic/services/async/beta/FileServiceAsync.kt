// File generated from our OpenAPI spec by Stainless.

package com.anthropic.services.async.beta

import com.anthropic.core.ClientOptions
import com.anthropic.core.RequestOptions
import com.anthropic.core.http.HttpResponse
import com.anthropic.core.http.HttpResponseFor
import com.anthropic.models.beta.files.DeletedFile
import com.anthropic.models.beta.files.FileDeleteParams
import com.anthropic.models.beta.files.FileDownloadParams
import com.anthropic.models.beta.files.FileListPageAsync
import com.anthropic.models.beta.files.FileListParams
import com.anthropic.models.beta.files.FileMetadata
import com.anthropic.models.beta.files.FileRetrieveMetadataParams
import com.anthropic.models.beta.files.FileUploadParams
import java.util.function.Consumer

interface FileServiceAsync {

    /**
     * Returns a view of this service that provides access to raw HTTP responses for each method.
     */
    fun withRawResponse(): WithRawResponse

    /**
     * Returns a view of this service with the given option modifications applied.
     *
     * The original service is not modified.
     */
    fun withOptions(modifier: Consumer<ClientOptions.Builder>): FileServiceAsync

    /** List Files */
    suspend fun list(): FileListPageAsync = list(FileListParams.none())

    /** @see list */
    suspend fun list(
        params: FileListParams = FileListParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): FileListPageAsync

    /** @see list */
    suspend fun list(params: FileListParams = FileListParams.none()): FileListPageAsync =
        list(params, RequestOptions.none())

    /** @see list */
    suspend fun list(requestOptions: RequestOptions): FileListPageAsync =
        list(FileListParams.none(), requestOptions)

    /** Delete File */
    suspend fun delete(fileId: String): DeletedFile =
        delete(fileId, FileDeleteParams.none())

    /** @see delete */
    suspend fun delete(
        fileId: String,
        params: FileDeleteParams = FileDeleteParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): DeletedFile =
        delete(params.toBuilder().fileId(fileId).build(), requestOptions)

    /** @see delete */
    suspend fun delete(
        fileId: String,
        params: FileDeleteParams = FileDeleteParams.none(),
    ): DeletedFile = delete(fileId, params, RequestOptions.none())

    /** @see delete */
    suspend fun delete(
        params: FileDeleteParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): DeletedFile

    /** @see delete */
    suspend fun delete(params: FileDeleteParams): DeletedFile =
        delete(params, RequestOptions.none())

    /** @see delete */
    suspend fun delete(fileId: String, requestOptions: RequestOptions): DeletedFile =
        delete(fileId, FileDeleteParams.none(), requestOptions)

    /** Download File */
    suspend fun download(fileId: String): HttpResponse =
        download(fileId, FileDownloadParams.none())

    /** @see download */
    suspend fun download(
        fileId: String,
        params: FileDownloadParams = FileDownloadParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): HttpResponse =
        download(params.toBuilder().fileId(fileId).build(), requestOptions)

    /** @see download */
    suspend fun download(
        fileId: String,
        params: FileDownloadParams = FileDownloadParams.none(),
    ): HttpResponse = download(fileId, params, RequestOptions.none())

    /** @see download */
    suspend fun download(
        params: FileDownloadParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): HttpResponse

    /** @see download */
    suspend fun download(params: FileDownloadParams): HttpResponse =
        download(params, RequestOptions.none())

    /** @see download */
    suspend fun download(fileId: String, requestOptions: RequestOptions): HttpResponse =
        download(fileId, FileDownloadParams.none(), requestOptions)

    /** Get File Metadata */
    suspend fun retrieveMetadata(fileId: String): FileMetadata =
        retrieveMetadata(fileId, FileRetrieveMetadataParams.none())

    /** @see retrieveMetadata */
    suspend fun retrieveMetadata(
        fileId: String,
        params: FileRetrieveMetadataParams = FileRetrieveMetadataParams.none(),
        requestOptions: RequestOptions = RequestOptions.none(),
    ): FileMetadata =
        retrieveMetadata(params.toBuilder().fileId(fileId).build(), requestOptions)

    /** @see retrieveMetadata */
    suspend fun retrieveMetadata(
        fileId: String,
        params: FileRetrieveMetadataParams = FileRetrieveMetadataParams.none(),
    ): FileMetadata = retrieveMetadata(fileId, params, RequestOptions.none())

    /** @see retrieveMetadata */
    suspend fun retrieveMetadata(
        params: FileRetrieveMetadataParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): FileMetadata

    /** @see retrieveMetadata */
    suspend fun retrieveMetadata(params: FileRetrieveMetadataParams): FileMetadata =
        retrieveMetadata(params, RequestOptions.none())

    /** @see retrieveMetadata */
    suspend fun retrieveMetadata(
        fileId: String,
        requestOptions: RequestOptions,
    ): FileMetadata =
        retrieveMetadata(fileId, FileRetrieveMetadataParams.none(), requestOptions)

    /** Upload File */
    suspend fun upload(params: FileUploadParams): FileMetadata =
        upload(params, RequestOptions.none())

    /** @see upload */
    suspend fun upload(
        params: FileUploadParams,
        requestOptions: RequestOptions = RequestOptions.none(),
    ): FileMetadata

    /** A view of [FileServiceAsync] that provides access to raw HTTP responses for each method. */
    interface WithRawResponse {

        /**
         * Returns a view of this service with the given option modifications applied.
         *
         * The original service is not modified.
         */
        fun withOptions(modifier: Consumer<ClientOptions.Builder>): FileServiceAsync.WithRawResponse

        /**
         * Returns a raw HTTP response for `get /v1/files?beta=true`, but is otherwise the same as
         * [FileServiceAsync.list].
         */
        suspend fun list(): HttpResponseFor<FileListPageAsync> =
            list(FileListParams.none())

        /** @see list */
        suspend fun list(
            params: FileListParams = FileListParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<FileListPageAsync>

        /** @see list */
        suspend fun list(
            params: FileListParams = FileListParams.none()
        ): HttpResponseFor<FileListPageAsync> =
            list(params, RequestOptions.none())

        /** @see list */
        suspend fun list(
            requestOptions: RequestOptions
        ): HttpResponseFor<FileListPageAsync> =
            list(FileListParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `delete /v1/files/{file_id}?beta=true`, but is otherwise
         * the same as [FileServiceAsync.delete].
         */
        suspend fun delete(fileId: String): HttpResponseFor<DeletedFile> =
            delete(fileId, FileDeleteParams.none())

        /** @see delete */
        suspend fun delete(
            fileId: String,
            params: FileDeleteParams = FileDeleteParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<DeletedFile> =
            delete(params.toBuilder().fileId(fileId).build(), requestOptions)

        /** @see delete */
        suspend fun delete(
            fileId: String,
            params: FileDeleteParams = FileDeleteParams.none(),
        ): HttpResponseFor<DeletedFile> =
            delete(fileId, params, RequestOptions.none())

        /** @see delete */
        suspend fun delete(
            params: FileDeleteParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<DeletedFile>

        /** @see delete */
        suspend fun delete(params: FileDeleteParams): HttpResponseFor<DeletedFile> =
            delete(params, RequestOptions.none())

        /** @see delete */
        suspend fun delete(
            fileId: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<DeletedFile> =
            delete(fileId, FileDeleteParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `get /v1/files/{file_id}/content?beta=true`, but is
         * otherwise the same as [FileServiceAsync.download].
         */
        suspend fun download(fileId: String): HttpResponse =
            download(fileId, FileDownloadParams.none())

        /** @see download */
        suspend fun download(
            fileId: String,
            params: FileDownloadParams = FileDownloadParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponse =
            download(params.toBuilder().fileId(fileId).build(), requestOptions)

        /** @see download */
        suspend fun download(
            fileId: String,
            params: FileDownloadParams = FileDownloadParams.none(),
        ): HttpResponse = download(fileId, params, RequestOptions.none())

        /** @see download */
        suspend fun download(
            params: FileDownloadParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponse

        /** @see download */
        suspend fun download(params: FileDownloadParams): HttpResponse =
            download(params, RequestOptions.none())

        /** @see download */
        suspend fun download(
            fileId: String,
            requestOptions: RequestOptions,
        ): HttpResponse =
            download(fileId, FileDownloadParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `get /v1/files/{file_id}?beta=true`, but is otherwise the
         * same as [FileServiceAsync.retrieveMetadata].
         */
        suspend fun retrieveMetadata(fileId: String): HttpResponseFor<FileMetadata> =
            retrieveMetadata(fileId, FileRetrieveMetadataParams.none())

        /** @see retrieveMetadata */
        suspend fun retrieveMetadata(
            fileId: String,
            params: FileRetrieveMetadataParams = FileRetrieveMetadataParams.none(),
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<FileMetadata> =
            retrieveMetadata(params.toBuilder().fileId(fileId).build(), requestOptions)

        /** @see retrieveMetadata */
        suspend fun retrieveMetadata(
            fileId: String,
            params: FileRetrieveMetadataParams = FileRetrieveMetadataParams.none(),
        ): HttpResponseFor<FileMetadata> =
            retrieveMetadata(fileId, params, RequestOptions.none())

        /** @see retrieveMetadata */
        suspend fun retrieveMetadata(
            params: FileRetrieveMetadataParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<FileMetadata>

        /** @see retrieveMetadata */
        suspend fun retrieveMetadata(
            params: FileRetrieveMetadataParams
        ): HttpResponseFor<FileMetadata> =
            retrieveMetadata(params, RequestOptions.none())

        /** @see retrieveMetadata */
        suspend fun retrieveMetadata(
            fileId: String,
            requestOptions: RequestOptions,
        ): HttpResponseFor<FileMetadata> =
            retrieveMetadata(fileId, FileRetrieveMetadataParams.none(), requestOptions)

        /**
         * Returns a raw HTTP response for `post /v1/files?beta=true`, but is otherwise the same as
         * [FileServiceAsync.upload].
         */
        suspend fun upload(params: FileUploadParams): HttpResponseFor<FileMetadata> =
            upload(params, RequestOptions.none())

        /** @see upload */
        suspend fun upload(
            params: FileUploadParams,
            requestOptions: RequestOptions = RequestOptions.none(),
        ): HttpResponseFor<FileMetadata>
    }
}
