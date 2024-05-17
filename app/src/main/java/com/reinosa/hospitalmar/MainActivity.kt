package com.reinosa.hospitalmar

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
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
import com.reinosa.hospitalmar.View.screens.GlobalScreens.AboutScreen
import com.reinosa.hospitalmar.View.screens.GlobalScreens.ProfileScreen
import com.reinosa.hospitalmar.View.screens.GlobalScreens.SettingsScreen
import com.reinosa.hospitalmar.ViewModel.ViewModel
import com.reinosa.hospitalmar.ui.theme.HospitalMarTheme
import com.reinosa.hospitalmar.widgets.Competencias.CompetenciasContainer
import com.reinosa.hospitalmar.widgets.Evaluacio.EvalContainer
import com.reinosa.hospitalmar.widgets.Global.Profile.SplashScreen
import com.reinosa.hospitalmar.widgets.Home.HomeContainer
import com.reinosa.hospitalmar.widgets.Informe.InformeContainer
import com.reinosa.hospitalmar.widgets.InformeFuera.InformeFueraContainer
import com.reinosa.hospitalmar.widgets.Login.LoginForm
import com.reinosa.hospitalmar.widgets.Modulos.ModulContainer
import com.reinosa.hospitalmar.widgets.StudentList.StudentContainer


class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
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

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Navigation(navController: NavHostController) {
    val loginViewModel : ViewModel = remember { ViewModel() }
//    val coevalViewModel : LoginViewModel = remember { LoginViewModel() }
    NavHost(
        navController = navController,
        startDestination = NavigationGraph.SPLASH
    ) {
        // Pantalla de settings
        composable(NavigationGraph.SETTINGS) {
            SettingsScreen(navController, loginViewModel)
        }
        // Pantalla de about
        composable(NavigationGraph.ABOUT) {
            AboutScreen(navController, loginViewModel)
        }
        // Pantalla de login
        composable(NavigationGraph.LOGIN) {
            LoginForm(navController, loginViewModel)
        }
        // Pantalla principal
        composable(NavigationGraph.HOME){
            HomeContainer(navController, loginViewModel)
        }
        // Pantalla de evaluacion para profesor
        composable(NavigationGraph.EVALUATE){
            EvalContainer(navController, loginViewModel)
        }
        // Pantalla de perfil
        composable(NavigationGraph.PROFILE){
            ProfileScreen(navController, loginViewModel)
        }
        // Lista de modulos
        composable(NavigationGraph.MODULO) {
            ModulContainer(navController, loginViewModel)
        }

        // Pantalla de resultados o informes
        composable(NavigationGraph.INFORME) {
            InformeContainer(navController, loginViewModel)
        }

        // Pantalla splashscreen
        composable(NavigationGraph.SPLASH){
            SplashScreen(navController)
        }
        //Lista alumnos
        composable(NavigationGraph.STUDENT){
            StudentContainer(navController, loginViewModel)
        }
        //Competencias
        composable(NavigationGraph.COMPETENCIAS){
            CompetenciasContainer(navController = navController, viewModel = loginViewModel)
        }
        //Competencias
        composable(NavigationGraph.INFORMEFUERA){
            InformeFueraContainer(navController, loginViewModel)
        }
    }
}

object NavigationGraph {
    const val SETTINGS = "settings"
    const val ABOUT = "about"
    const val LOGIN = "login"
    const val HOME = "home"
    const val EVALUATE = "evaluate"
    const val PROFILE = "profile"
    const val MODULO = "modulo"
    const val STUDENT = "student"
    const val INFORMEFUERA = "informeFuera"
    const val INFORME = "informe"
    const val SPLASH = "splash"
    const val COMPETENCIAS = "competencias"

}