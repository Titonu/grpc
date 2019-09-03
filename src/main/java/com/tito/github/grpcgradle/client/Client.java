package com.tito.github.grpcgradle.client;

import com.proto.greet.GreetRequest;
import com.proto.greet.GreetResponse;
import com.proto.greet.GreetServiceGrpc;
import com.proto.greet.Greeting;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class Client {
    public static void main(String[] args) {
        System.out.println("Hi iam client");

        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50051)
                .usePlaintext()
                .build();
//        DummyServiceGrpc.DummyServiceBlockingStub syncClient = DummyServiceGrpc.newBlockingStub(channel);

        //Sync service to greet service
        GreetServiceGrpc.GreetServiceBlockingStub greetService = GreetServiceGrpc.newBlockingStub(channel);
        Greeting greeting = Greeting.newBuilder()
                .setFirstName("Aditya")
                .setLastName("Tito")
                .build();
        GreetRequest greetRequest = GreetRequest.newBuilder()
                .setGreeting(greeting)
                .build();

        //sending an request and return a response
        GreetResponse greetResponse = greetService.greet(greetRequest);
        System.out.println(greetResponse.getResult());

        System.out.println("shutting down channel");
        channel.shutdown();
    }
}
