package ${generate.controllerPackage}.${packageName};

import ${bean.beanPackage}.${beanName};
import com.aunewtop.common.domain.page.Pageable;
import ${generate.servicePackage}.${bean.table}.${beanName}Service;
import ${bean.paramPackage}.${bean.nameUpper}Param;
[#list bean.fields as field]
    [#if field.enumtype??]
import ${field.enumtype};
    [/#if]
[/#list]
import com.aunewtop.${generate.project}.web.action.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
* User: ${author}
* Date: ${date?datetime}
* auto Generator created.
*/
@Controller("${bean.table}Controller")
@RequestMapping("/${bean.table}")
public class ${beanName}Controller extends BaseController {
    @Resource(name = "${bean.table}ServiceImpl")
    private ${beanName}Service ${bean.table}Service;

    @RequestMapping(value = "/list", method = { RequestMethod.GET,RequestMethod.POST })
    public String list(${beanName}Param ${bean.table}Param,Pageable pageable, ModelMap modelMap, HttpServletRequest request) {
        modelMap.addAttribute("page",${bean.table}Service.find${beanName}List(${bean.table}Param, transPage(request,pageable)));
        modelMap.addAttribute("${bean.table}", ${bean.table}Param);
        [#list bean.fields as field]
            [#if field.enumtype??]
        modelMap.addAttribute("${field.lowerEnum}s", ${field.enumName}.values());
            [/#if]
        [/#list]
        return "/${bean.table}/list";
    }

    @RequestMapping(value = "/update", method = {RequestMethod.POST })
    public String update(${beanName} ${bean.table}, ModelMap model) {
        int result = ${bean.table}Service.update(${bean.table});
        return getResultJson(result, "Update");
    }

    @RequestMapping(value = "/save", method = {RequestMethod.POST })
    public String save(${beanName} ${bean.table}, ModelMap model) {
        int result = ${bean.table}Service.insert(${bean.table});
        return getResultJson(result, "Add");
    }

    @RequestMapping(value = "/edit", method = { RequestMethod.GET,RequestMethod.POST })
    public String edit(Long ${bean.primaryKey}, ModelMap model) {
        model.addAttribute("${bean.table}", ${bean.table}Service.get(${bean.primaryKey}));
        [#list bean.fields as field]
            [#if field.enumtype??]
        model.addAttribute("${field.lowerEnum}s", ${field.enumName}.values());
            [/#if]
        [/#list]
        return "/${bean.table}/edit";
    }

    @RequestMapping(value = "/add", method = { RequestMethod.GET,RequestMethod.POST })
    public String add(${beanName} ${bean.table}, ModelMap model) {
        [#list bean.fields as field]
            [#if field.enumtype??]
        model.addAttribute("${field.lowerEnum}s", ${field.enumName}.values());
            [/#if]
        [/#list]
        return "/${bean.table}/add";
    }

    @ResponseBody
    @RequestMapping(value = "/delete", method = { RequestMethod.GET,RequestMethod.POST })
    public String delete(Long[] ids,ModelMap model) {
        int c = 0;
        if(ids !=null && ids.length > 0){
            for(Long id:ids){
                c += ${bean.table}Service.delete(id);
            }
        }
        return getResultJson(c, "Delete");
    }
}