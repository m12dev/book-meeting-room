package com.example.demo.response;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.domain.MeetingRoom;

public class MeetingRoomResponse {
    private final String meetingRoomId;
    private final String meetingRoomName;
    private final String meetingRoomStatus;
    private final String meetingName;
    private final String meetingDelegate;

    public MeetingRoomResponse(MeetingRoom meetingRoom) {
        this.meetingRoomId = meetingRoom.getMeetingRoomId().stringValue();
        this.meetingRoomName = meetingRoom.getMeetingRoomName().stringValue();
        this.meetingRoomStatus = meetingRoom.getMeetingRoomStatus().name();
        this.meetingName = meetingRoom.getMeeting().getMeetingName().stringValue();
        this.meetingDelegate = meetingRoom.getMeeting().getMeetingDelegate().stringValue();
    }

    public static List<MeetingRoomResponse> convMeetingRoom(List<MeetingRoom> meetingRooms) {
        List<MeetingRoomResponse> response = new ArrayList<MeetingRoomResponse>();
        for (MeetingRoom meetingRoom : meetingRooms) {
            response.add(new MeetingRoomResponse(meetingRoom));
        }
        return response;
    }

    public String getMeetingRoomId() {
        return meetingRoomId;
    }

    public String getMeetingRoomName() {
        return meetingRoomName;
    }

    public String getMeetingRoomStatus() {
        return meetingRoomStatus;
    }

    public String getMeetingName() {
        return meetingName;
    }

    public String getMeetingDelegate() {
        return meetingDelegate;
    }
}
