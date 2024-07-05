package com.green.glampick.dto.response.user;

import com.green.glampick.common.response.ResponseCode;
import com.green.glampick.common.response.ResponseMessage;
import com.green.glampick.dto.ResponseDto;
import com.green.glampick.repository.resultset.GetFavoriteGlampingResultSet;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class GetFavoriteGlampingListResponseDto extends ResponseDto {

//    private long glampId;
//    private String glampingName;
//    private String region;
//    private int starPoint;
//    private int reviewCount;
//    private int price;
//    private String glampingImg;

    private List<GetFavoriteGlampingResultSet> favoritelist;

    private GetFavoriteGlampingListResponseDto(List<GetFavoriteGlampingResultSet> favoritelist) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.favoritelist = favoritelist;
    }

    public static ResponseEntity<ResponseDto> success(List<GetFavoriteGlampingResultSet> favoritelist) {
        GetFavoriteGlampingListResponseDto result = new GetFavoriteGlampingListResponseDto(favoritelist);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    public static ResponseEntity<ResponseDto> noExistedUser() {
        ResponseDto result = new ResponseDto(ResponseCode.NOT_EXISTED_USER, ResponseMessage.NOT_EXISTED_USER);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }

    public static ResponseEntity<ResponseDto> noExistedGlamp() {
        ResponseDto result = new ResponseDto(ResponseCode.NOT_EXISTED_GLAMP, ResponseMessage.NOT_EXISTED_GLAMP);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }

    public static ResponseEntity<ResponseDto> noPermission() {
        ResponseDto result = new ResponseDto(ResponseCode.NOT_PERMISSION, ResponseMessage.NOT_PERMISSION);
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(result);
    }

}
