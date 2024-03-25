package com.csn.commentservice.service;

import com.csn.commentservice.controller.dto.CommentDto;
import com.csn.commentservice.entity.Comment;
import com.csn.commentservice.exception.ResourceNotFoundException;
import com.csn.commentservice.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommentService {
    private final CommentRepository commentRepository;

    public void createComment(CommentDto commentDto){
        Comment comment = Comment.builder()
                        .content(commentDto.getContent()).build();
        Comment createdComment = commentRepository.save(comment);
        log.info("Comment created with id of {}",createdComment.getId());
    }

    public CommentDto fetchComment(Long id){
        Optional<Comment> comment = commentRepository.findById(id);
        if(comment.isPresent()){
            return CommentDto.builder().content(comment.get().getContent()).build();
        }
        throw new ResourceNotFoundException("comment","id",id.toString());
    }

    public void updateComment(Long id,CommentDto commentDto){
        Optional<Comment> OptionalComment = commentRepository.findById(id);
        if(OptionalComment.isPresent()){
            Comment comment = OptionalComment.get();
            comment.setContent(commentDto.getContent());
            Comment updatedComment = commentRepository.save(comment);
            log.info("Comment updated with id of {}",updatedComment.getId());
        } else {
           throw new ResourceNotFoundException("comment","id",id.toString());
        }

    }

    public void deleteComment(Long id){
        Optional<Comment> comment = commentRepository.findById(id);
        if(comment.isPresent()){
            commentRepository.deleteById(id);
            log.info("Comment deleted with id of {}",id);
        } else {
            throw new ResourceNotFoundException("comment","id",id.toString());
        }
    }
}
