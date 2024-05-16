package com.example.QuizService.Controller;

import com.example.QuizService.Model.QuestionWrapper;
import com.example.QuizService.Model.QuizDTO;
import com.example.QuizService.Model.Response;
import com.example.QuizService.Service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {

    @Autowired
    private QuizService quizService;

    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestBody QuizDTO quizDTO){
        return quizService.createQuiz(quizDTO.getCategoryName(),quizDTO.getNumQ(),quizDTO.getTitle());
    }

    @GetMapping("getQuiz/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable int id){
        return quizService.getQuizQuestions(id);
    }

    @PostMapping("/submit/{id}")
    public ResponseEntity<Integer> getResults(@RequestBody List<Response> response){
        return quizService.calculateResult(response);
    }
}