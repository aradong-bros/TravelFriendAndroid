package com.estsoft.futures.aradongbros.travelfriend.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.estsoft.futures.aradongbros.travelfriend.dto.TravelRootByCity;
import com.estsoft.futures.aradongbros.travelfriend.kruskal.Kruskal;
import com.estsoft.futures.aradongbros.travelfriend.service.AndroidService;
import com.estsoft.futures.aradongbros.travelfriend.vo.AttractionVo;
import com.estsoft.futures.aradongbros.travelfriend.vo.CityListVo;
import com.estsoft.futures.aradongbros.travelfriend.vo.CityVo;
import com.estsoft.futures.aradongbros.travelfriend.vo.PostVo;

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
	 * 4. getCityData : "cityList"의 PK를 매개변수로 받아서 각 도시의 정보를 가져온다.
	 * 5. getTravelRoot : "사용자가 선택한 관좡지 정보"를 받아서 최단 경로를 반환한다.
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
	 * category : 1-food, 2-inn, 3-tour
	 */
	@RequestMapping("/getPinData/{cityList_no}/{categoryNo}")
	@ResponseBody
	public Map<String, Object> getPinData(@PathVariable("cityList_no")int cityList_no,
										  @PathVariable("categoryNo") int categoryNo)
	{
		List<AttractionVo> atrList; 
		Map<String, Object> map = new HashMap<String, Object>();
		
		if ( categoryNo == 1 )
		{
			atrList = androidService.getPinDataByCategory(cityList_no, "food");
			map.put("atrList", atrList);
		}
		else if ( categoryNo == 2 )
		{
			atrList = androidService.getPinDataByCategory(cityList_no, "inn");
			map.put("atrList", atrList);
		}
		else if ( categoryNo == 3 )
		{
			atrList = androidService.getPinDataByCategory(cityList_no, "tour");
			map.put("atrList", atrList);
		}
		else
		{
			atrList = androidService.selectListByCityNo(cityList_no);
			map.put("atrList", atrList);
		}
				
		return map;
	}
	
	/*
	 * 2번 메소드
	 * 
	 * no : DB에 들어있는 postList의 PK 번호 임.
	 */
	@RequestMapping("/getThumbnailData/{no}")
	@ResponseBody
	public Map<String, Object> getThumbnailData(@PathVariable("no") int no)
	{
		AttractionVo atrVo = androidService.selectAtrByNo(no);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("atrVo", atrVo);
		
		return map;
	}
	
	/*
	 * 3번 메소드
	 * 
	 * no : DB에 들어있는 postList의 PK 번호 임.
	 */
	@RequestMapping("/getDetailData/{no}")
	@ResponseBody
	public Map<String, Object> getDetailData(@PathVariable("no") int no)
	{
		AttractionVo allAtrVo = androidService.selectAllAtrByNo(no);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("allAtrVo", allAtrVo);
		
		return map;
	}

	/*
	 * 4번 메소드
	 * 
	 * no : DB에 들어있는 cityList의 PK 번호 임.
	 * no 를  1 ~ 12 까지 넣으면 각 번호에 해당하는 관광지의 전체 정보를 가져온다.
	 */
	@RequestMapping("/getCityData/{no}")
	@ResponseBody
	public Map<String, Object> getCityData(@PathVariable("no") int no)
	{
		CityListVo cityVo = androidService.selectCityByNo(no);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("cityVo", cityVo);
		
		return map;
	}
	
	/*
	 * 5번 메소드 
	 * atrList : 사용자가 선택한 관과지의  no, location 정보를 담고 있는 AttractionVo 리스트
	 * 스케줄 - 도시 - 관광지 모든 담기 끝난후 '완료' 버튼 누르면 경로 계산할 수있게 schedule no 받아서 경로 계산 -> requestparam으로 할 것.
	 * url : http://222.239.250.207:8080/TravelFriendAndroid/android/getTravelRoot?schedule_no={schedule_no 값}
	 */
	@RequestMapping("/getTravelRoot")
	@ResponseBody							
	public Map<String, Object> getTravelRoot(@RequestParam("schedule_no") int schedule_no)  
	{
		Map<String, Object> map = new HashMap<String, Object>();
		List<AttractionVo> atrList = new ArrayList<AttractionVo>();
		
		List<CityVo> cityVoList = androidService.getCityList(schedule_no);
		
		TravelRootByCity[] travelRootByCity = new TravelRootByCity[cityVoList.size()];

		//------------------------------------------------------------------------------------------------
		
		for ( int i = 0; i < cityVoList.size(); i++ )
		{
			atrList.clear();
			
			List<PostVo> postVoList = androidService.getPostList(cityVoList.get(i).getNo());
			
			for ( int j = 0; j < postVoList.size(); j++ )
			{
				atrList.add(androidService.selectAtrByNo(postVoList.get(j).getPostList_no()));	
			}

		    map.put("cityNoList", cityVoList);
		    map.put("postListNoList", postVoList);
		    map.put("atrList", atrList);
			
		    Kruskal kruskal = new Kruskal(atrList);
		    map.put("kruskal", kruskal.getTravelRoot());
			//travelRootByCity[i].setTRAVEL_ROOT(kruskal.getTravelRoot());
		}

		//------------------------------------------------------------------------------------------------

		map.put("travelRootByCity", travelRootByCity);
		
		return map;
	}	
	//테스트 
	//컨트롤러 메소드 끼리 list를 주고받을 때 사용
	//받을땐 모텔어트리뷰터로 받는다.
	// http://222.239.250.207:8080/TravelFriendAndroid/android/test
/*	@RequestMapping("/test")  
	public String test(RedirectAttributes redirectAttributes)
	{
		System.out.println(androidService.selectAtrByNo(6588));
		
		List<AttractionVo> atrList = new ArrayList<AttractionVo>(); 
		atrList.add(androidService.selectAtrByNo(6588));
		atrList.add(androidService.selectAtrByNo(6574));
		atrList.add(androidService.selectAtrByNo(6570));
		atrList.add(androidService.selectAtrByNo(6564));
		
		redirectAttributes.addFlashAttribute("atrList", atrList);
			
		return "redirect:/android/getTravelRoot";
	}*/
	
	//localhost:8080/TravelFriendAndroid/android/test
	@RequestMapping("/test")
	@ResponseBody	 
	public Map<String, Object> test()
	{
		Map<String, Object> map = new HashMap<String, Object>();
		
		List<String> atrList = new ArrayList<String>();
		
		atrList.add(0, "1");
		atrList.add(1, "2");
		atrList.add(2, "3");
		atrList.add(3, "4");
		
		map.put("atrList", atrList);
		
		atrList.clear();
			
		return map;
	}
	
}
