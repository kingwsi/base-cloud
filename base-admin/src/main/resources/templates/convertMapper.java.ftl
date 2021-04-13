package ${package.Entity};

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
* description: ${table.comment} 转换工具 <br>
* date: ${date} <br>
* author: ${author} <br>
*/
@Component
@Mapper(componentModel = "spring")
public interface ${entity}ConvertMapper {
    ${entity} to${entity}(${entity}VO vo);

    List<${entity}> to${entity}List(List<${entity}VO> vo);

    ${entity}VO to${entity}VO(${entity} ${entity?lower_case});

    List<${entity}VO> to${entity}VOList(List<${entity}> vo);
}