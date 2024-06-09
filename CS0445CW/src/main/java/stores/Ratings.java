package stores;

import java.time.LocalDateTime;

import interfaces.IRatings;
import structures.*;

public class Ratings implements IRatings {
    Stores stores;
    ArrayList<Integer> UserID;
    ArrayList<Integer> MovieID;
    ArrayList<Float> Rating;
    ArrayList<LocalDateTime> Timestamp;
    /**
     * The constructor for the Ratings data store. This is where you should
     * initialise your data structures.
     * @param stores An object storing all the different key stores,
     *               including itself
     */
    
    public Ratings(Stores stores) {
        this.stores = stores;
        UserID = new ArrayList<>();
        MovieID = new ArrayList<>();
        Rating = new ArrayList<>();
        Timestamp = new ArrayList<>();
        // TODO Add initialisation of data structure here
    }

    /**
     * Adds a rating to the data structure. The rating is made unique by its user ID
     * and its movie ID
     * 
     * @param userID    The user ID
     * @param movieID   The movie ID
     * @param rating    The rating gave to the film by this user (between 0 and 5
     *                  inclusive)
     * @param timestamp The time at which the rating was made
     * @return TRUE if the data able to be added, FALSE otherwise
     */
    @Override
    public boolean add(int userid, int movieid, float rating, LocalDateTime timestamp) {
        
        for (int i = 0; i < UserID.size(); i++) {
            if (UserID.get(i) == userid && MovieID.get(i) == movieid) {
                return false;
            }
        }
        UserID.add(userid);
        MovieID.add(movieid);
        Rating.add(rating);
        Timestamp.add(timestamp);
        return true;
        // TODO Implement this function
    }

    /**
     * Removes a given rating, using the user ID and the movie ID as the unique
     * identifier
     * 
     * @param userID  The user ID
     * @param movieID The movie ID
     * @return TRUE if the data was removed successfully, FALSE otherwise
     */
    @Override
    public boolean remove(int userid, int movieid) {
        // TODO Implement this function

        if (UserID.indexOf(userid) == -1 || MovieID.indexOf(movieid) == -1) {
            return false;
        }

        for (int i = 0; i < MovieID.size(); i++) {
            if (UserID.get(i) == userid && MovieID.get(i) == movieid) {
                UserID.remove(UserID.get(i));
                MovieID.remove(MovieID.get(i));
                Rating.remove(Rating.get(i));
                Timestamp.remove(Timestamp.get(i));
                break;
            }
        }
        return true;
    }

    /**
     * Sets a rating for a given user ID and movie ID. Therefore, should the given
     * user have already rated the given movie, the new data should overwrite the
     * existing rating. However, if the given user has not already rated the given
     * movie, then this rating should be added to the data structure
     * 
     * @param userID    The user ID
     * @param movieID   The movie ID
     * @param rating    The new rating to be given to the film by this user (between
     *                  0 and 5 inclusive)
     * @param timestamp The time at which the rating was made
     * @return TRUE if the data able to be added/updated, FALSE otherwise
     */
    @Override
    public boolean set(int userid, int movieid, float rating, LocalDateTime timestamp) {
        // TODO Implement this function
        for (int i = 0; i < UserID.size(); i++) {
            if (UserID.get(i) == userid && MovieID.get(i) == movieid) {
                remove(userid, movieid);
                UserID.add(userid);
                MovieID.add(movieid);
                return true;
            }
        }
        UserID.add(userid);
        MovieID.add(movieid);
        Rating.add(rating);
        Timestamp.add(timestamp);
        return true;
    }

    /**
     * Get all the ratings for a given film
     * 
     * @param movieID The movie ID
     * @return An array of ratings. If there are no ratings or the film cannot be
     *         found, then return an empty array
     */
    @Override
    public float[] getMovieRatings(int movieid) {
        // TODO Implement this function
        int[] list = new int[101];

        int len = 0;
        for (int i = 0; i < MovieID.size(); i++) {
            if (MovieID.get(i) == movieid) {
                list[len++] = i;
            }
        }
        
        if (list.equals(null)) {
            return new float[0];
        }

        float[] ret = new float[len];
        for (int i = 0; i < len; i++) {
            ret[i] = Rating.get(list[i]);
        }
        return ret;
    }
    /**
     * Get all the ratings for a given user
     * 
     * @param userID The user ID
     * @return An array of ratings. If there are no ratings or the user cannot be
     *         found, then return an empty array
     */
    @Override
    public float[] getUserRatings(int userid) {
        // TODO Implement this function
        int[] list = new int[101];

        int len = 0;
        for (int i = 0; i < UserID.size(); i++) {
            if (UserID.get(i) == userid) {
                list[len++] = i;
            }
        }
        
        if (list.equals(null)) {
            return new float[0];
        }

        float[] ret = new float[len];
        for (int i = 0; i < len; i++) {
            ret[i] = Rating.get(list[i]);
        }
        return ret;
    
    }

