package com.my.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.my.dto.MyDto;

public class MyDao {

	public List<MyDto> selectList(){
		
		//1.driver 연결
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("1.driver 연결");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("[error]:1");
			e.printStackTrace();
		}
		
		//2.계정 연결
		String url="jdbc:oracle:thin:@localhost:1521:orcl";
		String user = "scott";
		String password ="tiger";
		
		Connection con = null;
		try {
			con = DriverManager.getConnection(url,user,password);
			System.out.println("2. 계정연결");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("[error]:2");
			e.printStackTrace();
		}
		
		//3. query 준비
		Statement stmt = null;
		ResultSet rs = null;
		String sql = " SELECT MYNO,MYNAME,MYTITLE,MYCONTENT,MYDATE " + " FROM MYBOARD " + " ORDER BY MYNO DESC ";
		List<MyDto> list = new ArrayList<MyDto>();
		
		try {
			stmt = con.createStatement();
			System.out.println("3. query 준비:"+sql);
			
			//4. 실행 및 리턴
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				MyDto dto = new MyDto();
				dto.setMyno(rs.getInt(1));
				dto.setMyname(rs.getString("MYNAME"));
				dto.setMytitle(rs.getString(3));
				dto.setMycontent(rs.getString(4));
				dto.setMydate(rs.getDate(5));
				list.add(dto);
			}
			System.out.println("4.실행 및 리턴");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//5.종료
			
			try {
				rs.close();
				stmt.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("5.db 종료");
		}
		
		
		return list;
	}
	public MyDto selectOne(int myno) {
		
		//1.driver 연결
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");
					System.out.println("1.driver 연결");
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					System.out.println("[error]:1");
					e.printStackTrace();
				}
				
				//2.계정 연결
				String url="jdbc:oracle:thin:@localhost:1521:orcl";
				String user = "scott";
				String password ="tiger";
				
				Connection con = null;
				try {
					con = DriverManager.getConnection(url,user,password);
					System.out.println("2. 계정연결");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					System.out.println("[error]:2");
					e.printStackTrace();
				}
				Statement stmt = null;
				ResultSet rs = null;
				String sql = " SELECT MYNO, MYNAME, MYTITLE, MYCONTENT, MYDATE " + 
				" FROM MYBOARD " + " WHERE MYNO= " + myno;
				
				MyDto res = null;
				
				try {
					stmt = con.createStatement();
					rs = stmt.executeQuery(sql);

					while(rs.next()) {
						res = new MyDto(rs.getInt(1),
								rs.getString(2),
								rs.getString(3),
								rs.getString(4),
								rs.getDate(5));
					
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally {
					
					try {
						rs.close();
						stmt.close();
						con.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				
		
		
		return res;
	}
	
	public int insert(MyDto dto) {
		//1.driver 연결
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("1.driver 연결");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("[error]:1");
			e.printStackTrace();
		}
		
		//2.계정 연결
		String url="jdbc:oracle:thin:@localhost:1521:orcl";
		String user = "scott";
		String password ="tiger";
		
		Connection con = null;
		
		try {
			con = DriverManager.getConnection(url,user,password);
			System.out.println("2. 계정연결");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("[error]:2");
			e.printStackTrace();
		}
		//3.query 준비
		
		PreparedStatement pstm = null;
		int res=0;
		
		String sql =" INSERT INTO MYBOARD " + " VALUES(MYSEQ.NEXTVAL, ?, ?, ?,SYSDATE) ";
		
		
		
	try {
		pstm = con.prepareStatement(sql);
		pstm.setString(1, dto.getMyname());
		pstm.setString(2, dto.getMytitle());
		pstm.setString(3, dto.getMycontent());
		System.out.println("3. query 준비 :" +sql);
		
		res = pstm.executeUpdate();
		System.out.println("4.실행 및 리턴");
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally {
		
		try {
			pstm.close();
			con.close();
			System.out.println("5.db종료");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

		
		//4.실행 및 리턴
	
	
	
		//5.db 종료
		
		return res;
	}
	
	public int update(MyDto dto) {
		
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				System.out.println("1. driver 연결");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				System.out.println("[error]: 1");
				e.printStackTrace();
			}
			
			String url = "jdbc:oracle:thin:@localhost:1521:orcl";
			String user = "scott";
			String password = "tiger";
			
			Connection con = null;
			
			try {
				con = DriverManager.getConnection(url,user,password);
				System.out.println("2. 계정연결");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("[error]: 2");
				e.printStackTrace();
			}
			
			PreparedStatement pstm = null;
			int res = 0;
			
			String sql = " UPDATE MYBOARD SET MYNAME = ?, MYTITLE = ? " + " WHERE MYNO = ?";
			
			try {
				pstm = con.prepareStatement(sql);
				pstm.setString(1, dto.getMyname());
				pstm.setString(2, dto.getMytitle());
				pstm.setInt(3, dto.getMyno());
				System.out.println("3. query 준비 :"+ sql);
				
				res = pstm.executeUpdate();
				System.out.println("4. 실행 및 리턴");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("[error]: 3");
				e.printStackTrace();
			}finally {
				
				try {
					pstm.close();
					con.close();
					System.out.println("5. db 종료");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					System.out.println("[error] : 4");
					e.printStackTrace();
				}
			}
			
			
			return res;
	}
	public int delete(int myno) {
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("1.driver 연결");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("[error]:1");
			e.printStackTrace();
		}
		
		//2.계정 연결
		String url="jdbc:oracle:thin:@localhost:1521:orcl";
		String user = "scott";
		String password ="tiger";
		
		Connection con = null;
		try {
			con = DriverManager.getConnection(url,user,password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		PreparedStatement pstm = null;
		int res = 0;
		String sql = " DELETE FROM MYBOARD WHERE MYNO = ? ";
		try {
			pstm = con.prepareStatement(sql);
			pstm.setInt(1, myno);
			res = pstm.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				pstm.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		return res;
	}
	
	
	

}