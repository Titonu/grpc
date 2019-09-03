package com.tito.github.grpcgradle.server;

import com.tito.github.grpcgradle.GreetServiceImpl;
import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class ServerGreeting {
    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("Hi world");

        Server server = ServerBuilder.forPort(50051)
                .addService(new GreetServiceImpl())
                .build();
        server.start();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Menerima request untuk mematikan server");

            server.shutdown();
            System.out.println("Server berhasil dimatikan");
        }));

        server.awaitTermination();
    }
}
