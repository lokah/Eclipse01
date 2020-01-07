package com.md.dao;

import com.md.db.JDBCTemplate;
import com.md.dto.MDDto;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
//import static com.md.db.JDBCTemplate.*;
public class MDDao extends JDBCTemplate {

	public List<MDDto> selectList(){
		
	
	Connection con = getConnection();
	
	Statement stmt = null;
	ResultSet rs = null;
	String sql = " SELECT SEQ, WRITER, TITLE, CONTENT, REGDATE " + " FROM MDBOARD " + " ORDER BY SEQ DESC ";
	List<MDDto> list = new ArrayList<MDDto>();

	try {
		stmt = con.createStatement();
		System.out.println("3. query 준비:" + sql);

		// 4. 실행 및 리턴
		rs = stmt.executeQuery(sql);
		while (rs.next()) {
			MDDto dto = new MDDto();
			dto.setSeq(rs.getInt(1));
			dto.setWriter(rs.getString(2));
			dto.setTitle(rs.getString(3));
			dto.setContent(rs.getString(4));
			dto.setRegdate(rs.getDate(5));
			list.add(dto);
		}
		System.out.println("4.실행 및 리턴");
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} finally {
		// 5.종료
		//JDBCTemplate 상속받을 경우
		//예외처리 없이
		//그냥 close(rs); close(stmt);
		//close(con)
		
			close(rs);
			close(stmt);
			close(con);
		
	}

	return list;
}
	
	public int multiDelete(String[] seq) {
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;
		String sql = " DELETE FROM MDBOARD WHERE SEQ = ? ";
		int[] cnt = null;
		
		try {
			pstm = con.prepareStatement(sql);
			
			for(int i=0; i<seq.length;i++) {
				
				pstm.setString(1, seq[i]);
				pstm.addBatch();
				//메모리에 적재 후
				//executeBatch()메소드가 호출될 때 한번에 실행
				System.out.println("삭제할 번호: "+ seq[i]);
			}
			
			System.out.println("3. query 준비: "+ sql);
			cnt = pstm.executeBatch();//메모리에 있던 쿼리를 한번에 실행. int[]로 리턴
			System.out.println("실행 및 리턴");
			//[-2,-2,-3,....]
			for(int i=0; i<cnt.length;i++) {
				//-2: 성공
				//-3: 실패
				if(cnt[i]==-2) {
					
					res++;
				}
			}
			
			if(res==seq.length) {
				commit(con);
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			
			close(pstm);
			close(con);
			System.out.println("5.db 종료");
		}
		
		
		return res;
	}
	
	public MDDto selectOne(int seq) {

		Connection con = getConnection();

		
		Statement stmt = null;
		ResultSet rs = null;
		String sql = " SELECT SEQ, WRITER, TITLE, CONTENT, REGDATE " + " FROM MDBOARD " + " WHERE SEQ= " + seq;

		MDDto res = null;

		try {
			stmt = con.createStatement();
			//pstm = con.prepareStatement(sql);
			//pstm.setInt(1,myno);
			//rs = pstm.executeQuery();
			rs = stmt.executeQuery(sql);
			

			while (rs.next()) {
				res = new MDDto(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getDate(5));
				//res = new MyDto();
				//res.setMyno(rs.getInt(1));
				//res.setMyname(rs.getString(2));
				//res.setMytitle(rs.getString(3));
				//res.setMycontent(rs.getString(4));
				//res.setMydate(rs.getDate(5));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			
				close(rs);
				close(stmt);
				close(con);
		

		
	}
		return res;
	}
	
	public int insert(MDDto dto) {
		
		// 3.query 준비
		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;

		String sql = " INSERT INTO MDBOARD " + " VALUES(MDBOARDSEQ.NEXTVAL, ?, ?, ?,SYSDATE) ";

		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, dto.getWriter());
			pstm.setString(2, dto.getTitle());
			pstm.setString(3, dto.getContent());
			System.out.println("3. query 준비 :" + sql);

			res = pstm.executeUpdate();
			System.out.println("4.실행 및 리턴");
			//jdbc 상속받을 경우
			if(res>0) {
			commit(con);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			
				//jdbc 상속받을 경우 try~catch 예외처리 없이...
				//그냥 close(pstm);
				//close(con);
				close(pstm);
				close(con);
				System.out.println("5.db종료");
			

		}

		return res;
	}
	
	public int update(MDDto dto) {

		Connection con = getConnection();
		PreparedStatement pstm = null;
		int res = 0;

		String sql = " UPDATE MDBOARD SET " + " TITLE = ?, CONTENT= ? " + " WHERE SEQ = ? ";

		try {
			pstm = con.prepareStatement(sql);
			pstm.setString(1, dto.getTitle());
			pstm.setString(2, dto.getContent());
			pstm.setInt(3, dto.getSeq());
			

			res = pstm.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {

			
				close(pstm);
				close(con);

			
		}

		return res;
	}
	
	public int delete(int seq) {
		
		Connection con = getConnection();
		
		PreparedStatement pstm = null;
		int res = 0;
		String sql = " DELETE FROM MDBOARD WHERE SEQ = ? ";
		try {
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, seq);
			res = pstm.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			
				close(pstm);
				close(con);
			
			
		}
		
		return res;
	}
	
	

	
}
