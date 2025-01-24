package com.onespan.android.interview.features.cats.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountBox
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.onespan.android.interview.R


@Composable
fun CatElement(
    breed: String,
    country: String,
    pattern: String,
    coat: String
) {
    Column {
        ListItem(
            overlineContent = {
                Text(
                    breed,
                    style = MaterialTheme.typography.labelLarge
                )
            },
            headlineContent = {
                Text(
                    stringResource(R.string.pattern_s, pattern),
                    style = MaterialTheme.typography.bodySmall,
                    overflow = TextOverflow.Ellipsis
                )
            },
            supportingContent = {
                Text(
                    stringResource(R.string.country_s, country),
                    style = MaterialTheme.typography.bodySmall,
                    overflow = TextOverflow.Ellipsis
                )
            },
            leadingContent = {
                Icon(
                    Icons.Outlined.AccountBox,
                    contentDescription = "",
                )
            },
            trailingContent = {
                Text(
                    coat,
                    style = MaterialTheme.typography.labelSmall,
                    overflow = TextOverflow.Ellipsis
                )
            }
        )
        HorizontalDivider()
    }
}

@Preview
@Composable
fun NotificationElementPreview() {
    CatElement(
        breed = "Breeded",
        country = "Supporting line text lorem ipsum dolor sit amet, consectetur.",
        pattern = "Colored",
        coat = "coated"
    )
}

