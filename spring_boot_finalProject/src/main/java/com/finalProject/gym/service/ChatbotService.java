package com.finalProject.gym.service;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;
import java.util.Date;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class ChatbotService {
	public static String main(String voiceMessage) {
		
		String apiURL = "https://r7ruz5mcjv.apigw.ntruss.com/custom/v1/15760/7bfad271513eb08a9c6da7c89ad43a1c7acd84ee64c0022465a38b34cf6a8c6d";
		String secretKey = "RmNlaUlrWnRNdXhqV2REWFdRUFBISldVeFRhQ2FJdEc=";

        String chatbotMessage = "";

        try {
            //String apiURL = "https://ex9av8bv0e.apigw.ntruss.com/custom_chatbot/prod/";

            URL url = new URL(apiURL);

            String message = getReqMessage(voiceMessage);
            //System.out.println("##" + message);

            String encodeBase64String = makeSignature(message, secretKey);

            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json;UTF-8");
            con.setRequestProperty("X-NCP-CHATBOT_SIGNATURE", encodeBase64String);

            // post request
            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.write(message.getBytes("UTF-8"));
            wr.flush();
            wr.close();
            int responseCode = con.getResponseCode();

            BufferedReader br;

            if(responseCode==200) { // Normal call
                //System.out.println(con.getResponseMessage());

                BufferedReader in = new BufferedReader(
                        new InputStreamReader(
                                con.getInputStream()));
                String decodedString;
                while ((decodedString = in.readLine()) != null) {
                    chatbotMessage = decodedString;
                }
                //chatbotMessage = decodedString;
                in.close();
                
                //System.out.println(chatbotMessage);
                
                // jsonToString() 메소드 호출하고 결과 받아서 chatbotMessage에 저장
                chatbotMessage = jsonToString(chatbotMessage);
            } else {  // Error occurred
                chatbotMessage = con.getResponseMessage();
            }
        } catch (Exception e) {
            System.out.println(e);
        }

        return chatbotMessage;
    }
	
	// JSON 데이터 그대로 반환하는 메소드
	public static String mainJSON(String voiceMessage) {
		
		String apiURL = "https://r7ruz5mcjv.apigw.ntruss.com/custom/v1/15760/7bfad271513eb08a9c6da7c89ad43a1c7acd84ee64c0022465a38b34cf6a8c6d";
		String secretKey = "RmNlaUlrWnRNdXhqV2REWFdRUFBISldVeFRhQ2FJdEc=";

        String chatbotMessage = "";

        try {
            //String apiURL = "https://ex9av8bv0e.apigw.ntruss.com/custom_chatbot/prod/";

            URL url = new URL(apiURL);

            String message = getReqMessage(voiceMessage);
            //System.out.println("##" + message);

            String encodeBase64String = makeSignature(message, secretKey);

            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/json;UTF-8");
            con.setRequestProperty("X-NCP-CHATBOT_SIGNATURE", encodeBase64String);

            // post request
            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.write(message.getBytes("UTF-8"));
            wr.flush();
            wr.close();
            int responseCode = con.getResponseCode();

            BufferedReader br;

            if(responseCode==200) { // Normal call
                //System.out.println(con.getResponseMessage());

                BufferedReader in = new BufferedReader(
                        new InputStreamReader(
                                con.getInputStream()));
                String decodedString;
                while ((decodedString = in.readLine()) != null) {
                    chatbotMessage = decodedString;
                }
                //chatbotMessage = decodedString;
                in.close();
                
                //System.out.println(chatbotMessage);
                
                // jsonToString() 메소드 호출하고 결과 받아서 chatbotMessage에 저장
               // chatbotMessage = jsonToString(chatbotMessage);
            } else {  // Error occurred
                chatbotMessage = con.getResponseMessage();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        
        // JSON 형식의 문자열 그대로 반환
        return chatbotMessage;
    }
	
	

    public static String makeSignature(String message, String secretKey) {

        String encodeBase64String = "";

        try {
            byte[] secrete_key_bytes = secretKey.getBytes("UTF-8");

            SecretKeySpec signingKey = new SecretKeySpec(secrete_key_bytes, "HmacSHA256");
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(signingKey);

            byte[] rawHmac = mac.doFinal(message.getBytes("UTF-8"));
            encodeBase64String = Base64.getEncoder().encodeToString(rawHmac);

            return encodeBase64String;

        } catch (Exception e){
            System.out.println(e);
        }

        return encodeBase64String;

    }
    
    //요청 메시지의 유형 설정해서 전달
    // send : 일반 메시지(voiceMessage) 전송
    // open : 웰컴 메시지
    public static String getReqMessage(String voiceMessage) {

        String requestBody = "";

        try {

            JSONObject obj = new JSONObject();

            long timestamp = new Date().getTime();

            //System.out.println("##"+timestamp);

            obj.put("version", "v2");
            obj.put("userId", "U47b00b58c90f8e47428af8b7bddc1231heo2");
//=> userId is a unique code for each chat user, not a fixed value, recommend use UUID. use different id for each user could help you to split chat history for users.

            obj.put("timestamp", timestamp);

            JSONObject bubbles_obj = new JSONObject();

            bubbles_obj.put("type", "text");

            JSONObject data_obj = new JSONObject();
            data_obj.put("description", voiceMessage);

            bubbles_obj.put("type", "text");
            bubbles_obj.put("data", data_obj);

            JSONArray bubbles_array = new JSONArray();
            bubbles_array.put(bubbles_obj);

            obj.put("bubbles", bubbles_array);
            //obj.put("event", "send"); // 일반 메시지 전송으로 되어 있는 것을
            
            // 전송 메시지가 없으면 웰컴 메시지, 있으면 일반 전송 메시지
            if(voiceMessage.equals("")) {
            	obj.put("event", "open");
            } else {
            	obj.put("event", "send");
            }

            requestBody = obj.toString();

        } catch (Exception e){
            System.out.println("## Exception : " + e);
        }

        return requestBody;

    }    
   
    // JSON 데이터 추출
    public static String jsonToString(String chatbotData) {    	
    	//(1) 전달한 값에서 object 추출
        JSONObject jsonObject = new JSONObject(chatbotData);
        
        // (2) bubbles 배열 추출
        JSONArray bubbles = jsonObject.getJSONArray("bubbles");
        
        // (3) object 0 추출
        JSONObject firstBubble = bubbles.getJSONObject(0);
        
        // (4) object data 추출
        JSONObject data = firstBubble.getJSONObject("data");
        
        // (5) String description 추출
        String description = data.getString("description");
        
        // (6) return description 
    	return description;    	
    }

    
}
