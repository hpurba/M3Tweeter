package edu.byu.cs.tweeter.server.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import edu.byu.cs.tweeter.model.service.request.RegisterRequest;
import edu.byu.cs.tweeter.model.service.response.RegisterResponse;
import edu.byu.cs.tweeter.server.service.LoginServiceImpl;

//public class RegisterHandler implements RequestHandler<RegisterRequest, RegisterResponse> {
////    @Override
////    public RegisterResponse handleRequest(RegisterRequest registerRequest, Context context) {
////        RegisterServiceImpl registerService = new RegisterServiceImpl();
////        return registerRequest.register(registerRequest);
////    }
//}