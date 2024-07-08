package com.green.glampick.service;

import com.green.glampick.dto.request.owner.GlampingPostRequestDto;
import com.green.glampick.dto.request.ReviewPatchRequestDto;
import com.green.glampick.dto.request.ReviewPostRequestDto;
import com.green.glampick.dto.request.owner.RoomPostRequestDto;
import com.green.glampick.dto.response.owner.*;
import com.green.glampick.dto.response.owner.get.GetOwnerBookListResponseDto;
import com.green.glampick.dto.response.owner.post.PostGlampingInfoResponseDto;
import com.green.glampick.dto.response.owner.post.PostRoomInfoResponseDto;
import com.green.glampick.dto.response.owner.put.PutGlampingInfoResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface OwnerService {

    ResponseEntity<? super PostGlampingInfoResponseDto> postGlampingInfo(GlampingPostRequestDto glampingPostRequestDtoReq, MultipartFile glampImg);
    ResponseEntity<? super PostRoomInfoResponseDto> postRoomInfo(RoomPostRequestDto req, List<MultipartFile> img);

    ResponseEntity<? super PutGlampingInfoResponseDto> updateGlampingInfo(GlampingPostRequestDto req, long glampId);

    ResponseEntity<? super GetOwnerBookListResponseDto> getGlampReservation(long glampId);

    ResponseEntity<? super PostOwnerReviewInfoResponseDto> postReview(ReviewPostRequestDto p);
    ResponseEntity<? super PatchOwnerReviewInfoResponseDto> patchReview(ReviewPatchRequestDto p);
}
