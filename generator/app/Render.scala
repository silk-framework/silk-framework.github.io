import java.io.{File, OutputStreamWriter, FileOutputStream}
import java.nio.file._
import java.nio.file.attribute.BasicFileAttributes
import java.util
import play.twirl.api.Html

object Render extends App {

  val outputDir = "../"

  // Copy assets to output directory
  new File(outputDir).mkdirs()
  Files.walkFileTree(Paths.get("public"),
                     util.EnumSet.of(FileVisitOption.FOLLOW_LINKS), 100,
                     new CopyDirectory(Paths.get("public"), Paths.get(outputDir + "assets/")))

  // Render html pages
  write("index", views.html.index.render())
  write("news", views.html.news.render())
  write("download", views.html.download.render())
  write("support", views.html.support.render())
  write("publications", views.html.publications.render())

  def write(name: String, html: Html) = {
    val writer = new OutputStreamWriter(new FileOutputStream(outputDir + name + ".html"), "UTF8")
    writer.write(html.toString().replace("/assets", "assets"))
    writer.close()
  }

  class CopyDirectory(source: Path, target: Path) extends SimpleFileVisitor[Path] {

    override def visitFile(file: Path, attributes: BasicFileAttributes): FileVisitResult = {
      System.out.println("Copying " + source.relativize(file))
      Files.copy(file, target.resolve(source.relativize(file)))
      FileVisitResult.CONTINUE
    }

    override def preVisitDirectory(directory: Path, attributes: BasicFileAttributes): FileVisitResult = {
      val targetDirectory = target.resolve(source.relativize(directory))
      try {
        System.out.println("Copying " + source.relativize(directory))
        Files.copy(directory, targetDirectory)
      } catch {
        case e: FileAlreadyExistsException if !Files.isDirectory(targetDirectory) =>
          throw e
      }
      FileVisitResult.CONTINUE
    }
  }
}
