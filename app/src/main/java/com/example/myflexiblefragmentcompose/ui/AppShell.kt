package com.example.myflexiblefragmentcompose.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myflexiblefragmentcompose.R
import com.example.myflexiblefragmentcompose.ui.components.CustomTopAppBar
import com.example.myflexiblefragmentcompose.ui.navigation.Screen
import com.example.myflexiblefragmentcompose.ui.screens.CategoryScreen
import com.example.myflexiblefragmentcompose.ui.screens.DetailCategoryScreen
import com.example.myflexiblefragmentcompose.ui.screens.HomeScreen
import com.example.myflexiblefragmentcompose.ui.theme.MyFlexibleFragmentComposeTheme
import kotlinx.coroutines.launch

@Composable
fun AppShell(
    onNavigateProfile: () -> Unit,
    modifier: Modifier = Modifier,
) {
    var backStack by rememberSaveable { mutableStateOf(listOf<Screen>(Screen.Home)) }
    val currentScreen = backStack.lastOrNull() ?: Screen.Home
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    val title = when (currentScreen) {
        is Screen.Home -> stringResource(R.string.app_name)
        is Screen.Category -> stringResource(R.string.title_category)
        is Screen.DetailCategory -> currentScreen.name
    }

    Scaffold(
        topBar = {
            CustomTopAppBar(
                title = title,
                canBack = backStack.size > 1,
                onBack = {
                    if (backStack.size > 1) {
                        backStack = backStack.dropLast(1)
                    }
                },
            )
        },
        snackbarHost = {
            SnackbarHost(
                hostState = snackbarHostState,
                modifier = Modifier.padding(16.dp),
            ) { data ->
                Snackbar(
                    snackbarData = data,
                    containerColor = MaterialTheme.colorScheme.inverseSurface,
                    contentColor = MaterialTheme.colorScheme.inverseOnSurface,
                    shape = MaterialTheme.shapes.medium,
                )
            }
        },
        modifier = modifier.fillMaxSize(),
    ) { innerPadding ->
        ScreenContent(
            screen = currentScreen,
            onNavigateCategory = {
                backStack += Screen.Category
            },
            onNavigateDetail = { name, description ->
                backStack += Screen.DetailCategory(name, description)
            },
            onNavigateProfile = onNavigateProfile,
            onShowMessage = { msg ->
                scope.launch {
                    snackbarHostState.currentSnackbarData?.dismiss()
                    snackbarHostState.showSnackbar(msg)
                }
            },
            modifier = Modifier.padding(innerPadding),
        )
    }
}

@Composable
private fun ScreenContent(
    screen: Screen,
    onNavigateCategory: () -> Unit,
    onNavigateDetail: (String, String) -> Unit,
    onNavigateProfile: () -> Unit,
    onShowMessage: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    when (screen) {
        is Screen.Home -> HomeScreen(
            onNavigateCategory = onNavigateCategory,
            modifier = modifier,
        )

        is Screen.Category -> CategoryScreen(
            onNavigateDetail = onNavigateDetail,
            modifier = modifier,
        )

        is Screen.DetailCategory -> DetailCategoryScreen(
            name = screen.name,
            description = screen.description,
            onNavigateProfile = onNavigateProfile,
            onShowMessage = onShowMessage,
            modifier = modifier,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun AppShellPreview() {
    MyFlexibleFragmentComposeTheme {
        AppShell(onNavigateProfile = {})
    }
}
