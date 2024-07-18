package com.green.glampick.repository.resultset;

public interface GetReservationCompleteResultSet {

    String getGlampName();
    String getGlampImage();
    long getGlampId();
    String getBookId();
    String getRoomName();
    long getReservationId();
    String getCheckInDate();
    String getCheckOutDate();
    String getCreatedAt();
    String getCheckInTime();
    String getCheckOutTime();

}
