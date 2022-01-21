package org.AdvancedJavaEindopdracht.resource.service;

import org.AdvancedJavaEindopdracht.resource.model.schedule.ScheduleDto;
import org.AdvancedJavaEindopdracht.resource.model.schedule.ScheduleMapper;
import org.AdvancedJavaEindopdracht.resource.repository.ScheduleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final ScheduleMapper scheduleMapper;

    public ScheduleService(ScheduleRepository scheduleRepository, ScheduleMapper scheduleMapper) {
        this.scheduleRepository = scheduleRepository;
        this.scheduleMapper = scheduleMapper;
    }

    /**
     * Maps Entity to DTO and returns a list of all schedules.
     *
     * @return      response entity with list of all schedules
     */
    public List<ScheduleDto> getAll() {
        return scheduleMapper.mapFromEntityList(scheduleRepository.get());
    }

    /**
     * Maps Entity to DTO and returns a single schedule.
     *
     * @param id    id of the schedule to find
     * @return      response entity with single schedule
     */
    public ScheduleDto getById(long id) {
        return scheduleMapper.mapFromEntity(scheduleRepository.getById(id));
    }

    /**
     * Maps Entity to DTO and posts a single schedule.
     *
     * @param scheduleDto   schedule to post
     * @return              response entity with posted schedule
     */
    public ScheduleDto persist(ScheduleDto scheduleDto) {
        return scheduleMapper.mapFromEntity(
                scheduleRepository.post(scheduleMapper.mapToEntity(scheduleDto))
        );
    }

    /**
     * Maps Entity to DTO and puts a single schedule.
     *
     * @param id            id of the schedule to put
     * @param scheduleDto   schedule to put
     * @return              response entity with put schedule
     */
    public ScheduleDto put(long id, ScheduleDto scheduleDto) {
        return scheduleMapper.mapFromEntity(scheduleRepository.put(id, scheduleMapper.mapToEntity(scheduleDto)));
    }

    /**
     * Maps Entity to DTO and patches a single schedule.
     *
     * @param id            id of the schedule to patch
     * @param scheduleDto   consultation to patch
     * @return              response entity with patched schedule
     */
    public ScheduleDto patch(long id, ScheduleDto scheduleDto) {
        return scheduleMapper.mapFromEntity(scheduleRepository.patch(id, scheduleMapper.mapToEntity(scheduleDto)));
    }

    /**
     * Maps Entity to DTO and deletes a single schedule.
     *
     * @param id    id of the schedule to delete
     * @return      response entity with deleted schedule
     */
    public ScheduleDto delete(long id) {
        return scheduleMapper.mapFromEntity(scheduleRepository.delete(id));
    }
}
