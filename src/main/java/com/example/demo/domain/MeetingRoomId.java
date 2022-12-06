package com.example.demo.domain;

public class MeetingRoomId {
    private final String value;

    public MeetingRoomId(String value) {
        this.value = value;
    }

    public String stringValue() {
        return value;
    }

    @Override
    public boolean equals(Object obj) {
        MeetingRoomId meetingRoomId = (MeetingRoomId) obj;
        return this.value.equals(meetingRoomId.stringValue());
    }
}
