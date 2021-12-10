package de.novatec.bpm.controller;

import de.novatec.bpm.model.Reservation;
import io.camunda.zeebe.client.ZeebeClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static de.novatec.bpm.process.ProcessMessage.SEATS_VERIFIED;

@RestController
public class CinemaController {

    private final ZeebeClient zeebeClient;
    private final Logger logger = LoggerFactory.getLogger(CinemaController.class);

    public CinemaController(ZeebeClient zeebeClientLifecycle) {
        this.zeebeClient = zeebeClientLifecycle;
    }

    @PostMapping("/reservation")
    public ResponseEntity<String> reserveSeat(@RequestBody Reservation reservation) {
        String reservationId = "RESERVATION-" + UUID.randomUUID();
        reservation.setReservationId(reservationId);
        zeebeClient.newCreateInstanceCommand().bpmnProcessId("bpmn-cinema-ticket-reservation")
                .latestVersion()
                .variables(reservation)
                .send()
                .join();
        logger.info("Reservation issued: " + reservationId);
        return new ResponseEntity<>("Reservation issued: " + reservationId, HttpStatus.ACCEPTED);
    }

    @GetMapping("/reservation/offer/{id}")
    public ResponseEntity<String> acceptOffer(@PathVariable String id) {
        zeebeClient.newPublishMessageCommand().messageName(SEATS_VERIFIED.getName()).correlationKey(id).send().join();
        logger.info("The offer for reservation {} was accepted", id);
        return new ResponseEntity<>("Reservation change accepted", HttpStatus.OK);
    }

}
