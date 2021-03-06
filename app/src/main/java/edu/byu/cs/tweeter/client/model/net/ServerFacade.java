package edu.byu.cs.tweeter.client.model.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import edu.byu.cs.tweeter.BuildConfig;
import edu.byu.cs.tweeter.model.domain.Tweet;
import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.domain.AuthToken;
import edu.byu.cs.tweeter.model.net.TweeterRemoteException;
import edu.byu.cs.tweeter.model.service.request.FeedTweetsRequest;
import edu.byu.cs.tweeter.model.service.request.FollowerRequest;
import edu.byu.cs.tweeter.model.service.request.FollowingRequest;
import edu.byu.cs.tweeter.model.service.request.FollowingStatusRequest;
import edu.byu.cs.tweeter.model.service.request.LoginRequest;
import edu.byu.cs.tweeter.model.service.request.LogoutRequest;
import edu.byu.cs.tweeter.model.service.request.RegisterRequest;
import edu.byu.cs.tweeter.model.service.request.StoryTweetsRequest;
import edu.byu.cs.tweeter.model.service.request.TweetRequest;
import edu.byu.cs.tweeter.model.service.response.FeedTweetsResponse;
import edu.byu.cs.tweeter.model.service.response.FollowerResponse;
import edu.byu.cs.tweeter.model.service.response.FollowingResponse;
import edu.byu.cs.tweeter.model.service.response.FollowingStatusResponse;
import edu.byu.cs.tweeter.model.service.response.LoginResponse;
import edu.byu.cs.tweeter.model.service.response.LogoutResponse;
import edu.byu.cs.tweeter.model.service.response.RegisterResponse;
import edu.byu.cs.tweeter.model.service.response.StoryTweetsResponse;
import edu.byu.cs.tweeter.model.service.response.TweetResponse;

/**
 * Acts as a Facade to the Tweeter server. All network requests to the server should go through
 * this class.
 */
public class ServerFacade {

    // TODO: Set this to the invoke URL of your API. Find it by going to your API in AWS, clicking
    //  on stages in the right-side menu, and clicking on the stage you deployed your API to.
    private static final String SERVER_URL = "https://136xswxwxa.execute-api.us-west-2.amazonaws.com/dev";

    private final ClientCommunicator clientCommunicator = new ClientCommunicator(SERVER_URL);

    /**
     * Performs a login and if successful, returns the logged in user and an auth token.
     *
     * @param request contains all information needed to perform a login.
     * @return the login response.
     */
    public LoginResponse login(LoginRequest request, String urlPath) throws IOException, TweeterRemoteException {
        LoginResponse response = clientCommunicator.doPost(urlPath, request, null, LoginResponse.class);

        if(response.isSuccess()) {
            return response;
        } else {
            throw new RuntimeException(response.getMessage());
        }
    }

    /**
     * Returns the users that the user specified in the request is following. Uses information in
     * the request object to limit the number of followees returned and to return the next set of
     * followees after any that were returned in a previous request.
     *
     * @param request contains information about the user whose followees are to be returned and any
     *                other information required to satisfy the request.
     * @return the followees.
     */
    public FollowingResponse getFollowees(FollowingRequest request, String urlPath)
            throws IOException, TweeterRemoteException {
        FollowingResponse response = clientCommunicator.doPost(urlPath, request, null, FollowingResponse.class);

        if(response.isSuccess()) {
            return response;
        } else {
            throw new RuntimeException(response.getMessage());
        }
    }



























    // This is the hard coded followee data returned by the 'getFollowees()' method
    private static final String MALE_IMAGE_URL = "https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/donald_duck.png";
    private static final String FEMALE_IMAGE_URL = "https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/daisy_duck.png";

