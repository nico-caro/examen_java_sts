package com.zoo.web.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class TipoDAO {

	@Autowired
	private ITipoDAO crud;
	
	public ITipoDAO crud()
	{
		return this.crud;
	}
}
