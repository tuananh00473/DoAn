import com.qsoft.augen.business.common.StringTemplateService;
import com.qsoft.augen.persistence.entity.MetaColumn;
import com.qsoft.augen.persistence.entity.MetaTable;
import com.qsoft.augen.persistence.entity.PropertyDB;
import com.qsoft.augen.ui.common.DaoUtils;
import com.qsoft.augen.ui.control.MainController;
import org.antlr.stringtemplate.StringTemplate;
import org.antlr.stringtemplate.StringTemplateGroup;
import org.antlr.stringtemplate.language.DefaultTemplateLexer;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * User: Anhnt
 * Date: 8/26/13
 * Time: 11:41 AM
 */
public class MainApp
{
    public static void main(String[] args)
    {
        new MainController();
    }
}
