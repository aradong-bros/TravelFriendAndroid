package com.estsoft.futures.aradongbros.travelfriend.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.estsoft.futures.aradongbros.travelfriend.service.AndroidService;
import com.estsoft.futures.aradongbros.travelfriend.vo.AttractionVo;

@Controller
@RequestMapping("/android")
public class AndroidController 
{
	@Autowired
	private AndroidService androidService;
	
	/**
	 * 세 종류의 메소드가 있다.
	 * 
	 * 1. getPinData : "지역 번호"를 매개변수로 받아서 지역별 핀의 정보만 가져온다  -> PK, 위치, 카테고리  -> 지역별 관광정보 반환
	 * 2. getThumbnailData : "관광지 정보 PK"를 매개변수로 받아서 핀 눌렀을때 나오는 썸네일 정보를 가져온다 -> PK, 위치, 카테고리  + 타이틀, 사진  -> 관광지 정보 1개 반환
	 * 3. getDetailData : "관광지 정보 PK"를 매개변수로 받아서 썸네일 눌렀을때 나오는 상세 정보를 가져온다 -> 관광지의 모든 정보 가져온다. -> 관광지 정보 1개 반환
	 * 
	 * ★★★★★주의 : location 없는  7개 데이터를 삭제해서  빠져있는 데이터가 7개 있음!!! 근데 이게 중간에 PK가 구멍이 뚫린채로 있어서 나중에 다시 정리 하겠음!!
	 * 구멍뚫린 번호 : 254, 1849, 3937, 4199, 4284, 4634, 5237 -> postList의 PK
	 * 
	 */
	
	/*
	 * 1번 메소드
	 * 
	 * cityList_no : DB에 들어있는 cityList의 no(PK) 번호 임. (주의 : 지역코드 아님)
	 * cityList_no 를  1 ~ 12 까지 넣으면 각 번호에 해당하는 지역의 정보를 가져온다.
	 */
	@RequestMapping("/getPinData/{cityList_no}")
	@ResponseBody
	public Map<String, Object> getPinData(@PathVariable("cityList_no")int cityList_no)
	{
		List<AttractionVo> atrList = androidService.selectListByCityNo(cityList_no);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("atrList", atrList);
		
		return map;
	}
	
	@RequestMapping("/getThumbnailData/{no}")
	@ResponseBody
	public Map<String, Object> getThumbnailData(@PathVariable("no") int no)
	{
		AttractionVo atrVo = androidService.selectAtrByNo(no);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("atrVo", atrVo);
		
		return map;
	}
	
	@RequestMapping("/getDetailData/{no}")
	@ResponseBody
	public Map<String, Object> getDetailData(@PathVariable("no") int no)
	{
		AttractionVo allAtrVo = androidService.selectAllAtrByNo(no);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("allAtrVo", allAtrVo);
		
		return map;
	}

}
