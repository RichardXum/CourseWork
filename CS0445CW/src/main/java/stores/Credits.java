package stores;

import structures.*;
import interfaces.ICredits;

public class Credits implements ICredits{
    Stores stores;

    /**
     * The constructor for the Credits data store. This is where you should
     * initialise your data structures.
     * 
     * @param stores An object storing all the different key stores, 
     *               including itself
     */

    HashTable<Integer, CastCredit[]> Cast;
    HashTable<Integer, CrewCredit[]> Crew;
    HashTable<Integer, Integer> Id;
    HashTable<Integer, Integer> FilmID;
    HashTable<Integer, Integer> CastID;
    HashTable<Integer, Integer> CrewID;

    public Credits (Stores stores) {
        this.stores = stores;
        Cast = new HashTable<>(1000);
        Crew = new HashTable<>(1000);
        Id = new HashTable<>(1000);
        FilmID = new HashTable<>(1000);
        CastID = new HashTable<>(1000);
        CrewID = new HashTable<>(1000);
        // TODO Add initialisation of data structure here
    }

    /**
     * Adds data about the people who worked on a given film. The movie ID should be
     * unique
     * 
     * @param cast An array of all cast members that starred in the given film
     * @param crew An array of all crew members that worked on a given film
     * @param id   The (unique) movie ID
     * @return TRUE if the data able to be added, FALSE otherwise
     */
    @Override
    public boolean add(CastCredit[] cast, CrewCredit[] crew, int id) {
        // TODO Implement this function
        if (Id.get(id) != null) {
            return false;
        }else{
            Cast.put(id, cast);
            Crew.put(id, crew);
            Id.put(id, id);
            return true;
        }
    }

    /**
     * Remove a given films data from the data structure
     * 
     * @param id The movie ID
     * @return TRUE if the data was removed, FALSE otherwise
     */
    @Override
    public boolean remove(int id) {
        // TODO Implement this function
        if (Id.get(id)!=null) {
            Id.remove(id);
            Cast.remove(id);
            Crew.remove(id);
            return true;
        }
        return false;
    }

    /**
     * Gets all the cast members for a given film
     * 
     * @param filmID The movie ID
     * @return An array of CastCredit objects, one for each member of cast that is 
     *         in the given film. The cast members should be in "order" order. If
     *         there is no cast members attached to a film, or the film canot be 
     *         found, then return an empty array
     */
    @Override
    public CastCredit[] getFilmCast(int filmID) {
        // TODO Implement this function
        
        if (Id.get(filmID) == null) {
            CastCredit[] non = new CastCredit[0];
            return non;
        }

        CastCredit[] res = Cast.get(filmID);
        return res;
    }

    /**
     * Gets all the crew members for a given film
     * 
     * @param filmID The movie ID
     * @return An array of CrewCredit objects, one for each member of crew that is
     *         in the given film. The crew members should be in ID order. If there 
     *         is no crew members attached to a film, or the film canot be found, 
     *         then return an empty array
     */
    @Override
    public CrewCredit[] getFilmCrew(int filmID) {
        // TODO Implement this function
        if (Id.get(filmID) == null) {
            CrewCredit[] non = new CrewCredit[0];
            return non;
        }

        CrewCredit[] res = Crew.get(filmID);
        return res;
    }

    /**
     * Gets the number of cast that worked on a given film
     * 
     * @param filmID The movie ID
     * @return The number of cast member that worked on a given film. If the film
     *         cannot be found, then return -1
     */
    @Override
    public int sizeOfCast(int filmID) {
        // TODO Implement this function
        if (Id.get(filmID) != null) {
            return Cast.size();
        }
        return -1;
    }

    /**
     * Gets the number of crew that worked on a given film
     * 
     * @param filmID The movie ID
     * @return The number of crew member that worked on a given film. If the film
     *         cannot be found, then return -1
     */
    @Override
    public int sizeofCrew(int filmID) {
        // TODO Implement this function
        if (Id.get(filmID) != null) {
            return Crew.size();
        }
        return -1;
    }

    /**
     * Gets the number of films stored in this data structure
     * 
     * @return The number of films in the data structure
     */
    @Override
    public int size() {
        // TODO Implement this function
        return Id.size();
    }

