package com.takemeaway.model;

import com.jfinal.plugin.activerecord.Model;
import com.takemeaway.util.MD5Util;

import java.util.Date;

/**
 * 用户
 * Created by longjianlin on 14/12/19.
 */
public class User extends Model<User> {
    public static final User dao = new User();

    /**
     * 用户注册
     *
     * @param mobile 手机号，登录账号
     * @param pwd    登录密码
     * @return
     */
    public boolean register(final String mobile, final String pwd) {
        return new User().set("mobile", mobile).set("pwd", MD5Util.getMD5Str(pwd)).set("createtime", new Date()).save();
    }

    /**
     * 用户登录
     *
     * @param mobile 手机号，登录账号
     * @param pwd    登录密码
     * @return
     */
    public boolean login(final String mobile, final String pwd) {
        User user = dao.findFirst("select u.id,u.mobile from tma_user u where u.mobile=? and u.pwd=?", mobile, pwd);
        return user == null ? false : true;
    }

    /**
     * 根据用户编号检测用户是否存在
     *
     * @param user_id
     * @return true:存在 false:不存在
     */
    public boolean existsUser(final long user_id) {
        User user = dao.findFirst("select u.id,u.mobile from tma_user u where u.id=?", user_id);
        return user == null ? false : true;
    }
}
