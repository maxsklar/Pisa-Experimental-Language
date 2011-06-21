package com.github.pisa.model;

import junit.framework.Assert;

import org.junit.Test;

/**
 * Created by IntelliJ IDEA.
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
        
        PisaObject trueObj = ns.getPisaObject("true");
        Assert.assertNotNull(trueObj);
        Assert.assertTrue(trueObj.size() == 1);

        Assert.assertFalse(ns.checkEqual("false", "true"));

        ns.addObject("another object");
        Assert.assertTrue(ns.checkEqual("another object", "false"));
        Assert.assertFalse(ns.checkEqual("another object", "true"));
        Assert.assertFalse(ns.checkEqual("false", "true"));
        Assert.assertTrue(ns.checkEqual("false", "false"));
    }
}
