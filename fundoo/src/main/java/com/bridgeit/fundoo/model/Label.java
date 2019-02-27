package com.bridgeit.fundoo.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="Label")
public class Label implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name="labelId")
	private int labelId;
	
	@Column(name="labelName")
	private String labelName;
	
	@Column(name="userId")
	private int userId;
	
	public int getUserId() {
		return userId;
	}


	public void setUserId(int userId) {
		this.userId = userId;
	}


	@ManyToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER) 
	@JoinTable(name="NOTE_LABEL" , joinColumns = {@JoinColumn(name="LABEL_ID" , referencedColumnName="labelId")},
	inverseJoinColumns= {@JoinColumn(name="NOTE_ID" , referencedColumnName="id")})
	
	private List<Note> noteList;

	



	public List<Note> getNoteList() {
		return noteList;
	}


	public void setNoteList(List<Note> noteList) {
		this.noteList = noteList;
	}


	public int getLabelId() {
		return labelId;
	}


	public void setLabelId(int labelId) {
		this.labelId = labelId;
	}


	public String getLabelName() {
		return labelName;
	}


	public void setLabelName(String labelName) {
		this.labelName = labelName;
	}


	@Override
	public String toString() {
		return "Label [labelId=" + labelId + ", labelName=" + labelName + ", noteList=" + noteList + "]";
	}
	

}
