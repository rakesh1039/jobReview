package com.job.jobApp.review.impl;

import com.job.jobApp.company.Company;
import com.job.jobApp.company.CompanyService;
import com.job.jobApp.review.Review;
import com.job.jobApp.review.ReviewRepository;
import com.job.jobApp.review.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Locale.filter;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final CompanyService companyService;

    public ReviewServiceImpl(ReviewRepository reviewRepository, CompanyService companyService) {
        this.reviewRepository = reviewRepository;
        this.companyService = companyService;
    }

    @Override
    public List<Review> getAllReview(Long companyId) {
        List<Review> reviews = reviewRepository.findByCompanyId(companyId);
        return reviews;
    }

    @Override
    public boolean createReview(Review review, Long companyId) {
        Company company = companyService.getCompanyById(companyId);
        if(company != null) {
            review.setCompany(company);
            reviewRepository.save(review);
            return  true;
        }
        return false;
    }

    @Override
    public Review getReviewById(Long companyId, Long reviewId) {
        List<Review> reviews =  reviewRepository.findByCompanyId(companyId);
        /*for (Review review : reviews) {
            if(review.getId().equals(reviewId)) {
                System.out.println(review.getTitle());
            }
        }*/
        return reviews.stream()
                .filter(review -> review.getId().equals(reviewId))
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean updateReview(Long companyId, Long reviewId, Review updateReview) {
        if(companyService.getCompanyById(companyId) != null && reviewRepository.findById(reviewId).isPresent()) {
            updateReview.setCompany(companyService.getCompanyById(companyId));
            updateReview.setId(reviewId);
            reviewRepository.save(updateReview);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteReview(Long companyId, Long reviewId) {
        try {
            if(companyService.getCompanyById(companyId) != null && reviewRepository.findById(reviewId).isPresent()) {
                Review review = reviewRepository.findById(reviewId).orElse(null);
                Company company = review.getCompany();
                company.getReviews().remove(review);
                review.setCompany(null);
                companyService.updateCompany(companyId, company);
                reviewRepository.deleteById(reviewId);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }
}
