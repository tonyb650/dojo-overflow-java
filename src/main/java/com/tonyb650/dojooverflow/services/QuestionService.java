package com.tonyb650.dojooverflow.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tonyb650.dojooverflow.models.Question;
import com.tonyb650.dojooverflow.repositories.QuestionRepository;


@Service
public class QuestionService {

	@Autowired
	QuestionRepository questionRepo;
	
//	@Autowired
//	TagService	tagService;
	
	public List<Question> getAllQuestions(){
		return questionRepo.findAll();
	}
	
	public Question getQuestionById(Long id) {
		Optional<Question> possibleQuestion= questionRepo.findById(id);
		if(possibleQuestion.isPresent()) {
			return possibleQuestion.get();
		}
		return null;
	}
	
	public Question createQuestion(Question question) {
		return questionRepo.save(question);
	}
	
	public Question updateQuestion(Question question) {
		return questionRepo.save(question);
	}
}
