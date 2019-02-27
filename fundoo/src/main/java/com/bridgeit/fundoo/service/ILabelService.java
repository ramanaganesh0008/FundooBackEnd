package com.bridgeit.fundoo.service;

import java.util.List;

import com.bridgeit.fundoo.model.Label;

public interface ILabelService {

	boolean addLabel(Label label,String token);

	void getLabel(int id);

	List<Label> getAllLabel(String token);

	boolean updateLabel(Label label, int id, String token);

	boolean deleteLabel(int id, String token);

}
