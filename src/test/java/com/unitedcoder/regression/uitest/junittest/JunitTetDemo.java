package com.unitedcoder.regression.uitest.junittest;

import org.junit.*;
import org.junit.rules.TestName;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class JunitTetDemo {
    @BeforeClass
    public static void setup(){
        System.out.println("Before class runs only once");
    }
    @Rule
    public TestName name=new TestName();
    @Before
    public  void testBegin(){
        System.out.println("Before annotaion will execute before every test ");
        System.out.println(name.getMethodName()+" started");
    }
    // @Ignore
    @Test
    public void edit(){
        System.out.println("Square root test");
        Assert.assertTrue(Math.sqrt(64)==8);
    }
    @Test
    public void delete(){
        System.out.println("Compare Two String");
        String s1="Selenium";
        String s2="selenium";
        Assert.assertTrue(s1.equals(s2));
    }
    @Test
    public void addCustomer(){
        System.out.println("Compare Two Numbers");
        Assert.assertEquals(10,10);
    }
    @After
    public void testEnd(){
        System.out.println("After annotation will execute after every test");
    }
    @AfterClass
    public static void teardown(){
        System.out.println("After class will execute once after test");
    }
}

