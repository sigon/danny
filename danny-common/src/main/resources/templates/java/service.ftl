package ${generate.servicePackage}.${packageName};

import ${bean.beanPackage}.${beanName};
import com.aunewtop.common.domain.page.Page;
import com.aunewtop.common.domain.page.Pageable;

import ${bean.paramPackage}.${beanName}Param;

import java.util.List;

/**
* User: ${author}
* Date: ${date?datetime}
* auto Generator created.
*/
public interface ${beanName}Service {

    public int insert(${beanName} ${packageName});

    public int update(${beanName} ${packageName});

    public ${beanName} get(Integer id);

    public Page<${beanName}> find${beanName}List(${beanName}Param ${packageName}, Pageable pageable);

    public int delete(Integer id);
}
