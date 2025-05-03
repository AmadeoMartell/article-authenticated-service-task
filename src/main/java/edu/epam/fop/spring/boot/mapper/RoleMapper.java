package edu.epam.fop.spring.boot.mapper;

import edu.epam.fop.spring.boot.dto.RoleDto;
import edu.epam.fop.spring.boot.entity.Role;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface RoleMapper {
    Role toEntity(RoleDto roleDto);

    RoleDto toDto(Role role);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Role partialUpdate(RoleDto roleDto, @MappingTarget Role role);
}