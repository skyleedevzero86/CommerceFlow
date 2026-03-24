package com.sleekydz86.helper

import com.sleekydz86.store.StoreApiApplication
import com.sleekydz86.store.auth.application.port.out.AuthRepositoryPort
import com.sleekydz86.store.auth.application.port.out.TokenProviderPort
import com.sleekydz86.store.auth.domain.Auth
import com.sleekydz86.store.auth.domain.service.AuthPasswordEncryptor
import io.restassured.RestAssured
import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
    classes = [StoreApiApplication::class]
)
class BaseIntegrationTest : IntegrationHelper() {

    @Autowired
    private lateinit var authRepository: AuthRepositoryPort

    @Autowired
    private lateinit var authPasswordEncryptor: AuthPasswordEncryptor

    @Autowired
    private lateinit var tokenProviderPort: TokenProviderPort

    protected lateinit var auth: Auth
    protected lateinit var token: String

    @BeforeEach
    override fun init() {
        super.init()
        setupTestUser()
    }

    private fun setupTestUser() {
        auth = authRepository.save(
            Auth.signUpWithEncryption(
                username = "testuser",
                password = "password",
                authPasswordEncryptor = authPasswordEncryptor
            )
        )
        token = tokenProviderPort.create(auth.id)
    }
}
