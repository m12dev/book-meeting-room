package com.example.demo.useCase;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.domain.MeetingRoomRepository;
import com.example.demo.domain.MeetingRoom;

@Service
public class ShowMeetingRoomUseCase {
    private final MeetingRoomRepository repository;

    public ShowMeetingRoomUseCase(MeetingRoomRepository repository) {
        this.repository = repository;
    }

    public List<MeetingRoom> execute() {
        // 会議室を検索する
        return repository.findAll();
    }
}
