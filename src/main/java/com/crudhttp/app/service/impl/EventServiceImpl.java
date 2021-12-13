package com.crudhttp.app.service.impl;

import com.crudhttp.app.model.Event;
import com.crudhttp.app.repository.impl.EventRepositoryImpl;
import com.crudhttp.app.service.EventService;

import java.util.List;

public class EventServiceImpl implements EventService {
    private final EventRepositoryImpl eventRepository;

    public EventServiceImpl() {
        eventRepository = new EventRepositoryImpl();
    }

    public EventServiceImpl(EventRepositoryImpl eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public Event getById(int id) {
        return eventRepository.getById(id);
    }

    @Override
    public List<Event> getAll() {
        return eventRepository.getAll();
    }

    @Override
    public Event save(Event event) {
        return eventRepository.save(event);
    }

    @Override
    public Event update(Event event) {
        return eventRepository.update(event);
    }

    @Override
    public void deleteById(int id) {
        eventRepository.deleteById(id);
    }
}
