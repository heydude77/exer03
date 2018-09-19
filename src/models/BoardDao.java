package models;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class BoardDao extends Dao{
	SqlSessionFactory factory;

	public BoardDao() throws IOException {
		SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
		InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
		factory = builder.build(is);
	}
	
	public int addIssue(Map map) {
		SqlSession sql = factory.openSession(); // JDBC 의 Connect과정
		try {
			int r = sql.insert("board.addIssue", map);
			if (r == 1)
				sql.commit();
			return r;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}finally {
			sql.close();
		}
	}	
	
	public int addOpinion(Map map) {
		SqlSession sql = factory.openSession(); // JDBC 의 Connect과정
		try {
			int r = sql.insert("board.addOpinion", map);
			if (r == 1)
				sql.commit();
			return r;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}finally {
			sql.close();
		}
	}

	public List<Map> getAllIssue() {
		SqlSession sql = factory.openSession(); 
		try {			
			List<Map> p = sql.selectList("board.getAllIssue");
			return p;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			sql.close();
		}
	}
	public List<Map> getAllOpinion() {
		SqlSession sql = factory.openSession(); 
		try {			
			List<Map> p = sql.selectList("board.getAllOpinion");
			return p;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			sql.close();
		}
	}
	public List<Map> getAllOpinionByNo(String no) {
		SqlSession sql = factory.openSession(); 
		try {			
			List<Map> p = sql.selectList("board.getAllOpinionByNo", no);
			return p;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally {
			sql.close();
		}
	}	
	
	public boolean getId(String id) {
		SqlSession sql = factory.openSession(); 
		try {
			Map p = sql.selectOne("account.getId", id);
			if(p.get("ID").equals(id))
				return true;
			else
				return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			sql.close();
		}	
		
	}
	
	
}
