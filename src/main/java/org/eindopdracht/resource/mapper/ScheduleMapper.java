package org.eindopdracht.resource.mapper;

import org.eindopdracht.resource.dto.ScheduleDTO;
import org.eindopdracht.resource.model.Schedule;
import org.eindopdracht.util.EntityMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("scheduleMapperComponent")
public class ScheduleMapper implements EntityMapper<Schedule, ScheduleDTO> {
    @Override
    public ScheduleDTO mapFromEntity(Schedule schedule) {
        return new ScheduleDTO(
                schedule.getId(),
                schedule.getUsers(),
                schedule.getTitle(),
                schedule.getDescription(),
                schedule.getStartDateTime(),
                schedule.getEndDateTime()
        );
    }

    @Override
    public Schedule mapToEntity(ScheduleDTO scheduleDto) {
        return new Schedule(
                scheduleDto.getId(),
                scheduleDto.getUsers(),
                scheduleDto.getTitle(),
                scheduleDto.getDescription(),
                scheduleDto.getStartDateTime(),
                scheduleDto.getEndDateTime()
        );
    }

    public List<ScheduleDTO> mapFromEntityList(List<Schedule> entities) {
        List<ScheduleDTO> scheduleDTOList = new ArrayList<>();
        for (Schedule entity : entities) {
            scheduleDTOList.add(mapFromEntity(entity));
        }

        return scheduleDTOList;
    }
}
