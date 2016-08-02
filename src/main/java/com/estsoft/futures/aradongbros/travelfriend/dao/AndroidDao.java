package com.estsoft.futures.aradongbros.travelfriend.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.estsoft.futures.aradongbros.travelfriend.vo.AttractionVo;
import com.estsoft.futures.aradongbros.travelfriend.vo.CityListVo;

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
    
    public CityListVo selectCityByNo(int no)
    {
    	CityListVo cityVo = sqlSession.selectOne("android.selectCityByNo", no);
    	
    	return cityVo;
    }
    
    public List<AttractionVo> getPinDataByCategory(int cityList_no, String category)
    {
    	Map<String, Object> map = new HashMap<String, Object>();
    	map.put("cityList_no", cityList_no);
    	map.put("category", category);
    	
    	List<AttractionVo> atrList = sqlSession.selectList("android.getPinDataByCategory", map);
    	
    	return atrList;
    }
}
