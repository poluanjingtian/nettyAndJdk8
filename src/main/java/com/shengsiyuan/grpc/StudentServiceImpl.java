package com.shengsiyuan.grpc;

import com.shengsiyuan.proto.*;
import io.grpc.stub.StreamObserver;

import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: renBin
 * @date: 2019/10/7 19:32
 * Description:
 * 使用grpc实现，实现service定义的方法，只需通过下面这种继承抽象类的方式实现
 */
public class StudentServiceImpl extends StudentServiceGrpc.StudentServiceImplBase {
    /**
     * 这里是通过StreamObserver来返回对象
     * @param request 传入的参数对象
     * @param responseObserver 用于返回结果的对象
     */
    @Override
    public void getRealNameByUserName(MyRequest request, StreamObserver<MyResponse> responseObserver) {
        System.out.println("接受客户端信息"+ request.getUsername());
        //开始返回对象（即返回值），对象的构建和protobuf一样
        responseObserver.onNext(MyResponse.newBuilder().setRealname("张三").build());
        responseObserver.onCompleted();

    }

    @Override
    public void getStudentsByAge(StudentRequest request, StreamObserver<StudentResponse> responseObserver) {
        System.out.println("接收到客户端的信息" + request.getAge());
        responseObserver.onNext(StudentResponse.newBuilder().setName("张三").setAge(20).setCity("北京").build());
        responseObserver.onNext(StudentResponse.newBuilder().setName("李四").setAge(30).setCity("天津").build());
        responseObserver.onNext(StudentResponse.newBuilder().setName("王五").setAge(40).setCity("成都").build());
        responseObserver.onNext(StudentResponse.newBuilder().setName("赵六").setAge(50).setCity("深圳").build());
        responseObserver.onCompleted();
    }

    /**
     * 后面两种情况比较复杂，是通过回调函数来完成的
     */

    @Override
    public StreamObserver<StudentRequest> getStudentsWrapperByAges(StreamObserver<StudentResponseList> responseObserver) {
        return new StreamObserver<StudentRequest>() {
            @Override
            public void onNext(StudentRequest value) {
                System.out.println(value.getAge());
            }

            @Override
            public void onError(Throwable t) {
                System.out.println(t.getMessage());
            }

            @Override
            public void onCompleted() {
                StudentResponse student1 = StudentResponse.newBuilder().setName("张三").setAge(20).setCity("深圳").build();
                StudentResponse student2 = StudentResponse.newBuilder().setName("李四").setAge(45).setCity("上海").build();
                StudentResponseList studentList = StudentResponseList.newBuilder().addStudentResponse(student1).addStudentResponse(student2).build();
                responseObserver.onNext(studentList);
                responseObserver.onCompleted();
            }
        };
    }

    @Override
    public StreamObserver<StreamRequest> biTalk(StreamObserver<StreamResponse> responseObserver) {
        return new StreamObserver<StreamRequest>() {
            @Override
            public void onNext(StreamRequest value) {
                System.out.println(value.getRequestInfo());
                responseObserver.onNext(StreamResponse.newBuilder().setResponseInfo(UUID.randomUUID().toString()).build());
            }

            @Override
            public void onError(Throwable t) {
                System.out.println(t.getMessage());
            }

            @Override
            public void onCompleted() {
                responseObserver.onCompleted();
            }
        };
    }
}
