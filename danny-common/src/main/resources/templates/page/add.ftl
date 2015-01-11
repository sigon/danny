<#ftl/>
[#if ${bean.table}.${bean.primaryKey}??]
    [#assign method="update"]
    [#assign methodTitle="Update"]
[#else]
    [#assign method="add"]
    [#assign methodTitle="Add"]
[/#if]
[#assign title="${bean.nameUpper} <@show>methodTitle</@show>"]
[#assign header="${bean.table}"]
[#assign nav="${bean.table}<@show>methodTitle</@show>"]
<!DOCTYPE html>
<html>
<head>
[#include "/include/head.ftl"]
</head>
<body>

<h1 data-icon="1">${bean.nameUpper}[#include "/include/user.ftl"]</h1>
    [#include "/include/header.ftl"]
    <div class="pages">
        [#include "/include/nav.ftl"]
        <div class="page">

            <form id="inputForm" method="post" class="form" action="<@show>method</@show>">
                    <input type="hidden" name="${bean.primaryKey}" value="<@show>${bean.table}.${bean.primaryKey}?if_exists</@show>">
                    <#list fields as field>
                        <#if field.type??>
                            <#if field.type == "radio">
                        <p>
                            <label class="f_info">${field.nameUpper}</label>
                            [#list ${field.enumName}s as item]
                            <input type="radio" required name="${field.name}" value="<@show>item.id</@show>" id="${field.name}<@show>item.id</@show>" [#if item.id==${bean.table}.${field.name}] checked="checked"[/#if]/>
                            <label for="${field.name}<@show>item.id</@show>" class="f_for"><@show>item.desc</@show></label>
                            [/#list]
                        </p>
                            <#elseif field.type == "select">
                        <p>
                            <label class="f_info">${field.nameUpper}</label>
                            <select id="${field.name}" name="${field.name}" <#if field.required??>required</#if>>
                                <option value="">select...</option>
                                [#list ${field.enumName}s as item]
                                <option value="<@show>item.id</@show>" [#if item.id==${bean.table}.enable] selected="selected" [/#if]><@show>item.desc</@show></option>
                                [/#list]
                            </select>
                        </p>
                            </#if>
                        <#else>
                    <p>
                        <label class="f_info">${field.nameUpper} <#if field.required??><span class="red">*</span></#if></label>
                        <input type="text" name="${field.name}" <#if field.required??>required</#if> <#if field.pattern??>pattern="${field.pattern}"</#if> value="<@show>${bean.table}.${field.name}?if_exists</@show>" placeholder=""/><span class="gray">${field.nameUpper}</span>
                    </p>
                        </#if>
                    </#list>

                    <p>
                        <button type="submit" class="btn bg_blue">Submit</button>
                        <input type="button" value="Cancel" id="go" class="btn"/>
                    </p>

            </form>

        </div>
    </div>
    <script>
        seajs.use("alert",function(alertM){
            $("#inputForm").on("submit",function(){
                if($un_info.is(".red")) {
                    $un.trigger("focus");
                    return false;
                }
                $.ajax({
                    url:"<@show>method</@show>.action",
                    dataType:"json",
                    method:"post",
                    data:$("#inputForm").serialize()
                }).done(function(data){
                    alertM(data.msg,{cls:data.status,rf:function(){
                        if(data.status=="succ")
                            window.location.href="list.action";
                    }})

                })
                return false;
            })
            $("#go").on("click",function(){
                window.location.href="list.action";
            })

        })
    </script>
[#include "/include/footer.ftl"]
</body>
</html>