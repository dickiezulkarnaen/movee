package com.dickiez.movee.modules.main

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import com.dickiez.core.constants.ApiConstants
import com.dickiez.core.constants.IMAGE_BASE_URL
import com.dickiez.core.constants.MovieCategory
import com.dickiez.core.constants.PosterSize
import com.dickiez.data.models.MovieSection
import com.dickiez.data.models.tmdb.MovieResult
import com.dickiez.movee.modules.detail.MovieDetailActivity
import com.dickiez.movee.modules.list.ListMovieActivity
import com.dickiez.movee.ui.theme.MoveeTheme
import com.dickiez.movee.ui.views.MovieCard
import com.dickiez.movee.ui.views.SeeAllCard
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

  private lateinit var viewModel: MainViewModel

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    viewModel = ViewModelProvider(this)[MainViewModel::class.java]
    setContent {
      val loading = viewModel.loading.collectAsState()
      MoveeTheme {
        MainView(viewModel, loading.value)
      }
    }
  }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainView(viewModel: MainViewModel, showLoading: Boolean) {
  Scaffold(
    topBar = { TopAppBar(title = { Text(text = "Movee") }) },
  ) {
    MainContent(paddingValues = it, viewModel)
    if (showLoading) {
      Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
      }
    }
  }
}

@Composable
fun MainContent(paddingValues: PaddingValues, viewModel: MainViewModel) {
  val sections = arrayListOf(
    MovieSection(MovieCategory.Popular, viewModel.popular),
    MovieSection(MovieCategory.TopRated, viewModel.topRated),
    MovieSection(MovieCategory.NowPlaying, viewModel.nowPlaying),
    MovieSection(MovieCategory.Upcoming, viewModel.upcoming),
  )
  Surface(
    modifier = Modifier
      .fillMaxSize()
      .padding(paddingValues),
    color = MaterialTheme.colorScheme.background
  ) {
    LazyColumn(modifier = Modifier
      .padding(10.dp)
      .clipToBounds()) {
      items(items = sections) { item ->
        MovieSectionView(section = item)
        Spacer(modifier = Modifier.size(width = Dp.Infinity, height = 15.dp))
      }
    }
  }
}

@Composable
fun MovieSectionView(section: MovieSection) {
  val context = LocalContext.current
  Column {
    Text(
      text = section.category.value,
      fontWeight = FontWeight.Bold,
      modifier = Modifier.padding(start = 5.dp)
    )
    Spacer(modifier = Modifier.size(width = Dp.Infinity, height = 10.dp))
    LazyRow(content = {
      itemsIndexed(section.items) { index: Int, item: MovieResult ->
        MovieCard(IMAGE_BASE_URL + PosterSize.MEDIUM+item.posterPath) {
          context.startActivity(MovieDetailActivity.intent(context, item.id ?: return@MovieCard))
        }
        if (index == section.items.lastIndex) {
          SeeAllCard {
            context.startActivity(ListMovieActivity.intent(context, section.category.key))
          }
        }
      }
    })
  }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
  MoveeTheme {
    //MainView()
  }
}