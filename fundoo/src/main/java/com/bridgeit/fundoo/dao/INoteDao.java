package com.bridgeit.fundoo.dao;

import java.util.List;

import com.bridgeit.fundoo.model.Note;
import com.bridgeit.fundoo.model.User;

public interface INoteDao {

	boolean saveNote(Note note);

	Note getNote(int id);

	boolean updateNote(Note note);

	List<Note> getAllNotes(int userId);

	Note getArchiveNote(User user);

	boolean deleteNote(Note note);

	

}
