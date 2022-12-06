package com.example.demo.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.domain.*;

@Repository
public class MeetingRoomRdbRepository implements MeetingRoomRepository {
    public List<MeetingRoom> findAll() {
        List<MeetingRoom> meetingRooms = new ArrayList<MeetingRoom>();
        try (Connection con = Driver.connect()) {
            PreparedStatement ps = con.prepareStatement(
                    "select * from meeting_room");
            ps.setQueryTimeout(30);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                meetingRooms.add(MeetingRoom.reconstruct(new MeetingRoomId(rs.getString("id")),
                        new MeetingRoomName(rs.getString("name")),
                        MeetingRoomStatus.valueOf(rs.getString("status")),
                        new Meeting(new MeetingName(rs.getString("meeting_name")),
                                new MeetingDelegate(rs.getString("meeting_delegate")))));
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try (Connection con = Driver.connect()) {
                if (con != null)
                    con.close();
            } catch (ClassNotFoundException | SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }
        return meetingRooms;
    }

    public MeetingRoom findById(MeetingRoomId meetingRoomId) {
        MeetingRoom meetingRoom = null;
        try (Connection con = Driver.connect()) {
            PreparedStatement ps = con.prepareStatement(
                    "select * from meeting_room where id = ?");
            ps.setQueryTimeout(30);
            ps.setString(1, meetingRoomId.stringValue());

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                meetingRoom = MeetingRoom.reconstruct(meetingRoomId, new MeetingRoomName(rs.getString("name")),
                        MeetingRoomStatus.valueOf(rs.getString("status")),
                        new Meeting(new MeetingName(rs.getString("meeting_name")),
                                new MeetingDelegate(rs.getString("meeting_delegate"))));
            }
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try (Connection con = Driver.connect()) {
                if (con != null)
                    con.close();
            } catch (ClassNotFoundException | SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }
        return meetingRoom;
    }

    public void save(MeetingRoom meetingRoom) {
        try (Connection con = Driver.connect()) {
            PreparedStatement ps = con.prepareStatement(
                    "update meeting_room set status = ?, meeting_name = ?, meeting_delegate = ? where id = ?");
            ps.setString(1, meetingRoom.getMeetingRoomStatus().name());
            ps.setString(2, meetingRoom.getMeeting().getMeetingName().stringValue());
            ps.setString(3, meetingRoom.getMeeting().getMeetingDelegate().stringValue());
            ps.setString(4, meetingRoom.getMeetingRoomId().stringValue());
            ps.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try (Connection con = Driver.connect()) {
                if (con != null)
                    con.close();
            } catch (ClassNotFoundException | SQLException e) {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }
    }
}
