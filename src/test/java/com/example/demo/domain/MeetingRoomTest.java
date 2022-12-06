package com.example.demo.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MeetingRoomTest {
    private MeetingRoomId meetingRoomId = new MeetingRoomId("A001");
    private MeetingRoomName meetingRoomName = new MeetingRoomName("Meeting Room 1");
    private MeetingName defaultMeetingName = new MeetingName("None");
    private MeetingDelegate defaultMeetingDelegate = new MeetingDelegate("None");
    private Meeting defaultMeeting = new Meeting(defaultMeetingName, defaultMeetingDelegate);
    private MeetingRoom notUsingMeetingRoom = MeetingRoom.reconstruct(
            meetingRoomId,
            meetingRoomName,
            MeetingRoomStatus.available,
            defaultMeeting);
    private MeetingName meetingName = new MeetingName("Test Meeting");
    private MeetingDelegate meetingDelegate = new MeetingDelegate("Tanaka");
    private Meeting meeting = new Meeting(meetingName, meetingDelegate);
    private MeetingRoom usingMeetingRoom = MeetingRoom.reconstruct(
            meetingRoomId,
            meetingRoomName,
            MeetingRoomStatus.inUse,
            meeting);

    @Test
    public void 利用可能な会議室で会議開始時にステータスがinUseとなる() {
        MeetingRoom meetingRoomStartMeeting = notUsingMeetingRoom.startMeeting(meetingName, meetingDelegate);
        assertThat(meetingRoomStartMeeting).isEqualTo(usingMeetingRoom);
    }

    @Test
    public void 利用不可な会議室で会議開始時に状態変化なし() {
        MeetingRoom meetingRoomStartMeeting = usingMeetingRoom.startMeeting(meetingName, meetingDelegate);
        assertThat(meetingRoomStartMeeting).isEqualTo(usingMeetingRoom);
    }

    @Test
    public void 会議内容を変更したときに会議名と会議代表者が変更となる() {
        MeetingName newMeetingName = new MeetingName("New Project Meeting");
        MeetingDelegate newMeetingDelegate = new MeetingDelegate("Yoshida");
        Meeting newMeeting = new Meeting(newMeetingName, newMeetingDelegate);
        MeetingRoom meetingRoomEditMeeting = usingMeetingRoom.editMeeting(newMeetingName, newMeetingDelegate);
        assertThat(meetingRoomEditMeeting.getMeeting()).isEqualTo(newMeeting);
    }

    @Test
    public void 会議終了時にステータスがavailableとなる() {
        MeetingRoom meetingRoomEndMeeting = usingMeetingRoom.endMeeting();
        assertThat(meetingRoomEndMeeting).isEqualTo(notUsingMeetingRoom);
    }

    @Test
    public void 利用していない会議室で会議終了しても状態変化なし() {
        MeetingRoom meetingRoomEndMeeting = notUsingMeetingRoom.endMeeting();
        assertThat(meetingRoomEndMeeting).isEqualTo(notUsingMeetingRoom);
    }

}
