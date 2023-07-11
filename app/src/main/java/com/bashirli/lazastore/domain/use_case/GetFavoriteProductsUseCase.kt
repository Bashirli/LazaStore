package com.bashirli.lazastore.domain.use_case

import com.bashirli.lazastore.data.dto.local.FavoritesDTO
import com.bashirli.lazastore.domain.repo.RoomRepository
import javax.inject.Inject

class GetFavoriteProductsUseCase @Inject constructor(private val repo: RoomRepository) {

    suspend operator fun invoke()=repo.getAllFavorites()

}