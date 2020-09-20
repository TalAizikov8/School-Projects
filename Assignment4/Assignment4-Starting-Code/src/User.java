import java.util.ArrayList;

public class User {
  private String userName;
  private boolean online;
  private ArrayList<Song> songList;


  public User() {
    this("");
    //online = false;
  }

  public User(String u) {
    userName = u;
    online = false;
    songList = new ArrayList<Song>();
  }

  public ArrayList<Song> getSongList() {
    return songList;
  }

  public String getUserName() {
    return userName;
  }

  public boolean isOnline() {
    return online;
  }

  //This method will add a song to the given array list
  public void addSong(Song newSong) {

    //might need to change something here
    //this sets the owner of the song to be whoever added the song
    //Song newS = new Song(newSong.getTitle(), newSong.getArtist(), newSong.getDuration(), this, newSong.getDownloads());
    if (newSong.getOwner() == null) {
      this.songList.add(newSong);
      newSong.setOwner(this);
    }else{
      this.songList.add(newSong);
    //  songList.add(newS);
    }
  }

  public int totalSongTime() {
    int duration = 0;
    for (int i = 0; i < songList.size(); i++) {
      duration += songList.get(i).getSeconds();
    }
    return duration;
  }

  public String toString() {
    String s = "" + userName + ": " + songList.size() + " songs (";
    if (!online) s += "not ";
    return s + "online)";
  }

  //registers the users of the class
  public void register(MusicExchangeCenter m) {
    m.registerUser(this); // This needs to be worked on
  }

  //stimulateed login in and out
  public void logon() {
    online = true;
  }

  public void logoff() {
    online = false;
  }

  //this method will give a nice output to the complete list of "online" songs
  public ArrayList<String> requestCompleteSonglist(MusicExchangeCenter m) {

    ArrayList<String> completeMusicList = new ArrayList<String>();
    completeMusicList.add(String.format("%-33s", "    TITLE") + String.format("%-20s", "ARTIST") +
            "TIME" + "   " + "OWNER");
    for (int i = 0; i < m.allAvailableSongs().size(); i++) {
      //this will need to be adjusted
      completeMusicList.add((String.format("%-4s",i+1 + ". ") + (String.format("%-30s", m.allAvailableSongs().get(i).getTitle())) +
              String.format("%-20s", m.allAvailableSongs().get(i).getArtist()) +
              String.format("%1d", m.allAvailableSongs().get(i).getMinutes()) + ":" +
              String.format("%02d", m.allAvailableSongs().get(i).getSeconds())) + "  "+
              m.allAvailableSongs().get(i).getOwner().getUserName());
    }
    return completeMusicList;
  }

  //this method will give a complete list of songs by a specific artist
  public ArrayList<String> requestSonglistByArtist(MusicExchangeCenter m, String artist) {
    ArrayList<String> songsByArtist = new ArrayList<String>();
    songsByArtist.add(String.format("%-33s", "    TITLE") + String.format("%-20s", "ARTIST") +
            "TIME" + "   " + "OWNER");
    for (int i = 0; i < m.availableSongsByArtist(artist).size(); i++) {
      songsByArtist.add(String.format("%-4s",i+1 + ". ") + (String.format("%-30s", m.availableSongsByArtist(artist).get(i).getTitle())) +
              String.format("%-20s", m.availableSongsByArtist(artist).get(i).getArtist()) +
              String.format("%1d", m.availableSongsByArtist(artist).get(i).getMinutes()) + ":"+
              String.format("%02d", m.availableSongsByArtist(artist).get(i).getSeconds())+"  "+
              m.availableSongsByArtist(artist).get(i).getOwner().getUserName());
    }
    return songsByArtist;
  }

  public void downloadSong(MusicExchangeCenter m, String title, String ownerName) {
    try {
      m.userWithName(userName).addSong(m.getSong(title, ownerName));
    }catch (Exception e) {}
  }
}
