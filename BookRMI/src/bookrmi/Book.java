/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bookrmi;

import java.io.Serializable;

/**
 *
 * @author Ghadeer
 */
public class Book implements Serializable{
    private static final long serialVersionUID = 1190476516911661470L;
    private String title;
    private String code;
    private double cost; 
    
     public Book(String code) {
        this.code = code;
    }

    public Book(String title, String isbn, double cost) {
        this.title = title;
        this.code = isbn;
        this.cost = cost;
    }

    public String getTitle() {
        return title;
    }

    public String getCode() {
        return code;
    }

    public double getCost() {
        return cost;
    }

    public String toString() {
        return "> " + this.title + " ($" + this.cost + ")";
    }
}
