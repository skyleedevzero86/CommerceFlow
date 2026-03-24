package com.sleekydz86.helper

import com.sleekydz86.store.auth.application.AuthService
import com.sleekydz86.store.auth.application.port.out.TokenProviderPort
import com.sleekydz86.store.global.config.auth.support.AuthenticationContext
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext
import org.springframework.test.context.bean.override.mockito.MockitoBean

class MockBeanInjection() {

    @MockitoBean
    protected lateinit var jpaMetamodelMappingContext: JpaMetamodelMappingContext

    @MockitoBean
    protected lateinit var tokenProvider: TokenProviderPort

    @MockitoBean
    protected lateinit var authenticationContext: AuthenticationContext

    @MockitoBean
    protected lateinit var authService: AuthService
}
