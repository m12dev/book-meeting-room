package com.example.demo.domain;

public class MeetingName {
    private final String value;

    public MeetingName(String value) {
        this.value = value;
    }

    public String stringValue() {
        return value;
    }

    @Override
    public boolean equals(Object obj) {
        MeetingName meetingName = (MeetingName) obj;
        return this.value.equals(meetingName.stringValue());
    }
}
