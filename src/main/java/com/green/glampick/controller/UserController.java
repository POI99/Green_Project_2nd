package com.green.glampick.controller;

import com.green.glampick.dto.request.user.*;
import com.green.glampick.dto.response.login.PostSignUpResponseDto;
import com.green.glampick.dto.response.user.*;
import com.green.glampick.entity.ReviewImageEntity;
import com.green.glampick.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static com.green.glampick.common.swagger.description.login.PostSignUpSwaggerDescription.SIGN_UP_RESPONSE_ERROR_CODE;
import static com.green.glampick.common.swagger.description.user.DeleteUserReviewSwaggerDescription.USER_REVIEW_REMOVE_DESCRIPTION;
import static com.green.glampick.common.swagger.description.user.DeleteUserReviewSwaggerDescription.USER_REVIEW_REMOVE_RESPONSE_ERROR_CODE;
import static com.green.glampick.common.swagger.description.user.GetUserBookSwaggerDescription.USER_BOOK_DESCRIPTION;
import static com.green.glampick.common.swagger.description.user.GetUserBookSwaggerDescription.USER_BOOK_RESPONSE_ERROR_CODE;
import static com.green.glampick.common.swagger.description.user.GetUserReviewSwaggerDescription.USER_REVIEW_VIEW_DESCRIPTION;
import static com.green.glampick.common.swagger.description.user.GetUserReviewSwaggerDescription.USER_REVIEW_VIEW_RESPONSE_ERROR_CODE;
import static com.green.glampick.common.swagger.description.user.PostUserBookCancelSwaggerDescription.USER_BOOK_CANCEL_DESCRIPTION;
import static com.green.glampick.common.swagger.description.user.PostUserBookCancelSwaggerDescription.USER_BOOK_CANCEL_RESPONSE_ERROR_CODE;
import static com.green.glampick.common.swagger.description.user.PostUserReviewSwaggerDescription.USER_REVIEW_DESCRIPTION;
import static com.green.glampick.common.swagger.description.user.PostUserReviewSwaggerDescription.USER_REVIEW_RESPONSE_ERROR_CODE;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
@Tag(name = "유저 컨트롤러")
public class UserController {
    private final UserService service;

    //  유저 페이지 - 예약 내역 불러오기  //
    @GetMapping("/book")
    @Operation(summary = "예약내역 불러오기", description = USER_BOOK_DESCRIPTION)
    @ApiResponse(responseCode = "200", description = USER_BOOK_RESPONSE_ERROR_CODE,
        content = @Content(
                mediaType = "application/json", schema = @Schema(implementation = PostSignUpResponseDto.class)))
    public ResponseEntity<?super GetBookResponseDto> getBook(@ParameterObject GetBookRequestDto dto) {
        return service.getBook(dto);
    }

    //  유저 페이지 - 예약 내역 취소하기  //
    @PostMapping("/book-cancel")
    @Operation(summary = "예약내역 취소하기", description = USER_BOOK_CANCEL_DESCRIPTION)
    @ApiResponse(responseCode = "200", description = USER_BOOK_CANCEL_RESPONSE_ERROR_CODE,
        content = @Content(
                mediaType = "application/json", schema = @Schema(implementation = PostSignUpResponseDto.class)))
    public ResponseEntity<?super CancelBookResponseDto> cancelBook(@RequestBody CancelBookRequestDto dto) {
        return service.cancelBook(dto);
    }

    //  유저 페이지 - 리뷰 작성하기  //
    @PostMapping("/review")
    @Operation(summary = "리뷰 작성하기", description = USER_REVIEW_DESCRIPTION)
    @ApiResponse(responseCode = "200", description = USER_REVIEW_RESPONSE_ERROR_CODE,
        content = @Content(
                mediaType = "application/json", schema = @Schema(implementation = PostReviewResponseDto.class)))
    public ResponseEntity<?super PostReviewResponseDto> postReview(@RequestPart PostReviewRequestDto dto, @RequestPart List<MultipartFile> mf) {
        return service.postReview(dto, mf);
    }

    //  유저 페이지 - 리뷰 삭제하기  //
    @DeleteMapping("/delete")
    @Operation(summary = "리뷰 삭제하기", description = USER_REVIEW_REMOVE_DESCRIPTION)
    @ApiResponse(responseCode = "200", description = USER_REVIEW_REMOVE_RESPONSE_ERROR_CODE,
            content = @Content(
                    mediaType = "application/json", schema = @Schema(implementation = DeleteReviewResponseDto.class)))
    public ResponseEntity<?super DeleteReviewResponseDto> deleteReview(@ParameterObject DeleteReviewRequestDto dto) {
        return service.deleteReview(dto);

    }

    //  유저 페이지 - 리뷰 불러오기  //
    @GetMapping("/review")
    @Operation(summary = "리뷰 불러오기", description = USER_REVIEW_VIEW_DESCRIPTION)
    @ApiResponse(responseCode = "200", description = USER_REVIEW_VIEW_RESPONSE_ERROR_CODE,
            content = @Content(
                    mediaType = "application/json", schema = @Schema(implementation = PostReviewResponseDto.class)))
    public ResponseEntity<?super GetReviewResponseDto> getReview(@ParameterObject @ModelAttribute GetReviewRequestDto dto) {
        return service.getReview(dto);
    }

    @GetMapping("/favorite-glamping")// 관심 글램핑 불러오기
    @Operation(summary = "관심 글램핑 불러오기", description = "<strong></strong>" +
            "<p></p>")
    public ResponseEntity<?super GetFavoriteGlampingResponseDto> getFavoriteGlamping(@ParameterObject @ModelAttribute GetFavoriteGlampingRequestDto dto) {
        return service.getFavoriteGlamping(dto);
    }

//    @GetMapping("/coupon")// 쿠폰 불러오기
//    public ResponseEntity<?super PostReviewResponseDto> getCoupon(@ParameterObject @ModelAttribute String email) {
//        ResponseEntity<? super PostReviewResponseDto> response = service.getCoupon(email);
//        return response;
//    }

    @GetMapping("")// 유저 정보 불러오기
    @Operation(summary = "유저 정보 불러오기", description = "<strong></strong>" +
            "<p></p>")
    public ResponseEntity<?super GetUserResponseDto> getUser(@ParameterObject GetUserRequestDto dto) {
        return service.getUser(dto);
    }

    @PutMapping// 유저 정보 수정하기
    @Operation(summary = "유저 정보 수정하기", description = "<strong></strong>" +
            "<p></p>")
    public ResponseEntity<?super UpdateUserResponseDto> updateUser
            (@RequestPart UpdateUserRequestDto dto, @RequestPart  MultipartFile mf)
    {
        return service.updateUser(dto, mf);
    }

    @DeleteMapping("")// 회원탈퇴
    @Operation(summary = "회원 탈퇴", description = "<strong></strong>" +
            "<p></p>")
    public ResponseEntity<?super DeleteUserResponseDto> deleteUser(@ParameterObject DeleteUserRequestDto dto) {
        return service.deleteUser(dto);
    }

}

