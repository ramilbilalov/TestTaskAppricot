package com.bilalov.testapplicationforappricot.navigation

sealed class Screen(val screenName: String) {

    object Main: Screen("main")

    object SecondView: Screen("second")

}