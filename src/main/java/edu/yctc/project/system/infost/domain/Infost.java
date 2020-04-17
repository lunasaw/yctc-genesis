package edu.yctc.project.system.infost.domain;

import edu.yctc.framework.aspectj.lang.annotation.Excel;
import edu.yctc.framework.web.domain.BaseEntity;
import edu.yctc.project.system.academy.domain.Academy;
import edu.yctc.project.system.classes.domain.Classes;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 学生/老师档案对象 sys_infost
 * 
 * @author yctc
 * @date 2020-04-17
 */
public class Infost extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 学生编号 */
    private Long              id;

    /** 用户编号 */
    @Excel(name = "用户编号")
    private Long              userId;

    /** 学院编号 */
    @Excel(name = "学院编号")
    private Long              academyId;

    /** 班级编号 */
    @Excel(name = "班级编号")
    private Long              classId;

    /** 学号/工号 */
    @Excel(name = "学号/工号")
    private String            number;

    /** 姓名 */
    @Excel(name = "姓名")
    private String            name;

    /** 用户类别 */
    @Excel(name = "用户类别")
    private Long              type;

    /** 性别 */
    @Excel(name = "性别")
    private String            sex;

    /** 身份证号 */
    @Excel(name = "身份证号")
    private String            idCardNumber;

    /** 手机号 */
    @Excel(name = "手机号")
    private String            phone;

    /** 邮箱 */
    @Excel(name = "邮箱")
    private String            mail;

    /** 人脸照片 */
    @Excel(name = "人脸照片")
    private String            picture;

    /** 人脸标识 */
    @Excel(name = "人脸标识")
    private String            faceToken;

    /** 修改时间 */
    @Excel(name = "修改时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date              modifyTime;

    private Classes classes;

	public Classes getClasses() {
		if (classes == null)
		{
			classes = new Classes();
		}
		return classes;
	}

	public void setClasses(Classes classes) {
		this.classes = classes;
	}

	public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setAcademyId(Long academyId) {
        this.academyId = academyId;
    }

    public Long getAcademyId() {
        return academyId;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
    }

    public Long getClassId() {
        return classId;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getNumber() {
        return number;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setType(Long type) {
        this.type = type;
    }

    public Long getType() {
        return type;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getSex() {
        return sex;
    }

    public void setIdCardNumber(String idCardNumber) {
        this.idCardNumber = idCardNumber;
    }

    public String getIdCardNumber() {
        return idCardNumber;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getMail() {
        return mail;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getPicture() {
        return picture;
    }

    public void setFaceToken(String faceToken) {
        this.faceToken = faceToken;
    }

    public String getFaceToken() {
        return faceToken;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userId", getUserId())
            .append("academyId", getAcademyId())
            .append("classId", getClassId())
            .append("number", getNumber())
            .append("name", getName())
            .append("type", getType())
            .append("sex", getSex())
            .append("idCardNumber", getIdCardNumber())
            .append("phone", getPhone())
            .append("mail", getMail())
            .append("picture", getPicture())
            .append("faceToken", getFaceToken())
            .append("createTime", getCreateTime())
            .append("modifyTime", getModifyTime())
            .toString();
    }
}
