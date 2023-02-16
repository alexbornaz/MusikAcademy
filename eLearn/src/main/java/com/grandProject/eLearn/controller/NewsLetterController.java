package com.grandProject.eLearn.controller;

import com.grandProject.eLearn.dto.response.MessageResponse;
import com.grandProject.eLearn.model.Subscriber;
import com.grandProject.eLearn.service.NewsletterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/newsletter/")
public class NewsLetterController {
    private final NewsletterService newsletterService;

    public NewsLetterController(NewsletterService newsletterService) {
        this.newsletterService = newsletterService;
    }

    @PostMapping("subscribe")
    public ResponseEntity<MessageResponse> subscribeNewsletter(@RequestBody Subscriber subscriber) {
        try {
            newsletterService.addSubscriber(subscriber);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.ok().body(new MessageResponse("Something went wrong try again"));
        }
        return ResponseEntity.ok().body((new MessageResponse("Subscribed with success")));
    }

    @GetMapping("unsubscribe/{email}")
    public void unsubscribeFromNewsletter(@PathVariable String email){
        try {
            newsletterService.removeSubscription(email);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @DeleteMapping("delete/{email}")
    public ResponseEntity<MessageResponse> deleteSubscriber(@PathVariable String email){
        try{
            newsletterService.deleteSubscriber(email);
        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.ok().body(new MessageResponse("Could not delete!"));

        }
        return ResponseEntity.ok().body(new MessageResponse("Subscriber deleted!"));
    }
}