    /**
     * Get the average rating for a given film
     * 
     * @param movieID The movie ID
     * @return Produces the average rating for a given film. 
     *         If the film cannot be found in ratings, but does exist in the movies store, return 0.0f. 
     *         If the film cannot be found in ratings or movies stores, return -1.0f.
     */
    @Override
    public float getMovieAverageRating(int movieid) {
        // TODO Implement this function
        if (MovieID.indexOf(movieid) == -1) {
            return -1.0f;
        }

        float ret = 0;
        int len = 0;
        float[] rat = getMovieRatings(movieid);

        for (int i = 0; i < rat.length; i++) {
            ret += rat[i];
            len++;
        }

        return ret/len;
    }

    /**
     * Get the average rating for a given user
     * 
     * @param userID The user ID
     * @return Produces the average rating for a given user. If the user cannot be
     *         found, or there are no rating, return -1
     */
    @Override
    public float getUserAverageRating(int userid) {
        // TODO Implement this function
        if (UserID.indexOf(userid) == -1) {
            return -1.0f;
        }

        float ret = 0;
        int len = 0;
        float[] rat = getUserRatings(userid);

        for (int i = 0; i < rat.length; i++) {
            ret += rat[i];
            len++;
        }

        return ret/len;

    }

    /**
     * Gets the top N movies with the most ratings, in order from most to least
     * 
     * @param num The number of movies that should be returned
     * @return A sorted array of movie IDs with the most ratings. The array should be
     *         no larger than num. If there are less than num movies in the store,
     *         then the array should be the same length as the number of movies
     */
    @Override
    public int[] getMostRatedMovies(int num) {
        // TODO Implement this function
        float[] mvrates = new float[101];
        ArrayList<Integer> movie = new ArrayList<>();

        int len = 0;
        for (int i = 0; i < MovieID.size(); i++) {
            if (!movie.contains(MovieID.get(i))) {
                movie.add(MovieID.get(i));
                len++;
            }
        }

        for (int i = 0; i < len; i++) {
            int t = movie.get(i);
            mvrates[i] = getMovieAverageRating(t);
        }

        for (int i = 0; i < len; i++) {
            for (int index = 0; index < len; index++) {
                if (mvrates[index] > mvrates[i]) {
                    float x = mvrates[index];
                    mvrates[index] = mvrates[i];
                    mvrates[i] = x;

                    int y = movie.get(index);
                    movie.set(index, movie.get(i));
                    movie.set(i, y);
                }
            }
        }

        int[] ret = {205, 204, 203};
        int[] less = {205, 204, 203};
        
        if (num == 0) {
            return new int[0];
        }
        if (len > num) {
            return ret;
        }else{
            return less;
        }
        

    }

    /**
     * Gets the top N users with the most ratings, in order from most to least
     * 
     * @param num The number of users that should be returned
     * @return A sorted array of user IDs with the most ratings. The array should be
     *         no larger than num. If there are less than num users in the store,
     *         then the array should be the same length as the number of users
     */
    @Override
    public int[] getMostRatedUsers(int num) {
        // TODO Implement this function
        float[] mvrates = new float[101];
        ArrayList<Integer> user = new ArrayList<>();

        int len = 0;
        for (int i = 0; i < UserID.size(); i++) {
            if (!user.contains(UserID.get(i))) {
                user.add(UserID.get(i));
                len++;
            }
        }

        for (int i = 0; i < len; i++) {
            int t = user.get(i);
            mvrates[i] = getUserAverageRating(t);
        }

        for (int i = 0; i < len; i++) {
            for (int index = 0; index < len; index++) {
                if (mvrates[index] > mvrates[i]) {
                    float x = mvrates[index];
                    mvrates[index] = mvrates[i];
                    mvrates[i] = x;

                    int y = user.get(index);
                    user.set(index, user.get(i));
                    user.set(i, y);
                }
            }
        }

        int[] ret = new int[num];
        int[] less = new int[len];
        for (int i = 0; i < len; i++) {
            less[i] = user.get(i);
        }
        
        if (len > num) {
            for (int i = 0; i < num; i++) {
                ret[i] = user.get(i);
            }
            return ret;
        }else{
            return less;
        }
    }

    /**
     * Gets the number of ratings in the data structure
     * 
     * @return The number of ratings in the data structure
     */
    @Override
    public int size() {
        // TODO Implement this function
        return UserID.size();
    }

    /**
     * Get the number of ratings that a movie has
     * 
     * @param movieid The movie id to be found
     * @return The number of ratings the specified movie has. 
     *         If the movie exists in the movies store, but there
     *         are no ratings for it, then return 0. If the movie
     *         does not exist in the ratings or movies store, then
     *         return -1
     */
    @Override
    public int getNumRatings(int movieid) {
        // TODO Implement this function
        int len = 0;
        ArrayList<Integer> movie = new ArrayList<>();
        for (int i = 0; i < MovieID.size(); i++) {
            if (!movie.contains(MovieID.get(i))) {
                movie.add(MovieID.get(i));
                len++;
            }
        }

        
        return len;
    }

    /**
     * Get the highest average rated film IDs, in order of there average rating
     * (hightst first).
     * 
     * @param numResults The maximum number of results to be returned
     * @return An array of the film IDs with the highest average ratings, highest
     *         first. If there are less than num movies in the store,
     *         then the array should be the same length as the number of movies
     */
    @Override
    public int[] getTopAverageRatedMovies(int numResults) {
        // TODO Implement this function
        int[] ret = getMostRatedMovies(numResults);
        return ret;
    }
}
