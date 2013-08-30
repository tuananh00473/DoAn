package com.qsoft.augen.ui.common;

import com.qsoft.augen.persistence.entity.MetaColumn;
import com.qsoft.augen.ui.view.MainGui;
import org.jdesktop.beansbinding.AutoBinding;
import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.swingbinding.JTableBinding;
import org.jdesktop.swingbinding.SwingBindings;

import javax.swing.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: tienhd
 * Date: 7/30/13
 * Time: 3:01 PM
 */
public class TableBinding
{
    public static Map<String, JTableBinding> bindMap = new HashMap<String, JTableBinding>();

    public static void bindingMetaColumn(List<MetaColumn> MetaColumnList, JTable table, JPanel panel)
    {
        unbinding(table, panel);
        JTableBinding jTableBinding = SwingBindings.createJTableBinding(AutoBinding.UpdateStrategy.READ, MetaColumnList, table);
        //-----------------------Option----------------------------------------------
        jTableBinding.addColumnBinding(BeanProperty.create(MetaColumn.NAME_COLUMN)).setColumnName(MainGui.COLUMN_NAME);
        jTableBinding.addColumnBinding(BeanProperty.create(MetaColumn.TYPE_DATA)).setColumnName(MainGui.DATA_TYPE);

        jTableBinding.bind();
        bindMap.put(panel.hashCode() + "." + table.hashCode(), jTableBinding);
    }

    //----------------- unbinding-------------------------------
    public static void unbinding(JTable table, JPanel panel)
    {
        String key = table.hashCode() + "." + panel.hashCode();
        if (bindMap.containsKey(key))
        {
            bindMap.get(key).unbind();
            bindMap.remove(bindMap.get(key));
        }
    }
}
