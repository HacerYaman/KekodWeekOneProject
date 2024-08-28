package com.example.kekodweekoneproject.di


import com.example.kekodweekoneproject.domain.usecase.ToggleEgoSwitchUseCase
import com.example.kekodweekoneproject.domain.usecase.UpdateBottomNavUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    fun provideToggleEgoSwitchUseCase(): ToggleEgoSwitchUseCase {
        return ToggleEgoSwitchUseCase(
        )
    }

    @Provides
    fun provideUpdateBottomNavUseCase(): UpdateBottomNavUseCase {
        return UpdateBottomNavUseCase()
    }
}
