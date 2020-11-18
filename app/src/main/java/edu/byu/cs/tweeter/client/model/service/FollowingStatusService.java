package edu.byu.cs.tweeter.client.model.service;

import java.io.IOException;

import edu.byu.cs.tweeter.client.model.net.ServerFacade;
import edu.byu.cs.tweeter.model.service.request.FollowingStatusRequest;
import edu.byu.cs.tweeter.model.service.response.FollowingStatusResponse;


public class FollowingStatusService {

    public FollowingStatusResponse getFollowingStatus(FollowingStatusRequest request) throws IOException {
        ServerFacade serverFacade = getServerFacade();
        FollowingStatusResponse followingStatusResponse = serverFacade.checkFollowingStatus(request);

        if(followingStatusResponse.isSuccess()) {

        }

        return followingStatusResponse;
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
