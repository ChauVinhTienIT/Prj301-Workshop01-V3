/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cart;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import model.Item;

/**
 *
 * @author Lenovo
 */
public class Cart {
    
    private Map<String, Item> cart;

    public Cart() {
    }

    public Cart(Map<String, Item> cart) {
        this.cart = cart;
    }

    public Map<String, Item> getCart() {
        return cart;
    }

    public void setCart(Map<String, Item> cart) {
        this.cart = cart;
    }

    public boolean add(Item item) {
        boolean result = false;
        if (this.cart == null) {
            this.cart = new HashMap<>();
        }
        if (this.cart.containsKey(item.getProduct().getProductId())) {
            int currentQuantity = this.cart.get(item.getProduct().getProductId()).getQuantity();
            item.setQuantity(currentQuantity + item.getQuantity());

        }
        this.cart.put(item.getProduct().getProductId(), item);
        result = true;
        return result;
    }

    public boolean update(String id, Item item) {
        boolean result = false;
        if (this.cart != null) {
            if (this.cart.containsKey(id)) {
                this.cart.replace(id, item);
                result = true;
            }
        }
        return result;
    }

    public boolean remove(String id) {
        boolean result = false;
        if (this.cart != null) {
            if (this.cart.containsKey(id)) {
                this.cart.remove(id);
                result = true;
            }
        }
        return result;
    }
    
    public int totalPrice(){
        int totalPrice = 0;
        List<Item> items = new ArrayList<>(this.getCart().values());
        for (Item item : items) {
            totalPrice += item.getProduct().getPrice() * item.getQuantity();
        }
        return totalPrice;
    }
}
