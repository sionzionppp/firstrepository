package com.biz.shop;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;

import com.biz.common.MyBatisFactory;

public class ShopDAO {
	//	ArrayList<ShopVO>	select	()	
	//	ShopVO	select	(int seq)	
	//	int	insert	(ShopVO svo)	
	//	int	update	(ShopVO svo)	
	//	int	delete	(int seq)	
	
	public int selectCount() {
		int res = 0;
		SqlSession conn =null;
		try { 
			conn = MyBatisFactory.getFactory().openSession();
			res = conn.selectOne("shopNameSpace.selectCount");
		} finally {
			conn.close();
		}
		return res;
	}
	
	public int replyInsert(ReplyVO rvo) {
		int res = 0;
		SqlSession conn =null;
		try { 
			conn = MyBatisFactory.getFactory().openSession();
			res = conn.insert("shopNameSpace.reply_insert", rvo);
			conn.commit();
		} finally {
			conn.close();
		}
		return res;
	}
	
	
	public ArrayList<ReplyVO> replySelect(int sseq) {
		ArrayList<ReplyVO> rlist = new ArrayList<ReplyVO>();
		
		SqlSession conn =null;
		try { 
			conn = MyBatisFactory.getFactory().openSession();
			rlist = (ArrayList)conn.selectList("shopNameSpace.reply_select",sseq);
		} finally {
			conn.close();
		}
		
		return rlist;
	}
	
	
	
	
	//------------------------------------------------------
	/**
	 * shop_info에 등록된 최대 sseq 구하기
	 * @param PRMCONN
	 * @return
	 */
	public int selectNextSseq(SqlSession PRMCONN) {
		return PRMCONN.selectOne("shopNameSpace.selectNextSseq");
	}
	/**
	 * 맛집 등록			
	 * @param svo
	 * @param PRMCONN
	 * @return
	 */
	public int insertShopInfo(ShopVO svo , SqlSession PRMCONN) {
		return PRMCONN.insert("shopNameSpace.insertShopInfo",svo);
	}
	/**
	 * 맛집 별 사진(N개) 등록
	 * @param pvo
	 * @param PRMCONN
	 * @return
	 */
	public int	insertShopPic(ShopPicVO pvo, SqlSession PRMCONN) {
		return PRMCONN.insert("shopNameSpace.insertShopPic",pvo);
	}
	//------------------------------------------------------
	
	
	/**
	 * 맛집 사진 목록
	 * @param sseq
	 * @return
	 */
	public ArrayList<ShopPicVO> selectShopPic(int sseq) {
		ArrayList<ShopPicVO> list = new ArrayList<ShopPicVO>(); 
		SqlSession conn =null;
		try { 
			conn = MyBatisFactory.getFactory().openSession();
			list = (ArrayList)conn.selectList("shopNameSpace.selectShopPic",sseq);
		} finally {
			conn.close();
		}
		return list;
	}

	/**
	 * 맛집 상세정보
	 * @param sseq
	 * @return
	 */
	public ShopVO selectShopInfo(int sseq) {
		ShopVO svo = new ShopVO(); 
		SqlSession conn =null;
		try { 
			conn = MyBatisFactory.getFactory().openSession();
			svo = conn.selectOne("shopNameSpace.selectShopInfo",sseq);
		} finally {
			conn.close();
		}
		return svo;
	}

	
	/**
	 * 상위 N개 맛집 목록
	 * @param vo
	 * @return
	 */
	public ArrayList<ShopVO> selectTopN(ShopVO vo) {
		ArrayList<ShopVO> list = new ArrayList<ShopVO>(); 
		SqlSession conn =null;
		try { 
			conn = MyBatisFactory.getFactory().openSession();
			list = (ArrayList)conn.selectList("shopNameSpace.selectTopN",vo);
		} finally {
			conn.close();
		}
		return list;
	}
	
	/**
	 * 맛집 전체 목록 selectAll
	 * @param vo
	 * @return
	 */
	public ArrayList<ShopVO> select(ShopVO vo) {
		ArrayList<ShopVO> list = new ArrayList<ShopVO>(); 
		SqlSession conn =null;
		try { 
			conn = MyBatisFactory.getFactory().openSession();
			list = (ArrayList)conn.selectList("shopNameSpace.selectAll",vo);
		} finally {
			conn.close();
		}
		return list;
	}
	
	

}
