package by.netcracker.test.vad.services;

import by.netcracker.test.vad.customers.client.CustomerService;
import by.netcracker.test.vad.customers.shared.Customer;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
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

@RunWith(SpringJUnit4ClassRunner.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@ContextConfiguration(locations = {"classpath:spring/service-tx-jpa.xml"})
public class CustomerServiceImplTest implements ApplicationContextAware {

    private static EntityManagerFactoryInfo emf;

    @Autowired
    private CustomerService customerService;

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void findOne() throws Exception {
        Customer actual = customerService.findOne(2);
        String expected = "None";
        Assert.assertEquals("CustomerService didn't found entity", expected, actual.getFirstName());
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        emf = applicationContext.getBean("emf", EntityManagerFactoryInfo.class);
    }
}