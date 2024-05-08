package com.reinosa.hospitalmar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.reinosa.hospitalmar.View.screens.StudentScreens.StudentDrawerAppScreen
import com.reinosa.hospitalmar.View.screens.TeacherScreens.TeacherDrawerAppScreen
import com.reinosa.hospitalmar.View.screens.GlobalScreens.AboutScreen
import com.reinosa.hospitalmar.View.screens.GlobalScreens.ModuloScreen
import com.reinosa.hospitalmar.View.screens.StudentScreens.CoevalScreen
import com.reinosa.hospitalmar.View.screens.GlobalScreens.ProfileScreen
import com.reinosa.hospitalmar.View.screens.GlobalScreens.SettingsScreen
import com.reinosa.hospitalmar.View.screens.evalScreen
import com.reinosa.hospitalmar.ViewModel.LoginViewModel
import com.reinosa.hospitalmar.ui.theme.HospitalMarTheme
import com.reinosa.hospitalmar.widgets.Informe.Result
import com.reinosa.hospitalmar.widgets.Login.LoginForm


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HospitalMarTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    Navigation(navController)
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    HospitalMarTheme {
        Greeting("Android")
    }
}



@Composable
fun Navigation(navController: NavHostController) {
    val loginViewModel : LoginViewModel = remember { LoginViewModel() }
    val coevalViewModel : LoginViewModel = remember { LoginViewModel() }
    NavHost(
        navController = navController,
            startDestination = NavigationGraph.LOGIN
    ) {

        composable(NavigationGraph.SETTINGS) {
            SettingsScreen(navController)
        }
        composable(NavigationGraph.ABOUT) {
            AboutScreen(navController)
        }
        composable(NavigationGraph.LOGIN) {
            LoginForm(navController, loginViewModel)
        }
        composable(NavigationGraph.DRAWER){
            StudentDrawerAppScreen(navController)
        }
        composable(NavigationGraph.EVALUATE){
            evalScreen(navController, coevalViewModel)
        }
        composable(NavigationGraph.PROFILE){
            ProfileScreen(navController)
        }
        composable(NavigationGraph.MODULO) {
            ModuloScreen(navController)
        }
        composable(NavigationGraph.COEVAL){
            CoevalScreen(navController, coevalViewModel)
        }
        composable(NavigationGraph.RESULT) { backStackEntry ->

            Result(navController)
        }
        composable(NavigationGraph.TEACHER){
            TeacherDrawerAppScreen(navController)
        }

    }
}

object NavigationGraph {
    const val SETTINGS = "settings"
    const val ABOUT = "about"
    const val LOGIN = "login"
    const val DRAWER = "drawer"
    const val EVALUATE = "evaluate"
    const val PROFILE = "profile"
    const val MODULO = "modulo"
    const val COEVAL = "coeval"
    const val RESULT = "result"
    const val TEACHER = "teacher"
}