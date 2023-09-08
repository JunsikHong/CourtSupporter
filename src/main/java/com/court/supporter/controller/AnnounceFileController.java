package com.court.supporter.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/download")
public class AnnounceFileController { //첨부파일 다운로드 컨트롤러
	
	@Value("${project.upload.path}")
	private String uploadPath;
	
	
	@GetMapping("/{announce_file_proper_num}")	
	public byte[] downloadFile(@PathVariable Integer announce_file_proper_num) throws IOException {
		
		String filepath = "";
		File file = new File(filepath);
		InputStream in = new FileInputStream(file);
		return FileCopyUtils.copyToByteArray(in);
	}
	
}
