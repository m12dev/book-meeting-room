package com.example.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.MeetingRoomId;
import com.example.demo.request.MeetingRequest;
import com.example.demo.response.MeetingRoomResponse;
import com.example.demo.useCase.AllEndMeetingUseCase;
import com.example.demo.useCase.EditMeetingUseCase;
import com.example.demo.useCase.EndMeetingUseCase;
import com.example.demo.useCase.ShowMeetingRoomUseCase;
import com.example.demo.useCase.StartMeetingUseCase;

@RestController
@RequestMapping("/api")
public class DemoController {
    public StartMeetingUseCase startMeetingUseCase;
    public EditMeetingUseCase editMeetingUseCase;
    public ShowMeetingRoomUseCase showMeetingRoomUseCase;
    public EndMeetingUseCase endMeetingUseCase;
    public AllEndMeetingUseCase allEndMeetingUseCase;

    public DemoController(
            StartMeetingUseCase startMeetingUseCase,
            EditMeetingUseCase editMeetingUseCase,
            ShowMeetingRoomUseCase showMeetingRoomUseCase,
            EndMeetingUseCase endMeetingUseCase,
            AllEndMeetingUseCase allEndMeetingUseCase) {
        this.startMeetingUseCase = startMeetingUseCase;
        this.editMeetingUseCase = editMeetingUseCase;
        this.showMeetingRoomUseCase = showMeetingRoomUseCase;
        this.endMeetingUseCase = endMeetingUseCase;
        this.allEndMeetingUseCase = allEndMeetingUseCase;
    }

    @CrossOrigin(origins = "*")
    @RequestMapping("/meetingrooms")
    @ResponseStatus(code = HttpStatus.OK)
    public List<MeetingRoomResponse> index() {
        return MeetingRoomResponse.convMeetingRoom(showMeetingRoomUseCase.execute());
    }

    @CrossOrigin(origins = "*")
    @ResponseStatus(code = HttpStatus.CREATED)
    @RequestMapping(path = "/meetingrooms/{id}/meeting", method = RequestMethod.POST)
    public List<MeetingRoomResponse> startMeeting(
            @PathVariable(name = "id", required = true) String id,
            @RequestBody(required = true) MeetingRequest meeting) {
        startMeetingUseCase.execute(new MeetingRoomId(id), meeting.getMeeting());
        return MeetingRoomResponse.convMeetingRoom(showMeetingRoomUseCase.execute());
    }

    @CrossOrigin(origins = "*")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    @RequestMapping(path = "/meetingrooms/{id}/meeting", method = RequestMethod.PUT)
    public List<MeetingRoomResponse> editMeeting(
            @PathVariable(name = "id", required = true) String id,
            @RequestBody(required = true) MeetingRequest meeting) {
        editMeetingUseCase.execute(new MeetingRoomId(id), meeting.getMeeting());
        return MeetingRoomResponse.convMeetingRoom(showMeetingRoomUseCase.execute());
    }

    @CrossOrigin(origins = "*")
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    @RequestMapping(path = "/meetingrooms/{id}/meeting", method = RequestMethod.DELETE)
    public List<MeetingRoomResponse> endMeeting(
            @PathVariable(name = "id", required = true) String id) {
        endMeetingUseCase.execute(new MeetingRoomId(id));
        return MeetingRoomResponse.convMeetingRoom(showMeetingRoomUseCase.execute());
    }

    @CrossOrigin(origins = "*")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @RequestMapping(path = "/meetingrooms/allmeetings", method = RequestMethod.DELETE)
    public void endMeeting() {
        allEndMeetingUseCase.execute();
    }

}
