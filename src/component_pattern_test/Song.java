package component_pattern_test;

/**
 * Created by shane on 8/12/15.
 */
public class Song extends SongComponent {
    String name;
    String band;
    int releaseYear;

    public Song(String name, String band, int releaseYear) {
        this.name = name;
        this.band = band;
        this.releaseYear = releaseYear;
    }

    public String getSongName() {
        return name;
    }

    public void setSongName(String name) {
        this.name = name;
    }

    public String getBandName() {
        return band;
    }

    public void setBandName(String band) {
        this.band = band;
    }

    @Override
    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public void displaySongInfo() {
        System.out.println(getSongName() + " was recorded by " +
                getBandName() + " in " + getReleaseYear());
    }
}
