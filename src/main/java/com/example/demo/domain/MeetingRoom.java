package com.example.demo.domain;

public class MeetingRoom {
    private final MeetingRoomId meetingRoomId;
    private final MeetingRoomName meetingRoomName;
    private final MeetingRoomStatus meetingRoomStatus;
    private final Meeting meeting;

    private MeetingRoom(MeetingRoomId id, MeetingRoomName name, MeetingRoomStatus status, Meeting meeting) {
        this.meetingRoomId = id;
        this.meetingRoomName = name;
        this.meetingRoomStatus = status;
        this.meeting = meeting;
    }

    // 会議室で会議開始
    public MeetingRoom startMeeting(MeetingName meetingName, MeetingDelegate meetingDelegate) {
        // 会議室が利用可能であれば，ステータスオブジェクトとMeetingオブジェクトを再生成してオブジェクトを作成
        // 利用中の場合は，同一のオブジェクトを作成
        if (meetingRoomStatus.equals(MeetingRoomStatus.available)) {
            return new MeetingRoom(
                    meetingRoomId,
                    meetingRoomName,
                    MeetingRoomStatus.inUse,
                    meeting.create(meetingName, meetingDelegate));
        } else {
            // エンティティをイミュータブル（改変不可）としているため，新しく同じ値のオブジェクトを生成している
            return new MeetingRoom(
                    meetingRoomId,
                    meetingRoomName,
                    meetingRoomStatus,
                    meeting);
        }
    }

    // 会議室で会議修正
    public MeetingRoom editMeeting(MeetingName meetingName, MeetingDelegate meetingDelegate) {
        // Meetingオブジェクトを再生成してオブジェクトを作成
        return new MeetingRoom(
                meetingRoomId,
                meetingRoomName,
                meetingRoomStatus,
                meeting.create(meetingName, meetingDelegate));
    }

    // 会議室で会議終了
    public MeetingRoom endMeeting() {
        return new MeetingRoom(
                meetingRoomId,
                meetingRoomName,
                MeetingRoomStatus.available,
                meeting.delete());
    }

    // オブジェクト比較
    @Override
    public boolean equals(Object obj) {
        MeetingRoom meetingRoom = (MeetingRoom) obj;
        if (this.meetingRoomId.equals(meetingRoom.meetingRoomId) &&
                this.meetingRoomName.equals(meetingRoom.meetingRoomName) &&
                this.meetingRoomStatus.equals(meetingRoom.meetingRoomStatus) &&
                this.meeting.equals(meetingRoom.meeting)) {
            return true;
        } else {
            return false;
        }
    }

    // リコンストラクタ
    public static MeetingRoom reconstruct(MeetingRoomId id, MeetingRoomName name, MeetingRoomStatus status,
            Meeting meeting) {
        return new MeetingRoom(id, name, status, meeting);
    }

    // getter
    public MeetingRoomId getMeetingRoomId() {
        return meetingRoomId;
    }

    public MeetingRoomName getMeetingRoomName() {
        return meetingRoomName;
    }

    public MeetingRoomStatus getMeetingRoomStatus() {
        return meetingRoomStatus;
    }

    public Meeting getMeeting() {
        return meeting;
    }

    // デバッグ
    public String stringValue() {
        return meetingRoomId.stringValue() + "," + meetingRoomName.stringValue() + "," + meetingRoomStatus.name()
                + "," + meeting.stringValue();
    }
}
