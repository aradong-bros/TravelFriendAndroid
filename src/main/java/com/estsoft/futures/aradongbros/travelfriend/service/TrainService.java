package com.estsoft.futures.aradongbros.travelfriend.service;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estsoft.futures.aradongbros.travelfriend.dao.ScheduleDao;
import com.estsoft.futures.aradongbros.travelfriend.dao.TrainDao;
import com.estsoft.futures.aradongbros.travelfriend.dao.TrainScheduleDao;
import com.estsoft.futures.aradongbros.travelfriend.dto.StartEndStation;
import com.estsoft.futures.aradongbros.travelfriend.vo.ScheduleVo;
import com.estsoft.futures.aradongbros.travelfriend.vo.TrainInfoVo;
import com.estsoft.futures.aradongbros.travelfriend.vo.TrainOperationRouteVo;
import com.estsoft.futures.aradongbros.travelfriend.vo.TrainScheduleVo;
import com.estsoft.futures.aradongbros.travelfriend.vo.TrainStationVo;

import util.DateUtils;

@Service
public class TrainService 
{
	@Autowired
	private TrainDao trainDao;
	@Autowired
	private TrainScheduleDao trainScheduleDao;
	@Autowired
	private ScheduleDao scheduleDao;
	
	//출발시간이 빠른 순으로 정렬
	public static class DepTimeASCCompare implements Comparator<Map<String,Object>>{
		@Override
		public int compare(Map<String, Object> o1, Map<String, Object> o2) {
			Time t1 = (Time) o1.get("departureTime");
			Time t2 = (Time) o2.get("departureTime");
			return t1.compareTo(t2);
		}
	}
	
	//거리가 짧은 순으로 정렬
	public static class DistanceASCCompare implements Comparator<Map<String, Object>>{
		@Override
		public int compare(Map<String, Object> distanceMap1, Map<String, Object> distanceMap2) {
			Double d1 = (Double) distanceMap1.get("distance");
			Double d2 = (Double) distanceMap2.get("distance");
			return d1.compareTo(d2);
		}
	}
	
	//걸린시간이 짧은 순으로 정렬
	public static class OperationTimeASCCompare implements Comparator<Map<String,Object>>{
		@Override
		public int compare(Map<String,Object>o1, Map<String,Object> o2){
			Time t1 = (Time) o1.get("operationTime");
			Time t2 = (Time) o2.get("operationTime");
			return t1.compareTo(t2);
		}
	}
	
	//걸린시간이 짧은 5개중 가장 빨리 출발하는 열차의 운행정보 구하기
	public Map<String, Object> getFastRouteMap(
			List<Map<String, Object>> operationTime)
	{
		Collections.sort(operationTime, new OperationTimeASCCompare()); //빨리 가는 순서대로 정렬
		//빨리 가는거 5개 정도 뽑음
		List<Map<String,Object>> subList;
		if(operationTime.size() > 5){
			subList = operationTime.subList(0, 5);
		}else{
			subList = operationTime;
		}
		
		Collections.sort(subList, new DepTimeASCCompare()); //빨리 출발하는 순서대로 정렬
		
		return subList.get(0);
	}
	
	//두 점 사이에 거리 구하기
	public double getDistance(double x, double y, double x1, double y1)
	{
		return Math.sqrt(Math.pow(Math.abs(x1-x), 2) + Math.pow(Math.abs(y1-y), 2));
	}
	
	//요일 구분넘버 가져오기
	public Integer getCategoryNo(Date goDate)
	{
		Calendar c = Calendar.getInstance();
		c.setTime(goDate);
		Integer dayOfWeek = c.get(Calendar.DAY_OF_WEEK); //수요일부터 1, 목2, 금3, 토4, 일5, 월6, 화7
		return dayOfWeek;
	}
	
	//모든 도시를 갔는지 확인
	public boolean isAllTrue(boolean boolArr[]){
		for(int i=0; i<boolArr.length; i++)
			if(!boolArr[i]) return false;
		
		return true;
	}
	
