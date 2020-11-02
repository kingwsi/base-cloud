package ${package.Entity};

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface ${entity}ConvertMapper {
    ${entity} ${entity?lower_case}VoTo${entity}(${entity}VO vo);

    ${entity}VO ${entity?lower_case}To${entity}VO(${entity} ${entity?lower_case});
}