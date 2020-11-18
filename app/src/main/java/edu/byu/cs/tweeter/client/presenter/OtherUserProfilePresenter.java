package edu.byu.cs.tweeter.client.presenter;

import java.io.IOException;

import edu.byu.cs.tweeter.client.model.service.FollowingStatusService;
import edu.byu.cs.tweeter.model.service.request.FollowingStatusRequest;
import edu.byu.cs.tweeter.model.service.response.FollowingStatusResponse;

public class OtherUserProfilePresenter {


    private final View view;

    /**
     * The interface by which this presenter communicates with it's view.
     */
    public interface View {
        // If needed, specify methods here that will be called on the view in response to model updates
    }

    /**
     * Creates an instance.
     *
     * @param view the view for which this class is the presenter.
     */
    public OtherUserProfilePresenter(View view) {
        this.view = view;
    }

    /**
     * Returns the users that the user specified in the request is following. Uses information in
     * the request object to limit the number of followees returned and to return the next set of
     * followees after any that were returned in a previous request.
     *
     * @param request contains the data required to fulfill the request.
     * @return the followees.
     */
    public FollowingStatusResponse getFollowingStatus(FollowingStatusRequest request) throws IOException {
        FollowingStatusService followingStatusService = getFollowingStatusService();
        return followingStatusService.getFollowingStatus(request);
    }

    /**
     * Returns an instance of {@link FollowerService}. Allows mocking of the FollowerService class
     * for testing purposes. All usages of FollowerService should get their FollowerService
     * instance from this method to allow for mocking of the instance.
     *
     * @return the instance.
     */
    FollowingStatusService getFollowingStatusService() {
        return new FollowingStatusService();
    }
}
