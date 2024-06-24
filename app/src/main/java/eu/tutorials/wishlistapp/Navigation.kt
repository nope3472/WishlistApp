package eu.tutorials.wishlistapp

import androidx.compose.runtime.Composable

import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

@Composable
fun Navigation(viewModel: WishViewModel = viewModel(), navController:NavHostController= rememberNavController()){
   NavHost(navController = navController, startDestination = Screen.HomeScreen.route){
       composable(Screen.HomeScreen.route){
           HomeView(viewModel,navController)
       }
       composable(
           route = Screen.AddScreen.route + "/{id}",
           arguments = listOf(
               navArgument("id") {
                   type = NavType.LongType
                   defaultValue = 0L
                   nullable = false
               }
           )
       ) { navBackStackEntry ->
           val id = navBackStackEntry.arguments?.getLong("id") ?: 0L
           AddEditDetailView(id = id, viewModel = viewModel, navController = navController)
       }    
   }
}