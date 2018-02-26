package com.androidmess.helix.movie.model.data

import com.google.gson.annotations.SerializedName

data class MovieSpokenLanguagesItem(@SerializedName("name")
                                    val name: String = "",
                                    @SerializedName("iso_639_1")
                                    val iso: String = "")

data class MovieGenresItem(@SerializedName("name")
                           val name: String = "",
                           @SerializedName("id")
                           val id: Int = 0)

data class MovieProductionCountriesItem(@SerializedName("iso_3166_1")
                                        val iso: String = "",
                                        @SerializedName("name")
                                        val name: String = "")

data class MovieProductionCompaniesItem(@SerializedName("name")
                                        val name: String = "",
                                        @SerializedName("id")
                                        val id: Int = 0)

data class MovieDetailsData(@SerializedName("original_language")
                        val originalLanguage: String = "",
                        @SerializedName("imdb_id")
                        val imdbId: String = "",
                        @SerializedName("video")
                        val video: Boolean = false,
                        @SerializedName("title")
                        val title: String = "",
                        @SerializedName("backdrop_path")
                        val backdropPath: String = "",
                        @SerializedName("revenue")
                        val revenue: Int = 0,
                        @SerializedName("genres")
                        val genres: List<MovieGenresItem>?,
                        @SerializedName("popularity")
                        val popularity: Double = 0.0,
                        @SerializedName("production_countries")
                        val productionCountries: List<MovieProductionCountriesItem>?,
                        @SerializedName("id")
                        val id: Int = 0,
                        @SerializedName("vote_count")
                        val voteCount: Int = 0,
                        @SerializedName("budget")
                        val budget: Int = 0,
                        @SerializedName("overview")
                        val overview: String = "",
                        @SerializedName("original_title")
                        val originalTitle: String = "",
                        @SerializedName("runtime")
                        val runtime: Int = 0,
                        @SerializedName("poster_path")
                        val posterPath: String = "",
                        @SerializedName("spoken_languages")
                        val spokenLanguages: List<MovieSpokenLanguagesItem>?,
                        @SerializedName("production_companies")
                        val productionCompanies: List<MovieProductionCompaniesItem>?,
                        @SerializedName("release_date")
                        val releaseDate: String = "",
                        @SerializedName("vote_average")
                        val voteAverage: Double = 0.0,
                        @SerializedName("tagline")
                        val tagline: String = "",
                        @SerializedName("adult")
                        val adult: Boolean = false,
                        @SerializedName("homepage")
                        val homepage: String = "",
                        @SerializedName("status")
                        val status: String = "")