//package ru.sokol;
//
//import io.khasang.sokol.model.RequestStatus;
//import io.khasang.sokol.repository.RequestStatusRepository;
//import org.springframework.boot.ApplicationArguments;
//import org.springframework.boot.ApplicationRunner;
//import org.springframework.stereotype.Component;
//
//@Component
//public class SokolApplicationDataLoader implements ApplicationRunner {
//
//    private final RequestStatusRepository requestStatusRepository;
//
//    public SokolApplicationDataLoader(RequestStatusRepository requestStatusRepository) {
//        this.requestStatusRepository = requestStatusRepository;
//    }
//
//    @Override
//    public void run(ApplicationArguments args) throws Exception {
//        if(requestStatusRepository.count() == 0) {
//            RequestStatus status = new RequestStatus();
//            status.setId(1);
//            status.setName("NEW");
//            requestStatusRepository.save(status);
//        }
//    }
//}

