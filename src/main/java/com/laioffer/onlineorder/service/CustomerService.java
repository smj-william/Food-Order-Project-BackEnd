package com.laioffer.onlineorder.service;

import com.laioffer.onlineorder.dao.CustomerDao;
import com.laioffer.onlineorder.entity.Cart;
import com.laioffer.onlineorder.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private final CustomerDao customerDao;

    @Autowired
    public CustomerService(CustomerDao customerDao) {
        this.customerDao= customerDao;
    }

    public void signUp(Customer customer) {
        //分配给每个customer一个cart，并且给enable
        Cart cart = new Cart();
        customer.setCart(cart);
        customer.setEnabled(true);

        customerDao.signUp(customer);
    }

    public Customer getCustomer(String email) {
        return customerDao.getCustomer(email);
    }
}
