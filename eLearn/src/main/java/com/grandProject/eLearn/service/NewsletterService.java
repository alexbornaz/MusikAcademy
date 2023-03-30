package com.grandProject.eLearn.service;

import com.grandProject.eLearn.model.Subscriber;
import com.grandProject.eLearn.repository.SubscriberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class NewsletterService {


    private final EmailSenderService emailSenderService;
    private final SubscriberService subscriberService;

    public NewsletterService( EmailSenderService emailSenderService, SubscriberService subscriberService) {
        this.emailSenderService = emailSenderService;
        this.subscriberService = subscriberService;
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
        List<Subscriber> subscribers = subscriberService.getAllSubscribers();
        String emailContent = generateNewsletterEmailContent();
        for (Subscriber subscriber : subscribers) {
            try {
                emailSenderService.sendNewsletterEmail(subscriber.getEmail(), emailContent);
                log.info("Sent newsletter email to {}", subscriber.getEmail());
            } catch (Exception e) {
                log.error("Could not send email to {}", subscriber.getEmail());
            }
        }
    }
}
