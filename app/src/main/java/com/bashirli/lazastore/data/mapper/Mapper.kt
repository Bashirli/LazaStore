package com.bashirli.lazastore.data.mapper

import com.bashirli.lazastore.data.dto.AuthDTO
import com.bashirli.lazastore.data.dto.CategoryDTO
import com.bashirli.lazastore.data.dto.Product
import com.bashirli.lazastore.data.dto.ProductDTO
import com.bashirli.lazastore.data.dto.SingleProductDTO
import com.bashirli.lazastore.data.dto.cart.Cart
import com.bashirli.lazastore.data.dto.cart.CartDTO
import com.bashirli.lazastore.data.dto.cart.CartProduct
import com.bashirli.lazastore.data.dto.cart.CartUpdateDTO
import com.bashirli.lazastore.data.dto.register.RegisterDTO
import com.bashirli.lazastore.data.dto.user.UserDTO
import com.bashirli.lazastore.domain.model.AuthModel
import com.bashirli.lazastore.domain.model.CategoryModel
import com.bashirli.lazastore.domain.model.MainProductModel
import com.bashirli.lazastore.domain.model.ProductModel
import com.bashirli.lazastore.domain.model.ProfileModel
import com.bashirli.lazastore.domain.model.RegisterModel
import com.bashirli.lazastore.domain.model.SingleProductModel
import com.bashirli.lazastore.domain.model.UserModel
import com.bashirli.lazastore.domain.model.cart.CartMainModel
import com.bashirli.lazastore.domain.model.cart.CartModel
import com.bashirli.lazastore.domain.model.cart.CartProductModel

fun AuthDTO.toAuthModel():AuthModel {
   return AuthModel(
        accessToken = token
    )
}

fun RegisterDTO.toRegisterModel():RegisterModel{
    return RegisterModel(
        username = username,
        email = email,
        password = password
    )
}

fun List<Product>.toProductModel() = map {
    ProductModel(
        brand = it.brand,
        category = it.category,
        description = it.description,
        discountPercentage = it.discountPercentage,
        price = it.price,
        title = it.title,
        images = it.images,
        rating = it.rating,
        thumbnail = it.thumbnail,
        id = it.id
    )
}

fun ProductDTO.toMainProductModel():MainProductModel=MainProductModel(
        total=total,
        products = products.toProductModel()
    )




fun CategoryDTO.toCategoryModel()=map {
    CategoryModel(
        it
    )
}

fun UserDTO.toUserModel()=UserModel(
    username = username,
    image = image,

)

fun UserDTO.toProfileModel()=ProfileModel(
    address,
    age,
    birthDate,
    bloodGroup,
    domain,
    ein,
    email,
    eyeColor,
    firstName,
    gender,
    height,
    id,
    image,
    ip,
    lastName,
    macAddress,
    maidenName,
    password,
    phone,
    ssn,
    university,
    userAgent,
    username,
    weight
)

fun SingleProductDTO.toSingleProductModel()=SingleProductModel(
    id=id,
    description = description,
    images = images,
    brand=brand,
    price = price,
    thumbnail = thumbnail,
    discountPercentage = discountPercentage,
    rating = rating,
    title = title,
    category = category
)

fun CartDTO.toCartMainModel()=CartMainModel(
    total = total,
    limit = limit,
    carts = carts.toCartModel()
)

fun List<Cart>.toCartModel()=map{
    CartModel(
        id = it.id ,
        total=it.total,
        products = it.cartProducts.toCartProductModel(),
        totalProducts = it.totalProducts,
        totalQuantity = it.totalQuantity,
        userId = it.userId
    )
}


fun List<CartProduct>.toCartProductModel() = map {
    CartProductModel(
        discountPercentage = it.discountPercentage,
        discountedPrice = it.discountedPrice,
        id = it.id,
        price = it.price,
        quantity = it.quantity,
        title = it.title,
        total = it.total
    )
}



fun CartUpdateDTO.toCartModel()=CartModel(
    id = id ,
    total=total,
    products = products.toCartProductModel(),
    totalProducts = totalProducts,
    totalQuantity = totalQuantity,
    userId = userId
)

