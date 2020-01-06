package com.jsp.minihome.visit.vo;

public class VisitVO
{
    private int visitNo;
    private String userId;
    private String writerId;
    private String userName;
    private String contents;
    private String writeDate;


    public int getVisitNo()
    {
        return visitNo;
    }

    public void setVisitNo(int visitNo)
    {
        this.visitNo = visitNo;
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public String getUserId()
    {
        return userId;
    }

    public void setUserId(String userId)
    {
        this.userId = userId;
    }

    public String getWriterId()
    {
        return writerId;
    }

    public void setWriterId(String writerId)
    {
        this.writerId = writerId;
    }

    public String getContents()
    {
        return contents;
    }

    public void setContents(String contents)
    {
        this.contents = contents;
    }

    public String getWriteDate()
    {
        return writeDate;
    }

    public void setWriteDate(String writeDate)
    {
        this.writeDate = writeDate;
    }
}
