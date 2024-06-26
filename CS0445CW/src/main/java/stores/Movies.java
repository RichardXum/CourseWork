package stores;

import java.time.LocalDate;

import interfaces.IMovies;
import structures.*;
import structures.HashTable.Entry;

public class Movies implements IMovies{
    Stores stores;
    HashTable<Integer, Integer> Id;
    HashTable<Integer, String> Title;
    HashTable<Integer, String> OriginalTitle;
    HashTable<Integer, String> Overview;
    HashTable<Integer, String> Tagline;
    HashTable<Integer, String> Status;
    HashTable<Integer, Genre[]> Genres;
    HashTable<Integer, LocalDate> Release;
    HashTable<Integer, Long> Budget;
    HashTable<Integer, Long> Revenue;
    HashTable<Integer, String[]> Languages;
    HashTable<Integer, String> OriginalLanguage;
    HashTable<Integer, Double> Runtime;
    HashTable<Integer, String> Homepage;
    HashTable<Integer, Boolean> Adult;
    HashTable<Integer, Boolean> Video;
    HashTable<Integer, String> Poster;
    HashTable<Integer, Double> VoteAverage;
    HashTable<Integer, Integer> VoteCount;
    HashTable<Integer, Integer> FilmID;
    HashTable<Integer, Integer> FilmID_1;
    HashTable<Integer, Integer> CollectionID;
    HashTable<Integer, String> CollectionName;
    HashTable<Integer, String> CollectionBackdropPath;
    HashTable<Integer, String> CollectionPosterPath;
    HashTable<Integer, String> ImdbID;
    HashTable<Integer, Double> Popularity;
    HashTable<Integer, Company> Company;
    HashTable<Integer, String> Country;
    /**
     * The constructor for the Movies data store. This is where you should
     * initialise your data structures.
     * @param stores An object storing all the different key stores,
     *               including itself
     */
    public Movies(Stores stores) {
        this.stores = stores;
        Id = new HashTable<>(1000);
        Title = new HashTable<>(1000);
        OriginalTitle = new HashTable<>(1000);
        Overview = new HashTable<>(1000);
        Tagline = new HashTable<>(1000);
        Status = new HashTable<>(1000);
        Genres = new HashTable<>(1000);
        Release = new HashTable<>(1000);
        Budget = new HashTable<>(1000);
        Revenue = new HashTable<>(1000);
        Languages = new HashTable<>(1000);
        OriginalLanguage = new HashTable<>(1000);
        Runtime = new HashTable<>(1000);
        Homepage = new HashTable<>(1000);
        Adult = new HashTable<>(1000);
        Video = new HashTable<>(1000);
        Poster = new HashTable<>(1000);
        VoteAverage = new HashTable<>(1000);
        VoteCount = new HashTable<>(1000);
        FilmID = new HashTable<>(1000);
        FilmID_1 = new HashTable<>(1000);
        CollectionBackdropPath = new HashTable<>(1000);
        CollectionID = new HashTable<>(1000);
        CollectionName = new HashTable<>(1000);
        CollectionPosterPath = new HashTable<>(1000);
        ImdbID = new HashTable<>(1000);
        Popularity = new HashTable<>(1000);
        Company = new HashTable<>(1000);
        Country = new HashTable<>(1000);
        // TODO Add initialisation of data structure here
    }

    /**
     * Adds data about a film to the data structure
     * 
     * @param id               The unique ID for the film
     * @param title            The English title of the film
     * @param originalTitle    The original language title of the film
     * @param overview         An overview of the film
     * @param tagline          The tagline for the film (empty string if there is no
     *                         tagline)
     * @param status           Current status of the film
     * @param genres           An array of Genre objects related to the film
     * @param release          The release date for the film
     * @param budget           The budget of the film in US Dollars
     * @param revenue          The revenue of the film in US Dollars
     * @param languages        An array of ISO 639 language codes for the film
     * @param originalLanguage An ISO 639 language code for the original language of
     *                         the film
     * @param runtime          The runtime of the film in minutes
     * @param homepage         The URL to the homepage of the film
     * @param adult            Whether the film is an adult film
     * @param video            Whether the film is a "direct-to-video" film
     * @param poster           The unique part of the URL of the poster (empty if
     *                         the URL is not known)
     * @return TRUE if the data able to be added, FALSE otherwise
     */
    @Override
    public boolean add(int id, String title, String originalTitle, String overview, String tagline, String status, Genre[] genres, LocalDate release, long budget, long revenue, String[] languages, String originalLanguage, double runtime, String homepage, boolean adult, boolean video, String poster) {

        if (Id.get(id) != null) {
            return false;
        }else{
            Id.put(id, id);
            Title.put(id, title);
            OriginalTitle.put(id, originalTitle);
            Overview.put(id, overview);
            Tagline.put(id, tagline);
            Status.put(id, status);
            Genres.put(id, genres);
            Release.put(id, release);
            Budget.put(id, budget);
            Revenue.put(id, revenue);
            Languages.put(id, languages);
            OriginalLanguage.put(id, originalLanguage);
            Runtime.put(id, runtime);
            Homepage.put(id, homepage);
            Adult.put(id, adult);
            Video.put(id, video);
            Poster.put(id, poster);
            return true;
        }
    }

