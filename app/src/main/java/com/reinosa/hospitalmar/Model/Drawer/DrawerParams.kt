package com.reinosa.hospitalmar.Model.Drawer

import com.reinosa.hospitalmar.Model.Drawer.AppDrawerItemInfo
import com.reinosa.hospitalmar.Nav.MainNavOption
import com.reinosa.hospitalmar.R

object DrawerParams {
    val drawerButtons = arrayListOf<AppDrawerItemInfo<MainNavOption>>(
        AppDrawerItemInfo(
            MainNavOption.HomeScreen,
            R.string.drawer_home,
            R.drawable.ic_home,
            R.string.drawer_home_description,
            "home"
        ),
        AppDrawerItemInfo(
            MainNavOption.ProfileScreen,
            R.string.drawer_profile,
            R.drawable.ic_profile,
            R.string.drawer_profile_description,
            "profile"
        ),
        AppDrawerItemInfo(
            MainNavOption.EvaluateScreen,
            R.string.drawer_graphics,
            R.drawable.ic_graphics,
            R.string.drawer_graphics_description,
            "evaluate"
        ),

        AppDrawerItemInfo(
            MainNavOption.SettingsScreen,
            R.string.drawer_settings,
            R.drawable.ic_settings,
            R.string.drawer_settings_description,
            "settings"
        ),
        AppDrawerItemInfo(
            MainNavOption.AboutScreen,
            R.string.drawer_about,
            R.drawable.ic_info,
            R.string.drawer_info_description,
            "about"   
        ),
        AppDrawerItemInfo(
            MainNavOption.LoginScreen,
            R.string.exit_app,
            R.drawable.ic_exit,
            R.string.exit_app_description,
            "Salir"
        ),
    )
}