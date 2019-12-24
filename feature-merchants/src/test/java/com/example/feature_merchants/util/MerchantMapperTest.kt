package com.example.feature_merchants.util

import com.example.domain.models.ImageDTO
import com.example.domain.models.LocationDTO
import com.example.domain.models.Merchant
import com.example.domain.models.MerchantAddressDTO
import org.junit.Assert.assertEquals
import org.junit.Test

class MerchantMapperTest {

    private val merchant = Merchant(
        id = 1,
        name = "name",
        phoneNumber = "123",
        location = LocationDTO(
            address = MerchantAddressDTO(
                street = "street",
                number = "1",
                zipcode = "12",
                city = "Rio",
                country = "Br"
            )
        ),
        reviewScore = "5",
        images = listOf(ImageDTO(
            url = "url"
        ))
    )

    @Test
    fun `Should map merchant to parcelable merchant dto`() {
        val result = MerchantMapper.mapMerchantToParcelableMerchantDTO(merchant)

        result.run {
            assertEquals(id, 1)
            assertEquals(name, "name")
            assertEquals(phoneNumber, "123")
            assertEquals(street, "street")
            assertEquals(number, "1")
            assertEquals(zipcode, "12")
            assertEquals(city, "Rio")
            assertEquals(country, "Br")
            assertEquals(reviewScore, "5")
            assertEquals(images, listOf("url"))
        }
    }
}