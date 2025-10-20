package com.example.androidreview.data.models

import com.google.gson.annotations.SerializedName


data class ProductResponseDTO (

    @SerializedName("id"                   ) var id                   : Int?               = null,
    @SerializedName("title"                ) var title                : String?            = null,
    @SerializedName("description"          ) var description          : String?            = null,
    @SerializedName("category"             ) var category             : String?            = null,
    @SerializedName("price"                ) var price                : Double?            = null,
    @SerializedName("discountPercentage"   ) var discountPercentage   : Double?            = null,
    @SerializedName("rating"               ) var rating               : Double?            = null,
    @SerializedName("stock"                ) var stock                : Int?               = null,
    @SerializedName("tags"                 ) var tags                 : ArrayList<String>  = arrayListOf(),
    @SerializedName("brand"                ) var brand                : String?            = null,
    @SerializedName("sku"                  ) var sku                  : String?            = null,
    @SerializedName("weight"               ) var weight               : Int?               = null,
    @SerializedName("warrantyInformation"  ) var warrantyInformation  : String?            = null,
    @SerializedName("shippingInformation"  ) var shippingInformation  : String?            = null,
    @SerializedName("availabilityStatus"   ) var availabilityStatus   : String?            = null,
    @SerializedName("returnPolicy"         ) var returnPolicy         : String?            = null,
    @SerializedName("minimumOrderQuantity" ) var minimumOrderQuantity : Int?               = null,
    @SerializedName("images"               ) var images               : ArrayList<String>  = arrayListOf(),
    @SerializedName("thumbnail"            ) var thumbnail            : String?            = null

)