    private final User user1 = new User("Allen", "Anderson", MALE_IMAGE_URL);
    private final User user2 = new User("Amy", "Ames", FEMALE_IMAGE_URL);
    private final User user3 = new User("Bob", "Bobson", MALE_IMAGE_URL);
    private final User user4 = new User("Bonnie", "Beatty", FEMALE_IMAGE_URL);
    private final User user5 = new User("Chris", "Colston", MALE_IMAGE_URL);
    private final User user6 = new User("Cindy", "Coats", FEMALE_IMAGE_URL);
    private final User user7 = new User("Dan", "Donaldson", MALE_IMAGE_URL);
    private final User user8 = new User("Dee", "Dempsey", FEMALE_IMAGE_URL);
    private final User user9 = new User("Elliott", "Enderson", MALE_IMAGE_URL);
    private final User user10 = new User("Elizabeth", "Engle", FEMALE_IMAGE_URL);
    private final User user11 = new User("Frank", "Frandson", MALE_IMAGE_URL);
    private final User user12 = new User("Fran", "Franklin", FEMALE_IMAGE_URL);
    private final User user13 = new User("Gary", "Gilbert", MALE_IMAGE_URL);
    private final User user14 = new User("Giovanna", "Giles", FEMALE_IMAGE_URL);
    private final User user15 = new User("Henry", "Henderson", MALE_IMAGE_URL);
    private final User user16 = new User("Helen", "Hopwell", FEMALE_IMAGE_URL);
    private final User user17 = new User("Igor", "Isaacson", MALE_IMAGE_URL);
    private final User user18 = new User("Isabel", "Isaacson", FEMALE_IMAGE_URL);
    private final User user19 = new User("Justin", "Jones", MALE_IMAGE_URL);
    private final User user20 = new User("Jill", "Johnson", FEMALE_IMAGE_URL);

    private final Tweet tweet1 = new Tweet("@AllenAnderson", "I am Allen! Nice to meet you all!");
    private final Tweet tweet2 = new Tweet("@AmyAmes", "Yooo I'm Amy.");
    private final Tweet tweet3 = new Tweet("@BobBobson", "Welcome to Bob paradise");
    private final Tweet tweet4 = new Tweet("@BonnieBeatty", "I don't have anything to say");
    private final Tweet tweet5 = new Tweet("@ChrisColston", "Motorycles are cool");
    private final Tweet tweet6 = new Tweet("@CindyCoats", "I love physics");
    private final Tweet tweet7 = new Tweet("@DanDonaldson", "Do I look like a duck to you?");
    private final Tweet tweet8 = new Tweet("@DeeDempsey", "Doooo doooooooo doodly dooo");
    private final Tweet tweet9 = new Tweet("@ElliottEnderson", "heyoooooo");
    private final Tweet tweet10 = new Tweet("@ElizabethEngle", "bah blah");
    private final Tweet tweet11 = new Tweet("@FrankFrandson", "frank is dope bro");
    private final Tweet tweet12 = new Tweet("@FranFranklin", "i am rich");
    private final Tweet tweet13 = new Tweet("@GaryGilbert", "i am poor");
    private final Tweet tweet14 = new Tweet("@GiovannaGiles", "i have a good life");
    private final Tweet tweet15 = new Tweet("@HenryHenderson", "one republic yo");
    private final Tweet tweet16 = new Tweet("@HelenHopwell", "Have hope for the future");
    private final Tweet tweet17 = new Tweet("@IgorIsaacson", "You should give this guy 100% because hes a good person");
    private final Tweet tweet18 = new Tweet("@IsabelIsaacson", "I like to eat pizza");
    private final Tweet tweet19 = new Tweet("@JustinJones", "world peace is not possible");
    private final Tweet tweet20 = new Tweet("@JillJohnson", "I am an olympian");

