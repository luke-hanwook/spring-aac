package com.dasol.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.dasol.domain.AttachVO;
import com.dasol.domain.Criteria;
import com.dasol.service.AttachService;
import com.dasol.util.MimeMediaUtil;
import com.dasol.util.UploadFileUtils;

@Controller
public class UploadController {
	private static final Logger logger = LoggerFactory.getLogger(UploadController.class);
	
	@Autowired
	private AttachService service;
	
	@Resource(name="uploadPath")
	private String uploadPath;
	
	@ResponseBody
	@RequestMapping(value = "/uploadAjax/{cno}", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
	public ResponseEntity<String> uploadAjax(Authentication auth, MultipartFile file, @PathVariable int cno) throws Exception {
		ResponseEntity<String> entity = null;
		try {
			logger.info("originalName : " + file.getOriginalFilename());
			logger.info("size : " + file.getSize());
			logger.info("contentType : " + file.getContentType());
			logger.info("cno : " + cno);
			
			String fullpath = UploadFileUtils.uploadFile(uploadPath, file.getOriginalFilename(), file.getBytes(), cno);
			String email = auth.getName();
			
			service.addAttach(new AttachVO(email, cno, fullpath));
			entity = new ResponseEntity<>("success", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	@ResponseBody
	@RequestMapping(value = "/uploadAjax/{cno}/{page}")
	public ResponseEntity<Map<String, Object>> getAttach(@PathVariable int cno, @PathVariable int page) throws Exception {
		ResponseEntity<Map<String, Object>> entity = null;
		
		try {
			Criteria cri = new Criteria();
			cri.setPage(page);
			cri.setPerPageNum(9);
			
			Map<String, Object> map = new HashMap<>();
			map.put("list", service.getAttachList(cno, cri));
			map.put("acnt", service.countAttach(cno));
			
			entity = new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<Map<String, Object>>(HttpStatus.BAD_REQUEST);
		}
		
		return entity;
	}
	
	@ResponseBody
	@RequestMapping("/displayFile")
	public ResponseEntity<byte[]> displayFile(String fileName) throws Exception {
		InputStream in = null;
		ResponseEntity<byte[]> entity = null;
		
		logger.info("fileName : " + fileName);
		
		try {
			String formatName = fileName.substring(fileName.lastIndexOf(".") + 1);
			MediaType mType = MimeMediaUtil.getMediaType(formatName);
			HttpHeaders headers = new HttpHeaders();
			
			in = new FileInputStream(uploadPath + fileName);
			
			if(mType != null) {
				headers.setContentType(mType);
			} else {
				fileName = fileName.substring(fileName.indexOf("_")+1);
				headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
				headers.add("Content-Disposition", "attachment; filename=\"" 
				+ new String(fileName.getBytes("UTF-8"), "ISO-8859-1")+"\"");
			}
			
			entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in), headers, HttpStatus.CREATED);

		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
		} finally {
			in.close();
		}
		return entity;
	}
	
	@ResponseBody
	@RequestMapping(value="/deleteFile/{ano}", method = RequestMethod.POST)
	public ResponseEntity<String> deleteFile(@PathVariable int ano, String fileName) {
		ResponseEntity<String> entity = null;
		try {
			logger.info("delete file : " + fileName);
			logger.info("delete ano : " + ano);
			service.removeAttach(ano);
			
			String front = fileName.substring(0, 14);
			String end = fileName.substring(16);
			String oriFilePath = front+end;
			
			new File(uploadPath + fileName.replace("/", File.separator)).delete();
			new File(uploadPath + oriFilePath.replace("/", File.separator)).delete();
			
			entity = new ResponseEntity<>("success", HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
		}
		
		return entity;
	}
}
