package com.peter.creditfins.data.model

import com.google.gson.annotations.SerializedName

data class ReviewResponse(
    val id: Int,
    val page: Int,
    @SerializedName("results")
    val review: List<Review>,
    val total_pages: Int,
    val total_results: Int
)

data class Review(
    val author: String,
    val author_details: AuthorDetails,
    val content: String,
    val created_at: String,
    val id: String,
    val updated_at: String,
    val url: String
)

data class AuthorDetails(
    val avatar_path: String?,
    val name: String,
    val rating: Any,
    val username: String
)