/***
 * Author         : Dicky Zulkarnain
 * Date           : 29/10/22
 * Original File  : MovieDetailActivity
 ***/

package com.dickiez.movee.modules.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import coil.compose.AsyncImage
import com.dickiez.core.constants.BackDropSize
import com.dickiez.core.constants.IMAGE_BASE_URL
import com.dickiez.core.constants.PosterSize
import com.dickiez.data.models.tmdb.Cast
import com.dickiez.data.models.tmdb.CreditResponse
import com.dickiez.data.models.tmdb.MovieDetailResponse
import com.dickiez.movee.ui.theme.MoveeTheme
import com.dickiez.movee.ui.views.CastView
import com.dickiez.movee.ui.views.RatingBar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailActivity: ComponentActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    val movieId = intent.getIntExtra(MOVIE_ID, 0)
    val viewModel = ViewModelProvider(this)[MovieDetailViewModel::class.java]
    viewModel.getMovie(movieId)
    viewModel.getCredit(movieId)
    setContent {
      val loading = viewModel.loading.collectAsState()
      val movie = viewModel.movieDetail.collectAsState()
      val director = viewModel.director.collectAsState()
      MovieDetailView(
        movie = movie.value,
        casts = viewModel.casts,
        showLoading = loading.value,
        director = director.value
      )
    }
  }

  companion object {
    private const val MOVIE_ID = "MOVIE_ID"
    fun intent(context: Context, movieId: Int) : Intent {
      return Intent(context, MovieDetailActivity::class.java).apply {
        putExtra(MOVIE_ID, movieId)
      }
    }
  }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieDetailView(movie: MovieDetailResponse, casts: List<Cast>, showLoading: Boolean, director: String) {
  Scaffold(containerColor = Color.Black) {
    MovieDetailContent(paddingValues = it, movie, casts, director)
    if (showLoading) {
      Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator()
      }
    }
  }
}

@Composable
fun MovieDetailContent(
  paddingValues: PaddingValues,
  movie: MovieDetailResponse,
  casts: List<Cast>,
  director: String,
) {
  Surface(
    modifier = Modifier
      .verticalScroll(state = ScrollState(initial = 0))
      .fillMaxSize()
      .padding(paddingValues),
    color = Color.Black
  ) {
    Column(
      modifier = Modifier.fillMaxWidth()
    ) {
      MovieBackDrop(movie.backdropPath)
      Spacer(modifier = Modifier.height(20.dp))
      MovieInfo(movie)
      SingleCredit(label = "Synopsis", value = movie.overview ?: "")
      SingleCredit(label = "Director", value = director)
      SingleCredit(label = "Company", value = movie.productionCompanies?.map { comp -> comp.name }?.joinToString(", ") ?: "-")
      SingleCredit(label = "County", value = movie.productionCountries?.map { comp -> comp.name }?.joinToString(", ") ?: "-")
      Casts(casts = casts)
    }
  }
}

@Composable
fun MovieBackDrop(backDrop: String?) {
  val configuration = LocalConfiguration.current
  AsyncImage(
    model = "${IMAGE_BASE_URL + BackDropSize.LARGE}/$backDrop",
    contentDescription = null,
    contentScale = ContentScale.Crop,
    modifier = Modifier
      .fillMaxWidth()
      .height((configuration.screenHeightDp * 40 / 100).dp)
  )
}

@Composable
fun MovieInfo(movie: MovieDetailResponse) {
  Column() {
    Text(
      text = movie.title ?: "Unknown Title",
      color = Color.White,
      fontSize = 30.sp,
      fontWeight = FontWeight.Bold,
      textAlign = TextAlign.Center,
      modifier = Modifier.fillMaxWidth()
    )
    Spacer(modifier = Modifier.height(5.dp))
    Row(
      horizontalArrangement = Arrangement.Center,
      modifier = Modifier
        .fillMaxWidth()
        .align(Alignment.CenterHorizontally),
    ) {
      Text(text = "${movie.releaseDate?.substringBefore('-')}", color = Color.White)
      Text(text = "-", modifier = Modifier.padding(horizontal = 5.dp), color = Color.White)
      Text(text = "${movie.genres?.map { it.name }?.joinToString(", ")}", color = Color.White)
      Text(text = "-", modifier = Modifier.padding(horizontal = 5.dp), color = Color.White)
      Text(text = "${movie.runtime} mins", color = Color.White)
    }
    Spacer(modifier = Modifier.height(5.dp))
    Box(
      contentAlignment = Alignment.Center,
      modifier = Modifier
        .fillMaxWidth()
    ) {
      RatingBar(rating =( movie.voteAverage ?: 0.0) / 2)
    }
  }
}

@Composable
fun SingleCredit(label: String, value: String) {
  Column(modifier = Modifier.padding(10.dp)) {
    Text(text = "$label :", color = Color.White, fontWeight = FontWeight.Bold)
    Spacer(modifier = Modifier.height(10.dp))
    Text(
      text = value,
      color = Color.White
    )
  }
}

@Composable
fun Casts(casts: List<Cast>) {
  Column() {
    Text(text = "Casts :", color = Color.White, fontWeight = FontWeight.Bold, modifier = Modifier.padding(10.dp))
    Spacer(modifier = Modifier.height(10.dp))
    LazyRow(content = {
      items(items = casts) { item: Cast ->
        CastView(
          imageUrl = "${IMAGE_BASE_URL + PosterSize.SMALL}${item.profilePath}",
          name = item.name ?: "Unknown",
          modifier = Modifier.padding(start = 20.dp)
        )
      }
    })
  }
}

@Preview(showBackground = true)
@Composable
fun MovieDetailPreview() {
  MoveeTheme {
    //MovieDetailView(showLoading = false)
  }
}

