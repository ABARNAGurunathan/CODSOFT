public class Movie {
    private String title;
    private String[] genres;

    public Movie(String title, String[] genres) {
        this.title = title;
        this.genres = genres;
    }

    public String getTitle() {
        return title;
    }

    public String[] getGenres() {
        return genres;
    }

    public boolean isOfGenre(String genre) {
        for (String g : genres) {
            if (g.equalsIgnoreCase(genre)) {
                return true;
            }
        }
        return false;
    }
}
