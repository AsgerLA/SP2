
public class PrintedBook extends PhysicalBook {

  private int pages;

  public PrintedBook(String title, String literatureType, int copies, int pages) {
    super(title, literatureType, copies);
    this.pages = pages;
  }

  protected double calculatePoints() {
    return (this.pages * this.literatureTypePoints * this.copies);
  }
}
