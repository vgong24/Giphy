package com.victoweng.giphy.dataModels.search.data

import com.victoweng.giphy.dataModels.search.data.images.ImagesData
import com.victoweng.giphy.dataModels.search.data.user.UserData

data class GifItem(
    val id: String? = "",
    val url: String? = "",
    val type: String? = "",
    val slug: String? = "",
    val bitly_url: String,
    val username: String,
    val source: String,
    val rating: String,
    val content_url: String,
    val user: UserData,
    val source_tld: String,
    val source_post_url: String,
    val update_datetime: String,
    val create_datetime: String,
    val import_datetime: String,
    val trending_datetime: String,
    val images: ImagesData,
    val title: String
)