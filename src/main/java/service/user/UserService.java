package service.user;

import pojo.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author LYJ
 * @create 2021-07-11 18:46
 */
public interface UserService {
    //用户登录
    public User login(String userCode, String password);

    // 根据id修改密码
    public boolean updatePwd(int id, String userPassword);

    /**
     * 根据条件查询用户表记录数
     * @param queryUserName
     * @param queryUserRole
     * @return
     */
    public int getUserCount(String queryUserName, int queryUserRole);

    /**
     * 根据条件查询用户列表
     * @param queryUserName
     * @param queryUserRole
     * @return
     */
    public List<User> getUserList(String queryUserName, int queryUserRole, int currentPageNo, int pageSize);

    /**
     * 添加用户
     * @param user
     * @return
     */
    public boolean add(User user);

    /**
     * 删除用户
     * @param id
     * @return
     */
    public boolean deleteUserById(int id);

    //通过userId查看当前用户信息
    public User selectUserById(int id);

    //修改用户信息
    public boolean modify(User user);

}
