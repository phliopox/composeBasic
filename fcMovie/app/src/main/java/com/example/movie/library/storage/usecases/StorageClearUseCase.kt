package com.example.movie.library.storage.usecases

import com.example.movie.library.storage.IStorage
import javax.inject.Inject

class StorageClearUseCase @Inject constructor(private val storage: IStorage) : IStorageClearUseCase {
    override fun invoke() {
        storage.clear()
    }
}