    /**
     * Removes a film from the data structure, and any data
     * added through this class related to the film
     * 
     * @param id The film ID
     * @return TRUE if the film has been removed successfully, FALSE otherwise
     */
    @Override
    public boolean remove(int id) {
        if (Id.get(id) != null) {
            Id.remove(id);
            Title.remove(id);
            OriginalTitle.remove(id);;
            Overview.remove(id);;
            Tagline.remove(id);;
            Status.remove(id);;
            Genres.remove(id);;
            Release.remove(id);;
            Budget.remove(id);;
            Revenue.remove(id);;
            Languages.remove(id);;
            OriginalLanguage.remove(id);;
            Runtime.remove(id);;
            Homepage.remove(id);;
            Adult.remove(id);;
            Video.remove(id);;
            Poster.remove(id);;
            return true;
        }
        return false;
    }

    /**
     * Gets all the IDs for all films
     * 
     * @return An array of all film IDs stored
     */
    @Override
    public int[] getAllIDs() {
        int[] AllIds = Id.traverseI();
        // TODO Implement this function
        return AllIds;
    }

    /**
     * Finds the film IDs of all films released within a given range. If a film is
     * released either on the start or end dates, then that film should not be
     * included
     * 
     * @param start The start point of the range of dates
     * @param end   The end point of the range of dates
     * @return An array of film IDs that were released between start and end
     */
    @Override
    public int[] getAllIDsReleasedInRange(LocalDate start, LocalDate end) {
        // TODO Implement this function
        
        int[] Ids = getAllIDs();
        int[] films = new int[Ids.length];
        
        for(int i = 0; i < Ids.length; i++) {
            if(getRelease(Ids[i]).isAfter(start) && getRelease(Ids[i]).isBefore(end))
                films[i] = Ids[i];
        }
        
        int len_ret = 0;
        for(int i = 0; i < films.length; i++) {
            if(films[i] != 0)
            len_ret++;
        }
        
        int j = 0;
        int[] ret = new int[len_ret];
        for(int i = 0; i < films.length; i++) {
            if(films[i] != 0)
            ret[j++] = films[i];
        }
        
        return ret;
    }

    /**
     * Gets the title of a particular film, given the ID number of that film
     * 
     * @param id The movie ID
     * @return The title of the requested film. If the film cannot be found, then
     *         return null9
     */
    @Override
    public String getTitle(int id) {
        
        // TODO Implement this function
        return Title.get(id);
    }

    /**
     * Gets the original title of a particular film, given the ID number of that
     * film
     * 
     * @param id The movie ID
     * @return The original title of the requested film. If the film cannot be
     *         found, then return null
     */
    @Override
    public String getOriginalTitle(int id) {
        // TODO Implement this function
        return OriginalTitle.get(id);
    }

    /**
     * Gets the overview of a particular film, given the ID number of that film
     * 
     * @param id The movie ID
     * @return The overview of the requested film. If the film cannot be found, then
     *         return null
     */
    @Override
    public String getOverview(int id) {
        // TODO Implement this function
        return Overview.get(id);
    }

    /**
     * Gets the tagline of a particular film, given the ID number of that film
     * 
     * @param id The movie ID
     * @return The tagline of the requested film. If the film cannot be found, then
     *         return null
     */
    @Override
    public String getTagline(int id) {
        // TODO Implement this function
        return Tagline.get(id);
    }

    /**
     * Gets the status of a particular film, given the ID number of that film
     * 
     * @param id The movie ID
     * @return The status of the requested film. If the film cannot be found, then
     *         return null
     */
    @Override
    public String getStatus(int id) {
        // TODO Implement this function
        return Status.get(id);
    }

    /**
     * Gets the genres of a particular film, given the ID number of that film
     * 
     * @param id The movie ID
     * @return The genres of the requested film. If the film cannot be found, then
     *         return null
     */
    @Override
    public Genre[] getGenres(int id) {
        // TODO Implement this function
        return Genres.get(id);
    }

