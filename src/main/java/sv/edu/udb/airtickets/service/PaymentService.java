package sv.edu.udb.airtickets.service;
import org.springframework.stereotype.Service; import sv.edu.udb.airtickets.domain.*; import sv.edu.udb.airtickets.repository.*; import java.time.LocalDateTime;
@Service public class PaymentService {
  private final PaymentRepository payRepo; private final ReservationRepository resRepo;
  public PaymentService(PaymentRepository payRepo, ReservationRepository resRepo){ this.payRepo=payRepo; this.resRepo=resRepo; }
  public Payment pay(Long reservationId, String method, double amount){
    var res = resRepo.findById(reservationId).orElseThrow(()->new RuntimeException("Reservation not found"));
    if(!"PENDING".equals(res.getStatus())) throw new RuntimeException("Reservation is not payable");
    if(amount < res.getAmount()) throw new RuntimeException("Insufficient amount");
    var p = Payment.builder().reservation(res).method(method).status("APPROVED").amount(amount).paidAt(LocalDateTime.now()).build();
    res.setStatus("PAID"); resRepo.save(res); return payRepo.save(p); }
}