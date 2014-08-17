package wss.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import wss.dao.UserlogDao;
import wss.model.Userlog;
@SuppressWarnings("unchecked")
@Repository("userlogDao")
public class UserlogDaoImpl extends SqlSessionDaoSupport implements UserlogDao{
	 /*sql ”Ôæ‰*/
    private static final String INSERT = "insert";
    private static final String SELECTALL ="selectAll";
    
	@Resource
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory){
        super.setSqlSessionFactory(sqlSessionFactory);
    }
	public boolean insert(Userlog userLog) {
		String sql = getStatementId(Userlog.class, INSERT);
        this.getSqlSession().insert(sql, userLog);
        return true;
	}
	@Override
	public List<Userlog> selectAll() {
		String sql = getStatementId(Userlog.class,SELECTALL);
		List<Userlog> list = getSqlSession().selectList(sql);
		return list;
	}
	@Override
	public boolean batchInsert(List<Userlog> list) {
		String sql = getStatementId(Userlog.class, "batchInsert");
        this.getSqlSession().insert("batchInsert",list);
        return true;
	}
	
	
	 /**  
     * ”≥…‰sqlid
     */
    private String getStatementId(Class entityClass, String suffix)
    {
        String sqlStr = entityClass.getName() + "." + suffix;
//        System.out.println("getStatementId:" + sqlStr);
        return sqlStr;
    }


}
