package com.grandProject.eLearn.appConfig;

import com.grandProject.eLearn.service.NewsletterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
@Slf4j
public class AppConfig {
    private final NewsletterService newsletterService;

    public AppConfig(NewsletterService newsletterService) {
        this.newsletterService = newsletterService;
    }
//second,minute,hour,day
    @Scheduled(cron = "0 0 0 * * ?")
    public void sendEmails(){
        log.info("Sending newsletter emails to subscribers");
        newsletterService.sendNewsletter();
    }

}
