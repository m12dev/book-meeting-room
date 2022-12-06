package com.example.demo.domain;

public class MeetingDelegate {
    private final String value;

    public MeetingDelegate(String value) {
        this.value = value;
    }

    public String stringValue() {
        return value;
    }

    @Override
    public boolean equals(Object obj) {
        MeetingDelegate meetingDelegate = (MeetingDelegate) obj;
        return this.value.equals(meetingDelegate.stringValue());
    }
}
