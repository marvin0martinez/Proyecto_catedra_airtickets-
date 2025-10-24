package sv.edu.udb.airtickets.service;
import org.springframework.stereotype.Service; import org.springframework.transaction.annotation.Transactional;
import sv.edu.udb.airtickets.domain.*; import sv.edu.udb.airtickets.repository.*; import sv.edu.udb.airtickets.util.SecurityUtil; import java.time.*;
@Service public class ReservationService {
  private final ReservationRepository resRepo; private final FlightRepository flightRepo; private final PassengerRepository paxRepo; private final UserRepository userRepo;
  public ReservationService(ReservationRepository resRepo, FlightRepository flightRepo, PassengerRepository paxRepo, UserRepository userRepo){
    this.resRepo=resRepo; this.flightRepo=flightRepo; this.paxRepo=paxRepo; this.userRepo=userRepo; }
  public Passenger createPassenger(Passenger p){ return paxRepo.save(p); }
  @Transactional public Reservation createReservation(Long flightId, Long paxId, String serviceClass){
    var flight = flightRepo.findById(flightId).orElseThrow(()->new RuntimeException("Flight not found"));
    if(flight.getBooked() >= flight.getCapacity()) throw new RuntimeException("No seats available");
    var pax = paxRepo.findById(paxId).orElseThrow(()->new RuntimeException("Passenger not found"));
    flight.setBooked(flight.getBooked()+1);
    var res = Reservation.builder().flight(flight).passenger(pax).serviceClass(serviceClass).status("PENDING")
      .amount(flight.getBaseFare() * ("BUSINESS".equalsIgnoreCase(serviceClass)?1.8:1.0))
      .createdAt(LocalDateTime.now()).locator(genLocator()).build();
    var email = SecurityUtil.currentEmail(); if(email!=null){ userRepo.findByEmail(email).ifPresent(res::setUser); }
    return resRepo.save(res); }
  public Reservation get(Long id){ return resRepo.findById(id).orElseThrow(()->new RuntimeException("Reservation not found")); }
  public Reservation getByLocator(String locator){ return resRepo.findByLocator(locator).orElseThrow(()->new RuntimeException("Reservation not found")); }
  public Reservation cancel(Long id){ var r=get(id); if("PAID".equals(r.getStatus())) throw new RuntimeException("Paid reservation cannot be cancelled here"); r.setStatus("CANCELLED"); return resRepo.save(r); }
  private String genLocator(){ String chars="ABCDEFGHJKLMNPQRSTUVWXYZ23456789"; String s=""; java.util.Random rnd=new java.util.Random(); for(int i=0;i<6;i++) s+=chars.charAt(rnd.nextInt(chars.length())); return s; }
}