package com.gear.test;

import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;

public class Listener implements ActionListener {

    @Override
    public void processAction(ActionEvent event)
            throws AbortProcessingException {

        System.out.println("Any use case here?");

    }

}
