package com.laioffer.onlineorder.dao;

import com.laioffer.onlineorder.entity.Cart;
import com.laioffer.onlineorder.entity.OrderItem;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

//为了check out时候模拟实现，就清空cart

@Repository
public class CartDao {

    @Autowired
    private SessionFactory sessionFactory;

    public void removeCartItem(int orderItemId) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            OrderItem orderItem = session.get(OrderItem.class, orderItemId);
            Cart cart = orderItem.getCart(); //先从Cart里面orderItemList删除，
            cart.getOrderItemList().remove(orderItem);

            session.beginTransaction();// 再删除database里面删除， 不然会被系统重写进去
            session.delete(orderItem);
            session.getTransaction().commit();

        } catch (Exception ex) {
            ex.printStackTrace();
            if (session != null) {
                session.getTransaction().rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }

    }

    public void removeAllCartItems(Cart cart) {
        for (OrderItem item : cart.getOrderItemList()) {
            removeCartItem(item.getId());
        }
    }
}

