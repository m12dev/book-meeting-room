package com.example.demo.request;

import com.example.demo.domain.Meeting;
import com.example.demo.domain.MeetingDelegate;
import com.example.demo.domain.MeetingName;
import com.fasterxml.jackson.annotation.JsonProperty;

public class MeetingRequest {
    @JsonProperty("name")
    private String meetingName;
    @JsonProperty("delegate")
    private String meetingDelegate;

    public String getName() {
        return meetingName;
    }

    public String getDelegate() {
        return meetingDelegate;
    }

    public Meeting getMeeting() {
        return new Meeting(new MeetingName(meetingName), new MeetingDelegate(meetingDelegate));
    }
}
