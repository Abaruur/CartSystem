package com.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public abstract class TheSystem
{
    private HashMap<String, Item> itemCollection; //Provides the list of items in the system or the cart depending on which class initiates it.

    TheSystem()
    {
        itemCollection = new HashMap<>();

        if(getClass().getSimpleName().equals("AppSystem"))
        {
            try {

                File myFile = new File("sample.txt");
                Scanner reader = new Scanner(myFile);

                int i=1;

                while(reader.hasNextLine())
                {
                    String[] itemInfo = reader.nextLine().split("  ");

                    Item item = new Item(itemInfo[0],itemInfo[1],Double.parseDouble(itemInfo[2]),Integer.parseInt(itemInfo[3]));

                    itemCollection.put(item.getItemName(), item);
                    i++;

                }

                reader.close();

            } catch (FileNotFoundException e) { System.out.println(e);}
        }
    }

    public void setItemCollection(HashMap<String, Item> itemCollection)
    {
        this.itemCollection = itemCollection;
    }

    public HashMap<String, Item> getItemCollection()
    {
        return itemCollection;
    }

    public Boolean checkAvailability(Item item)
    {
        Boolean result = true;

        //System.out.println("Asked Quantity "+item.getQuantity()+" "+"Available "+item.getAvailableQuantity());

        if(item.getQuantity()>=item.getAvailableQuantity())
        {
            System.out.println("System is unable to add "+item.getItemName()+" to the card. System only has "+item.getAvailableQuantity()+" "+item.getItemName()+"s");
            result = false;
        }

        return result;
    }

    public Boolean add(Item item)
    {
        Boolean result = false;

        if(item==null)
        {
            result = false;
        }
        else if(itemCollection.containsValue(item)&&checkAvailability(item))
        {
            item.setQuantity(item.getQuantity()+1);
            result = true;
        }
        else if(!itemCollection.containsValue(item))
        {
            item.setQuantity(1);
            itemCollection.put(item.getItemName(), item);
            result = true;
        }

        return result;
    }

    public Item remove(String itemName)
    {
        Item result = null;

        if(itemCollection.containsKey(itemName))
        {
            result = itemCollection.get(itemName);
            itemCollection.remove(itemName);
        }

        return result;
    }

    public abstract void display();
}
