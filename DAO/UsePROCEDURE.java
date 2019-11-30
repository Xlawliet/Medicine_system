package DAO;

import java.sql.*;


public class UsePROCEDURE {
    private Connection conn = null;
    private CallableStatement call = null;

    public boolean res=true;

    public DBconnection db=null;

    public void PRO_Link(String name,String password)
    {
        db=new DBconnection();
        conn=db.getLink_U_P(name,password);
    }

    public void getConn(Connection con)
    {
        conn=con;
    }

    public ResultSet Select_Table(String tablename) throws SQLException {
        String sql="SELECT * FROM MTB_"+tablename;
        ResultSet resultSet=null;
        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            resultSet = statement.executeQuery();
        }catch (Exception e)
        {
                res=false;
        }

        return resultSet;
    }

    public String[] PRO_checkValidity(String M_ID) {

        String sql = "{call MTB_PRO_CHECKVALIDITY(?,?,?,?)}";
        String[] rs=new String[3];

        try{
//得到一个连接
            //conn = DBconnection.getLink();
//通过连接创建出statment
            call = conn.prepareCall(sql);

//对于in参数，赋值，取出员工编号为7934的员工信息
            call.setString(1,M_ID);

            //对于out参数，申明
            call.registerOutParameter(2, oracle.jdbc.OracleTypes.VARCHAR);
            call.registerOutParameter(3, oracle.jdbc.OracleTypes.VARCHAR);
            call.registerOutParameter(4, oracle.jdbc.OracleTypes.DATE);
           // ResultSet resultSet = null;
            //执行调用
            call.execute();

            //取出结果
            System.out.println("药品代号为： "+ call.getString(2)+" ，药品名称为： "+call.getString(3)
            +" ,保质期到： "+call.getDate(4));
            System.out.println("完成");
            //            double sal = call.getDouble(3);
            rs[0]=call.getString(2);
            rs[1]=call.getString(3);
            rs[2]=call.getString(4);

        } catch (Exception e){
            res=false;
            e.printStackTrace();
        }
        return rs;
    }

    public void  PRO_addmedicine(String M_id,String M_name,String M_type,String M_spec,String M_validity,float M_price) {
        String sql = "{call MTB_PRO_ADDMEDICINE(?,?,?,?,?,?)}";
        try {
//得到一个连接
            //conn = DBconnection.getLink();
//通过连接创建出statment
            call = conn.prepareCall(sql);

//对于in参数，赋值，取出员工编号为7934的员工信息
            call.setString(1, M_id);
            call.setString(2, M_name);
            call.setString(3, M_type);
            call.setString(4, M_spec);
            call.setString(5, M_validity);
            call.setFloat(6, M_price);

            //执行调用
            call.execute();

            System.out.println("完成");

        } catch (SQLException e) {
            res=false;
            e.printStackTrace();
        }
    }

    public void PRO_delmedicine(String M_ID){

        String sql = "{call MTB_PRO_DLEMEDICINE(?)}";
        try{
//得到一个连接
            //conn = DBconnection.getLink();
//通过连接创建出statment
            call = conn.prepareCall(sql);

            call.setString(1,M_ID);

            //执行调用
            call.execute();

            System.out.println("完成");
        } catch (Exception e){
            res=false;
            e.printStackTrace();
        }

    }

    public void PRO_instorage(String M_IN_NUM,String HOUSE_ID,String M_ID,String E_ID,int M_IN_QUANTITY,String M_IN_DATE)
    {
        String sql = "{call MTB_PRO_ADDMEDICINE(?,?,?,?,?,?)}";
        try {
//得到一个连接
            //conn = DBconnection.getLink();
//通过连接创建出statment
            call = conn.prepareCall(sql);

//对于in参数，赋值，取出员工编号为7934的员工信息
            call.setString(1, M_IN_NUM);
            call.setString(2, HOUSE_ID);
            call.setString(3, M_ID);
            call.setString(4, E_ID);
            call.setInt(5, M_IN_QUANTITY);
            call.setString(6, M_IN_DATE);

            //执行调用
            call.execute();

            System.out.println("完成");

        } catch (SQLException e) {
            res=false;
            e.printStackTrace();
        }
    }
}
