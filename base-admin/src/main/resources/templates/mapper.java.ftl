package ${package.Mapper};

import ${package.Entity}.${entity};
import ${package.Entity}.${entity}VO;
import ${superMapperClassPackage};
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
* description: ${table.comment} Mapper接口 <br>
* date: ${date} <br>
* author: ${author} <br>
*/
<#if kotlin>
interface ${table.mapperName} : ${superMapperClass}<${entity}>
<#else>
@Component
public interface ${table.mapperName} extends ${superMapperClass}<${entity}> {
    IPage<${entity}VO> selectPage(Page<${entity}VO> page, @Param("vo") ${entity}VO vo);
}
</#if>
