package com.green.glampick.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "reservation_before")
@Table(name = "reservation_before")
public class ReservationBeforeEntity {
    //예약 테이블
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long reservationId;  //예약 ID

    private long userId;  //유저 ID

    private long glampId;

    private long roomId;  //객실 ID

    private String inputName;  //예약자 성함

    private String checkInDate;  //체크인 일자

    private String checkOutDate;  //체크아웃 일자

    private long reservationAmount;  //최종 결제 가격

    private String createdAt;  //예약 일자



}