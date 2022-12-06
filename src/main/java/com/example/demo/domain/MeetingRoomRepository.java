package com.example.demo.domain;

import java.util.List;

public interface MeetingRoomRepository {
    public List<MeetingRoom> findAll();

    public MeetingRoom findById(MeetingRoomId meetingRoomId);

    public void save(MeetingRoom meetingRoom);
}
