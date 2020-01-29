package com.kanchan.foodmandu;

import com.kanchan.foodmandu.bll.LoginBLL;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class LoginTest {

    @Test
    public void testLogin(){
        LoginBLL loginBLL=new LoginBLL();
        boolean result=loginBLL.checkUser("kanchan","kanchan");
        assertEquals(true,result);

    }
}
