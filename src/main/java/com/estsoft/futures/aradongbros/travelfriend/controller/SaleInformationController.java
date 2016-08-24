package com.estsoft.futures.aradongbros.travelfriend.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.estsoft.futures.aradongbros.travelfriend.service.SaleInformationService;
import com.estsoft.futures.aradongbros.travelfriend.vo.SaleInformationVo;

@Controller
@RequestMapping("/saleInfo")
public class SaleInformationController 
{
	@Autowired
	private SaleInformationService saleInfoService;
	
	// 각 도시별 할인정보 조회
	@RequestMapping("/getSaleInfoListByCity/{cityList_no}")
	@ResponseBody
	public Map<String, Object> getSaleInfoListByCity(@PathVariable("cityList_no")int cityList_no)
	{
		Map<String, Object> map = new HashMap<String, Object>();
		
		List<SaleInformationVo> saleInfoListByCity = saleInfoService.getSaleInfoListByCity(cityList_no);
		
		map.put("saleInfoListByCity", saleInfoListByCity);
		
		return map;
	}
}
