package com.jsp.minihome.visit.service;

import com.jsp.minihome.visit.dao.VisitDAO;
import com.jsp.minihome.visit.vo.VisitVO;

import java.util.List;

public class VisitService
{
    private VisitDAO visitDAO;

    public List<VisitVO> getVisitList(String id)
    {
        visitDAO = VisitDAO.getInstance();
        return visitDAO.selectAllVisit(id);
    }

    public void writeVisit(VisitVO visitVO)
    {
        visitDAO = VisitDAO.getInstance();
        visitDAO.insertSingleVisit(visitVO);
    }

    public void deleteVisit(int no)
    {
        visitDAO = VisitDAO.getInstance();
        visitDAO.deleteSingleVisit(no);
    }

}
