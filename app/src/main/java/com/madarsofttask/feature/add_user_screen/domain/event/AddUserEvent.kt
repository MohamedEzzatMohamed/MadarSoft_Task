package com.madarsofttask.feature.add_user_screen.domain.event

import com.tru.core.bases.base_viewmodel.UiEvent

sealed class AddUserEvent : UiEvent {
    object NavigateToList : AddUserEvent()
}