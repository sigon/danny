<#ftl/>
[#assign title="${bean.nameUpper} List"]
[#assign header="${bean.table}"]
[#assign nav="${bean.table}List"]
<!DOCTYPE html>
<html>
<head>
[#include "/include/head.ftl"]
    <script src="/js/datepicker/wdatepicker.js" type="text/javascript"></script>
    [#if msg??]
    <script>
        seajs.use("alert",function(alertM){
            alertM("<@show>msg</@show>");
        });
    </script>
    [/#if]
</head>
<body>
<h1 data-icon="1">${bean.nameUpper}[#include "/include/user.ftl"]</h1>
[#include "/include/header.ftl"]
<div class="pages">
[#include "/include/nav.ftl"]
    <div class="page">
        <form id="listForm" action="list.action" method="post">
            <input type="hidden" id="queryint" name="queryint" value="1">
            <menu>
                <#if bean.moduleMap["add"]??>
                    <a href="add.action" class="btn add" data-icon="a">Create New</a>
                </#if>
                <div class="form">
                    <#list bean.fields as field>
                        <#if field.search??>

                        <#if field.search == "text">
                        <p class="w50p">
                            <label class="f_info">${field.nameUpper}</label>
                            <input type="text" id="${field.name}" name="${field.name}" value="<@show>${bean.table}.${field.name}</@show>" placeholder="${field.name}">
                        </p>
                        </#if>
                        <#if field.search == "between">
                        <p class="w50p">
                            <label class="f_info">Start ${field.nameUpper}</label>
                            <input type="text" id="start${field.nameUpper}" name="start${field.nameUpper}" onClick="WdatePicker()" class="Wdate" value="<@show>${bean.table}.start${field.nameUpper}</@show>"/>
                        </p>
                        <p class="w50p">
                            <label class="f_info">End ${field.nameUpper}</label>
                            <input type="text" id="end${field.nameUpper}" name="end${field.nameUpper}" onClick="WdatePicker()" class="Wdate" value="<@show>${bean.table}end${field.nameUpper}</@show>"/>
                        </p>
                        </#if>
                        <#if field.search == "select">
                        <p class="w50p">
                            <label class="f_info">${field.nameUpper}</label>
                            <select id="${field.name}" name="${field.name}">
                                [#list ${field.lowerEnum}s as enum]
                                    <option value="<@show>enum.id</@show>"><@show>enum.desc</@show></option>
                                [/#list]
                            </select>
                        </p>
                        </#if>
                        </#if>
                    </#list>

                    <p class="w50p">
                        <button type="button" id="searchForm" class="btn" data-icon="s">Search</button>
                        <button type="button" id="reset" class="btn">Reset</button>
                    </p>
                </div>
            </menu>
            <div class="table">
                <table>
                    <tr>

                        <#list bean.fields as field>
                            <#if field.showList??>
                                <th>${field.showList}</th>
                            </#if>
                        </#list>
                        <th>Settings</th>
                    </tr>

                [#list page.content as item]
                    <tr>
                    <#list bean.fields as field>
                        <#if field.showList??>
                            <td><@show>item.${field.name}<#if field.type == "date">?datetime</#if></@show></td>
                        </#if>
                    </#list>
                        <td>
                            <span class="btn_i item_menu">
                                o<span data-itemid="<@show>item.${bean.primaryKey}</@show>">
                                    <#if module.edit??>
                                        <a href="edit.action?${bean.primaryKey}=<@show>item.${bean.primaryKey}</@show>"  class="edit" data-icon="e">Edit</a>
                                    </#if>
                                    <#if module.delete??>
                                        <a href="delete.action?${bean.primaryKey}=<@show>item.${bean.primaryKey}</@show>"  class="chg_stu" data-icon="d">Delete</a>
                                    </#if>
                                </span>
                            </span>

                        </td>
                    </tr>
                [/#list]
                </table>
            [@pagination pageNumber = page.pageNumber totalPages = page.totalPages]
                [#include "/include/pagination.ftl"]
            [/@pagination]
            </div>
        </form>
    </div>
</div>
<script>
    seajs.use("alert",function(alertM){
        $("#searchForm").click(function(){
            $("#pageNumber").val("1");
            $("#listForm").submit();
        });
        $("#reset").click(function(){
        <#list bean.fields as field>
            <#if field.search??>
                <#if field.search == "between">
            $("#${field.name}Start").val("");
            $("#${field.name}End").val("");
                <#else>
            $("#${field.name}").val("");
                </#if>
            </#if>
        </#list>
        });
    })
</script>
[#include "/include/footer.ftl"]
</body>
</html>