package org.example.config.hibernate.my_pwd;

public class SetPwd {
    public static Configuration getConfigurationWithPWDProperty(Configuration configuration){
        return configuration.setProperty("123", MyPwd.getPwd());
    }
}
