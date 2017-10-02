package controllers;

import play.mvc.With;


@CRUD.For(models.CamTipi.class)
@With(Secure.class)
public class CamTipi extends CRUD{

}
