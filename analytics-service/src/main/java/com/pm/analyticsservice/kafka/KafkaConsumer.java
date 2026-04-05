package com.pm.analyticsservice.kafka;

import com.google.protobuf.InvalidProtocolBufferException;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import patient.event.PatientEvent;

@Service
public class KafkaConsumer
{

    @PostConstruct
    public void init() {
        System.out.println("🚀 Kafka Consumer STARTED");
    }
    private static final Logger log = LoggerFactory.getLogger(KafkaConsumer.class);

    @KafkaListener(topics = "patient", groupId ="analytics-service")
    public void consumeEvent(byte[] event)
    {
        log.info("🔥 RAW message received");
        try {
            PatientEvent patientEvent = PatientEvent.parseFrom(event);
            //.. perform business logic

            log.info("Received patient event: [patientId={}, patientName={},patientEmail={}]", patientEvent.getPatientId(),patientEvent.getName(),patientEvent.getEmail() );

        } catch (InvalidProtocolBufferException e) {
            log.error("Error deserilization event {}",e.getMessage());
        }

    }
}
