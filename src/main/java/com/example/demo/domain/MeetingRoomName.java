package com.example.demo.domain;

public class MeetingRoomName {
    private final String value;

    public MeetingRoomName(String value) {
        this.value = value;
    }

    public String stringValue() {
        return value;
    }

    @Override
    public boolean equals(Object obj) {
        MeetingRoomName meetingRoomName = (MeetingRoomName) obj;
        return this.value.equals(meetingRoomName.stringValue());
    }
}
