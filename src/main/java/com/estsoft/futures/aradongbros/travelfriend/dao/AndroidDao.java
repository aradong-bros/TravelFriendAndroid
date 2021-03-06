package com.estsoft.futures.aradongbros.travelfriend.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.estsoft.futures.aradongbros.travelfriend.vo.AttractionVo;
import com.estsoft.futures.aradongbros.travelfriend.vo.Category;
import com.estsoft.futures.aradongbros.travelfriend.vo.CityListVo;
import com.estsoft.futures.aradongbros.travelfriend.vo.CityVo;
import com.estsoft.futures.aradongbros.travelfriend.vo.PostVo;

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
    
    public List<CityVo> getCityList(int schedule_no)
    {
    	List<CityVo> cityVoList = sqlSession.selectList("android.getCityList", schedule_no);
    	
    	return cityVoList;
    }
    
    public List<PostVo> getPostList(int city_no)
	{
		List<PostVo> postVoList = sqlSession.selectList("android.getPostList", city_no);
		
		return postVoList;
	}
    
	public String getCategory(int no)
	{
		String category = sqlSession.selectOne("android.getCategory", no);
		
		return category;
	}
}
