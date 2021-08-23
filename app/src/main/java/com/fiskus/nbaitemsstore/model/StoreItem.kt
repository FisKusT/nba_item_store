package com.fiskus.nbaitemsstore.model


class StoreItem(var name: String, val type: Type, var price: Double, private val currency: Currency) {

    enum class Type(val displayName: String) {
        BASKETBALL("Basketball \uD83C\uDFC0"),
        JERSEY("Jersey \uD83C\uDFBD"),
        SHORTS("Shorts \uD83E\uDE73"),
        T_SHIRT("T-Shirt \uD83D\uDC55"),
        SHOE("Shoes \uD83D\uDC5F")
    }

    enum class Currency(val symbol: String) {
        USD("$"),
        NIS("₪"),
        EUR("€"),
        GBP("£")
    }

    private fun getDisplayPrice() = "$price${currency.symbol}"

    override fun toString(): String {
        return "$name\n${type.displayName}\n${getDisplayPrice()}"
    }
}

