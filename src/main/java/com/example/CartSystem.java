package com.example;

public class CartSystem extends TheSystem
{
    CartSystem()
    {

    }

    @Override
    public void display()
    {
        System.out.println("Cart:");
        System.out.format("%-20s%-20s%-10s%-10s%-10s\n","Name","Description","Price","Quantity","SubTotal");

        double pre_tax=0;

        for(String key : getItemCollection().keySet())
        {
            Item item = getItemCollection().get(key);
            System.out.format("%-20s%-20s%-10s%-10s%-10.2f\n",item.getItemName(),item.getItemDesc(),item.getItemPrice(),item.getQuantity(),item.getItemPrice()*item.getQuantity());
            pre_tax = pre_tax + item.getItemPrice()*item.getQuantity();
        }

        System.out.format("%-20s %-20.2f\n","Pre-Tax Total:",pre_tax);
        System.out.format("%-20s %-20.2f\n","Tax:",pre_tax*.05);
        System.out.format("%-20s %-20.2f\n","Total:",pre_tax+pre_tax*.05);

    }
}