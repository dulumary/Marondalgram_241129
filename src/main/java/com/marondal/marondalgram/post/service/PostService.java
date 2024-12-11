package com.marondal.marondalgram.post.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.marondal.marondalgram.common.FileManager;
import com.marondal.marondalgram.post.domain.Post;
import com.marondal.marondalgram.post.dto.CardDTO;
import com.marondal.marondalgram.post.repository.PostRepository;
import com.marondal.marondalgram.user.domain.User;
import com.marondal.marondalgram.user.service.UserService;

@Service
public class PostService {
	
	private PostRepository postRepository;
	private UserService userService;
	
	public PostService(PostRepository postRepository, UserService userService) {
		this.postRepository = postRepository;
		this.userService = userService;
	}
	
	public boolean addPost(int userId, String contents, MultipartFile file) {
		
		String imagePath = FileManager.saveFile(userId, file);
		
		Post post = Post.builder()
		.userId(userId)
		.contents(contents)
		.imagePath(imagePath)
		.build();
		
		try {			
			postRepository.save(post);
			return true;
		} catch(Exception e) {
			return false;
		}
		
	}
	
	
	public List<CardDTO> getPostList() {
		
		// 조회된 게시글마다 작성자의 로그인 ID 얻어오기
		List<Post> postList = postRepository.findAllByOrderByIdDesc();
		
		List<CardDTO> cardList = new ArrayList<>();
		for(Post post:postList) {
			
			int userId = post.getUserId();
			User user = userService.getUserById(userId);
			
			CardDTO card = CardDTO.builder()
			.postId(post.getId())
			.userId(userId)
			.contents(post.getContents())
			.imagePath(post.getImagePath())
			.loginId(user.getLoginId())
			.build();
			
			cardList.add(card);
		}
		
		return cardList;
		
	}
	

	
	
	
	
	
	
	
	
	
	

}
