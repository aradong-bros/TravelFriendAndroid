package com.estsoft.futures.aradongbros.travelfriend.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.estsoft.futures.aradongbros.travelfriend.vo.SaleInformationVo;

@Repository
public class SaleInformationDao 
{
    @Autowired
    private SqlSession sqlSession;  // mybatis 사용하기위해 선언
    
	// 각 도시별 할인정보 조회
	public List<SaleInformationVo> getSaleInfoListByCity(int cityList_no)
	{
		List<SaleInformationVo> saleInfoListByCity = sqlSession.selectList("saleinformation.getSaleInfoListByCity", cityList_no);
		
		return saleInfoListByCity;
	}
}
