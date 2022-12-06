package com.example.demo.useCase;

import org.springframework.stereotype.Service;

import com.example.demo.domain.Meeting;
import com.example.demo.domain.MeetingRoom;
import com.example.demo.domain.MeetingRoomId;
import com.example.demo.domain.MeetingRoomRepository;

@Service
public class StartMeetingUseCase {
    private final MeetingRoomRepository repository;

    public StartMeetingUseCase(MeetingRoomRepository repository) {
        this.repository = repository;
    }

    public void execute(MeetingRoomId meetingRoomId, Meeting meeting) {
        // 会議したい会議室を検索する
        final MeetingRoom foundMeetingRoom = repository.findById(meetingRoomId);

        // 会議室で会議開始する（既に会議中であれば，新たに会議を開始できないのでそのままにする）
        final MeetingRoom useMeetingRoom = foundMeetingRoom.startMeeting(meeting.getMeetingName(), meeting.getMeetingDelegate());

        // 会議室で会議が開始したことを保存する
        // ただし，会議室が状態変化していなければ，保存しない
        if (!foundMeetingRoom.equals(useMeetingRoom)) {
            System.out.println("Start Meeting Change");
            repository.save(useMeetingRoom);
        } else {
            System.out.println("Start Meeting No Change");
        }
    }
}
