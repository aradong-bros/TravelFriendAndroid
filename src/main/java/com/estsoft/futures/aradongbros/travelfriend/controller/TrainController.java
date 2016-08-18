package com.estsoft.futures.aradongbros.travelfriend.controller;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.estsoft.futures.aradongbros.travelfriend.dto.StartEnd;
import com.estsoft.futures.aradongbros.travelfriend.service.AndroidService;
import com.estsoft.futures.aradongbros.travelfriend.service.CityService;
import com.estsoft.futures.aradongbros.travelfriend.service.ScheduleService;
import com.estsoft.futures.aradongbros.travelfriend.service.TrainScheduleService;
import com.estsoft.futures.aradongbros.travelfriend.service.TrainService;
import com.estsoft.futures.aradongbros.travelfriend.vo.ScheduleVo;
import com.estsoft.futures.aradongbros.travelfriend.vo.TrainScheduleVo;

import util.DateUtils;

@Controller
@RequestMapping("/train")
public class TrainController 
{
	@Autowired
	private TrainService trainService;
	@Autowired
	private ScheduleService scheduleService;
	@Autowired
	private TrainScheduleService trainScheduleService;
	@Autowired
	private AndroidService androidService; //관광지정보
	
	private boolean isAllTrue(boolean boolArr[]){
		for(int i=0; i<boolArr.length; i++){
			if(!boolArr[i]) return false;
		}
		return true;
	}
	
	//직통 기차 검색
	@RequestMapping("/getDirectPath")
	@ResponseBody
	public Map<String, Object> getDirectPath(
			@RequestParam("goDate")Date goDate,
			@RequestParam("goTime")Time goTime,
			@RequestParam("startStation")String startStation,
			@RequestParam("endStation")String endStation)
	{
		Map<String, Object> map = new HashMap<>();
		
		List<Map<String, Object>> trainTimeList = trainService.getTrainTimeList(
				startStation, endStation, goDate, goTime);
		
		map.put("trainTimeList", trainTimeList);
		return map;
	}
	
	//환승 기차 검색 결과
	@RequestMapping("/getTransferPath")
	@ResponseBody
	public Map<String, Object> getTransferPath(
			@RequestParam("goDate")Date goDate,
			@RequestParam("goTime")Time goTime,
			@RequestParam("startStation")String startStation,
			@RequestParam("endStation")String endStation)
	{
		Map<String, Object> map = new HashMap<>();
		
		List<Map<String, Object>> trainTimeList = trainService.getTransferTrainTimeList(
				startStation, endStation, goDate, goTime);
		
		map.put("trainTimeList", trainTimeList);
		return map;
	}
	
