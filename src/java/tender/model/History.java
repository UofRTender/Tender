/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tender.model;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author marlon
 */
public class History {

    private String pk;
    private query query;
    HashMap conditions = new HashMap();

    public History(String pk) {
        this.query = new query();
        this.pk = pk;
    }

    public HashMap getHistory() {
        conditions.clear();
        conditions.put("user_id", pk);
        HashMap data = new HashMap();
        for (Object history : query.getManyRows("history", "pk", conditions)) {
            conditions.clear();
            //data.put("test", history);
            conditions.put("pk", history.toString());
            data.put(query.getValue("history", "restaurant_pk", conditions), query.getValue("history", "name", conditions));
        }
        return data;
    }

    public ArrayList getTypeHistory() {
        conditions.clear();
        conditions.put("user_id", pk);
        ArrayList data = new ArrayList();
        for (Object history : query.getManyRows("history", "pk", conditions)) {
            conditions.clear();
            //data.put("test", history);
            conditions.put("pk", history.toString());
            data.add(query.getValue("history", "type", conditions));
        }
        return data;
    }

    public ArrayList getGroupHistory(String pk) {
        conditions.clear();
        conditions.put("group_pk", pk);
        ArrayList data = new ArrayList();

        for (Object history : query.getManyRows("grouphistory", "pk", conditions)) {
            conditions.clear();
            conditions.put("pk", history.toString());
            data.add(query.getValue("grouphistory", "restaurant_pk", conditions));
        }
        return data;
    }

    public ArrayList getTypeGroupHistory(String pk) {
        conditions.clear();
        conditions.put("group_pk", pk);
        ArrayList data = new ArrayList();

        for (Object history : query.getManyRows("grouphistory", "pk", conditions)) {
            conditions.clear();
            conditions.put("pk", history.toString());
            data.add(query.getValue("grouphistory", "type", conditions));
        }
        return data;
    }
}
