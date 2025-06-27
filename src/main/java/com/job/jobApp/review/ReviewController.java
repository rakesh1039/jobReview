package com.job.jobApp.review;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies/{companyId}")
public class ReviewController {
    private ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/reviews")
    public ResponseEntity<List<Review>> getAllReview(@PathVariable Long companyId) {
        return new ResponseEntity<>(reviewService.getAllReview(companyId), HttpStatus.OK);
    }

    @PostMapping("/reviews")
    public ResponseEntity<String> createReview(@PathVariable Long companyId, @RequestBody Review review) {
        boolean isReview = reviewService.createReview(review, companyId);
        if(isReview) {
            return new ResponseEntity<>("Review is added successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Review is not added", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/reviews/{reviewId}")
    public ResponseEntity<Review> getReviewById(@PathVariable Long companyId, @PathVariable Long reviewId) {
        Review isReview = reviewService.getReviewById(companyId, reviewId);
        if(isReview != null) {
            return new ResponseEntity<>(isReview, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/reviews/{reviewId}")
    public ResponseEntity<String> updateReview(@PathVariable Long companyId, @PathVariable Long reviewId, @RequestBody Review updateReview) {
        boolean isUpdated = reviewService.updateReview(companyId, reviewId, updateReview);
        if(isUpdated) {
            return new ResponseEntity<>("Review is updated successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Review is not updated", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/reviews/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable Long companyId, @PathVariable Long reviewId) {
        boolean isDeleted = reviewService.deleteReview(companyId, reviewId);
        if(isDeleted) {
            return new ResponseEntity<>("Review is deleted successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Review is not deleted", HttpStatus.NOT_FOUND);
    }
}
