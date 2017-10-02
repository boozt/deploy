package controllers;

import models.IslemTanim;
import play.mvc.With;

@CRUD.For(IslemTanim.class)
@With(Secure.class)
public class IslemTanimlari extends CRUD{

}
