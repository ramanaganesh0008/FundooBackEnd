package com.bridgeit.fundoo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.bridgeit.fundoo.dao.ILabelDao;
import com.bridgeit.fundoo.model.Label;
import com.bridgeit.fundoo.utility.UserToken;

@Transactional
public class LabelServiceImplementation implements ILabelService
{
	@Autowired
	ILabelDao labeldao;

	

	@Override
	public boolean addLabel(Label label,String token) {
		System.out.println(labeldao);
		try {
			int userId=UserToken.tokenVerify(token);
			label.setUserId(userId);
			System.out.println("service "+label);

			boolean check=labeldao.save(label);
			

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}



	@Override
	public void getLabel(int id) {
		
		//labeldao.getLabel()
	}



	@Override
	public List<Label> getAllLabel(String token) {
	
		try {
				int userId = UserToken.tokenVerify(token);
				List<Label> labelList=labeldao.getAllLabel(userId);
				return labelList;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}



	@Override
	public boolean updateLabel(Label updateLabel,int labelId, String token) {
		try {
			int userId=UserToken.tokenVerify(token);
			Label label=labeldao.getLabel(labelId);
			if(label.getUserId()==userId)
			{
				
				label.setLabelName(updateLabel.getLabelName());
				labeldao.updateLabel(label);
				return true;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}



	@Override
	public boolean deleteLabel(int labelId, String token) 
	{
		try {
			System.out.println(labelId);
			int userId=UserToken.tokenVerify(token);
			Label label=labeldao.getLabel(labelId);
			System.out.println(label);
			labeldao.deleteLabel(label);
			return true;
				
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

}
