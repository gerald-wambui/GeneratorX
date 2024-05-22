package com.jaguh.someapp.navigation

interface Destinations {
    val route: String
}

object Home : Destinations {
    override val route: String
        get() = "Home"
}

object Step2 : Destinations {
    override val route: String
        get() = "Step2"
}

object Step3 : Destinations {
    override val route: String
        get() = "Step3"
}

object AI : Destinations {
    override val route: String
        get() = "AI"
}