package applicationcafebistro.ca.polymtl.equipe3.client.applicationcafebistro.model;

/**
 * this class is the model of music
 */
public class Music {

    private int id;
    private String title;
    private String artist;
    private String duration;
    private User user;
    private boolean owner;

    public Music(int id, String title, String artist, String duration, User user, boolean owner) {
        this.id = id;
        this.title = title;
        this.artist = artist;
        this.duration = duration;
        this.user = user;
        this.owner = owner;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isOwner() {
        return owner;
    }

    public void setOwner(boolean owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "Music{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", artist='" + artist + '\'' +
                ", duration='" + duration + '\'' +
                ", user=" + user +
                ", owner=" + owner +
                '}';
    }
}
