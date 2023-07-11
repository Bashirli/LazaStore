package com.bashirli.lazastore.data.mapper

import com.bashirli.lazastore.data.dto.local.FavoritesDTO
import com.bashirli.lazastore.data.dto.remote.AuthDTO
import com.bashirli.lazastore.data.dto.remote.CategoryDTO
import com.bashirli.lazastore.data.dto.remote.product.ProductDTO
import com.bashirli.lazastore.data.dto.remote.SingleProductDTO
import com.bashirli.lazastore.data.dto.remote.cart.Cart
import com.bashirli.lazastore.data.dto.remote.cart.CartDTO
import com.bashirli.lazastore.data.dto.remote.cart.CartProduct
import com.bashirli.lazastore.data.dto.remote.cart.CartUpdateDTO
import com.bashirli.lazastore.data.dto.remote.product.Product
import com.bashirli.lazastore.data.dto.remote.register.RegisterDTO
import com.bashirli.lazastore.data.dto.remote.search.SearchDTO
import com.bashirli.lazastore.data.dto.remote.search.SearchProduct
import com.bashirli.lazastore.data.dto.remote.user.UserDTO
import com.bashirli.lazastore.domain.model.local.FavoritesModel
import com.bashirli.lazastore.domain.model.remote.AuthModel
import com.bashirli.lazastore.domain.model.remote.CategoryModel
import com.bashirli.lazastore.domain.model.remote.MainProductModel
import com.bashirli.lazastore.domain.model.remote.ProductModel
import com.bashirli.lazastore.domain.model.remote.ProfileModel
import com.bashirli.lazastore.domain.model.remote.RegisterModel
import com.bashirli.lazastore.domain.model.remote.SingleProductModel
import com.bashirli.lazastore.domain.model.remote.UserModel
import com.bashirli.lazastore.domain.model.remote.cart.CartMainModel
import com.bashirli.lazastore.domain.model.remote.cart.CartModel
import com.bashirli.lazastore.domain.model.remote.cart.CartProductModel

fun AuthDTO.toAuthModel(): AuthModel {
   return AuthModel(
        accessToken = token
    )
}

fun RegisterDTO.toRegisterModel(): RegisterModel {
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

fun ProductDTO.toMainProductModel(): MainProductModel = MainProductModel(
        total=total,
        products = products.toProductModel()
    )




fun CategoryDTO.toCategoryModel()=map {
    CategoryModel(
        it
    )
}

fun UserDTO.toUserModel()= UserModel(
    username = username,
    image = image,

)

fun UserDTO.toProfileModel()= ProfileModel(
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

fun SingleProductDTO.toSingleProductModel()= SingleProductModel(
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

fun CartDTO.toCartMainModel()= CartMainModel(
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



fun CartUpdateDTO.toCartModel()= CartModel(
    id = id ,
    total=total,
    products = products.toCartProductModel(),
    totalProducts = totalProducts,
    totalQuantity = totalQuantity,
    userId = userId
)

fun List<SearchProduct>.toProductModelFromSearch():List<ProductModel>{
    return map{
        ProductModel(
            it.brand,
            it.category,
            it.description,
            it.discountPercentage,
            it.id,
            it.images,
            it.price,
            it.rating,
            it.thumbnail,
            it.title
        )
    }
}
fun SearchDTO.toMainProductModel()= MainProductModel(
    total = total,
    products = searchProducts.toProductModelFromSearch()
)


fun List<FavoritesDTO>.toFavoriteModel()=map {
    FavoritesModel(
        id=it.id,
        title = it.title,
        price = it.price,
        description = it.description,
        imageURL = it.imageURL

    )
}

