/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.beans;

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.event.ActionEvent;

/**
 *
 * @author dbatskel
 */
@Named(value = "testbenam")
@RequestScoped
public class testbenam {

    /**
     * Creates a new instance of testbenam
     */
    public testbenam() {
    }
    
    public void stat (ActionEvent ae){
        System.out.println("asdasdasdasdasd");
    }
    
    
}
