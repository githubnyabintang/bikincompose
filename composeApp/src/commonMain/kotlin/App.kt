import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.bikincompose.ui.SentimentScreen
import com.example.bikincompose.ui.theme.BikincomposeTheme
import com.example.bikincompose.DashboardScreen
import com.example.bikincompose.DashboardBottomNavigation
import com.example.bikincompose.Screen

@Composable
fun App() {
    BikincomposeTheme {
        var currentScreen by remember { mutableStateOf(Screen.DASHBOARD) }

        Scaffold(
            bottomBar = {
                DashboardBottomNavigation(
                    currentScreen = currentScreen,
                    onScreenSelected = { currentScreen = it }
                )
            }
        ) { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)) {
                when (currentScreen) {
                    Screen.DASHBOARD -> DashboardScreen()
                    Screen.SENTIMEN -> SentimentScreen(onBack = { currentScreen = Screen.DASHBOARD })
                    else -> {
                        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                            Text("Halaman dalam pengembangan")
                        }
                    }
                }
            }
        }
    }
}
