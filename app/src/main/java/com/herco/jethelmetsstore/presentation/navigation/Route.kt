package com.herco.jethelmetsstore.presentation.navigation

object HomeRoute {
    const val route = "home"
}

object HelmetDetailRoute {
    const val route = "message/{id}"
    const val argument = "id"
}

fun params(route: String, argument: String): String {
    return route.substring(0, route.indexOf('/') + 1).plus(argument)
}