	//역이름으로 역번호 가져오기
	public Integer getStationNo(String stationName)
	{
		Integer no = trainDao.getStationNo(stationName);
		
		if(no == null || no == 0) return 0;
		else return no;
	}

	//직통 기차경로
	public List<Map<String, Object>> getTrainTimeList(
			String startStation, String endStation, Date goDate, Time goTime) 
	{
		int startStationNo = getStationNo(startStation);
		int endStationNo = getStationNo(endStation);
		int weekdayNo = getCategoryNo(goDate);
		
		List<TrainOperationRouteVo> trainStartTimeList = trainDao.getStartTrainTimeList(startStationNo, endStationNo, 1, goTime);
		List<TrainOperationRouteVo> trainEndTimeList = trainDao.getEndTrainTimeList(startStationNo, endStationNo, 1, goTime);
		
		switch (weekdayNo) {
		case 6: //월
		case 7: //화
		case 1: //수
		case 2: //목
			trainStartTimeList.addAll(trainDao.getStartTrainTimeList(startStationNo, endStationNo, 2, goTime));
			trainEndTimeList.addAll(trainDao.getEndTrainTimeList(startStationNo, endStationNo, 2, goTime));
			break;
		case 3: //금
			trainStartTimeList.addAll(trainDao.getStartTrainTimeList(startStationNo, endStationNo, 2, goTime));
			trainEndTimeList.addAll(trainDao.getEndTrainTimeList(startStationNo, endStationNo, 2, goTime));
			
			trainStartTimeList.addAll(trainDao.getStartTrainTimeList(startStationNo, endStationNo, 3, goTime));
			trainEndTimeList.addAll(trainDao.getEndTrainTimeList(startStationNo, endStationNo, 3, goTime));
			
			trainStartTimeList.addAll(trainDao.getStartTrainTimeList(startStationNo, endStationNo, 4, goTime));
			trainEndTimeList.addAll(trainDao.getEndTrainTimeList(startStationNo, endStationNo, 4, goTime));
			break;
		case 4: //토
			trainStartTimeList.addAll(trainDao.getStartTrainTimeList(startStationNo, endStationNo, 3, goTime));
			trainEndTimeList.addAll(trainDao.getEndTrainTimeList(startStationNo, endStationNo, 3, goTime));
			
			trainStartTimeList.addAll(trainDao.getStartTrainTimeList(startStationNo, endStationNo, 4, goTime));
			trainEndTimeList.addAll(trainDao.getEndTrainTimeList(startStationNo, endStationNo, 4, goTime));
			
			trainStartTimeList.addAll(trainDao.getStartTrainTimeList(startStationNo, endStationNo, 5, goTime));
			trainEndTimeList.addAll(trainDao.getEndTrainTimeList(startStationNo, endStationNo, 5, goTime));
			
			trainStartTimeList.addAll(trainDao.getStartTrainTimeList(startStationNo, endStationNo, 6, goTime));
			trainEndTimeList.addAll(trainDao.getEndTrainTimeList(startStationNo, endStationNo, 6, goTime));
			break;
		case 5: //일
			trainStartTimeList.addAll(trainDao.getStartTrainTimeList(startStationNo, endStationNo, 3, goTime));
			trainEndTimeList.addAll(trainDao.getEndTrainTimeList(startStationNo, endStationNo, 3, goTime));
			
			trainStartTimeList.addAll(trainDao.getStartTrainTimeList(startStationNo, endStationNo, 5, goTime));
			trainEndTimeList.addAll(trainDao.getEndTrainTimeList(startStationNo, endStationNo, 5, goTime));
			
			trainStartTimeList.addAll(trainDao.getStartTrainTimeList(startStationNo, endStationNo, 7, goTime));
			trainEndTimeList.addAll(trainDao.getEndTrainTimeList(startStationNo, endStationNo, 7, goTime));
			break;
		default:
			break;
		}

		List<Map<String, Object>> mappedTrainTimeList = new ArrayList<>();
		
		for(int i=0; i<trainStartTimeList.size(); i++){
			TrainInfoVo trainInfoVo = trainDao.selectTrainInfoByNo(trainStartTimeList.get(i).getTrainInfo_no());
			
			Map<String, Object> map = new HashMap<>();
			map.put("startStationName", startStation);
			map.put("endStationName", endStation);
			map.put("departureTime", trainStartTimeList.get(i).getDepartureTime());
			map.put("arrivalTime", trainEndTimeList.get(i).getDepartureTime());
			map.put("trainNum", trainInfoVo.getTrainNum());
			map.put("trainModel", trainInfoVo.getTrainModel());
			map.put("goDate", goDate);
			
			int startHour = trainStartTimeList.get(i).getDepartureTime().getHours();
			int startMinut = trainStartTimeList.get(i).getDepartureTime().getMinutes();
			int endHour = trainEndTimeList.get(i).getDepartureTime().getHours();
			int endMinut = trainEndTimeList.get(i).getDepartureTime().getMinutes();
			int startTime = (startHour*60) + startMinut;
			int endTime = (endHour*60) + endMinut;
			if(startTime > endTime) endTime += (24*60);
			int operationTime = endTime - startTime;
			map.put("operationTime", new Time(operationTime/60, operationTime%60, 0));
			
			mappedTrainTimeList.add(map);
		}
		
		return mappedTrainTimeList;
	}

