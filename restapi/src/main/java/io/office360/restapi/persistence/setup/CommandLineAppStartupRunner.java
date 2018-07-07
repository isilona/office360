package io.office360.restapi.persistence.setup;


import io.office360.restapi.persistence.model.Patient;
import io.office360.restapi.service.IPatientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class CommandLineAppStartupRunner implements CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(CommandLineAppStartupRunner.class);

    private final IPatientService patientService;

    @Autowired
    public CommandLineAppStartupRunner(IPatientService patientService) {
        this.patientService = patientService;
    }

    @Override
    public void run(String... args) {


        if (logger.isInfoEnabled()) {
            logger.info("Application started with command-line arguments: {} .", Arrays.toString(args));
            logger.info("To kill this application, press Ctrl + C.");
        }

        logger.info("Executing Setup");

        createPatientRecords();

        logger.info("Setup Done");
    }


    // patient records
    public void createPatientRecords() {
        createPatientRecordIfNotexist("john");
        createPatientRecordIfNotexist("jane");
    }

    void createPatientRecordIfNotexist(String name) {
        final Patient entity = new Patient();
        entity.setName(name);

        if (patientService.findByName(name) == null) {
            patientService.create(entity);
        }
    }
}