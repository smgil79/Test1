import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DB {
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

	public DB() {
		String dburl = "jdbc:oracle:thin:@192.168.10.100:1521:orcl";
		String username = "smg";
		String password = "1234";

		try {
			con = DriverManager.getConnection(dburl, username, password);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("연결실패");
		}
		
		this.init();
	}

	private void init() {			
		try {
			String dr0 = "DROP TABLE apple";
			ps = con.prepareStatement(dr0);
			ps.executeUpdate();
			dr0 = "PURGE RECYCLEBIN";		
			ps = con.prepareStatement(dr0);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("테이블 만들기 실패");
		}
	}
	
	public void createTable(String tabName) {
		try {
			String dr1 = "CREATE TABLE "+tabName+"(eid NUMBER)";		
			ps = con.prepareStatement(dr1);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	public void insert(int id) {	
		try {
			
			String dr2 = "INSERT INTO apple VALUES(" + id + ")";
			ps = con.prepareStatement(dr2);
			ps.executeUpdate();	
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}

	public void printAll() {
		try {
			String dr3 = "SELECT * FROM apple";
			ps = con.prepareStatement(dr3);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				int id = rs.getInt("eid");
				
				System.out.println(id);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
