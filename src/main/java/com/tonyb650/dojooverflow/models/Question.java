package com.tonyb650.dojooverflow.models;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="questions")
public class Question {
	
	// MEMBER VARIABLES
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank (message="Question is required.")
	@Size(min=8, max=128, message="Question must be between 8 and 128 characters.")
	private String text;

	@Transient
	@NotBlank (message="At least one tag is required.")
	private String tagEntry;
	
	@Column(updatable=false)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date createdAt;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date updatedAt;
	
	// RELATIONSHIPS
	@ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "tags_questions", 
            joinColumns = @JoinColumn(name = "tag_id"), 
            inverseJoinColumns = @JoinColumn(name = "question_id")
    )
	private List<Tag> tags;
	
	@OneToMany(mappedBy="question",  cascade=CascadeType.ALL, fetch=FetchType.LAZY)  // mappedBy = the 'one' table
	private List<Answer> answers;
	
	
	// CONSTRUCTORS 
	public Question() {
	}
	
	// METHODS
	@PrePersist
	protected void onCreate() {
		this.createdAt = new Date();
	}
	
	@PreUpdate
	protected void onUpdate() {
		this.updatedAt = new Date();
	}

	// GETTERS AND SETTERS
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}

	public List<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}
	
	public String getTagEntry() {
		return tagEntry;
	}

	public void setTagEntry(String tagEntry) {
		this.tagEntry = tagEntry;
	}
}
