package `in`.designway.goicarapp.di

import om.tech.sample.repo.Repository
import om.tech.sample.repo.RepositoryImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RespositoryModule {

    @Binds
    @Singleton
    abstract fun bindMyRepository(repo: RepositoryImp): Repository
}