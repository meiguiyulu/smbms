package dao.user;

import com.mysql.cj.util.StringUtils;
import dao.BaseDao;
import pojo.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author LYJ
 * @create 2021-07-11 18:44
 */
public class UserDaoImpl implements UserDao {
    //得到要登录的用户
    public User getLoginUser(Connection connection, String userCode, String userPassword) throws SQLException {
        PreparedStatement pstm = null;
        ResultSet rs = null;
        User user = null;

        if (connection!=null){
            String sql = "select * from smbms.smbms_user where userCode=?";
            Object[] params = {userCode};
            //System.out.println(userPassword);
            rs = BaseDao.execute(connection,sql,params,rs,pstm);
            if (rs.next()){
                user = new User();
                user.setId(rs.getInt("id"));
                user.setUserCode(rs.getString("userCode"));
                user.setUserName(rs.getString("userName"));
                user.setUserPassword(rs.getString("userPassword"));
                user.setGender(rs.getInt("gender"));
                user.setBirthday(rs.getDate("birthday"));
                user.setPhone(rs.getString("phone"));
                user.setAddress(rs.getString("address"));
                user.setUserRole(rs.getInt("userRole"));
                user.setCreatedBy(rs.getInt("createdBy"));
                user.setCreationDate(rs.getTimestamp("creationDate"));
                user.setModifyBy(rs.getInt("modifyBy"));
                user.setModifyDate(rs.getTimestamp("modifyDate"));
            }
            BaseDao.closeResource(null,pstm,rs);
            if (!user.getUserPassword().equals(userPassword))
                user=null;
        }
        return user;
    }

    @Override
    public int updatePwd(Connection connection, int id, String userPassword) throws SQLException {

        int rows = 0;
        PreparedStatement statement = null;
        if (connection != null){
            String sql = "update smbms.smbms_user set userPassword = ? where id = ?";
            Object[] params = {userPassword, id};
            rows  = BaseDao.execute(connection, sql, params, statement);
        }
        BaseDao.closeResource(null, statement, null);
        return rows;
    }

    @Override
    //根据用户输入的名字或者角色id来查询计算用户数量
    public int getUserCount(Connection connection, String userName, int userRole) throws Exception {
        int count = 0;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        if (connection != null){
            StringBuilder builder = new StringBuilder();
            builder.append("select count(1) as number\n" +
                    "from smbms_user as u, smbms_role as r\n" +
                    "where u.userRole=r.id");
            ArrayList<Object> list = new ArrayList<Object>();//存放可能会放进sql里的参数，就是用来替代?的params

            if (!StringUtils.isNullOrEmpty(userName)){
                builder.append(" and u.userName like ?");
                list.add("%" + userName + "%" );
            }
            if (userRole > 0){
                builder.append(" and u.userRole = ?");
                list.add(userRole);
            }
            Object[] params = list.toArray();
            System.out.println("getUserCount SQL---->" + builder.toString());
            resultSet = BaseDao.execute(connection, builder.toString(), params, resultSet, statement);
            if (resultSet.next()){
                count = resultSet.getInt("number");
            }
            BaseDao.closeResource(null, statement, resultSet);
        }
        return count;
    }

    @Override
    public List<User> getUserList(Connection connection, String userName, int userRole, int currentPageNo, int pageSize) throws Exception {
        List<User> userList = new ArrayList<>();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        if (connection != null){
            StringBuilder builder = new StringBuilder();
            builder.append("select u.*, r.roleName as userRoleName\n" +
                    "from smbms_user as u, smbms_role as r\n" +
                    "where u.userRole=r.id");
            ArrayList<Object> list = new ArrayList<Object>();//存放可能会放进sql里的参数，就是用来替代?的params

            if (!StringUtils.isNullOrEmpty(userName)){
                builder.append(" and u.userName like ?");
                list.add("%" + userName + "%" );
            }
            if (userRole > 0){
                builder.append(" and u.userRole = ?");
                list.add(userRole);
            }

            // 实现分页展示
            builder.append(" order by creationDate DESC limit ?, ?");
            currentPageNo = (currentPageNo - 1) * pageSize;
            list.add(currentPageNo);
            list.add(pageSize);

            Object[] params = list.toArray();
//            System.out.println("getUserList SQL---->" + builder.toString());
            resultSet = BaseDao.execute(connection, builder.toString(), params, resultSet, statement);
            while (resultSet.next()){
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setUserCode(resultSet.getString("userCode"));
                user.setUserName(resultSet.getString("userName"));
                user.setUserPassword(resultSet.getString("userPassword"));
                user.setGender(resultSet.getInt("gender"));
                user.setBirthday(resultSet.getDate("birthday"));
                user.setPhone(resultSet.getString("phone"));
                user.setAddress(resultSet.getString("address"));
                user.setUserRole(resultSet.getInt("userRole"));
                user.setCreatedBy(resultSet.getInt("createdBy"));
                user.setCreationDate(resultSet.getTimestamp("creationDate"));
                user.setModifyBy(resultSet.getInt("modifyBy"));
                user.setModifyDate(resultSet.getTimestamp("modifyDate"));
                user.setUserRoleName(resultSet.getString("userRoleName"));
                userList.add(user);
            }

            BaseDao.closeResource(null, statement, resultSet);
        }
        return userList;
    }

