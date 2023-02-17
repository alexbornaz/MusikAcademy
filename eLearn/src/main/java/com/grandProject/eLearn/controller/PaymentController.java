package com.grandProject.eLearn.controller;

import com.grandProject.eLearn.dto.request.ChargeRequest;
import com.grandProject.eLearn.dto.response.MessageResponse;
import com.grandProject.eLearn.model.course.Course;
import com.grandProject.eLearn.service.StripeService;
import com.grandProject.eLearn.service.course.CourseService;
import com.stripe.exception.StripeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/payment")
@Slf4j
public class PaymentController {
    private final StripeService stripeService;
    private final CourseService courseService;

    public PaymentController(StripeService stripeService, CourseService courseService) {
        this.stripeService = stripeService;
        this.courseService = courseService;
    }

    @PostMapping("/charge")
    public ResponseEntity<?> chargeCard(@RequestBody ChargeRequest chargeRequest) throws StripeException {
        Course course = courseService.getValidCourseById(chargeRequest.getCourseId());
        log.info("Charge request for course with id {}", chargeRequest.getCourseId());
        String paymentIntentId = stripeService.createPaymentIntent(course.getDefaultPrice().longValue(),chargeRequest.getToken());
        return ResponseEntity.ok().body(new MessageResponse(paymentIntentId));
    }

}
