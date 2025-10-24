package sv.edu.udb.airtickets.service;
import org.springframework.stereotype.Service; import sv.edu.udb.airtickets.domain.*; import sv.edu.udb.airtickets.repository.*; import sv.edu.udb.airtickets.util.SecurityUtil; import java.time.LocalDateTime; import java.util.List;
@Service public class ClaimService {
  private final ClaimRepository repo; private final ReservationRepository resRepo; private final UserRepository userRepo;
  public ClaimService(ClaimRepository repo, ReservationRepository resRepo, UserRepository userRepo){ this.repo=repo; this.resRepo=resRepo; this.userRepo=userRepo; }
  public Claim create(Long reservationId, String type, String description){
    var email = SecurityUtil.currentEmail(); var user = email!=null ? userRepo.findByEmail(email).orElse(null) : null;
    var res = reservationId!=null ? resRepo.findById(reservationId).orElse(null) : null;
    var c = Claim.builder().user(user).reservation(res).type(type).description(description).status("OPEN").createdAt(LocalDateTime.now()).build();
    return repo.save(c); }
  public List<Claim> myClaims(){ var email = SecurityUtil.currentEmail(); var user = userRepo.findByEmail(email).orElseThrow();
    return repo.findAll().stream().filter(c->c.getUser()!=null && c.getUser().getId().equals(user.getId())).toList(); }
  public Claim updateStatus(Long id, String status){ var c=repo.findById(id).orElseThrow(()->new RuntimeException("Claim not found")); c.setStatus(status); return repo.save(c); }
}