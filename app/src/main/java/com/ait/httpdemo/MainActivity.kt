package com.ait.httpdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ait.httpdemo.ui.navigation.Screen
import com.ait.httpdemo.ui.screen.MainScreen
import com.ait.httpdemo.ui.screen.money.MoneyApiScreen
import com.ait.httpdemo.ui.screen.nasa.NasaMarsApiScreen
import com.ait.httpdemo.ui.theme.HttpDemoTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HttpDemoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavGraph(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}
@Composable
fun NavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Screen.Main.route
    ) {
        composable(Screen.Main.route) {
            MainScreen(
                onMoneyAPISelected = { navController.navigate(Screen.MoneyRatesAPI.route) },
                onNasaMarsAPISelected = { navController.navigate(Screen.NasaMarsAPI.route) }
            )
        }
        composable(Screen.MoneyRatesAPI.route) {
            MoneyApiScreen()
        }
        composable(Screen.NasaMarsAPI.route) {
            NasaMarsApiScreen()
        }
    }
}