package com.tonyb650.dojooverflow.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tonyb650.dojooverflow.models.Tag;
import com.tonyb650.dojooverflow.repositories.TagRepository;

@Service
public class TagService {

	@Autowired
	TagRepository tagRepo;
	
	public List<Tag> getAllTags(){
		return tagRepo.findAll();
	}
	
	public Tag createTag(Tag tag) {
		return tagRepo.save(tag);
	}
	
	public Tag updateTag(Tag tag) {
		return tagRepo.save(tag);
	}
	
	public Tag getTagById(Long id) {
		Optional<Tag> possibleTag = tagRepo.findById(id);
		if(possibleTag.isPresent()) {
			return possibleTag.get();
		}
		return null; 
	}
	
	public Tag getTagBySubject(String subject) {
		Optional<Tag> possibleTag = tagRepo.findBySubject(subject);
		if(possibleTag.isPresent()) {
			return possibleTag.get();
		}
		return null; 
	}	

	
}
