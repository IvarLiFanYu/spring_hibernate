package com.lfy.dao.impl;

import com.lfy.dao.UserDao;
import com.lfy.pojo.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private HibernateTemplate hibernateTemplate;

    @Override
    public void saveUser(User user) {
        hibernateTemplate.save(user);
    }

    @Override
    public void updateUser(User user) {
        hibernateTemplate.update(user);
    }

    @Override
    public void deleteUser(User user) {
        hibernateTemplate.delete(user);
    }

    @Override
    public User findById(Integer id) {
        return hibernateTemplate.get(User.class,id);
    }

    @Override
    public List<User> findUserByName(String name) {

        // 该方法返回的 session 必须在事务中使用含有此方法的方法(即在使用 findUserById 的方法需要加事务),
        // 且只能处理唯一的事务, 当事务提交或者回滚后session 自动失效
        //Session session = this.hibernateTemplate.getSessionFactory().getCurrentSession();

        //每次都会打开一个新的 session, 若连续 openSession, 则获得不同的 session 对象,
        //使用完毕后,需要手动的调用 close 方法关闭 session.
        Session session = this.hibernateTemplate.getSessionFactory().openSession();
        Query query = session.createQuery("from User where name = :name");
        Query queryTemp = query.setString("name",name);

        return queryTemp.list();
    }

    @Override
    public List<User> findUserByNameBySQL(String name) {
        Session session = this.hibernateTemplate.getSessionFactory().openSession();
        Query query = session.createSQLQuery
                ("select * from  t_user where name = ?").addEntity(User.class)
                                                                    .setString(0,name);
        return query.list();
    }

    @Override
    public List<User> findUserByNameByQBC(String name) {
        Session  session = hibernateTemplate.getSessionFactory().getCurrentSession();
        // sql : select * from user
        Criteria criteria = session.createCriteria(User.class);
        //设置 where 条件
        criteria.add(Restrictions.eq("name", name));
        //执行查询
        List list = criteria.list();
        return list;
    }
}
