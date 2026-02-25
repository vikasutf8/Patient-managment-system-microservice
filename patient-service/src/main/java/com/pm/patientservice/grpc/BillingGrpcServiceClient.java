package com.pm.patientservice.grpc;

// Import the generated gRPC classes from the billing proto
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import billing.*;
import com.google.api.Billing;

/**
 * gRPC client for the billing service.  
 * The generated `BillingServiceGrpc` stub lives in the same package
 * (adjust the import below if your proto uses a different java_package).
 */
@Service
public class BillingGrpcServiceClient {

    private final BillingServiceGrpc.BillingServiceBlockingStub billingStub;
    private static final Logger log = LoggerFactory.getLogger(BillingGrpcServiceClient.class);

    public BillingGrpcServiceClient(
            @Value("${billing.service.address:localhost}") String serverAddress,
            @Value("${billing.service.port:9091}") int serverPort) {

        log.info("Creating billing service client with server address: {}", serverAddress);
        log.info("Creating billing service client with server port: {}", serverPort);

        ManagedChannel channel = ManagedChannelBuilder.forAddress(serverAddress, serverPort)
                                                     .usePlaintext()
                                                     .build();
        billingStub = BillingServiceGrpc.newBlockingStub(channel);
    }

    // …methods that use billingStub…
    public BillingResponse createBillingForPatient(String patientId,String name, String email) {
        BillingRequest request = BillingRequest.newBuilder()
                                             .setPatientId(patientId)
                                             .setName(name)
                                             .setEmail(email)
                                             .build();
        log.info("Sending billing request for patientId: {}", patientId);
        BillingResponse response = billingStub.createBillingService(request);
        log.info("Received billing response: {}", response.toString());
        return response;
    }

}
