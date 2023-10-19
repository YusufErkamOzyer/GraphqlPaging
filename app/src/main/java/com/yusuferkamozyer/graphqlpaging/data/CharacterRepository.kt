package com.yusuferkamozyer.graphqlpaging.data

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.yusuferkamozyer.graphqlpaging.domain.model.SimpleCharacter
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {
    fun getCharacters(): LiveData<PagingData<SimpleCharacter>>
}