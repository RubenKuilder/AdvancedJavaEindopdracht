package org.AdvancedJavaEindopdracht.resource.service;

import org.AdvancedJavaEindopdracht.resource.model.event.content.ContentDto;
import org.AdvancedJavaEindopdracht.resource.model.event.content.ContentMapper;
import org.AdvancedJavaEindopdracht.resource.model.schedule.ScheduleDto;
import org.AdvancedJavaEindopdracht.resource.model.schedule.ScheduleMapper;
import org.AdvancedJavaEindopdracht.resource.repository.ContentRespository;
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

    public List<ScheduleDto> get() {
        return scheduleMapper.mapFromEntityList(scheduleRepository.get());
    }

    public ScheduleDto getById(long id) {
        return scheduleMapper.mapFromEntity(scheduleRepository.getById(id));
    }

    public ScheduleDto persist(ScheduleDto scheduleDto) {
        return scheduleMapper.mapFromEntity(
                scheduleRepository.persist(scheduleMapper.mapToEntity(scheduleDto))
        );
    }
}
