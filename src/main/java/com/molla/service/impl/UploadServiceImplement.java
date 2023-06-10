package com.molla.service.impl;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.molla.service.UploadService;

import jakarta.servlet.ServletContext;

@Service
public class UploadServiceImplement implements UploadService {
	@Autowired
	ServletContext app;
	
	private final String URL = System.getProperty("user.dir") + "/src/main/resources/static/assets/images/";
//	private final String URL = System.getProperty("user.home") + "/Desktop/";

	@Override
	public File save(MultipartFile file, String folder) {
		File dir = new File(URL + folder);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		String name = file.getOriginalFilename();
		try {
			File savedFile = new File(dir, name);
			file.transferTo(savedFile);
			System.out.println(savedFile.getAbsolutePath());
			return savedFile;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	

}
