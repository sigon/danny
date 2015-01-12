package net.sigon.danny.common.generate.bean;

import org.apache.commons.lang.StringUtils;

/**
 * Created with IntelliJ IDEA.
 * User: sigon
 * Date: 14-12-9
 * Time: 下午7:30
 * To change this template use File | Settings | File Templates.
 */
public class BeanField {
    /**
     * 字段名
     */
    private String name;
    /**
     * 字段的展示类型
     */
    private String type;
    /**
     * 字段的枚举类型
     */
    private String enumtype;
    /**
     * 是否是列表查询字段，默认不是
     */
    private String search;
    /**
     * 在添加和修改时的格式判断
     */
    private String pattern;

    /**
     * 添加和修改时使用的，是否必填
     */
    private Boolean required;

    private String nameUpper;
    private String showList;

    public String getEnumName(){
        if(StringUtils.isBlank(enumtype)){
            return null;
        }
        return enumtype.substring(enumtype.lastIndexOf(".") + 1);
    }

    public String getLowerEnum(){
        if(StringUtils.isBlank(enumtype)){
            return null;
        }
        return getEnumName().substring(0, 1).toLowerCase() + getEnumName().substring(1);
    }
    public String getShowList() {
        return showList;
    }

    public void setShowList(String showList) {
        this.showList = showList;
    }

    public String getNameUpper() {
        if(StringUtils.isBlank(name)){
            return null;
        }
        return StringUtils.capitalize(name);
    }

    public void setNameUpper(String nameUpper) {
        this.nameUpper = nameUpper;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEnumtype() {
        return enumtype;
    }

    public void setEnumtype(String enumtype) {
        this.enumtype = enumtype;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public Boolean getRequired() {
        return required;
    }

    public void setRequired(Boolean required) {
        this.required = required;
    }
}
