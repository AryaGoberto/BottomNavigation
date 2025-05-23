package com.example.bottomnavigation

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.bottomnavigation.ui.theme.BottomNavigationTheme
import com.example.bottomnavigation.ui.theme.greenJC
import androidx.navigation.compose.composable


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BottomNavigationTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    MyBottomBar()
                }
            }
        }
    }
}

@Composable
fun MyBottomBar() {
    val navigationController = rememberNavController()
    val context = LocalContext.current.applicationContext
    val selected = remember {
        mutableStateOf(Icons.Default.Home)
    }
    Scaffold (
        bottomBar = {
            BottomAppBar(containerColor = greenJC){
                IconButton(onClick = {
                    selected.value = Icons.Default.Home
                    navigationController.navigate(Screens.Home.screens){
                        popUpTo(0)
                    }
                }, modifier = Modifier.weight(1f)) {
                    Icon(Icons.Default.Home,
                        contentDescription = null,
                        modifier = Modifier.size(26.dp),
                        tint = if(selected.value ==Icons.Default.Home) Color.White else Color.DarkGray)
                }
                //search
                IconButton(onClick = {
                    selected.value = Icons.Default.Search
                    navigationController.navigate(Screens.Search.screens){
                        popUpTo(0)
                    }
                }, modifier = Modifier.weight(1f)) {
                    Icon(Icons.Default.Search,
                        contentDescription = null,
                        modifier = Modifier.size(26.dp),
                        tint = if(selected.value ==Icons.Default.Search) Color.White else Color.DarkGray)
                }
                //Box
                Box(modifier = Modifier
                    .weight(1f)
                    .padding(16.dp),
                    contentAlignment = Alignment.Center) {
                    FloatingActionButton(onClick = {Toast.makeText(context, "Open Bottom Sheet", Toast.LENGTH_SHORT).show()}) {
                        Icon(Icons.Default.Add, contentDescription = null, tint = greenJC)
                    }
                }
                //Profile
                IconButton(onClick = {
                    selected.value = Icons.Default.Person
                    navigationController.navigate(Screens.Profile.screens){
                        popUpTo(0)
                    }
                }, modifier = Modifier.weight(1f)) {
                    Icon(Icons.Default.Person,
                        contentDescription = null,
                        modifier = Modifier.size(26.dp),
                        tint = if(selected.value ==Icons.Default.Person) Color.White else Color.DarkGray)
                }
                //Notification
                IconButton(onClick = {
                    selected.value = Icons.Default.Notifications
                    navigationController.navigate(Screens.Notification.screens){
                        popUpTo(0)
                    }
                }, modifier = Modifier.weight(1f)) {
                    Icon(Icons.Default.Notifications,
                        contentDescription = null,
                        modifier = Modifier.size(26.dp),
                        tint = if(selected.value ==Icons.Default.Notifications) Color.White else Color.DarkGray)
                }


            }
        }
    ) {
        paddingValues ->
        NavHost(
            navController = navigationController,
            startDestination = Screens.Home.screens,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(Screens.Home.screens) { Home() }
            composable(Screens.Search.screens) { Search() }
            composable(Screens.Notification.screens) { Notification() }
            composable(Screens.Profile.screens) { Profile() }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun myBottomBarPreview() {
    MyBottomBar()
}