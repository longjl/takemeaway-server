package com.takemeaway.model;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Model;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.plugin.activerecord.Record;

import java.util.List;

/**
 * 用户属性
 * Created by longjianlin on 14/12/19.
 */
public class UserAttribute extends Model<UserAttribute> {
    public static final UserAttribute dao = new UserAttribute();

    /**
     * 获取带我走用户信息
     *
     * @param pageNumber 当前页数
     * @return
     */
    public Page<Record> getTMAUsers(final int pageNumber) {
        return Db.paginate(pageNumber, 20, "select ua.avatar,ua.nickname,u.mobile", "from tma_user_attribute ua left join tma_user u on ua.user_id=u.id where ua.authentication=1");
    }

    /**
     * 根据用户编号修改字段内容
     *
     * @param user_id    用户编号
     * @param filedKey   字段
     * @param filedValue 字段值
     * @return
     */
    public boolean updateFiled(final long user_id, final String filedKey, final String filedValue) {
        //不存在此用户
        if (!User.dao.existsUser(user_id)) return false;

        if (!existsUserAttribute(user_id)) {
            //添加一条用户属性
            return new UserAttribute().set("user_id", user_id).set(filedKey, filedValue).save();
        } else {
            StringBuffer updateFiledSql = new StringBuffer("update from tma_user_attribute SET ");
            updateFiledSql.append(filedKey + "=? ");
            updateFiledSql.append("where tua.user_id=? ");
            //修改用户属性
            List<UserAttribute> list = new UserAttribute().find(updateFiledSql.toString(), filedValue, user_id);
            if (list != null && list.size() > 0) {
                return true;
            }
        }
        return false;
    }


    /**
     * 根据用户编号检测用户属性是否存在
     *
     * @param user_id
     * @return true:存在 false:不存在
     */
    public boolean existsUserAttribute(final long user_id) {
        UserAttribute ua = dao.findFirst("select tua.id,tua.user_id from tma_user_attribute tua where tua.user_id=?", user_id);
        return ua == null ? false : true;
    }
}
