package com.yusuferkamozyer.graphqlpaging.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.yusuferkamozyer.graphqlpaging.domain.apollo.CharacterClient
import com.yusuferkamozyer.graphqlpaging.domain.model.SimpleCharacter
import javax.inject.Inject

class CharacterPagingDataSource
    (private val characterClient: CharacterClient):PagingSource<Int,SimpleCharacter>() {
    override fun getRefreshKey(state: PagingState<Int, SimpleCharacter>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, SimpleCharacter> {
        val page = params.key ?: STARTING_PAGE_INDEX
        return try {
            val charList=characterClient.downloadCharacters(page)
            println(params.key)
            LoadResult.Page(
                data = charList,
                prevKey = if (page == STARTING_PAGE_INDEX) null else page.minus(1),
                nextKey = if (charList.isEmpty()) null else page + (params.loadSize / 20)
            )
        }catch (e:Exception){
            return LoadResult.Error(e)
        }
    }
    companion object {
        private const val STARTING_PAGE_INDEX = 1
    }
}