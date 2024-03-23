package com.csn.commentservice.controller;

import com.csn.commentservice.dto.CommentDto;
import com.csn.commentservice.dto.ResponseDto;
import com.csn.commentservice.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/comment",produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<ResponseDto> createComment(@Valid @RequestBody CommentDto comment){
        commentService.createComment(comment);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto("201","Comment created successfully"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommentDto> fetchComment(@PathVariable("id") Long id){
        CommentDto commentDto = commentService.fetchComment(id);
        return ResponseEntity
                .status(HttpStatus.FOUND)
                .body(commentDto);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ResponseDto> updateComment(@PathVariable("id") Long id,@Valid @RequestBody CommentDto commentDto){
        commentService.updateComment(id,commentDto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto("201","Comment updated"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto> deleteComment(@PathVariable("id") Long id){
        commentService.deleteComment(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto("200","Comment deleted"));
    }
}
