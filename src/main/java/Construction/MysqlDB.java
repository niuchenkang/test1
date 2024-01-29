package Construction;

import java.sql.*;

public class MysqlDB {

    private Connection conn= null;
    private Statement stmt = null;
    private ResultSet rs =null;
    public Connection ConnDB(){
        conn=null;
        try{
            String url="jdbc:mysql://localhost:3306/userLogin?Unicode=true&character=UTF-8";
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            conn= DriverManager.getConnection(url,"root","123.com");

            return conn;
        }catch (Exception fe) {
            System.err.println("ConnDB():"+fe.getMessage());
            return null;
        }

    }
    public Statement CreatStat(){
        stmt=null;
        try {
            if (conn==null){
                conn=this.ConnDB();
            }
            stmt=conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
            return stmt;
        }catch (Exception fe){
            System.err.println("CreatStat():"+fe.getMessage());
            return null;
        }
    }
    public PreparedStatement prepare(String sql) {
        PreparedStatement pstmt = null;
        try {
            if (conn == null){
                conn = this.ConnDB();
            }
            pstmt = conn.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return pstmt;
    }

    public ResultSet getResult(String sql){
        rs=null;
        try {
            stmt=this.CreatStat();
            rs=stmt.executeQuery(sql);
            return rs;
        }catch (Exception ex){
            System.err.println("getResult"+ex.getMessage());
            return null;
        }
    }
    public int executeSql(String sql){
        int RowCount;
        try{
            stmt=this.CreatStat();
            RowCount=stmt.executeUpdate(sql);
            if (!conn.getAutoCommit()){
                conn.commit();
            }
            return RowCount;
        }catch (Exception e){
            System.err.println("executeSql"+e.toString());
            return 0;

        }
    }
    public boolean exeUpdate(String sql) {
        try {
            stmt = this.CreatStat();
            stmt.executeUpdate(sql);
            if (!conn.getAutoCommit()) {
                conn.commit();
            }
            return true;
        } catch (Exception e) {
            System.err.println("executeUpdate: " + e.toString());
            return false;
        }
    }
    public void Release() throws SQLException {
        if (rs != null) {
            rs.close();
            rs = null;
        }
        if (stmt != null) {
            stmt.close();
            stmt = null;
        }
        if (conn != null) {
            conn.close();
            conn = null;
        }
    }
}
