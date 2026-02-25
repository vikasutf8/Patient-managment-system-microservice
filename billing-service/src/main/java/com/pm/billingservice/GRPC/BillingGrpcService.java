package com.pm.billingservice.GRPC;

import billing.*;
import billing.BillingServiceGrpc.BillingServiceImplBase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import net.devh.boot.grpc.server.service.GrpcService;


@GrpcService
public class BillingGrpcService  extends BillingServiceGrpc.BillingServiceImplBase {
    private static final Logger log = LoggerFactory.getLogger(BillingGrpcService.class);

    @Override
    public void createBillingService(BillingRequest request,
                                     io.grpc.stub.StreamObserver<BillingResponse> responseObserver) {
        // simple placeholder implementation
        log.info("Received billing request for patientId: {}", request.toString());
        BillingResponse response = BillingResponse.newBuilder()
                .setAccountId("acct-" + request.getPatientId())
                .setStatus("CREATED")
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted(); 
    }
}
