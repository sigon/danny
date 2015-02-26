package net.sigon.danny.common.generate.bean;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: sigon
 * Date: 14-12-9
 * Time: 下午8:30
 * To change this template use File | Settings | File Templates.
 */
public class Generate {
    private String project;
    private String path;
    private String author;
    private List<Bean> beans;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getFtlpath() {
        return String.format("%s/aunewtop-%s-web/src/main/webapp/WEB-INF/template", path, project);
    }

    public String getServicePath() {
        return String.format("%s/aunewtop-%s-service/src/main/java", path, project);
    }

    public String getServicePackage() {
        return String.format("com.aunewtop.%s.service", project);
    }


    public String getControllerPath() {
        return String.format("%s/aunewtop-%s-web/src/main/java", path, project);
    }


    public String getControllerPackage() {
        return String.format("com.aunewtop.%s.web.action", project);
    }


    public String getMapperPackage() {
        return String.format("com.aunewtop.common.dao.mapper");
    }


    public List<Bean> getBeans() {
        return beans;
    }

    public void setBeans(List<Bean> beans) {
        this.beans = beans;
    }

    public String getParamPath() {
        return String.format("%s/aunewtop-%s-domain/src/main/java", path, project);
    }

    public String getParamPackage() {
        return String.format("com.aunewtop.%s.domain.param", project);
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }
}
