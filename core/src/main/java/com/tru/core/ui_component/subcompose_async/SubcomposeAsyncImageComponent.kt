package com.core.ui_component.subcompose_async

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest


@Composable
fun SubcomposeAsyncImageComponent(
    modifier: Modifier = Modifier,
    imageUrl: Any?,
    @DrawableRes errorPlaceholder: Int,
    contentScale: ContentScale = ContentScale.Fit,
    circularProgressPadding: Dp = 5.dp,
    circularProgressColor: Color = MaterialTheme.colorScheme.secondary,
) {

    val imageRequest = ImageRequest.Builder(LocalContext.current)
        .data(imageUrl)
        .crossfade(true)
        .build()

    SubcomposeAsyncImage(
        model = imageRequest,
        contentDescription = "Image Resource",
        contentScale = contentScale,
        modifier = modifier,
        loading = {
            CircularProgressIndicator(
                color = circularProgressColor,
                modifier = modifier.padding(circularProgressPadding),
            )
        },
        error = {
            Image(
                painter = painterResource(errorPlaceholder),
                contentDescription = "Loaded image",
                modifier = modifier.fillMaxSize()
            )
        }
    )
}