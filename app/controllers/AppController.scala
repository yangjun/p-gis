package controllers

import javax.inject.Inject

import scala.concurrent.Future

import play.api.Logger
import play.api.mvc.{Action, Controller}
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.libs.json._

// Reactive Mongo imports

import reactivemongo.api.Cursor

import play.modules.reactivemongo.{// ReactiveMongo Play2 plugin
MongoController,
ReactiveMongoApi,
ReactiveMongoComponents
}

// BSON-JSON conversions/collection

import reactivemongo.play.json._
import reactivemongo.play.json.collection._

/**
 * Created by yangjungis@126.com on 2016/4/14.
 */
class AppController @Inject()(val reactiveMongoApi: ReactiveMongoApi)
  extends Controller with MongoController with ReactiveMongoComponents {

  def collection: JSONCollection = db.collection[JSONCollection]("persons")

  import play.api.data.Form
  import models._
  import models.JsonFormats._

  def create = Action.async {
    val config = WMSConfig(
      "http://mesonet.agron.iastate.edu/cgi-bin/wms/nexrad/n0r.cgi",
      "chengdu",
      "image/png",
      true,
      "Weather data © 2012 IEM Nexrad")
    val layer = WMSLayer(
      Utils.nextId(),
      "WMS",
      true,
      config
    )

    Future {
      Ok("")
    }
  }
}
