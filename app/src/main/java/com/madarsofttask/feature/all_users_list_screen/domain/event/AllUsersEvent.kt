package com.madarsofttask.feature.all_users_list_screen.domain.event

import com.tru.core.bases.base_viewmodel.UiEvent

sealed class AllUsersEvent : UiEvent {
    data class ShowError(val message: String) : AllUsersEvent()
}