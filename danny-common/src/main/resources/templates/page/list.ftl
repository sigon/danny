<#ftl/>
[#assign title="User List"]
[#assign header="user"]
[#assign nav="traderList"]
<!DOCTYPE html>
<html>
<head>
[#include "/include/head.ftl"]
    <script src="/js/datepicker/wdatepicker.js" type="text/javascript"></script>
</head>
<body>
<h1 data-icon="1">${bean.table}[#include "/include/user.ftl"]</h1>
[#include "/include/header.ftl"]
<div class="pages">
[#include "/include/nav.ftl"]
    <div class="page">
        <form id="listForm" action="list.action" method="post">
            <menu>
                <#if bean.moduleMap["add"]??>
                    <a href="add.action" class="btn add" data-icon="a">Create New</a>
                </#if>
                <div class="form">
                    <#list bean.fields as field>
                        <#if field.search??>

                        <#if field.search == "text">
                        <p class="w50p">
                            <label class="f_info">${field.name}</label>
                            <input type="text" id="${field.name}" name="${field.name}" value="<@show>${bean.table}.name</@show>" placeholder="${field.name}">
                        </p>
                        </#if>
                        <#if field.search == "between">
                            <p class="w50p">
                                <label class="f_info">Start ${field.nameUpper}</label>
                                <input type="text" id="start${field.nameUpper}" name="start${field.name}" onClick="WdatePicker()" class="Wdate" value="<@show>${bean.table}.start${field.nameUpper}</@show>"/>
                            </p>
                            <p class="w50p">
                                <label class="f_info">End ${field.nameUpper}</label>
                                <input type="text" id="end${field.nameUpper}" name="endDate" onClick="WdatePicker()" class="Wdate" value="<@show>${bean.table}end${field.nameUpper}</@show>"/>
                            </p>
                        </#if>
                        <#if field.search == "select">
                            <p class="w50p">
                                <label class="f_info">${field.name}</label>
                                <select id="${field.name}" name="${field.name}">
                                    [#list ${field.enumName}Enum as enum]
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
                            <td><@show>item.${field.name}</@show></td>
                        </#if>
                    </#list>
                        <td>
                            <#if bean.moduleMap["edit"]??>
                            <a href="/edit.action?${bean.primaryKey}=<@show>item.id</@show>" class="btn edit">Edit</a>
                            </#if>
                            <#if bean.moduleMap["delete"]??>
                                <a href="/delete.action?${bean.primaryKey}=<@show>item.id</@show>" class="btn delete">Delete</a>
                            </#if>
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