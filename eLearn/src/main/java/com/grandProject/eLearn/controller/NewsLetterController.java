package com.grandProject.eLearn.controller;

import com.grandProject.eLearn.dto.response.MessageResponse;
import com.grandProject.eLearn.model.Subscriber;
import com.grandProject.eLearn.service.NewsletterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/newsletter/")
@Slf4j
public class NewsLetterController {
    private final NewsletterService newsletterService;

    public NewsLetterController(NewsletterService newsletterService) {
        this.newsletterService = newsletterService;
    }

    @PostMapping("subscribe")
    public ResponseEntity<MessageResponse> subscribeNewsletter(@RequestBody Subscriber subscriber) {
        try {
            newsletterService.addSubscriber(subscriber);
            log.info("Request to add subscriber to newsletter database with email {}",subscriber.getEmail() );
        }catch (Exception e){
            log.error("Could not add email to subscribers");
            return ResponseEntity.ok().body(new MessageResponse("Something went wrong try again"));
        }
        log.info("{} subscribed to newsletter",subscriber.getEmail());
        return ResponseEntity.ok().body((new MessageResponse("Subscribed with success")));
    }

    @GetMapping("unsubscribe/{email}")
    public void unsubscribeFromNewsletter(@PathVariable String email){
        try {
            newsletterService.removeSubscription(email);
            log.info("{} unsubscribed from newsletter",email);
        }catch (Exception e){
            log.error("{} could not unsubscribe",email);
        }
    }

    @DeleteMapping("delete/{email}")
    public ResponseEntity<MessageResponse> deleteSubscriber(@PathVariable String email){
        try{
            newsletterService.deleteSubscriber(email);
            log.info("Request to delete {} from subscribers database", email);
        }catch (Exception e){
            log.error("{} could not be removed from database",email);
            return ResponseEntity.ok().body(new MessageResponse("Could not delete!"));

        }
        return ResponseEntity.ok().body(new MessageResponse("Subscriber deleted!"));
    }
}
