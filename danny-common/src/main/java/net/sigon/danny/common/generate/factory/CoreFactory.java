package net.sigon.danny.common.generate.factory;

import net.sigon.danny.common.db.ConnectionFactory;
import net.sigon.danny.common.generate.bean.Configuration;
import org.apache.commons.collections.CollectionUtils;

import java.sql.Connection;
import java.sql.SQLException;


/**
 * Created with IntelliJ IDEA.
 * User: sigon
 * Date: 14-12-9
 * Time: 下午10:32
 * To change this template use File | Settings | File Templates.
 */
public class CoreFactory {
    private Connection conn;

    public Boolean execute(Configuration config){
        init(config);

        return true;
    }
    public void init(Configuration config){
        if(config != null && CollectionUtils.isEmpty(config.getClassPaths())){
            return;
        }
        try {
            conn = ConnectionFactory.getInstance().getConnection(config.getJdbcConnection());
        } catch (SQLException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}
