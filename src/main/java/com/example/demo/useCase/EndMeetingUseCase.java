package com.example.demo.useCase;

import org.springframework.stereotype.Service;

import com.example.demo.domain.MeetingRoom;
import com.example.demo.domain.MeetingRoomId;
import com.example.demo.domain.MeetingRoomRepository;

@Service
public class EndMeetingUseCase {
    private final MeetingRoomRepository repository;

    public EndMeetingUseCase(MeetingRoomRepository repository) {
        this.repository = repository;
    }

    public void execute(MeetingRoomId meetingRoomId) {
        // 会議している会議室を検索する
        final MeetingRoom foundMeetingRoom = repository.findById(meetingRoomId);

        // 会議室で会議終了する（未会議の場合は，そのままにする）
        final MeetingRoom nonUseMeetingRoom = foundMeetingRoom.endMeeting();

        // 会議室で会議が終了したことを保存する
        // ただし，会議室が状態変化していなければ，保存しない
        if (!foundMeetingRoom.equals(nonUseMeetingRoom)) {
            repository.save(nonUseMeetingRoom);
        } else {
            System.out.println("End Meeting No Change");
        }
    }
}