	//trainSchedule 짜기
	@RequestMapping("/makeTrainSchedule")
	@ResponseBody
	public String makeTrainSchedule(
			@ModelAttribute Map<String, Object> map,
			RedirectAttributes redirectAttributes)
	{
		int schedule_no = (int) map.get("schedule_no");
		
		List<StartEnd> list = (ArrayList<StartEnd>) map.get("StartEndByCity");
		List<Map<String,Object>> startEndList = new ArrayList<>();
		for(int i=0; i<list.size(); i++){
			Map<String, Object> startEnd = new HashMap<>();
			startEnd.put("city_no", list.get(i).getCity_no());
			startEnd.put("startStation", trainService.getNearStation(androidService.selectAllAtrByNo(list.get(i).getStart()).getLocation(), list.get(i).getCity_no()));
			startEnd.put("endStation", trainService.getNearStation(androidService.selectAllAtrByNo(list.get(i).getEnd()).getLocation(), list.get(i).getCity_no()));
//			startEnd.put("startStation", trainService.getNearStation(androidService.selectAllAtrByNo(list.get(i).getStart()).getLocation(), list.get(i).getCity_no()).get(0));
//			startEnd.put("endStation", trainService.getNearStation(androidService.selectAllAtrByNo(list.get(i).getEnd()).getLocation(), list.get(i).getCity_no()).get(0));
			startEnd.put("totalTime", list.get(i).getTOTAL_TIME());
			startEndList.add(startEnd);
		}
		
		List<Integer> cityOrderList = new ArrayList<>();
		int nowCityIndex = -1; //현재 갈 곳을 찾고있는 도시(startEndList의 index)
		boolean isUsed[] = new boolean[startEndList.size()]; //이미 갔던 도시인지 확인하는 배열. boolean의 기본값은 false
		TrainScheduleVo vo = new TrainScheduleVo();
		while(!isAllTrue(isUsed)){
			List<Map<String, Object>> operationTime = new ArrayList<>(); //?도시로 가는데 ?역에서 ?역으로 가고 ?시간 걸리는지 모아놓은 리스트(city_no, startStationName, endStationName, operationTime, 그외에도 경로 찾는것처럼 있음)
			if(nowCityIndex == -1){ //시작역 -> 첫도시
				ScheduleVo scheduleVo = scheduleService.selectScheduleData(schedule_no); //여행의 시작역을 찾기위해 스케줄을 받아온다.
				for(int i=0; i<startEndList.size(); i++){ //모든 도시를 돌면서
					List<String> nearStationList = (ArrayList<String>)startEndList.get(i).get("startStation");
					List<Map<String, Object>> mappedTrainTimeList = trainService.getTrainTimeList(scheduleVo.getFirstStation(), nearStationList.get(0), DateUtils.getDateByString(scheduleVo.getStartDate()), DateUtils.getTimeByString(scheduleVo.getStartDate())); //가장 가까운 역이랑 경로를 뽑음
					if(mappedTrainTimeList.isEmpty() || mappedTrainTimeList == null || mappedTrainTimeList.size() == 0){ //가장 가까운 역이랑 경로가 없으면
						for(int j=1; j<nearStationList.size(); j++){ //도시 안에서 다음으로 가까운 역이랑 경로를 뽑음
							mappedTrainTimeList.addAll(trainService.getTrainTimeList(scheduleVo.getFirstStation(), nearStationList.get(j), DateUtils.getDateByString(scheduleVo.getStartDate()), DateUtils.getTimeByString(scheduleVo.getStartDate())));
							if( !(mappedTrainTimeList.isEmpty() || mappedTrainTimeList == null || mappedTrainTimeList.size() == 0) ) break; //경로가 하나라도 있으면 그만 찾음
						}
					}
					
					if(mappedTrainTimeList.isEmpty() || mappedTrainTimeList == null || mappedTrainTimeList.size() == 0){ //직통이 결국 없으면 환승경로를 찾음
						for (String nearStationName : nearStationList) { //도시안에서 가장 가까운 역부터 환승경로를 찾음
							mappedTrainTimeList.addAll(trainService.getTransferTrainTimeList(scheduleVo.getFirstStation(), nearStationName, DateUtils.getDateByString(scheduleVo.getStartDate()), DateUtils.getTimeByString(scheduleVo.getStartDate())));
						}
					}

					for (Map<String, Object> map2 : mappedTrainTimeList) { //그 경로의 city_no와 cityIndex를 넣어줌
						map2.put("city_no", startEndList.get(i).get("city_no"));
						map2.put("cityIndex", i);
					}
					operationTime.addAll(mappedTrainTimeList);
				}
			}else{ //도시 -> 도시
				for(int i=0; i<startEndList.size(); i++){ //모든 도시를 돌면서
					if(isUsed[i] || i==nowCityIndex) continue; //갔던 도시와 지금 떠나려는 도시는 제외 (지금 떠나려는 도시도 아마 isUsed에 체크 되있을텐데 혹시 모르니)
					
//					List<String> nowNearStationList = (ArrayList<String>)startEndList.get(nowCityIndex).get("endStation");
//					List<String> nextNearStationList = (ArrayList<String>)startEndList.get(i).get("startStation");
//					List<Map<String, Object>> mappedTrainTimeList = trainService.getTrainTimeList(
//							nowNearStationList.get(0), 
//							nextNearStationList.get(0), 
//							new Date(DateUtils.getAddMillis(DateUtils.getDateByString(vo.getArrivalDate()), DateUtils.getTimeByString(vo.getArrivalDate()), (Time)startEndList.get(nowCityIndex).get("totalTime"))), 
//							new Time(DateUtils.getAddMillis(DateUtils.getDateByString(vo.getArrivalDate()), DateUtils.getTimeByString(vo.getArrivalDate()), (Time)startEndList.get(nowCityIndex).get("totalTime")))
//					);
//					if(mappedTrainTimeList.isEmpty() || mappedTrainTimeList == null || mappedTrainTimeList.size() == 0){
//						for(int j=0; j<nowNearStationList.size(); j++){
//							for(int k=0; j<nowNearStationList.size(); k++){
//								mappedTrainTimeList.addAll(trainService.getTrainTimeList(
//										nowNearStationList.get(j), 
//										nextNearStationList.get(k), 
//										new Date(DateUtils.getAddMillis(DateUtils.getDateByString(vo.getArrivalDate()), DateUtils.getTimeByString(vo.getArrivalDate()), (Time)startEndList.get(nowCityIndex).get("totalTime"))), 
//										new Time(DateUtils.getAddMillis(DateUtils.getDateByString(vo.getArrivalDate()), DateUtils.getTimeByString(vo.getArrivalDate()), (Time)startEndList.get(nowCityIndex).get("totalTime")))
//										)
//								);
//								if( !(mappedTrainTimeList.isEmpty() || mappedTrainTimeList == null || mappedTrainTimeList.size() == 0) ) break;
//							}
//						}
//					}
					
					
					
					
					
					List<Map<String, Object>> mappedTrainTimeList = trainService.getTrainTimeList(
							(String)startEndList.get(nowCityIndex).get("endStation"), 
							(String)startEndList.get(i).get("startStation"), 
							new Date(DateUtils.getAddMillis(DateUtils.getDateByString(vo.getArrivalDate()), DateUtils.getTimeByString(vo.getArrivalDate()), (Time)startEndList.get(i).get("totalTime"))), 
							new Time(DateUtils.getAddMillis(DateUtils.getDateByString(vo.getArrivalDate()), DateUtils.getTimeByString(vo.getArrivalDate()), (Time)startEndList.get(i).get("totalTime")))
					); //직통열차 찾음(line128~133)
					if(mappedTrainTimeList.isEmpty() || mappedTrainTimeList == null || mappedTrainTimeList.size() == 0){ //직통열차 없으면
						mappedTrainTimeList = trainService.getTransferTrainTimeList(
								(String)startEndList.get(nowCityIndex).get("endStation"), 
								(String)startEndList.get(i).get("startStation"), 
								new Date(DateUtils.getAddMillis(DateUtils.getDateByString(vo.getArrivalDate()), DateUtils.getTimeByString(vo.getArrivalDate()), (Time)startEndList.get(i).get("totalTime"))), 
								new Time(DateUtils.getAddMillis(DateUtils.getDateByString(vo.getArrivalDate()), DateUtils.getTimeByString(vo.getArrivalDate()), (Time)startEndList.get(i).get("totalTime")))
						); //환승을 찾는다(line135~140)
					}
					for (Map<String, Object> map2 : mappedTrainTimeList) { //그 경로의 city_no와 cityIndex를 넣어줌
						map2.put("city_no", startEndList.get(i).get("city_no"));
						map2.put("cityIndex", i);
					}
				}
			}
			
			//전주 <-> 안동 처럼 직통과 1회 환승이 전혀 없는 경우
			if(operationTime.isEmpty() || operationTime.size() == 0){
				
			}
			
			Collections.sort(operationTime, new TrainService.OperationTimeASCCompare()); //빨리 가는 순서대로 정렬
			//빨리 가는거 5개 정도 뽑음
			List<Map<String,Object>> subList;
			if(operationTime.size() > 5){
				subList = operationTime.subList(0, 5);
			}else{
				subList = operationTime;
			}
			Collections.sort(subList, new TrainService.DepTimeASCCompare()); //빨리 출발하는 순서대로 정렬
			Map<String, Object> fastestRouteMap = subList.get(0); //빨리 가는거 5개중 가장 빨리 출발하는거 뽑음
			cityOrderList.add((Integer)fastestRouteMap.get("city_no"));
			isUsed[(int)fastestRouteMap.get("cityIndex")] = true;
			nowCityIndex = (int)fastestRouteMap.get("cityIndex");
			
			//TrainSchedule 만들고 디비에 저장하기
			vo.setSchedule_no(schedule_no);
			vo.setCity_no((int)fastestRouteMap.get("city_no"));
			vo.setStartStation((String)fastestRouteMap.get("startStationName"));
			vo.setEndStation((String)fastestRouteMap.get("endStationName"));
			vo.setStartDate(DateUtils.getDateTimeString((Date)fastestRouteMap.get("goDate"), (Time)fastestRouteMap.get("departureTime")));
			vo.setArrivalDate(
					DateUtils.getDateTimeString(
							new Date(DateUtils.getAddMillis((Date)fastestRouteMap.get("goDate"), (Time)fastestRouteMap.get("departureTime"), (Time)fastestRouteMap.get("operationTime"))), 
							new Time(DateUtils.getAddMillis((Date)fastestRouteMap.get("goDate"), (Time)fastestRouteMap.get("departureTime"), (Time)fastestRouteMap.get("operationTime")))
					)
			);
			trainScheduleService.insertTrainSchedule(vo);
		}
		
		redirectAttributes.addFlashAttribute("cityOrderList", cityOrderList);
		return "redirect:/android/cityModifyOrder";
	}
	
	//기차역 이름 조회
	@RequestMapping("/getTrainStationName")
	@ResponseBody
	public List<String> getTrainStationName()
	{
		List<String> stationNameList = trainService.getAllStationName();
		return stationNameList;
	}
}