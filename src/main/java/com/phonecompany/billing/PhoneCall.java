package com.phonecompany.billing;

import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * Model-ovy objekt predstavujici jeden radek vypisu
 */
public class PhoneCall {

    private String phoneNumber;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;

    public PhoneCall(String phoneNumber, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        this.phoneNumber = phoneNumber;
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public LocalDateTime getStartDateTime() {
        return startDateTime;
    }

    public LocalDateTime getEndDateTime() {
        return endDateTime;
    }

    public LocalTime getStartTime() {
        return startDateTime.toLocalTime();
    }

    public LocalTime getEndTime() {
        return endDateTime.toLocalTime();
    }

    public int getMinutes() {
        //todo implement;
        return 0;
    }
}
