package com.bridgeit.fundoo.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bridgeit.fundoo.model.Label;

@Repository
public class LabelDao implements ILabelDao
{
	@Autowired
	SessionFactory factory;

	

	@Override
	public boolean save(Label label) 
	{
		if(factory!=null)
		{
			System.out.println("dao "+label);
			factory.getCurrentSession().save(label);
			System.out.println("label added successfully");
			return true;
		}
		return false;
	}



	@Override
	public List<Label> getAllLabel(int userId) {
		if(factory!=null)
		{
			List<Label> listLabel=factory.getCurrentSession().createCriteria(Label.class).add(Restrictions.eq("userId", userId)).list();
			return listLabel;
		}
		return null;
	}



	@Override
	public Label getLabel(int labelId) 
	{
		if(factory!=null)
		{
			Label label=(Label)factory.getCurrentSession().get(Label.class, labelId);
			return label;
		}
		return null;
	}



	@Override
	public boolean updateLabel(Label label) {
		if(factory!=null)
		{
			factory.getCurrentSession().update(label);
			System.out.println("label updated successfully");
			return true;
		}
		
		return false;
	}



	@Override
	public boolean deleteLabel(Label label) {
		if(factory!=null)
		{
			factory.getCurrentSession().delete(label);
			System.out.println("deleted succssfully");
			return true;
		}
		return false;
	}

}
