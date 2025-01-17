package com.green.glampick.dto.response.user;

import com.green.glampick.common.response.ResponseCode;
import com.green.glampick.common.response.ResponseMessage;
import com.green.glampick.dto.ResponseDto;
import com.green.glampick.entity.ReviewEntity;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class PostReviewResponseDto extends ResponseDto {

    private long reviewId;

    private PostReviewResponseDto(long reviewId) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.reviewId = reviewId;
    }

    public static ResponseEntity<PostReviewResponseDto> success(long reviewId) {
        PostReviewResponseDto result = new PostReviewResponseDto(reviewId);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    public static ResponseEntity<ResponseDto> noExistedUser() {
        ResponseDto result = new ResponseDto(ResponseCode.NOT_EXISTED_USER, ResponseMessage.NOT_EXISTED_USER);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }

    public static ResponseEntity<ResponseDto> validateStarPoint() {
        ResponseDto result = new ResponseDto(ResponseCode.VALIDATION_STAR_POINT, ResponseMessage.VALIDATION_STAR_POINT);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }

    public static ResponseEntity<ResponseDto> noPermission() {
        ResponseDto result = new ResponseDto(ResponseCode.NOT_PERMISSION, ResponseMessage.NOT_PERMISSION);
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(result);
    }

    public static ResponseEntity<ResponseDto> validateUserId() {
        ResponseDto result = new ResponseDto(ResponseCode.CANT_FIND_USER, ResponseMessage.CANT_FIND_USER);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }

    public static ResponseEntity<ResponseDto> reservationIdError() {
        ResponseDto result = new ResponseDto(ResponseCode.RESERVATION_ID_ERROR, ResponseMessage.RESERVATION_ID_ERROR);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }

}
