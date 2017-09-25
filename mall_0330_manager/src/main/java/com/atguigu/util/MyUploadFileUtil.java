package com.atguigu.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

public class MyUploadFileUtil {

	public static List<String> upload_image(MultipartFile[] files) {
		List<String> list_image = new ArrayList<String>();

		for (int i = 0; i < files.length; i++) {
			String myProperty = MyProperty.getMyProperty("windows_path");
			// UUID randomUUID = UUID.randomUUID();
			String originalFilename = System.currentTimeMillis() + files[i].getOriginalFilename();
			try {
				files[i].transferTo(new File(myProperty + "\\" + originalFilename));
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			list_image.add(originalFilename);
		}

		return list_image;
	}

}
