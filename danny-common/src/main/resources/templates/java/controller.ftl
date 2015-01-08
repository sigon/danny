package ${generate.controllerPackage}.${packageName};

import ${bean.beanPackage}.${beanName};
import com.aunewtop.common.domain.page.Pageable;
import ${generate.servicePackage}.${bean.table}.${beanName}Service;
import ${bean.paramPackage}.${bean.nameUpper}Param;
import com.aunewtop.trader.web.action.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

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
    public String list(${beanName}Param ${bean.table}Param,Pageable pageable, ModelMap modelMap) {
        modelMap.addAttribute("page",${bean.table}Service.find${beanName}List(${bean.table}Param, pageable));
        modelMap.addAttribute("${bean.table}", ${bean.table}Param);
        return "/${bean.table}/list";
    }

    @RequestMapping(value = "/save", method = {RequestMethod.POST })
    public String doEdit(${beanName} ${bean.table}, ModelMap model,RedirectAttributes redirectAttributes) {
        int result = ${bean.table}Service.update(${bean.table});
        return getResultPage(result,"Edit",redirectAttributes);
    }

    @RequestMapping(value = "/doAdd", method = {RequestMethod.POST })
    public String doAdd(${beanName} ${bean.table}, ModelMap model,RedirectAttributes redirectAttributes) {
        int result = ${bean.table}Service.insert(${bean.table});
        return getResultPage(result,"Add",redirectAttributes);
    }

    @RequestMapping(value = "/edit", method = { RequestMethod.GET,RequestMethod.POST })
    public String edit(Integer ${bean.table}Id, ModelMap model) {
        model.addAttribute("${bean.table}", ${bean.table}Service.get(${bean.table}Id));
        return "/${bean.table}/edit";
    }

    @RequestMapping(value = "/add", method = { RequestMethod.GET,RequestMethod.POST })
    public String add(${beanName} ${bean.table}, ModelMap model) {
        return "/${bean.table}/add";
    }

    @ResponseBody
    @RequestMapping(value = "/delete", method = { RequestMethod.GET,RequestMethod.POST })
    public String delete(Integer[] ids,ModelMap model,RedirectAttributes redirectAttributes) {
        if(ids !=null && ids.length > 0){
            for(Integer id:ids){
                ${bean.table}Service.delete(id);
            }
        }

        return getResultPage(1,"Delete",redirectAttributes);
    }
}
