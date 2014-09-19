package net.sigon.danny.common.wechat;

/**
 * Created with IntelliJ IDEA.
 * User: Sguang
 * Date: 14-8-26
 * Time: 下午10:41
 * To change this template use File | Settings | File Templates.
 */
public class WechatResult {
    private String errcode;
    private String errmsg;
    private String access_token;
    private String expires_in;

    public String getErrcode() {
        return errcode;
    }

    public void setErrcode(String errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(String expires_in) {
        this.expires_in = expires_in;
    }
}
