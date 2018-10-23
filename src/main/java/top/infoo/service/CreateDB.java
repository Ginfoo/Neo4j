package top.infoo.service;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import top.infoo.types.NodeLabels;
import top.infoo.types.Relationships;
import top.infoo.util.ParseUtil;

import java.io.*;
import java.net.URL;

public class CreateDB {
    public static void main(String[] args) throws IOException {
        GraphDatabaseFactory dbFactory = new GraphDatabaseFactory();
        GraphDatabaseService db = dbFactory.newEmbeddedDatabase(new File(ParseUtil.getNeo4jPath()));
        try (Transaction tx = db.beginTx()) {
            URL url = CreateDB.class.getClassLoader().getResource("Alarm_graph.txt");
            File file = new File(url.getPath());
            BufferedReader bf = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            String line = null;
            int i = 0;
            while ((line = bf.readLine()) != null) {
                String[] r = line.split("  ");
                if (i == 0) {
                    System.out.println(r.length);
                    for (int j = 0; j < r.length; j++) {
                        Node node = db.createNode(NodeLabels.NODE);
                        node.setProperty("index", j);
                    }
                }
                for (int k = 0; k < r.length; k++) {
                    if (r[k].equals("1")) {
                        Node fromNode = db.findNode(NodeLabels.NODE, "index", i);
                        Node toNode = db.findNode(NodeLabels.NODE, "index", k);
                        Relationship relationship = fromNode.createRelationshipTo(toNode, Relationships.ARROW);
                    }
                }
                System.out.println(i++);
            }

            bf.close();

            System.out.println(file.exists());
            tx.success();
        }

    }
}
