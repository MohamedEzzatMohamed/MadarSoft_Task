package com.madarsofttask.feature.all_users_list_screen.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tru.core.ui_component.failure_view.FailureView
import com.tru.core.ui_component.main_top_bar.MainTopBar
import com.tru.core.ui_component.ui_generic.GeneralLazyColumn
import com.madarsofttask.R
import com.madarsofttask.common.domain.entitty.UserEntity
import com.madarsofttask.feature.all_users_list_screen.presentation.composables.UserItem
import com.madarsofttask.feature.all_users_list_screen.presentation.viewmodel.AllUsersListViewModel

@Composable
fun AllUsersListScreen(
    viewModel: AllUsersListViewModel = hiltViewModel(),
    onBackClicked: () -> Unit
) {

    val uiState = viewModel.uiStateFlow.collectAsStateWithLifecycle().value
    val isUserListEmpty = uiState.userUiModelList.isEmpty()
    val isErrorOrEmpty = uiState.appError != null || uiState.userUiModelList.isEmpty()

    val errorText = if (isUserListEmpty) {
        stringResource(id = R.string.there_is_no_user_here)
    } else {
        stringResource(id = R.string.failed_to_load_content)
    }

    MainTopBar(
        title = R.string.users,
        isPullRefresh = false,
        leftIcon = R.drawable.ic_vector_black_back_arrow,
        onLeftIconClicked = onBackClicked,
        content = {
            when {
                isErrorOrEmpty -> FailureView(
                    tapText = R.string.refresh,
                    errText = errorText,
                    icon = R.drawable.ic_vector_error,
                    onTapToRefresh = viewModel::refresh
                )

                else -> UsersListContent(
                    userUiModelList = uiState.userUiModelList,
                )
            }
        })




}

@Composable
fun UsersListContent(
    userUiModelList: List<UserEntity>,
) {
    GeneralLazyColumn(
        modifier = Modifier.padding(all = 8.dp),
        list = userUiModelList
    ) { user ->
        UserItem(
            name = user.name ?: "-",
            age = user.age.toString(),
            jobTitle = user.jobTitle ?: "-",
            genderType = user.genderType ?: "-",
        )
    }
}