    private final Tweet tweet_S1 = new Tweet("@TestUser", "The greatest glory in living lies not in never falling, but in rising every time we fall.");
    private final Tweet tweet_S2 = new Tweet("@TestUser", "The way to get started is to quit talking and begin doing.");
    private final Tweet tweet_S3 = new Tweet("@TestUser", "Your time is limited, so don't waste it living someone else's life. Don't be trapped by dogma – which is living with the results of other people's thinking.");
    private final Tweet tweet_S4 = new Tweet("@TestUser", "If life were predictable it would cease to be life, and be without flavor.");
    private final Tweet tweet_S5 = new Tweet("@TestUser", "If you look at what you have in life, you'll always have more. If you look at what you don't have in life, you'll never have enough.");
    private final Tweet tweet_S6 = new Tweet("@TestUser", "If you set your goals ridiculously high and it's a failure, you will fail above everyone else's success.");
    private final Tweet tweet_S7 = new Tweet("@TestUser", "Life is what happens when you're busy making other plans.");
    private final Tweet tweet_S8 = new Tweet("@TestUser", "Spread love everywhere you go. Let no one ever come to you without leaving happier.");
    private final Tweet tweet_S9 = new Tweet("@TestUser", "When you reach the end of your rope, tie a knot in it and hang on.");
    private final Tweet tweet_S10 = new Tweet("@TestUser", "Always remember that you are absolutely unique. Just like everyone else.");
    private final Tweet tweet_S11 = new Tweet("@TestUser", "Don't judge each day by the harvest you reap but by the seeds that you plant.");
    private final Tweet tweet_S12 = new Tweet("@TestUser", "The future belongs to those who believe in the beauty of their dreams.");
    private final Tweet tweet_S13 = new Tweet("@TestUser", "Tell me and I forget. Teach me and I remember. Involve me and I learn.");
    private final Tweet tweet_S14 = new Tweet("@TestUser", "The best and most beautiful things in the world cannot be seen or even touched - they must be felt with the heart.");
    private final Tweet tweet_S15 = new Tweet("@TestUser", "Do not go where the path may lead, go instead where there is no path and leave a trail.");
    private final Tweet tweet_S16 = new Tweet("@TestUser", "In the end, it's not the years in your life that count. It's the life in your years.");

    /**
     * Performs a logout and if successful
     *
     * @param request contains all information needed to perform a login.
     * @return the login response.
     */
    public LogoutResponse logout(LogoutRequest request) {
        LogoutResponse logoutResponse;
        // Used in place of assert statements because Android does not support them
        if(BuildConfig.DEBUG) {
            if(request.getUsername() == null) {
                throw new AssertionError();
            }
        }
        if (request.getUsername() != null) {
            logoutResponse = new LogoutResponse(true);
        }
        else {
            logoutResponse = new LogoutResponse(false);
        }
        return new LogoutResponse();
    }


    /**
     * Returns the users that the user specified in the request is following. Uses information in
     * the request object to limit the number of followees returned and to return the next set of
     * followees after any that were returned in a previous request. The current implementation
     * returns generated data and doesn't actually make a network request.
     *
     * @param request contains information about the user whose followees are to be returned and any
     *                other information required to satisfy the request.
     * @return the following response.
     */
    public FollowingResponse getFollowees(FollowingRequest request) {

        // Used in place of assert statements because Android does not support them
        if(BuildConfig.DEBUG) {
            if(request.getLimit() < 0) {
                throw new AssertionError();
            }

            if(request.getFollower() == null) {
                throw new AssertionError();
            }
        }

        List<User> allFollowees = getDummyFollowees();
        List<User> responseFollowees = new ArrayList<>(request.getLimit());

        boolean hasMorePages = false;

        if(request.getLimit() > 0) {
            int followeesIndex = getFolloweesStartingIndex(request.getLastFollowee(), allFollowees);

            for(int limitCounter = 0; followeesIndex < allFollowees.size() && limitCounter < request.getLimit(); followeesIndex++, limitCounter++) {
                responseFollowees.add(allFollowees.get(followeesIndex));
            }

            hasMorePages = followeesIndex < allFollowees.size();
        }

        return new FollowingResponse(responseFollowees, hasMorePages);
    }

