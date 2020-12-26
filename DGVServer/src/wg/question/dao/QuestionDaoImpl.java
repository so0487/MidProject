package wg.question.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;

import util.BuildSqlMapClient;
import wg.vo.QuestionVO;

public class QuestionDaoImpl implements IQuestionDao {
	private static QuestionDaoImpl dao;
	private SqlMapClient smc;
	
	
	private QuestionDaoImpl() {
		smc = BuildSqlMapClient.getSqlMapClient();
	}
	
	public static QuestionDaoImpl getInstance() {
		if(dao == null) dao = new QuestionDaoImpl();
		return dao;
	}

	@Override
	public int insertQuestion(QuestionVO questionVo) {
		int cnt = 0;
		
		try {
			Object obj = smc.insert("question.insertQuestion",questionVo);
			if(obj==null) cnt = 1;
		} catch (SQLException e) {
			cnt=0;
			e.printStackTrace();
		}
		
		return cnt;
	}

	@Override
	public int deleteQuestion(String question_no) {
		int cnt=0;
		
		try {
			cnt = smc.delete("question.deleteQuestion",question_no);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return cnt;
	}

	@Override
	public int updateQuestion(QuestionVO questionVo) {
		int cnt = 0;
		
		try {
			cnt = smc.update("question.updateQuestion",questionVo);
		} catch (SQLException e) {
			cnt=0;
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public List<QuestionVO> getaAllQuestion(Map<String, String> searchMap) {
		List<QuestionVO> questionList = null;
		
		try {
			questionList = smc.queryForList("question.getAllQuestion",searchMap);
		} catch (SQLException e) {
			questionList = null;
			e.printStackTrace();
		}
		
		return questionList;
	}

	@Override
	public int getMaxQuestionNo() {
		int question_no = 0;
		
		try {
			question_no = (int) smc.queryForObject("question.getMaxQuestionNo");
			int temp = question_no+1;
			question_no = temp;
		} catch (SQLException e) {
			// TODO Auto-generated catch blocks
			e.printStackTrace();
		}
		
		return question_no;
	}

	@Override
	public int getQuestionCount(Map<String, String> searchMap) {
		int cnt = 0;
		
		
		try {
			cnt = (int) smc.queryForObject("question.getQuestinCount",searchMap);
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		}
		
		return cnt;
	}
}
