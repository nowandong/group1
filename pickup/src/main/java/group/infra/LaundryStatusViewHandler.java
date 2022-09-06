package group.infra;

import group.config.kafka.KafkaProcessor;
import group.domain.*;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class LaundryStatusViewHandler {

    @Autowired
    private LaundryStatusRepository laundryStatusRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whenPickupRequested_then_CREATE_1(
        @Payload PickupRequested pickupRequested
    ) {
        try {
            if (!pickupRequested.validate()) return;

            // view 객체 생성
            LaundryStatus laundryStatus = new LaundryStatus();
            // view 객체에 이벤트의 Value 를 set 함
            laundryStatus.setReturnMethod(pickupRequested.getReturnMethod());
            laundryStatus.setPickupId(pickupRequested.getId());
            laundryStatus.setStatus(pickupRequested.getStatus());
            // view 레파지 토리에 save
            laundryStatusRepository.save(laundryStatus);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenReturnMethodChanged_then_UPDATE_1(
        @Payload ReturnMethodChanged returnMethodChanged
    ) {
        try {
            if (!returnMethodChanged.validate()) return;
            // view 객체 조회

            List<LaundryStatus> laundryStatusList = laundryStatusRepository.findByPickupId(
                returnMethodChanged.getId()
            );
            for (LaundryStatus laundryStatus : laundryStatusList) {
                // view 객체에 이벤트의 eventDirectValue 를 set 함
                laundryStatus.setReturnMethod(
                    returnMethodChanged.getReturnMethod()
                );
                // view 레파지 토리에 save
                laundryStatusRepository.save(laundryStatus);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenDeliveryStarted_then_UPDATE_2(
        @Payload DeliveryStarted deliveryStarted
    ) {
        try {
            if (!deliveryStarted.validate()) return;
            // view 객체 조회

            List<LaundryStatus> laundryStatusList = laundryStatusRepository.findByPickupId(
                deliveryStarted.getId()
            );
            for (LaundryStatus laundryStatus : laundryStatusList) {
                // view 객체에 이벤트의 eventDirectValue 를 set 함
                laundryStatus.setStatus("DeliveryStarted");
                // view 레파지 토리에 save
                laundryStatusRepository.save(laundryStatus);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @StreamListener(KafkaProcessor.INPUT)
    public void whenPickupCanceled_then_DELETE_1(
        @Payload PickupCanceled pickupCanceled
    ) {
        try {
            if (!pickupCanceled.validate()) return;
            // view 레파지 토리에 삭제 쿼리
            laundryStatusRepository.deleteByPickupId(pickupCanceled.getId());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // keep

}