    /**
     * Determines the index for the first followee in the specified 'allFollowees' list that should
     * be returned in the current request. This will be the index of the next followee after the
     * specified 'lastFollowee'.
     *
     * @param lastFollowee the last followee that was returned in the previous request or null if
     *                     there was no previous request.
     * @param allFollowees the generated list of followees from which we are returning paged results.
     * @return the index of the first followee to be returned.
     */
    private int getFolloweesStartingIndex(User lastFollowee, List<User> allFollowees) {

        int followeesIndex = 0;

        if(lastFollowee != null) {
            // This is a paged request for something after the first page. Find the first item
            // we should return
            for (int i = 0; i < allFollowees.size(); i++) {
                if(lastFollowee.equals(allFollowees.get(i))) {
                    // We found the index of the last item returned last time. Increment to get
                    // to the first one we should return
                    followeesIndex = i + 1;
                    break;
                }
            }
        }

        return followeesIndex;
    }

    /**
     * Returns the list of dummy followee data. This is written as a separate method to allow
     * mocking of the followees.
     *
     * @return the generator.
     */
    List<User> getDummyFollowees() {
        return Arrays.asList(user1, user2, user3, user4, user5, user6, user7,
                user8, user9, user10, user11, user12, user13, user14, user15, user16, user17, user18,
                user19, user20);
    }




    /**
     * Returns the users that the user specified in the request is following. Uses information in
     * the request object to limit the number of followers returned and to return the next set of
     * followers after any that were returned in a previous request. The current implementation
     * returns generated data and doesn't actually make a network request.
     *
     * @param request contains information about the user whose followers are to be returned and any
     *                other information required to satisfy the request.
     * @return the following response.
     */
    public FollowerResponse getFollowers(FollowerRequest request) {


        // Used in place of assert statements because Android does not support them
        if(BuildConfig.DEBUG) {
            if(request.getLimit() < 0) {
                throw new AssertionError();
            }

            if(request.getFollowee() == null) {
                throw new AssertionError();
            }
        }

        List<User> allFollowers = getDummyFollowers();
        List<User> responseFollowers = new ArrayList<>(request.getLimit());

        boolean hasMorePages = false;

        if(request.getLimit() > 0) {
            int followersIndex = getFolloweesStartingIndex(request.getLastFollower(), allFollowers);

            for(int limitCounter = 0; followersIndex < allFollowers.size() && limitCounter < request.getLimit(); followersIndex++, limitCounter++) {
                responseFollowers.add(allFollowers.get(followersIndex));
            }

            hasMorePages = followersIndex < allFollowers.size();
        }

        return new FollowerResponse(responseFollowers, hasMorePages);
    }

    /**
     * Determines the index for the first follower in the specified 'allFollowers' list that should
     * be returned in the current request. This will be the index of the next follower after the
     * specified 'lastFollower'.
     *
     * @param lastFollower the last follower that was returned in the previous request or null if
     *                     there was no previous request.
     * @param allFollowers the generated list of followers from which we are returning paged results.
     * @return the index of the first follower to be returned.
     */
    private int getFollowersStartingIndex(User lastFollower, List<User> allFollowers) {

        int followersIndex = 0;

        if(lastFollower != null) {
            // This is a paged request for something after the first page. Find the first item
            // we should return
            for (int i = 0; i < allFollowers.size(); i++) {
                if(lastFollower.equals(allFollowers.get(i))) {
                    // We found the index of the last item returned last time. Increment to get
                    // to the first one we should return
                    followersIndex = i + 1;
                    break;
                }
            }
        }

        return followersIndex;
    }

    /**
     * Returns the list of dummy follower data. This is written as a separate method to allow
     * mocking of the followers.
     *
     * @return the generator.
     */
    List<User> getDummyFollowers() {
        return Arrays.asList(user1, user2, user3, user4, user5, user6, user7,
                user8, user9, user10, user11, user12, user13, user14, user15, user16, user17, user18,
                user19, user20);
    }




