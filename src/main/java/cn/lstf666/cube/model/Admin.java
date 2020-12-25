package cn.lstf666.cube.model;

import java.io.Serializable;

/**
 * @Author liaotao
 * @Date 2020/10/3 18:28
 * 管理员实体类
 */
public class Admin implements Serializable {

    private int aId;
    private String username;
    private String password;
    private String qqNumber;
    private String email;
    private String phoneNumber;
    private String sex;
    private String code;
    private String status;

    public Admin( String username, String password, String qqNumber, String email, String phoneNumber, String sex) {
        this.username = username;
        this.password = password;
        this.qqNumber = qqNumber;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.sex = sex;
    }

    public int getaId() {
        return aId;
    }

    public void setaId(int aId) {
        this.aId = aId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getQqNumber() {
        return qqNumber;
    }

    public void setQqNumber(String qqNumber) {
        this.qqNumber = qqNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "aId=" + aId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", qqNumber='" + qqNumber + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", sex='" + sex + '\'' +
                ", code='" + code + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
