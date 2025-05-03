package edu.epam.fop.spring.boot.mapper;

import edu.epam.fop.spring.boot.dto.ArticleDto;
import edu.epam.fop.spring.boot.entity.Article;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ArticleMapper {
    Article toEntity(ArticleDto articleDto);

    ArticleDto toDto(Article article);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Article partialUpdate(ArticleDto articleDto, @MappingTarget Article article);
}