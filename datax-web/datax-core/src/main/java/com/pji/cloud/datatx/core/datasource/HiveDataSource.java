package com.pji.cloud.datatx.core.datasource;

import com.pji.cloud.datatx.core.enums.DbType;
import com.pji.cloud.datatx.core.util.Constants;

/**
 * data source of hive
 */
public class HiveDataSource extends BaseDataSource {

  /**
   * gets the JDBC url for the data source connection
   * @return jdbc url
   */
  @Override
  public String driverClassSelector() {
    return Constants.ORG_APACHE_HIVE_JDBC_HIVE_DRIVER;
  }

  /**
   * @return db type
   */
  @Override
  public DbType dbTypeSelector() {
    return DbType.HIVE;
  }
}