package com.example.QuizService.Feign;

import com.example.QuizService.Model.QuestionWrapper;
import com.example.QuizService.Model.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("QUESTION-SERVICE")
public interface QuizInterface {
    @GetMapping("Question/generate")
    public ResponseEntity<List<Integer>> getQuestionsIdsForQuiz(@RequestParam String categoryName, @RequestParam int numQuestions);

    @PostMapping("Question/getQuestions")
    public ResponseEntity<List<QuestionWrapper>> getQuestionsForQuiz(@RequestBody List<Integer> questionIds);

    @PostMapping("Question/getScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<Response> responseList);
}
