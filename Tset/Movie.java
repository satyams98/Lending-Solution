package Tset;

import java.time.LocalDate;
import java.util.Comparator;


public class Movie implements Comparator
{
    private int movieId;
    private String movieName;
    private String genre;
    private LocalDate releaseDate;
    Movie(int mid,String mname,String gen,LocalDate rdate)
    {
        this.genre=gen;
        this.movieId=mid;
        this.movieName=mname;
        this.releaseDate=rdate;
    }

    public int getMovieId() {
        return movieId;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public String getGenre() {
        return genre;
    }

    public String getMovieName() {
        return movieName;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "movieId=" + movieId +
                ", movieName='" + movieName + '\'' +
                ", genre='" + genre + '\'' +
                ", releaseDate=" + releaseDate +
                '}';
    }

    @Override
    public int compare(Object o1, Object o2)
    {
        Movie m1=(Movie) o1;
        Movie m2=(Movie) o2;
        return m1.releaseDate.compareTo(m2.releaseDate);
    }
}
