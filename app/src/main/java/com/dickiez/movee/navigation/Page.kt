/***
 * Author         : Dicky Zulkarnain
 * Date           : 29/10/22
 * Original File  : Page
 ***/

package com.dickiez.movee.navigation


sealed class Page(val route: String) {
  object MainPage : Page("main_page")
  object DetailPage : Page("detail_page")
  object SectionPage : Page("section_page")
}