    /**
     * Gets the release date of a particular film, given the ID number of that film
     * 
     * @param id The movie ID
     * @return The release date of the requested film. If the film cannot be found,
     *         then return null
     */
    @Override
    public LocalDate getRelease(int id) {
        // TODO Implement this function
        return Release.get(id);
    }

    /**
     * Gets the budget of a particular film, given the ID number of that film
     * 
     * @param id The movie ID
     * @return The budget of the requested film. If the film cannot be found, then
     *         return -1
     */
    @Override
    public long getBudget(int id) {
        // TODO Implement this function
        if (Budget.get(id)!=null) {
            return Budget.get(id);
        }
        return -1;
    }

    /**
     * Gets the revenue of a particular film, given the ID number of that film
     * 
     * @param id The movie ID
     * @return The revenue of the requested film. If the film cannot be found, then
     *         return -1
     */
    @Override
    public long getRevenue(int id) {
        // TODO Implement this function
        if (Revenue.get(id)!=null) {
            return Revenue.get(id);
        }
        return -1;
    }

    /**
     * Gets the languages of a particular film, given the ID number of that film
     * 
     * @param id The movie ID
     * @return The languages of the requested film. If the film cannot be found,
     *         then return null
     */
    @Override
    public String[] getLanguages(int id) {
        // TODO Implement this function
        return Languages.get(id);
    }

    /**
     * Gets the original language of a particular film, given the ID number of that
     * film
     * 
     * @param id The movie ID
     * @return The original language of the requested film. If the film cannot be
     *         found, then return null
     */
    @Override
    public String getOriginalLanguage(int id) {
        // TODO Implement this function
        return OriginalLanguage.get(id);
    }

    /**
     * Gets the runtime of a particular film, given the ID number of that film
     * 
     * @param id The movie ID
     * @return The runtime of the requested film. If the film cannot be found, then
     *         return -1
     */
    @Override
    public double getRuntime(int id) {
        // TODO Implement this function
        if (Runtime.get(id)!=null) {
            return Runtime.get(id);
        }
        return -1;
    }

    /**
     * Gets the homepage of a particular film, given the ID number of that film
     * 
     * @param id The movie ID
     * @return The homepage of the requested film. If the film cannot be found, then
     *         return null
     */
    @Override
    public String getHomepage(int id) {
        // TODO Implement this function
        return Homepage.get(id);
    }

    /**
     * Gets weather a particular film is classed as "adult", given the ID number of
     * that film
     * 
     * @param id The movie ID
     * @return The "adult" status of the requested film. If the film cannot be
     *         found, then return false
     */
    @Override
    public boolean getAdult(int id) {
        // TODO Implement this function
        if (Adult.get(id)!=null) {
            return Adult.get(id);
        }
        return false;
    }

    /**
     * Gets weather a particular film is classed as "direct-to-video", given the ID
     * number of that film
     * 
     * @param id The movie ID
     * @return The "direct-to-video" status of the requested film. If the film
     *         cannot be found, then return false
     */
    @Override
    public boolean getVideo(int id) {
        // TODO Implement this function
        if (Video.get(id)!=null) {
            return Video.get(id);
        }
        return false;
    }

    /**
     * Gets the poster URL of a particular film, given the ID number of that film
     * 
     * @param id The movie ID
     * @return The poster URL of the requested film. If the film cannot be found,
     *         then return null
     */
    @Override
    public String getPoster(int id) {
        // TODO Implement this function
        return Poster.get(id);
    }


    /**
     * Sets the average IMDb score and the number of reviews used to generate this
     * score, for a particular film
     * 
     * @param id          The movie ID
     * @param voteAverage The average score on IMDb for the film
     * @param voteCount   The number of reviews on IMDb that were used to generate
     *                    the average score for the film
     * @return TRUE if the data able to be added, FALSE otherwise
     */
    @Override
    public boolean setVote(int id, double voteAverage, int voteCount) {
        // TODO Implement this function
        if (Id.get(id)!=null) {
            VoteAverage.put(id, voteAverage);
            VoteCount.put(id, voteCount);

            return true;
        }
        return false;
    }

    /**
     * Gets the average score for IMDb reviews of a particular film, given the ID
     * number of that film
     * 
     * @param id The movie ID
     * @return The average score for IMDb reviews of the requested film. If the film
     *         cannot be found, then return -1
     */
    @Override
    public double getVoteAverage(int id) {
        // TODO Implement this function
        if (Id.get(id) != null) {
            return VoteAverage.get(id);
        }
        return -1;
    }

