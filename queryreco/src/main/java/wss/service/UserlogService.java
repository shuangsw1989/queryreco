package wss.service;

import java.util.List;

import wss.model.Userlog;
public interface UserlogService {
	public boolean insert(Userlog userLog);
	public List<Userlog> selectAll();
	public boolean batchInsert(List<Userlog> list);
}
