package models;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MybatisDao extends Dao{
	SqlSessionFactory factory;

	public MybatisDao() throws IOException {
		SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
		InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
		factory = builder.build(is);
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
	
	public boolean getUserPw(String pw) {
		SqlSession sql = factory.openSession(); 
		try {
			Map p = sql.selectOne("account.getUserPw", pw);
			if(p.get("pass").equals(pw))
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
	
	public boolean loginCheck(Map m) {
		SqlSession sql = factory.openSession(); 
		try {
			String inputId =(String) m.get("inputId");
			String inputPw =(String) m.get("inputPw");
			Map p = sql.selectOne("account.getLoginInfo", inputId);
			if (p !=null) {
				if(p.get("ID").equals(inputId) && p.get("PASS").equals(inputPw))
					return true;
				else 
					return false;
			}
			else
				return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			sql.close();
		}	
	}
	
	public void addAccount(Map m) {
		SqlSession sql = factory.openSession();
		try {
			int r = sql.insert("account.addAccount", m);
			if(r==1)
				sql.commit();
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			sql.close();
		}
	}	
	
}
