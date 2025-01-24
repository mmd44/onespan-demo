package com.onespan.android.interview

import android.content.res.Resources
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.onespan.android.interview.base.snackbar.SnackbarManager
import com.onespan.android.interview.features.cats.ui.CatsScreen
import com.onespan.android.interview.ui.theme.AndroidIntermediateInterviewTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidIntermediateInterviewTheme {
                ScreenContent()
            }
        }
    }
}

@Composable
fun ScreenContent() {
    val appState = rememberAppState()

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = appState.snackbarHostState) },
        content = { paddingValues ->
            CatsScreen(modifier = Modifier.padding(paddingValues))
        })
}

@Composable
fun rememberAppState(
    snackbarHostState: SnackbarHostState = remember { SnackbarHostState() },
    navController: NavHostController = rememberNavController(),
    snackbarManager: SnackbarManager = SnackbarManager,
    resources: Resources = resources(),
    coroutineScope: CoroutineScope = rememberCoroutineScope()
) = remember(snackbarHostState, navController, snackbarManager, resources, coroutineScope) {
    AppState(snackbarHostState, navController, snackbarManager, resources, coroutineScope)
}


@Composable
@ReadOnlyComposable
fun resources(): Resources {
    LocalConfiguration.current
    return LocalContext.current.resources
}

/*@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar() {
    TopAppBar(title = { Text(stringResource(R.string.app_name)) })
}

@Composable
fun CatBreeds(modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(20) {
            Text(text = "Cat Breed ${it + 1}")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ScreenContentPreview() {
    AndroidIntermediateInterviewTheme {
        ScreenContent()
    }
}*/