    /**
     * Gets the amount of IMDb reviews used to generate the average score of a
     * particular film, given the ID number of that film
     * 
     * @param id The movie ID
     * @return The amount of IMDb reviews used to generate the average score of the
     *         requested film. If the film cannot be found, then return -1
     */
    @Override
    public int getVoteCount(int id) {
        // TODO Implement this function
        if (Id.get(id) != null) {
            return VoteCount.get(id);
        }
        return -1;
    }

    /**
     * Adds a given film to a collection. The collection is required to have an ID
     * number, a name, and a URL to a poster for the collection
     * 
     * @param filmID                 The movie ID
     * @param collectionID           The collection ID
     * @param collectionName         The name of the collection
     * @param collectionPosterPath   The URL where the poster can
     *                               be found
     * @param collectionBackdropPath The URL where the backdrop can
     *                               be found
     * @return TRUE if the data able to be added, FALSE otherwise
     */
    @Override
    public boolean addToCollection(int filmID, int collectionID, String collectionName, String collectionPosterPath, String collectionBackdropPath) {
        // TODO Implement this function
        if (Id.get(filmID) != null) {
            FilmID.put(filmID, collectionID);
            FilmID_1.put(collectionID, filmID);
            CollectionID.put(collectionID, collectionID);
            CollectionName.put(collectionID, collectionName);
            CollectionBackdropPath.put(collectionID, collectionBackdropPath);
            CollectionPosterPath.put(collectionID, collectionPosterPath);

            return true;
        }
        return false;
    }

    /**
     * Get all films that belong to a given collection
     * 
     * @param collectionID The collection ID to be searched for
     * @return An array of film IDs that correspond to the given collection ID. If
     *         there are no films in the collection ID, or if the collection ID is
     *         not valid, return an empty array.
     */
    @Override
    public int[] getFilmsInCollection(int collectionID) {
        // TODO Implement this function
        int len_ret = 0;

        int[] res = new int[size()];
        Entry<Integer, Integer> entry = CollectionID.table[collectionID];
            while (entry != null) {
                res[len_ret++] = FilmID_1.get(entry.value);
                entry = entry.next;
            }

        int[] ret = new int[len_ret];
        if (len_ret == 0) {
            int[] non = new int[0];
            return non;
        }
        for(int i = 0; i < len_ret; i++) {
            ret[i] = res[i];
        }
        
        return ret;
    }

    /**
     * Gets the name of a given collection
     * 
     * @param collectionID The collection ID
     * @return The name of the collection. If the collection cannot be found, then
     *         return null
     */
    @Override
    public String getCollectionName(int collectionID) {
        // TODO Implement this function
        return CollectionName.get(collectionID);
    }

    /**
     * Gets the poster URL for a given collection
     * 
     * @param collectionID The collection ID
     * @return The poster URL of the collection. If the collection cannot be found,
     *         then return null
     */
    @Override
    public String getCollectionPoster(int collectionID) {
        // TODO Implement this function
        return CollectionPosterPath.get(collectionID);
    }

    /**
     * Gets the backdrop URL for a given collection
     * 
     * @param collectionID The collection ID
     * @return The backdrop URL of the collection. If the collection cannot be
     *         found, then return null
     */
    @Override
    public String getCollectionBackdrop(int collectionID) {
        // TODO Implement this function
        return CollectionBackdropPath.get(collectionID);
    }

    /**
     * Gets the collection ID of a given film
     * 
     * @param filmID The movie ID
     * @return The collection ID for the requested film. If the film cannot be
     *         found, then return -1
     */
    @Override
    public int getCollectionID(int filmID) {
        // TODO Implement this function
        if (FilmID.get(filmID)!=null) {
            return FilmID.get(filmID);
        }
        return -1;
    }

    /**
     * Sets the IMDb ID for a given film
     * 
     * @param filmID The movie ID
     * @param imdbID The IMDb ID
     * @return TRUE if the data able to be set, FALSE otherwise
     */
    @Override
    public boolean setIMDB(int filmID, String imdbID) {
        // TODO Implement this function
        if (Id.get(filmID) != null && ImdbID.get(filmID) == null) {
            ImdbID.put(filmID, imdbID);
            return true;
        }
        return false;
    }

    /**
     * Gets the IMDb ID for a given film
     * 
     * @param filmID The movie ID
     * @return The IMDb ID for the requested film. If the film cannot be found,
     *         return null
     */
    @Override
    public String getIMDB(int filmID) {
        // TODO Implement this function
        return ImdbID.get(filmID);
    }

