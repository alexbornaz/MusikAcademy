package com.grandProject.eLearn.service;

import com.grandProject.eLearn.model.Subscriber;
import com.grandProject.eLearn.repository.NewsletterRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NewsletterService {

    private final NewsletterRepository newsletterRepository;

    private final EmailSenderService emailSenderService;

    public NewsletterService(NewsletterRepository newsletterRepository, EmailSenderService emailSenderService) {
        this.newsletterRepository = newsletterRepository;
        this.emailSenderService = emailSenderService;
    }

    private List<Subscriber> getAllSubscribers() {
        List<Subscriber> subscriberList = newsletterRepository.findAll();
        return new ArrayList<>(subscriberList);
    }

    public void addSubscriber(Subscriber subscriber) {
        newsletterRepository.save(subscriber);
    }

    public void deleteSubscriber(String email){
        if (newsletterRepository.existsByEmail(email)){
            Subscriber subscriber = newsletterRepository.findByEmail(email);
            newsletterRepository.delete(subscriber);
        }
    }
    public void removeSubscription(String email) {
        if (newsletterRepository.existsByEmail(email)) {
            Subscriber sub = newsletterRepository.findByEmail(email);
            sub.unsubscribe();
            newsletterRepository.save(sub);
        }
    }

    private String generateNewsletterEmailContent() {
        String greeting = "Hello!";
        String introduction = "Here's your daily newsletter.";
        String content = "Today's new courses:\n";
        //here the main content can be added accordingly.
        String conclusion = "Thanks for subscribing!";
        return greeting + "\n\n" + introduction + "\n\n" + content + "\n" + conclusion;
    }

    public void sendNewsletter() {
        List<Subscriber> subscribers = getAllSubscribers();
        String emailContent = generateNewsletterEmailContent();
        if (subscribers.size() > 0) {
            for (Subscriber subscriber : subscribers) {
                try {
                    emailSenderService.sendNewsletterEmail(subscriber.getEmail(), emailContent);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
