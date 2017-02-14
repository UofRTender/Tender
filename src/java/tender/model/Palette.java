/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tender.model;

import static java.lang.Boolean.parseBoolean;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author marlon
 */
public class Palette {

    public HashMap getFoodPreference(String pk) {
        HashMap foodPreference = new HashMap();
        query select = new query();
        HashMap id = new HashMap();
        id.put("user_id", pk);

        for (Object food : select.getManyRows("palette", "foodtype", id)) {
            id.put("foodtype", food);
            foodPreference.put(food, select.getValue("palette", "preference", id));
            id.remove("foodtype");
        }
        
        return foodPreference;
    }

    public void updatePreferences(String pk) {
        query update = new query();
        HashMap id = new HashMap();
        id.put("user_id", pk);

        try {
            for (Object foods : update.getManyRows("palette", "foodtype", id)) {
                id.put("foodtype", foods);
                update.update("palette", "preference", id, pk);
                id.clear();
                id.put("user_id", pk);
            }
        } catch (Exception e) {

        }
    }

    public ArrayList getLikedFood(String pk) {
        query select = new query();
        HashMap id = new HashMap();
        ArrayList likedFood=new ArrayList();
        
        id.put("user_id", pk);

        for (Object food : select.getManyRows("palette", "foodtype", id)) {
            id.put("foodtype", food);
            String check = select.getValue("palette", "preference", id);
            if (check.equals("t")) {
                likedFood.add(food);
            }
            id.remove("foodtype");
        }
        return likedFood;
    }

    public HashMap getDislikedFood(String pk) {
        HashMap foodPreference = new HashMap();
        query select = new query();
        HashMap id = new HashMap();
        id.put("user_id", pk);

        for (Object food : select.getManyRows("palette", "foodtype", id)) {
            id.put("foodtype", food);
            String check = select.getValue("palette", "preference", id);
            if (check.equals("f")) {
                foodPreference.put(food, check);
            }
            id.remove("foodtype");
        }
        return foodPreference;
    }
}
