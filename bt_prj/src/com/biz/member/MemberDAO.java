package com.biz.member;

import org.apache.ibatis.session.SqlSession;
import com.biz.common.MyBatisFactory;

public class MemberDAO {
	
	/**
	 * 비밀번호 변경
	 * @param email
	 * @return
	 */
	public int changePassword(MemberVO vo) {
		SqlSession conn =null;
		int res = 0;
		try { 
			conn = MyBatisFactory.getFactory().openSession();
			res = conn.update("changePassword", vo);
			conn.commit();
		} finally {
			conn.close();
		}
		return res;
	}
	
	/**
	 * 회원 가입
	 * @param mvo
	 * @return
	 */
	public int insert(MemberVO mvo) {
		SqlSession conn =null;
		int res = 0;
		try { 
			conn = MyBatisFactory.getFactory().openSession();
			res = conn.insert("member_register", mvo);
			conn.commit();
		} finally {
			conn.close();
		}
		return res;
	}
	
	/**
	 * 회원정보 수정
	 * @param mvo
	 * @return
	 */
	public int update(MemberVO mvo) {
		SqlSession conn =null;
		int res = 0;
		try { 
			conn = MyBatisFactory.getFactory().openSession();
			res = conn.update("member_update", mvo);;
			conn.commit();
		} finally {
			conn.close();
		}
		return res;
	}
	
	
	/**
	 * 회원 탈퇴 : USER_GUBUN='0' / USER_DEL='y'
	 * @param userId
	 * @return
	 */
	public int update(String userId) {
		SqlSession conn =null;
		int res = 0;
		try { 
			conn = MyBatisFactory.getFactory().openSession();
			res = conn.update("member_delete",userId);
			conn.commit();
		} finally {
			conn.close();
		}
		return res;
	}
	
	/**
	 * 회원 정보 수정을 위한 모든 정보 가져오기
	 * @param userId
	 * @return
	 */
	public MemberVO select(String userId) {
		MemberVO mvo = new MemberVO();
		SqlSession conn =null;
		try { 
			conn = MyBatisFactory.getFactory().openSession();
			mvo = conn.selectOne("member_detail",userId);
		} finally {
			conn.close();
		}
		return mvo;
	}
	
	/**
	 * 로그인 정보 가져오기
	 * @param mvo
	 * @return
	 */
	public MemberVO select(MemberVO mvo) {
		SqlSession conn =null;
		try { 
			conn = MyBatisFactory.getFactory().openSession();
			mvo = conn.selectOne("login",mvo);
			//MemberMapper mapper = conn.getMapper(MemberMapper.class);
			//mvo = mapper.selectMember(mvo.getUserId());
		} finally {
			conn.close();
		}
		
		return mvo;
	}
}


