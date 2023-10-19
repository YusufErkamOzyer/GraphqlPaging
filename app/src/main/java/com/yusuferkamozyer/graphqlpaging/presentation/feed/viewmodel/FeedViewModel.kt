package com.yusuferkamozyer.graphqlpaging.presentation.feed.viewmodel


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import androidx.paging.liveData
import androidx.paging.map
import com.yusuferkamozyer.graphqlpaging.data.CharacterRepository
import com.yusuferkamozyer.graphqlpaging.domain.apollo.CharacterClient
import com.yusuferkamozyer.graphqlpaging.domain.model.CharacterUiState
import com.yusuferkamozyer.graphqlpaging.paging.CharacterPagingDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class FeedViewModel
    @Inject constructor(private val characterRepository: CharacterRepository
    ,private val characterClient: CharacterClient) : ViewModel() {
        val loading=MutableLiveData<Boolean>()

        val charList=Pager(PagingConfig(1)){
            CharacterPagingDataSource(characterClient)
        }.liveData.cachedIn(viewModelScope)



}