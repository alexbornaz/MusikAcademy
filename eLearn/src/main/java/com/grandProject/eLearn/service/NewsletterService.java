package com.grandProject.eLearn.service;

import com.grandProject.eLearn.model.Subscriber;
import com.grandProject.eLearn.repository.NewsletterRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
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
        log.info("{} added to subscribers",subscriber.getEmail());
        newsletterRepository.save(subscriber);
    }

    public void deleteSubscriber(String email){
        if (newsletterRepository.existsByEmail(email)){
            Subscriber subscriber = newsletterRepository.findByEmail(email);
            newsletterRepository.delete(subscriber);
            log.info("{} deleted from subscribers", email);
        }
    }
    public void removeSubscription(String email) {
        if (newsletterRepository.existsByEmail(email)) {
            Subscriber sub = newsletterRepository.findByEmail(email);
            sub.unsubscribe();
            newsletterRepository.save(sub);
            log.info("{} unsubscribed from newsletter", email);
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
                    log.info("Sent newsletter email to {}",subscriber.getEmail());
                } catch (Exception e) {
                    log.error("Could not send email to {}",subscriber.getEmail());
                }
            }
        }
    }
}
