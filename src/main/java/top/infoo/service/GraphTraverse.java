package top.infoo.service;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.traversal.TraversalDescription;
import org.neo4j.graphdb.traversal.Traverser;
import top.infoo.types.Relationships;

/**
 *  遍历节点
 */
public class GraphTraverse {
    private GraphDatabaseService db;

    public GraphDatabaseService getDb() {
        return db;
    }

    public void setDb(GraphDatabaseService db) {
        this.db = db;
    }

    public Traverser getFriends(final Node node) {
        TraversalDescription td = db.traversalDescription();
        td.breadthFirst();
        td.relationships(Relationships.ARROW);
        return td.traverse(node);
    }

}