    /**
     * Gets a list of all unique cast members present in the data structure
     * 
     * @return An array of all unique cast members as Person objects. If there are 
     *         no cast members, then return an empty array
     */
    @Override
    public Person[] getUniqueCast() {
        // TODO Implement this function
    int[] ids = Id.traverseI();

    Person[] list = new Person[1000];
    int len = 0;

    for (int i = 0; i < ids.length; i++) {
        CastCredit[] cast = Cast.get(ids[i]);
        
        for (int j = 0; j < cast.length; j++) {
            String name = cast[j].getName();
            int id = cast[j].getID();
            String path = cast[j].getProfilePath();
            
            list[len++] = new Person(id, name, path);
        }
    }
    
    Person[] uniqueList = new Person[1000];
    int uniqueLen = 0;

    for(int i = 0; i < len; i++){
        boolean isUnique = true;

        for (int j = 0; j < uniqueLen; j++) {
            if (list[i].getName().equals(uniqueList[j].getName())) {
                isUnique = false;
                break;
            }
        }
        
        if (isUnique) {
            uniqueList[uniqueLen++] = list[i];
        }
    }

    Person[] finalList = new Person[uniqueLen];
    System.arraycopy(uniqueList, 0, finalList, 0, uniqueLen);

    return finalList;
    }

    /**
     * Gets a list of all unique crew members present in the data structure
     * 
     * @return An array of all unique crew members as Person objects. If there are
     *         no crew members, then return an empty array
     */
    @Override
    public Person[] getUniqueCrew() {
        // TODO Implement this function

        int[] ids = Id.traverseI();

    Person[] list = new Person[1000];
    int len = 0;

    for (int i = 0; i < ids.length; i++) {
        CrewCredit[] crew = Crew.get(ids[i]);
        
        for (int j = 0; j < crew.length; j++) {
            String name = crew[j].getName();
            int id = crew[j].getID();
            String path = crew[j].getProfilePath();
            
            list[len++] = new Person(id, name, path);
        }
    }
    
    Person[] uniqueList = new Person[1000];
    int uniqueLen = 0;

    for(int i = 0; i < len; i++){
        boolean isUnique = true;

        for (int j = 0; j < uniqueLen; j++) {
            if (list[i].getName().equals(uniqueList[j].getName())) {
                isUnique = false;
                break;
            }
        }
        
        if (isUnique) {
            uniqueList[uniqueLen++] = list[i];
        }
    }

    Person[] finalList = new Person[uniqueLen];
    System.arraycopy(uniqueList, 0, finalList, 0, uniqueLen);

    return finalList;
    }

    /**
     * Get all the cast members that have the given string within their name
     * 
     * @param cast The string that needs to be found
     * @return An array of unique Person objects of all cast members that have the 
     *         requested string in their name
     */
    @Override
    public Person[] findCast(String cast) {
        // TODO Implement this function
        Person[] Casts = getUniqueCast();
        Person[] list = new Person[1000];

        int len = 0;
        for (int i = 0; i < Casts.length; i++) {
            if (Casts[i].getName().contains(cast)) {
                String name = Casts[i].getName();
                int id = Casts[i].getID();
                String path = Casts[i].getProfilePath();
                list[len++] = new Person(id, name, path);
            }
        }

        Person[] ret = new Person[len];
        for (int i = 0; i < len; i++) {
            ret[i] = list[i];
        }
        
        return ret;
    }

    /**
     * Get all the crew members that have the given string within their name
     * 
     * @param crew The string that needs to be found
     * @return An array of unique Person objects of all crew members that have the 
     *         requested string in their name
     */
    @Override
    public Person[] findCrew(String crew) {
        // TODO Implement this function
        Person[] Crews = getUniqueCrew();
        Person[] list = new Person[1000];

        int len = 0;
        for (int i = 0; i < Crews.length; i++) {
            if (Crews[i].getName().contains(crew)) {
                String name = Crews[i].getName();
                int id = Crews[i].getID();
                String path = Crews[i].getProfilePath();
                list[len++] = new Person(id, name, path);
            }
        }

        Person[] ret = new Person[len];
        for (int i = 0; i < len; i++) {
            ret[i] = list[i];
        }
        
        return ret;
    }

    /**
     * Gets the Person object corresponding to the cast ID
     * 
     * @param castID The cast ID of the person to be found
     * @return The Person object corresponding to the cast ID provided. 
     *         If a person cannot be found, then return null
     */
    @Override
    public Person getCast(int castID) {
        // TODO Implement this function
        Person[] Casts = getUniqueCast();

        if (Casts == null) {
            return null;
        }
        Person ret;
        for (int i = 0; i < Casts.length; i++) {
            if (Casts[i] != null && Casts[i].getID() == castID) {
                String name = Casts[i].getName();
                int id = Casts[i].getID();
                String path = Casts[i].getProfilePath();
                ret = new Person(id, name, path);

                return ret;
            }
        }

        return null;
    }

