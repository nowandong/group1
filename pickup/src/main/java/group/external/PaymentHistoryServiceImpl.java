package group.external;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class PaymentHistoryServiceImpl implements PaymentHistoryService {

    /**
     * 배송 fallback
     */
    public void pay(@RequestBody PaymentHistory paymentHistory) {
        System.out.println("@@@@@@@ 결제가 지연중 입니다. @@@@@@@@@@@@");
        System.out.println("@@@@@@@ 결제가 지연중 입니다. @@@@@@@@@@@@");
        System.out.println("@@@@@@@ 결제가 지연중 입니다. @@@@@@@@@@@@");
    }

}