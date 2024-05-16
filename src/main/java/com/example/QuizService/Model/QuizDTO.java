package com.example.QuizService.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuizDTO {

    private String categoryName;
    private int numQ;
    private String title;
}
