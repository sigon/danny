package net.sigon.danny.common.generate.bean;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: sigon
 * Date: 14-12-9
 * Time: 下午8:38
 * To change this template use File | Settings | File Templates.
 */
public class Configuration {
    private List<String> classPaths;
    private JdbcConnection jdbcConnection;
    private List<Generate> generates;

    public List<String> getClassPaths() {
        return classPaths;
    }

    public void setClassPaths(List<String> classPaths) {
        this.classPaths = classPaths;
    }

    public JdbcConnection getJdbcConnection() {
        return jdbcConnection;
    }

    public void setJdbcConnection(JdbcConnection jdbcConnection) {
        this.jdbcConnection = jdbcConnection;
    }

    public List<Generate> getGenerates() {
        return generates;
    }

    public void setGenerates(List<Generate> generates) {
        this.generates = generates;
    }
}
