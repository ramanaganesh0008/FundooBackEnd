package com.bridgeit.fundoo.dao;

import java.util.List;

import com.bridgeit.fundoo.model.Label;

public interface ILabelDao {

	boolean save(Label label);

	List<Label> getAllLabel(int userId);

	Label getLabel(int labelId);

	boolean updateLabel(Label label);

	boolean deleteLabel(Label label);

}