	//환승 기차경로
	public List<Map<String, Object>> getTransferTrainTimeList(
			String startStation, String endStation, Date goDate, Time goTime) 
	{
		int startStationNo = getStationNo(startStation);
		int endStationNo = getStationNo(endStation);
		int weekdayNo = getCategoryNo(goDate);
		
		List<Map<String, Object>> mappedTrainTimeList = new ArrayList<>();
		List<Integer> transferStationList = trainDao.getTransferStationList(startStationNo, endStationNo);
		for (Integer transferStationNo : transferStationList) {
			mappedTrainTimeList.addAll(transferMethod(startStationNo, endStationNo, transferStationNo, weekdayNo, goTime));
		}
		
		for (Map<String,Object> map : mappedTrainTimeList) {
			map.put("goDate", goDate);
		}
		
		return mappedTrainTimeList;
	}
	
	public List<Map<String, Object>> transferMethod(
			int startStationNo, int endStationNo, int transferStationNo, int weekdayNo, Time goTime)
	{
		List<Map<String, Object>> timeMap = new ArrayList<>();
		
		List<TrainOperationRouteVo> startList = trainDao.getStartTrainTimeList(startStationNo, transferStationNo, 1, goTime);
		List<TrainOperationRouteVo> transferEndList = trainDao.getEndTrainTimeList(startStationNo, transferStationNo, 1, goTime);
		
		switch (weekdayNo) {
		case 6:
		case 7:
		case 1:
		case 2:
			startList.addAll(trainDao.getStartTrainTimeList(startStationNo, transferStationNo, 2, goTime));
			transferEndList.addAll(trainDao.getEndTrainTimeList(startStationNo, transferStationNo, 2, goTime));
			break;
		case 3:
			startList.addAll(trainDao.getStartTrainTimeList(startStationNo, transferStationNo, 2, goTime));
			transferEndList.addAll(trainDao.getEndTrainTimeList(startStationNo, transferStationNo, 2, goTime));
			
			startList.addAll(trainDao.getStartTrainTimeList(startStationNo, transferStationNo, 3, goTime));
			transferEndList.addAll(trainDao.getEndTrainTimeList(startStationNo, transferStationNo, 3, goTime));
			
			startList.addAll(trainDao.getStartTrainTimeList(startStationNo, transferStationNo, 4, goTime));
			transferEndList.addAll(trainDao.getEndTrainTimeList(startStationNo, transferStationNo, 4, goTime));
			break;
		case 4:
			startList.addAll(trainDao.getStartTrainTimeList(startStationNo, transferStationNo, 3, goTime));
			transferEndList.addAll(trainDao.getEndTrainTimeList(startStationNo, transferStationNo, 3, goTime));
			
			startList.addAll(trainDao.getStartTrainTimeList(startStationNo, transferStationNo, 4, goTime));
			transferEndList.addAll(trainDao.getEndTrainTimeList(startStationNo, transferStationNo, 4, goTime));
			
			startList.addAll(trainDao.getStartTrainTimeList(startStationNo, transferStationNo, 5, goTime));
			transferEndList.addAll(trainDao.getEndTrainTimeList(startStationNo, transferStationNo, 5, goTime));
			
			startList.addAll(trainDao.getStartTrainTimeList(startStationNo, transferStationNo, 6, goTime));
			transferEndList.addAll(trainDao.getEndTrainTimeList(startStationNo, transferStationNo, 6, goTime));
			break;
		case 5:
			startList.addAll(trainDao.getStartTrainTimeList(startStationNo, transferStationNo, 3, goTime));
			transferEndList.addAll(trainDao.getEndTrainTimeList(startStationNo, transferStationNo, 3, goTime));
			
			startList.addAll(trainDao.getStartTrainTimeList(startStationNo, transferStationNo, 5, goTime));
			transferEndList.addAll(trainDao.getEndTrainTimeList(startStationNo, transferStationNo, 5, goTime));
			
			startList.addAll(trainDao.getStartTrainTimeList(startStationNo, transferStationNo, 7, goTime));
			transferEndList.addAll(trainDao.getEndTrainTimeList(startStationNo, transferStationNo, 7, goTime));
			break;
		default:
			break;
		}
		
		for(int i=0; i<transferEndList.size(); i++){
			List<TrainOperationRouteVo> transferStartList = new ArrayList<>();
			List<TrainOperationRouteVo> endList = new ArrayList<>();
			
			TrainOperationRouteVo TETO = transferEndList.get(i);
			transferStartList.addAll(trainDao.getTransferStartTrainTimeList(transferStationNo, endStationNo, 1, TETO.getDepartureTime()));
			endList.addAll(trainDao.getEndTrainTimeAfterList(transferStationNo, endStationNo, 1, TETO.getDepartureTime()));
			
			switch (weekdayNo) {
			case 6:
			case 7:
			case 1:
			case 2:
				transferStartList.addAll(trainDao.getTransferStartTrainTimeList(transferStationNo, endStationNo, 2, TETO.getDepartureTime()));
				endList.addAll(trainDao.getEndTrainTimeAfterList(transferStationNo, endStationNo, 2, TETO.getDepartureTime()));
				break;
			case 3:
				transferStartList.addAll(trainDao.getTransferStartTrainTimeList(transferStationNo, endStationNo, 2, TETO.getDepartureTime()));
				endList.addAll(trainDao.getEndTrainTimeAfterList(transferStationNo, endStationNo, 2, TETO.getDepartureTime()));
				
				transferStartList.addAll(trainDao.getTransferStartTrainTimeList(transferStationNo, endStationNo, 3, TETO.getDepartureTime()));
				endList.addAll(trainDao.getEndTrainTimeAfterList(transferStationNo, endStationNo, 3, TETO.getDepartureTime()));
				
				transferStartList.addAll(trainDao.getTransferStartTrainTimeList(transferStationNo, endStationNo, 4, TETO.getDepartureTime()));
				endList.addAll(trainDao.getEndTrainTimeAfterList(transferStationNo, endStationNo, 4, TETO.getDepartureTime()));
				break;
			case 4:
				transferStartList.addAll(trainDao.getTransferStartTrainTimeList(transferStationNo, endStationNo, 3, TETO.getDepartureTime()));
				endList.addAll(trainDao.getEndTrainTimeAfterList(transferStationNo, endStationNo, 3, TETO.getDepartureTime()));
				
				transferStartList.addAll(trainDao.getTransferStartTrainTimeList(transferStationNo, endStationNo, 4, TETO.getDepartureTime()));
				endList.addAll(trainDao.getEndTrainTimeAfterList(transferStationNo, endStationNo, 4, TETO.getDepartureTime()));
				
				transferStartList.addAll(trainDao.getTransferStartTrainTimeList(transferStationNo, endStationNo, 5, TETO.getDepartureTime()));
				endList.addAll(trainDao.getEndTrainTimeAfterList(transferStationNo, endStationNo, 5, TETO.getDepartureTime()));
				
				transferStartList.addAll(trainDao.getTransferStartTrainTimeList(transferStationNo, endStationNo, 6, TETO.getDepartureTime()));
				endList.addAll(trainDao.getEndTrainTimeAfterList(transferStationNo, endStationNo, 6, TETO.getDepartureTime()));
				break;
			case 5:
				transferStartList.addAll(trainDao.getTransferStartTrainTimeList(transferStationNo, endStationNo, 3, TETO.getDepartureTime()));
				endList.addAll(trainDao.getEndTrainTimeAfterList(transferStationNo, endStationNo, 3, TETO.getDepartureTime()));
				
				transferStartList.addAll(trainDao.getTransferStartTrainTimeList(transferStationNo, endStationNo, 5, TETO.getDepartureTime()));
				endList.addAll(trainDao.getEndTrainTimeAfterList(transferStationNo, endStationNo, 5, TETO.getDepartureTime()));
				
				transferStartList.addAll(trainDao.getTransferStartTrainTimeList(transferStationNo, endStationNo, 7, TETO.getDepartureTime()));
				endList.addAll(trainDao.getEndTrainTimeAfterList(transferStationNo, endStationNo, 7, TETO.getDepartureTime()));
				break;
			default:
				break;
			}
			
			for(int j=0; j<endList.size(); j++){
				Map<String, Object> map = new HashMap<>();
				map.put("startStationName", trainDao.getStationName(startList.get(i).getTrainStation_no()));
				map.put("transferStationName", trainDao.getStationName(TETO.getTrainStation_no()));
				map.put("endStationName", trainDao.getStationName(endList.get(j).getTrainStation_no()));
				map.put("departureTime", startList.get(i).getDepartureTime());
				map.put("transferStationArrivalTime", TETO.getDepartureTime());
				map.put("transferStationDepartureTime", transferStartList.get(j).getDepartureTime());
				map.put("arrivalTime", endList.get(j).getDepartureTime());
				map.put("trainNum", trainDao.selectTrainInfoByNo(startList.get(i).getTrainInfo_no()).getTrainNum());
				map.put("transferTrainNum", trainDao.selectTrainInfoByNo(transferStartList.get(j).getTrainInfo_no()).getTrainNum());
				map.put("trainModel", trainDao.selectTrainInfoByNo(startList.get(i).getTrainInfo_no()).getTrainModel());
				map.put("transferTrainModel", trainDao.selectTrainInfoByNo(transferStartList.get(j).getTrainInfo_no()).getTrainModel());
				
				int startHour = startList.get(i).getDepartureTime().getHours();
				int startMinut = startList.get(i).getDepartureTime().getMinutes();
				int endHour = endList.get(j).getDepartureTime().getHours();
				int endMinut = endList.get(j).getDepartureTime().getMinutes();
				int startTime = (startHour*60) + startMinut;
				int endTime = (endHour*60) + endMinut;
				if(startTime > endTime) endTime += (24*60);
				int operationTime = endTime - startTime;
				map.put("operationTime", new Time(operationTime/60, operationTime%60, 0));
				
				timeMap.add(map);
			}
		}
		
		return timeMap;
	}
	
