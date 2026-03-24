package com.sleekydz86.store.product.application

import com.sleekydz86.store.product.application.port.`in`.command.ProductCreateCommand
import com.sleekydz86.store.product.application.port.out.ProductRepositoryPort
import com.sleekydz86.store.product.fixture.ProductFixture
import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.core.spec.style.BehaviorSpec
import io.mockk.every
import io.mockk.mockk

class ProductCommandServiceTest : BehaviorSpec({

    val productRepositoryPort: ProductRepositoryPort = mockk()
    val productCommandService = ProductCommandService(productRepositoryPort)

    val title = "title"
    val content = "content"
    val price = 100
    val quantity = 100
    val command = ProductCreateCommand(title, content, price, quantity)

    Given("상품을 생성시") {
        When("예외가 없다면") {
            every {
                productRepositoryPort.save(
                    match {
                        it.title == title && it.content == content &&
                            it.price.value == price && it.quantity.value == quantity
                    }
                )
            } returns ProductFixture.defaultProduct()

            Then("정상 생성된다") {
                shouldNotThrowAny {
                    productCommandService.createProduct(command)
                }
            }
        }
    }
})
