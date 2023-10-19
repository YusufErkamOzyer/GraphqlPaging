package com.yusuferkamozyer.graphqlpaging.data

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.yusuferkamozyer.graphqlpaging.domain.apollo.CharacterClient
import com.yusuferkamozyer.graphqlpaging.domain.model.SimpleCharacter
import com.yusuferkamozyer.graphqlpaging.paging.CharacterPagingDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CharacterRepositoryImp
@Inject constructor(private val characterClient: CharacterClient):CharacterRepository {
    override fun getCharacters(): LiveData<PagingData<SimpleCharacter>> = Pager(
        config = PagingConfig(pageSize = NETWORK_PAGE_SIZE,enablePlaceholders = false) ,
        pagingSourceFactory = {CharacterPagingDataSource(characterClient)},
    ).liveData

    companion object {
        const val NETWORK_PAGE_SIZE = 20
    }
}