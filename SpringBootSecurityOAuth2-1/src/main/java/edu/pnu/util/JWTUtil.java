package edu.pnu.util;

import java.util.Date;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

public class JWTUtil {
	private static final long ACCESS_TOKEN_MESC = 10 * (60 * 1000);	// 10분 유지
	private static final String JWT_KEY = "edu.pnu.jwtkey";	//인코딩에 담을 secret key
	private static final String claimName = "username";		//토큰에 담을 정보의 key값
	private static final String prefix = "Bearer ";			// jwt 토큰 헤더 문자열
	
	//주어진 토큰에서 Bearer 제거
	private static String getJWTSource(String token) { 
		if (token.startsWith(prefix)) return token.replace(prefix, "");
		return token;
	}
	// 주어진 사용자 이름으로 JWT토큰 생성
	public static String getJWT(String username) { 
		String src = JWT.create() // jwt토큰 생성
				.withClaim(claimName, username)// payload-claim에서 username 저장
				.withExpiresAt(new Date(System.currentTimeMillis()+ACCESS_TOKEN_MESC)) //만료시간/시간은 현재 시간과 유효시간10분으로 
				.sign(Algorithm.HMAC256(JWT_KEY));  //key 설정
		return prefix + src; // Bearer + payload + signiture
	}
	//주어진 토큰에서 클레임 정보 추출
	public static String getClaim(String token) {		//토큰에 담긴 정보 중 key가 username인 데이터 가져오기
		String tok = getJWTSource(token);
		return JWT.require(Algorithm.HMAC256(JWT_KEY)).build().verify(tok).getClaim(claimName).asString();
	}
	
	//주어진 토큰의 유효기간이 만료되었는지 확인
	public static boolean isExpired(String token) {		//유효기간 만료 여부
		String tok = getJWTSource(token);
		return JWT.require(Algorithm.HMAC256(JWT_KEY)).build().verify(tok).getExpiresAt().before(new Date());
	}
}
