package com.tru.core.extensions

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tru.core.error.AppError
import com.tru.core.state.State
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

fun Exception.getValidationError() = AppError.I(exception = this)


fun Context.showToast(message: Int, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, duration).show()
}


fun Context.restartActivity(activity: Activity) {
    val intent = Intent(this, activity::class.java)
    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
    startActivity(intent)
}


fun ViewModel.viewModelScope(
    context: CoroutineDispatcher = Dispatchers.Main, block: suspend () -> Unit
) = this.viewModelScope.launch(context = context) { block() }


suspend inline fun <T> Flow<State<T>>.collectOnFlowState(
    crossinline onLoading: () -> Unit = {},
    crossinline onError: (AppError) -> Unit,
    crossinline onSuccess: suspend (T) -> Unit,
) = collect {
    onLoading()
    when (it) {
        is State.Loading -> {}
        is State.Success -> onSuccess(it.data ?: return@collect)
        is State.Error -> onError(it.error)
    }
}
