package service;

import com.sly.springboot.entities.Customer;
import com.sly.springboot.service.CustomerService;
import com.sly.springboot.service.impl.CustomerServiceImpl;
import org.junit.Test;

import java.util.List;

/**
 * TODO:
 *
 * @author leyuan
 * @date 2021/7/22 14:01
 */
public class CustomerServiceTest {
    private CustomerService customerService = new CustomerServiceImpl();

    @Test
    public void listCustomerTest() {
        List<Customer> customers = customerService.listCustomer();
        for (Customer customer : customers) {
            System.out.println(customer);
        }
    }
}
