<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:tx="http://www.springframework.org/schema/tx"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
                http://www.springframework.org/schema/beans/spring-beans.xsd
                http://www.springframework.org/schema/context
                http://www.springframework.org/schema/context/spring-context.xsd
                http://www.springframework.org/schema/tx
                http://www.springframework.org/schema/tx/spring-tx.xsd">

    <context:annotation-config />
    <context:spring-configured/>
    <context:component-scan base-package="$package_scan$"/>

    <bean id="entityManager" class="org.springframework.orm.jpa.support.SharedEntityManagerBean">
        <property name="entityManagerFactory" ref="entityManagerFactory"/>
    </bean>

    <bean id="entityManagerFactory" class="org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean">
        <property name="packagesToScan" value="$package_scan$"/>
        <property name="dataSource" ref="dataSource"/>
        <property name="persistenceUnitName" value="NewPersistenceUnit"/>
        <property name="persistenceXmlLocation" value="META-INF/persistence.xml"/>
        <property name="jpaVendorAdapter">
            <bean class="org.springframework.orm.jpa.vendor.EclipseLinkJpaVendorAdapter ">
            </bean>
        </property>
    </bean>

    <bean id="dataSource" class="org.apache.commons.dbcp.BasicDataSource" destroy-method="close">
        <property name="driverClassName" value="$driver$"/>
        <property name="url" value="$url$"/>
        <property name="username" value="$username$"/>
        <property name="password" value="$password$"/>
    </bean>

    <tx:annotation-driven transaction-manager="transactionManager"/>
    <bean id="transactionManager" class="org.springframework.orm.jpa.JpaTransactionManager"/>

</beans>