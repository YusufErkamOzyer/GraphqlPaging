package com.yusuferkamozyer.graphqlpaging.domain.apollo

import com.yusuferkamozyer.graphqlpaging.domain.model.DetailCharacter
import com.yusuferkamozyer.graphqlpaging.domain.model.SimpleCharacter

interface CharacterClient {

    suspend fun downloadCharacters(page:Int):List<SimpleCharacter>
    suspend fun downloadSpecialCharacter(id:String): DetailCharacter
}