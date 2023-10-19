package com.yusuferkamozyer.graphqlpaging.domain.apollo

import com.apollographql.apollo3.ApolloClient
import com.yusuferkamozyer.CharacterQuery
import com.yusuferkamozyer.CharactersQuery
import com.yusuferkamozyer.graphqlpaging.domain.model.DetailCharacter
import com.yusuferkamozyer.graphqlpaging.domain.model.SimpleCharacter
import com.yusuferkamozyer.graphqlpaging.util.toDetailCharacter
import com.yusuferkamozyer.graphqlpaging.util.toSimpleCharacter

class ApolloCharacterClient(
    private val apolloClient: ApolloClient
): CharacterClient {
    override suspend fun downloadCharacters(page: Int): List<SimpleCharacter> {
        return apolloClient.query(CharactersQuery())
            .execute().data?.characters?.results?.map{
                it!!.toSimpleCharacter()
            }?: emptyList()
    }

    override suspend fun downloadSpecialCharacter(id: String): DetailCharacter {
        return apolloClient.query(CharacterQuery(id))
            .execute().data?.character?.toDetailCharacter()!!
    }
}