package com.bridgeit.fundoo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bridgeit.fundoo.model.Label;
import com.bridgeit.fundoo.model.Response;
import com.bridgeit.fundoo.service.ILabelService;

@RestController
public class LabelController {

	Response response;
	@Autowired
	ILabelService labelService;

	
	/**
	 * 
	 * @param label contains label to add 
	 * @param token contains userId 
	 * @return
	 */
	@RequestMapping(value = "/label", method = RequestMethod.POST)
	public ResponseEntity<Response> addLabel(@RequestBody Label label, @RequestHeader("token") String token) {
		System.out.println(labelService);

		System.out.println(label);
		boolean check = labelService.addLabel(label,token);
		response = new Response();
		response.setStatusCode(200);
		response.setStatus("success");
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}

	/**
	 *  
	 * @param token contains userId to get label 
	 * @return
	 */
	@RequestMapping(value = "/label", method = RequestMethod.GET)
	public ResponseEntity<List<Label>> getAllLabel(@RequestHeader("token") String token)
	{
		List<Label> labelList = labelService.getAllLabel(token);
		return new ResponseEntity<List<Label>>(labelList, HttpStatus.OK);
	}
	
	
	/**
	 * 
	 * @param label contains update label details
	 * @param id contains label id
	 * @param token contains userId for validation
	 * @return
	 */
	@RequestMapping(value="/label/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Response> updateLabel(@RequestBody Label label,@PathVariable int id,@RequestHeader("token") String token)
	{
		labelService.updateLabel(label,id,token);
		response =new Response();
		response.setStatus("updated successfully");
		response.setStatusCode(166);
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
	
	/**
	 * 
	 * @param id contains labelId
	 * @param token contains userId
	 * @return
	 */
	@RequestMapping(value="/label/{id}" , method=RequestMethod.DELETE)
	public ResponseEntity<Response> deleteLabel(@PathVariable int id,@RequestHeader("token") String token)
	{
		labelService.deleteLabel(id,token);
		response =new Response();
		response.setStatus("deleted successfully");
		response.setStatusCode(166);
		return new ResponseEntity<Response>(response,HttpStatus.OK);
	}
}
