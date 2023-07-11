package com.bashirli.lazastore.domain.use_case

import com.bashirli.lazastore.domain.repo.RoomRepository
import javax.inject.Inject

class GetSingleFavoriteProductUseCase @Inject constructor(private val repo:RoomRepository) {
    suspend operator fun invoke(id:Int) = repo.getSingleFavoritesProduct(id)
}