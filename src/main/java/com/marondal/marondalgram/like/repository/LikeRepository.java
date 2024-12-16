package com.marondal.marondalgram.like.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.marondal.marondalgram.like.domain.Like;

import jakarta.transaction.Transactional;

public interface LikeRepository extends JpaRepository<Like, Integer> {

	// SELECT count(*) FROM `like` WHERE `postId` = #{}
	public int countByPostId(int postId);
	
	// SELECT count(*) FROM `like` WHERE `postId` = #{} AND `userId` = #{}
	public int countByPostIdAndUserId(int postId, int userId);
	
	public Optional<Like> findByPostIdAndUserId(int postId, int userId);
	
	// transcation
	// SELECT * FROM `like` WHERE `postId` = #{}
	// DELETE FROM `like` WHERE `postId` = #{}
	@Transactional
	public void deleteByPostId(int postId);
	
}
