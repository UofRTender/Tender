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
public class groups {

    private String name;
    private String gpk;

    public groups(String gpk, String name) {
        this.name = name;
        this.gpk = gpk;
    }

    public groups() {
        name = "";
        gpk = "0";
    }

    public String getName() {
        return name;
    }

    public String getPk() {
        return gpk;
    }

    public void makeGroup(String groupName, String pk, String[] members) {
        query check = new query();
        HashMap groupInfo = new HashMap();
        groupInfo.put("name", groupName);
        int newGroupId = check.insert("groups", groupInfo);

        HashMap groupMembers = new HashMap();
        groupMembers.put("person_pk", pk);
        groupMembers.put("groups_id", newGroupId);
        check.insert("mygroups", groupMembers);
        if (members.length > 0) {
            for (String member : members) {
                groupMembers.clear();
                groupMembers.put("person_pk", member);
                groupMembers.put("groups_id", newGroupId);
                check.insert("mygroups", groupMembers);
            }
        }
    }

    public HashMap getGroups(String pk) {
        ArrayList<String> groupPK = new ArrayList<>();
        HashMap groupInfo = new HashMap();
        String test = "";
        query check = new query();
        HashMap userInfo = new HashMap();

        userInfo.put("person_pk", pk);
        groupPK = check.getManyRows("mygroups", "groups_id", userInfo);

        for (int i = 0; i < groupPK.size(); i++) {
            userInfo.clear();
            userInfo.put("pk", groupPK.get(i));
            groupInfo.put(groupPK.get(i), check.getValue("groups", "name", userInfo));
        }

        return groupInfo;
    }

    public ArrayList getGroupMembers(String pk) {
        ArrayList groupMembers;
        query check = new query();
        HashMap groupInfo = new HashMap();

        groupInfo.put("groups_id", pk);
        groupMembers = check.getManyRows("myGroups", "person_pk", groupInfo);

        return groupMembers;
    }

    public String getName(String pk) {
        query check = new query();
        HashMap groupInfo = new HashMap();

        groupInfo.put("pk", pk);
        return check.getValue("groups", "name", groupInfo);
    }

    public void leaveGroup(String pk, String user) {
        query check = new query();
        HashMap groupInfo = new HashMap();
        groupInfo.put("groups_id", pk);
        groupInfo.put("person_pk", user);
        check.delete("mygroups", groupInfo);
    }

    public void updateName(String pk, String newName) {
        query update = new query();
        HashMap restraints = new HashMap();
        restraints.put("pk", pk);
        //restraints.put("name", newName);
        update.update("groups", "name", restraints, newName);
    }

    public void addMember(String pk, String userPK) {
        query update = new query();
        HashMap restraints = new HashMap();
        restraints.put("person_pk", userPK);
        restraints.put("groups_id", pk);
        update.insert("mygroups", restraints);
    }

    public boolean isMember(String pk, String userPK) {
        query check = new query();
        HashMap restraints = new HashMap();
        restraints.put("person_pk", userPK);
        restraints.put("groups_id", pk);
        return check.exists("mygroups", restraints);
    }

    public ArrayList<groups> getGroup(String userPK) {
        ArrayList<groups> group = new ArrayList<>();
        query query = new query();
        HashMap conditions = new HashMap();
        conditions.put("person_pk", userPK);
        for (Object groupPK : query.getManyRows("mygroups", "groups_id", conditions)) {
            conditions.clear();
            conditions.put("pk", groupPK.toString());

            group.add(new groups(groupPK.toString(), query.getValue("groups", "name", conditions)));
        }
        return group;
    }
}
