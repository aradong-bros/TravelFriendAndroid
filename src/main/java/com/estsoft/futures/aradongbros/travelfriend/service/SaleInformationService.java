package com.estsoft.futures.aradongbros.travelfriend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estsoft.futures.aradongbros.travelfriend.dao.SaleInformationDao;
import com.estsoft.futures.aradongbros.travelfriend.vo.SaleInformationVo;

@Service
public class SaleInformationService 
{
	@Autowired
	private SaleInformationDao saleInfoDao;
	
	// 각 도시별 할인정보 조회
	public List<SaleInformationVo> getSaleInfoListByCity(int cityList_no)
	{
		List<SaleInformationVo> saleInfoListByCity = saleInfoDao.getSaleInfoListByCity(cityList_no);
		
		return saleInfoListByCity;
	}
}
