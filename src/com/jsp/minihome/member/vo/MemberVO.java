package com.jsp.minihome.member.vo;

public class MemberVO
{
    private String userId;
    private String userPassword;
    private String userName;
    private String userGender;
    private String userPhone;
    private String userClass;
    private String userStateMessage;
    private int userTotalVisit;
    private int userTodayVisit;

    public String getUserStateMessage()
    {
        return userStateMessage;
    }

    public void setUserStateMessage(String userStateMessage)
    {
        this.userStateMessage = userStateMessage;
    }

    public int getUserTotalVisit()
    {
        return userTotalVisit;
    }

    public void setUserTotalVisit(int userTotalVisit)
    {
        this.userTotalVisit = userTotalVisit;
    }

    public int getUserTodayVisit()
    {
        return userTodayVisit;
    }

    public void setUserTodayVisit(int userTodayVisit)
    {
        this.userTodayVisit = userTodayVisit;
    }

    public String getUserId()
    {
        return userId;
    }

    public void setUserId(String userId)
    {
        this.userId = userId;
    }

    public String getUserPassword()
    {
        return userPassword;
    }

    public void setUserPassword(String userPassword)
    {
        this.userPassword = userPassword;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getUserGender()
    {
        return userGender;
    }

    public void setUserGender(String userGender)
    {
        this.userGender = userGender;
    }

    public String getUserPhone()
    {
        return userPhone;
    }

    public void setUserPhone(String userPhone)
    {
        this.userPhone = userPhone;
    }

    public String getUserClass()
    {
        return userClass;
    }

    public void setUserClass(String userClass)
    {
        this.userClass = userClass;
    }
}
