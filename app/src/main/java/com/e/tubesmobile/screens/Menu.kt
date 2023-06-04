package com.e.tubesmobile.screens

import androidx.annotation.StringRes
import com.e.tubesmobile.R

enum class Menu(
    @StringRes val title: Int,
    val icon: Int,
    val route: String
) {
    HOME(R.string.home, R.drawable.baseline_home_24, "home"),
    PENGELOLAAN_KOMPUTER(R.string.pengelolaan_komputer, R.drawable.baseline_computer_24, "pengelolaan-komputer"),
    SETTING(R.string.setting, R.drawable.baseline_settings_24, "setting");
    companion object {
        fun getTabFromResource(@StringRes resource: Int) : Menu
        {
            return when (resource) {
                R.string.home -> HOME
                R.string.pengelolaan_komputer ->
                    PENGELOLAAN_KOMPUTER
                else -> SETTING
            }
        }
    }
}