package com.csn.commentservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(
        name="Comment",
        description =  "Comment schema which holds the comment information of the user"
)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentDto {
    @NotBlank(message = "Comment cannot be blank")
    private String content;
}
