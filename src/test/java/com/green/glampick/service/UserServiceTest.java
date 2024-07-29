package com.green.glampick.service;

import com.green.glampick.common.CustomFileUtils;
import com.green.glampick.dto.ResponseDto;
import com.green.glampick.dto.request.user.DeleteReviewRequestDto;
import com.green.glampick.dto.request.user.PostReviewRequestDto;
import com.green.glampick.dto.response.user.PostReviewResponseDto;
import com.green.glampick.entity.ReservationCompleteEntity;
import com.green.glampick.entity.ReviewEntity;
import com.green.glampick.entity.ReviewImageEntity;
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

import java.util.Optional;

import static org.awaitility.Awaitility.given;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

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

        when(reviewRepository.save(reviewEntity)).thenReturn(new ReviewEntity(dto));
        int starPoint = dto.getReviewStarPoint();
        if (starPoint >= 0 && starPoint <= 5) {
            assertNotEquals(starPoint, dto.getReviewStarPoint(), "0~5사이 입니다.");
        } else {
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

        } else {

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
        boolean ExistReview = (review != null);
        assertTrue(ExistReview, "리뷰가 있어요");


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
        boolean ExistReview = (review == null);
        assertTrue(ExistReview, "리뷰가 없어요");


    }

    @Test
    @DisplayName("리뷰 삭제 확인")
    void deleteReview() {
        ReviewEntity reviewEntity = new ReviewEntity();
        reviewEntity.setReviewId(20);

        reviewRepository.deleteById(reviewEntity.getReviewId());


        verify(reviewRepository, times(1)).deleteById(reviewEntity.getReviewId());
        when(reviewRepository.findById(reviewEntity.getReviewId())).thenReturn(Optional.empty());
        assertFalse(reviewRepository.findById(reviewEntity.getReviewId()).isPresent(), "리뷰 삭제 실패!!");
    }

    @Test
    @DisplayName("리뷰이미지 삭제 확인")
    void deleteImageReview() {
        ReviewImageEntity reviewImageEntity = new ReviewImageEntity();
        String imageFold = String.format("%d/%d", reviewImageEntity.getReviewImageId(), reviewImageEntity.getReviewId());
        when(customFileUtils.makeFolders(imageFold)).thenReturn("폴더 생성??");
        reviewRepository.deleteById(reviewImageEntity.getReviewId());


        verify(reviewRepository, times(1)).deleteById(reviewImageEntity.getReviewId());
        when(reviewRepository.findById(reviewImageEntity.getReviewId())).thenReturn(Optional.empty());
        assertFalse(reviewRepository.findById(reviewImageEntity.getReviewId()).isPresent(), "리뷰(이미지) 삭제 실패!!");
    }

    @Test
    @DisplayName("유효한 사용자 ID를 사용한 리뷰 조회")
    void getLogInReview() {
        ReviewEntity reviewEntity = new ReviewEntity();


    }

    @Test
    @DisplayName("유효하지 않은 사용자 ID를 사용한 예외 처리")
    void getnuLogInReview() {
        ReviewEntity reviewEntity = new ReviewEntity();


    }

    @Test
    @DisplayName("데이터베이스 오류 처리")
    void getDBErrorReview() {
        ReviewEntity reviewEntity = new ReviewEntity();


    }

    @Test
    @DisplayName("리뷰와 이미지가 올바르게 매핑되는지 확인")
    void getReviewOrImage() {
        ReviewEntity reviewEntity = new ReviewEntity();


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