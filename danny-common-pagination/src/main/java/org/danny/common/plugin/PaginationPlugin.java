package org.danny.common.plugin;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.Document;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;

import java.util.List;

/**
 * Created by Administrator on 2016/11/9.
 */
public class PaginationPlugin extends PluginAdapter {

    @Override
    public boolean sqlMapSelectByExampleWithoutBLOBsElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
        XmlElement include = new XmlElement("include");
        include.addAttribute(new Attribute("refid", "MysqlDialectSuffix"));
        element.addElement(include);
        return super.sqlMapSelectByExampleWithoutBLOBsElementGenerated(element, introspectedTable);
    }

    @Override
    public boolean sqlMapDocumentGenerated(Document document, IntrospectedTable introspectedTable) {
        XmlElement root = document.getRootElement();
        XmlElement sql = new XmlElement("sql");
        sql.addAttribute(new Attribute("id", "MysqlDialectSuffix"));
        XmlElement iff = new XmlElement("if");
        iff.addAttribute(new Attribute("test", "page != null"));
        iff.addElement(new TextElement("limit #{page.begin},#{page.end}"));
        sql.addElement(iff);
        root.addElement(sql);
        return super.sqlMapDocumentGenerated(document, introspectedTable);
    }

    @Override
    public boolean modelExampleClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        topLevelClass.addImportedType("org.danny.common.pagination.Pageable");
        Field page = new Field("page", new FullyQualifiedJavaType("Pageable"));
        topLevelClass.addField(page);
        page.setVisibility(JavaVisibility.PRIVATE);
        addSetMethod(topLevelClass, "page");
        addGetMethod(topLevelClass, "page");
        return super.modelExampleClassGenerated(topLevelClass, introspectedTable);
    }

    private void addSetMethod(TopLevelClass topLevelClass, String fieldName){
        String camel = Character.toUpperCase(fieldName.charAt(0)) + fieldName.substring(1);
        Method method = new Method("set" + camel);
        method.setVisibility(JavaVisibility.PUBLIC);
        method.addParameter(new Parameter(new FullyQualifiedJavaType("Pageable"), fieldName));
        method.addBodyLine("this." + fieldName + " = " + fieldName + ";");
        topLevelClass.addMethod(method);
    }
    private void addGetMethod(TopLevelClass topLevelClass, String fieldName){
        String camel = Character.toUpperCase(fieldName.charAt(0)) + fieldName.substring(1);
        Method method = new Method("get" + camel);
        method.setVisibility(JavaVisibility.PUBLIC);
        method.addBodyLine("return " + fieldName + ";");
        method.setReturnType(new FullyQualifiedJavaType("Pageable"));
        topLevelClass.addMethod(method);
    }

    /**
     * This plugin is always valid - no properties are required
     */
    public boolean validate(List<String> warnings) {
        return true;
    }
}
