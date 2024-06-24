package eu.tutorials.wishlistapp

 sealed class Screen(val route:String) {
     object HomeScreen:Screen("Home_Screen")
     object AddScreen:Screen("add_Screen")
}