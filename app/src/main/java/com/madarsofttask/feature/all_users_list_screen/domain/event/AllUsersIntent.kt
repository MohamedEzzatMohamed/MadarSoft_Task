package com.madarsofttask.feature.all_users_list_screen.domain.event

import com.tru.core.bases.base_viewmodel.ViewIntent

sealed class AllUsersIntent : ViewIntent {
    data object GetAllUsersIntent : AllUsersIntent()
}