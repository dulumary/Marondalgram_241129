package com.marondal.marondalgram.post.dto;

import java.util.List;

import com.marondal.marondalgram.comment.dto.CommentDTO;

import lombok.Builder;
import lombok.Getter;


@Builder
@Getter
public class CardDTO {
	
	private int postId;
	private int userId;
	
	private String contents;
	private String imagePath;
	
	private String loginId;
	
	private int likeCount;
	private boolean isLike;
	
	List<CommentDTO> commentList;

}
