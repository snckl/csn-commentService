package com.csn.commentservice.service;

import com.csn.commentservice.dto.CommentDto;
import com.csn.commentservice.entity.Comment;
import com.csn.commentservice.exception.ResourceNotFoundException;
import com.csn.commentservice.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommentService {
    private final CommentRepository commentRepository;

    public void createComment(CommentDto commentDto){
        Comment comment = Comment.builder()
                        .content(commentDto.getContent())
                        .postId(commentDto.getPostId()).build();
        Comment createdComment = commentRepository.save(comment);
        log.info("Comment created with id of {}",createdComment.getId());
    }

    public List<CommentDto> fetchComment(Long id){
        Optional<List<Comment>> comments = commentRepository.findAllByPostId(id);
        if(comments.isPresent() && !comments.get().isEmpty()){
            return comments.get().stream().map(comment -> CommentDto.builder()
                    .content(comment.getContent())
                    .postId(comment.getPostId()).build()).collect(Collectors.toList());
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

    public void deleteAllComments(Long id){
        commentRepository.deleteAllByPostId(id);
        log.info("Comments deleted with id of {}",id.toString());
    }
}
