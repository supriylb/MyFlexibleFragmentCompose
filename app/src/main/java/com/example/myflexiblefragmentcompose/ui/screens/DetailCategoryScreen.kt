package com.example.myflexiblefragmentcompose.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myflexiblefragmentcompose.R
import com.example.myflexiblefragmentcompose.ui.components.CustomButton
import com.example.myflexiblefragmentcompose.ui.components.OptionDialog
import com.example.myflexiblefragmentcompose.ui.theme.MyFlexibleFragmentComposeTheme

@Composable
fun DetailCategoryScreen(
    name: String,
    description: String,
    onNavigateProfile: () -> Unit,
    onShowMessage: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    var showDialog by rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant,
            ),
            shape = MaterialTheme.shapes.large,
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                Text(
                    text = name,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )

                Text(
                    text = description,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
        }

        CustomButton(
            text = stringResource(R.string.btn_go_profile),
            onClick = onNavigateProfile,
        )

        CustomButton(
            text = stringResource(R.string.btn_show_dialog),
            onClick = { showDialog = true },
        )
    }

    if (showDialog) {
        OptionDialog(
            onDismiss = { showDialog = false },
            onSelect = { coach ->
                onShowMessage(coach)
                showDialog = false
            },
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun DetailCategoryScreenPreview() {
    MyFlexibleFragmentComposeTheme {
        DetailCategoryScreen(
            name = "Lifestyle",
            description = "This category contains lifestyle products.",
            onNavigateProfile = {},
            onShowMessage = {},
        )
    }
}
