package com.green.glampick.service;

import com.green.glampick.common.CustomFileUtils;
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
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@Import({UserServiceImpl.class })
class UserServiceTest {
    @Autowired private UserService userservice;
    @MockBean private ReviewRepository reviewRepository;
    @MockBean private GlampingStarRepository glampingStarRepository;
    @MockBean private ReservationCompleteRepository reservationCompleteRepository;
    @MockBean private ReviewImageRepository reviewImageRepository;
    @MockBean private CustomFileUtils customFileUtils;
    @MockBean private AuthenticationFacade authenticationFacade;

    @Test
    void getBook() {
    }

    @Test
    void cancelBook() {
    }

    @Test
    @DisplayName("별점")
    void postReview() {

        PostReviewRequestDto dto = new PostReviewRequestDto();

        ReviewEntity reviewEntity = new ReviewEntity();
        dto.setReviewStarPoint(5);
        dto.setReservationId(93);
        dto.setReviewContent("Great experience!");
// given(); 객체가 특정 상황에서 해야하는 행위를 정의하는 메소드
        when(reviewRepository.save(reviewEntity)).thenReturn(new ReviewEntity(dto));

    }
    @Test
    void deleteReview() {
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