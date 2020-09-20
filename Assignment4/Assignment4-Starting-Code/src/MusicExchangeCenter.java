import com.sun.jdi.Value;
import com.sun.source.tree.Tree;

import java.util.*;

public class MusicExchangeCenter {
    private ArrayList<User> users;
    private HashMap<String, Float> royalties= new HashMap<String, Float>();
    private ArrayList<Song> downloadedSongs = new ArrayList<Song>();

    public MusicExchangeCenter () {
        users = new ArrayList<User>();
    }

    //This method returns all the users that are online
    public ArrayList<User> onlineUsers () {
        ArrayList<User> onlineUsers = new ArrayList<User>();
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).isOnline()){
                onlineUsers.add(users.get(i));
            }
        }
        return onlineUsers;
    }
    //This method returns all the songs that are available for download
    public ArrayList<Song> allAvailableSongs() {
        ArrayList<Song> availableSongs = new ArrayList<Song>();
        for (int i = 0; i < onlineUsers().size(); i++) {
            availableSongs.addAll(onlineUsers().get(i).getSongList());
        }
        return availableSongs;
    }

    public String toString() {
        return "Music Exchange Center ("+ onlineUsers().size()+" users on line, " + allAvailableSongs().size()+" songs available)";
    }
    //returns a user with a given name
    public User userWithName(String givenName) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUserName().equals(givenName)) {
                return users.get(i);
            }
        }
        return null;
    }
    //this method will add a user to users
    //it will use userWithName() to check if the user exists
    public void registerUser (User givenUser) {
        if (userWithName(givenUser.getUserName()) == null) {
            users.add(givenUser);
        }
    }
    //This method will return on the songs by a given artist
    public ArrayList<Song> availableSongsByArtist(String artist) {
        ArrayList<Song> availableSongs = new ArrayList<Song>();
        for (int i = 0; i < allAvailableSongs().size(); i++) {
            if (allAvailableSongs().get(i).getArtist().equals(artist)) {
                availableSongs.add(allAvailableSongs().get(i));
            }
        }
        return availableSongs;
    }
    //this method will return a song by a user given he is online and the song exists
    public Song getSong(String title, String ownerName) {
        if (userWithName(ownerName).isOnline()) {
            for (int i = 0; i < userWithName(ownerName).getSongList().size(); i++) {
                if (userWithName(ownerName).getSongList().get(i).getTitle().equals(title)) {
                    if (royalties.containsKey(userWithName(ownerName).getSongList().get(i).getArtist())) {
                        royalties.put(userWithName(ownerName).getSongList().get(i).getArtist(), royalties.get(userWithName(ownerName).getSongList().get(i).getArtist()) + (float) 0.25);
                    }
                    else{
                        royalties.put(userWithName(ownerName).getSongList().get(i).getArtist(), (float)0.25);
                    }
                    userWithName(ownerName).getSongList().get(i).setDownloads();
                    downloadedSongs.add(userWithName(ownerName).getSongList().get(i));
                    return userWithName(ownerName).getSongList().get(i);
                }
            }
        }
        return null;
    }
    //this method will desplay how much money each artist made
    public void displayRoyalties(){
        System.out.println(" Amount   Artist");
        System.out.println("----------------");
        royalties.forEach((key, value) -> System.out.println("$"+String.format("%.02f     ",value) + key));
    }

    public TreeSet<Song> uniqueDownloads() {
        TreeSet<Song> tree = new TreeSet<Song>();
        for (int i = 0; i<downloadedSongs.size(); i++){
            if (!tree.contains(downloadedSongs.get(i))){
                tree.add(downloadedSongs.get(i));
            }
        }
        return tree;
    }

    public ArrayList<Pair<Integer, Song>> songsByPopularity() {
        ArrayList<Pair<Integer,Song>> thePairs = new ArrayList<>();
        for (Song holder: uniqueDownloads()) {
            thePairs.add(new Pair<>(holder.getDownloads(),holder));
        }
        Collections.sort(thePairs, new Comparator<Pair<Integer, Song>>() {
            public int compare(Pair<Integer, Song> p1, Pair<Integer, Song> p2) {
                return -(p1.getKey() - p2.getKey());
            }
        });
        return thePairs;
    }

    public ArrayList<Song> getDownloadedSongs() {
        return downloadedSongs;
    }
}
