package com.example.labb4.VolleyClasses;

public class Url {
    // Default settings


    // https://www.myapifilms.com/imdb/idIMDB?title=Gladiator&token=ef4d3f92-e0df-4646-9f48-b5a116ace7b6&format=json&language=en-us&aka=0&business=0&seasons=0&seasonYear=0&technical=0&filter=3&exactFilter=0&limit=10&forceYear=0&trailers=0&movieTrivia=0&awards=0&moviePhotos=0&movieVideos=0&actors=0&biography=0&uniqueName=0&filmography=0&bornAndDead=0&starSign=0&actorActress=0&actorTrivia=0&similarMovies=0&adultSearch=0&goofs=0&keyword=0&quotes=0&fullSize=0&companyCredits=0&filmingLocations=0
    // https://www.myapifilms.com/imdb/idIMDB?title=Gladiator&token=ef4d3f92-e0df-4646-9f48-b5a116ace7b6&format=json&language=en-us&aka=0&business=0&seasons=0&seasonYear=0&technical=0&filter=2&exactFilter=0&limit=10&forceYear=0&trailers=0&movieTrivia=0&awards=0&moviePhotos=0&movieVideos=0&actors=0&biography=0&uniqueName=0&filmography=0&bornAndDead=0&starSign=0&actorActress=0&actorTrivia=0&similarMovies=0&adultSearch=0&goofs=0&keyword=0&quotes=0&fullSize=0&companyCredits=0&filmingLocations=0

    private String baseUrl = "https://www.myapifilms.com/imdb/idIMDB?";
    private String movieTitle = "title=Name";
    private String token = "&token=ef4d3f92-e0df-4646-9f48-b5a116ace7b6";
    private String format = "&format=json";
    private String language = "&language=en-us";
    private String aka = "&aka=0";
    private String business = "&business=0";
    private String seasons = "&seasons=0";
    private String seasonYear = "&seasonYear=0";
    private String technical = "&technical=0";
    private String filter = "&filter=3"; // 3 = Movies
    private String exactFilter = "&exactFilter=0";
    private String limit = "&limit=10";
    private String forceYear = "&forceYear=0";
    private String trailers = "&trailers=0";
    private String movieTrivia = "&movieTrivia=0";
    private String awards = "&awards=0";
    private String moviePhotos = "&moviePhotos=0";
    private String movieVideos = "&movieVideos=0";
    private String actors = "&actors=0";
    private String biography = "&biography=0";
    private String uniqueName = "&uniqueName=0";
    private String filmography = "&filmography=0";
    private String bornAndDead = "&bornAndDead=0";
    private String starSign = "&starSign=0";
    private String actorActress = "&actorActress=0";
    private String actorTrivia = "&actorTrivia=0";
    private String similarMovies = "&similarMovies=0";
    private String adultSearch = "&adultSearch=0";
    private String goofs = "&goofs=0";
    private String keyword = "&keyword=0";
    private String quotes = "&quotes=0";
    private String fullSize = "&fullSize=0";
    private String companyCredits = "&companyCredits=0";
    private String filmingLocations = "&filmingLocations=0";

