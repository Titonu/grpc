package com.tito.github.grpcgradle;

import com.proto.greet.GreetRequest;
import com.proto.greet.GreetResponse;
import com.proto.greet.GreetServiceGrpc;
import com.proto.greet.Greeting;
import io.grpc.stub.StreamObserver;

public class GreetServiceImpl extends GreetServiceGrpc.GreetServiceImplBase {
    @Override
    public void greet(GreetRequest request, StreamObserver<GreetResponse> responseObserver) {
        //Extract request field we need
        Greeting greeting = request.getGreeting();
        String firstName = greeting.getFirstName();
        String lastName = greeting.getLastName();

        //Create the result response
        String result = "Hai bro" + firstName + " " + lastName;

        //Send the response
        GreetResponse greetResponse = GreetResponse.newBuilder()
                .setResult(result)
                .build();
        responseObserver.onNext(greetResponse);


        responseObserver.onCompleted();
    }
}