	//도시안에 좌표 근처 기차역 가까운 순으로 찾기
	public List<String> getNearStation(String location, int cityNum) 
	{
		String locationSplit[] = location.split(",");
		double latitude = Double.parseDouble(locationSplit[0]);
		double longitude = Double.parseDouble(locationSplit[1]);
		
		List<TrainStationVo> cityStationList = trainDao.getCityStationList(cityNum);
		List<Map<String, Object>> distanceMapList = new ArrayList<>();
		
		for(int i=0; i<cityStationList.size(); i++){
			TrainStationVo stationVo = cityStationList.get(i);
			String stationLocation = stationVo.getLocation();
			String stationLocationSplit[] = stationLocation.split(",");
			double stationLatitude = Double.parseDouble(stationLocationSplit[0]);
			double stationLongitude = Double.parseDouble(stationLocationSplit[1]);
			
			Map<String, Object> distanceMap = new HashMap<>();
			distanceMap.put("stationName", stationVo.getName());
			distanceMap.put("distance", getDistance(stationLatitude, stationLongitude, latitude, longitude));
			
			distanceMapList.add(distanceMap);
		}
		
		Collections.sort(distanceMapList, new DistanceASCCompare());
		
		List<String> stationList = new ArrayList<>();
		for (Map<String, Object> distanceMap : distanceMapList) {
			stationList.add((String)distanceMap.get("stationName"));
		}
		
		return stationList;
	}

