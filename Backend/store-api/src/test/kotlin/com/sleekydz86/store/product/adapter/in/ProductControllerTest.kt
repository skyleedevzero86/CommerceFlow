package com.sleekydz86.store.product.adapter.`in`

import com.sleekydz86.helper.BaseIntegrationTest
import com.sleekydz86.store.product.adapter.`in`.request.ProductCreateRequest
import com.sleekydz86.store.product.application.port.out.ProductRepositoryPort
import com.sleekydz86.store.product.fixture.ProductFixture
import io.restassured.RestAssured
import io.restassured.http.ContentType
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import kotlin.test.Test
import kotlin.test.assertEquals

class ProductControllerTest(
    @Autowired
    private val productRepository: ProductRepositoryPort
) : BaseIntegrationTest() {

    @Test
    fun `상품을 저장한다`() {
        // given
        val request = ProductCreateRequest("title", "content", 100, 100)

        // when & then
        RestAssured.given().log().all()
            .header("Authorization", "bearer $token")
            .contentType(ContentType.JSON)
            .body(request)
            .`when`()
            .post("/products")
            .then().log().all()
            .statusCode(HttpStatus.CREATED.value())
    }

    @Test
    fun `상품을 단건 조회한다`() {
        // given
        val savedProduct = productRepository.save(ProductFixture.defaultProduct())

        // when & then
        val response = RestAssured.given().log().all()
            .header("Authorization", "bearer $token")
            .contentType(ContentType.JSON)
            .`when`()
            .get("/products/${savedProduct.id}")
            .then().log().all()
            .extract()

        assertEquals(response.statusCode(), HttpStatus.OK.value())
    }
}
