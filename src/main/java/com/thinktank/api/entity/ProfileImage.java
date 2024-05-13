package com.thinktank.api.entity;

import static com.thinktank.global.common.util.GlobalConstant.*;

import com.thinktank.api.dto.user.request.UserUpdateDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "tbl_profile_image")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProfileImage {
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "file_name")
	private String fileName;

	@Column(name = "file_url")
	private String fileUrl;

	@Column(name = "original_file_name")
	private String originalFileName;

	@OneToOne(fetch = FetchType.LAZY, orphanRemoval = true)
	@JoinColumn(name = "user_id")
	private User user;

	@Builder
	private ProfileImage(String fileName, String fileUrl, String originalFileName, User user) {
		this.fileName = fileName;
		this.fileUrl = fileUrl;
		this.originalFileName = originalFileName;
		this.user = user;
	}

	public static ProfileImage createDefaultForUser(User user) {
		return ProfileImage.builder()
			.fileName(DEFAULT_FILE_NAME)
			.fileUrl(DEFAULT_FILE_URL)
			.originalFileName(DEFAULT_FILE_NAME)
			.user(user)
			.build();
	}

	public void updateProfileImage(UserUpdateDto userUpdateDto) {
		this.fileName = userUpdateDto.fileName();
		this.fileUrl = userUpdateDto.fileUrl();
		this.originalFileName = userUpdateDto.originalFileName();
	}
}
