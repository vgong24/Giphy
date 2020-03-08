package com.victoweng.giphy.dataModels.search

import com.victoweng.giphy.dataModels.search.data.GifItem
import com.victoweng.giphy.dataModels.search.meta.Meta
import com.victoweng.giphy.dataModels.search.pagination.Pagination

data class GifResponse(val data: MutableList<GifItem>? = mutableListOf(),
                       val pagination: Pagination? = Pagination(),
                       val meta: Meta? = Meta()
)