    /**
     * Returns the users that the user specified in the request is following. Uses information in
     * the request object to limit the number of followers returned and to return the next set of
     * followers after any that were returned in a previous request. The current implementation
     * returns generated data and doesn't actually make a network request.
     *
     * @param request contains information about the tweet are to be returned and any
     *                other information required to satisfy the request.
     * @return the following response.
     */
    public FeedTweetsResponse getFeedTweets(FeedTweetsRequest request) {


        // Used in place of assert statements because Android does not support them
        if(BuildConfig.DEBUG) {
            if(request.getLimit() < 0) {
                throw new AssertionError();
            }

//             the request isn't really important
//            if(request.getTweet() == null) {
//                throw new AssertionError();
//            }
        }

        List<Tweet> allTweets = getDummyTweets();
        List<Tweet> responseTweets = new ArrayList<>(request.getLimit());

        boolean hasMorePages = false;

        if(request.getLimit() > 0) {
            int tweetsIndex = getTweetsStartingIndex(request.getLastTweet(), allTweets);

            for(int limitCounter = 0; tweetsIndex < allTweets.size() && limitCounter < request.getLimit(); tweetsIndex++, limitCounter++) {
                responseTweets.add(allTweets.get(tweetsIndex));
            }

            hasMorePages = tweetsIndex < allTweets.size();
        }

        return new FeedTweetsResponse(responseTweets, hasMorePages);
    }

    /**
     * Determines the index for the first tweet in the specified 'allTweets' list that should
     * be returned in the current request. This will be the index of the next tweet after the
     * specified 'lastTweet'.
     *
     * @param lastTweet the last tweet that was returned in the previous request or null if
     *                     there was no previous request.
     * @param allTweets the generated list of tweets from which we are returning paged results.
     * @return the index of the first tweet to be returned.
     */
    private int getTweetsStartingIndex(Tweet lastTweet, List<Tweet> allTweets) {

        int tweetsIndex = 0;

        if(lastTweet != null) {
            // This is a paged request for something after the first page. Find the first item
            // we should return
            for (int i = 0; i < allTweets.size(); i++) {
                if(lastTweet.equals(allTweets.get(i))) {
                    // We found the index of the last item returned last time. Increment to get
                    // to the first one we should return
                    tweetsIndex = i + 1;
                    break;
                }
            }
        }

        return tweetsIndex;
    }

    /**
     * Returns the list of dummy tweet data. This is written as a separate method to allow
     * mocking of the tweets.
     *
     * @return the generator.
     */
    List<Tweet> getDummyTweets() {
        return Arrays.asList(tweet1, tweet2, tweet3, tweet4, tweet5, tweet6, tweet7, tweet8, tweet9, tweet10, tweet11, tweet12,
                tweet13, tweet14, tweet15, tweet16, tweet17, tweet18, tweet19, tweet20);
    }




    /**
     * Returns the users that the user specified in the request is following. Uses information in
     * the request object to limit the number of followers returned and to return the next set of
     * followers after any that were returned in a previous request. The current implementation
     * returns generated data and doesn't actually make a network request.
     *
     * @param request contains information about the tweet are to be returned and any
     *                other information required to satisfy the request.
     * @return the following response.
     */
    public StoryTweetsResponse getStoryTweets(StoryTweetsRequest request) {


        // Used in place of assert statements because Android does not support them
        if(BuildConfig.DEBUG) {
            if(request.getLimit() < 0) {
                throw new AssertionError();
            }

//             the request isn't really important
//            if(request.getTweet() == null) {
//                throw new AssertionError();
//            }
        }

        List<Tweet> allTweets = getDummyTweetsForStory();
        List<Tweet> responseTweets = new ArrayList<>(request.getLimit());

        boolean hasMorePages = false;

        if(request.getLimit() > 0) {
            int tweetsIndex = getTweetsStartingIndex(request.getLastTweet(), allTweets);

            for(int limitCounter = 0; tweetsIndex < allTweets.size() && limitCounter < request.getLimit(); tweetsIndex++, limitCounter++) {
                responseTweets.add(allTweets.get(tweetsIndex));
            }

            hasMorePages = tweetsIndex < allTweets.size();
        }

        return new StoryTweetsResponse(responseTweets, hasMorePages);
    }

