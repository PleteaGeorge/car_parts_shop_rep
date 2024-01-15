package org.example.config.hibernate.pwd;


import org.example.config.hibernate.pwd.my_pwd.MyPwd;
import org.hibernate.cfg.Configuration;

public class SetPwd {
    public static Configuration getConfigurationWithPwdProperty(Configuration configuration){
        return configuration.setProperty("hibernate.connection.password", MyPwd.gewPwd());
    }
}
