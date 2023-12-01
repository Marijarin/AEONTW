package com.assignment.loginandpay.di

import com.assignment.loginandpay.domain.repository.AuthRepository
import com.assignment.loginandpay.data.repository.AuthRepositoryImpl
import com.assignment.loginandpay.data.repository.PaymentRepositoryImpl
import com.assignment.loginandpay.domain.repository.PaymentRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
interface RepositoryModule {
    @Singleton
    @Binds
    fun bindsPaymentRepository(impl: PaymentRepositoryImpl): PaymentRepository

    @Singleton
    @Binds
    fun bindsAuthRepository(impl: AuthRepositoryImpl): AuthRepository
}