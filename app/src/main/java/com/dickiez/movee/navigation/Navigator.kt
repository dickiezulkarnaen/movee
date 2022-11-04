/***
 * Author         : Dicky Zulkarnain
 * Date           : 29/10/22
 * Original File  : Navigation
 ***/

package com.dickiez.movee.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dickiez.movee.modules.detail.MovieDetailActivity
import com.dickiez.movee.modules.main.MainActivity
import com.dickiez.movee.modules.main.MainView

@Composable
fun Navigator(
  navController: NavHostController,
  startDestination: String,
  route: String,
) {
  NavHost(navController = navController, startDestination = startDestination) {
    composable(route = Page.MainPage.route) {

    }
    composable(route = Page.DetailPage.route) {
      MovieDetailActivity()
    }
  }
}