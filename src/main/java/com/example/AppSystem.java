package com.example;
import java.util.HashMap;
public class AppSystem extends TheSystem {
    AppSystem()
    {

    }

    @Override
    public void display()
    {
        System.out.println("AppSystem Inventory");
        System.out.format("%-20s%-20s%-10s%-10s\n","Name","Description","Price","Available Quantity");

        for(String key : getItemCollection().keySet())
        {
            Item item = getItemCollection().get(key);
            System.out.format("%-20s%-20s%-10.2f%-10s\n",item.getItemName(),item.getItemDesc(),item.getItemPrice(),item.getAvailableQuantity());
        }
    }

    @Override
    public Boolean add(Item item)
    {
        Boolean result = false;

        if(item==null)
        {
            result = false;
        }
        else if(getItemCollection().containsValue(item))
        {
            System.out.println(item.getItemName()+" is already in the AppSystem");
            result = false;
        }
        else if(!getItemCollection().containsValue(item))
        {
            HashMap<String, Item> demoItemCollection = getItemCollection();
            demoItemCollection.put(item.getItemName(), item);
            setItemCollection(demoItemCollection);

            result = true;
        }

        return result;
    }

    public Item reduceAvailableQuantity(String item_name)
    {
        Item item = null;

        if(getItemCollection().containsKey(item_name))
        {
            item = getItemCollection().get(item_name);
            item.setAvailableQuantity(item.getAvailableQuantity()-1);
        }

        return item;
    }
}