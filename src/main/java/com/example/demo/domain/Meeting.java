package com.example.demo.domain;

public class Meeting {
    private final MeetingName meetingName;
    private final MeetingDelegate meetingDelegate;

    public Meeting(MeetingName meetingName, MeetingDelegate meetingDelegate) {
        this.meetingName = meetingName;
        this.meetingDelegate = meetingDelegate;
    }

    // 会議作成
    public Meeting create(MeetingName meetingName, MeetingDelegate meetingDelegate) {
        return new Meeting(meetingName, meetingDelegate);
    }

    // 会議削除
    public Meeting delete() {
        return new Meeting(new MeetingName("None"), new MeetingDelegate("None"));
    }

    // getter
    public MeetingName getMeetingName() {
        return meetingName;
    }

    public MeetingDelegate getMeetingDelegate() {
        return meetingDelegate;
    }

    @Override
    public boolean equals(Object obj) {
        Meeting meeting = (Meeting) obj;
        if (this.meetingName.equals(meeting.meetingName) &&
                this.meetingDelegate.equals(meeting.meetingDelegate)) {
            return true;
        } else {
            return false;
        }
    }

    // デバッグ用
    public String stringValue() {
        return meetingName.stringValue() + "," + meetingDelegate.stringValue();
    }
}
