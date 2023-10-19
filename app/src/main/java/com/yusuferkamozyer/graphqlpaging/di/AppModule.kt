package com.yusuferkamozyer.graphqlpaging.di

import com.apollographql.apollo3.ApolloClient
import com.yusuferkamozyer.graphqlpaging.data.CharacterRepository
import com.yusuferkamozyer.graphqlpaging.data.CharacterRepositoryImp
import com.yusuferkamozyer.graphqlpaging.domain.apollo.ApolloCharacterClient
import com.yusuferkamozyer.graphqlpaging.domain.apollo.CharacterClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideApolloClient():ApolloClient{
        return ApolloClient.Builder()
            .serverUrl("https://rickandmortyapi.com/graphql").build()
    }

    @Provides
    @Singleton
    fun provideCharacterClient(apolloClient: ApolloClient):CharacterClient{
        return ApolloCharacterClient(apolloClient)
    }

    @Provides
    @Singleton
    fun provideCharacterRepository(characterClient:CharacterClient):CharacterRepository{
        return CharacterRepositoryImp(characterClient)
    }
}