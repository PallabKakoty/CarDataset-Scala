import scala.collection.mutable.ListBuffer

object Main {

  case class Dataset(val name: String, val origin: String, val hp: Float)

  def main(args: Array[String]) = {

    var carBuffer = new ListBuffer[Dataset]()
    val fileSource = io.Source.fromFile("cars_input1.txt")

    if(fileSource.isEmpty) {
      println("Data not found in the file")
    } else {
      for (line <- fileSource.getLines.drop(1)) {
        val cols = line.split(",").map(_.trim)
        carBuffer += Dataset(cols(0), cols(1), cols(2).toFloat)
      }
    }

    println("Enter Number Of Cars (N):")
    val N=scala.io.StdIn.readInt()
    println("Please Enter Origin Name (O):")
    val O=scala.io.StdIn.readLine()

    val filteredList = carBuffer.toList.filter(_.origin == O)
    val sumAvg = filteredList.map(_.hp).sum / filteredList.length
    val carListGreaterThanAvg = filteredList.filter(_.hp > sumAvg).sortWith(_.hp > _.hp)

    if(carListGreaterThanAvg.isEmpty || N == 0) {
      println("Data Not Found")
    } else {
      var (i, j) = (0, 1)
      while(i < carListGreaterThanAvg.length && i < N) {
        println(j+" -> "+carListGreaterThanAvg(i).name+", "+carListGreaterThanAvg(i).origin+", "+carListGreaterThanAvg(i).hp)
        i+=1
        j+=1
      }
    }
  }
}
