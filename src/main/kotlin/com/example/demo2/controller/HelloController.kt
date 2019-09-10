package com.example.demo2.controller

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.annotation.PostConstruct
import javax.persistence.*
import javax.transaction.Transactional

@RestController
@RequestMapping("/hello")
class HelloController(
        val sampleComponent: SampleComponent,
        val sampleService: SampleService) {

    @PostConstruct
    fun postConstruct() {
        sampleService.init()
    }

    @GetMapping
    fun get(): HelloDto {
        return HelloDto(sampleComponent.hello())
    }
}

data class HelloDto(
        val greeting: String
)


@Component
class SampleComponent(
        val sampleService: SampleService
) {
    fun hello(): String = "${sampleService.hello()} ${sampleService.world()} ${sampleService.name()}"
}

@Service
@Transactional
class SampleService(
        val sampleRepository: SampleRepository
) {
    fun hello(): String = "Hello"
    fun world(): String = "World"

    fun name(): String {
        val sample = sampleRepository.findById("Koo").get()
        sample.child.kind = ""
        sampleRepository.save(sample)
        return sample.name
    }

    fun init() {
        sampleRepository.save(Sample("Koo", Child("human")))
    }
}

interface SampleRepository : JpaRepository<Sample, String>

@Entity
data class Sample(
        @Id val name: String,
        @ManyToOne(cascade = [CascadeType.ALL])
        val child: Child
)

@Entity
data class Child(
        var kind: String
) {
    @Id
    @GeneratedValue
    val id: Int = 0
}
