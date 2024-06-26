package com.csn.commentservice.controller;

import com.csn.commentservice.dto.CommentDto;
import com.csn.commentservice.dto.ResponseDto;
import com.csn.commentservice.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "CRUD API for post service of CSN",
        description = "Create fetch patch delete for comment details"
)
@RestController
@RequestMapping(path = "/api/v1",produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
@Slf4j
public class CommentController {

    private final CommentService commentService;

    @Operation(summary = "Create comment REST API",
            description = "Creates new comment with input of commentDto"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status CREATED"
    )
    @PostMapping
    public ResponseEntity<ResponseDto> createComment(@Valid @RequestBody CommentDto comment){
        commentService.createComment(comment);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto("201","Comment created successfully"));
    }

    @Operation(summary = "Fetch comment details REST API",
            description = "Fetches comment details"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status OK"
    )
    @GetMapping("/{id}")
    public ResponseEntity<List<CommentDto>> fetchComment(@PathVariable("id") Long id,@RequestHeader("csn-correlation-id") String correlationId){
        log.debug("csn-correlation-id found: {}",correlationId);
        List<CommentDto> commentDto = commentService.fetchComment(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(commentDto);
    }

    @Operation(summary = "Patch comment REST API",
            description = "Patch comment with input of id"
    )

    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"),
            @ApiResponse(
                    responseCode = "404",
                    description = "NOT FOUND"
            )
    })
    @PatchMapping("/{id}")
    public ResponseEntity<ResponseDto> updateComment(@PathVariable("id") Long id,@Valid @RequestBody CommentDto commentDto){
        commentService.updateComment(id,commentDto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto("201","Comment updated"));
    }

    @Operation(summary = "Delete comment REST API",
            description = "Delete comment with input of id"
    )

    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"),
            @ApiResponse(
                    responseCode = "404",
                    description = "NOT FOUND"
            )
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto> deleteComment(@PathVariable("id") Long id){
        commentService.deleteComment(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto("200","Comment deleted"));
    }

    @DeleteMapping("/p/{id}")
    public ResponseEntity<ResponseDto> deleteAllComments(@PathVariable("id") Long id){
        commentService.deleteAllComments(id);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto("200","Comments deleted"));
    }
}
