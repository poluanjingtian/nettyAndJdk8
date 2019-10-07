package com.shengsiyuan.netty.protobuf;

import com.google.protobuf.InvalidProtocolBufferException;

/**
 * Created with IntelliJ IDEA.
 *
 * @author: renBin
 * @date: 2019/10/4 22:29
 * Description:
 */
public class ProtoBufTest {
    public static void main(String[] args) throws InvalidProtocolBufferException {
        MyDataInfo.Student student = MyDataInfo.Student.newBuilder()
                .setId(1)
                .setName("pick")
                .setAddress("广州")
                .build();
        byte[] sb = student.toByteArray();
        MyDataInfo.Student student1 = MyDataInfo.Student.parseFrom(sb);
        System.out.println(student1.getName() + " " + student1.getId() + " " + student1.getAddress());
    }
}
