package com.springboot.Main.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.Main.Entity.Comment;

@Repository
public interface CommentRepo extends JpaRepository<Comment, Integer>
{

}
