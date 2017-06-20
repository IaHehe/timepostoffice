package com.cqust.tpo.service;

import java.util.List;

import com.cqust.tpo.models.LetterMessageBoard;
import com.cqust.tpo.models.TimeLetter;
import com.cqust.tpo.models.User;

/**
 * @author DongJiJun
 *
 */
public interface ILetterMessageBoardService extends IService<Integer,LetterMessageBoard>{
	
	/**
	 * 根据时光信Id,生成List()
	 * @param timeletterId
	 * @return
	 */
	public List<LetterMessageBoard> list(int timeletterId);
}