    /**
     * Returns the list of dummy tweet data. This is written as a separate method to allow
     * mocking of the tweets.
     *
     * @return the generator.
     */
    List<Tweet> getDummyTweetsForStory() {
        return Arrays.asList(tweet_S1, tweet_S2, tweet_S3, tweet_S4, tweet_S5, tweet_S6, tweet_S7, tweet_S8, tweet_S9, tweet_S10, tweet_S11, tweet_S12,
                tweet_S13, tweet_S14, tweet_S15, tweet_S16);
    }







    /**
     * Performs a login and if successful, returns the logged in user and an auth token. The current
     * implementation is hard-coded to return a dummy user and doesn't actually make a network
     * request.
     *
     * @param request contains all information needed to perform a login.
     * @return the login response.
     */
    public RegisterResponse register(RegisterRequest request) throws IOException {
        request.getAlias();
        request.getPassword();
        String firstName = request.getFirstName();
        String lastName = request.getLastName();
        String alias = request.getAlias();

        byte[] byteArray = request.getByteArray();


        User user = new User(firstName, lastName, alias,
                "https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/donald_duck.png");


        if(request.getAlias() == null || request.getPassword() == null || request.getFirstName() == null || request.getLastName() == null || request.getByteArray() == null) {
            return new RegisterResponse(null, null);
        }

//        User user = new User(firstName, lastName,
//                "https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/donald_duck.png");

        user.setImageBytes(byteArray);





        // ------------------------------------------------------------------------------------------------------------
        // Create a neat value object to hold the URL
        URL url = new URL("https://136xswxwxa.execute-api.us-west-2.amazonaws.com/dev/registeruser");

        // Open a connection(?) on the URL(??) and cast the response(???)
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("POST");
        // Now it's "open", we can set the request method, headers etc.
        connection.setRequestProperty("?Content-Type", "application/json");
        connection.setRequestProperty("Accept", "application/json");

        connection.setDoOutput(true);

        String jsonInputString = "{\"firstname\": \"Superman\", \"lastname\":\"INSERT A LASTNAME HERE\", \"username\":\"INSERT A USERNAME HERE\", \"password\":\"INSERT A PASSWORD HERE\", \"profilepicture\":\"INSERT AN IMAGE HERE\"}";

        try(OutputStream os = connection.getOutputStream()) {
            byte[] input = jsonInputString.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        try(BufferedReader br = new BufferedReader(
                new InputStreamReader(connection.getInputStream(), "utf-8"))) {
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            System.out.println(response.toString());
        }


        // ------------------------------------------------------------------------------------------------------


//        User user = new User("Test", "User",
//                "https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/donald_duck.png");
        return new RegisterResponse(user, null);
    }



    // POST A TWEET
    public TweetResponse tweet(TweetRequest tweetRequest) {
        tweetRequest.getUsername();
        tweetRequest.getTweetText();
        // Add tweet to database using the username provided


        // Used in place of assert statements because Android does not support them
        if(BuildConfig.DEBUG) {
            if(tweetRequest.getUsername() == null || tweetRequest.getTweetText() == null) {
                throw new AssertionError();
            }
        }

        TweetResponse tweetResponse;

        if(tweetRequest.getTweetText() != null && tweetRequest.getUsername() != null) {
            User user = new User("Test", "User",
                    "https://faculty.cs.byu.edu/~jwilkerson/cs340/tweeter/images/donald_duck.png");
            tweetResponse = new TweetResponse(user);
        }
        else {
            tweetResponse = new TweetResponse((User) null);
        }
        return tweetResponse;
    }



    private Boolean followingStatus = true;

    public FollowingStatusResponse checkFollowingStatus(FollowingStatusRequest followingStatusRequest) {
        followingStatus = followingStatusRequest.getFollowing();
        FollowingStatusResponse followingStatusResponse = new FollowingStatusResponse(followingStatusRequest.getOtherPersonUsername(), followingStatus);
        return followingStatusResponse;
    }
}