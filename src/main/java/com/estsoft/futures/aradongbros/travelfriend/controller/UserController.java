package com.estsoft.futures.aradongbros.travelfriend.controller;

import java.io.File;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.estsoft.futures.aradongbros.travelfriend.service.UserService;
import com.estsoft.futures.aradongbros.travelfriend.vo.UserVo;

import util.CommonUtils;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	
	private static final String filePath = "/home/aradongbros/userImage/";
	
	/*
	 * 유저 insert 메소드
	 * 
	 * 필요 파라미터 : name, picture, platform, userID
	 * 모든 파라미터는 인코딩 필요
	 * 리턴타입 : jsonString
	 */
	@RequestMapping("/insertUser")
	@ResponseBody
	public Map<String, UserVo> insertUser(
			@ModelAttribute UserVo userVo)
	{
		UserVo dbUserVo = userService.getUser(userVo.getPlatform(), userVo.getUserID());
		Map<String, UserVo> map = new HashMap<>();
		
		if(dbUserVo == null){ //db에 유저가 없음
			map.put("userVo", userService.insertUser(userVo));
		}
		else{ //db에 유저가 있음
			map.put("userVo", dbUserVo);
		}
		
		return map;
	}
	
	/*
	 * 유저 modify 메소드
	 * 
	 * 필요 파라미터 : no, name, picture
	 * 모든 파라미터는 인코딩 필요
	 * 리턴타입 : jsonString
	 */
	@RequestMapping("/modifyUser")
	@ResponseBody
	public Map<String, UserVo> modifyUser(
			@ModelAttribute UserVo userVo)
	{
		UserVo modifiedUser = userService.modifyUser(userVo);
		Map<String, UserVo> map = new HashMap<>();
		map.put("userVo", modifiedUser);
		
		return map;
	}
	
	/*
	 * 유저 프로필 사진 업로드
	 * 
	 * 필요 파라미터 : no, file
	 * 리턴 데이터 : 파일 URL
	 */
	@RequestMapping("/profileUpload")
	@ResponseBody
	public String profileUpload(HttpServletRequest request) throws Exception
	{
		MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;
		Iterator<String> iterator = multipartHttpServletRequest.getFileNames();
		
		MultipartFile multipartFile = null;
		String originalFileName = null;
		String originalFileExtension = null;
		String storedFileName = null;
		
		File file = new File(filePath);
		if(file.exists() == false) file.mkdirs();
		
		while(iterator.hasNext()){
			multipartFile = multipartHttpServletRequest.getFile(iterator.next());
			if(multipartFile.isEmpty() == false){
				originalFileName = multipartFile.getOriginalFilename();
				originalFileExtension = originalFileName.substring(originalFileName.lastIndexOf("."));
				storedFileName = CommonUtils.getRandomString() + originalFileExtension;
				
				file = new File(filePath + storedFileName);
				multipartFile.transferTo(file);
			}
		}
		
		String storedURL = "http://222.239.250.207:8080/user/profileDownload?fileName=" + storedFileName;
		return storedURL;
	}
	
	@RequestMapping("/profileDownload")
	public void profileDownload(
			@RequestParam("fileName")String fileName,
			HttpServletResponse response) throws Exception
	{
		File file = new File(filePath + fileName);
		if(file.canRead()) System.out.println("읽힘");
		byte fileByte[] = FileUtils.readFileToByteArray(file);
		
		response.setContentType("application/octet-stream");
		response.setContentLength(fileByte.length);
		response.setHeader("Content-Disposition", "attachment; fileName=\"" + URLEncoder.encode(fileName, "UTF-8") + "\";");
		response.setHeader("Content-Transfer-Encoding", "binary");
		response.getOutputStream().write(fileByte);
		
		response.getOutputStream().flush();
		response.getOutputStream().close();
	}
}
