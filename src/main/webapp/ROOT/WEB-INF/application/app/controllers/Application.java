package controllers;

import play.*;
import play.mvc.*;

import java.text.ParseException;
import java.util.*;

import controllers.Secure.Security;
import dto.SiparisDto;

import models.*;

@With(Secure.class)
public class Application extends Controller {

    public static void index() throws ParseException  {
    	IsAkis.bugun(0, null , null);
    }

}