package edu.byu.cs.tweeter.client.model.service;

import java.io.IOException;


import edu.byu.cs.tweeter.client.model.net.ServerFacade;
import edu.byu.cs.tweeter.model.service.request.FeedTweetsRequest;
import edu.byu.cs.tweeter.model.service.response.FeedTweetsResponse;

public class FeedTweetsService {

    public FeedTweetsResponse getFeedTweets(FeedTweetsRequest request) throws IOException {
        FeedTweetsResponse response = getServerFacade().getFeedTweets(request);

        if(response.isSuccess()) {
            //
        }
        return response;
    }


    /**
     * Returns an instance of {@link ServerFacade}. Allows mocking of the ServerFacade class for
     * testing purposes. All usages of ServerFacade should get their ServerFacade instance from this
     * method to allow for proper mocking.
     *
     * @return the instance.
     */
    ServerFacade getServerFacade() {
        return new ServerFacade();
    }
}
