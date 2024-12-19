package com.tomato.running.domain.meeting.repository;


import com.tomato.running.domain.meeting.entity.Meeting;
import com.tomato.running.domain.user.entity.User;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface MeetingRepository extends JpaRepository<Meeting, UUID> {
    @Query("select m from Meeting m where m.title like %:title%")
    List<Meeting> findByTitleContaining(@Param("title") String title);
    List<Meeting> findByUser(User user);
}
