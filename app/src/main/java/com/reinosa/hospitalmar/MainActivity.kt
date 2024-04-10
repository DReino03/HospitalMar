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
import com.reinosa.hospitalmar.View.DrawerAppScreen
import com.reinosa.hospitalmar.View.screens.AboutScreen
import com.reinosa.hospitalmar.View.screens.EvalScreen
import com.reinosa.hospitalmar.View.screens.HomeScreen
import com.reinosa.hospitalmar.View.screens.SettingsScreen
import com.reinosa.hospitalmar.ViewModel.LoginViewModel
import com.reinosa.hospitalmar.ui.theme.HospitalMarTheme
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
    NavHost(
        navController = navController,
        startDestination = NavigationGraph.LOGIN
    ) {
        composable(NavigationGraph.HOME) {
            HomeScreen(navController)
        }
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
            DrawerAppScreen(navController)
        }
        composable(NavigationGraph.EVALUATE){
            EvalScreen(navController)
        }
    }
}

object NavigationGraph {
    const val HOME = "home"
    const val SETTINGS = "settings"
    const val ABOUT = "about"
    const val LOGIN = "login"
    const val DRAWER = "drawer"
    const val EVALUATE = "evaluate"
}