package com.qsoft.augen.business.common;

import com.qsoft.augen.persistence.entity.MetaTable;
import com.qsoft.augen.persistence.entity.PropertyDB;
import org.antlr.stringtemplate.StringTemplate;
import org.antlr.stringtemplate.StringTemplateGroup;
import org.antlr.stringtemplate.language.DefaultTemplateLexer;

import java.util.Iterator;
import java.util.List;

public class StringTemplateService
{

    public final static String PATH_TEMPLATE_RESOURCE = "C://Users/Admin/Desktop/DoAn/AutoGen/src/main/resources/template/";
    public final static String PATH_OUT_PUT_ENTITY = "C://Users/Admin/Desktop/DoAn/ProjectAutoGen/src/main/java/com/qsoft/augen/persistence/entity/";
    public final static String PATH_OUT_PUT_INTERFACE_SERVICE = "C://Users/Admin/Desktop/DoAn/ProjectAutoGen/src/main/java/com/qsoft/augen/business/";
    public final static String PATH_OUT_PUT_SERVICE_IMLP = "C://Users/Admin/Desktop/DoAn/ProjectAutoGen/src/main/java/com/qsoft/augen/business/impl/";
    public final static String PATH_OUT_PUT_INTERFACE_DAO = "C://Users/Admin/Desktop/DoAn/ProjectAutoGen/src/main/java/com/qsoft/augen/persistence/dao/";
    public final static String PATH_OUT_PUT_DAO_IMLP = "C://Users/Admin/Desktop/DoAn/ProjectAutoGen/src/main/java/com/qsoft/augen/persistence/dao/impl/";

    public final static String PATH_OUT_PUT_POM = "C://Users/Admin/Desktop/DoAn/ProjectAutoGen/";
    public final static String PATH_OUT_PUT_PERSISTENCE_FILE = "C://Users/Admin/Desktop/DoAn/ProjectAutoGen/src/main/resources/META-INF/";
    public final static String PATH_OUT_PUT_PROPERTY_FILE = "C://Users/Admin/Desktop/DoAn/ProjectAutoGen/src/main/resources/";
    public final static String PATH_OUT_PUT_SPRING_CONFIG_FILE = "C://Users/Admin/Desktop/DoAn/ProjectAutoGen/src/main/resources/";

    public final static String GROUP_ID = "com.qsoft.augen";
    public final static String ARTIFACT_ID = "AutoGenAdminWebPage";

    public final static String PACKAGE_ENTITY = "com.qsoft.augen.persistence.entity";
    public final static String PACKAGE_DAO_IMPL = "com.qsoft.augen.persistence.dao.impl";
    public final static String PACKAGE_DAO_INTERFACE = "com.qsoft.augen.persistence.dao";
    public final static String PACKAGE_SERVICE_IMPL = "com.qsoft.augen.business.impl";
    public final static String PACKAGE_SERVICE_INTERFACE = "com.qsoft.augen.business";


    static StringTemplateGroup group = new StringTemplateGroup("myGroup", PATH_TEMPLATE_RESOURCE, DefaultTemplateLexer.class);

    public static void genEntity(List<MetaTable> metaTables)
    {
        for (Iterator<MetaTable> it = metaTables.iterator(); it.hasNext(); )
        {
            StringTemplate template = group.getInstanceOf("Entity");
            MetaTable mt = it.next();

            template.setAttribute("package", PACKAGE_ENTITY);
            template.setAttribute("tableName", mt.getNameTable());
            template.setAttribute("entity", mt.getNameTableUpperFirst());
            template.setAttribute("meta_columns", mt.getMetaColumns());

            String content = template.toString();
            String fileName = mt.getNameTableUpperFirst() + ".java";

            WriteFileJava.WritToFile(PATH_OUT_PUT_ENTITY, fileName, content);
        }
    }

    public static void genDAOInterface(List<MetaTable> metaTables)
    {
        for (Iterator<MetaTable> it = metaTables.iterator(); it.hasNext(); )
        {
            StringTemplate template = group.getInstanceOf("DAOInterface");
            MetaTable mt = it.next();

            template.setAttribute("package", PACKAGE_DAO_INTERFACE);
            template.setAttribute("entity_package", PACKAGE_ENTITY);
            template.setAttribute("entity", mt.getNameTableUpperFirst());
            template.setAttribute("type_of_key", mt.getTypeDataNamePrimaryKey());

            String content = template.toString();
            String fileName = mt.getNameTableUpperFirst() + "DAO.java";

            WriteFileJava.WritToFile(PATH_OUT_PUT_INTERFACE_DAO, fileName, content);
        }
    }

