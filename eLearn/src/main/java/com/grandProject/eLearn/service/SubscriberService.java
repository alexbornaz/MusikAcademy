package com.grandProject.eLearn.service;

import com.grandProject.eLearn.model.Subscriber;
import com.grandProject.eLearn.repository.SubscriberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class SubscriberService {
    private final SubscriberRepository subscriberRepository;


    public SubscriberService(SubscriberRepository subscriberRepository) {
        this.subscriberRepository = subscriberRepository;
    }

    public List<Subscriber> getAllSubscribers() {
        List<Subscriber> subscriberList = subscriberRepository.findAll();
        return new ArrayList<>(subscriberList);
    }

    public void addSubscriber(Subscriber subscriber) {
        log.info("{} added to subscribers", subscriber.getEmail());
        subscriberRepository.save(subscriber);
    }

    public void deleteSubscriber(String email) {
        if (subscriberRepository.existsByEmail(email)) {
            Subscriber subscriber = subscriberRepository.findByEmail(email);
            subscriberRepository.delete(subscriber);
            log.info("{} deleted from subscribers", email);
        }
    }

    public void removeSubscription(String email) {
        if (subscriberRepository.existsByEmail(email)) {
            Subscriber sub = subscriberRepository.findByEmail(email);
            sub.unsubscribe();
            subscriberRepository.save(sub);
            log.info("{} unsubscribed from newsletter", email);
        }
    }
}
