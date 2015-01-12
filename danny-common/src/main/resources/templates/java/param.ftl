package ${bean.paramPackage};

import java.util.Date;
import java.io.Serializable;
/**
* User: ${author}
* Date: ${date?datetime}
* auto Generator created.
*/
public class ${bean.nameUpper}Param implements Serializable{
    private Long ${bean.primaryKey};
<#list bean.fields as field>
    <#if field.search??>
        <#if field.search == "text">
    private String ${field.name};
        </#if>
        <#if field.search == "between">
            <#if field.type == "number">
                <#assign type="Integer"/>
            <#else>
                <#assign type="String"/>
            </#if>
    private ${type} start${field.nameUpper};
    private ${type} end${field.nameUpper};
        </#if>
        <#if field.search == "select">
    private Integer ${field.name};
        </#if>
    </#if>
</#list>

    public Long get${bean.keyUpper}() {
        return ${bean.primaryKey};
    }

    public void set${bean.keyUpper}(Long ${bean.keyUpper}) {
        this.${bean.keyUpper} = ${bean.keyUpper};
    }
<#list bean.fields as field>
    <#if field.search??>
        <#if field.search == "text">
    public String get${field.nameUpper}() {
        return ${field.name};
    }

    public void set${field.nameUpper}(String ${field.name}) {
        this.${field.name} = ${field.name};
    }
        </#if>
        <#if field.search == "between">
            <#if field.type == "number">
                <#assign type="Integer"/>
            <#else>
                <#assign type="String"/>
            </#if>
    public ${type} getStart${field.nameUpper}() {
        return start${field.nameUpper};
    }

    public void setStart${field.nameUpper}(${type} start${field.nameUpper}) {
        this.start${field.nameUpper} = start${field.nameUpper};
    }
    public ${type} getEnd${field.nameUpper}() {
        return end${field.nameUpper};
    }

    public void setEnd${field.nameUpper}(${type} start${field.nameUpper}) {
        this.end${field.nameUpper} = end${field.nameUpper};
    }
        </#if>
        <#if field.search == "select">
    public Integer get${field.nameUpper}() {
        return ${field.name};
    }

    public void set${field.nameUpper}(Integer ${field.name}) {
        this.${field.name} = ${field.name};
    }
        </#if>
    </#if>
</#list>
}
