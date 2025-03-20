
public abstract class Title {

  private String title;
  protected float literatureTypePoints;
  protected int copies;
  protected double rate = 0.067574;

  public Title(String title, String literatureType, int copies) {
    this.title = title;
    this.copies = copies;

    if (literatureType.equals("BI")) {
      this.literatureTypePoints = 3.0f;
    } else if (literatureType.equals("TE")) {
      this.literatureTypePoints = 3.0f;
    } else if (literatureType.equals("LYRIK")) {
      this.literatureTypePoints = 6.0f;
    } else if (literatureType.equals("SKØN")) {
      this.literatureTypePoints = 1.7f;
    } else if (literatureType.equals("FAG")) {
      this.literatureTypePoints = 1.0f;
    } else {
      throw new IllegalArgumentException("Unknown literatureType '"+literatureType+"'");
    }

  }

  public double calculateRoyalty() {
    return (this.calculatePoints() * this.rate);
  }

  protected abstract double calculatePoints();

}