    /**
     * Sets the popularity of a given film. If the popularity for a film already exists, replace it with the new value
     * 
     * @param id         The movie ID
     * @param popularity The popularity of the film
     * @return TRUE if the data able to be set, FALSE otherwise
     */
    @Override
    public boolean setPopularity(int id, double popularity) {
        // TODO Implement this function
        if (Id.get(id)==null) {
            return false;
        }else{
            Popularity.remove(id);
            Popularity.put(id, popularity);
        }
        return true;
    }

    /**
     * Gets the popularity of a given film
     * 
     * @param id The movie ID
     * @return The popularity value of the requested film. If the film cannot be
     *         found, then return -1.0. If the popularity has not been set, return 0.0
     */
    @Override
    public double getPopularity(int id) {
        // TODO Implement this function
        if (Id.get(id)!=null) {
            if(Popularity.get(id) == null){
                return 0.0;
            }else{
                return Popularity.get(id);
            }
        }
        return -1.0;
    }

    /**
     * Adds a production company to a given film
     * 
     * @param id      The movie ID
     * @param company A Company object that represents the details on a production
     *                company
     * @return TRUE if the data able to be added, FALSE otherwise
     */
    @Override
    public boolean addProductionCompany(int id, Company company) {
        // TODO Implement this function
        if (Id.get(id) != null) {
            Company.put(id, company);
            return true;
        }
        return false;
    }

    /**
     * Adds a production country to a given film
     * 
     * @param id      The movie ID
     * @param country A ISO 3166 string containing the 2-character country code
     * @return TRUE if the data able to be added, FALSE otherwise
     */
    @Override
    public boolean addProductionCountry(int id, String country) {
        // TODO Implement this function
        if (Id.get(id)!=null) {
            Country.put(id, country);
            return true;
        }
        return false;
    }

    /**
     * Gets all the production companies for a given film
     * 
     * @param id The movie ID
     * @return An array of Company objects that represent all the production
     *         companies that worked on the requested film. If the film cannot be
     *         found, then return null
     */
    @Override
    public Company[] getProductionCompanies(int id) {
        // TODO Implement this function
        if (Id.get(id) == null) {
            return null;
        }

        int len_ret = 0;

        Company[] res = new Company[size()];
        Entry<Integer, Company> entry = Company.table[id];
            while (entry != null) {
                res[len_ret++] = entry.value;
                entry = entry.next;
                
            }

        Company[] ret = new Company[len_ret];
        if (len_ret == 0) {
            return null;
        }
        for(int i = 0; i < len_ret; i++) {
            ret[i] = res[i];
        }
        
        return ret;
    }

    /**
     * Gets all the production companies for a given film
     * 
     * @param id The movie ID
     * @return An array of Strings that represent all the production countries (in
     *         ISO 3166 format) that worked on the requested film. If the film
     *         cannot be found, then return null
     */
    @Override
    public String[] getProductionCountries(int id) {
        // TODO Implement this function
        if (Id.get(id) == null) {
            return null;
        }

        int len_ret = 0;

        String[] res = new String[size()];
        Entry<Integer, String> entry = Country.table[id];
            while (entry != null) {
                res[len_ret++] = entry.value;
                entry = entry.next;
            }

        String[] ret = new String[len_ret];
        if (len_ret == 0) {
            return null;
        }
        for(int i = 0; i < len_ret; i++) {
            ret[i] = res[i];
        }
        
        return ret;
    
    }

    /**
     * States the number of movies stored in the data structure
     * 
     * @return The number of movies stored in the data structure
     */
    @Override
    public int size() {
        // TODO Implement this function
        return Id.size();
    }

    /**
     * Produces a list of movie IDs that have the search term in their title,
     * original title or their overview
     * 
     * @param searchTerm The term that needs to be checked
     * @return An array of movie IDs that have the search term in their title,
     *         original title or their overview. If no movies have this search term,
     *         then an empty array should be returned
     */
    @Override
    public int[] findFilms(String searchTerm) {
        // TODO Implement this function
        int[] Ids = getAllIDs();
        int[] films = new int[Ids.length];
        int len_ret = 0;

        for(int i = 0; i < Ids.length; i++) {
            if(Title.get(Ids[i]).contains(searchTerm)){
                films[i] = Ids[i];
                len_ret++;
        }
    }
        int j = 0;
        int[] ret = new int[len_ret];
        for(int i = 0; i < films.length; i++) {
            if(films[i] != 0)
            ret[j++] = films[i];
        }
        
        return ret;
    }
}
