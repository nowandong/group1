package group.infra;
import group.domain.*;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

@RestController
// @RequestMapping(value="/pickupHistories")
@Transactional
public class PickupHistoryController {
    @Autowired
    PickupHistoryRepository pickupHistoryRepository;




    @RequestMapping(value = "pickupHistories/{id}/cancel",
        method = RequestMethod.PUT,
        produces = "application/json;charset=UTF-8")
    public PickupHistory cancel(@PathVariable(value = "id") Long id, HttpServletRequest request, HttpServletResponse response) throws Exception {
            System.out.println("##### /pickupHistory/cancel  called #####");
            Optional<PickupHistory> optionalPickupHistory = pickupHistoryRepository.findById(id);
            
            optionalPickupHistory.orElseThrow(()-> new Exception("No Entity Found"));
            PickupHistory pickupHistory = optionalPickupHistory.get();
            pickupHistory.cancel();
            
            pickupHistoryRepository.save(pickupHistory);
            return pickupHistory;
            
    }
    



    @RequestMapping(value = "pickupHistories/{id}/changereturnmethod",
        method = RequestMethod.PUT,
        produces = "application/json;charset=UTF-8")
    public PickupHistory changeReturnMethod(@PathVariable(value = "id") Long id, @RequestBody ChangeReturnMethodCommand changeReturnMethodCommand, HttpServletRequest request, HttpServletResponse response) throws Exception {
            System.out.println("##### /pickupHistory/changeReturnMethod  called #####");
            Optional<PickupHistory> optionalPickupHistory = pickupHistoryRepository.findById(id);
            
            optionalPickupHistory.orElseThrow(()-> new Exception("No Entity Found"));
            PickupHistory pickupHistory = optionalPickupHistory.get();
            pickupHistory.changeReturnMethod(changeReturnMethodCommand);
            
            pickupHistoryRepository.save(pickupHistory);
            return pickupHistory;
            
    }
    



    // keep
}
