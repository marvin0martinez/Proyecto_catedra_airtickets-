package sv.edu.udb.airtickets.util;
import org.springframework.security.core.context.SecurityContextHolder;
public class SecurityUtil { public static String currentEmail(){
  var a = SecurityContextHolder.getContext().getAuthentication(); return a!=null ? String.valueOf(a.getPrincipal()) : null; } }