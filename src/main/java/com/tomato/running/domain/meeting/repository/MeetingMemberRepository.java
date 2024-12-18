package com.tomato.running.domain.meeting.repository;

import com.tomato.running.domain.meeting.Meeting;
import com.tomato.running.domain.meeting.MeetingMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MeetingMemberRepository extends JpaRepository<MeetingMember, UUID> {
    Integer countAllByMeeting(Meeting meeting);
}
