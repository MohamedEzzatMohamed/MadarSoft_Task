package com.tru.core.bases.base_repository


import com.tru.core.error.AppError
import com.tru.core.error.BadRequestException
import com.tru.core.error.GeneralException
import com.tru.core.error.IoException
import com.tru.core.error.ResponseException
import com.tru.core.error.ResponseUnAuthorizedException
import com.tru.core.state.State
import retrofit2.Response
import java.io.IOException
import java.util.concurrent.CancellationException


abstract class BaseRepository<RequestDto, ResponseDto> {

    suspend fun getOperationState(requestDto: RequestDto): State<ResponseDto> {
        return try {
            performApiCall(requestDto)
        } catch (e: IOException) {
            State.Error(getIoExceptionError(e))
        } catch (e: Exception) {
            if (e is CancellationException) throw e
            State.Error(getGeneralExceptionError(e))
        }
    }

    abstract suspend fun performApiCall(requestDto: RequestDto): State<ResponseDto>

    fun <T> getNotSuccessfulResponseState(response: Response<*>, errorMessage: String? = null): State<T> {
        return when {
            response.code() == 401 -> State.Error(getUnauthorizedError(response, errorMessage = errorMessage))
            response.code() == 400 -> State.Error(getBadRequestError(response, errorMessage = errorMessage))
            else -> State.Error(getNotSuccessfulResponseError(response))
        }
    }

    private fun getIoExceptionError(e: IOException) = AppError.E(
        exception = IoException(cause = e),
        logMessage = "Failed to load data from Api with IOException",
    )

    private fun getGeneralExceptionError(e: Exception) = AppError.E(
        exception = GeneralException(cause = e),
        logMessage = "Failed to load data from Api with General exception",
    )

    private fun getNotSuccessfulResponseError(response: Response<*>) = AppError.E(
        exception = ResponseException(),
        logMessage = "Api request to url: ${response.raw().request.url}: failed with code ${response.code()}",
        extraData = response
    )

    private fun getUnauthorizedError(response: Response<*>, errorMessage: String?) = AppError.E(
        exception = ResponseUnAuthorizedException(),
        logMessage = "Api request to url: ${response.raw().request.url}: failed with code ${response.code()}",
        message = errorMessage,
        extraData = response
    )

    private fun getBadRequestError(response: Response<*>, errorMessage: String?) = AppError.E(
        exception = BadRequestException(),
        logMessage = "Api request to url: ${response.raw().request.url}: failed with code ${response.code()}",
        message = errorMessage,
        extraData = response
    )
}