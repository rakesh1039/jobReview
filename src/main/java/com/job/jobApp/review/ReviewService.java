package com.job.jobApp.review;

import java.util.List;

public interface ReviewService{
    List<Review> getAllReview(Long companyId);

    boolean createReview(Review review, Long companyId);

    Review getReviewById(Long companyId, Long reviewId);

    boolean updateReview(Long companyId, Long reviewId, Review updateReview);

    boolean deleteReview(Long companyId, Long reviewId);
}
