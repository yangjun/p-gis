package controllers

import javax.inject.Inject

import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.mvc.{Action, Controller}

import scala.concurrent.Future

// Reactive Mongo imports

import play.modules.reactivemongo.{MongoController, ReactiveMongoApi, ReactiveMongoComponents}

// BSON-JSON conversions/collection

import reactivemongo.play.json.collection._

/**
 * Created by yangjungis@126.com on 2016/4/14.
 */
class AppController @Inject()(val reactiveMongoApi: ReactiveMongoApi)
  extends Controller with MongoController with ReactiveMongoComponents {

  def wmsLayers: Future[JSONCollection] = database map (f => {
    f.collection[JSONCollection]("wmsLayers")
  })


  import models._

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

    import models.JsonFormats._

    wmsLayers map (f => {
      f.insert(layer)
    }) map (f => {
      Ok("")
    })

  }
}
