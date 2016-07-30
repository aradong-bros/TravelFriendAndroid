package com.estsoft.futures.aradongbros.travelfriend.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.estsoft.futures.aradongbros.travelfriend.vo.AttractionVo;

@Repository
public class AndroidDao 
{
    @Autowired
    private SqlSession sqlSession;  // mybatis 사용하기위해 선언
    
    public List<AttractionVo> selectListByCityNo(int cityList_no)
    {
    	List<AttractionVo> atrList = sqlSession.selectList("android.selectListByCityNo", cityList_no);
    	
    	return atrList;
    }
    
    public AttractionVo selectAtrByNo(int no)
    {
    	AttractionVo atrVo = sqlSession.selectOne("android.selectAtrByNo", no);
    	
    	return atrVo;
    }
    
    public AttractionVo selectAllAtrByNo(int no)
    {
    	AttractionVo allAtrVo = sqlSession.selectOne("android.selectAllAtrByNo", no);
    	
    	return allAtrVo;
    }
}
