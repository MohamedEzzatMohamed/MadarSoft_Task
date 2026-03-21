package com.madarsofttask.feature.all_users_list_screen.presentation.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.madarsofttask.R
import com.madarsofttask.ui.theme.DarkBlue
import com.madarsofttask.ui.theme.OffWhite

@Composable
fun UserItem(
    modifier: Modifier = Modifier,
    name: String,
    age: String,
    jobTitle: String,
    genderType: String,
) {
    Surface(
        color = OffWhite,
        shape = RoundedCornerShape(16.dp)
    ) {

        Column(
            modifier = modifier
                .padding(all = 16.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Row(modifier = modifier.fillMaxWidth()) {
                Text(
                    text = stringResource(id = R.string.name),
                    fontSize = 16.sp,
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.bodyMedium.copy(color = DarkBlue),
                )

                Spacer(modifier = modifier.width(8.dp))

                Text(
                    text = name,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.bodyMedium.copy(color = DarkBlue),
                )
            }


            Spacer(modifier = modifier.height(8.dp))

            Row(modifier = modifier.fillMaxWidth()) {
                Text(
                    text = stringResource(id = R.string.age),
                    fontSize = 16.sp,
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.bodyMedium.copy(color = DarkBlue),
                )

                Spacer(modifier = modifier.width(8.dp))

                Text(
                    text = stringResource(id = R.string.noYears, age),
                    fontSize = 16.sp,
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.bodyMedium.copy(color = DarkBlue),
                )
            }


            Spacer(modifier = modifier.height(8.dp))


            Row(modifier = modifier.fillMaxWidth()) {
                Text(
                    text = stringResource(id = R.string.jobTitle),
                    fontSize = 16.sp,
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.bodyMedium.copy(color = DarkBlue),
                )

                Spacer(modifier = modifier.width(8.dp))

                Text(
                    text = jobTitle,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.bodyMedium.copy(color = DarkBlue),
                )
            }

            Spacer(modifier = modifier.height(8.dp))

            Row(modifier = modifier.fillMaxWidth()) {
                Text(
                    text = stringResource(id = R.string.gender),
                    fontSize = 16.sp,
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.bodyMedium.copy(color = DarkBlue),
                )

                Spacer(modifier = modifier.width(8.dp))

                Text(
                    text = genderType,
                    fontSize = 16.sp,
                    textAlign = TextAlign.Start,
                    style = MaterialTheme.typography.bodyMedium.copy(color = DarkBlue),
                )
            }

        }
    }
}