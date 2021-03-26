package com.project2.repository;

import java.util.List;

import com.project2.models.Posts;

public interface PostRepository
{
	public boolean createPost(Posts post);
	public void deletePost(Posts post);
	public boolean updatePost(Posts post);
	public List<Posts> getAllPosts();	
}
