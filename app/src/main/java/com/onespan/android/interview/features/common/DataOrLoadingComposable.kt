package com.onespan.android.interview.features.common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.onespan.android.interview.base.ui_states.DataOrLoadingState

@Composable
fun DataOrLoadingComposable(
    modifier: Modifier = Modifier,
    uiState: DataOrLoadingState<*>,
    doWhenLoading: (() -> Unit)? = null,
    doWhenLoadingDone: (() -> Unit)? = null,
    isSilentLoading: Boolean = false,
    content: (@Composable () -> Unit)? = null
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(3.dp)
    ) {
        when {
            uiState.isLoading -> {
                if (!isSilentLoading) {
                    LinearProgressIndicator(
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                LaunchedEffect(Unit) {
                    doWhenLoading?.invoke()
                }
            }

            else -> {
                LaunchedEffect(Unit) {
                    doWhenLoadingDone?.invoke()
                }
                content?.invoke()
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DataOrLoadingComposablePreview() {
    DataOrLoadingComposable(
        uiState = DataOrLoadingState<Boolean>(isLoading = true)
    )
}