	//모든 기차역의 이름을 ㄱㄴㄷ 순으로 찾기
	public List<String> getAllStationName() 
	{
		List<String> stationNameList = trainDao.getAllStationName();
		return stationNameList;
	}

	// 두 기차역 사이의 거리 구하기
	public Double getStationDistance(String nowLeaveStation, String nextStation) 
	{
		TrainStationVo station1 = trainDao.getStationByName(nowLeaveStation);
		TrainStationVo station2 = trainDao.getStationByName(nextStation);
		
		String spot1[] = station1.getLocation().split(",");
		double x = Double.parseDouble(spot1[1]);
		double y = Double.parseDouble(spot1[0]);
		String spot2[] = station2.getLocation().split(",");
		double x1 = Double.parseDouble(spot2[1]);
		double y1 = Double.parseDouble(spot2[0]);
		
		return getDistance(x, y, x1, y1);
	}
	
	//trainSchedule 짜기
//	public ArrayList<Integer> getCityOrderAndMakeTrainSchedule(
//			int schedule_no,
//			List<StartEndStation> startEndList)
//	{
//		ArrayList<Integer> cityOrderList = new ArrayList<>(); //도시순서를 순서대로 넣은 리스트
//		int nowCityIndex = -1; //현재 갈 곳을 찾고있는 도시(startEndList의 index)
//		boolean isUsed[] = new boolean[startEndList.size()]; //도시를 간적 있는지 확인하는 배열
//		
//		TrainScheduleVo vo = new TrainScheduleVo();
//		while( !( isAllTrue(isUsed) ) ){ //모든 도시가 다 사용될 때 까지 도시의 경로를 찾음
//			List<Map<String, Object>> operationTime = new ArrayList<>(); //?도시로 가는데 ?역에서 ?역으로 가고 ?시간 걸리는지 모아놓은 리스트(city_no, startStationName, endStationName, operationTime, 그외에도 경로 찾는것처럼 있음)
//			
//			if(vo.getArrivalDate() == "1899-12-31 00:00:00"){ //이전 도시를 못찾으면 다음 도시를 구할 수 없어서 못찾은걸로 설정
//				System.out.println("----- 경로를 찾을 수 없습니다. -----");
//				operationTime.clear();
//			}else{
//				if(nowCityIndex == -1){ //시작역 -> 도시
//					ScheduleVo scheduleVo = scheduleDao.selectScheduleData(schedule_no); //여행의 시작역을 찾기위해 스케줄을 받아온다.
//					String firstStation = scheduleVo.getFirstStation();
//					for(int i=0; i<startEndList.size(); i++){ //모든 도시를 돌면서
//						List<String> nearStationList = startEndList.get(i).getStartStation();
//						List<Map<String, Object>> mappedTrainTimeList = new ArrayList<>();
//						
//						//직통열차 찾기
//						for(int j=0; j<nearStationList.size(); j++){
//							mappedTrainTimeList.addAll(
//									getTrainTimeList(
//											firstStation, 
//											nearStationList.get(j), 
//											DateUtils.getDateByString(scheduleVo.getStartDate()), 
//											DateUtils.getTimeByString(scheduleVo.getStartDate())
//									)
//							);
//							if(!mappedTrainTimeList.isEmpty()) break;
//						}
//						
//						//직통열차 없으면 환승열차 찾기
//						if(mappedTrainTimeList.isEmpty()){
//							for(int j=0; j<nearStationList.size(); j++){
//								mappedTrainTimeList.addAll(
//										getTransferTrainTimeList(
//												firstStation,
//												nearStationList.get(j),
//												DateUtils.getDateByString(scheduleVo.getStartDate()),
//												DateUtils.getTimeByString(scheduleVo.getStartDate())
//										)
//								);
//								if(!mappedTrainTimeList.isEmpty()) break;
//							}
//						}
//						
//						//환승열차 없으면 다음날 직통열차 찾기
//						if(mappedTrainTimeList.isEmpty()){
//							Date today = DateUtils.getDateByString(scheduleVo.getStartDate());
//							int tomorrowYear = today.getYear();
//							int tomorrowMonth = today.getMonth();
//							int tomorrowDate = today.getDate() + 1;
//							Date tomorrow = new Date(tomorrowYear,tomorrowMonth,tomorrowDate);
//							for(int j=0; j<nearStationList.size(); j++){
//								mappedTrainTimeList.addAll(
//										getTrainTimeList(
//												firstStation,
//												nearStationList.get(j),
//												tomorrow,
//												new Time(0,0,0)
//										)
//								);
//								if(!mappedTrainTimeList.isEmpty()) break;
//							}
//						}
//						
//						//다음날 직통열차 없으면 다음날 환승열차 찾기
//						if(mappedTrainTimeList.isEmpty()){
//							Date today = DateUtils.getDateByString(scheduleVo.getStartDate());
//							int tomorrowYear = today.getYear();
//							int tomorrowMonth = today.getMonth();
//							int tomorrowDate = today.getDate() + 1;
//							Date tomorrow = new Date(tomorrowYear,tomorrowMonth,tomorrowDate);
//							for(int j=0; j<nearStationList.size(); j++){
//								mappedTrainTimeList.addAll(
//										getTransferTrainTimeList(
//												firstStation,
//												nearStationList.get(j),
//												tomorrow,
//												new Time(0,0,0)
//										)
//								);
//							}
//							if(!mappedTrainTimeList.isEmpty()) break;
//						}
//						
//						for (Map<String, Object> map : mappedTrainTimeList) {
//							map.put("city_no", startEndList.get(i).getCity_no());
//							map.put("cityIndex", i);
//						}
//						operationTime.addAll(mappedTrainTimeList);
//					}
//				}else{ //도시 -> 도시
//					for(int i=0; i<startEndList.size(); i++){ //모든 도시를 돌면서
//						if(isUsed[i] || i==nowCityIndex) continue; //이미 갔던 도시 + 지금 떠날려고 하는 도시 패쓰
//						
//						List<String> nowNearStationList = startEndList.get(nowCityIndex).getEndStation();
//						List<String> nextNearStationList = startEndList.get(i).getStartStation();
//						List<Map<String, Object>> mappedTrainTimeList = new ArrayList<>();
//						
//						//직통열차 찾기
//						for(int j=0; j<nowNearStationList.size(); j++){
//							for(int k=0; k<nextNearStationList.size(); k++){
//								mappedTrainTimeList.addAll(
//										getTrainTimeList(
//												nowNearStationList.get(j), 
//												nextNearStationList.get(k), 
//												goDate, 
//												goTime
//										)
//								);
//							}
//						}
//					}
//				}
//			}
//			
//			if(operationTime.isEmpty()){ //찾은 경로가 없을 때
//				//Map<String, Object> nearCity의 구성
//				//startStation : 떠날역, endStation : 갈역, distance : 거리, 
//				//city_no : 트레인스캐줄에 넣을 city_no, cityIndex : isUsed,nowIndex를 설정할 번호
//				Map<String, Object> nearCity = new HashMap<>();
//				for(int i=0; i<startEndList.size(); i++){
//					if(isUsed[i] || i==nowCityIndex) continue;
//					
//					String nowLeaveStation = null;
//					String nextStation = startEndList.get(i).getStartStation().get(0);
//					if(nowCityIndex == -1){
//						ScheduleVo scheduleVo = scheduleDao.selectScheduleData(schedule_no);
//						nowLeaveStation = scheduleVo.getFirstStation();
//					}else{
//						nowLeaveStation = startEndList.get(nowCityIndex).getEndStation().get(0);
//					}
//					Map<String, Object> distanceMap = new HashMap<>();
//					distanceMap.put("startStation", nowLeaveStation);
//					distanceMap.put("endStation", nextStation);
//					distanceMap.put("distance", getStationDistance(nowLeaveStation, nextStation));
//					if(nearCity.isEmpty() || (double)(distanceMap.get("distance")) < (double)(nearCity.get("distance"))){
//						distanceMap.put("city_no", startEndList.get(i).getCity_no());
//						distanceMap.put("cityIndex", i);
//						nearCity = distanceMap;
//					}
//				}
//				
//				//cityOrder 정해주고
//				cityOrderList.add((Integer)nearCity.get("city_no"));
//				//isUsed 설정해주고
//				isUsed[(int)nearCity.get("cityIndex")] = true;
//				//nowCityIndex 설정해주고
//				nowCityIndex = (int)nearCity.get("cityIndex");
//				//TrainSchedule을 만드는데 startDate, arrivalDate 1899-12-31 00:00:00 으로 설정(경로 없다는 표시 대신)
//				vo.setSchedule_no(schedule_no);
//				vo.setCity_no((int)nearCity.get("city_no"));
//				vo.setStartStation((String)nearCity.get("startStation"));
//				vo.setEndStation((String)nearCity.get("endStation"));
//				vo.setStartDate("1899-12-31 00:00:00");
//				vo.setArrivalDate("1899-12-31 00:00:00");
//				trainScheduleDao.insertTrainSchedule(vo);
//				//if 뒤에 하는 처리 대신 처리한거라 뒤에는 건너 뜀
//				continue;
//			}
//			
//			Map<String, Object> fastestRouteMap = getFastRouteMap(operationTime); //빨리 가는거 5개중 가장 빨리 출발하는거 뽑음
//			cityOrderList.add((Integer)fastestRouteMap.get("city_no"));
//			isUsed[(int)fastestRouteMap.get("cityIndex")] = true;
//			nowCityIndex = (int)fastestRouteMap.get("cityIndex");
//			
//			//TrainSchedule 만들고 디비에 저장하기
//			vo.setSchedule_no(schedule_no);
//			vo.setCity_no((int)fastestRouteMap.get("city_no"));
//			vo.setStartStation((String)fastestRouteMap.get("startStationName"));
//			vo.setEndStation((String)fastestRouteMap.get("endStationName"));
//			vo.setStartDate(DateUtils.getDateTimeString((Date)fastestRouteMap.get("goDate"), (Time)fastestRouteMap.get("departureTime")));
//			vo.setArrivalDate(
//					DateUtils.getDateTimeString(
//							new Date(DateUtils.getAddMillis((Date)fastestRouteMap.get("goDate"), (Time)fastestRouteMap.get("departureTime"), (Time)fastestRouteMap.get("operationTime"))), 
//							new Time(DateUtils.getAddMillis((Date)fastestRouteMap.get("goDate"), (Time)fastestRouteMap.get("departureTime"), (Time)fastestRouteMap.get("operationTime")))
//					)
//			);
//			System.out.println("디비에 들어갈 내용 -----> " + vo);
//			trainScheduleDao.insertTrainSchedule(vo);
//		}
//		
//		return cityOrderList;
//	}
	
	public List<TrainStationVo> selectStartEndStationByName(String first, String last)
	{
		
		
		List<TrainStationVo> StartEndStation = trainDao.selectStartEndStationByName(first, last);
		
		return StartEndStation;
	}
}