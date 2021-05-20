package com.test.notificationapp.usecases.impl

import com.test.notificationapp.data.SharedPreferenceRepository
import com.test.notificationapp.usecases.base.NextIndexUseCase

class GetNextIndexUseCase(private val repository: SharedPreferenceRepository): NextIndexUseCase {

    override fun getNextIndex() = repository.getNextIndex()
}