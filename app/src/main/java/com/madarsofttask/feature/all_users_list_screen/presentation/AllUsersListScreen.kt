package com.madarsofttask.feature.all_users_list_screen.presentation

import android.widget.Toast
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tru.core.ui_component.failure_view.FailureView
import com.tru.core.ui_component.main_top_bar.MainTopBar
import com.tru.core.ui_component.ui_generic.GeneralLazyColumn
import com.madarsofttask.R
import com.madarsofttask.common.domain.entitty.UserEntity
import com.madarsofttask.feature.all_users_list_screen.domain.event.AllUsersEvent
import com.madarsofttask.feature.all_users_list_screen.presentation.composables.UserItem
import com.madarsofttask.feature.all_users_list_screen.presentation.viewmodel.AllUsersListViewModel
import com.madarsofttask.ui.theme.DarkBlue
import com.tru.core.ui_component.loading.LoadingView

@Composable
fun AllUsersListScreen(
    viewModel: AllUsersListViewModel = hiltViewModel(), onBackClicked: () -> Unit
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is AllUsersEvent.ShowError -> {
                    Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    MainTopBar(
        title = R.string.users,
        isRefreshing = uiState.isLoading,
        leftIcon = R.drawable.ic_vector_black_back_arrow,
        onLeftIconClicked = onBackClicked,
        content = {
            when {
                uiState.isLoading && uiState.userUiModelList.isEmpty() -> {
                    LoadingView(color = DarkBlue)
                }

                uiState.appError != null -> {
                    FailureView(
                        tapText = R.string.refresh,
                        errText = stringResource(id = R.string.failed_to_load_content),
                        icon = R.drawable.ic_vector_error,
                        onTapToRefresh = viewModel::refresh
                    )
                }

                uiState.userUiModelList.isEmpty() -> {
                    FailureView(
                        tapText = R.string.addNewUser,
                        errText = stringResource(id = R.string.there_is_no_user_here),
                        image = R.drawable.ic_vector_empty_list,
                        onTapToRefresh = onBackClicked
                    )
                }

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
        modifier = Modifier.padding(all = 8.dp), list = userUiModelList
    ) { user ->
        UserItem(
            name = user.name ?: "-",
            age = user.age.toString(),
            jobTitle = user.jobTitle ?: "-",
            genderType = user.genderType ?: "-",
        )
    }
}