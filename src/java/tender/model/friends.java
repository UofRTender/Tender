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
public class friends {

    private int adderPK;
    private int addeePK;
    private HashMap relationship = new HashMap();
    private boolean flipped;

    public void friends() {
        adderPK = 0;
        addeePK = 0;
        flipped = false;
        relationship.put("adder_pk", adderPK);
        relationship.put("addee_pk", addeePK);
    }

    public void friends(int adderPK, int addeePK) {
        this.adderPK = adderPK;
        this.addeePK = addeePK;
        flipped = false;
        relationship.put("adder_pk", adderPK);
        relationship.put("addee_pk", addeePK);
    }

    public boolean checkRequest() {
        query data = new query();

        if (data.exists("friends", relationship)) {
            return true;
        } else {
            flipped = true;
            relationship.clear();
            relationship.put("adder_pk", addeePK);
            relationship.put("addee_pk", adderPK);
            if (data.exists("friends", relationship)) {
                return true;
            } else {
                friends(adderPK, addeePK);
                return false;
            }

        }
    }

    public int checkStatusOfRequest() {
        query data = new query();
        return Integer.parseInt(data.getValue("friends", "confirmed", relationship));
    }

    public void makeFriendRequest() {
        query data = new query();
        relationship.put("confirmed", "2");
        data.insert("friends", relationship);
    }

    public boolean getFlipped() {
        return flipped;
    }

    public boolean showButton() {
        if (!checkRequest()) {
            return true;
        } else if (checkStatusOfRequest() == 0 && flipped) {
            return true;
        } else if (checkStatusOfRequest() == 1) {
            return false;
        } else if (checkStatusOfRequest() == 2) {
            return false;
        } else if (checkStatusOfRequest() == 0) {
            return false;
        }
        return false;
    }

    public ArrayList<user> getPendingFriends(int pk) {
        ArrayList<user> pendingFriends = new ArrayList<user>();
        query check = new query();
        HashMap addee_pk = new HashMap();
        user requester = new user();

        addee_pk.put("addee_pk", pk);
        addee_pk.put("confirmed", "2");

        for (Object adder : check.getManyRows("friends", "adder_pk", addee_pk)) {
            requester.user(Integer.parseInt(adder.toString()));
            pendingFriends.add(requester);
        }

        return pendingFriends;
    }

    public ArrayList<user> getConfirmedFriends(int pk) {
        ArrayList<user> pendingFriends = new ArrayList<user>();
        query check = new query();
        HashMap addee_pk = new HashMap();
        user requester = new user();

        addee_pk.put("addee_pk", pk);
        addee_pk.put("confirmed", "1");

        for (Object adder : check.getManyRows("friends", "adder_pk", addee_pk)) {
            requester.user(Integer.parseInt(adder.toString()));
            pendingFriends.add(requester);
        }
        
        addee_pk.clear();
        addee_pk.put("adder_pk", pk);
        addee_pk.put("confirmed", "1");
        for (Object adder : check.getManyRows("friends", "addee_pk", addee_pk)) {
            requester.user(Integer.parseInt(adder.toString()));
            pendingFriends.add(requester);
        }
        
        return pendingFriends;
    }
    
    public void acceptRequest(int pk, int friendPk){
        query update = new query();
        HashMap conditions = new HashMap();
        
        conditions.put("addee_pk", pk);
        conditions.put("adder_pk", friendPk);
        update.update("friends", "confirmed", conditions, "1");
    }

    public void rejectRequest(int pk, int friendPk) {
        query update = new query();
        HashMap conditions = new HashMap();
        
        conditions.put("addee_pk", pk);
        conditions.put("adder_pk", friendPk);
        update.update("friends", "confirmed", conditions, "0");
    }
}
