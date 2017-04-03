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
    private String date;
    private String historyPk;
    private String name;
    HashMap conditions = new HashMap();

    public History(String pk) {
        this.query = new query();
        this.pk = pk;
    }

    public History(String date, String historyPk, String name) {
        this.date = date.substring(0, date.indexOf(" "));
        this.historyPk = historyPk;
        this.name = name;
    }

    public ArrayList<History> getHistory(String table, String column) {
        conditions.clear();
        conditions.put(column, pk);
        
        ArrayList<History> history = new ArrayList<>();
        ArrayList data = query.getManyRows(table, "pk", conditions);

        for (int i = data.size()-1; i >= 0; i--) {
            conditions.clear();
            conditions.put("pk", data.get(i).toString());
            history.add(new History(query.getValue(table, "timestamp", conditions),
                    query.getValue(table, "restaurant_pk", conditions),
                    query.getValue(table, "name", conditions)));
        }
        return history;
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

    public String getName() {
        return name;
    }

    public String getHistoryPk() {
        return historyPk;
    }

    public String getDate() {
        return date;
    }

    @Override
    public String toString() {
        return name + historyPk + date;
    }
}
