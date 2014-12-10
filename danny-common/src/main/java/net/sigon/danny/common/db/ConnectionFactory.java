/*
 *  Copyright 2005 The Apache Software Foundation
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package net.sigon.danny.common.db;

import net.sigon.danny.common.generate.bean.JdbcConnection;
import net.sigon.danny.common.util.ObjectFactory;
import org.apache.commons.lang.StringUtils;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.SQLException;
import java.util.Properties;

/**
 * This class assumes that classes are cached elsewhere for performance reasons,
 * but also to make sure that any native libraries are only loaded one time
 * (avoids the dreaded UnsatisfiedLinkError library loaded in another
 * classloader)
 * 
 * @author Jeff Butler
 */
public class ConnectionFactory {

    private static ConnectionFactory instance = new ConnectionFactory();

    public static ConnectionFactory getInstance() {
        return instance;
    }

    /**
	 *  
	 */
    private ConnectionFactory() {
        super();
    }

    public Connection getConnection(JdbcConnection config)
            throws SQLException {
        Driver driver = getDriver(config);

        Properties props = new Properties();

        if (StringUtils.isNotBlank(config.getUserId())) {
            props.setProperty("user", config.getUserId()); //$NON-NLS-1$
        }

        if (StringUtils.isNotBlank(config.getPassword())) {
            props.setProperty("password", config.getPassword()); //$NON-NLS-1$
        }

        Connection conn = driver.connect(config.getConnectionURL(), props);

        if (conn == null) {
            throw new SQLException("conn is null"); //$NON-NLS-1$
        }

        return conn;
    }

    private Driver getDriver(JdbcConnection connectionInformation) {
        String driverClass = connectionInformation.getDriverClass();
        Driver driver;

        try {
            Class<?> clazz = ObjectFactory.externalClassForName(driverClass);
            driver = (Driver) clazz.newInstance();
        } catch (Exception e) {
            throw new RuntimeException("", e); //$NON-NLS-1$
        }

        return driver;
    }
}
