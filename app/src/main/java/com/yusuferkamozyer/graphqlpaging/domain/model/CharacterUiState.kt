package com.yusuferkamozyer.graphqlpaging.domain.model

data class CharacterUiState(private val simpleCharacter: SimpleCharacter){
    fun getCharImageUrl()=simpleCharacter.image
    fun getCharName()=simpleCharacter.name
    fun getCharId()=simpleCharacter.id
}
