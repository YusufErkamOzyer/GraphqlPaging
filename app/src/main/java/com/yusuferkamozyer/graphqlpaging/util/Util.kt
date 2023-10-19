package com.yusuferkamozyer.graphqlpaging.util

import android.content.Context
import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.yusuferkamozyer.CharacterQuery
import com.yusuferkamozyer.CharactersQuery
import com.yusuferkamozyer.graphqlpaging.R
import com.yusuferkamozyer.graphqlpaging.domain.model.DetailCharacter
import com.yusuferkamozyer.graphqlpaging.domain.model.SimpleCharacter

fun CharactersQuery.Result.toSimpleCharacter(): SimpleCharacter {
    return SimpleCharacter(name= name!!,id=id!!.toInt(),image=image!!)
}

fun CharacterQuery.Character.toDetailCharacter(): DetailCharacter {
    return DetailCharacter(name = name!!, id =id!!.toInt(),image=image!!,species=species!!,status=status!!,gender=gender!!, location = location!! )
}
fun ImageView.downloadImage(url: String?, progressDrawable: CircularProgressDrawable){
    val options= RequestOptions()
        .placeholder(progressDrawable)
        .error(R.mipmap.ic_launcher_round)
    Glide.with(context)
        .setDefaultRequestOptions(options)
        .load(url).into(this)
}
fun placeHolderProgressBar(context: Context):CircularProgressDrawable{
    return CircularProgressDrawable(context).apply {
        strokeWidth=8f
        centerRadius=40f
        start()
    }
}


