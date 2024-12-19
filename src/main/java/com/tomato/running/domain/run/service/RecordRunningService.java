package com.tomato.running.domain.run.service;

import com.tomato.running.domain.run.entity.EndLocation;
import com.tomato.running.domain.run.entity.Run;
import com.tomato.running.domain.run.entity.StartLocation;
import com.tomato.running.domain.run.exception.InvalidRunDistanceException;
import com.tomato.running.domain.run.presentation.data.req.RecordRunningRequestDto;
import com.tomato.running.domain.run.repository.RunRepository;
import com.tomato.running.domain.run.util.LevelUtil;
import com.tomato.running.domain.running.entity.RunningUser;
import com.tomato.running.domain.running.repository.RunningUserRepository;
import com.tomato.running.domain.user.entity.User;
import com.tomato.running.domain.user.util.UserUtil;
import com.tomato.running.global.annotation.TransactionService;
import lombok.RequiredArgsConstructor;

@TransactionService
@RequiredArgsConstructor
public class RecordRunningService {

    private final RunningUserRepository runningUserRepository;
    private final LevelUtil levelUtil;
    private final RunRepository runRepository;
    private final UserUtil userUtil;

    public void recordRunning(RecordRunningRequestDto dto) {
        User user = userUtil.getCurrentUser();

        if (dto.getDistance() < 0)
            throw new InvalidRunDistanceException();

        saveRun(dto, user);
        saveRunningUser(user, dto);
    }

    private void saveRun(RecordRunningRequestDto dto, User user) {
        Run run = Run.builder()
                .distance(dto.getDistance())
                .startLocation(new StartLocation(dto.getStartLongitude(), dto.getStartLatitude()))
                .endLocation(new EndLocation(dto.getEndLongitude(), dto.getEndLatitude()))
                .runningTime(dto.getRunningTime())
                .user(user)
                .build();

        runRepository.save(run);
    }

    private void saveRunningUser(User user, RecordRunningRequestDto dto) {
        RunningUser runningUser = user.getRunningUser();

        Integer best = runningUser.getBestDistance() < dto.getDistance() ? dto.getDistance() : runningUser.getBestDistance();

        Integer worst;

        if (runningUser.getWorstDistance() == null) {
            worst = dto.getDistance();
        } else {
            worst = runningUser.getWorstDistance() > dto.getDistance() ? dto.getDistance() : runningUser.getWorstDistance();
        }

        Integer newTotalDistance = runningUser.getTotalDistance() + dto.getDistance();

        RunningUser newRunningUser = RunningUser.builder()
                .id(runningUser.getId())
                .bestDistance(best)
                .worstDistance(worst)
                .totalDistance(newTotalDistance)
                .levelPercentage(levelUtil.calculatePercentage(newTotalDistance))
                .level(levelUtil.calculateLevel(newTotalDistance))
                .build();

        runningUserRepository.save(newRunningUser);
    }
}
