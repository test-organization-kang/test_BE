package com.thinktank.global.error.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum ErrorCode {

	// 400: BAD REQUEST
	BAD_REQUEST("[❎ ERROR]"),
	FAIL_WRONG_PASSWORD("[❎ ERROR] 비밀번호가 일치하지 않습니다."),
	FAIL_INVALID_CATEGORY("[❎ ERROR] 유효하지 않은 카테고리입니다."),
	FAIL_INVALID_LANGUAGE("[❎ ERROR] 유효하지 않은 카테고리입니다."),
	FAIL_INVALID_TOKEN("[❎ ERROR] 유효하지 않은 토큰입니다."),

	// 401: UNAUTHORIZED
	FAIL_UNAUTHORIZED_EXCEPTION("[❎ ERROR] 로그인이 필요한 기능입니다."),
	FAIL_TOKEN_EXPIRE_EXCEPTION("[❎ ERROR] 토큰이 만료되었습니다."),

	// 403: FORBIDDEN
	POST_POST_FORBIDDEN_EXCEPTION("[❎ ERROR] 게시글 작성 권한이 없습니다."),
	DELETE_POST_FORBIDDEN_EXCEPTION("[❎ ERROR] 게시글 삭제 권한이 없습니다."),
	POST_COMMENT_FORBIDDEN_EXCEPTION("[❎ ERROR] 댓글 작성 권한이 없습니다."),
	DELETE_COMMENT_FORBIDDEN_EXCEPTION("[❎ ERROR] 댓글 삭제 권한이 없습니다."),

	// 404: NOT FOUNT
	FAIL_NOT_POST_FOUND_EXCEPTION("[❎ ERROR] 요청하신 게시물을 찾을 수 없습니다."),
	FAIL_NOT_USER_FOUND_EXCEPTION("[❎ ERROR] 요청하신 회원을 찾을 수 없습니다."),
	FAIL_NOT_COMMENT_FOUND_EXCEPTION("[❎ ERROR] 요청하신 댓글을 찾을 수 없습니다."),
	FAIL_NOT_TOKEN_FOUND_EXCEPTION("[❎ ERROR] 토큰이 존재하지 않습니다."),

	// 409: CONFLICT
	FAIL_EMAIL_CONFLICT("[❎ ERROR] 이미 존재하는 이메일입니다."),
	FAIL_NICKNAME_CONFLICT("[❎ ERROR] 이미 존재하는 닉네임입니다.");

	private String message;
}
