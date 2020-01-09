package com.jsp.minihome.freeBoard.vo;

public class FreeBoardVO
{
    private int no;
    private String writerId;
    private String writerName;
    private String email;
    private String subject;
    private String categories;
    private String content;
    private String imgLoc;
    private int readcnt;
    private int ref;
    private int step;
    private int depth;
    private String regdate;
    private String ip;

    public String getCategories()
    {
        return categories;
    }

    public void setCategories(String categories)
    {
        this.categories = categories;
    }

    public String getWriterId()
    {
        return writerId;
    }

    public void setWriterId(String writerId)
    {
        this.writerId = writerId;
    }

    public String getWriterName()
    {
        return writerName;
    }

    public void setWriterName(String writerName)
    {
        this.writerName = writerName;
    }

    public String getImgLoc()
    {
        return imgLoc;
    }

    public void setImgLoc(String imgLoc)
    {
        this.imgLoc = imgLoc;
    }

    public int getNo()
    {
        return no;
    }

    public void setNo(int no)
    {
        this.no = no;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getSubject()
    {
        return subject;
    }

    public void setSubject(String subject)
    {
        this.subject = subject;
    }

    public int getReadcnt()
    {
        return readcnt;
    }

    public void setReadcnt(int readcnt)
    {
        this.readcnt = readcnt;
    }

    public int getRef()
    {
        return ref;
    }

    public void setRef(int ref)
    {
        this.ref = ref;
    }

    public int getStep()
    {
        return step;
    }

    public void setStep(int step)
    {
        this.step = step;
    }

    public int getDepth()
    {
        return depth;
    }

    public void setDepth(int depth)
    {
        this.depth = depth;
    }

    public String getRegdate()
    {
        return regdate;
    }

    public void setRegdate(String regdate)
    {
        this.regdate = regdate;
    }

    public String getContent()
    {
        return content;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

    public String getIp()
    {
        return ip;
    }

    public void setIp(String ip)
    {
        this.ip = ip;
    }
}
