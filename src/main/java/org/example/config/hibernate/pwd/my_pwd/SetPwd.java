package org.example.config.hibernate.pwd.my_pwd;


import org.hibernate.cfg.Configuration;

public class SetPwd {
    public static Configuration getConfigurationWithProperty(Configuration configuration){
        return configuration.setProperty("hibernate.conection.password", MyPwd.getPwd());
    }
}
