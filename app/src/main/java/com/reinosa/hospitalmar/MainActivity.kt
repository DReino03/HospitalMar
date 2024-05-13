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
import com.reinosa.hospitalmar.View.screens.GlobalScreens.ProfileScreen
import com.reinosa.hospitalmar.View.screens.GlobalScreens.SettingsScreen
import com.reinosa.hospitalmar.ViewModel.LoginViewModel
import com.reinosa.hospitalmar.ui.theme.HospitalMarTheme
import com.reinosa.hospitalmar.widgets.Competencias.CompetenciasContainer
import com.reinosa.hospitalmar.widgets.Global.Profile.SplashScreen
import com.reinosa.hospitalmar.widgets.Evaluacio.EvaluarScreen
import com.reinosa.hospitalmar.widgets.Informe.InformeScreen
import com.reinosa.hospitalmar.widgets.Login.LoginForm
import com.reinosa.hospitalmar.widgets.Modulos.ModulContainer
import com.reinosa.hospitalmar.widgets.StudentList.StudentList


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
//    val coevalViewModel : LoginViewModel = remember { LoginViewModel() }
    NavHost(
        navController = navController,
        startDestination = NavigationGraph.LOGIN
    ) {
        // Pantalla de settings
        composable(NavigationGraph.SETTINGS) {
            SettingsScreen(navController)
        }
        // Pantalla de about
        composable(NavigationGraph.ABOUT) {
            AboutScreen(navController)
        }
        // Pantalla de login
        composable(NavigationGraph.LOGIN) {
            LoginForm(navController, loginViewModel)
        }
        // Pantalla principal como alumno
        composable(NavigationGraph.DRAWER){
            StudentDrawerAppScreen(navController)
        }
        // Pantalla de evaluacion para profesor
        composable(NavigationGraph.EVALUATE){
            EvaluarScreen(navController, loginViewModel)
        }
        // Pantalla de perfil
        composable(NavigationGraph.PROFILE){
            ProfileScreen(navController)
        }
        // Lista de modulos
        composable(NavigationGraph.MODULO) {
            ModulContainer(navController, loginViewModel)
        }

        // Pantalla de resultados o informes
        composable(NavigationGraph.INFORME) {
            InformeScreen(navController, loginViewModel)
        }
        // Pantalla principal como profesor
        composable(NavigationGraph.TEACHER){
            TeacherDrawerAppScreen(navController, loginViewModel)
        }
        // Pantalla splashscreen
        composable(NavigationGraph.SPLASH){
            SplashScreen(navController)
        }
        //Lista alumnos
        composable(NavigationGraph.STUDENT){
            StudentList(navController, loginViewModel)
        }
        //Competencias
        composable(NavigationGraph.COMPETENCIAS){
            CompetenciasContainer(navController = navController, viewModel = loginViewModel)
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
    const val STUDENT = "student"
    const val INFORME = "informe"
    const val TEACHER = "teacher"
    const val SPLASH = "splash"
    const val COMPETENCIAS = "competencias"

}