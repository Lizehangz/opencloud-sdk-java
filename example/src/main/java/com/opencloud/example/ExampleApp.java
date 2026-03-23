package com.opencloud.example;

import com.opencloud.sdk.OpenCloudClient;
import com.opencloud.sdk.OpenCloudClientConfig;
import com.opencloud.sdk.auth.BasicAuthProvider;
import com.opencloud.sdk.graph.model.CollectionResponse;
import com.opencloud.sdk.graph.model.Drive;
import com.opencloud.sdk.graph.model.User;

public class ExampleApp {
    public static void main(String[] args) throws Exception {
        OpenCloudClient client = OpenCloudClient.create(
            OpenCloudClientConfig.builder()
                .baseUrl("https://localhost:9200")
                .authProvider(new BasicAuthProvider("admin", "YOUR_PASSWORD"))
                // Preferred if you package a trust store under src/main/resources/certs/opencloud-truststore.jks
                .trustStoreResource("certs/opencloud-truststore.jks", "changeit")
                // For local development only. Uncomment if you do not want to manage certificates yet.
                // .insecureSkipTlsVerification(true)
                .build()
        );

        User me = client.graph().me().getProfileModel().getBody();
        CollectionResponse<Drive> drives = client.graph().drives().listModel().getBody();

        System.out.println("Current user: " + me);
        System.out.println("Drives returned: " + (drives != null && drives.getValue() != null ? drives.getValue().size() : 0));
    }
}
