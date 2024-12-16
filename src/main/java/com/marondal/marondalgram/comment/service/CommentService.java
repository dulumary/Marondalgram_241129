package com.marondal.marondalgram.comment.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.marondal.marondalgram.comment.domain.Comment;
import com.marondal.marondalgram.comment.dto.CommentDTO;
import com.marondal.marondalgram.comment.repository.CommentRepository;
import com.marondal.marondalgram.user.domain.User;
import com.marondal.marondalgram.user.service.UserService;

@Service
public class CommentService {
	
	private CommentRepository commentRepository;
	private UserService userService;
	
	public CommentService(
			CommentRepository commentRepository
			, UserService userService) {
		this.commentRepository = commentRepository;
		this.userService = userService;
	}
	
	public boolean addComment(int postId, int userId, String contents) {
		
		Comment comment = Comment.builder()
				.postId(postId)
				.userId(userId)
				.contents(contents)
				.build();
		
		try {			
			commentRepository.save(comment);
			return true;
		} catch(Exception e) {
			return false;
		}
		
	}
	
	
	public List<CommentDTO> getCommentList(int postId) {
		
		List<Comment> commentList = commentRepository.findByPostId(postId);
		
		List<CommentDTO> commentDTOList = new ArrayList<>();
		for(Comment comment:commentList) {
			
			int userId = comment.getUserId();
			
			User user = userService.getUserById(userId);
			
			CommentDTO commentDTO = CommentDTO.builder()
			.commentId(comment.getId())
			.userId(userId)
			.loginId(user.getLoginId())
			.contents(comment.getContents())
			.build();
			
			commentDTOList.add(commentDTO);
		}
		return commentDTOList;
	}
	
	
	public void deleteCommentByPostId(int postId) {
		commentRepository.deleteByPostId(postId);
	}
	
	
	
	
	

}
