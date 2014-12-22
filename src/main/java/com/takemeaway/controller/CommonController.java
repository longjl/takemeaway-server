package com.takemeaway.controller;

import com.jfinal.core.Controller;

/**
 * Created by longjianlin on 14/12/19.
 */
public class CommonController extends Controller {
    public void index() {
        render("/index.html");
    }
}
