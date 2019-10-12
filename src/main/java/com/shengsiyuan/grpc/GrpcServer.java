package com.shengsiyuan.grpc;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: renBin
 * @date: 2019/10/7 19:44
 * Description:
 */
public class GrpcServer {
    private Server server;
    private void start() throws IOException {
        int port = 8899;
        this.server = ServerBuilder.forPort(port)
                .addService(new StudentServiceImpl())
                .build()
                .start();
        System.out.println("server started!");
        /**通过关闭程序，发生中断触发钩子函数。
         * 1.Runtime不能直接new，它是单例的，通过静态方法返回。
         */
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("关闭jvm");
//            GrpcServer.this.stop();
            stop();

        }));
        System.out.println("执行到这里");
    }

    private void stop() {
        if (this.server != null) {
            this.server.shutdown();
        }
    }

    /**
     * Await termination on the main thread since the grpc library uses daemon threads.
     */
    //等待client的连接
    private void blockUntilShutDown() throws InterruptedException {
        if (this.server != null) {
//            this.server.awaitTermination(3000, TimeUnit.MILLISECONDS);
            this.server.awaitTermination();
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        GrpcServer grpcServer = new GrpcServer();
        grpcServer.start();
        grpcServer.blockUntilShutDown();;
    }
}
