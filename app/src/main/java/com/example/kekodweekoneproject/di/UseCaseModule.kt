package com.example.kekodweekoneproject.di


import com.example.kekodweekoneproject.domain.usecase.ToggleEgoSwitchUseCase
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
            // Pass any dependencies here if needed
        )
    }
}
