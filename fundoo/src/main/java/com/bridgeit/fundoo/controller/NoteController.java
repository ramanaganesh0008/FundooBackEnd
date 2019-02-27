package com.bridgeit.fundoo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bridgeit.fundoo.model.Note;
import com.bridgeit.fundoo.model.Response;
import com.bridgeit.fundoo.service.INoteService;

@RestController
////@RequestMapping("/fundoo")

@CrossOrigin(origins= {"*"},allowedHeaders = "*",
exposedHeaders= {"token"})
//@CrossOrigin
public class NoteController 
{

	Response response;
	@Autowired
	INoteService noteService;


	/**
	 * 
	 * @param note contains note to add
	 * @param token contains userId for validation 
	 * @return
	 */
	@RequestMapping(value="/note",method=RequestMethod.POST) 
	public ResponseEntity<Response> addNoteToUser(@RequestBody Note note,@RequestHeader("token")String token)
	{

		noteService.addNote(token,note);
		
		response=new Response();

		response.setStatusCode(166);
		response.setStatus("note added successfully");
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
	
	
	/**
	 * 
	 * @param note contains update note
	 * @param token contains userId for validation
	 * @return
	 */
	@RequestMapping(value="/note",method=RequestMethod.PUT)
	public ResponseEntity<Response> editNote(@RequestBody Note note,@RequestHeader("token") String token)
	{
		boolean check=noteService.updateNote(note,token);
		response=new Response();
		if(check)
		{
			response.setStatusCode(166);
			response.setStatus("note updated successfully");
			return new ResponseEntity<Response>(response,HttpStatus.OK);
		}
		response.setStatusCode(0);
		response.setStatus("note is not present");
		return new ResponseEntity<Response>(response,HttpStatus.NOT_FOUND);
	}

	
	/**
	 * 
	 * @param token contains userId for delete note
	 * @return
	 */
	@RequestMapping(value="/note",method=RequestMethod.GET)
	public ResponseEntity<List<Note>> getAllNote(@RequestHeader("token") String token)
	{	
		List<Note> noteList=noteService.getAllNote(token);	
		System.out.println("return Note "+noteList);
		return new ResponseEntity<List<Note>>(noteList,HttpStatus.OK);
	}
	
	
	/**
	 * 
	 * @param id contains note id to delte permenantly
	 * @param token contains userId for delete
	 * @return
	 */
	@RequestMapping(value="/note/{id}" , method=RequestMethod.DELETE)
	public ResponseEntity<Response> delteForeverNote(@PathVariable int id,@RequestHeader("token") String token)
	{
		noteService.deleteNote(id,token);
		response=new Response();
		response.setStatusCode(166);
		response.setStatus("note deleted permenantly successfully");
//		System.out.println("before send "+trashNote);
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}

	

	/**
	 * 
	 * @param id contains note id for update archive/unarchive
	 * @param token contains userId for validation
	 * @return
	 */
	@RequestMapping(value="/note/archive/{id}",method=RequestMethod.PUT)
	public ResponseEntity<Response> updateArchive(@PathVariable("id") int id,@RequestHeader("token") String token)
	{
		//noteService.updateArchive(note);
		System.out.println("archive");
	
		noteService.updateArchive(id,token);
		response=new Response();
		response.setStatusCode(166);
		response.setStatus("note updated successfully");
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
	
	/**
	 * 
	 * @param id contains note id for update trash/restore
	 * @param token contains userId for validation
	 * @return
	 */
	@RequestMapping(value="/note/trash/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Response> deleteNote(@PathVariable int id,@RequestHeader("token") String token)
	{
		
		System.out.println("UPDATE trash note");
	
		noteService.trashUpdateNote(id,token);
		response=new Response();
		response.setStatusCode(166);
		response.setStatus("note deleted successfully");
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
	
	/**
	 * 
	 * @param id contains note id for update pin/unpin
	 * @param token contains userId for validation
	 * @return
	 */
	@RequestMapping(value="/note/pin/{id}",method=RequestMethod.PUT)
	public ResponseEntity<Response> updatePin(@PathVariable int id,@RequestHeader("token") String token)
	{
		noteService.updatePin(id,token);
		response=new Response();
		response.setStatusCode(166);
		response.setStatus("update pin successfully");
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
	
	
	
//	@RequestMapping(value="/note/archive/{id}",method=RequestMethod.POST)
//	public ResponseEntity<Response> archiveNote(@PathVariable int id,@RequestHeader("token") String token)
//	{
//		System.out.println(token);
//		noteService.archiveNote(token,id);
//		response=new Response();
//		response.setStatusCode(200);
//		response.setStatus("archive successfully");
//		System.out.println("conroller");
//		return new ResponseEntity<Response>(response,HttpStatus.OK);
//	}
//	
	
//	@RequestMapping(value="/note/color/{id}", method=RequestMethod.PUT)
//	public ResponseEntity<Response> updateColorNote(@RequestBody Note note,@PathVariable int id,@RequestHeader("token") String token)
//	{
//		
//		System.out.println("UPDATE color note");
//		noteService.colorUpdateNote(id,token,note);
//		response=new Response();
//		response.setStatusCode(166);
//		response.setStatus("color updated successfully");
//		return new ResponseEntity<Response>(response,HttpStatus.OK);
//	}
	
	
//	@RequestMapping(value="getTrashNote",method=RequestMethod.GET)
//	public ResponseEntity<List<Note>> getTrashNote(@RequestHeader("token") String token)
//	{
//		List<Note> trashNote=noteService.getTrashNote(token);
//		response=new Response();
//		response.setStatusCode(166);
//		response.setStatus("note get successfully");
//		System.out.println("before send "+trashNote);
//		return new ResponseEntity<List<Note>>(trashNote,HttpStatus.OK);
//	}
//	
//	
//	@RequestMapping("/createLabel/{id}")
//	public ResponseEntity<Response> addLabel(@RequestBody Note note,@PathVariable int id)
//	{
//		System.out.println(note);
//		response.setStatusCode(166);
//		response.setStatus("note deleted permenantly successfully");
//		return new ResponseEntity<Response>(response,HttpStatus.OK);
//	}
//	
	
//	
//	@RequestMapping(value="/getNote",method=RequestMethod.GET)
//	public ResponseEntity<Note> getNote(@RequestHeader("token")String token)
//	{
//		//System.out.println("hi");
//		Note userNote=noteService.getNote(token);
//		response=new Response();
//		response.setStatusCode(166);
//		response.setStatus("note get successfully");
//		System.out.println("before send "+userNote);
//		return new ResponseEntity<Note>(userNote,HttpStatus.OK);
//	}
//	
//	
}
