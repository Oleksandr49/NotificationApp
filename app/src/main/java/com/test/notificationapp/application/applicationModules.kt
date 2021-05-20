package com.test.notificationapp.application

import androidx.room.Room
import com.test.notificationapp.viewmodels.ActivityViewModel
import com.test.notificationapp.viewmodels.CommonViewModel
import com.test.notificationapp.data.Database
import com.test.notificationapp.data.NDFRepository
import com.test.notificationapp.usecases.impl.CreateNFD
import com.test.notificationapp.usecases.impl.DeleteNFD
import com.test.notificationapp.usecases.impl.GetAllNFD
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val applicationModules = module {

    viewModel { ActivityViewModel(GetAllNFD(get())) }
    viewModel { CommonViewModel(CreateNFD(get()), DeleteNFD(get()))}

    factory { CreateNFD(get()) }
    factory { DeleteNFD(get()) }
    factory { GetAllNFD(get()) }

    single { NDFRepository(get())}

    single {
        Room.databaseBuilder(androidApplication(), Database::class.java, "Database")
            .fallbackToDestructiveMigration()
            .build()
            .dao()
    }
}