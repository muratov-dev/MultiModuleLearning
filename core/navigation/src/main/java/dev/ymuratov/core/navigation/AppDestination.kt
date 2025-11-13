package dev.ymuratov.core.navigation

import kotlinx.serialization.Serializable

sealed class AppDestination {

    @Serializable
    object Categories : AppDestination()
}