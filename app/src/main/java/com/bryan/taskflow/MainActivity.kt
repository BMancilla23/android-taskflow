package com.bryan.taskflow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.*
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.bryan.taskflow.navigation.AppNavHost
import com.bryan.taskflow.navigation.Screen
import com.bryan.taskflow.presentation.splash.SplashScreen
import com.bryan.taskflow.presentation.login.LoginScreen
import com.bryan.taskflow.presentation.register.RegisterScreen
import com.bryan.taskflow.presentation.task.TaskScreen
import com.bryan.taskflow.ui.theme.TaskFlowTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TaskFlowTheme {
                AppNavHost()
                /**
                 * remember conserva un objeto entre recomposiciones.
                 *
                 * Sin remember, cada vez que Compose recomponga la UI
                 * se crearía nuevamente el repositorio.
                 *
                 * Similar a mantener una instancia estable durante
                 * todo el ciclo de vida del composable.
                 *
                 * Similar a useMemo en React.
                 * Conserva la instancia entre recomposiciones
                 */
//                val userRepository = remember {
//                    RepositoryProvider.provideUserRepository(this@MainActivity)
//                }

                /**
                 * SessionManager encapsula el acceso a DataStore.
                 *
                 * Necesita Context porque DataStore es una API
                 * del sistema Android que requiere acceso al
                 * almacenamiento interno de la aplicación
                 */
//                val sessionManager = SessionProvider.provide(this@MainActivity)

                /**
                 * Los ViewModels reciben sus dependencias por
                 * constructor (Dependency Injection manual).
                 *
                 * Esto evita que el ViewModel cree sus propias
                 * dependencias y facilita pruebas y mantenimiento.
                 */
//                val loginViewModel = remember {
//                    LoginViewModel(userRepository, sessionManager)
//                }

//                val splashViewModel = remember {
//                    SplashViewModel(sessionManager)
//                }

//                val registerViewModel = remember {
//                    RegisterViewModel(userRepository)
//                }

//                val taskViewModel = remember {
//                    TaskViewModel()
//                }


                /**
                 * Estado que representa la pantalla actual.
                 *
                 * mutableStateOf notifica a Compose cuando cambia
                 * el valor y provoca una recomposicion automática.
                 *
                 * Screen es una sealed class que nos permite
                 * navegación tipada y evita errores por strings.
                 */
//                var currentScreen by remember {
////                    mutableStateOf("login")
//                    mutableStateOf<Screen>(
//                        Screen.Splash
//                    )
//                }


                /**
                 * when funciona como un router simple.
                 *
                 * Dependiendo de la pantalla actual.
                 * Compose renderiza un árbol de UI diferente.
                 *
                 * Es equivalente conceptual a:
                 *
                 * React:
                 * if (route === "/login")
                 *
                 * Angular:
                 * <router-outlet>
                 *
                 * Vue:
                 * <router-view>
                 */
//                when (currentScreen) {
//                    Screen.Splash -> {
//                        SplashScreen(
////                            viewModel = splashViewModel,
//                            viewModel = hiltViewModel(),
//                            onNavigateToLogin = {
//                                currentScreen = Screen.Login
//                            },
//                            onNavigateToTasks = {
//                                currentScreen = Screen.Tasks
//                            }
//                        )
//                    }
//
//                    Screen.Login -> LoginScreen(
////                        viewModel = LoginViewModel(),
////                        viewModel = loginViewModel,
//                        viewModel = hiltViewModel(),
//                        onLoginSuccess = {
//                            currentScreen = Screen.Tasks
//                        },
//                        onNavigationToRegister = {
//                            currentScreen = Screen.Register
//                        }
//                    )
//
//                    Screen.Register -> RegisterScreen(
////                        viewModel = RegisterViewModel(),
////                        viewModel = registerViewModel,
//                        viewModel = hiltViewModel(),
//                        onRegisterSuccess = {
//                            currentScreen = Screen.Login
//                        },
//                        onNavigationToLogin = {
//                            currentScreen = Screen.Login
//                        }
//                    )
////                    "home" -> HomeScreen()
//                    Screen.Tasks -> TaskScreen(
////                        viewModel = taskViewModel
//                        viewModel = hiltViewModel()
//                    )
//                }
            }
        }
    }
}
