package com.jsp.minihome.freeBoard.service;

import com.jsp.minihome.freeBoard.dao.FreeBoardCommentsDAO;
import com.jsp.minihome.freeBoard.dao.FreeBoardDAO;
import com.jsp.minihome.freeBoard.vo.FreeBoardCommentsVO;
import com.jsp.minihome.freeBoard.vo.FreeBoardVO;

import java.util.List;

public class FreeBoardService
{
    public int getFreeBoardCount()
    {
        FreeBoardDAO freeBoardDAO = FreeBoardDAO.getInstance();
        return freeBoardDAO.selectBoardCount();
    }

    public List<FreeBoardVO> getFreeBoardList(int startNum, int endNum)
    {
        FreeBoardDAO freeBoardDAO = FreeBoardDAO.getInstance();
        return freeBoardDAO.selectAllBoardList(startNum, endNum);
    }

    public void writeFreeBoard(FreeBoardVO freeBoardVO)
    {
        FreeBoardDAO freeBoardDAO = FreeBoardDAO.getInstance();
        freeBoardDAO.insertNewBoard(freeBoardVO);
    }

    public FreeBoardVO getSingleBoard(int no)
    {
        FreeBoardDAO freeBoardDAO = FreeBoardDAO.getInstance();
        freeBoardDAO.updateReadCnt(no);
        return freeBoardDAO.selectSingleFreeBoard(no);
    }

    public List<FreeBoardCommentsVO> getComments(int no)
    {
        FreeBoardCommentsDAO freeBoardCommentsDAO = FreeBoardCommentsDAO.getInstance();
        return freeBoardCommentsDAO.selectAllFreeBoardCommentsList(no);
    }


    public void writeComment(FreeBoardCommentsVO freeBoardCommentsVO)
    {
        FreeBoardCommentsDAO freeBoardCommentsDAO = FreeBoardCommentsDAO.getInstance();
        freeBoardCommentsDAO.insertComment(freeBoardCommentsVO);
    }

    public void deleteComment(int commentsTableNum)
    {
        FreeBoardCommentsDAO freeBoardCommentsDAO = FreeBoardCommentsDAO.getInstance();
        freeBoardCommentsDAO.deleteComment(commentsTableNum);
    }

}
