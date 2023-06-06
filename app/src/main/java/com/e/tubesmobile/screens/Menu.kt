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
    PENGELOLAAN_PERIFERAL(R.string.pengelolaan_periferal, R.drawable.baseline_mouse_24, "pengelolaan-periferal"),
    PENGELOLAAN_SMARTHPHONE(R.string.pengelolaan_smarthphone, R.drawable.baseline_phone_android_24, "pengelolaan-smarthphone"),
    SETTING(R.string.setting, R.drawable.baseline_settings_24, "setting");
    companion object {
        fun getTabFromResource(@StringRes resource: Int) : Menu
        {
            return when (resource) {
                R.string.home -> HOME
                R.string.pengelolaan_komputer -> PENGELOLAAN_KOMPUTER
                R.string.pengelolaan_periferal -> PENGELOLAAN_PERIFERAL
                R.string.pengelolaan_smarthphone -> PENGELOLAAN_SMARTHPHONE
                else -> SETTING
            }
        }
    }
}