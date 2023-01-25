package com.example.prueba.service

import com.example.prueba.model.Event
import com.example.prueba.repository.EventRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.springframework.boot.test.context.SpringBootTest


@SpringBootTest
class EventServiceTest {
    @InjectMocks
    lateinit var eventService: EventService

    @Mock
    lateinit var eventRepository: EventRepository

    val eventMock = Event().apply {
        id = 1
        description = "01506825334"
        startDate = "2021-08-11"
        endDate = "2021-09-12"
        totalAttendees=55
        city="Cuenca"
    }
    @Test
    fun saveEventWhenIsCorrect() {
        Mockito.`when`(eventRepository.save(Mockito.any(Event::class.java))).thenReturn(eventMock)
        val response = eventService.save(eventMock)
        Assertions.assertEquals(response.id, eventMock.id)
    }

    @Test
    fun saveEventWhenIsDescriptionIsBlank() {
        Assertions.assertThrows(Exception::class.java) {
            eventMock.apply { description = " " }
            Mockito.`when`(eventRepository.save(Mockito.any(Event::class.java))).thenReturn(eventMock)
            eventService.save(eventMock)
        }
    }

}