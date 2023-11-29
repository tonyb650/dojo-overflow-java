package com.tonyb650.dojooverflow.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tonyb650.dojooverflow.models.Answer;
import com.tonyb650.dojooverflow.models.Question;
import com.tonyb650.dojooverflow.models.Tag;
import com.tonyb650.dojooverflow.services.AnswerService;
import com.tonyb650.dojooverflow.services.QuestionService;
import com.tonyb650.dojooverflow.services.TagService;

import jakarta.validation.Valid;

@Controller
public class MainController {
	
	@Autowired
	QuestionService questions;
	
	@Autowired
	TagService tags;
	
	@Autowired
	AnswerService answers;
	
	@GetMapping("/")
	public String index() {
		return "redirect:/dashboard";
	}
	
	@GetMapping("/dashboard")
	public String dashboard(Model model) {
		model.addAttribute("questions", questions.getAllQuestions());
		return "dashboard.jsp";
	}
	
	@GetMapping("/questions/new")
	public String addQuestion(@ModelAttribute("question") Question question) {
		return "addquestion.jsp";
	}
	
	@GetMapping("/questions/{id}/show")
	public String showQuestion(@ModelAttribute("answer") Answer answer, Model model, @PathVariable("id") Long questionId) {
		model.addAttribute("question", questions.getQuestionById(questionId));
		return "detail.jsp";
	}
	
	@PostMapping("/questions/new")
	public String createQuestion(@Valid @ModelAttribute("question") Question question, BindingResult result) { //, @RequestParam("tagEntry") String tagEntry
		// split string into an array of Strings: 'tagList'
		String[] tagList = question.getTagEntry().split(",");
		
		//
		if(tagList.length>3 || tagList.length<1) {
			result.rejectValue("tagEntry", "Matches", "Need between 1 and 3 tags!");
		}
		if(result.hasErrors()) {
			return "addquestion.jsp";
		}
		
		// turn into tag objects and put into ArrayList<Tag>
		ArrayList<Tag> tagsForThisQuestion = new ArrayList<Tag>();
		for(String tagSubject : tagList){
			tagSubject = tagSubject.trim().toLowerCase();
			Tag thisTag = tags.getTagBySubject(tagSubject);
			if( thisTag == null) {
				// doesn't already exist
				thisTag = new Tag(tagSubject);
				tags.createTag(thisTag);
			}
			tagsForThisQuestion.add((Tag) thisTag);
		// should end up with this: List<Tag>		
		}
		// now, assign them to the question
		question.setTags(tagsForThisQuestion);
		questions.createQuestion(question);
		return "redirect:/dashboard";
	}
	
	@PostMapping("/answers/new")
	public String createAnswer(@Valid @ModelAttribute("answer") Answer answer, BindingResult result, Model model, @RequestParam("question_id") Long questionId) {
		if(result.hasErrors()) {
			model.addAttribute("question", questions.getQuestionById(questionId));
			return "detail.jsp";
		}
		answer.setQuestion(questions.getQuestionById(questionId));
		answers.createAnswer(answer);
		return "redirect:/dashboard";
	}
}