    public static void genDaoImpl(List<MetaTable> metaTables)
    {
        for (Iterator<MetaTable> it = metaTables.iterator(); it.hasNext(); )
        {
            StringTemplate template = group.getInstanceOf("DaoImpl");
            MetaTable mt = it.next();

            template.setAttribute("package", PACKAGE_DAO_IMPL);
            template.setAttribute("dao_interface_package", PACKAGE_DAO_INTERFACE);
            template.setAttribute("entity_package", PACKAGE_ENTITY);
            template.setAttribute("entity_lowwerCase", mt.getNameTable());
            template.setAttribute("entity", mt.getNameTableUpperFirst());
            template.setAttribute("type_of_key", mt.getTypeDataNamePrimaryKey());

            String content = template.toString();
            String fileName = mt.getNameTableUpperFirst() + "DAOImpl.java";

            WriteFileJava.WritToFile(PATH_OUT_PUT_DAO_IMLP, fileName, content);
        }
    }

    public static void genServiceInterface(List<MetaTable> metaTables)
    {
        for (Iterator<MetaTable> it = metaTables.iterator(); it.hasNext(); )
        {
            StringTemplate template = group.getInstanceOf("ServiceInterface");
            MetaTable mt = it.next();

            template.setAttribute("package", PACKAGE_SERVICE_INTERFACE);
            template.setAttribute("entity_package", PACKAGE_ENTITY);
            template.setAttribute("entity", mt.getNameTableUpperFirst());
            template.setAttribute("type_of_key", mt.getTypeDataNamePrimaryKey());

            String content = template.toString();
            String fileName = mt.getNameTableUpperFirst() + "Service.java";

            WriteFileJava.WritToFile(PATH_OUT_PUT_INTERFACE_SERVICE, fileName, content);
        }
    }

    public static void genServiceImpl(List<MetaTable> metaTables)
    {
        for (Iterator<MetaTable> it = metaTables.iterator(); it.hasNext(); )
        {
            StringTemplate template = group.getInstanceOf("ServiceImpl");
            MetaTable mt = it.next();

            template.setAttribute("package", PACKAGE_SERVICE_IMPL);
            template.setAttribute("service_interface_package", PACKAGE_SERVICE_INTERFACE);
            template.setAttribute("entity", mt.getNameTableUpperFirst());
            template.setAttribute("entity_lowwerCase", mt.getNameTable());
            template.setAttribute("dao_interface_package", PACKAGE_DAO_INTERFACE);
            template.setAttribute("entity_package", PACKAGE_ENTITY);
            template.setAttribute("type_of_key", mt.getTypeDataNamePrimaryKey());

            String content = template.toString();
            String fileName = mt.getNameTableUpperFirst() + "ServiceImpl.java";

            WriteFileJava.WritToFile(PATH_OUT_PUT_SERVICE_IMLP, fileName, content);
        }
    }

    public static void genPomFile()
    {
        StringTemplate template = group.getInstanceOf("Pom");

        template.setAttribute("groupId", GROUP_ID);
        template.setAttribute("artifactId", ARTIFACT_ID);

        String content = template.toString();
        String fileName = "pom.xml";

        WriteFileJava.WritToFile(PATH_OUT_PUT_POM, fileName, content);
    }

    public static void genPersistenceFile()
    {
        StringTemplate template = group.getInstanceOf("Persistence");
        String content = template.toString();
        String fileName = "persistence.xml";
        WriteFileJava.WritToFile(PATH_OUT_PUT_PERSISTENCE_FILE, fileName, content);
    }

    public static void genPropertiesFile(PropertyDB propertyDB)
    {
        StringTemplate template = group.getInstanceOf("Properties");

        template.setAttribute("driver", propertyDB.getDriver());
        template.setAttribute("url", propertyDB.getUrl());
        template.setAttribute("username", propertyDB.getUsername());
        template.setAttribute("password", propertyDB.getPassword());

        String content = template.toString();
        String fileName = "db.properties";
        WriteFileJava.WritToFile(PATH_OUT_PUT_PROPERTY_FILE, fileName, content);
    }

    public static void genSpringConfigFile(PropertyDB propertyDB)
    {
        StringTemplate template = group.getInstanceOf("Spring-Config");

        template.setAttribute("package_scan", GROUP_ID);
        template.setAttribute("driver", propertyDB.getDriver());
        template.setAttribute("url", propertyDB.getUrl());
        template.setAttribute("username", propertyDB.getUsername());
        template.setAttribute("password", propertyDB.getPassword());

        String content = template.toString();
        String fileName = "spring-config.xml";
        WriteFileJava.WritToFile(PATH_OUT_PUT_SPRING_CONFIG_FILE, fileName, content);
    }
}
