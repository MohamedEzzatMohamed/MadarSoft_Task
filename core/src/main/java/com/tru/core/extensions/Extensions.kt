package com.tru.core.extensions


import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tru.core.error.AppError
import com.tru.core.state.State
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import java.util.Locale


fun Context.openUrl(uri: Uri?) {
    val extension = uri.toString().substringAfterLast('.', "")
    val mimeType = getMimeTypeFromExtension(extension)
    val intent = Intent(Intent.ACTION_VIEW)
    intent.setDataAndType(uri, mimeType)
    intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
    try {
        this.startActivity(intent)
    } catch (e: ActivityNotFoundException) {
        AppError.E(exception = e, "Open file with action view error")
    }
}

private fun getMimeTypeFromExtension(extension: String): String {
    return when (extension.lowercase(Locale.getDefault())) {
        "jpg", "jpeg", "png" -> "image/*"
        "pdf" -> "application/pdf"
        "docx", "doc" -> "application/*"
        else -> "*/*"
    }
}


fun ViewModel.viewModelScope(block: suspend () -> Unit) = this.viewModelScope.launch {
    block()
}


suspend inline fun <T> Flow<State<T>>.collectOnFlowState(
    crossinline onLoading: () -> Unit = {},
    crossinline onError: (AppError) -> Unit,
    crossinline onSuccess: suspend (Any) -> Unit,
) = collect {
    onLoading()
    when (it) {
        is State.Error -> onError(it.error)
        is State.Loading -> {}
        is State.Success -> onSuccess(it.data ?: return@collect)
    }
}