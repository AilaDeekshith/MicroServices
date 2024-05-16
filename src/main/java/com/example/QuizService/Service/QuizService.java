package com.example.QuizService.Service;

import com.example.QuizService.Feign.QuizInterface;
import com.example.QuizService.Model.Question;
import com.example.QuizService.Model.QuestionWrapper;
import com.example.QuizService.Model.Quiz;
import com.example.QuizService.Model.Response;
import com.example.QuizService.Repository.QuizRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    private QuizRepo quizRepo;

    @Autowired
    private QuizInterface quizInterface;

    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {

        List<Integer> questionIds = quizInterface.getQuestionsIdsForQuiz(category,numQ).getBody();

        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestionIds(questionIds);
        quizRepo.save(quiz);

        return new ResponseEntity<>("success", HttpStatus.CREATED);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(int id) {
        Optional<Quiz> quiz = quizRepo.findById(id);

       List<Integer> questionIds = quiz.get().getQuestionIds();
       return quizInterface.getQuestionsForQuiz(questionIds);

    }

    public ResponseEntity<Integer> calculateResult(List<Response> response) {
        return quizInterface.getScore(response);
    }
}
