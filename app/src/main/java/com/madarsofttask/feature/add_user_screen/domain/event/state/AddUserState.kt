package com.madarsofttask.feature.add_user_screen.domain.event.state

import com.tru.core.ui_component.custom_text_failed.ui_text.UiText
import com.tru.core.bases.base_viewmodel.ViewState
import com.tru.core.error.AppError

data class AddUserState(

    val name: String = "",
    val errorName: UiText? = null,
    val age: String = "",
    val errorAge: UiText? = null,
    val jobTitle: String = "",
    val errorJobTitle: UiText? = null,
    val gender: String = "",
    val errorGender: UiText? = null,

    val isLoading: Boolean = false,
    val appError: AppError? = null,
    val isAddedSuccess: Boolean = false

) : ViewState