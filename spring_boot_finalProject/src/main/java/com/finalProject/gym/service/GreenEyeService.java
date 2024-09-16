package com.finalProject.gym.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.util.Base64;
import java.util.UUID;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.finalProject.gym.model.GreenEyeVO;



@Service
public class GreenEyeService {
	public GreenEyeVO greenEye(String filePath) {
		
		GreenEyeVO vo = new GreenEyeVO();
		
		String apiURL = "https://clovagreeneye.apigw.ntruss.com/custom/v1/133/f38ecf90e7d670236ef927645a336db61cf996810ef16e7de7fb9c8fc1dc811d/predict";
		String secretKey = "WUxYUVNUTWpBa25rT2ZaSmVtVGV6Q0dKbGthdVBSSEQ=";
		
		String imageFile = filePath;
		
		try {
			URL url = new URL(apiURL);
			
			// (1) 이미지 파일 base64 인코딩
			byte[] fileContent  =  Files.readAllBytes(new File(imageFile).toPath());
			String encodedString = Base64.getEncoder().encodeToString(fileContent);
			
			// 커넥션 설정
			HttpURLConnection con = (HttpURLConnection)url.openConnection();
			con.setRequestMethod("POST");
			con.setRequestProperty("Content-Type", "application/json; utf-8");
			con.setRequestProperty("X-GREEN-EYE-SECRET", secretKey);
			con.setDoOutput(true);
			
			// (2) 요청 바디에 맞춰 JSON 데이터 생성
			JSONObject jsonData = new JSONObject();
			jsonData.put("version", "V1");
			jsonData.put("requestId", UUID.randomUUID().toString());
			jsonData.put("timestamp", System.currentTimeMillis());
			
			JSONObject image = new JSONObject(); // images 배열에 넣기 위한 객체 생성/설정
			image.put("name", "greenEye");
			image.put("data", encodedString);
			JSONArray images = new JSONArray();			
			images.put(image);			
			jsonData.put("images", images);
			
			// 요청
			String postParams = jsonData.toString();
			con.setDoOutput(true);
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(con.getOutputStream()));
			bw.write(postParams);
			bw.flush();
			bw.close();
			
			// 응답
			int responseCode = con.getResponseCode();
			BufferedReader br;
			if (responseCode == 200) {
				br = new BufferedReader(new InputStreamReader(con.getInputStream()));
			} else {
				br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
			}
			String inputLine;
			StringBuffer response = new StringBuffer();
			while ((inputLine = br.readLine()) != null) {
				response.append(inputLine);
			}
			br.close();
			
			// (3) 결과 출력
			//System.out.println(response); // 결과 출력
			vo = jsonToVO(response.toString());
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return vo;
	}
	
	// JSON 데이터 추출 
	public GreenEyeVO jsonToVO(String jonsResultStr) {
		JSONObject jsonObj = new JSONObject(jonsResultStr);
		JSONArray imagesArray = jsonObj.getJSONArray("images");
		
		JSONObject result = imagesArray.getJSONObject(0)
																 .getJSONObject("result");
		
		double adult = result.getJSONObject("adult").getDouble("confidence");
		double normal = result.getJSONObject("normal").getDouble("confidence");
		double porn = result.getJSONObject("porn").getDouble("confidence");
		double sexy = result.getJSONObject("sexy").getDouble("confidence");
		
		//System.out.println(adult);
		//System.out.println(normal);
		//System.out.println(porn);
		//System.out.println(sexy);
		
		// GreenEyeVO에 저장
		GreenEyeVO vo = new GreenEyeVO();
		vo.setAdult(adult);
		vo.setNormal(normal);
		vo.setPorn(porn);
		vo.setSexy(sexy);
		
		return vo;
	}
}






