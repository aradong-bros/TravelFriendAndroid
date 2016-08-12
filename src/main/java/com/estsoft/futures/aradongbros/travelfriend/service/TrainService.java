package com.estsoft.futures.aradongbros.travelfriend.service;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estsoft.futures.aradongbros.travelfriend.dao.TrainDao;
import com.estsoft.futures.aradongbros.travelfriend.vo.TrainInfoVo;
import com.estsoft.futures.aradongbros.travelfriend.vo.TrainOperationRouteVo;

@Service
public class TrainService 
{
	@Autowired
	private TrainDao trainDao;
	
	//출발시간이 빠른 순으로 정렬
	static class DepTimeASCCompare implements Comparator<Map<String,Object>>{
		@Override
		public int compare(Map<String, Object> o1, Map<String, Object> o2) {
			Time t1 = (Time) o1.get("departureTime");
			Time t2 = (Time) o2.get("departureTime");
			return t1.compareTo(t2);
		}
	}
	
	//역이름으로 역번호 가져오기
	public Integer getStationNo(String stationName)
	{
		Integer no = trainDao.getStationNo(stationName);
		
		if(no == null || no == 0) return 0;
		else return no;
	}
	
	//요일 구분넘버 가져오기
	public Integer getCategoryNo(Date goDate)
	{
		Calendar c = Calendar.getInstance();
		c.setTime(goDate);
		Integer dayOfWeek = c.get(Calendar.DAY_OF_WEEK); //수요일부터 1, 목2, 금3, 토4, 일5, 월6, 화7
		return dayOfWeek;
	}

	public List<Map<String, Object>> getTrainTimeList(String startStation, String endStation, Date goDate, Time goTime) 
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

		if(trainStartTimeList.size() == 0 || trainStartTimeList == null || trainStartTimeList.size() != trainEndTimeList.size()) return null;
		
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
				map.put("transferTrainNum", trainDao.selectTrainInfoByNo(startList.get(i).getTrainInfo_no()).getTrainNum());
				map.put("trainModel", trainDao.selectTrainInfoByNo(startList.get(i).getTrainInfo_no()).getTrainModel());
				map.put("transferTrainModel", trainDao.selectTrainInfoByNo(transferStartList.get(j).getTrainInfo_no()).getTrainModel());
				
				timeMap.add(map);
			}
		}
		
		return timeMap;
	}
}
