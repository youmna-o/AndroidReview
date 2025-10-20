package com.example.androidreview.data.mappers

import com.example.androidreview.data.models.ProductResponseDTO
import com.example.androidreview.domain.entities.ProductResponse


class ProductMapper {
    fun mapResponseDtoToResponse(dtoList: List<ProductResponseDTO>): List<ProductResponse> {
        return dtoList.map { dto ->
            ProductResponse(
                id = dto.id,
                title = dto.title,
                price = dto.price,
                description = dto.description,
                image = dto.thumbnail
            )
        }
    }

}