    // 添加用户
    @Override
    public int add(Connection connection, User user) throws SQLException {

        PreparedStatement statement = null;
        int ans = 0;

        if (connection != null){
            String sql = "insert into " +
                    "smbms_user(userCode, userName, userPassword, gender, birthday, phone, address, userRole, createdBy, creationDate)" +
                    "values(?,?,?,?,?,?,?,?,?,?)";
            Object[] params = {user.getUserCode(), user.getUserName(), user.getUserPassword(), user.getGender(),
            user.getBirthday(), user.getPhone(), user.getAddress(), user.getUserRole(), user.getCreatedBy(),user.getCreationDate()};
           ans =  BaseDao.execute(connection, sql, params, statement);
           BaseDao.closeResource(null, statement, null);
        }
        return ans;
    }

    // 删除用户
    @Override
    public int deleteUserById(Connection connection, int id) throws SQLException {
        PreparedStatement statement = null;
        int ans = 0;

        if (connection != null){
            String sql = "delete from smbms_user where id = ?";
            Object[] params = {id};
            ans = BaseDao.execute(connection, sql, params, statement);
            BaseDao.closeResource(null, statement, null);
        }
        return ans;
    }

    // 查找某个用户
    @Override
    public User selectUserById(Connection connection, int id) throws SQLException {
        ResultSet resultSet = null;
        PreparedStatement statement = null;
        User user = new User();
        if (connection != null){
            String sql = "select u.*, r.roleName as userRoleName\n" +
                    "from smbms_user as u, smbms_role as r\n" +
                    "where u.userRole=r.id and u.id = ?";
            Object[] params = {id};
            resultSet = BaseDao.execute(connection, sql, params, resultSet, statement);
            if (resultSet.next()){
                user.setId(resultSet.getInt("id"));
                user.setUserCode(resultSet.getString("userCode"));
                user.setUserName(resultSet.getString("userName"));
                user.setUserPassword(resultSet.getString("userPassword"));
                user.setGender(resultSet.getInt("gender"));
                user.setBirthday(resultSet.getDate("birthday"));
                user.setPhone(resultSet.getString("phone"));
                user.setAddress(resultSet.getString("address"));
                user.setUserRole(resultSet.getInt("userRole"));
                user.setCreatedBy(resultSet.getInt("createdBy"));
                user.setCreationDate(resultSet.getTimestamp("creationDate"));
                user.setModifyBy(resultSet.getInt("modifyBy"));
                user.setModifyDate(resultSet.getTimestamp("modifyDate"));
                user.setUserRoleName(resultSet.getString("userRoleName"));
            }
            BaseDao.closeResource(null, statement, resultSet);
        }
        return user;
    }

    // 修改用户信息
    @Override
    public int modify(Connection connection, User user) throws SQLException {

        int num = 0;
        PreparedStatement statement = null;

        if (connection != null){
            String sql = "update smbms_user set userName=?," +
                    "gender=?,birthday=?,phone=?,address=?,userRole=?,modifyBy=?,modifyDate=? " +
                    "where id = ? ";

            Object[] params = {user.getUserName(), user.getGender(), user.getBirthday(), user.getPhone(),
            user.getAddress(), user.getUserRole(), user.getModifyBy(), user.getModifyDate(),
            user.getId()};

            num = BaseDao.execute(connection, sql, params, statement);
            BaseDao.closeResource(null, statement, null);
        }

        return num;
    }
}

