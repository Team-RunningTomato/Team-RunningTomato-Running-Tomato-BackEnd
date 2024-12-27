package com.tomato.running.domain.run.service;

import com.tomato.running.domain.meeting.entity.Meeting;
import com.tomato.running.domain.meeting.entity.MeetingMember;
import com.tomato.running.domain.meeting.exception.MeetingNotFoundException;
import com.tomato.running.domain.meeting.repository.MeetingMemberRepository;
import com.tomato.running.domain.meeting.repository.MeetingRepository;
import com.tomato.running.domain.run.entity.EndLocation;
import com.tomato.running.domain.run.entity.Run;
import com.tomato.running.domain.run.entity.StartLocation;
import com.tomato.running.domain.run.exception.InvalidRunDistanceException;
import com.tomato.running.domain.run.exception.MeetingMemberNotFoundException;
import com.tomato.running.domain.run.presentation.data.req.RecordRunningRequestDto;
import com.tomato.running.domain.run.repository.RunRepository;
import com.tomato.running.domain.run.util.LevelUtil;
import com.tomato.running.domain.running.entity.RunningUser;
import com.tomato.running.domain.running.exception.RunningUserNotFoundException;
import com.tomato.running.domain.running.repository.RunningUserRepository;
import com.tomato.running.domain.user.entity.User;
import com.tomato.running.domain.user.util.UserUtil;
import com.tomato.running.global.annotation.TransactionService;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@TransactionService
@RequiredArgsConstructor
public class RecordRunningService {

    private final RunningUserRepository runningUserRepository;
    private final MeetingRepository meetingRepository;
    private final MeetingMemberRepository meetingMemberRepository;
    private final LevelUtil levelUtil;
    private final RunRepository runRepository;
    private final UserUtil userUtil;

    public void recordRunning(UUID meetingId, RecordRunningRequestDto dto) {
        User user = userUtil.getCurrentUser();

        Meeting meeting = meetingRepository.findById(meetingId)
                .orElseThrow(MeetingNotFoundException::new);

        if (meeting.getDistance() < 0)
            throw new InvalidRunDistanceException();

        saveRun(dto, user, meeting);
        saveRunningUser(user, meeting);
        processMeetingMember(user, meeting);
    }

    private void saveRun(RecordRunningRequestDto dto, User user, Meeting meeting) {
        Run run = Run.builder()
                .distance(meeting.getDistance())
                .startLocation(new StartLocation(dto.getStartLongitude(), dto.getStartLatitude()))
                .endLocation(new EndLocation(dto.getEndLongitude(), dto.getEndLatitude()))
                .runningTime(dto.getRunningTime())
                .meeting(meeting)
                .user(user)
                .build();

        runRepository.save(run);
    }

    private void saveRunningUser(User user, Meeting meeting) {
        RunningUser runningUser = runningUserRepository.findByUser(user)
                .orElseThrow(RunningUserNotFoundException::new);

        Integer best = runningUser.getBestDistance() < meeting.getDistance() ? meeting.getDistance() : runningUser.getBestDistance();

        Integer worst;

        if (runningUser.getWorstDistance() == null) {
            worst = meeting.getDistance();
        } else {
            worst = runningUser.getWorstDistance() > meeting.getDistance() ? meeting.getDistance() : runningUser.getWorstDistance();
        }

        Integer newTotalDistance = runningUser.getTotalDistance() + meeting.getDistance();

        RunningUser newRunningUser = RunningUser.builder()
                .id(runningUser.getId())
                .bestDistance(best)
                .worstDistance(worst)
                .totalDistance(newTotalDistance)
                .levelPercentage(levelUtil.calculatePercentage(newTotalDistance))
                .level(levelUtil.calculateLevel(newTotalDistance))
                .user(user)
                .build();

        runningUserRepository.save(newRunningUser);
    }

    private void processMeetingMember(User user, Meeting meeting) {
        MeetingMember meetingMember = meetingMemberRepository.findByMeetingAndUser(meeting, user)
                .orElseThrow(MeetingMemberNotFoundException::new);

        meetingMember.updateStatus();

        if (!meetingMemberRepository.existsByMeetingAndStatus(meeting, false)) {
            meeting.updateStatus();
        }
    }
}