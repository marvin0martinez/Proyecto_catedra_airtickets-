package sv.edu.udb.airtickets.service;
import org.springframework.boot.CommandLineRunner; import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder; import org.springframework.stereotype.Component;
import sv.edu.udb.airtickets.domain.*; import sv.edu.udb.airtickets.repository.*; import java.time.*; import java.util.*;
@Component public class InitDataService implements CommandLineRunner {
  private final RoleRepository roleRepo; private final UserRepository userRepo; private final AirlineRepository airlineRepo; private final AirportRepository airportRepo; private final FlightRepository flightRepo;
  public InitDataService(RoleRepository roleRepo, UserRepository userRepo, AirlineRepository airlineRepo, AirportRepository airportRepo, FlightRepository flightRepo){
    this.roleRepo=roleRepo; this.userRepo=userRepo; this.airlineRepo=airlineRepo; this.airportRepo=airportRepo; this.flightRepo=flightRepo; }
  @Override public void run(String... args){
    var enc = new BCryptPasswordEncoder();
    var adminRole = roleRepo.findByName("ADMIN").orElseGet(()->roleRepo.save(Role.builder().name("ADMIN").build()));
    var agentRole = roleRepo.findByName("AGENT").orElseGet(()->roleRepo.save(Role.builder().name("AGENT").build()));
    var userRole  = roleRepo.findByName("USER").orElseGet(()->roleRepo.save(Role.builder().name("USER").build()));
    if(userRepo.findByEmail("admin@air.tix").isEmpty())
      userRepo.save(User.builder().email("admin@air.tix").password(enc.encode("123456")).fullName("Admin").roles(Set.of(adminRole)).build());
    if(userRepo.findByEmail("agent@air.tix").isEmpty())
      userRepo.save(User.builder().email("agent@air.tix").password(enc.encode("123456")).fullName("Agent").roles(Set.of(agentRole)).build());
    if(userRepo.findByEmail("user@air.tix").isEmpty())
      userRepo.save(User.builder().email("user@air.tix").password(enc.encode("123456")).fullName("User").roles(Set.of(userRole)).build());
    var SAL = airportRepo.findByIata("SAL").orElseGet(()->airportRepo.save(Airport.builder().iata("SAL").city("San Salvador").country("El Salvador").build()));
    var MIA = airportRepo.findByIata("MIA").orElseGet(()->airportRepo.save(Airport.builder().iata("MIA").city("Miami").country("USA").build()));
    var PTY = airportRepo.findByIata("PTY").orElseGet(()->airportRepo.save(Airport.builder().iata("PTY").city("Panama City").country("Panama").build()));
    var LAS = airportRepo.findByIata("LAS").orElseGet(()->airportRepo.save(Airport.builder().iata("LAS").city("Las Vegas").country("USA").build()));
    var BOG = airportRepo.findByIata("BOG").orElseGet(()->airportRepo.save(Airport.builder().iata("BOG").city("Bogotá").country("Colombia").build()));
    var AV = airlineRepo.findByCode("AV").orElseGet(()->airlineRepo.save(Airline.builder().code("AV").name("Avianca").build()));
    var CM = airlineRepo.findByCode("CM").orElseGet(()->airlineRepo.save(Airline.builder().code("CM").name("Copa Airlines").build()));
    if(flightRepo.count()==0){
      for(int d=1; d<=10; d++){
        var date = LocalDate.now().plusDays(d);
        var dep1 = LocalDateTime.of(date, LocalTime.of(8, 0)); flightRepo.save(Flight.builder().airline(AV).origin(SAL).destination(MIA).aircraftType("A320").capacity(180).baseFare(220).departureTime(dep1).arrivalTime(dep1.plusHours(3)).build());
        var dep2 = LocalDateTime.of(date, LocalTime.of(9,30)); flightRepo.save(Flight.builder().airline(CM).origin(SAL).destination(PTY).aircraftType("737-800").capacity(160).baseFare(180).departureTime(dep2).arrivalTime(dep2.plusHours(2)).build());
        var dep3 = LocalDateTime.of(date, LocalTime.of(14,10)); flightRepo.save(Flight.builder().airline(CM).origin(SAL).destination(LAS).aircraftType("737 MAX 9").capacity(170).baseFare(420).departureTime(dep3).arrivalTime(dep3.plusHours(5)).build());
        var dep4 = LocalDateTime.of(date, LocalTime.of(7,45)); flightRepo.save(Flight.builder().airline(AV).origin(SAL).destination(BOG).aircraftType("A320neo").capacity(180).baseFare(210).departureTime(dep4).arrivalTime(dep4.plusHours(3)).build());
      }
    }
  }
}