    public Url(){

    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        movieTitle.replaceAll(" ", "+");
        this.movieTitle = "title=";
        this.movieTitle = this.movieTitle.concat(movieTitle);
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getAka() {
        return aka;
    }

    public void setAka(String aka) {
        this.aka = aka;
    }

    public String getBusiness() {
        return business;
    }

    public void setBusiness(String business) {
        this.business = business;
    }

    public String getSeasons() {
        return seasons;
    }

    public void setSeasons(String seasons) {
        this.seasons = seasons;
    }

    public String getSeasonYear() {
        return seasonYear;
    }

    public void setSeasonYear(String seasonYear) {
        this.seasonYear = seasonYear;
    }

    public String getTechnical() {
        return technical;
    }

    public void setTechnical(String technical) {
        this.technical = technical;
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    public String getExactFilter() {
        return exactFilter;
    }

    public void setExactFilter(String exactFilter) {
        this.exactFilter = exactFilter;
    }

    public String getLimit() {
        return limit;
    }

    public void setLimit(String limit) {
        this.limit = "&limit=";
        this.limit = this.limit.concat(limit);
    }

    public String getForceYear() {
        return forceYear;
    }

    public void setForceYear(String forceYear) {
        this.forceYear = forceYear;
    }

    public String getTrailers() {
        return trailers;
    }

    public void setTrailers(String trailers) {
        this.trailers = trailers;
    }

    public String getMovieTrivia() {
        return movieTrivia;
    }

    public void setMovieTrivia(String movieTrivia) {
        this.movieTrivia = movieTrivia;
    }

    public String getAwards() {
        return awards;
    }

    public void setAwards(String awards) {
        this.awards = awards;
    }

    public String getMoviePhotos() {
        return moviePhotos;
    }

    public void setMoviePhotos(String moviePhotos) {
        this.moviePhotos = moviePhotos;
    }

    public String getMovieVideos() {
        return movieVideos;
    }

    public void setMovieVideos(String movieVideos) {
        this.movieVideos = movieVideos;
    }

    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public String getUniqueName() {
        return uniqueName;
    }

    public void setUniqueName(String uniqueName) {
        this.uniqueName = uniqueName;
    }

    public String getFilmography() {
        return filmography;
    }

    public void setFilmography(String filmography) {
        this.filmography = filmography;
    }

    public String getBornAndDead() {
        return bornAndDead;
    }

    public void setBornAndDead(String bornAndDead) {
        this.bornAndDead = bornAndDead;
    }

    public String getStarSign() {
        return starSign;
    }

    public void setStarSign(String starSign) {
        this.starSign = starSign;
    }

    public String getActorActress() {
        return actorActress;
    }

    public void setActorActress(String actorActress) {
        this.actorActress = actorActress;
    }

    public String getActorTrivia() {
        return actorTrivia;
    }

    public void setActorTrivia(String actorTrivia) {
        this.actorTrivia = actorTrivia;
    }

    public String getSimilarMovies() {
        return similarMovies;
    }

    public void setSimilarMovies(String similarMovies) {
        this.similarMovies = "&similarMovies=";
        this.similarMovies = this.similarMovies.concat(similarMovies);
    }

    public String getAdultSearch() {
        return adultSearch;
    }

    public void setAdultSearch(String adultSearch) {
        this.adultSearch = adultSearch;
    }

    public String getGoofs() {
        return goofs;
    }

    public void setGoofs(String goofs) {
        this.goofs = goofs;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getQuotes() {
        return quotes;
    }

    public void setQuotes(String quotes) {
        this.quotes = quotes;
    }

    public String getFullSize() {
        return fullSize;
    }

    public void setFullSize(String fullSize) {
        this.fullSize = fullSize;
    }

    public String getCompanyCredits() {
        return companyCredits;
    }

    public void setCompanyCredits(String companyCredits) {
        this.companyCredits = companyCredits;
    }

    public String getFilmingLocations() {
        return filmingLocations;
    }

    public void setFilmingLocations(String filmingLocations) {
        this.filmingLocations = filmingLocations;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }
    public String ConstructCompleteUrl(){
        baseUrl = baseUrl.concat(movieTitle);
        baseUrl = baseUrl.concat(token);
        baseUrl = baseUrl.concat(format);
        baseUrl = baseUrl.concat(language);
        baseUrl = baseUrl.concat(aka);
        baseUrl = baseUrl.concat(business);
        baseUrl = baseUrl.concat(seasons);
        baseUrl = baseUrl.concat(seasonYear);
        baseUrl = baseUrl.concat(technical);
        baseUrl = baseUrl.concat(filter);
        baseUrl = baseUrl.concat(exactFilter);
        baseUrl = baseUrl.concat(limit);
        baseUrl = baseUrl.concat(forceYear);
        baseUrl = baseUrl.concat(trailers);
        baseUrl = baseUrl.concat(movieTrivia);
        baseUrl = baseUrl.concat(awards);
        baseUrl = baseUrl.concat(moviePhotos);
        baseUrl = baseUrl.concat(movieVideos);
        baseUrl = baseUrl.concat(actors);
        baseUrl = baseUrl.concat(biography);
        baseUrl = baseUrl.concat(uniqueName);
        baseUrl = baseUrl.concat(filmography);
        baseUrl = baseUrl.concat(bornAndDead);
        baseUrl = baseUrl.concat(starSign);
        baseUrl = baseUrl.concat(actorActress);
        baseUrl = baseUrl.concat(actorTrivia);
        baseUrl = baseUrl.concat(similarMovies);
        baseUrl = baseUrl.concat(adultSearch);
        baseUrl = baseUrl.concat(goofs);
        baseUrl = baseUrl.concat(keyword);
        baseUrl = baseUrl.concat(quotes);
        baseUrl = baseUrl.concat(fullSize);
        baseUrl = baseUrl.concat(companyCredits);
        baseUrl = baseUrl.concat(filmingLocations);

        return baseUrl;
    }
}
