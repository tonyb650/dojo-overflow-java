package com.tonyb650.dojooverflow.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tonyb650.dojooverflow.models.Answer;
import com.tonyb650.dojooverflow.repositories.AnswerRepository;

@Service
public class AnswerService {

	@Autowired
	AnswerRepository answerRepo;
	
	public List<Answer> getAllAnswers(){
		return answerRepo.findAll();
	}
	
	public Answer createAnswer(Answer answer) {
		return answerRepo.save(answer);
	}
	
	public Answer updateAnswer(Answer answer) {
		return answerRepo.save(answer);
	}	
	
}
