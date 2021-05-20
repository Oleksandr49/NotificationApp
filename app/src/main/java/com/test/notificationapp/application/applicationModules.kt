package com.test.notificationapp.application

import androidx.room.Room
import com.test.notificationapp.data.NotificationPagesDatabase
import com.test.notificationapp.data.NotificationPagesRepository
import com.test.notificationapp.usecases.impl.NotificationPageCreationUseCase
import com.test.notificationapp.usecases.impl.DeleteNotificationPageUseCase
import com.test.notificationapp.usecases.impl.GetAllNotificationPagesUseCase
import com.test.notificationapp.viewmodels.ActivityViewModel
import com.test.notificationapp.viewmodels.CommonViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val applicationModules = module {

    viewModel { ActivityViewModel(get() as GetAllNotificationPagesUseCase) }
    viewModel { CommonViewModel(get() as NotificationPageCreationUseCase, get() as DeleteNotificationPageUseCase)}

    factory { NotificationPageCreationUseCase(get() as NotificationPagesRepository) }
    factory { DeleteNotificationPageUseCase(get() as NotificationPagesRepository) }
    factory { GetAllNotificationPagesUseCase(get() as NotificationPagesRepository) }

    single { NotificationPagesRepository(get())}

    single {
        Room.databaseBuilder(androidApplication(), NotificationPagesDatabase::class.java, "Database")
            .fallbackToDestructiveMigration()
            .build()
            .notificationPageDao()
    }
}