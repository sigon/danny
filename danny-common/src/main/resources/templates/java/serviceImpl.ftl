package ${generate.servicePackage}.${packageName}.impl;

import ${generate.mapperPackage}.${beanName}Mapper;
import com.aunewtop.common.domain.enumtype.ActionEnum;
import ${bean.beanPackage}.${beanName};
import ${bean.beanPackage}.${beanName}Example;
import com.aunewtop.common.domain.page.Page;
import com.aunewtop.common.domain.page.Pageable;
import com.aunewtop.common.web.annotation.WriteLog;
import ${bean.paramPackage}.${beanName}Param;
import ${generate.servicePackage}.${bean.table}.${beanName}Service;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
* User: ${author}
* Date: ${date?datetime}
*/
@Service("${bean.table}ServiceImpl")
public class ${beanName}ServiceImpl implements ${beanName}Service {
    @Autowired
    private ${beanName}Mapper ${packageName}Mapper;

    public int insert (${beanName} ${packageName}) {
        ${packageName}.setCreateDate(new Date());
        ${packageName}.setModifyDate(new Date());
        return ${packageName}Mapper.insert(${packageName});
    }

    public int update(${beanName} ${packageName}) {
        return ${packageName}Mapper.updateByPrimaryKeySelective(${packageName});
    }

    public ${beanName} get(String id) {
        return ${packageName}Mapper.selectByPrimaryKey(id);
    }

    public Page<${beanName}> find${beanName}List(${beanName}Param ${packageName}, Pageable pageable) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        //设置排序信息
        ${beanName}Example example = new ${beanName}Example();
        example.setPage(pageable);
        example.setOrderByClause("create_date desc");

        ${beanName}Example.Criteria criteria = example.createCriteria();

        //根据当前有的bean信息设置查询条件，循环bean.fields信息
        [#list fields as field]
            //如果是有between，说明属于范围查询，特殊处理一下，其他的就按正常处理
            [#if field.search??]
                [#if field.search == "between"]
                    try {
                        if (${packageName}!=null&&StringUtils.isNotBlank(${packageName}.getStart${field.nameUpper}())){
                            Date startTime = sdf.parse(${packageName}.getStart${field.nameUpper}());
                            criteria.and${field.nameUpper}GreaterThanOrEqualTo(startTime);
                        }
                        if (${packageName}!=null&&StringUtils.isNotBlank(${packageName}.getEnd${field.nameUpper}())){
                            Date endTime = sdf.parse(${packageName}.getEnd${field.nameUpper}());
                            Calendar calendar = Calendar.getInstance();
                            calendar.setTime(endTime);
                            calendar.add(Calendar.DATE, 1);
                            criteria.and${field.nameUpper}LessThanOrEqualTo(calendar.getTime());
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                [/#if]

                //如果不是select 或者 checkbox，radio之类的，一律实现为模糊查询模式
                [#if field.search == 'true']
                    if (${packageName}!=null&& ${packageName}.get${field.nameUpper}() != null){
                        criteria.and${field.nameUpper}Like("%" + ${packageName}.get${field.nameUpper}().replaceAll(" ","") + "%");
                    }
                [/#if]

                //如果不是select 或者 checkbox，radio之类的，一律实现为模糊查询模式
                [#if field.search == 'select' || field.search == 'radio' || field.search == 'checkbox']
                if (${packageName}!=null&& ${packageName}.get${field.nameUpper}() != null){
                    criteria.and${field.nameUpper}EqualTo(${packageName}.get${field.nameUpper}());
                }
                [/#if]
            [/#if]
        [/#list]

        List<${beanName}> list = ${packageName}Mapper.selectByExample(example);
        int count = ${packageName}Mapper.countByExample(example);

        return new Page<${beanName}>(list,count,pageable);
    }

    public int delete(String id){
        return ${packageName}Mapper.deleteByPrimaryKey(id);
    }
}
