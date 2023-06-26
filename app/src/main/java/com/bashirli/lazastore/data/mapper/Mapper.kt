package com.bashirli.lazastore.data.mapper

import com.bashirli.lazastore.data.dto.AuthDTO
import com.bashirli.lazastore.data.dto.Category
import com.bashirli.lazastore.data.dto.CategoryDTOItem
import com.bashirli.lazastore.data.dto.ProductDTOItem
import com.bashirli.lazastore.data.dto.RegisterDTO
import com.bashirli.lazastore.data.dto.UserDTO
import com.bashirli.lazastore.domain.model.AuthModel
import com.bashirli.lazastore.domain.model.CategoryModel
import com.bashirli.lazastore.domain.model.ProductCategoryModel
import com.bashirli.lazastore.domain.model.ProductModel
import com.bashirli.lazastore.domain.model.RegisterModel
import com.bashirli.lazastore.domain.model.UserModel

fun AuthDTO.toAuthModel():AuthModel {
   return AuthModel(
        accessToken = accessToken,
        refreshToken = refreshToken
    )
}

fun RegisterDTO.toRegisterModel():RegisterModel{
    return RegisterModel(
        avatar = avatar,
        name = name,
        email = email,
        password = password
    )
}

fun Category.toProductCategoryModel():ProductCategoryModel{
    return ProductCategoryModel(
        id=id,
        image=image,
        name=name
    )
}

fun ProductDTOItem.toProductModel():ProductModel{
    return ProductModel(
        category = category.toProductCategoryModel(),
        description=description,
        id=id,
        images=images,
        price=price,
        title=title
    )
}

fun List<ProductDTOItem>.toProductModel()=map{
    ProductModel(
        category = it.category.toProductCategoryModel(),
        description=it.description,
        id=it.id,
        images=it.images,
        price=it.price,
        title=it.title
    )
}

fun List<CategoryDTOItem>.toCategoryModel() = map {
    CategoryModel(
        id = it.id,
        name = it.name,
        image = it.image
    )
}

fun UserDTO.toUserModel()=UserModel(
    name = name,
    avatar = avatar,
    password = password,
    role=role,
    email=email
)

