package wg.answer.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;

import util.BuildSqlMapClient;
import wg.vo.AnswerVO;

public class AnswerDaoImpl implements IAnswerDao {
	private static AnswerDaoImpl dao;  
	private SqlMapClient smc;


	private AnswerDaoImpl(){
		smc = BuildSqlMapClient.getSqlMapClient();
	}  	

	public static AnswerDaoImpl getInstance(){
		if(dao == null) dao = new AnswerDaoImpl();

		return dao;
	}

	@Override
	public int insertAnswer(AnswerVO answerVo) {
		int cnt=0;

		try {
			Object obj = smc.insert("answer.insertAnswer",answerVo);
			if(obj==null) cnt=1;
		} catch (SQLException e) {
			cnt=0;
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int deleteAnswer(String answer_no) {
		int cnt = 0;

		try {
			cnt = smc.delete("answer.deleteAnswer",answer_no);
		} catch (SQLException e) {
			cnt=0;
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int updateAnswer(AnswerVO answerVo) {
		int cnt = 0;


		try {
			cnt = smc.update("answer.updateAnswer",answerVo);
		} catch (SQLException e) {
			cnt=0;
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public List<AnswerVO> getAllAnswerList(Map<String, String> searchMap) {
		List<AnswerVO> answerList = null;
		
		
		try {
			answerList = smc.queryForList("answer.getAllAnswer",searchMap);
		} catch (SQLException e) {
			answerList = null;
			e.printStackTrace();
		}
		
		return answerList;
	}

	@Override
	public int getMaxAnswerNo() {
		int answer_no = 0;
		
		try {
			answer_no = (int) smc.queryForObject("answer.getMaxAnswerNo");
			int tmep = answer_no+1;
			answer_no = tmep;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return answer_no;
	}

	@Override
	public int getAnswerCount(Map<String, String> searchMap) {
		int cnt = 0;
		
		try {
			cnt = (int) smc.queryForObject("answer.getAnswerNoCount",searchMap);
		} catch (SQLException e) {
			cnt=0;
			e.printStackTrace();
		}
		
		return cnt;
	}

}
