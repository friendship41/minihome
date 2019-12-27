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
}
