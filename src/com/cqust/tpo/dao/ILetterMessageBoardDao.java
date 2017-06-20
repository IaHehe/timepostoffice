package com.cqust.tpo.dao;

import java.util.List;

import com.cqust.tpo.models.LetterMessageBoard;
import com.cqust.tpo.models.TimeLetter;

/**
 * @author DongJiJun
 *
 */
public interface ILetterMessageBoardDao extends IDAO<Integer,LetterMessageBoard>{

	/**
	 * 根据时光信Id创建List()
	 * @param timelettetId
	 * @return
	 * @throws Exception 
	 */
	public List<LetterMessageBoard> findByTLid(int timelettetId) throws Exception;
}	
