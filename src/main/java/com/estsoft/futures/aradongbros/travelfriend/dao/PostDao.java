package com.estsoft.futures.aradongbros.travelfriend.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import com.estsoft.futures.aradongbros.travelfriend.vo.CityVo;
import com.estsoft.futures.aradongbros.travelfriend.vo.PostVo;

@Repository
public class PostDao 
{
    @Autowired
    private SqlSession sqlSession;  // mybatis 사용하기위해 선언
    
	// 조회
	public List<PostVo> selectPostListBySchedule(int city_no)
	{
		List<PostVo> postListBySchedule = sqlSession.selectList("post.selectPostListBySchedule", city_no);
				
		return postListBySchedule;
	}
	
	//selectPostCountByCity
	public int selectPostCountByCity(@PathVariable int city_no)
	{	
		int postCount = 0;
		
		postCount = sqlSession.selectOne("post.selectPostCountByCity", city_no);
		
		return postCount;
	}
    
	// 삽입
	public void insertPostData(PostVo postVo)
	{
		sqlSession.insert("post.insertPostData", postVo);
	}
	
	// 삭제
	public void deletePostData(int no)
	{			
		sqlSession.delete("post.deletePostData", no);
	}
	
	// city와 함께 삭제
	public void deleteCityRelatedData(int no)
	{
		sqlSession.delete("post.deleteCityRelatedData", no);
	}
	
	// 스케줄과 함께 삭제
	public void deleteCityRelatedDataInSchedule(int no)
	{
		List<CityVo> cityList = sqlSession.selectList("city.selectCityBySchNo", no);
		
		for ( int i = 0; i < cityList.size(); i++ )
		{
			int city_no = cityList.get(i).getNo();
			sqlSession.delete("post.deleteCityRelatedDataInSchedule", city_no);
		}
	}
	
	// 수정 : postOrder
	public void modifyPostOrder(int postList_no, int postOrder)
	{
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("postList_no", postList_no);
		map.put("postOrder", postOrder);
		
		sqlSession.update("post.modifyPostOrder", map);
	}

	public List<Map<String,Object>> selectPostByCity_no(int city_no) 
	{
		return sqlSession.selectList("post.selectPostByCity_no", city_no);
	}

	public List<Map<String,Object>> selectPostBySchedule_no(int schedule_no) 
	{
		return sqlSession.selectList("post.selectPostBySchedule_no", schedule_no);
	}
}
