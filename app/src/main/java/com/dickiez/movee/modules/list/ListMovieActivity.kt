/***
 * Author         : Dicky Zulkarnain
 * Date           : 31/10/22
 * Original File  : ListMovieActivity
 ***/

package com.dickiez.movee.modules.list

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import com.dickiez.core.constants.ApiConstants
import com.dickiez.core.constants.IMAGE_BASE_URL
import com.dickiez.core.constants.MovieCategory
import com.dickiez.core.constants.PosterSize
import com.dickiez.data.models.tmdb.MovieResult
import com.dickiez.movee.modules.detail.MovieDetailActivity
import com.dickiez.movee.ui.views.MovieCard
import com.dickiez.movee.ui.views.RatingBar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListMovieActivity: ComponentActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    val category = intent.getStringExtra(CATEGORY) ?: MovieCategory.Popular.value
    val viewModel = ViewModelProvider(this)[ListMovieViewModel::class.java]
    viewModel.setCategory(MovieCategory.fromString(category) ?: MovieCategory.Popular)
    viewModel.fetchInitialData()
    setContent {
      ListMovieView(viewModel = viewModel)
    }
  }

  companion object {
    private const val CATEGORY = "CATEGORY"
    fun intent(context: Context, category: String): Intent {
      return Intent(context, ListMovieActivity::class.java).apply {
        putExtra(CATEGORY, category)
      }
    }
  }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListMovieView(viewModel: ListMovieViewModel) {
  Scaffold(
    topBar = { TopAppBar(title = { Text(text = viewModel.currentCategory.value) }) },
  ) {
    ListMovieContent(paddingValues = it, viewModel)
  }
}

@Composable
fun ListMovieContent(paddingValues: PaddingValues, viewModel: ListMovieViewModel) {
  val loading = viewModel.loading.collectAsState()
  Surface(
    modifier = Modifier
      .fillMaxSize()
      .padding(paddingValues),
    color = MaterialTheme.colorScheme.background
  ) {
    LazyColumn(modifier = Modifier
      .padding(10.dp)
      .clipToBounds()) {
      itemsIndexed(items = viewModel.movies) { index, item ->
        if (index >= viewModel.movies.size-1 && !loading.value) {
          viewModel.getMovies()
        }
        ListMovieItem(item = item, viewModel)
      }
      item {
        if (loading.value) {
          Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
          }
        }
      }
    }
  }
}

@Composable
fun ListMovieItem(item: MovieResult, viewModel: ListMovieViewModel) {
  val context = LocalContext.current
  Row(modifier = Modifier.clickable { context.startActivity(MovieDetailActivity.intent(context, item.id ?: return@clickable)) }) {
    MovieCard(posterPath = IMAGE_BASE_URL+PosterSize.MEDIUM+item.posterPath)
    Column(modifier = Modifier.height(180.dp)) {
      Text(text = item.title ?: "-", fontWeight = FontWeight.Bold)
      viewModel.mapGenreIdsToNames(item.genreIds)?.let { Text(text = it, fontStyle = FontStyle.Italic) }
      Row(verticalAlignment = Alignment.CenterVertically) {
        Text(text = "${item.voteAverage ?: "-"}", fontWeight = FontWeight.Bold)
        RatingBar(rating = item.voteAverage?.div(2) ?: 0.0, stars = 5)
      }
      Text(text = item.overview ?: "-", overflow = TextOverflow.Ellipsis)
    }
  }
}

