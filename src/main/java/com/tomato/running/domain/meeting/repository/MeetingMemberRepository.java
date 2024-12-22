package com.tomato.running.domain.meeting.repository;

import com.tomato.running.domain.meeting.entity.Meeting;
import com.tomato.running.domain.meeting.entity.MeetingMember;
import com.tomato.running.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface MeetingMemberRepository extends JpaRepository<MeetingMember, UUID> {
    Integer countAllByMeeting(Meeting meeting);

    @Query("SELECT mm.meeting FROM MeetingMember mm " +
            "JOIN mm.meeting m " +
            "WHERE mm.user = :user")
    List<Meeting> findMeetingsByUser(User user);
    Boolean existsByMeetingAndUser(Meeting meeting, User user);
    void deleteAllByMeeting(Meeting meeting);
}

