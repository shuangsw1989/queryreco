package wss.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



import wss.dao.UserlogDao;
import wss.model.Userlog;
import wss.service.UserlogService;
@Scope("prototype")  
@Service("userlogService")
@Transactional
public class UserlogServiceImpl implements UserlogService{
	@Autowired(required = true)
    private UserlogDao userlogDao;

	public boolean insert(Userlog userLog) {
		userlogDao.insert(userLog);  
	        return true;  
	}
	@Override
	public List<Userlog> selectAll() {
		userlogDao.selectAll();
		return null;
	}
	@Override
	public boolean batchInsert( List<Userlog> list) {
		userlogDao.batchInsert(list);
	        return true;  
	}



}
