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

    public void makeGroup(String groupName, String pk, String[] members) {
        query check = new query();
        HashMap groupInfo = new HashMap();
        groupInfo.put("name", groupName);
        int newGroupId = check.insert("groups", groupInfo);

        HashMap groupMembers = new HashMap();
        groupMembers.put("person_pk", pk);
        groupMembers.put("groups_id", newGroupId);
        check.insert("mygroups", groupMembers);
        for (String member : members) {
            groupMembers.clear();
            groupMembers.put("person_pk", member);
            groupMembers.put("groups_id", newGroupId);
            check.insert("mygroups", groupMembers);
        }
    }

    public String getGroups(String pk) {
        ArrayList<String> groupPK = new ArrayList<>();
        HashMap groupInfo=new HashMap();
        String test="";
        query check = new query();
        HashMap userInfo = new HashMap();
        
        userInfo.put("person_pk", pk);
        groupPK=check.getManyRows("mygroups", "groups_id", userInfo);
        
        for(int i=0;i<groupPK.size();i++){
            userInfo.clear();
            userInfo.put("pk", groupPK.get(i));
            groupInfo.put(groupPK.get(i), check.getValue("groups", "name", userInfo));
        }
        
        return groupInfo.toString();
    }
}
