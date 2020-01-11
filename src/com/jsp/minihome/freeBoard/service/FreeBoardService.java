package com.jsp.minihome.freeBoard.service;

import com.jsp.minihome.freeBoard.dao.FreeBoardDAO;
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
}
