package com.tru.core.ui_component.paging_items_content

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import kotlin.collections.get

@Composable
fun <T : Any> PagingItemsContent(
    pagingItems: LazyPagingItems<T>,
    item: @Composable LazyItemScope.(T) -> Unit,
    onFirstLoadError: @Composable () -> Unit = {},
    onFirstLoadLoading: @Composable () -> Unit = {},
    onLoadMoreError: @Composable () -> Unit = {},
    onLoadMoreLoading: @Composable () -> Unit = {},
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        items(
            count = pagingItems.itemCount,
            key = { index -> pagingItems[index]?.hashCode() ?: index }
        ) { index ->
            pagingItems[index]?.let { item(it) }
        }

        pagingItems.loadToFirstItem(
            onError = { item { onFirstLoadError() } },
            onLoading = { item { onFirstLoadLoading() } }
        )

        pagingItems.loadToMoreItem(
            onError = { item { onLoadMoreError() } },
            onLoading = { item { onLoadMoreLoading() } }
        )
    }
}

private fun <T : Any> LazyPagingItems<T>.loadToFirstItem(onError: () -> Unit, onLoading: () -> Unit) =
    when (this.loadState.refresh) {
        is LoadState.Error -> onError()

        is LoadState.Loading -> onLoading()
        is LoadState.NotLoading -> {}
    }


private fun <T : Any> LazyPagingItems<T>.loadToMoreItem(onError: () -> Unit, onLoading: () -> Unit) =
    when (this.loadState.append) {
        is LoadState.Error -> onError()

        is LoadState.Loading -> onLoading()
        is LoadState.NotLoading -> {}
    }