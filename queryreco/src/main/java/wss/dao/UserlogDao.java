package wss.dao;

import java.util.List;

import wss.model.Userlog;

public interface UserlogDao {
	public boolean insert(Userlog userLog);
	public List<Userlog> selectAll();
	boolean batchInsert(List<Userlog> list);
	
}
