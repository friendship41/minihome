package com.jsp.minihome.freeBoard.vo;

public class FreeBoardCommentsVO
{
    private int commentsTableNum;
    private int freeBoardNum;
    private String writerId;
    private String writerName;
    private String commentContent;
    private String commentDate;
    private String writerProfileImgLoc;

    public String getWriterProfileImgLoc()
    {
        return writerProfileImgLoc;
    }

    public void setWriterProfileImgLoc(String writerProfileImgLoc)
    {
        this.writerProfileImgLoc = writerProfileImgLoc;
    }

    public int getCommentsTableNum()
    {
        return commentsTableNum;
    }

    public void setCommentsTableNum(int commentsTableNum)
    {
        this.commentsTableNum = commentsTableNum;
    }

    public int getFreeBoardNum()
    {
        return freeBoardNum;
    }

    public void setFreeBoardNum(int freeBoardNum)
    {
        this.freeBoardNum = freeBoardNum;
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

    public String getCommentContent()
    {
        return commentContent;
    }

    public void setCommentContent(String commentContent)
    {
        this.commentContent = commentContent;
    }

    public String getCommentDate()
    {
        return commentDate;
    }

    public void setCommentDate(String commentDate)
    {
        this.commentDate = commentDate;
    }
}
