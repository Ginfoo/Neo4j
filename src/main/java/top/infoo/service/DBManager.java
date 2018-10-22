package top.infoo.service;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import top.infoo.util.ParseUtil;

import java.io.File;

public class DBManager {
    private volatile static GraphDatabaseService db;

    private static void registerShutDownHook(final GraphDatabaseService db) {
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                db.shutdown();
            }
        });
    }

    /**
     * 获取数据库
     *
     * @return
     */
    public static GraphDatabaseService getDB() {
        if (db == null) {
            synchronized (DBManager.class) {
                GraphDatabaseFactory dbFactory = new GraphDatabaseFactory();
                db = dbFactory.newEmbeddedDatabase(new File(ParseUtil.getNeo4jPath()));
                registerShutDownHook(db);
            }
        }
        return db;
    }

}
