package com.github.pisa.model;

import junit.framework.Assert;

import org.junit.Test;

import com.github.pisa.model.node.PisaNode;
import com.github.pisa.model.node.PisaObject;
import com.github.pisa.model.node.PisaObjectPrimitive;

/**
 * User: max
 * Date: 6/20/11
 * Time: 10:48 PM
 * To change this template use File | Settings | File Templates.
 */
public class TestPisaNamespace {

    @Test public void testBasicEquality() {
        PisaNamespace ns = new PisaNamespace();
        ns.addObject("false");
        ns.addObject("true", "false");
        
        PisaNode trueObj = ns.getPisaObject("true");
        Assert.assertNotNull(trueObj);
        Assert.assertTrue(trueObj.size() == 1);

        Assert.assertFalse(ns.checkEqual("false", "true"));

        ns.addObject("another object");
        Assert.assertTrue(ns.checkEqual("another object", "false"));
        Assert.assertFalse(ns.checkEqual("another object", "true"));
        Assert.assertFalse(ns.checkEqual("false", "true"));
        Assert.assertTrue(ns.checkEqual("false", "false"));
    }
    
    @Test public void testObjectPrimitives() {
        PisaNamespace ns = new PisaNamespace();
        ns.addObject("false");
        ns.addObject("true", "false");
        
        ns.addObject("myChar", new PisaObjectPrimitive.Char('s'));
        
        PisaNode myChar = ns.getPisaObject("myChar");
        System.out.println(myChar.size());
        Assert.assertTrue(myChar.size() == 16);
        
        int accum = 0;
        for(int i = 15; i >= 0; i--) {
        	accum *= 2;
        	accum += myChar.get(i);
        }
        
        Assert.assertTrue(accum == 115);
    }
}
