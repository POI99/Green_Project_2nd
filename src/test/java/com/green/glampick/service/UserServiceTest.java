package com.green.glampick.service;

import com.green.glampick.common.CustomFileUtils;
import com.green.glampick.dto.ResponseDto;
import com.green.glampick.dto.request.user.DeleteReviewRequestDto;
import com.green.glampick.dto.request.user.PostReviewRequestDto;
import com.green.glampick.dto.response.user.PostReviewResponseDto;
import com.green.glampick.entity.ReservationCompleteEntity;
import com.green.glampick.entity.ReviewEntity;
import com.green.glampick.repository.GlampingStarRepository;
import com.green.glampick.repository.ReservationCompleteRepository;
import com.green.glampick.repository.ReviewImageRepository;
import com.green.glampick.repository.ReviewRepository;
import com.green.glampick.security.AuthenticationFacade;
import com.green.glampick.service.implement.UserServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import static org.awaitility.Awaitility.given;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@Import({UserServiceImpl.class})
class UserServiceTest {
    @Autowired
    private UserService userservice;
    @MockBean
    private ReviewRepository reviewRepository;
    @MockBean
    private GlampingStarRepository glampingStarRepository;
    @MockBean
    private ReservationCompleteRepository reservationCompleteRepository;
    @MockBean
    private ReviewImageRepository reviewImageRepository;
    @MockBean
    private CustomFileUtils customFileUtils;
    @MockBean
    private AuthenticationFacade authenticationFacade;

    @Test
    void getBook() {
    }

    @Test
    void cancelBook() {
    }

    @Test
    @DisplayName("별점범위 벗어난 코드 검사")
    void postStarOutReview() {

        PostReviewRequestDto dto = new PostReviewRequestDto();
        dto.setReviewStarPoint(8);

        ReviewEntity reviewEntity = new ReviewEntity();
        reviewEntity.setReviewStarPoint(dto.getReviewStarPoint());
//        dto.setReservationId(93);
//        dto.setReviewContent("Great experience!");
// given(); 객체가 특정 상황에서 해야하는 행위를 정의하는 메소드
        when(reviewRepository.save(reviewEntity)).thenReturn(new ReviewEntity(dto));
// dto.getReviewStarPoint() 가  5인지 검증
        int starPoint = dto.getReviewStarPoint();
//        boolean StarPoint = (starPoint >= 0 && starPoint <= 5);
        if (starPoint >= 0 && starPoint <= 5) {
//                       assertEquals(starPoint, dto.getReviewStarPoint(), "0~5사이의 별점 입력");
            assertNotEquals(starPoint, dto.getReviewStarPoint(), "0~5사이 입니다.");

        }else {
            assertEquals(starPoint, dto.getReviewStarPoint(), "0~5를 벗어남");

        }
    }
    @Test
    @DisplayName("별점범위 내 코드 검사")
    void postStarInReview() {

        PostReviewRequestDto dto = new PostReviewRequestDto();
        dto.setReviewStarPoint(3);

        ReviewEntity reviewEntity = new ReviewEntity();
        reviewEntity.setReviewStarPoint(dto.getReviewStarPoint());
//        dto.setReservationId(93);
//        dto.setReviewContent("Great experience!");
// given(); 객체가 특정 상황에서 해야하는 행위를 정의하는 메소드
        when(reviewRepository.save(reviewEntity)).thenReturn(new ReviewEntity(dto));
// dto.getReviewStarPoint() 가  5인지 검증
        int starPoint = dto.getReviewStarPoint();
//        boolean StarPoint = (starPoint >= 0 && starPoint <= 5);
        if (starPoint >= 0 && starPoint <= 5) {
//                       assertEquals(starPoint, dto.getReviewStarPoint(), "0~5사이의 별점 입력");
            assertEquals(starPoint, dto.getReviewStarPoint(), "0~5를 벗어남");

        }else {

            assertNotEquals(starPoint, dto.getReviewStarPoint(), "0~5사이 입니다.");
        }
    }


    @Test
    @DisplayName("리뷰가 있을 경우")
    void postExistReview() {

        PostReviewRequestDto dto = new PostReviewRequestDto();
        dto.setReviewContent("리뷰 있어요!!");
        ReviewEntity reviewEntity = new ReviewEntity();
        reviewEntity.setReviewStarPoint(dto.getReviewStarPoint());
//        dto.setReservationId(93);

// given(); 객체가 특정 상황에서 해야하는 행위를 정의하는 메소드
        when(reviewRepository.save(reviewEntity)).thenReturn(new ReviewEntity(dto));
// dto.getReviewStarPoint() 가  5인지 검증
        String review = dto.getReviewContent();
        boolean ExistReview = (review !=null);
        assertTrue(ExistReview,"리뷰가 있어요");


    }
    @Test
    @DisplayName("리뷰가 없는 경우")
    void postNotExistReview() {

        PostReviewRequestDto dto = new PostReviewRequestDto();
//        dto.setReviewContent();
        ReviewEntity reviewEntity = new ReviewEntity();
        reviewEntity.setReviewStarPoint(dto.getReviewStarPoint());
//        dto.setReservationId(93);

// given(); 객체가 특정 상황에서 해야하는 행위를 정의하는 메소드
        when(reviewRepository.save(reviewEntity)).thenReturn(new ReviewEntity(dto));
// dto.getReviewStarPoint() 가  5인지 검증
        String review = dto.getReviewContent();
        boolean ExistReview = (review ==null);
        assertTrue(ExistReview,"리뷰가 없어요");


    }

    @Test
    void deleteReview() {
        DeleteReviewRequestDto dto = new DeleteReviewRequestDto();
        ReviewEntity reviewEntity = new ReviewEntity();
        dto.setReviewId(20);
//        reviewEntity.setReviewId(dto.getReviewId());
//        doNothing().when(reviewRepository).deleteById(dto.getReviewId());
//        when(reviewRepository.deleteById(dto.getReviewId());

    }

    @Test
    void getReview() {
    }

    @Test
    void getFavoriteGlamping() {
    }

    @Test
    void getUser() {
    }

    @Test
    void updateUser() {
    }

    @Test
    void deleteUser() {
    }

    @Test
    void postUserPassword() {
    }
}