package sv.edu.udb.airtickets.service;
import org.springframework.stereotype.Service; import sv.edu.udb.airtickets.domain.*; import sv.edu.udb.airtickets.repository.*; import java.time.*; 
@Service public class FlightService {
  private final FlightRepository flightRepo; private final AirportRepository airportRepo;
  public FlightService(FlightRepository flightRepo, AirportRepository airportRepo){ this.flightRepo=flightRepo; this.airportRepo=airportRepo; }
  public java.util.List<Flight> search(String originIata, String destIata, LocalDate date){
    var origin = airportRepo.findByIata(originIata).orElseThrow(()->new RuntimeException("Origin not found"));
    var dest = airportRepo.findByIata(destIata).orElseThrow(()->new RuntimeException("Destination not found"));
    var start = date.atStartOfDay(); var end = date.atTime(23,59,59);
    return flightRepo.findByOriginAndDestinationAndDepartureTimeBetween(origin, dest, start, end); }
}