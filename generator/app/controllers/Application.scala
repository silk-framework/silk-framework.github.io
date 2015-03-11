package controllers

import play.api.mvc.{Action, Controller}

object Application extends Controller {

  def index = Action {
    Ok(views.html.index())
  }

  def news = Action {
    Ok(views.html.news())
  }
  
  def download = Action {
    Ok(views.html.download())
  }

  def support = Action {
    Ok(views.html.support())
  }

  def publications = Action {
    Ok(views.html.publications())
  }
}