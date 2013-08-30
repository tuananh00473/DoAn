package com.qsoft.augen.ui.control;

import com.qsoft.augen.business.common.StringTemplateService;
import com.qsoft.augen.persistence.entity.MetaColumn;
import com.qsoft.augen.persistence.entity.MetaTable;
import com.qsoft.augen.persistence.entity.PropertyDB;
import com.qsoft.augen.ui.common.DaoUtils;
import com.qsoft.augen.ui.view.MainGui;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * User: Anhnt
 * Date: 8/29/13
 * Time: 12:52 AM
 */
public class MainController
{
    private String driver = "org.postgresql.Driver";
    private String url = "jdbc:postgresql://localhost:5432/";
    private String user;
    private String password;

    private DaoUtils daoUtils = new DaoUtils();
    private PropertyDB propertyDB;
    private MainGui mainGui;

    private Connection connection;
    private List<String> tables;
    private List<MetaTable> metaTables;

    public MainController()
    {
        mainGui = new MainGui(this);

        mainGui.show();
    }

    public void doGenerate()
    {
        StringTemplateService.genEntity(metaTables);
        StringTemplateService.genDAOInterface(metaTables);
        StringTemplateService.genDaoImpl(metaTables);
        StringTemplateService.genServiceInterface(metaTables);
        StringTemplateService.genServiceImpl(metaTables);
        StringTemplateService.genPomFile();
        StringTemplateService.genPersistenceFile();
        StringTemplateService.genPropertiesFile(propertyDB);
        StringTemplateService.genSpringConfigFile(propertyDB);
    }

    public void doQuit()
    {
        System.exit(0);
    }

    public void doDisConnect()
    {
        try
        {
            connection.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void doConnect()
    {
        try
        {
            url = url.concat(mainGui.getTxtDBName().getText());
            user = mainGui.getTxtUserName().getText();
            password = mainGui.getTxtPassWord().getText();

            propertyDB = new PropertyDB(driver, url, user, password);
            connection = daoUtils.getConnection(propertyDB);

            getMetaTables();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void getMetaTables()
    {
        try
        {
            System.out.println("--------------GetListTable---------------");
            DatabaseMetaData metaData = connection.getMetaData();

            String[] types = {"TABLE"};
            ResultSet rs = metaData.getTables(null, null, "%", types);
            tables = new ArrayList<String>();
            metaTables = new ArrayList<MetaTable>();
            while (rs.next())
            {
                String tableName = rs.getString(3);
                tables.add(tableName); // 1: none 2: schema 3: table name 4: table type (TABLE, VIEW)
            }

            for (String table : tables)
            {
                ResultSet rsPrimaryKeys = metaData.getPrimaryKeys("", "", table);
                boolean flagPrimaryKey = false;
                String nameColumPrimaryKey = "";
                while (rsPrimaryKeys.next())
                {
                    flagPrimaryKey = true;
                    nameColumPrimaryKey = rsPrimaryKeys.getString("COLUMN_NAME");
                }
                if (!flagPrimaryKey)
                {
                    continue;
                }

                MetaTable metaTable = new MetaTable();
                metaTable.setNameTable(table);
                metaTable.setNamePrimaryKey(nameColumPrimaryKey);

                ResultSet rsTable = metaData.getColumns(null, null, table, "%");

                MetaColumn metaColumn = null;
                while (rsTable.next())
                {
                    metaColumn = new MetaColumn();
                    if (rsTable.getString(4).equals(nameColumPrimaryKey))
                    {
                        metaColumn.setFlagPrimaryKey(true);
                    }
                    System.out.println("Column Name : " + rsTable.getString(4) + " ; " + "Type Data   : " + rsTable.getString(6));

                    metaColumn.setNameColumn(rsTable.getString(4));
                    metaColumn.setTypeData(rsTable.getString(6));

                    metaTable.addMetaColumns(metaColumn);

                    metaTable.setTypeDataNamePrimaryKey(metaColumn.getTypeDataPrimaryKey());
                    // metaTables.add(metaColumn); //1: none 2: schema 3: table name
                    // 4: column name 5: length 6: data type (CHAR, VARCHAR,
                    // TIMESTAMP, ...)
                }
                metaTables.add(metaTable);
                System.out.println("\n");
            }
            rs.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
