package com.example.QuestionService.Service;

import com.example.QuestionService.Model.Question;
import com.example.QuestionService.Model.QuestionWrapper;
import com.example.QuestionService.Model.Response;
import com.example.QuestionService.Repository.QuestionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
    @Autowired
    private QuestionRepo questionRepo;

    public String addQuestion(Question question){
        questionRepo.save(question);
        return "Question Added Successfully";
    }

    public String updateQuestion(Question question) {
        questionRepo.save(question);
        return "Question "+question.getId()+ " updated Successfully";
    }

    public List<Question> getAllQuestions() {
        return questionRepo.findAll();
    }

    public List<Question> getAllQuestionsByCategory(String category) {
        return questionRepo.findByCategory(category);
    }

    public String deleteQuestion(int id) {
        questionRepo.deleteById(id);
        return "Question number "+id+" deleted";
    }

    public ResponseEntity<List<Integer>> getQuestionIdsForQuiz(String categoryName, int numQuestions) {
        List<Integer> questionIds = questionRepo.findRandomQuestionsByCategory(categoryName,numQuestions);
        return new ResponseEntity<>(questionIds, HttpStatus.OK);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuestionsForQuiz(List<Integer> questionIds) {

        List<QuestionWrapper> questionWrapperList = new ArrayList<>();
        List<Question> questionList = new ArrayList<>();
        for(Integer id : questionIds){
            questionList.add(questionRepo.findById(id).get());
        }
        for(Question q:questionList){
            QuestionWrapper questionWrapper = new QuestionWrapper(q.getQuestion(),q.getOption1(),q.getOption2(),q.getOption3(),q.getOption4());
            questionWrapperList.add(questionWrapper);
        }
        return new ResponseEntity<>(questionWrapperList,HttpStatus.OK);
    }

    public ResponseEntity<Integer> CalculateScore(List<Response> responseList) {
        int score = 0;
        for(Response r:responseList){
            Question question = questionRepo.findById(r.getQuestionId()).get();
            if(question.getCorrectOption().equals(r.getOptionSelected())){
                score++;
            }
        }
        return new ResponseEntity<>(score,HttpStatus.OK);
    }
}
