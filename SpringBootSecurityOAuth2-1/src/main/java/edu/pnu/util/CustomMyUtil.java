package edu.pnu.util;

import org.springframework.security.oauth2.core.user.OAuth2User;


// OAuth2User 객체에서 사용자 이름을 추출해서 특정 포맷으로 반환하는 유틸리티 클래스
public class CustomMyUtil {
	
	
//	/OAuth2User 객체에서 사용자 이름을 추출하고 특정 형식을 반환
	public static String getUsernameFromOAuth2User(OAuth2User user) {
		
		String userString = user.toString(); //OAuth2User 객체를 문자열로 반환
		String regName = null;
		
		if (userString.contains("google")) 			regName = "Google";
		else if (userString.contains("facebook")) 	regName = "Facebook";
		else if (userString.contains("naver")) 		regName = "Naver";
		else if (userString.contains("kakao")) 		regName = "Kakao";
		else {
			//특정 문자열 패턴이 포함된 경우에는 naver로 간주
			if (userString.contains("id=") && userString.contains("resultcode=") && userString.contains("response="))
				regName = "Naver";
			else
				return null; //식별 안되면 null
		}
		String name = user.getName(); //사용자 이름 가져오기
		if(name == null) {
			return null;
		}
		
		return regName + "_" + name; //판별된 소셜 플랫폼의 이름(regName)과 사용자 이름(name)을 결합하여 "제공자명_사용자이름" 형식으로 반환합니다.
	}

}
