package com.example.myflexiblefragmentcompose.ui.navigation

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

sealed class Screen : Parcelable {
    @Parcelize
    data object Home : Screen()

    @Parcelize
    data object Category : Screen()

    @Parcelize
    data class DetailCategory(
        val name: String,
        val description: String,
    ) : Screen()
}
