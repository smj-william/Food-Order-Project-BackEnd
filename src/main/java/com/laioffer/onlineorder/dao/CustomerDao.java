package com.laioffer.onlineorder.dao;

import com.laioffer.onlineorder.entity.Authorities;
import com.laioffer.onlineorder.entity.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerDao {  // data access object 通过hibernate交互，增删改查（session from session factory）

    @Autowired
    private SessionFactory sessionFactory; //创建的时候自动连接数据库了，mapping。然后用在open s

    public void signUp(Customer customer) {
        Session session = null; //通过Session 的method对数据库操作
        Authorities authorities = new Authorities();
        authorities.setEmail(customer.getEmail());
        authorities.setAuthorities("ROLE_USER"); // ROLE_USER is 随意set

        try{
            session = sessionFactory.openSession(); //创建一个具体的 session 的instance
            //增删改查开始
            session.beginTransaction();//start
            session.save(authorities);
            session.save(customer);
            session.getTransaction().commit(); //done

        } catch (Exception ex){
            ex.printStackTrace(); //本地打出在console里
            if(session != null) session.getTransaction().rollback();
        } finally {
            if(session != null){
                session.close();
            }
        }
    }

    public Customer getCustomer(String email) {
        Customer customer = null;

        //有一种写法叫做try with resource， 可以看看
        //正常写法
        Session session = null;
        try{
            session = sessionFactory.openSession();
            customer = session.get(Customer.class, email);
        } catch (Exception ex){
            ex.printStackTrace();
        } finally {
            if(session != null) session.close();
        }
        return customer;
    }
}

