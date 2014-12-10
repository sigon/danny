package net.sigon.danny.common.generate.bean;

/**
 * Created with IntelliJ IDEA.
 * User: sigon
 * Date: 14-12-9
 * Time: 下午8:26
 * To change this template use File | Settings | File Templates.
 */
public class Module {
    private String type;
    private String template;
    private Boolean delete;
    private String ignoreFields;

    public String getIgnoreFields() {
        return ignoreFields;
    }

    public void setIgnoreFields(String ignoreFields) {
        this.ignoreFields = ignoreFields;
    }

    public Boolean getDelete() {
        return delete;
    }

    public void setDelete(Boolean delete) {
        this.delete = delete;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }
}
