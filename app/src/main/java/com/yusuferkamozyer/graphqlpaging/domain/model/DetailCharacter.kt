package com.yusuferkamozyer.graphqlpaging.domain.model

import com.yusuferkamozyer.CharacterQuery

data class DetailCharacter(
    val name:String,
    val id:Int,
    val image:String,
    val species:String,
    val status:String,
    val gender:String,
    val location: CharacterQuery.Location
)
