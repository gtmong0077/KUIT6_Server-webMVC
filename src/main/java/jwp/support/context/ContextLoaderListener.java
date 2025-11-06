//package jwp.support.context;
//
//import org.springframework.core.io.ClassPathResource;
//import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
//import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
//
//import javax.servlet.ServletContextEvent;
//import javax.servlet.ServletContextListener;
//import javax.servlet.annotation.WebListener;
//import java.util.logging.Logger;
//
//
////웹 애플리케이션이 시작될 때(contextInitialized) jwp.sql 파일을 읽어서 데이터베이스를 초기화하는 역할을 합니다.
//
//@WebListener
//public class ContextLoaderListener implements ServletContextListener {
//    private static final Logger logger = Logger.getLogger(ContextLoaderListener.class.getName());
//
//    @Override
//    public void contextInitialized(ServletContextEvent sce) {
//        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
//        populator.addScript(new ClassPathResource("jwp.sql"));
//        ConnectionManager.getDataSource();
//        DatabasePopulatorUtils.execute(populator, ConnectionManager.getDataSource());
//
//        logger.info("Completed Load ServletContext!");
//    }
//
//    @Override
//    public void contextDestroyed(ServletContextEvent sce) {
//    }
//}