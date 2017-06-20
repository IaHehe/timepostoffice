package com.cqust.tpo.dao;

import java.util.List;

import com.cqust.tpo.models.IndexAd;

public interface IIndexAdDao extends IDAO<Integer, IndexAd>{
	public List<IndexAd> findAll() throws Exception;
}