    /**
     * Gets the Person object corresponding to the crew ID
     * 
     * @param crewID The crew ID of the person to be found
     * @return The Person object corresponding to the crew ID provided. 
     *         If a person cannot be found, then return null
     */
    @Override
    public Person getCrew(int crewID){
        // TODO Implement this function

        Person[] Crews = getUniqueCrew();

        if (Crews == null) {
            return null;
        }
        Person ret;
        for (int i = 0; i < Crews.length; i++) {
            if (Crews[i].getID() == crewID) {
                String name = Crews[i].getName();
                int id = Crews[i].getID();
                String path = Crews[i].getProfilePath();
                ret = new Person(id, name, path);

                return ret;
            }
        }

        return null;
    }

    
    /**
     * Get an array of film IDs where the cast member has starred in
     * 
     * @param castID The cast ID of the person
     * @return An array of all the films the member of cast has starred
     *         in. If there are no films attached to the cast member, 
     *         then return an empty array
     */
    @Override
    public int[] getCastFilms(int castID){
        // TODO Implement this function

        Person[] Casts = getUniqueCast();

        String path = "";
        for (int i = 0; i < Casts.length; i++) {
            if (Casts[i].getID() == castID) {
                path = Casts[i].getProfilePath();
            }
        }

        if (path == "") {
            return new int[0];
        }

        int[] AllCasts = new int[1000];
        int[] ids = Id.traverseI();
        
        int len = 0;
        for (int i = 0; i < ids.length; i++) {
            CastCredit[] case_1 = getFilmCast(ids[i]);
            for (int j = 0; j < case_1.length; j++) {
                if(case_1[i].getProfilePath() == path){
                    AllCasts[len++] = case_1[i].getID();
            }
        }
    }

        int[] fmids = new int[len];
        for (int j = 0; j < len; j++) {
            fmids[j] = AllCasts[j];
        }

        return fmids;
    }

    /**
     * Get an array of film IDs where the crew member has starred in
     * 
     * @param crewID The crew ID of the person
     * @return An array of all the films the member of crew has starred
     *         in. If there are no films attached to the crew member, 
     *         then return an empty array
     */
    @Override
    public int[] getCrewFilms(int crewID) {
        // TODO Implement this function
        Person[] Crews = getUniqueCrew();

        String path = "";
        for (int i = 0; i < Crews.length; i++) {
            if (Crews[i].getID() == crewID) {
                path = Crews[i].getProfilePath();
            }
        }

        if (path == "") {
            return new int[0];
        }

        int[] AllCrews = new int[1000];
        int[] ids = Id.traverseI();
        
        int len = 0;
        for (int i = 0; i < ids.length; i++) {
            CrewCredit[] case_1 = getFilmCrew(ids[i]);
            for (int j = 0; j < case_1.length; j++) {
                if(case_1[i].getProfilePath() == path){
                    AllCrews[len++] = case_1[i].getID();
            }
        }
    }

        int[] fmids = new int[len];
        for (int j = 0; j < len; j++) {
            fmids[j] = AllCrews[j];
        }

        return fmids;
    }

    /**
     * Get the films that this cast member stars in (in the top 3 cast
     * members/top 3 billing). This is determined by the order field in
     * the CastCredit class
     * 
     * @param castID The cast ID of the cast member to be searched for
     * @return An array of film IDs where the the cast member stars in.
     *         If there are no films where the cast member has starred in,
     *         or the cast member does not exist, return an empty array
     */
    @Override
    public int[] getCastStarsInFilms(int castID){
        // TODO Implement this function
        int[] fmids = getCastFilms(castID);

        int[] exist = new int[1000];
        for (int i = 0; i < fmids.length; i++) {
            CastCredit[] person = getFilmCast(fmids[i]);
            
        }

        return null;
    }
    
    /**
     * Get Person objects for cast members who have appeared in the most
     * films. If the cast member has multiple roles within the film, then
     * they would get a credit per role played. For example, if a cast
     * member performed as 2 roles in the same film, then this would count
     * as 2 credits. The list should be ordered by the highest number of credits.
     * 
     * @param numResults The maximum number of elements that should be returned
     * @return An array of Person objects corresponding to the cast members
     *         with the most credits, ordered by the highest number of credits.
     *         If there are less cast members that the number required, then the
     *         list should be the same number of cast members found.
     */
    @Override
    public Person[] getMostCastCredits(int numResults) {
        // TODO Implement this function
        return null;
    }

    /**
     * Get the number of credits for a given cast member. If the cast member has
     * multiple roles within the film, then they would get a credit per role
     * played. For example, if a cast member performed as 2 roles in the same film,
     * then this would count as 2 credits.
     * 
     * @param castID A cast ID representing the cast member to be found
     * @return The number of credits the given cast member has. If the cast member
     *         cannot be found, return -1
     */
    @Override
    public int getNumCastCredits(int castID) {
        // TODO Implement this function
        return -1;
    }

}
