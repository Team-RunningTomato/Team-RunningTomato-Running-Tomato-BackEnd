package com.tomato.running.domain.meeting.repository;


import com.tomato.running.domain.meeting.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MeetingRepository extends JpaRepository<Meeting, UUID> {
}
