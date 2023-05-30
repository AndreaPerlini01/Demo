package com.example.demo;

import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.SQLExec;
import org.testcontainers.containers.MySQLContainer;

import java.io.File;

public class MysqlContainerBaseTest {

    final MySQLContainer MY_SQL_CONTAINER;

    public MysqlContainerBaseTest() {
        MY_SQL_CONTAINER = new MySQLContainer("mysql:latest");
        // MY_SQL_CONTAINER.withUrlParam("serverTimezone", "Europe/Rome");
        MY_SQL_CONTAINER.start();
        System.setProperty("DB_URL", MY_SQL_CONTAINER.getJdbcUrl());
        System.setProperty("DB_USERNAME", MY_SQL_CONTAINER.getUsername());
        System.setProperty("DB_PASSWORD", MY_SQL_CONTAINER.getPassword());

        Project project = new Project();
        project.init();
        SQLExec task = new SQLExec();
        task.setDriver( MY_SQL_CONTAINER.getDriverClassName() );
        task.setUrl( MY_SQL_CONTAINER.getJdbcUrl() );
        task.setUserid( MY_SQL_CONTAINER.getUsername() );
        task.setPassword( MY_SQL_CONTAINER.getPassword() );
        task.setProject(project);
        task.setTaskType("sql");
        task.setTaskName("sql");
        task.setSrc(new File("src/test/resources/db/migration/V1__sql.sql"));
        task.execute();

    }

    public void close() {
        MY_SQL_CONTAINER.close();
    }

}











