package by.netcracker.test.vad.customers.server;

import by.netcracker.test.vad.customers.client.CustomerService;
import by.netcracker.test.vad.customers.shared.Customer;
import org.apache.commons.codec.language.Metaphone;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.orm.jpa.EntityManagerFactoryInfo;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@ContextConfiguration(locations = {"classpath:spring/service-tx-jpa.xml"})
public class CustomerServiceImplTest implements ApplicationContextAware {

    private static EntityManagerFactoryInfo emf;

    @Autowired
    private CustomerService customerService;

    @Test
    public void findOne() throws Exception {
        Customer actual = customerService.findOne(2);
        String expected = "Mary";
        Assert.assertEquals("CustomerService didn't found entity", expected, actual.getFirstName());
    }

    @Test
    public void findByName() throws Exception {

        Metaphone metaphone = new Metaphone();
        metaphone.setMaxCodeLen(30);
        String code = metaphone.encode("GWT");


        // Send a request to the server.
        List<Customer> result = customerService.findByName("GWT");

        result.forEach(item -> Assert.assertTrue("CustomerService found unexpected entity", item.getFirstNameMetaphone().contains(code)
                || item.getLastNameMetaphone().contains(code)));

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        emf = applicationContext.getBean("emf", EntityManagerFactoryInfo.class);
    }
}