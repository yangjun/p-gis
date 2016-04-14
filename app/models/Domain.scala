package models

import java.util.UUID

/**
 * Created by yangjungis@126.com on 2016/4/14.
 */


/**
 * WMS配置
 * @param baseUrl
 * @param layers
 * @param format
 * @param transparent
 * @param attribution
 */
case class WMSConfig(baseUrl: String, layers: String, format: String, transparent: Boolean, attribution: String)

/**
 * WMS图层
 * @param id
 * @param name
 * @param enabled
 * @param config
 */
case class WMSLayer(id: String, name: String, enabled: Boolean, config: WMSConfig)


object JsonFormats {
  import play.api.libs.json.Json
  // Generates Writes and Reads for Feed and User thanks to Json Macros
  implicit val wmsConfigFormat = Json.format[WMSConfig]
  implicit val wmsLayerFormat = Json.format[WMSLayer]
}

object Utils {
   def nextId() = {
     UUID.randomUUID.toString
   }
}


