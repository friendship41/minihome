package com.jsp.minihome.freeBoard.service;

import com.jsp.minihome.freeBoard.dao.FreeBoardDAO;
import com.jsp.minihome.freeBoard.vo.FreeBoardVO;

import java.util.List;

public class FreeBoardService
{
    public List<FreeBoardVO> getFreeBoardList(int startNum, int endNum)
    {
        FreeBoardDAO freeBoardDAO = FreeBoardDAO.getInstance();
        int cnt = freeBoardDAO.selectBoardCount();
        if(cnt < 0)
        {
            return null;
        }
        else
        {
            return freeBoardDAO.selectAllBoardList(startNum, endNum);
        }
    }

    public void writeFreeBoard(FreeBoardVO freeBoardVO)
    {
        FreeBoardDAO freeBoardDAO = FreeBoardDAO.getInstance();
        freeBoardDAO.insertNewBoard(freeBoardVO);
    }
}
