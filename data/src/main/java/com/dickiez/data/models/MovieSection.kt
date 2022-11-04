package com.dickiez.data.models

import com.dickiez.core.constants.MovieCategory
import com.dickiez.data.models.tmdb.MovieResult

data class MovieSection(val category: MovieCategory, val items: List<MovieResult>)
