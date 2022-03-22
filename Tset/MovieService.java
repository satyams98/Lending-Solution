package Tset;

import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class MovieService {
    static HashMap<Movie, Double> movieRating = new HashMap<>();
    void addKeyValuePairsToMap(Movie movie, Double rating) {
            movieRating.put(movie, rating);
    }

    List<String> getHigherRatedMovieNames(int rating) {
       return movieRating.entrySet().stream()
                .filter(e->e.getValue()>rating)
               .map(e->e.getKey().getMovieName())
                .collect(Collectors.toList());
    }

    List<String> getMovieNamesOfSpecificGenre(String genre) {
        return movieRating.entrySet().stream()
                .filter(e->e.getKey().getGenre().equalsIgnoreCase(genre))
                .map(e->e.getKey().getMovieName())
                .collect(Collectors.toList());
    }

    List<String > getMovieNamesReleasedAfterSpecificDateAndRatingLesserThanThree(LocalDate releaseDate) {
        return movieRating.entrySet()
                .stream()
                .filter(e->e.getKey().getReleaseDate().isAfter(releaseDate) && e.getValue()<3)
                .map(e->e.getKey().getMovieName())
                .collect(Collectors.toList());
    }

    List<Movie> getSortedMovieListByReleaseDate() {
        Comparator<Movie> c = new Comparator<Movie>() {
            @Override
            public int compare(Movie o1, Movie o2) {
                return o1.getReleaseDate().compareTo(o2.getReleaseDate());
            }
        };

        List<Movie> m = movieRating.entrySet()
                .stream()
                .map(e->e.getKey())
                .collect(Collectors.toList());

        m.sort(c);
        return m;
    }

    List<Movie> getSortedMovieListByRating() {
        Comparator<Movie> c = new Comparator<Movie>() {
            @Override
            public int compare(Movie o1, Movie o2) {
               return movieRating.get(o1).compareTo(movieRating.get(o2));
            }
        };

        List<Movie> m = movieRating.entrySet()
                .stream()
                .map(e->e.getKey())
                .collect(Collectors.toList());

        m.sort(c);
        return m;
    }

    public static void main(String[] args) {

        Movie m1 = new Movie(101,  "The Shawshank Redemption", "Thriller", LocalDate.of(1994, 5, 3));
        Movie m2 = new Movie(102, "The Godfather", "Crime", LocalDate.of(1972, 2, 15));
        Movie m3 = new Movie(103, "The Dark Knight", "Superhero", LocalDate.of(2008, 4, 20));
        Movie m4 = new Movie(104, " The Godfather: Part II ", "Crime", LocalDate.of(1974, 3,25));
        Movie m5 = new Movie(105, " 12 Angry Men", "Action", LocalDate.of(1957, 6, 12));
        Movie m6= new Movie(1,"Avatar","Adventure",LocalDate.of(2008,4,1));
        Movie m7= new Movie(2,"Avenger","Action",LocalDate.of(2021, 3,26));
        Movie m8= new Movie(3,"Mad Max","Action",LocalDate.of(2010, 11,20));

        MovieService ms = new MovieService();
        ms.addKeyValuePairsToMap(m1, 9.3);
        ms.addKeyValuePairsToMap(m2, 9.2);
        ms.addKeyValuePairsToMap(m3, 8.9);
        ms.addKeyValuePairsToMap(m4, 9.0);
        ms.addKeyValuePairsToMap(m5, 9.0);
        ms.addKeyValuePairsToMap(m6, 8.6);
        ms.addKeyValuePairsToMap(m7, 8.8);
        ms.addKeyValuePairsToMap(m8, 2.0);

        System.out.println("Higher Rated: "+ms.getHigherRatedMovieNames(8));
        System.out.println("Specific Genre Crime: "+ms.getMovieNamesOfSpecificGenre("Crime"));
        System.out.println("After Specific release date and ratig less than 3");
        ms.getMovieNamesReleasedAfterSpecificDateAndRatingLesserThanThree(LocalDate.of(1990, 5, 1))
                        .forEach(m->{
                            System.out.println(m);
                        });

        System.out.println("Sorted By rating: ");
        ms.getSortedMovieListByRating().forEach(m->{
            System.out.println(m.getMovieName()+" "+movieRating.get(m));
        });

        System.out.println("Sorted By Release Date: ");
        ms.getSortedMovieListByReleaseDate().forEach(m->{
            System.out.println(m.getMovieName()+" "+ m.getReleaseDate() );
                }

        );


    }
}
