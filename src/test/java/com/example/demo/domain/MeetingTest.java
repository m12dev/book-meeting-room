package com.example.demo.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MeetingTest {
    private MeetingName meetingName = new MeetingName("None");
    private MeetingDelegate meetingDelegate = new MeetingDelegate("None");
    private Meeting meeting = new Meeting(meetingName, meetingDelegate);
    private MeetingName newMeetingName = new MeetingName("Kickoff");
    private MeetingDelegate newMeetingDelegate = new MeetingDelegate("Sato");
    
    @Test
    public void 会議を作成する() {
        Meeting newMeeting = meeting.create(newMeetingName, newMeetingDelegate);
        assertThat(newMeeting.getMeetingName()).isEqualTo(newMeetingName);
        assertThat(newMeeting.getMeetingDelegate()).isEqualTo(newMeetingDelegate);
    }

    @Test
    public void 会議を削除する() {
        Meeting newMeeting = meeting.create(newMeetingName, newMeetingDelegate);
        Meeting deleteMeeting = newMeeting.delete();
        assertThat(deleteMeeting).isEqualTo(meeting);
    }
}
