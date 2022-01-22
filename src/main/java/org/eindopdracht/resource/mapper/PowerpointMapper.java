package org.eindopdracht.resource.mapper;

import org.eindopdracht.resource.dto.PowerpointDTO;
import org.eindopdracht.resource.model.Powerpoint;
import org.eindopdracht.util.EntityMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component("powerpointMapperComponent")
public class PowerpointMapper implements EntityMapper<Powerpoint, PowerpointDTO> {
    @Override
    public PowerpointDTO mapFromEntity(Powerpoint powerpoint) {
        return new PowerpointDTO(
                powerpoint.getId(),
                powerpoint.getUser(),
                powerpoint.getPath()
        );
    }

    @Override
    public Powerpoint mapToEntity(PowerpointDTO powerpointDTO) {
        return new Powerpoint(
                powerpointDTO.getId(),
                powerpointDTO.getUser(),
                powerpointDTO.getPath()
        );
    }

    public List<PowerpointDTO> mapFromEntityList(List<Powerpoint> entities) {
        List<PowerpointDTO> powerpointDTOList = new ArrayList<>();
        for (Powerpoint entity : entities) {
            powerpointDTOList.add(mapFromEntity(entity));
        }

        return powerpointDTOList;
    }
}
