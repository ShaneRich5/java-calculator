package component_pattern_test;

/**
 * Created by shane on 8/12/15.
 */
public class DiscJockey{

    SongComponent songList;

    // newSongList contains every component_pattern_test.Song, component_pattern_test.SongGroup,
    // and any Songs saved in SongGroups

    public DiscJockey(SongComponent newSongList){

        songList = newSongList;

    }

    // Calls the displaySongInfo() on every component_pattern_test.Song
    // or component_pattern_test.SongGroup stored in songList

    public void getSongList(){

        songList.displaySongInfo();

    }

}
