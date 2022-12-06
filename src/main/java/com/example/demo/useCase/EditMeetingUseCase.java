package com.example.demo.useCase;

import org.springframework.stereotype.Service;

import com.example.demo.domain.Meeting;
import com.example.demo.domain.MeetingRoom;
import com.example.demo.domain.MeetingRoomId;
import com.example.demo.domain.MeetingRoomRepository;

@Service
public class EditMeetingUseCase {
    private final MeetingRoomRepository repository;

    public EditMeetingUseCase(MeetingRoomRepository repository) {
        this.repository = repository;
    }

    public void execute(MeetingRoomId meetingRoomId, Meeting meeting) {
        // 会議内容を編集したい会議室を検索する
        final MeetingRoom foundMeetingRoom = repository.findById(meetingRoomId);

        // 会議内容を編集する
        final MeetingRoom useMeetingRoom = foundMeetingRoom.editMeeting(meeting.getMeetingName(), meeting.getMeetingDelegate());

        // 会議室で会議が開始したことを保存する
        // ただし，会議室が状態変化していなければ，保存しない
        if (!foundMeetingRoom.equals(useMeetingRoom)) {
            repository.save(useMeetingRoom);
        } else {
            System.out.println("Edit Meeting No Change");
        }
    }
}
