package com.example.demo.useCase;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.domain.MeetingRoom;
import com.example.demo.domain.MeetingRoomId;
import com.example.demo.domain.MeetingRoomRepository;

@Service
public class AllEndMeetingUseCase {
    private final MeetingRoomRepository repository;

    public AllEndMeetingUseCase(MeetingRoomRepository repository) {
        this.repository = repository;
    }

    public void execute() {
        //会議室を全検索する
        final List<MeetingRoom> allMeetingRoom = repository.findAll();

        //会議室毎に会議を終了する
        for (MeetingRoom meetingRoom: allMeetingRoom) {
            final MeetingRoomId meetingRoomId = meetingRoom.getMeetingRoomId();
            final MeetingRoom foundMeetingRoom = repository.findById(meetingRoomId);
            final MeetingRoom nonUseMeetingRoom = foundMeetingRoom.endMeeting();
            if (!foundMeetingRoom.equals(nonUseMeetingRoom)) {
                repository.save(nonUseMeetingRoom);
            } else {
                System.out.println("End Meeting No Change");
            }
        }
    }
}