package com.core.ui_component.main_top_bar


import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.pulltorefresh.pullToRefresh
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.dropUnlessResumed
import com.core.ui_component.pull_to_refresh_indicator.PullToRefreshIndicator
import com.core.ui_component.app_top_bar.AppTopAppBar
import com.core.ui_component.icon_wrapper.IconWrapper


@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun MainTopBar(
    isRefreshing: Boolean = false,
    isPullRefresh: Boolean = true,
    @StringRes title: Int,
    titleText: String? = null,
    @DrawableRes leftIcon: Int? = null,
    @DrawableRes rightIcon: Int? = null,
    contentAlignment: Alignment = Alignment.TopStart,
    onRefresh: () -> Unit = {},
    onRightIconClicked: () -> Unit = {},
    onLeftIconClicked: () -> Unit = {},
    content: @Composable () -> Unit,
) {

    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    val refreshState = rememberPullToRefreshState()


    Scaffold(
        modifier = Modifier
            .nestedScroll(scrollBehavior.nestedScrollConnection)
            .clearFocusOnTouch(),
        topBar = {
            AppTopAppBar(
                title = if (titleText.isNullOrEmpty()) {
                    stringResource(id = title)
                } else {
                    titleText
                }, navigationIcon = {
                    leftIcon?.let { leftIcon ->
                        IconButton(onClick = { onLeftIconClicked() }) {
                            IconWrapper(size = 24.dp) {
                                Icon(
                                    painter = painterResource(id = leftIcon),
                                    contentDescription = "Menu icon",
                                )
                            }
                        }
                    }
                }, actions = {
                    rightIcon?.let { rightIcon ->
                        IconButton(onClick = dropUnlessResumed(block = onRightIconClicked)) {
                            IconWrapper(size = 24.dp) {
                                Icon(
                                    painter = painterResource(id = rightIcon),
                                    contentDescription = "Profile",
                                    modifier = Modifier.size(20.dp)
                                )
                            }
                        }
                    }
                }, scrollBehavior = scrollBehavior
            )
        },
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .pullToRefresh(
                    state = refreshState, isRefreshing = isRefreshing, onRefresh = onRefresh
                ), contentAlignment = contentAlignment
        ) {

            content()

            if (isPullRefresh)
                PullToRefreshIndicator(
                    modifier = Modifier.align(Alignment.TopCenter),
                    isRefreshing = isRefreshing,
                    state = refreshState
                )
        }
    }
}


@Composable
fun Modifier.clearFocusOnTouch(): Modifier {
    val localFocusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current
    return this then Modifier.pointerInput(Unit) {
        detectTapGestures {
            keyboardController?.hide()
            localFocusManager.clearFocus(force = true)
        }
    }
}
