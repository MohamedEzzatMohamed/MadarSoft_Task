package com.madarsofttask.feature.add_user_screen.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.core.ui_component.custom_text_failed.ui_text.UiText
import com.core.ui_component.main_top_bar.MainTopBar
import com.madarsofttask.R
import com.madarsofttask.feature.add_user_screen.domain.event.state.AddUserState
import com.madarsofttask.feature.add_user_screen.domain.model.Gender
import com.madarsofttask.feature.add_user_screen.presentation.composables.NextButton
import com.madarsofttask.feature.add_user_screen.presentation.composables.RadioButton
import com.madarsofttask.feature.add_user_screen.presentation.composables.SubmitButton
import com.madarsofttask.feature.add_user_screen.presentation.viewmodel.AddUserViewModel
import com.madarsofttask.ui.theme.Black
import com.madarsofttask.ui.theme.DarkBlue
import com.madarsofttask.ui.theme.White
import com.tru.core.ui_component.custom_text_failed.CustomTextField

@Composable
fun AddUserScreen(
    viewModel: AddUserViewModel = hiltViewModel(),
    onNavigateToAllUsers: () -> Unit,
) {

    val uiState = viewModel.uiStateFlow.collectAsStateWithLifecycle().value

    MainTopBar(
        title = R.string.addNewUser,
        isRefreshing = uiState.isLoading,
        content = {

            Column(modifier = Modifier.fillMaxSize()) {
                Box(modifier = Modifier.weight(1f)) {
                    AddUserContent(
                        state = uiState,
                        onValidateUserNameIntent = viewModel::sendValidateUserNameIntent,
                        onValidateUserAgeIntent = viewModel::sendValidateUserAgeIntent,
                        onValidateUserGenderIntent = viewModel::sendValidateUserGenderIntent,
                        onValidateUserJobTitleIntent = viewModel::sendValidateUserJobTitleIntent,
                        onAddUserBtnClicked = { viewModel.sendAddUserIntent() })
                }

                NextButton(
                    modifier = Modifier.fillMaxWidth().padding(16.dp),

                    content = {
                        Text(
                            text = stringResource(id = R.string.showAllUsers),
                            style = MaterialTheme.typography.bodyLarge.copy(color = White),
                            fontSize = 16.sp,
                        )
                    }, onClick = onNavigateToAllUsers
                )
            }



        })

    LaunchedEffect(key1 = uiState) {
        if (uiState.isAddedSuccess) {
            onNavigateToAllUsers()
            viewModel.resetAddUserState()
        }
    }

}

@Composable
fun AddUserContent(
    modifier: Modifier = Modifier,
    state: AddUserState,
    onValidateUserNameIntent: (String) -> Unit,
    onValidateUserAgeIntent: (String) -> Unit,
    onValidateUserGenderIntent: (String) -> Unit,
    onValidateUserJobTitleIntent: (String) -> Unit,
    onAddUserBtnClicked: () -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .verticalScroll(state = rememberScrollState())
            .padding(all = 16.dp),
    ) {

        Text(
            text = stringResource(id = R.string.pleaseEnterUserInfo),
            style = MaterialTheme.typography.bodyLarge.copy(color = Black),
            fontSize = 16.sp,
        )

        Spacer(modifier = modifier.height(8.dp))

        NameTextFailed(
            nameValue = state.name,
            onValidateUserNameIntent = onValidateUserNameIntent,
            nameMessage = state.errorName,
        )

        Spacer(modifier = modifier.height(8.dp))

        AgeTextFailed(
            ageTextValue = state.age,
            onValidateUserAgeIntent = onValidateUserAgeIntent,
            ageMessage = state.errorAge,
        )

        Spacer(modifier = modifier.height(8.dp))

        JobTitleTextFailed(
            jobTitleTextValue = state.jobTitle,
            onValidateUserJobTitleIntent = onValidateUserJobTitleIntent,
            jobTitleMessage = state.errorJobTitle
        )

        Spacer(modifier = modifier.height(12.dp))

        GenderTypeSelector(
            gender = state.gender,
            onSelectGender = onValidateUserGenderIntent,
            genderMessage = state.errorGender
        )

        Spacer(modifier = modifier.height(30.dp))

        SubmitButton(
            modifier = modifier.fillMaxWidth(),
            content = {
                Text(
                    style = MaterialTheme.typography.bodyLarge.copy(color = White),
                    text = stringResource(id = R.string.addNewUser),
                    fontSize = 16.sp,
                )
            }, onClick = onAddUserBtnClicked
        )


    }
}

@Composable
fun NameTextFailed(
    nameValue: String,
    onValidateUserNameIntent: (String) -> Unit,
    nameMessage: UiText?,
) {
    CustomTextField(
        placeholder = stringResource(id = R.string.nameRequired),
        text = nameValue,
        textStyle = MaterialTheme.typography.bodyLarge.copy(color = DarkBlue),
        placeHolderTextStyle = MaterialTheme.typography.bodyLarge.copy(color = Color.Gray),
        keyboardType = KeyboardType.Text,
        imeAction = ImeAction.Next,
        modifier = Modifier.fillMaxWidth(),
        onValueChange = onValidateUserNameIntent,
        singleLine = true,
        isError = nameMessage != null,
        errorMessage = nameMessage,
    )
}


@Composable
fun AgeTextFailed(
    ageTextValue: String,
    onValidateUserAgeIntent: (String) -> Unit,
    ageMessage: UiText?
) {
    CustomTextField(
        placeholder = stringResource(id = R.string.ageRequired),
        text = ageTextValue,
        textStyle = MaterialTheme.typography.bodyLarge.copy(color = DarkBlue),
        placeHolderTextStyle = MaterialTheme.typography.bodyLarge.copy(color = Color.Gray),
        keyboardType = KeyboardType.Number,
        imeAction = ImeAction.Next,
        modifier = Modifier.fillMaxWidth(),
        onValueChange = onValidateUserAgeIntent,
        singleLine = true,
        isError = ageMessage != null,
        errorMessage = ageMessage
    )
}


@Composable
fun JobTitleTextFailed(
    jobTitleTextValue: String,
    onValidateUserJobTitleIntent: (String) -> Unit,
    jobTitleMessage: UiText?
) {
    CustomTextField(
        placeholder = stringResource(id = R.string.jobTitleRequired),
        text = jobTitleTextValue,
        textStyle = MaterialTheme.typography.bodyLarge.copy(color = DarkBlue),
        placeHolderTextStyle = MaterialTheme.typography.bodyLarge.copy(color = Color.Gray),
        keyboardType = KeyboardType.Text,
        imeAction = ImeAction.Done,
        modifier = Modifier.fillMaxWidth(),
        onValueChange = onValidateUserJobTitleIntent,
        singleLine = true,
        isError = jobTitleMessage != null,
        errorMessage = jobTitleMessage,
    )
}

@Composable
fun GenderTypeSelector(
    gender: String?,
    onSelectGender: (String) -> Unit,
    genderMessage: UiText?
) {

    Column {
        Text(
            text = stringResource(id = R.string.selectGender),
            fontSize = 16.sp,
            style = MaterialTheme.typography.titleMedium.copy(color = Black),
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(60.dp)
        ) {
            RadioButton(Gender.MALE.value, gender) {
                onSelectGender(it)
            }

            RadioButton(Gender.FEMALE.value, gender) {
                onSelectGender(it)
            }
        }
        genderMessage?.let {
            Text(
                text = stringResource(id = R.string.selectGender),
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall,
            )
        }
    }
}

