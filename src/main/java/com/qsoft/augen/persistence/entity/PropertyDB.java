package com.qsoft.augen.persistence.entity;

/**
 * User: Anhnt
 * Date: 8/27/13
 * Time: 11:54 PM
 */
public class PropertyDB
{
    private String Driver;
    private String Url;
    private String Username;
    private String Password;

    public PropertyDB()
    {
    }

    public PropertyDB(String driver, String url, String username, String password)
    {
        Driver = driver;
        Url = url;
        Username = username;
        Password = password;
    }

    public String getDriver()
    {
        return Driver;
    }

    public void setDriver(String driver)
    {
        Driver = driver;
    }

    public String getUrl()
    {
        return Url;
    }

    public void setUrl(String url)
    {
        Url = url;
    }

    public String getUsername()
    {
        return Username;
    }

    public void setUsername(String username)
    {
        Username = username;
    }

    public String getPassword()
    {
        return Password;
    }

    public void setPassword(String password)
    {
        Password = password;
    }
}
