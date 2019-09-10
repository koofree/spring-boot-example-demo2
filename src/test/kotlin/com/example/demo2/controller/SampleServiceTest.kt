package com.example.demo2.controller

import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
class SampleServiceTest {

    @Autowired
    lateinit var sampleService: SampleService

    @Test
    fun `이름이 정상적으로 나오는지 확인`() {
        assertEquals(sampleService.name(), "Koo")
    }

    @Test
    fun world() {
    }
}
