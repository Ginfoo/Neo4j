package top.infoo.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ParseUtil {
    private static final String NEO4J_PROPERTIES = "neo4j.properties";
    private static final String NEO4J_PATH = "neo4j.path";

    /**
     * 解析 neo4j.properties 并返回 neo4j_path 的值
     *
     * @return
     */
    public static String getNeo4jPath() {
        InputStream in = null;
        Properties proUtil = new Properties();
        try {
            in = ParseUtil.class.getClassLoader().getResourceAsStream(NEO4J_PROPERTIES);
            proUtil.load(in);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return proUtil.getProperty(NEO4J_PATH);
    }
}
