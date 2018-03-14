package com.wong.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;
import java.util.Date;

//@Document注解指定了实体类对应的ES存储时的index（索引）名称和type（类型）名称
//@Id注解指定了主键
@Document(indexName="userindex",type="user")
public class User implements Serializable {
    @Id
    private Integer id;

    private Long mobile;

    private String password;

    private String bookClass;

    private Integer wechatUserId;

    private Integer qqUserId;

    private String channelId;

    private Short bookCnt;

    private Byte deviceType;

    private String header;

    private String nickname;

    private Byte gender;

    //@Field(format= DateFormat.date_time,index= FieldIndex.no,store=true,type= FieldType.Object)
    private Date birthday;

    private String signature;

    private String photoes;

    private Float longitude;

    private Float latitude;

    private Integer balance;

    private String uuid;

    private Date createTime;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getMobile() {
        return mobile;
    }

    public void setMobile(Long mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getBookClass() {
        return bookClass;
    }

    public void setBookClass(String bookClass) {
        this.bookClass = bookClass == null ? null : bookClass.trim();
    }

    public Integer getWechatUserId() {
        return wechatUserId;
    }

    public void setWechatUserId(Integer wechatUserId) {
        this.wechatUserId = wechatUserId;
    }

    public Integer getQqUserId() {
        return qqUserId;
    }

    public void setQqUserId(Integer qqUserId) {
        this.qqUserId = qqUserId;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId == null ? null : channelId.trim();
    }

    public Short getBookCnt() {
        return bookCnt;
    }

    public void setBookCnt(Short bookCnt) {
        this.bookCnt = bookCnt;
    }

    public Byte getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(Byte deviceType) {
        this.deviceType = deviceType;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header == null ? null : header.trim();
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    public Byte getGender() {
        return gender;
    }

    public void setGender(Byte gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature == null ? null : signature.trim();
    }

    public String getPhotoes() {
        return photoes;
    }

    public void setPhotoes(String photoes) {
        this.photoes = photoes == null ? null : photoes.trim();
    }

    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid == null ? null : uuid.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", mobile=").append(mobile);
        sb.append(", password=").append(password);
        sb.append(", bookClass=").append(bookClass);
        sb.append(", wechatUserId=").append(wechatUserId);
        sb.append(", qqUserId=").append(qqUserId);
        sb.append(", channelId=").append(channelId);
        sb.append(", bookCnt=").append(bookCnt);
        sb.append(", deviceType=").append(deviceType);
        sb.append(", header=").append(header);
        sb.append(", nickname=").append(nickname);
        sb.append(", gender=").append(gender);
        sb.append(", birthday=").append(birthday);
        sb.append(", signature=").append(signature);
        sb.append(", photoes=").append(photoes);
        sb.append(", longitude=").append(longitude);
        sb.append(", latitude=").append(latitude);
        sb.append(", balance=").append(balance);
        sb.append(", uuid=").append(uuid);
        sb.append(", createTime=").append(createTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}