package ingest

import java.sql.{Date, Timestamp}
import java.text.{DateFormat, SimpleDateFormat}

object DataUtils extends Serializable {

  def getTime(timeString: String): Long = {
    // Date format of data
    val dateFormat: DateFormat = new SimpleDateFormat("yyyy-MM-ddHH:mm:ss")
    dateFormat.parse(timeString).getTime
  }

  def extractTimestamp(line: String): Long = getTime(line.split("\"timestamp\":\"")(1).substring(0, line.split("\"timestamp\":\"")(1).indexOf("\"")))

  def splitLineInKeyAndValue(line: String): (String, String) = {
    val splittedLine = line.split("=")
    (splittedLine(0), splittedLine(1).replace(" ", ""))
  }

  // we need to add the lane to the body to keep the same partitioning for input and output data and get correct latency meausurements
  def putLaneNumberInBody(key: String, message: String): (String, String) = {
    val indexOfLaneNumber = key.lastIndexOf("/")

    val measurementId = key.substring(0, indexOfLaneNumber)
    val lane = key.substring(indexOfLaneNumber + 1)

    val msg = s"""{"internalId": "$lane", ${message.substring(1)}"""

    (measurementId, msg)
  }
}

case class Observation(timestamp: Long, key: String, message: String) extends Serializable {

  def replaceTimestampWithCurrentTimestamp(): Observation = {
    val timestampToReplace: String = message.split("\"timestamp\":\"")(1).substring(0, message.split("\"timestamp\":\"")(1).indexOf("\""))

    val dateFormat: DateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS")
    val currentTimeString: String = dateFormat.format(new Timestamp(System.currentTimeMillis()))
    val newMsg = message.replaceFirst(timestampToReplace, currentTimeString)
    Observation(timestamp, key, newMsg)
  }
}