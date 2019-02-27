package com.bridgeit.fundoo.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.bridgeit.fundoo.dao.INoteDao;
import com.bridgeit.fundoo.model.Note;
import com.bridgeit.fundoo.model.User;
import com.bridgeit.fundoo.utility.UserToken;

@Transactional
public class NoteServiceImplementation implements INoteService
{

	@Autowired
	INoteDao noteDao;
	@Autowired
	IUserService userService;
	
	@Override
	public boolean addNote(String token,Note note) 
	{
		
		try {
			int id=UserToken.tokenVerify(token);
			System.out.println("entered id is "+id);
			User user=userService.getUser(id);
			if(user!=null)
			{
				note.setUser(user);
				Date date=new Date();
				note.setCreateStamp(date);
				boolean check=noteDao.saveNote(note);
					if(check)
						return true;
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}


	@Override
	public List<Note> getAllNote(String token) 
	{
		try {
			int userId=UserToken.tokenVerify(token);
			List<Note> userNoteList=noteDao.getAllNotes(userId);

			return userNoteList;
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Note> getArchiveNote(String token) {
		try {
			int id=UserToken.tokenVerify(token);
			System.out.println("entered id is "+id);
			User user=userService.getUser(id);
			
			
			List<Note> userNoteList=noteDao.getAllNotes(user.getUserId());
			System.out.println("userNote "+userNoteList);
			List<Note> archiveNotes=new ArrayList<>();
//			int index=0;
			for (int i = 0; i < userNoteList.size(); i++) 
			{
				if(userNoteList.get(i).isArchive()==true)
				{
					archiveNotes.add(userNoteList.get(i));
				}
			}
			System.out.println(archiveNotes);
			return archiveNotes;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	
		return null;
	}
	@Override
	public boolean updateArchive(int id,String token) 
	{
		
		try {
			int userId = UserToken.tokenVerify(token);
			Note note=noteDao.getNote(id);
			if(userId==note.getUser().getUserId())
			{
				System.out.println(note.isArchive());
				if(note.isArchive()==false)
					note.setArchive(true);
				else
					note.setArchive(false);
				Date date=new Date();
				note.setLastModifiedStamp(date);
				noteDao.updateNote(note);
				return true;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return false;
	}
	@Override
	public boolean trashUpdateNote(int id,String token) 
	{
		try {
			int userId = UserToken.tokenVerify(token);
			Note note=noteDao.getNote(id);
			if(userId==note.getUser().getUserId())
			{
					if(note.isTrash()==false)
						note.setTrash(true);
					else
						note.setTrash(false);
					Date date=new Date();
					note.setLastModifiedStamp(date);
					noteDao.updateNote(note);
					return true;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	
	@Override
	public boolean updatePin(int id, String token) {
		
		try {
			int userId = UserToken.tokenVerify(token);
			Note note=noteDao.getNote(id);
			if(userId==note.getUser().getUserId())
			{
					if(note.isPin()==false)
						note.setPin(true);
					else
						note.setPin(false);
					Date date=new Date();
					note.setLastModifiedStamp(date);
					noteDao.updateNote(note);
					return true;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	
	
	@Override
	public List<Note> getTrashNote(String token) {
		try {
			int id=UserToken.tokenVerify(token);
			System.out.println("entered id is "+id);
			User user=userService.getUser(id);
			
			
			List<Note> userNoteList=noteDao.getAllNotes(user.getUserId());
			System.out.println("userNote "+userNoteList);
			List<Note> trashNote=new ArrayList<>();
//			int index=0;
			for (int i = 0; i < userNoteList.size(); i++) 
			{
				if(userNoteList.get(i).isTrash()==true)
				{
					trashNote.add(userNoteList.get(i));
				}
			}
			System.out.println(trashNote);
			return trashNote;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	
		return null;
	}
	
	@Override
	public boolean deleteNote(int id,String token) 
	{
		try {
			int userId=UserToken.tokenVerify(token);
			Note note=noteDao.getNote(id);
			if(userId==note.getUser().getUserId())
			{
				System.out.println(note.getId());
			boolean check=noteDao.deleteNote(note);
		
			return check;
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  return false;
	}
	@Override
	public boolean updateNote(Note note,String token) 
	{
		try {
			int userId=UserToken.tokenVerify(token);
		
			
			if(userId==note.getUser().getUserId())
			{
				Date date=new Date();
				note.setLastModifiedStamp(date);
				boolean check=noteDao.updateNote(note);
				return check;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

	
	
	
	
//	@Override
//	public Note getNote(String token) 
//	{
//		try {
//			int id=UserToken.tokenVerify(token);
//			System.out.println("entered id is "+id);
//			User user=userService.getUser(id);
//			
//			
//			Note userNote=noteDao.getNote(user);
//			return userNote;
//			
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return null;
//	}
	
	@Override
	public boolean archiveNote(String token, int id) 
	{
//		try {
//			int userId=UserToken.tokenVerify(token);
//			System.out.println("entered id is "+id);
//			User user=userService.getUser(id);
//			Note note=noteDao.getNote(id);
//			if(userId==note.getUser().getUserId())
//			{
//				note.setArchive(true);
//				boolean check=noteDao.
//			}
//			
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		return false;
	}
	
	@Override
	public boolean colorUpdateNote(int id,String token,Note note) {
//		noteDao.updateNote(note);
		return true;
	}
	}






