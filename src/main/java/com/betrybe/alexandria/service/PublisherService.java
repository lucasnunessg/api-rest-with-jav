package com.betrybe.alexandria.service;

import com.betrybe.alexandria.entity.Publisher;
import com.betrybe.alexandria.exception.PublisherNotFoundException;
import com.betrybe.alexandria.repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublisherService {

    private final PublisherRepository publisherRepository;

    @Autowired
    public PublisherService(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

    public Publisher findById(Long id) throws PublisherNotFoundException{
        return publisherRepository.findById((id))
                .orElseThrow(PublisherNotFoundException::new);
    }

    public List<Publisher> findAll() {
        return publisherRepository.findAll();
    }

    public Publisher update(Long id, Publisher publisher) throws PublisherNotFoundException {
        Publisher publisherFromDb = findById(id);

        publisherFromDb.setName(publisher.getName());
        publisherFromDb.setAddress(publisher.getAddress());

        return publisherRepository.save(publisherFromDb);
    }

    public Publisher deleteById(Long id) throws PublisherNotFoundException {
        Publisher publisher = findById(id);

        publisherRepository.deleteById(id);

        return publisher;
    }

    public Publisher create(Publisher publisher ) {
        return publisherRepository.save(publisher);
    }
}
